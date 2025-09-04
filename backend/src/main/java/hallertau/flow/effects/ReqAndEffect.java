package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;

public class ReqAndEffect {
    public Requirement req;
    public Effect effect;

    public static ReqAndEffect of(ResourceSet required, ResourceSet gives, boolean consumes, boolean repeats) {
        ReqAndEffect reqAndEffect = new ReqAndEffect();
        reqAndEffect.req = new Requirement();
        reqAndEffect.req.requiredResources = required;
        reqAndEffect.req.consumesRequired = consumes;

        reqAndEffect.effect = new Effect();
        reqAndEffect.effect.givesResources = gives;
        if(repeats) {
            reqAndEffect.effect.repeats = true;
            reqAndEffect.effect.repeatPhase = Phase.draw;
        }

        return reqAndEffect;
    }

    public static ReqAndEffect ofStagedReward(List<Requirement> reqs, List<Effect> effects, boolean consumes) {
        ReqAndEffect reqAndEffect = new ReqAndEffect();
        reqAndEffect.req = new Requirement();
        reqAndEffect.req.stagedRequirements = reqs;
        reqAndEffect.req.consumesRequired = consumes;

        reqAndEffect.effect = new Effect();
        reqAndEffect.effect.stagedEffects = effects;

        return reqAndEffect;
    }
}
