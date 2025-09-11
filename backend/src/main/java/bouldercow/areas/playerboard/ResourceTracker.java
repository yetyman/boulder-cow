package bouldercow.areas.playerboard;

import bouldercow.flow.effects.ResourceSet;

import static bouldercow.flow.effects.ResourceUnits.*;

public class ResourceTracker  {
    public FarmLand[] farms = new FarmLand[7];//4 rows of 7 items each
    public int maxValue = 5;
    public ResourceSet resources = ResourceSet.all(barley, 1, flax, 1, wheat, 1);

    public ResourceTracker(){
        farms[0] = new FarmLand() {{ value = 4;}};
        farms[1] = new FarmLand() {{ value = 5;}};
        farms[2] = new FarmLand() {{ value = 3;}};
    }
}
