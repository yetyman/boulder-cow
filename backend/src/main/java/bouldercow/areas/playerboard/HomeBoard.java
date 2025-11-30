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
        ResourceEntry re = new ResourceEntry();
        for (int i = 0; i < buildingRows.length; i++) {
            re.subEntries.add(buildingRows[i].allResources());
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        // Check if any building row can handle the modification
        for (BuildingTracker row : buildingRows) {
            String result = row.canModifyResource(resource);
            if (result == null) return null;
        }
        return "No valid building row for modification";
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        // Apply to first valid building row
        for (BuildingTracker row : buildingRows) {
            if (row.canModifyResource(resource) == null) {
                return row.modifyResource(resource);
            }
        }
        return ResourceEntry.empty();
    }
}
