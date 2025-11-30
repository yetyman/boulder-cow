package bouldercow.areas.playerboard;

import bouldercow.flow.effects.EffectModifier;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

import java.util.Arrays;

import static bouldercow.flow.effects.ReqAndEffectBuilder.require;
import static bouldercow.flow.effects.ResourceUnits.*;

public class ResourceTracker implements IHoldsResources {
    public FarmLand[] farms = new FarmLand[7];//4 rows of 7 items each
    public int maxValue = 5;
    public ResourceEntry resources = ResourceEntry.of(barley, 1, flax, 1, rye, 1);

    public ResourceTracker(){
        farms[0] = new FarmLand() {{ value = 4;}};
        farms[1] = new FarmLand() {{ value = 5;}};
        farms[2] = new FarmLand() {{ value = 3;}};
    }

    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        re.subEntries.add(resources);
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(resourceTracker);
        
        for (int i = 0; i < farms.length; i++) {
            if (farms[i] != null) {
                re.subEntries.add(farms[i].allResources());
                re.modifiers.add(EffectModifier.ON);
                re.referenceUnits.add(fieldAnyLvl);
            } else {
                re.subEntries.add(null);
            }
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.resources.contains(ResourceUnits.barley) || 
            resource.resources.contains(ResourceUnits.flax) || 
            resource.resources.contains(ResourceUnits.rye)) {
            
            for (ResourceUnits res : resource.resources) {
                int resIndex = resource.resources.indexOf(res);
                double change = resource.values.get(resIndex);
                
                if (resources.resources.contains(res)) {
                    int currentIndex = resources.resources.indexOf(res);
                    double currentAmount = resources.values.get(currentIndex);
                    if (currentAmount + change < 0) return "Cannot have negative resources";
                }
            }
        }

        //TODO: this step needs some work, we have too many ways to define a field

        //note. this is if the resource is the farm, not if the reference unit is the farm
        if (resource.resources.contains(ResourceUnits.fieldAnyLvl)) {
            boolean hasEmptyFarm = false;
            for (FarmLand farm : farms) {
                if (farm == null) {
                    hasEmptyFarm = true;
                    break;
                }
            }
            if (!hasEmptyFarm) return "No empty farmland available";
        }

        if (resource.referenceUnits.contains(fieldAnyLvl)) {
            boolean hasUnsownFarm = false;
            for (FarmLand farm : farms) {
                if (farm != null && farm.resource == null) {
                    hasUnsownFarm = true;
                    break;
                }
            }
            if (!hasUnsownFarm) return "No unsown farmland available";
        }
        
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        ResourceEntry removed = new ResourceEntry();
        
        for (int i = 0; i < resource.resources.size(); i++) {
            ResourceUnits res = resource.resources.get(i);
            double change = resource.values.get(i);
            
            if (resources.resources.contains(res)) {
                int currentIndex = resources.resources.indexOf(res);
                double currentAmount = resources.values.get(currentIndex);
                if (change < 0) {
                    removed.resources.add(res);
                    removed.values.add(-change);
                }
                resources.values.set(currentIndex, currentAmount + change);
            } else {
                if(res == fieldAnyLvl) {
                    for(int j = 0 ; j < farms.length; j++)
                        if(farms[j]==null) {
                            farms[j] = new FarmLand();
                            farms[j].value = (int) change;//TODO: i don't think this accounts for UPGRADE vs LEVEL, but those are applied on the existing farmland, not to making new ones.
                            break;
                        }
                } else {
                    resources.values.add(change);
                    resources.resources.add(res);
                }
            }
        }
        return removed;
    }
}
