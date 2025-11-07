package bouldercow.flow.effects;

import bouldercow.flow.Phase;

public class Effect {
    public ResourceEntry givesResources;
    public boolean repeats;
    public Phase repeatPhase;
    public boolean nonPassive;//indicates whether the user must choose the action

    public static Effect chooseReward(ResourceEntry... options) {
        Effect effect = new Effect();
        effect.givesResources = ResourceEntry.withSubEntries(EffectModifier.CHOOSE, options);
        return effect;
    }

    public static Effect of(ResourceEntry gives, boolean repeats, Phase phase) {
        Effect effect = new Effect();
        effect.givesResources = gives;
        effect.repeats = repeats;
        effect.repeatPhase = phase;
        return effect;
    }

    public static Effect of(ResourceEntry gives) {
        Effect effect = new Effect();
        effect.givesResources = gives;
        return effect;
    }

    public static Effect of(ResourceEntry[] staggeredSet) {
        Effect effect = new Effect();
        effect.givesResources = ResourceEntry.withSubEntries(EffectModifier.STAGED, staggeredSet);
        return effect;
    }
}
