package bouldercow.areas.playerboard;




import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class TreasureChest implements IHoldsResources {
    public ResourceEntry rings = ResourceEntry.of(ResourceUnits.jewelry, 0);
    public TreasureChest(){
    }

    @Override
    public ResourceEntry allResources() {
        return rings;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.resources.contains(ResourceUnits.jewelry)) {
            int jewelryIndex = resource.resources.indexOf(ResourceUnits.jewelry);
            double change = resource.values.get(jewelryIndex);
            double currentAmount = rings.values.get(0);
            double newAmount = currentAmount + change;
            
            if (newAmount < 0) return "Cannot have negative jewelry";
            if (newAmount > 10) return "Cannot exceed 10 jewelry";
        }
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        ResourceEntry removed = new ResourceEntry();
        if (resource.resources.contains(ResourceUnits.jewelry)) {
            int jewelryIndex = resource.resources.indexOf(ResourceUnits.jewelry);
            double change = resource.values.get(jewelryIndex);
            double currentAmount = rings.values.get(0);
            
            if (change < 0) {
                removed.resources.add(ResourceUnits.jewelry);
                removed.values.add(-change);
            }
            rings.values.set(0, currentAmount + change);
        }
        return removed;
    }
}
