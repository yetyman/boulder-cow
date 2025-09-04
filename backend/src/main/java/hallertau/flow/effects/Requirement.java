package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;

public class Requirement {
    boolean consumesRequired = false;
    Phase requiredPhase = null;
    ResourceSet requiredResources = null;
    List<Requirement> stagedRequirements = null;
}
