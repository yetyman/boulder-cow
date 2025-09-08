package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;
import java.util.function.Predicate;

public class Cause {
    public boolean consumesRequired = false;
    public Phase requiredPhase = null;
    public ResourceSet requiredResources = null;
    public List<ResourceSet> stagedResources = null;//laying it out so that symbolic displays can be made
    private String description;
    private Predicate<ResourceSet> offering;

    public static Cause of(ResourceSet required, boolean consumes) {
        return null;
    }

    public void setComplexRequirement(String description, Predicate<ResourceSet> offering) {
        this.description = description;
        this.offering = offering;
    }
}
