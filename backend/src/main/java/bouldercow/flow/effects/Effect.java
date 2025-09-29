package bouldercow.flow.effects;

import bouldercow.flow.Phase;
import bouldercow.model.GameState;
import bouldercow.player.Player;
import org.apache.commons.lang3.function.TriFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Effect {
    public ResourceEntry givesResources;
    public boolean repeats;
    public Phase repeatPhase;
    public boolean nonPassive;//indicates whether the user must choose the action
    List<Effect> multiEffects;
    List<EffectModifier> modifiers;
    boolean isStaged;

    public static Effect chooseReward(ResourceEntry... options) {
        Effect effect = new Effect();
        effect.multiEffects = new ArrayList<>();

        for (ResourceEntry option : options) {
            Effect e = new Effect();
            effect.multiEffects.add(e);
        }
        effect.modifiers.add(EffectModifier.CHOOSE);
        effect.isStaged = false;

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

    public static Effect of(Effect eff, ResourceEntry[] staggeredSet) {
        Effect effect = new Effect();
        effect.multiEffects = new ArrayList<>();
        effect.multiEffects.add(eff);
        for (ResourceEntry resourceEntry : staggeredSet) {
            effect.multiEffects.add(Effect.of(resourceEntry));
        }
        return effect;
    }
    public static Effect of(ResourceEntry[] staggeredSet) {
        Effect effect = new Effect();
        effect.multiEffects = new ArrayList<>();
        for (ResourceEntry resourceEntry : staggeredSet) {
            effect.multiEffects.add(Effect.of(resourceEntry));
        }
        return effect;
    }
}
