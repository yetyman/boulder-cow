package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.List;

public class ReqAndEffectBuilder {
    private Requirement requirement;
    private Effect effect;

    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num) {
        return require(ResourceSet.all(resourceUnits, num));
    }
    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return require(ResourceSet.all(resourceUnits, num, resourceUnits2, num2));
    }
    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return require(ResourceSet.all(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3));
    }
    public static ReqAndEffectBuilder require(ResourceSet resources) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = new Requirement();
        builder.requirement.requiredResources = resources;
        return builder;
    }

    public static ReqAndEffectBuilder require(Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        return builder;
    }

    public ReqAndEffectBuilder consuming() {
        this.requirement.consumesRequired = true;
        return this;
    }

    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num) {
        return give(ResourceSet.of(resourceUnits, num));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return give(ResourceSet.of(resourceUnits, num, resourceUnits2, num2));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return give(ResourceSet.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3));
    }
    public ReqAndEffectBuilder give(ResourceSet resources, ResourceUnits resourceUnits, int num) {
        this.effect = Effect.of( new ResourceSet[] { resources, ResourceSet.of(resourceUnits, num) } );
        return this;
    }
    public ReqAndEffectBuilder give(ResourceSet resources) {
        this.effect = Effect.of(resources);
        return this;
    }

    public ReqAndEffectBuilder give(Effect effect) {
        this.effect = effect;
        return this;
    }

    public ReqAndEffectBuilder repeating(Phase phase) {
        this.effect.repeats = true;
        this.effect.repeatPhase = phase;
        return this;
    }

    public ReqAndEffect build() {
        return new ReqAndEffect(requirement, effect);
    }

    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.of(unit,  stages[i]);
        }
        return stagedReq(sets);
    }

    public static Requirement stagedReq(ResourceSet... stages) {
        Requirement req = new Requirement();
        req.multiRequirement = java.util.Arrays.asList(stages);
        return req;
    }

    public static Effect stagedEff(ResourceUnits unit, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.of(unit,  stages[i]);
        }
        return stagedEff(sets);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, ResourceUnits unit2, int unit2Cnt) {
        ResourceSet[] sets = new ResourceSet[3];
        sets[0] = ResourceSet.of(unit, stage1);
        sets[1] = ResourceSet.of(unit, stage2);
        sets[2] = ResourceSet.of(unit, stage3);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, ResourceUnits unit2, int unit2Cnt) {
        ResourceSet[] sets = new ResourceSet[4];
        sets[0] = ResourceSet.of(unit, stage1);
        sets[1] = ResourceSet.of(unit, stage2);
        sets[2] = ResourceSet.of(unit, stage3);
        sets[3] = ResourceSet.of(unit, stage4);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceSet... stages) {
        Effect effect = Effect.of(stages);
        effect.isStaged = true;
        return effect;
    }

    public static Effect and(Effect... effects) {
        Effect combined = new Effect();
        combined.multiEffects = java.util.Arrays.asList(effects);
        return combined;
    }
    public static Effect and(Effect effect, ResourceUnits unit, int cnt) {
        Effect combined = new Effect();
        combined.multiEffects = List.of(effect, Effect.of(ResourceSet.of(unit, cnt)));
        return combined;
    }
    public static Effect and(Effect effect, ResourceUnits unit, int cnt, ResourceUnits unit2, int cnt2) {
        Effect combined = new Effect();
        combined.multiEffects = List.of(effect, Effect.of(ResourceSet.of(unit, cnt, unit2, cnt2)));
        return combined;
    }
}