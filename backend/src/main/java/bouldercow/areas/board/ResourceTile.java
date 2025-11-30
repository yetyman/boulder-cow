package bouldercow.areas.board;


import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;
import bouldercow.symbols.ImageRef;
import bouldercow.symbols.SymbolicDisplay;

public class ResourceTile implements IHoldsResources {
    public ImageRef image;
    public TitleText title = new TitleText();
    public WorkerPlacementLocation row3 = new WorkerPlacementLocation(3);
    public WorkerPlacementLocation row2 = new WorkerPlacementLocation(2);
    public WorkerPlacementLocation row1 = new WorkerPlacementLocation(1);
    public SymbolicDisplay symbolicDisplay;//filling the empty corner left by the partial rows 1 & 2

    public ResourceTile() {
    }

    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        if (row1.playerId != null) {
            re.resources.add(ResourceUnits.worker);
            re.values.add(1.0);
        }
        if (row2.playerId != null) {
            re.resources.add(ResourceUnits.worker);
            re.values.add(2.0);
        }
        if (row3.playerId != null) {
            re.resources.add(ResourceUnits.worker);
            re.values.add(3.0);
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.resources.contains(ResourceUnits.worker)) {
            int workerIndex = resource.resources.indexOf(ResourceUnits.worker);
            double workerCount = resource.values.get(workerIndex);
            
            if (workerCount == 1 && row1.playerId == null) return null;
            if (workerCount == 2 && row2.playerId == null) return null;
            if (workerCount == 3 && row3.playerId == null) return null;
            if (workerCount < 0) {
                if (workerCount == -1 && row1.playerId != null) return null;
                if (workerCount == -2 && row2.playerId != null) return null;
                if (workerCount == -3 && row3.playerId != null) return null;
            }
            return "Invalid worker placement";
        }
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        ResourceEntry removed = new ResourceEntry();
        if (resource.resources.contains(ResourceUnits.worker)) {
            int workerIndex = resource.resources.indexOf(ResourceUnits.worker);
            double workerCount = resource.values.get(workerIndex);
            
            if (workerCount == 1) row1.playerId = 1;
            else if (workerCount == 2) row2.playerId = 1;
            else if (workerCount == 3) row3.playerId = 1;
            else if (workerCount == -1) {
                row1.playerId = null;
                removed.resources.add(ResourceUnits.worker);
                removed.values.add(1.0);
            }
            else if (workerCount == -2) {
                row2.playerId = null;
                removed.resources.add(ResourceUnits.worker);
                removed.values.add(2.0);
            }
            else if (workerCount == -3) {
                row3.playerId = null;
                removed.resources.add(ResourceUnits.worker);
                removed.values.add(3.0);
            }
        }
        return removed;
    }
}
