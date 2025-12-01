package bouldercow.flow;

import bouldercow.flow.effects.Requirement;

public class ActionChoice {
    public String choiceId;
    public String description;
    public Requirement requirement;
    public Object choiceData;
    
    public ActionChoice(String choiceId, String description, Requirement requirement) {
        this.choiceId = choiceId;
        this.description = description;
        this.requirement = requirement;
    }
}