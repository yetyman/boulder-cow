package bouldercow.areas.playerboard;



import bouldercow.card.Card;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class TurnTracker  {
    public final int rounds = 6;
    public Card[] cards = new Card[rounds];
    public ResourceEntry[] sheeps = new ResourceEntry[rounds];

    public TurnTracker(){
        for (int i = 0; i < rounds; i++) {
            sheeps[i] = ResourceEntry.of(ResourceUnits.sheep, 0);
        }
    }

}
