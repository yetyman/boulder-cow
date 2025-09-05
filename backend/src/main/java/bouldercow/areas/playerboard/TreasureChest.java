package bouldercow.areas.playerboard;




import java.util.ArrayList;
import java.util.List;

public class TreasureChest  {
    public List<RingPlacementLocation> ringLocations = new ArrayList<>();
    public RingPlacementLocation zeroLocation = new RingPlacementLocation();

    //holds ring placements including the zero spot.
    public List<RingPlacementLocation> consecutive = new ArrayList<>();

    public TreasureChest(){
        consecutive.add(zeroLocation);

        for (int i = 0; i < 10; i++) {
            RingPlacementLocation r = new RingPlacementLocation();
            ringLocations.add(r);
            consecutive.add(r);
        }

    }

    public static class RingPlacementLocation {
        public RingPlacementLocation(){
        }
    }
}
