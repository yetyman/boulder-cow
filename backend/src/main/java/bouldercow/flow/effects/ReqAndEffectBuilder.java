package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

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


    public static ReqAndEffectBuilder require(Phase requiredPhase, Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        builder.requirement.requiredPhase = requiredPhase;
        return builder;
    }
    public static ReqAndEffectBuilder require(Phase requiredPhase, ResourceUnits units, int num) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(ResourceSet.all(units, num), false);
        builder.requirement.requiredPhase = requiredPhase;
        return builder;
    }

    public ReqAndEffectBuilder consuming() {
        this.requirement.consumesRequired = true;
        return this;
    }

    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num) {
        return give(ResourceSet.all(resourceUnits, num));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return give(ResourceSet.all(resourceUnits, num, resourceUnits2, num2));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return give(ResourceSet.all(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3));
    }
    public ReqAndEffectBuilder give(ResourceSet resources, ResourceUnits resourceUnits, int num) {
        this.effect = Effect.of( new ResourceSet[] { resources, ResourceSet.all(resourceUnits, num) } );
        return this;
    }
    public ReqAndEffectBuilder give(Effect effect, ResourceUnits resourceUnits, int num) {
        this.effect = Effect.of( new ResourceSet[] { resources, ResourceSet.all(resourceUnits, num) } );
        return this;
    }
    public ReqAndEffectBuilder give(ResourceSet resources, ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        this.effect = Effect.of( new ResourceSet[] { resources, ResourceSet.all(resourceUnits, num, resourceUnits2, num2) } );
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

    public ReqAndEffectBuilder give(Function<ResourceSet, ResourceSet> effect) {
        this.effect = Effect.of(effect);
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
            sets[i] = ResourceSet.all(unit,  stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.all(unit, unit2,  stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2,ResourceUnits unit3, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.all(unit, unit2, unit3, stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2,ResourceUnits unit3,ResourceUnits unit4, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.all(unit, unit2, unit3, unit4, stages[i]);
        }
        return stagedReq(sets);
    }

    public static Requirement stagedReq(ResourceSet... stages) {
        return Requirement.staged(stages);
    }

    public static Effect stagedEff(ResourceUnits unit, int... stages) {
        ResourceSet[] sets = new ResourceSet[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = ResourceSet.all(unit,  stages[i]);
        }
        return stagedEff(sets);
    }
    public static Effect stagedEff(Effect... stages) {
        Effect effect = new Effect();
        effect.multiEffects = Arrays.asList(stages);
        effect.isStaged = true;
        return effect;
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, ResourceUnits unit2, int unit2Cnt) {
        ResourceSet[] sets = new ResourceSet[3];
        sets[0] = ResourceSet.all(unit, stage1);
        sets[1] = ResourceSet.all(unit, stage2);
        sets[2] = ResourceSet.all(unit, stage3);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, ResourceUnits unit2, int unit2Cnt) {
        ResourceSet[] sets = new ResourceSet[4];
        sets[0] = ResourceSet.all(unit, stage1);
        sets[1] = ResourceSet.all(unit, stage2);
        sets[2] = ResourceSet.all(unit, stage3);
        sets[3] = ResourceSet.all(unit, stage4);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }
    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, int stage5, ResourceUnits unit2, int unit2Cnt) {
        ResourceSet[] sets = new ResourceSet[4];
        sets[0] = ResourceSet.all(unit, stage1);
        sets[1] = ResourceSet.all(unit, stage2);
        sets[2] = ResourceSet.all(unit, stage3);
        sets[3] = ResourceSet.all(unit, stage4);
        sets[4] = ResourceSet.all(unit, stage5);

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
        return and(effect, ResourceSet.all(unit, cnt));
    }
    public static Effect and(Effect effect, ResourceSet resources) {
        Effect combined = new Effect();
        combined.multiEffects = List.of(effect, Effect.of(resources));
        return combined;
    }
    public static Effect and(ResourceSet resources, Effect effect) {
        Effect combined = new Effect();
        combined.multiEffects = List.of(Effect.of(resources), effect);
        return combined;
    }
    public static Effect and(Effect effect, ResourceUnits unit, int cnt, ResourceUnits unit2, int cnt2) {
        Effect combined = new Effect();
        combined.multiEffects = List.of(effect, Effect.of(ResourceSet.all(unit, cnt, unit2, cnt2)));
        return combined;
    }
}