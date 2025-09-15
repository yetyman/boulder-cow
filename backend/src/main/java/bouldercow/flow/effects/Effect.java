package bouldercow.flow.effects;

import bouldercow.areas.playerboard.PlayerArea;
import bouldercow.flow.Phase;
import bouldercow.model.GameState;
import bouldercow.player.Player;
import org.apache.commons.lang3.function.TriConsumer;
import org.apache.commons.lang3.function.TriFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Effect {
    public ResourceSet givesResources;
    public boolean repeats;
    public Phase repeatPhase;
    public boolean nonPassive;//indicates whether the user must choose the action
    List<Effect> multiEffects;
    boolean isStaged;
    private TriFunction<ResourceSet, GameState, Player, ResourceSet> specialEffect;

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
    public static Effect of(Function<ResourceSet,ResourceSet> gives) {
        Effect effect = new Effect();
        effect.specialEffect = (rs, state, player) -> gives.apply(rs);
        return effect;
    }
    public static Effect of(TriFunction<ResourceSet, GameState, Player, ResourceSet> gives) {
        Effect effect = new Effect();
        effect.specialEffect = gives;
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
