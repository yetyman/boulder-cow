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
}
