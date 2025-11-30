package bouldercow.areas.playerboard;


import bouldercow.flow.effects.EffectModifier;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

public class FarmLand implements IHoldsResources {

    public int value = 2;
    public int maxValue = 5;
    public ResourceUnits resource;

    public FarmLand() {

    }

    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        re.referenceUnits.add(ResourceUnits.fieldAnyLvl);
        re.referenceValues.add((double) value);

        if (resource != null) {
            re.resources.add(resource);
            re.values.add(1.0);
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.modifiers.contains(EffectModifier.LEVEL) || resource.modifiers.contains(EffectModifier.UPGRADE)) {
            int fieldIndex = resource.referenceValues.getFirst().intValue();
            double newLevel = resource.values.getFirst();
            if (resource.modifiers.contains(EffectModifier.UPGRADE)) {//upgrade is relative level set.
                newLevel += this.value;
            }

            if (newLevel < 2)
                return "Farm level must be more than 1";
                //you could short change 2 lvl upgrades from 4 to only 5, so only return an error when value was already max.
            else if (this.value == maxValue && newLevel > maxValue)
                return "Farm cannot exceed level 5";
        }

        //We need to handle sewing and reaping here. doesn't matter what the resource is, we just need to check if it can change
        //BUT, we also need to handle checks that will just remove the resources without reaping them
        // but that's no different in this method than normal reaping.
        if (this.resource == null && resource.referenceUnits.contains(ResourceUnits.harvest)) {
            return "Cannot harvest and empty field";
        }
        else if (this.resource != null && resource.referenceUnits.contains(ResourceUnits.sowAnyAction)) {
            return "Cannot plant on a sewn field";
        }

        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return null;

        if (resource.modifiers.contains(EffectModifier.LEVEL) || resource.modifiers.contains(EffectModifier.UPGRADE)) {
            int fieldIndex = resource.referenceValues.getFirst().intValue();
            double newLevel = resource.values.getFirst();
            if (resource.modifiers.contains(EffectModifier.UPGRADE)) {//upgrade is relative level set.
                newLevel += this.value;
            }

            value = (int) newLevel;
        }
        else {
            if (this.resource != null && resource.referenceUnits.contains(ResourceUnits.harvest)) {
                ResourceEntry re = ResourceEntry.of(this.resource, this.value);
                this.resource = null;
                return re;
            }
            else if (this.resource == null && resource.referenceUnits.contains(ResourceUnits.sowAnyAction)) {
                this.resource = resource.resources.get(0);
            }
        }
        return ResourceEntry.empty();
    }
}
