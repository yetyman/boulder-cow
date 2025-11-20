package bouldercow.areas.playerboard;



import bouldercow.card.Card;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class TurnTracker implements IHoldsResources {
    public final int rounds = 6;
    public Card[] cards = new Card[rounds];
    public ResourceEntry[] sheeps = new ResourceEntry[rounds+1];

    public TurnTracker(){
        for (int i = 0; i < rounds; i++) {
            sheeps[i] = ResourceEntry.of(ResourceUnits.sheep, 0);
        }
    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list sheep and cards. sheep have one more location than cards
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canAddResource(ResourceEntry resource) {
        //TODO: check removing sheep. return a message if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean addResource(ResourceEntry resource) {
        String canAdd = canAddResource(resource);
        if(canAdd != null) {
            throw new RuntimeException(canAdd);
        }

        //TODO: handle adding and removing sheep and cards. return false if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }
}
