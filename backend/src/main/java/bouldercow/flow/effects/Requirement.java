package bouldercow.flow.effects;

import java.util.function.Predicate;

public class Requirement {
    public boolean consumesRequired = false;
    public TimingRequirement timing = null;
    public ResourceEntry requiredResources = null;

    private String description;
    private Predicate<ResourceEntry> specialRequirement;

    public static Requirement of(ResourceEntry required, boolean consumes) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = required;
        requirement.consumesRequired = consumes;
        return requirement;
    }

    public static Requirement staged(ResourceEntry... staggeredSet) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = ResourceEntry.withSubEntries(EffectModifier.STAGED, staggeredSet);
        return requirement;
    }

    public static Requirement choose(ResourceEntry... set) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = ResourceEntry.withSubEntries(EffectModifier.CHOOSE, set);
        return requirement;
    }

    public static Requirement with(ResourceEntry... set) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = ResourceEntry.withSubEntries(EffectModifier.WITH, set);
        requirement.consumesRequired = true;
        return requirement;
    }

    public void setComplexRequirement(String description, Predicate<ResourceEntry> offering) {
        this.description = description;
        this.specialRequirement = offering;
    }
}
