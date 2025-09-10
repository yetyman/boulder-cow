package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.ArrayList;
import java.util.List;

public class Effect {
    public ResourceSet givesResources;
    public boolean repeats;
    public Phase repeatPhase;
    public boolean nonPassive;//indicates whether the user must choose the action
    List<Effect> multiEffects;
    boolean isStaged;

    public static Effect of(ResourceSet gives, boolean repeats, Phase phase) {
        Effect effect = new Effect();
        effect.givesResources = gives;
        effect.repeats = repeats;
        effect.repeatPhase = phase;

        return effect;
    }

    public static Effect of(ResourceSet gives) {
        Effect effect = new Effect();
        effect.givesResources = gives;
        return effect;
    }

    public static Effect of(ResourceSet[] staggeredSet) {
        Effect effect = new Effect();
        effect.multiEffects = new ArrayList<>();
        for (ResourceSet resourceSet : staggeredSet) {
            effect.multiEffects.add(Effect.of(resourceSet));
        }
        return effect;
    }
}
