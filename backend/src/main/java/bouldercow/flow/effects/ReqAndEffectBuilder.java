package bouldercow.flow.effects;

import bouldercow.flow.Phase;

import static bouldercow.flow.effects.ResourceEntry.each;
import static bouldercow.flow.effects.ResourceEntry.template;

public class ReqAndEffectBuilder {
    private Requirement requirement;
    private Effect effect;

    // Base builder creation methods
    private static ReqAndEffectBuilder createBuilder(Requirement requirement) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = requirement;
        return builder;
    }

    private static ReqAndEffectBuilder createBuilder(ResourceEntry resources, boolean consumes, TimingRequirement timing) {
        Requirement req = Requirement.of(resources, consumes);
        if (timing != null) req.timing = timing;
        return createBuilder(req);
    }

    // Base require methods - all others delegate to these
    public static ReqAndEffectBuilder require(ResourceEntry... resources) {
        ResourceEntry combined = resources.length == 1 ? resources[0] : 
            ResourceEntry.withSubEntries(EffectModifier.EACH, resources);
        return createBuilder(combined, false, null);
    }

    public static ReqAndEffectBuilder require(EffectModifier mod, ResourceEntry... resources) {
        return createBuilder(ResourceEntry.withSubEntries(mod, resources), false, null);
    }

    public static ReqAndEffectBuilder require(Requirement req) {
        return createBuilder(req);
    }

    public static ReqAndEffectBuilder require(TimingRequirement timing) {
        ReqAndEffectBuilder builder = new ReqAndEffectBuilder();
        builder.requirement = new Requirement();
        builder.requirement.timing = timing;
        return builder;
    }

    public static ReqAndEffectBuilder require(TimingRequirement timing, Requirement req) {
        req.timing = timing;
        return createBuilder(req);
    }

    public static ReqAndEffectBuilder require(TimingRequirement timing, ResourceEntry resources) {
        return createBuilder(resources, false, timing);
    }

    // Convenience require methods - delegate to base methods
    public static ReqAndEffectBuilder require(ResourceUnits unit, int amount) {
        return require(each(unit, amount));
    }

    public static ReqAndEffectBuilder require(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2) {
        return require(each(unit1, amt1), each(unit2, amt2));
    }

    public static ReqAndEffectBuilder require(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3) {
        return require(each(unit1, amt1), each(unit2, amt2), each(unit3, amt3));
    }

    public static ReqAndEffectBuilder require(ResourceUnits unit, ResourceEntry entry) {
        return require(template(unit, entry));
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

    public static ReqAndEffectBuilder require(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceUnits unit5, ResourceEntry entry) {
        return require(template(unit1, unit2, unit3, unit4, unit5, entry));
    }

    public static ReqAndEffectBuilder require(TimingRequirement timing, ResourceUnits unit, int amount) {
        return require(timing, each(unit, amount));
    }

    public static ReqAndEffectBuilder require(TimingRequirement timing, ResourceUnits unit, ResourceEntry entry) {
        return require(timing, template(unit, entry));
    }

    // Base consume methods - all others delegate to these
    public static ReqAndEffectBuilder consume(ResourceEntry... resources) {
        ResourceEntry combined = resources.length == 1 ? resources[0] : 
            ResourceEntry.withSubEntries(EffectModifier.EACH, resources);
        return createBuilder(combined, true, null);
    }

    public static ReqAndEffectBuilder consume(EffectModifier mod, ResourceEntry... resources) {
        return createBuilder(ResourceEntry.withSubEntries(mod, resources), true, null);
    }

    public static ReqAndEffectBuilder consume(Requirement req) {
        req.consumesRequired = true;
        return createBuilder(req);
    }

    public static ReqAndEffectBuilder consume(TimingRequirement timing, Requirement req) {
        req.timing = timing;
        req.consumesRequired = true;
        return createBuilder(req);
    }

    public static ReqAndEffectBuilder consume(TimingRequirement timing, ResourceEntry resources) {
        return createBuilder(resources, true, timing);
    }

    // Convenience consume methods - delegate to base methods
    public static ReqAndEffectBuilder consume(ResourceUnits unit, int amount) {
        return consume(each(unit, amount));
    }

    public static ReqAndEffectBuilder consume(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2) {
        return consume(each(unit1, amt1), each(unit2, amt2));
    }

    public static ReqAndEffectBuilder consume(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3) {
        return consume(each(unit1, amt1), each(unit2, amt2), each(unit3, amt3));
    }

    public static ReqAndEffectBuilder consume(ResourceUnits unit, ResourceEntry entry) {
        return consume(template(unit, entry));
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

    public static ReqAndEffectBuilder consume(TimingRequirement timing, ResourceUnits unit, int amount) {
        return consume(timing, each(unit, amount));
    }

    public static ReqAndEffectBuilder consume(TimingRequirement timing, ResourceUnits unit, ResourceEntry entry) {
        return consume(timing, template(unit, entry));
    }

    public static ReqAndEffectBuilder consume(ResourceUnits unit1, ResourceEntry template, int amt) {
        ResourceEntry modifiedTemplate = ResourceEntry.create(template.resources, template.values, template.modifiers, template.referenceUnits, template.subEntries);
        modifiedTemplate.values.add(amt);
        return consume(template(unit1, modifiedTemplate));
    }

    // Base give methods
    public ReqAndEffectBuilder give(ResourceEntry... resources) {
        this.effect = Effect.of(resources);
        return this;
    }

    public ReqAndEffectBuilder give(Effect effect) {
        this.effect = effect;
        return this;
    }

    // Convenience give methods - delegate to base methods
    public ReqAndEffectBuilder give(ResourceUnits unit, int amount) {
        return give(each(unit, amount));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2) {
        return give(each(unit1, amt1), each(unit2, amt2));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3) {
        return give(each(unit1, amt1), each(unit2, amt2), each(unit3, amt3));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return give(each(unit1, unit2, entry));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return give(each(unit1, unit2, unit3, unit4, entry));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit, ResourceEntry entry) {
        return give(template(unit, entry));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, ResourceEntry entry2) {
        return give(template(unit1, entry), template(unit2, entry2));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return give(template(unit1, unit2, unit3, entry));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceUnits unit5, ResourceEntry entry) {
        return give(template(unit1, unit2, unit3, unit4, unit5, entry));
    }

    public ReqAndEffectBuilder give(ResourceEntry entry, ResourceUnits unit, int amount) {
        return give(entry, each(unit, amount));
    }

    public ReqAndEffectBuilder give(ResourceEntry entry, ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2) {
        return give(entry, each(unit1, amt1), each(unit2, amt2));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3) {
        return give(template(unit1, entry), each(unit2, amt2), each(unit3, amt3));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3, ResourceUnits unit4, int amt4) {
        return give(template(unit1, entry), each(unit2, amt2), each(unit3, amt3), each(unit4, amt4));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceEntry entry, ResourceUnits unit2, int amount) {
        return give(template(unit1, entry), each(unit2, amount));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry, ResourceUnits unit3, int amount) {
        return give(template(unit1, unit2, entry), each(unit3, amount));
    }

    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry, ResourceUnits unit5, int amount) {
        return give(template(unit1, unit2, unit3, unit4, entry), each(unit5, amount));
    }

    public ReqAndEffectBuilder give(Effect effect, ResourceUnits unit, int amount) {
        this.effect = and(effect, unit, amount);
        return this;
    }

    public ReqAndEffectBuilder give(Effect effect, ResourceEntry entry) {
        this.effect = and(effect, entry);
        return this;
    }

    public ReqAndEffectBuilder repeating(Phase phase) {
        if (this.effect != null) {
            this.effect.repeats = true;
            this.effect.repeatPhase = phase;
        }
        return this;
    }

    public ReqAndEffect build() {
        return new ReqAndEffect(requirement, effect);
    }

    // Static helper methods for staged requirements and effects
    public static Requirement stagedReq(ResourceUnits unit, int... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = each(unit, stages[i]);
        }
        return Requirement.staged(entries);
    }

    public static Requirement stagedReq(ResourceUnits unit1, ResourceUnits unit2, int... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = each(unit1, unit2, stages[i]);
        }
        return Requirement.staged(entries);
    }

    public static Requirement stagedReq(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, int... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = each(unit1, unit2, unit3, stages[i]);
        }
        return Requirement.staged(entries);
    }

    public static Requirement stagedReq(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, int... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = each(unit1, unit2, unit3, unit4, stages[i]);
        }
        return Requirement.staged(entries);
    }

    public static Requirement stagedReq(ResourceEntry... stages) {
        return Requirement.staged(stages);
    }

    public static Effect stagedEff(ResourceUnits unit, int... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = each(unit, stages[i]);
        }
        return Effect.of(entries);
    }

    public static Effect stagedEff(ResourceEntry... stages) {
        return Effect.of(stages);
    }

    public static Effect stagedEff(Effect... stages) {
        ResourceEntry[] entries = new ResourceEntry[stages.length];
        for (int i = 0; i < stages.length; i++) {
            entries[i] = stages[i].givesResources;
        }
        return Effect.of(entries);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, ResourceUnits unit2, int unit2Cnt) {
        return and(stagedEff(unit, stage1, stage2, stage3), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, ResourceUnits unit2, int unit2Cnt) {
        return and(stagedEff(unit, stage1, stage2, stage3, stage4), unit2, unit2Cnt);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, int stage5, ResourceUnits unit2, int unit2Cnt) {
        return and(stagedEff(unit, stage1, stage2, stage3, stage4, stage5), unit2, unit2Cnt);
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

    public static Effect and(Effect effect, ResourceUnits unit1, int cnt1, ResourceUnits unit2, int cnt2) {
        Effect combined = new Effect();
        combined.givesResources = ResourceEntry.withSubEntries(EffectModifier.EACH, 
            effect.givesResources, each(unit1, cnt1), each(unit2, cnt2));
        return combined;
    }

    public static Requirement and(Requirement req, ResourceUnits unit, int amount) {
        Requirement combined = new Requirement();
        combined.requiredResources = ResourceEntry.withSubEntries(EffectModifier.EACH, 
            req.requiredResources, each(unit, amount));
        combined.timing = req.timing;
        combined.consumesRequired = req.consumesRequired;
        return combined;
    }

    // Additional overloads for CardDefinitions compatibility
    public ReqAndEffectBuilder give(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, int amount) {
        return give(each(unit1, unit2, unit3, unit4, amount));
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4) {
        return stagedEff(unit, stage1, stage2, stage3, stage4);
    }

    public static Effect stagedEff(ResourceUnits unit, int stage1, int stage2, int stage3, int stage4, int stage5) {
        return stagedEff(unit, stage1, stage2, stage3, stage4, stage5);
    }


}