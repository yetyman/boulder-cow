package bouldercow.controller;

import com.fasterxml.jackson.databind.SerializationFeature;
import bouldercow.board.SharedBoard;
import bouldercow.flow.Table;
import bouldercow.playerboard.PlayerArea;
import org.springframework.web.bind.annotation.*;
import bouldercow.model.GameState;
import bouldercow.model.PatchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.flipkart.zjsonpatch.JsonPatch;
import jakarta.servlet.http.HttpSession;
import bouldercow.websocket.GameWebSocketHandler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:5173")
@Component
public class GameController {

    public static GameState currentGame = new GameState();
    private static JsonNode lastSnapshot = null;
    private static final Map<String, Long> clientVersions = new HashMap<>();
    {
        Table table = new Table();
        currentGame.setTable(table);
        currentGame.getTable().setCurrentPlayerIndex(0);


        table.playerAreas = new ArrayList<>();
        table.playerAreas.add(new PlayerArea("Jody"));
        table.playerAreas.add(new PlayerArea("Chad"));
        table.playerAreas.add(new PlayerArea("Shane"));
        table.playerAreas.add(new PlayerArea("Neil"));

        table.sharedBoard = new SharedBoard();
    }

    @GetMapping("/state")
    public GameState getGameState() {
        System.out.println("GET /api/game/state called");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            String json = mapper.writeValueAsString(currentGame);
            System.out.println("Returning game state JSON: " + json);
        } catch (Exception e) {
            System.out.println("Error serializing game state: " + e.getMessage());
        }
        return currentGame;
    }
    
    @PostMapping("/action")
    public GameState performAction(@RequestBody String action) {
        currentGame.incrementVersion();
        return currentGame;
    }
    
    @PostMapping("/patch")
    public Object applyPatch(@RequestBody PatchRequest request, HttpSession session) {
        try {
            String clientId = session.getId();
            Long lastClientVersion = clientVersions.get(clientId);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            JsonNode currentState = mapper.valueToTree(currentGame);
            JsonNode newState = JsonPatch.apply(request.getPatches(), currentState);
            
            currentGame = mapper.treeToValue(newState, GameState.class);
            currentGame.incrementVersion();
            
            clientVersions.put(clientId, request.getClientVersion());
            
            // Broadcast update to all clients
            GameWebSocketHandler.broadcastUpdate(mapper.writeValueAsString(
                Map.of("type", "update", "version", currentGame.getVersion())
            ));
            
            return Map.of(
                "success", true, 
                "serverVersion", currentGame.getVersion(),
                "lastClientVersion", lastClientVersion != null ? lastClientVersion : 0L
            );
        } catch (Exception e) {
            return Map.of("success", false, "error", e.getMessage());
        }
    }
    
    @GetMapping("/delta")
    public Object getStateDelta(@RequestParam(required = false) Long since, HttpSession session) {
        try {
            String clientId = session.getId();
            Long lastClientVersion = clientVersions.get(clientId);
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            JsonNode currentSnapshot = mapper.valueToTree(currentGame);
            
            if (since == null || lastSnapshot == null) {
                lastSnapshot = currentSnapshot;
                return Map.of(
                    "serverVersion", currentGame.getVersion(),
                    "version", currentGame.getVersion(),
                    "lastClientVersion", lastClientVersion != null ? lastClientVersion : 0L,
                    "table", currentGame.getTable()
                );
            }
            
            JsonNode diff = JsonDiff.asJson(lastSnapshot, currentSnapshot);
            lastSnapshot = currentSnapshot;
            
            return Map.of(
                "serverVersion", currentGame.getVersion(),
                "version", currentGame.getVersion(),
                "lastClientVersion", lastClientVersion != null ? lastClientVersion : 0L,
                "diff", diff
            );
        } catch (Exception e) {
            return currentGame;
        }
    }
    
    @Scheduled(fixedRate = 1000)
    public void testVersionIncrement() {
        try {
            System.out.println("Incrementing version to: " + (currentGame.getVersion() + 1));

            //any change at all
            currentGame.incrementVersion();
            currentGame.getTable().playerAreas.getFirst().player.setName("Thaddius"+currentGame.getVersion());

            //sending an update as jsonDiff over webSockets
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            JsonNode currentSnapshot = mapper.valueToTree(currentGame);
            
            if (lastSnapshot != null) {
                JsonNode diff = JsonDiff.asJson(lastSnapshot, currentSnapshot);
                GameWebSocketHandler.broadcastUpdate(mapper.writeValueAsString(
                    Map.of("serverVersion", currentGame.getVersion(), "diff", diff)
                ));
            }
            lastSnapshot = currentSnapshot;
        } catch (Exception e) {
            // Handle error
        }
    }
}
