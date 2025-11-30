package bouldercow.areas.playerboard;


import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class FarmLand implements IHoldsResources {

    public int value = 2;
    public int maxValue = 5;
    public ResourceUnits resource;
    public FarmLand(){

    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list resource and farm level
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO: resource can be removed if there is a resource, farmland can be upgraded or down graded between(noninclusive) 2 and 5
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        String canAdd = canModifyResource(resource);
        if(canAdd != null) {
            throw new RuntimeException(canAdd);
        }

        //TODO: handle sowing and upgrading here.
        throw new RuntimeException("Not implemented yet");
    }
}
