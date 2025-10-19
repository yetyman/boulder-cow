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
}
