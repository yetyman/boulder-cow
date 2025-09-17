package bouldercow.flow.effects;

import bouldercow.flow.Phase;

public class ReqAndEffect {
    public Requirement req;
    public Effect effect;

    public ReqAndEffect(){}
    public ReqAndEffect(Requirement req, Effect effect)
    {
        this.req = req;
        this.effect = effect;
    }

    public static ReqAndEffect of(ResourceEntry required, ResourceEntry gives, boolean consumes, boolean repeats) {
        Effect effect = Effect.of(gives, repeats, repeats? Phase.draw : null);

        return new ReqAndEffect(Requirement.of(required, consumes), effect);
    }

    public static ReqAndEffect consume(ResourceEntry required, ResourceEntry gives) {
        return of(required, gives, true, false);
    }
    public static ReqAndEffect have(ResourceEntry required, ResourceEntry gives) {
        return of(required, gives, false, false);
    }
    public static ReqAndEffect have(Requirement req, Effect effect) {
        return new ReqAndEffect(req, effect);
    }

    public static Requirement stagedC(ResourceEntry... staggeredSet) {
        Requirement c = Requirement.staged(staggeredSet);
        return c;
    }
    public static Effect stagedE(ResourceEntry... staggeredSet) {
        Effect c = Effect.of(staggeredSet);
        c.isStaged = true;
        return c;
    }
    public static Effect multiE(ResourceEntry... staggeredSet) {
        Effect c = Effect.of(staggeredSet);
        c.isStaged = false;
        return c;
    }

    public static Effect and(Effect effectA, Effect effectB) {
        Effect e = new Effect();
        e.multiEffects.add(effectA);
        e.multiEffects.add(effectB);
        e.isStaged = false;
        return e;
    }

    public static Effect and(Effect effectA, ResourceEntry moreGive) {
        Effect e = new Effect();
        e.multiEffects.add(effectA);
        e.multiEffects.add(Effect.of(moreGive, false, null));
        e.isStaged = false;

        return e;
    }
}
