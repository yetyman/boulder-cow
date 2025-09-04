package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;

public class Effect {
    public ResourceSet givesResources;
    public boolean repeats;
    public Phase repeatPhase;
    public boolean nonPassive;//indicates whether the user must choose the action
    List<Effect> stagedEffects;
}
