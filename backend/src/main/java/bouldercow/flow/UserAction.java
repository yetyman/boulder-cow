package bouldercow.flow;

import bouldercow.flow.effects.CauseAndEffect;
import bouldercow.model.GameState;

public class UserAction {
    public int cardIndex;
    public CauseAndEffect causeAndEffect;
    //more possible card actions


    public GameState doAction(GameState gameState){
        return gameState;
    }
}
