package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;

public class CauseAndEffect {
    public Cause req;
    public Effect effect;

    public CauseAndEffect(){}
    public CauseAndEffect(Cause req, Effect effect)
    {
        this.req = req;
        this.effect = effect;
    }

    public static CauseAndEffect of(ResourceSet required, ResourceSet gives, boolean consumes, boolean repeats) {
        Effect effect = Effect.of(gives, repeats, repeats? Phase.draw : null);

        return new CauseAndEffect(Cause.of(required, consumes), effect);
    }
}
