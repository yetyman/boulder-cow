package bouldercow.areas.playerboard;

import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class BuildingTracker implements IHoldsResources {
    public int maxValue = 13;
    public Building building;
    public Boulder boulder1;
    public Boulder boulder2;

    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        if (building != null) {
            re.resources.add(ResourceUnits.craftBuilding);
            re.values.add((double) building.value);
        }
        if (boulder1 != null) {
            re.resources.add(ResourceUnits.boulder);
            re.values.add((double) boulder1.value);
        }
        if (boulder2 != null) {
            re.resources.add(ResourceUnits.boulder);
            re.values.add((double) boulder2.value);
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.resources.contains(ResourceUnits.craftBuilding)) {
            int buildingIndex = resource.resources.indexOf(ResourceUnits.craftBuilding);
            double change = resource.values.get(buildingIndex);
            int newValue = building.value + (int) change;
            
            if (newValue < 0) return "Building value cannot be negative";
            if (newValue > maxValue) return "Building value cannot exceed maximum";
        }
        
        if (resource.resources.contains(ResourceUnits.boulder)) {
            int boulderIndex = resource.resources.indexOf(ResourceUnits.boulder);
            double change = resource.values.get(boulderIndex);
            
            if (boulder1 != null && boulder1.value + change < 0) return "Boulder value cannot be negative";
            if (boulder2 != null && boulder2.value + change < 0) return "Boulder value cannot be negative";
        }
        
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        ResourceEntry removed = new ResourceEntry();
        
        if (resource.resources.contains(ResourceUnits.craftBuilding)) {
            int buildingIndex = resource.resources.indexOf(ResourceUnits.craftBuilding);
            double change = resource.values.get(buildingIndex);
            if (change < 0) {
                removed.resources.add(ResourceUnits.craftBuilding);
                removed.values.add(-change);
            }
            building.value += (int) change;
        }
        
        if (resource.resources.contains(ResourceUnits.boulder)) {
            int boulderIndex = resource.resources.indexOf(ResourceUnits.boulder);
            double change = resource.values.get(boulderIndex);
            
            if (boulder1 != null) {
                if (change < 0) {
                    removed.resources.add(ResourceUnits.boulder);
                    removed.values.add(-change);
                }
                boulder1.value += (int) change;
            }
        }
        
        return removed;
    }
}
