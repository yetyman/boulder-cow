package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static bouldercow.flow.effects.ResourceEntry.each;
import static bouldercow.flow.effects.ResourceEntry.template;

public class ReqAndEffectBuilder {
    private Requirement requirement;
    private Effect effect;

    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceEntry entry) {
        return require(template(unit1, entry));
    }
    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, ResourceEntry entry2) {
        return require(template(unit1, entry), template(unit2, entry2));
    }
    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return require(template(unit1, unit2, entry));
    }
    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return require(template(unit1, unit2, unit3, entry));
    }
    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return require(template(unit1, unit2, unit3, unit4, entry));
    }


    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num) {
        return require(each(resourceUnits, num));
    }
    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return require(each(resourceUnits, num), each(resourceUnits2, num2));
    }
    public static ReqAndEffectBuilder require(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return require(each(resourceUnits, num), each(resourceUnits2, num2), each(resourceUnits3, num3));
    }
    public static ReqAndEffectBuilder require(ResourceEntry... resources) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        if (resources.length == 1) {
            builder.requirement = Requirement.of(resources[0], false);
        } else {
            builder.requirement = Requirement.of(ResourceEntry.withSubEntries(EffectModifier.EACH, resources), false);
        }
        return builder;
    }

    public static ReqAndEffectBuilder require(EffectModifier mod, ResourceEntry... resources) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(ResourceEntry.withSubEntries(mod, resources), false);
        return builder;
    }
    public static ReqAndEffectBuilder require(Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        return builder;
    }

    public static ReqAndEffectBuilder require(TimingRequirement timingRequirement) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = new Requirement();
        builder.requirement.timing = timingRequirement;
        return builder;
    }
    public static ReqAndEffectBuilder require(TimingRequirement timingRequirement, Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        builder.requirement.timing = timingRequirement;
        return builder;
    }
    public static ReqAndEffectBuilder require(TimingRequirement timingRequirement, ResourceUnits units, int num) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(each(units, num), false);
        builder.requirement.timing = timingRequirement;
        return builder;
    }
    public static ReqAndEffectBuilder require(TimingRequirement timing, ResourceUnits units, ResourceEntry entries) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(template(units, entries), false);
        builder.requirement.timing = timing;
        return builder;
    }
    public static ReqAndEffectBuilder require(TimingRequirement timing, ResourceEntry entries) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(entries, false);
        builder.requirement.timing = timing;
        return builder;
    }

    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceEntry entry) {
        return consume(template(unit1, entry));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceEntry template, int amt) {
        template.values = List.of(amt);
        return consume(template(unit1, template));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, ResourceEntry entry2) {
        return consume(template(unit1, entry), template(unit2, entry2));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return consume(template(unit1, unit2, entry));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return consume(template(unit1, unit2, unit3, entry));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return consume(template(unit1, unit2, unit3, unit4, entry));
    }


    public static ReqAndEffectBuilder consume(ResourceUnits resourceUnits, int num) {
        return consume(each(resourceUnits, num));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return consume(each(resourceUnits, num), each(resourceUnits2, num2));
    }
    public static ReqAndEffectBuilder consume(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return consume(each(resourceUnits, num), each(resourceUnits2, num2), each(resourceUnits3, num3));
    }
    public static ReqAndEffectBuilder consume(ResourceEntry... resources) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        if (resources.length == 1) {
            builder.requirement = Requirement.of(resources[0], true);
        } else {
            builder.requirement = Requirement.of(ResourceEntry.withSubEntries(EffectModifier.EACH, resources), true);
        }
        return builder;
    }

    public static ReqAndEffectBuilder consume(EffectModifier mod, ResourceEntry... resources) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(ResourceEntry.withSubEntries(mod, resources), true);
        return builder;
    }

    public static ReqAndEffectBuilder consume(Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        builder.requirement.consumesRequired = true;
        return builder;
    }

    public static ReqAndEffectBuilder consume(TimingRequirement timingRequirement, Requirement req) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = req;
        builder.requirement.timing = timingRequirement;
        builder.requirement.consumesRequired = true;
        return builder;
    }
    public static ReqAndEffectBuilder consume(TimingRequirement timingRequirement, ResourceUnits units, int num) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(each(units, num), false);
        builder.requirement.timing = timingRequirement;
        builder.requirement.consumesRequired = true;
        return builder;
    }
    public static ReqAndEffectBuilder consume(TimingRequirement timing, ResourceUnits units, ResourceEntry entries) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = Requirement.of(template(units, entries), false);
        builder.requirement.timing = timing;
        builder.requirement.consumesRequired = true;
        return builder;
    }

    public static Effect recurring(Phase phase, ResourceUnits unit, int amount) {
        Effect e = Effect.of(each(unit, amount), true, phase);

        return e;
    }
    public static Effect recurring(Phase phase, ResourceEntry entry){
        Effect e = Effect.of(entry, true, phase);

        return e;
    }

    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num) {
        return give(each(resourceUnits, num));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return give(each(resourceUnits, num), each(resourceUnits2, num2));
    }
    public ReqAndEffectBuilder give(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return give(each(resourceUnits, num), each(resourceUnits2, num2), each(resourceUnits3, num3));
    }
    public ReqAndEffectBuilder give(ResourceEntry resources, ResourceUnits resourceUnits, int num) {
        this.effect = Effect.of( new ResourceEntry[] { resources, each(resourceUnits, num) } );
        return this;
    }
    public ReqAndEffectBuilder give(Effect effect, ResourceUnits resourceUnits, int num) {
        this.effect = and(effect, resourceUnits, num);
        return this;
    }
    public ReqAndEffectBuilder give(Effect effect, ResourceEntry entry) {
        this.effect = and(effect, entry);
        return this;
    }
    public ReqAndEffectBuilder give(ResourceEntry resources, ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        this.effect = Effect.of( new ResourceEntry[] { resources, each(resourceUnits, num), each(resourceUnits2, num2) } );
        return this;
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits cardUnit, int amount) {
        return give(template(unit1, entry), cardUnit, amount);
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry, ResourceUnits cardUnit, int amount) {
        return give(template(unit1, unit2, entry), cardUnit, amount);
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry, ResourceUnits cardUnit, int amount) {
        return give(template(unit1, unit2, unit3, entry), cardUnit, amount);
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry, ResourceUnits cardUnit, int amount) {
        return give(template(unit1, unit2, unit3, unit4, entry), cardUnit, amount);
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, int amount, ResourceUnits unit3, int amount2) {
        return give(template(unit1, entry), each(unit2, amount), each(unit3, amount2));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceUnits unit5, ResourceEntry entry) {
        return give(template(unit1, unit2, unit3, unit4, unit5, entry));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry) {
        return give(template(unit1, entry));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, ResourceEntry entry2) {
        return give(template(unit1, entry), template(unit2, entry2));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return give(template(unit1, unit2, entry));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return give(template(unit1, unit2, unit3, entry));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return give(template(unit1, unit2, unit3, unit4, entry));
    }
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, int amount) {
        return give(template(unit1, unit2, unit3, unit4, each(amount)));
    }
    public ReqAndEffectBuilder give(ResourceEntry... resources) {
        this.effect = Effect.of(resources);
        return this;
    }

    public ReqAndEffectBuilder give(ResourceUnits units1, ResourceEntry staged, ResourceUnits units2, int amt2, ResourceUnits units3, int amt3, ResourceUnits units4, int amt4) {
        return give(template(units1, staged), each(units2, amt2), each(units3, amt3), each(units4, amt4));
    }

    public ReqAndEffectBuilder give(ResourceEntry resources, ResourceEntry resources2) {
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
        ResourceEntry[] sets = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = each(unit,  stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2, int... stages) {
        ResourceEntry[] sets = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = each(unit, unit2,  stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2,ResourceUnits unit3, int... stages) {
        ResourceEntry[] sets = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = each(unit, unit2, unit3, stages[i]);
        }
        return stagedReq(sets);
    }
    // Static helper methods for complex requirements
    public static Requirement stagedReq(ResourceUnits unit,ResourceUnits unit2,ResourceUnits unit3,ResourceUnits unit4, int... stages) {
        ResourceEntry[] sets = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = each(unit, unit2, unit3, unit4, stages[i]);
        }
        return stagedReq(sets);
    }

    public static Requirement stagedReq(ResourceEntry... stages) {
        return Requirement.staged(stages);
    }

    public static Effect stagedEff(ResourceUnits unit, int... stages) {
        ResourceEntry[] sets = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            sets[i] = each(unit,  stages[i]);
        }
        return stagedEff(sets);
    }
    public static Effect stagedEff(Effect... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = stages[i].givesResources;
        }
        return Effect.of(entries);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, ResourceUnits unit2, int unit2Cnt) {
        ResourceEntry[] sets = new ResourceEntry[3];
        sets[0] = each(unit, stage1);
        sets[1] = each(unit, stage2);
        sets[2] = each(unit, stage3);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, ResourceUnits unit2, int unit2Cnt) {
        ResourceEntry[] sets = new ResourceEntry[4];
        sets[0] = each(unit, stage1);
        sets[1] = each(unit, stage2);
        sets[2] = each(unit, stage3);
        sets[3] = each(unit, stage4);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }
    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, int stage5, ResourceUnits unit2, int unit2Cnt) {
        ResourceEntry[] sets = new ResourceEntry[4];
        sets[0] = each(unit, stage1);
        sets[1] = each(unit, stage2);
        sets[2] = each(unit, stage3);
        sets[3] = each(unit, stage4);
        sets[4] = each(unit, stage5);

        return and(stagedEff(sets), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceEntry... stages) {
        return Effect.of(stages);
    }

    public static Effect and(Effect... effects) {
        ResourceEntry[] entries = new ResourceEntry[effects.length];
        for (int i = 0; i < effects.length; i++) {
            entries[i] = effects[i].givesResources;
        }
        Effect combined = new Effect();
        combined.givesResources = ResourceEntry.withSubEntries(EffectModifier.EACH, entries);
        return combined;
    }
    public static Effect and(Effect effect, ResourceUnits unit, int cnt) {
        return and(effect, Effect.of(each(unit, cnt)));
    }
    public static Effect and(Effect effect, ResourceEntry resources) {
        Effect combined = new Effect();
        combined.givesResources = ResourceEntry.withSubEntries(EffectModifier.EACH, effect.givesResources, resources);
        return combined;
    }
    public static Effect and(ResourceEntry resources, Effect effect) {
        Effect combined = new Effect();
        combined.givesResources = ResourceEntry.withSubEntries(EffectModifier.EACH, resources, effect.givesResources);
        return combined;
    }
    public static Effect and(Effect effect, ResourceUnits unit, int cnt, ResourceUnits unit2, int cnt2) {
        Effect combined = new Effect();
        combined.givesResources = ResourceEntry.withSubEntries(EffectModifier.EACH, effect.givesResources, each(unit, cnt), each(unit2, cnt2));
        return combined;
    }
    public static Requirement and(Requirement req, ResourceUnits unit, int amount) {
        Requirement combined = new Requirement();
        combined.requiredResources = ResourceEntry.withSubEntries(EffectModifier.EACH, req.requiredResources, each(unit, amount));
        combined.timing = req.timing;
        combined.consumesRequired = req.consumesRequired;
        return combined;
    }

}