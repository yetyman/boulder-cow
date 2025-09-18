package bouldercow.flow.effects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Requirement {
    public boolean consumesRequired = false;

    public TimingRequirement timing = null;

    public List<ResourceEntry> requiredResources = null;
    public List<Requirement> multiRequirement = null;//laying it out so that symbolic displays can be made
    public List<EffectModifier> modifiers = new ArrayList<>();

    private String description;
    private Predicate<ResourceEntry> specialRequirement;

    public static Requirement of(ResourceEntry required, boolean consumes) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = List.of(required);
        requirement.consumesRequired = consumes;

        return requirement;
    }

    public static Requirement staged(ResourceEntry... staggeredSet) {
        Requirement requirement = new Requirement();
        requirement.multiRequirement = new ArrayList<>();
        for (ResourceEntry resourceSet : staggeredSet) {
            requirement.multiRequirement.add(Requirement.of(resourceSet, false));
        }
        requirement.modifiers.add(EffectModifier.STAGED);

        return requirement;
    }
    public static Requirement or(ResourceEntry... set) {
        Requirement requirement = new Requirement();
        requirement.multiRequirement = new ArrayList<>();
        for (ResourceEntry resourceSet : set) {
            requirement.multiRequirement.add(Requirement.of(resourceSet, false));
        }
        requirement.modifiers.add(EffectModifier.EITHER);

        return requirement;
    }
    public static Requirement of(ResourceEntry... set) {
        Requirement requirement = new Requirement();
        requirement.multiRequirement = new ArrayList<>();
        requirement.multiRequirement.addAll(Arrays.asList(set));
        requirement.modifiers.add(EffectModifier.EITHER);

        return requirement;
    }

    public static Requirement and(Requirement requirement, ResourceUnits resourceUnits, int num) {
        Requirement requirement1 = new Requirement();
        requirement1.multiRequirement = Arrays.asList(requirement, Requirement.of(ResourceEntry.each(resourceUnits, num), false));
        requirement.modifiers.add(EffectModifier.STAGED);

        return requirement1;
    }

    public void setComplexRequirement(String description, Predicate<ResourceEntry> offering) {
        this.description = description;
        this.specialRequirement = offering;
    }
}
