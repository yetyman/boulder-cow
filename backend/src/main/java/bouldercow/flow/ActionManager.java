package bouldercow.flow;

import bouldercow.model.GameState;
import java.util.HashMap;
import java.util.Map;

public class ActionManager {
    private final Map<String, ActionProcessor> processors = new HashMap<>();
    
    public void registerProcessor(String locationId, ActionProcessor processor) {
        processors.put(locationId, processor);
    }
    
    public ActionResponse handleAction(ActionRequest request, GameState gameState) {
        ActionProcessor processor = processors.get(request.locationId);
        if (processor == null) {
            return ActionResponse.invalid("No processor for location: " + request.locationId);
        }
        return processor.processAction(request, gameState);
    }
}