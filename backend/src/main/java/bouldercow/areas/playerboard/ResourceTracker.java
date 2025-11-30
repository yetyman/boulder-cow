package bouldercow.areas.playerboard;

import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;

import static bouldercow.flow.effects.ReqAndEffectBuilder.require;
import static bouldercow.flow.effects.ResourceUnits.*;

public class ResourceTracker implements IHoldsResources {
    public FarmLand[] farms = new FarmLand[7];//4 rows of 7 items each
    public int maxValue = 5;
    public ResourceEntry resources = ResourceEntry.of(barley, 1, flax, 1, rye, 1);

    public ResourceTracker(){
        farms[0] = new FarmLand() {{ value = 4;}};
        farms[1] = new FarmLand() {{ value = 5;}};
        farms[2] = new FarmLand() {{ value = 3;}};
    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list the resources field in one sub entry and list each farmland with a sub entry
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO: check removing resources,farmlands, and resources on farmlands. also sowing crops can only be added to a farmland if an empty farmland exists
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        String canAdd = canModifyResource(resource);
        if(canAdd != null) {
            throw new RuntimeException(canAdd);
        }

        //TODO: handle adding and removing resources/farmland/farmland-crops.
        throw new RuntimeException("Not implemented yet");
    }
}
