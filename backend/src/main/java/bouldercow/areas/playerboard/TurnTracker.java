package bouldercow.areas.playerboard;



import bouldercow.card.Card;
import bouldercow.flow.effects.ResourceSet;
import bouldercow.flow.effects.ResourceUnits;

public class TurnTracker  {
    public final int rounds = 6;
    public Card[] cards = new Card[rounds];
    public ResourceSet[] sheeps = new ResourceSet[rounds];

    public TurnTracker(){
        for (int i = 0; i < rounds; i++) {
            sheeps[i] = ResourceSet.of(ResourceUnits.sheep, 0);
        }
    }

}
