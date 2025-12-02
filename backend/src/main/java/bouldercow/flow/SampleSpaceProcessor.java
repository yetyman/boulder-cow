package bouldercow.flow;

import bouldercow.flow.effects.*;
import bouldercow.model.GameState;
import java.util.Arrays;
import java.util.List;

public class SampleSpaceProcessor implements ActionProcessor {
    
    @Override
    public ActionResponse processAction(ActionRequest request, GameState gameState) {
        if ("select".equals(request.actionType)) {
            return ActionResponse.choices(getAvailableChoices(request.locationId, gameState));
        }
        
        if ("choose".equals(request.actionType)) {
            String choiceId = (String) request.actionData;
            // Validate choice and requirements here
            return ActionResponse.completed();
        }
        
        return ActionResponse.invalid("Unknown action type: " + request.actionType);
    }
    
    @Override
    public List<ActionChoice> getAvailableChoices(String locationId, GameState gameState) {
        return Arrays.asList(
            new ActionChoice("choice1", "Take 2 rye", 
                Requirement.of(ResourceEntry.exactly(ResourceUnits.rye, 0d), false)),
            new ActionChoice("choice2", "Trade 1 sheep for 2 barley", 
                Requirement.of(ResourceEntry.exactly(ResourceUnits.sheep, 1d), true))
        );
    }
}