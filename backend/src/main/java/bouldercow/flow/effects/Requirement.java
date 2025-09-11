package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Requirement {
    public boolean consumesRequired = false;
    public Phase requiredPhase = null;
    //flips required phase to excluded phase.
    public boolean invertPhaseRequirement = false;
    public ResourceSet requiredResources = null;
    public List<Requirement> multiRequirement = null;//laying it out so that symbolic displays can be made
    boolean isStaged;
    boolean isXMoreThanY;

    private String description;
    private Predicate<ResourceSet> specialRequirement;

    public static Requirement of(ResourceSet required, boolean consumes) {
        Requirement requirement = new Requirement();
        requirement.requiredResources = required;
        requirement.consumesRequired = consumes;

        return requirement;
    }

    public static Requirement staged(ResourceSet... staggeredSet) {
        Requirement requirement = new Requirement();
        requirement.multiRequirement = new ArrayList<>();
        for (ResourceSet resourceSet : staggeredSet) {
            requirement.multiRequirement.add(Requirement.of(resourceSet, false));
        }
        requirement.isStaged = true;
        requirement.isXMoreThanY = false;
        requirement.consumesRequired = false;

        return requirement;
    }

    public static Requirement and(Requirement requirement, ResourceUnits resourceUnits, int num) {
        Requirement requirement1 = new Requirement();
        requirement1.multiRequirement = Arrays.asList(requirement, Requirement.of(ResourceSet.all(resourceUnits, num), false));
        requirement1.isStaged = false;
        requirement1.isXMoreThanY = false;
        requirement1.consumesRequired = false;

        return requirement1;
    }

    public static Requirement moreXThanY(ResourceUnits resourceUnits, ResourceUnits resourceUnits2) {
        Requirement requirement = new Requirement();

        requirement.multiRequirement = new ArrayList<>();
        requirement.multiRequirement.add(Requirement.of(ResourceSet.all(resourceUnits, 1), false));
        requirement.multiRequirement.add(Requirement.of(ResourceSet.all(resourceUnits2, 1), false));
        requirement.isXMoreThanY = true;
        requirement.consumesRequired = false;

        return requirement;
    }
    public static Requirement moreXThanY(ResourceSet resources, ResourceUnits resourceUnits2) {
        Requirement requirement = new Requirement();

        requirement.multiRequirement = new ArrayList<>();
        requirement.multiRequirement.add(Requirement.of(resources, false));
        requirement.multiRequirement.add(Requirement.of(ResourceSet.all(resourceUnits2, 1), false));
        requirement.isXMoreThanY = true;
        requirement.consumesRequired = false;

        return requirement;
    }

    public void setComplexRequirement(String description, Predicate<ResourceSet> offering) {
        this.description = description;
        this.specialRequirement = offering;
    }
}
