package bouldercow.areas.playerboard;


import bouldercow.flow.Phase;
import bouldercow.flow.effects.*;

public class HomeBoard  {
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

    private CauseAndEffect getBuildingRequirement(int i) {
        Effect moveForward = Effect.of(ResourceSet.of(ResourceUnits.building, 1));

        Cause cost = new Cause();
        //just for symbolic displays, include a resource set.
        // the 1max flax spot makes an object definition more complex than i want to encode in structure until i have a clearer picture
        cost.requiredResources = ResourceSet.of(ResourceUnits.wheat, 1, ResourceUnits.barley, 1);

        //complex requirements being functional isn't ideal, we can't generate symbolic displays off of that,
        // but we'll break it down once we see how complicated it is, or just create symbolic displays manually for it.
        cost.setComplexRequirement("At least x of y with z", (ResourceSet resourcesOffered)->{
            //TODO: return either success or failure.
            //TODO: base cost on which round we're in.

            return true;
        });
        cost.consumesRequired = true;
        cost.requiredPhase = Phase.build;

        return new CauseAndEffect(cost, moveForward);
    }
}
