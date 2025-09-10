package bouldercow.flow;

import bouldercow.flow.effects.ReqAndEffect;
import bouldercow.model.GameState;

public class UserAction {
    public int cardIndex;
    public ReqAndEffect reqAndEffect;
    //more possible card actions


    public GameState doAction(GameState gameState){
        return gameState;
    }
}
