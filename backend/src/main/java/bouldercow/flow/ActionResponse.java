package bouldercow.flow;

import bouldercow.flow.effects.Requirement;
import java.util.List;

public class ActionResponse {
    public boolean valid;
    public String message;
    public List<ActionChoice> choices;
    public boolean completed;
    
    public static ActionResponse invalid(String message) {
        ActionResponse response = new ActionResponse();
        response.valid = false;
        response.message = message;
        return response;
    }
    
    public static ActionResponse choices(List<ActionChoice> choices) {
        ActionResponse response = new ActionResponse();
        response.valid = true;
        response.choices = choices;
        return response;
    }
    
    public static ActionResponse completed() {
        ActionResponse response = new ActionResponse();
        response.valid = true;
        response.completed = true;
        return response;
    }
}