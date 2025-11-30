package bouldercow.areas.playerboard;


import bouldercow.flow.Phase;
import bouldercow.flow.effects.*;

import static bouldercow.flow.effects.Requirement.with;
import static bouldercow.flow.effects.ResourceEntry.*;
import static bouldercow.flow.effects.ResourceUnits.*;

public class HomeBoard implements IHoldsResources  {
    public HomeBGImage homeBGImage = new HomeBGImage();
    public HomeImage homeImage = new HomeImage();
    public BuildingTracker[] buildingRows = new BuildingTracker[5];

    public HomeBoard(){
        //will move forward columns throughout game

        for (int i = 0; i < buildingRows.length; i++) {//row
             buildingRows[i] = new BuildingTracker();
             buildingRows[i].building = new Building(getBuildingRequirement(i));
             buildingRows[i].boulder1  = new Boulder() {{ value = 4; }};
             buildingRows[i].boulder2 = new Boulder() {{ value = 6; }};
        }
    }

    private ReqAndEffect getBuildingRequirement(int i) {
        Effect moveForward = Effect.of(ResourceEntry.of(ResourceUnits.craftBuilding, 1));

        Requirement cost;
        //just for symbolic displays, include a resource set.
        // the 1max flax spot makes an object definition more complex than i want to encode in structure until i have a clearer picture
        cost = Requirement.choose(
            with(template(rye, barley, flax, total(4)), upTo(flax, 1), distinct(3)),//total 4, atleast one of each, max 1 flax
            with(template(rye, barley, flax, total(5)), upTo(flax, 1), distinct(2)),//total 5, atleast two kinds, max 1 flax
            with(template(rye, barley, flax, total(6)), upTo(flax, 1))//total 6, max 1 flax
        );
//        choose(of(rye, meat, total(3)), of(choose(rye,meat), 2), 1);
        //complex requirements being functional isn't ideal, we can't generate symbolic displays off of that,
        // but we'll break it down once we see how complicated it is, or just create symbolic displays manually for it.
        cost.consumesRequired = true;
        cost.timing = TimingRequirement.timing(Phase.buildings);

        return new ReqAndEffect(cost, moveForward);
    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list each building row. so building value, boulder value, and other boulder value
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO: check moving buildings and boulders. return false if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        //TODO: handle moving buildings and boulders. return false if the change would make it negative
        throw new RuntimeException("Not implemented yet");
    }
}
