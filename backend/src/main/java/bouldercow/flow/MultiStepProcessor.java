package bouldercow.flow;

import bouldercow.flow.effects.*;
import bouldercow.model.GameState;
import java.util.Arrays;
import java.util.List;

public class MultiStepProcessor implements ActionProcessor {
    
    @Override
    public ActionResponse processAction(ActionRequest request, GameState gameState) {
        if ("select".equals(request.actionType)) {
            return ActionResponse.choices(getStep1Choices());
        }
        
        if ("choose".equals(request.actionType)) {
            String choiceId = (String) request.actionData;
            
            if ("step1_choice1".equals(choiceId)) {
                return ActionResponse.choices(getStep2Choices());
            }
            if ("step2_choice1".equals(choiceId)) {
                return ActionResponse.choices(getStep3Choices());
            }
            if ("step3_choice1".equals(choiceId)) {
                return ActionResponse.completed();
            }
        }
        
        return ActionResponse.invalid("Invalid action");
    }
    
    @Override
    public List<ActionChoice> getAvailableChoices(String locationId, GameState gameState) {
        return getStep1Choices();
    }
    
    private List<ActionChoice> getStep1Choices() {
        return Arrays.asList(
            new ActionChoice("step1_choice1", "Choose resources to trade", 
                Requirement.of(ResourceEntry.exactly(ResourceUnits.sheep, 1), true))
        );
    }
    
    private List<ActionChoice> getStep2Choices() {
        return Arrays.asList(
            new ActionChoice("step2_choice1", "Select what to receive", 
                Requirement.of(ResourceEntry.exactly(ResourceUnits.rye, 0), false))
        );
    }
    
    private List<ActionChoice> getStep3Choices() {
        return Arrays.asList(
            new ActionChoice("step3_choice1", "Confirm trade", 
                Requirement.of(ResourceEntry.empty(), false))
        );
    }
}