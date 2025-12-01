package bouldercow.flow;

import bouldercow.model.GameState;
import java.util.List;

public interface ActionProcessor {
    ActionResponse processAction(ActionRequest request, GameState gameState);
    List<ActionChoice> getAvailableChoices(String locationId, GameState gameState);
}