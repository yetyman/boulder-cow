package bouldercow.areas.playerboard;




import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class TreasureChest implements IHoldsResources {
    public ResourceEntry rings = ResourceEntry.of(ResourceUnits.jewelry, 0);
    public TreasureChest(){
    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list rings
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canAddResource(ResourceEntry resource) {
        //TODO: check adding and removing rings. max 10. return false if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean addResource(ResourceEntry resource) {
        String canAdd = canAddResource(resource);
        if(canAdd != null) {
            throw new RuntimeException(canAdd);
        }

        //TODO: handle adding and removing rings. max 10. return false if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }
}
