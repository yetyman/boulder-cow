package bouldercow.playerboard;




import java.util.ArrayList;
import java.util.List;

public class ResourceTracker  {
    public FarmPlacementLocation[][] farm = new FarmPlacementLocation[4][7];//4 rows of 7 items each
    public List<FarmLand> farmLand = new ArrayList<>();
    public Counter counter5 = new Counter(5);
    public Counter counter4 = new Counter(4);
    public Counter counter3 = new Counter(3);
    public Counter counter2 = new Counter(2);
    public Counter counter1 = new Counter(1);

    public List<Counter> counters = new ArrayList<>();

    public ResourceTracker(){
        

        for (int i = 0; i < farm.length; i++) {
            for (int j = 0; j < farm[i].length; j++) {
                farm[i][j] = new FarmPlacementLocation();
            }
        }

        farmLand.add(new FarmLand() {{value = 4;}});
        farmLand.add(new FarmLand() {{value = 5;}});
        farmLand.add(new FarmLand() {{value = 3;}});

        counters.addAll(List.of(counter1, counter2, counter3, counter4, counter5));

        
        
        
    }
}
