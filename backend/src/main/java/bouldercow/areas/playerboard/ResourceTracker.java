package bouldercow.areas.playerboard;

import bouldercow.flow.effects.ReqAndEffect;
import bouldercow.flow.effects.ReqAndEffectBuilder;
import bouldercow.flow.effects.ResourceEntry;

import static bouldercow.flow.effects.ReqAndEffectBuilder.require;
import static bouldercow.flow.effects.ResourceUnits.*;

public class ResourceTracker  {
    public FarmLand[] farms = new FarmLand[7];//4 rows of 7 items each
    public int maxValue = 5;
    public ResourceEntry resources = ResourceEntry.of(barley, 1, flax, 1, rye, 1);

    public ResourceTracker(){
        farms[0] = new FarmLand() {{ value = 4;}};
        farms[1] = new FarmLand() {{ value = 5;}};
        farms[2] = new FarmLand() {{ value = 3;}};
    }
}
