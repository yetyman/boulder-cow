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
        ResourceEntry re = new ResourceEntry();
        
        for (int i = 0; i < rounds; i++) {
            if (cards[i] != null) {
                re.resources.add(cards[i].cardType);
                re.values.add((double) cards[i].appendixIndex);
            }
            re.subEntries.add(sheeps[i]);
        }
        re.subEntries.add(sheeps[rounds]); // Extra sheep location
        
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO: we should be checking for the upgrade modifier here. which indicates that we are upgrading an existing sheep rather than adding a new one.
        if (resource.resources.contains(ResourceUnits.sheep)) {
            int sheepIndex = resource.resources.indexOf(ResourceUnits.sheep);
            double change = resource.values.get(sheepIndex);
            
            // Check if any sheep location would go negative
            for (int i = 0; i <= rounds; i++) {
                double currentSheep = sheeps[i].values.get(0);
                if (currentSheep + change < 0) {
                    return "Cannot have negative sheep";
                }
            }
        }
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();

        ResourceEntry removed = new ResourceEntry();
        
        //TODO: we should be checking for the upgrade modifier here. which indicates that we are upgrading an existing sheep rather than adding a new one.
        // the LEVEL modifier and a value would be on any sheep we are adding fresh to tell us which round index it should go on.
        if (resource.resources.contains(ResourceUnits.sheep)) {
            int sheepIndex = resource.resources.indexOf(ResourceUnits.sheep);
            double change = resource.values.get(sheepIndex);
            
            // Apply change to first available sheep location
            for (int i = 0; i <= rounds; i++) {
                double currentSheep = sheeps[i].values.get(0);
                if (change < 0) {
                    removed.resources.add(ResourceUnits.sheep);
                    removed.values.add(-change);
                }
                sheeps[i].values.set(0, currentSheep + change);
                break;
            }
        }
        return removed;
    }
}
