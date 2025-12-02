package bouldercow.controller;

import bouldercow.areas.board.SharedBoard;
import bouldercow.flow.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import bouldercow.areas.playerboard.PlayerArea;
import org.springframework.web.bind.annotation.*;
import bouldercow.model.GameState;
import bouldercow.model.PatchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
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
@CrossOrigin(origins = {"http://localhost:5173", "https://localhost:5173"})
@Component
public class GameController {

    public static GameState currentGame = new GameState();
    private static JsonNode lastSnapshot = null;
    private static final Map<String, Long> clientVersions = new HashMap<>();
    private static final Map<String, JsonNode> clientStates = new HashMap<>();
    private static final List<JsonNode> patchHistory = new ArrayList<>();
    private final ActionManager actionManager = new ActionManager();
    {
        Table table = new Table();
        currentGame.table = table;
        currentGame.table.currentPlayerIndex = 0;


        table.playerAreas = new ArrayList<>();
        table.playerAreas.add(new PlayerArea("Jody"));
        table.playerAreas.add(new PlayerArea("Chad"));
        table.playerAreas.add(new PlayerArea("Shane"));
        table.playerAreas.add(new PlayerArea("Neil"));

        table.sharedBoard = new SharedBoard();
        
        // Register sample processors
        actionManager.registerProcessor("sampleSpace", new SampleSpaceProcessor());
    }

    @GetMapping("/state")
    public GameState getGameState(HttpSession session) {
        System.out.println("GET /api/game/state called");
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            String json = mapper.writeValueAsString(currentGame);
            System.out.println("Returning game state JSON: " + json);
            
            // Cache client's known state
            String clientId = session.getId();
            clientStates.put(clientId, mapper.valueToTree(currentGame));
            clientVersions.put(clientId, currentGame.version);
        } catch (Exception e) {
            System.out.println("Error serializing game state: " + e.getMessage());
        }
        return currentGame;
    }
    
    @PostMapping("/action")
    public ActionResponse performAction(@RequestBody ActionRequest request) {
        ActionResponse response = actionManager.handleAction(request, currentGame);
        if (response.valid && response.completed) {
            currentGame.incrementVersion();
        }
        return response;
    }
    
    @PostMapping("/undo")
    public Object undo(HttpSession session) {
        if (patchHistory.isEmpty()) {
            return Map.of("success", false, "message", "No actions to undo");
        }
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            
            JsonNode lastPatch = patchHistory.remove(patchHistory.size() - 1);
            JsonNode inversePatch = JsonDiff.asJson(
                JsonPatch.apply(lastPatch, mapper.valueToTree(currentGame)),
                mapper.valueToTree(currentGame)
            );
            
            PatchRequest undoRequest = new PatchRequest();
            undoRequest.setPatches(inversePatch);
            
            Object result = applyPatch(undoRequest, session);
            
            // Remove the undo patch from history and set version from result
            if (!patchHistory.isEmpty()) {
                patchHistory.remove(patchHistory.size() - 1);
                if (result instanceof Map) {
                    Object serverVersion = ((Map<?, ?>) result).get("serverVersion");
                    if (serverVersion instanceof Long) {
                        currentGame.version = (Long) serverVersion;
                    }
                }
            }
            
            return result;
        } catch (Exception e) {
            return Map.of("success", false, "error", e.getMessage());
        }
    }
    
    @PostMapping("/patch")
    public Object applyPatch(@RequestBody PatchRequest request, HttpSession session) {
        try {
            String clientId = session.getId();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            
            JsonNode clientLastKnownState = clientStates.get(clientId);
            JsonNode currentState = mapper.valueToTree(currentGame);
            
            // Check for conflicts if we have the client's last known state
            if (clientLastKnownState != null && !clientLastKnownState.equals(currentState)) {
                JsonNode serverChanges = JsonDiff.asJson(clientLastKnownState, currentState);
                if (hasConflict(request.getPatches(), serverChanges)) {
                    JsonNode alignmentPatch = JsonDiff.asJson(clientLastKnownState, currentState);
                    return Map.of(
                        "success", false, 
                        "error", "Conflict detected", 
                        "serverVersion", currentGame.getClass(),
                        "alignmentPatch", alignmentPatch
                    );
                }
            }
            
            JsonNode newState = JsonPatch.apply(request.getPatches(), currentState);
            currentGame = mapper.treeToValue(newState, GameState.class);
            currentGame.incrementVersion();
            
            // Update client cache
            clientStates.put(clientId, mapper.valueToTree(currentGame));
            clientVersions.put(clientId, currentGame.version);
            
            // Store patches in history
            patchHistory.add(request.getPatches());
            
            // Broadcast changes using original patches
            String broadcastMessage = mapper.writeValueAsString(
                Map.of("serverVersion", currentGame.version, "diff", request.getPatches())
            );
            System.out.println("Broadcasting to clients: " + broadcastMessage);
            GameWebSocketHandler.broadcastUpdate(broadcastMessage);
            
            return Map.of("success", true, "serverVersion", currentGame.version);
        } catch (Exception e) {
            return Map.of("success", false, "error", e.getMessage());
        }
    }
    
    private boolean hasConflict(JsonNode clientPatches, JsonNode serverChanges) {
        for (JsonNode clientPatch : clientPatches) {
            String clientPath = clientPatch.get("path").asText();
            for (JsonNode serverChange : serverChanges) {
                String serverPath = serverChange.get("path").asText();
                if (clientPath.equals(serverPath)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    @GetMapping("/delta")
    public Object getStateDelta(@RequestParam(required = false) Long since, HttpSession session) {
        try {
            String clientId = session.getId();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            JsonNode currentSnapshot = mapper.valueToTree(currentGame);
            
            // Update client cache
            clientStates.put(clientId, currentSnapshot);
            clientVersions.put(clientId, currentGame.version);
            
            if (since == null || lastSnapshot == null) {
                lastSnapshot = currentSnapshot;
                return Map.of(
                    "serverVersion", currentGame.version,
                    "table", currentGame.table
                );
            }
            
            JsonNode diff = JsonDiff.asJson(lastSnapshot, currentSnapshot);
            lastSnapshot = currentSnapshot;
            
            return Map.of(
                "serverVersion", currentGame.version,
                "diff", diff
            );
        } catch (Exception e) {
            return currentGame;
        }
    }
    
    // @Scheduled(fixedRate = 1000)
    // public void testVersionIncrement() {
    //     try {
    //         System.out.println("Incrementing version to: " + (currentGame.getVersion() + 1));

    //         //any change at all
    //         currentGame.incrementVersion();
    //         currentGame.getTable().playerAreas.getFirst().player.setName("Thaddius"+currentGame.getVersion());

    //         //sending an update as jsonDiff over webSockets
    //         ObjectMapper mapper = new ObjectMapper();
    //         mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    //         JsonNode currentSnapshot = mapper.valueToTree(currentGame);
            
    //         if (lastSnapshot != null) {
    //             JsonNode diff = JsonDiff.asJson(lastSnapshot, currentSnapshot);
    //             GameWebSocketHandler.broadcastUpdate(mapper.writeValueAsString(
    //                 Map.of("serverVersion", currentGame.getVersion(), "diff", diff)
    //             ));
    //         }
    //         lastSnapshot = currentSnapshot;
    //     } catch (Exception e) {
    //         // Handle error
    //     }
    // }
}
