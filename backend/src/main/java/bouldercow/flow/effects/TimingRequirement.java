package bouldercow.flow.effects;

import bouldercow.flow.Phase;

public class TimingRequirement {
    public Phase requiredPhase = null;
    //flips required phase to excluded phase.
    public boolean invertPhaseRequirement = false;
    public ResourceUnits reactionTo;
    public boolean repeats = false;
    public boolean nonPassive = false;//indicates whether the user must choose the action

    public static TimingRequirement timing(Phase requiredPhase) {
        TimingRequirement timingRequirement = new TimingRequirement();
        timingRequirement.requiredPhase = requiredPhase;
        return timingRequirement;
    }
    public static TimingRequirement timing(Phase requiredPhase, ResourceUnits reactionTo) {
        TimingRequirement timingRequirement = new TimingRequirement();
        timingRequirement.requiredPhase = requiredPhase;
        timingRequirement.reactionTo = reactionTo;
        return timingRequirement;
    }
    public static TimingRequirement timing(Phase requiredPhase, Boolean  invertPhaseRequirement) {
        TimingRequirement timingRequirement = new TimingRequirement();
        timingRequirement.requiredPhase = requiredPhase;
        timingRequirement.invertPhaseRequirement = invertPhaseRequirement;
        return timingRequirement;
    }
    public static TimingRequirement timing(ResourceUnits reactionTo) {
        TimingRequirement timingRequirement = new TimingRequirement();
        timingRequirement.reactionTo = reactionTo;
        return timingRequirement;
    }
}
