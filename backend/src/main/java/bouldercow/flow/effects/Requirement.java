package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;
import java.util.function.Predicate;

public class Requirement {
    public boolean consumesRequired = false;
    public Phase requiredPhase = null;
    //flips required phase to excluded phase.
    public boolean invertPhaseRequirement = false;
    public ResourceSet requiredResources = null;
    public List<ResourceSet> stagedResources = null;//laying it out so that symbolic displays can be made
    private String description;
    private Predicate<ResourceSet> offering;

    public static Requirement of(ResourceSet required, boolean consumes) {
        return null;
    }

    public static Requirement of(ResourceSet[] staggeredSet) {
        return null;
    }

    public void setComplexRequirement(String description, Predicate<ResourceSet> offering) {
        this.description = description;
        this.offering = offering;
    }
}
