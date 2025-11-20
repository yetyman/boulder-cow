package bouldercow.flow.effects;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static bouldercow.flow.effects.EffectModifier.*;

public class ResourceEntry {
    public List<ResourceUnits> resources = new ArrayList<>();
    public List<EffectModifier> modifiers = new ArrayList<>();
    public List<Double> values = new ArrayList<>();
    public List<ResourceUnits> referenceUnits = new ArrayList<>();
    public List<ResourceEntry> subEntries = new ArrayList<>();

    // Base constructor - all others delegate to this
    private ResourceEntry(List<ResourceUnits> resources, List<Double> values, List<EffectModifier> modifiers, List<ResourceUnits> referenceUnits, List<ResourceEntry> subEntries) {
        if(resources != null) this.resources.addAll(resources);
        if(values != null) this.values.addAll(values);
        if(modifiers != null) this.modifiers.addAll(modifiers);
        if(referenceUnits != null) this.referenceUnits.addAll(referenceUnits);
        if(subEntries != null) this.subEntries.addAll(subEntries);
    }
    public ResourceEntry(){
        this(null,null,null,null,null);
    }

    // Base factory method - all others delegate to this
    public static ResourceEntry create(List<ResourceUnits> units, List<Double> values, List<EffectModifier> modifiers, List<ResourceUnits> referenceUnits, List<ResourceEntry> subEntries) {
        return new ResourceEntry(units, values, modifiers, referenceUnits, subEntries);
    }

    public static ResourceEntry create(List<ResourceUnits> units, List<Double> values, List<EffectModifier> modifiers) {
        return new ResourceEntry(units, values, modifiers, null, null);
    }
    public static ResourceEntry create(List<ResourceUnits> units, List<Double> values, List<EffectModifier> modifiers, List<ResourceUnits> referenceUnits) {
        return create(units, values, modifiers, referenceUnits, null);
    }

    public static ResourceEntry of(ResourceUnits unit1, double amt1, ResourceUnits unit2, double amt2) {
        return create(List.of(unit1, unit2), List.of(amt1, amt2), null, null);
    }

    public static ResourceEntry of(ResourceUnits unit, double amt) {
        return create(List.of(unit), List.of(amt), null, null);
    }

    public static ResourceEntry of(ResourceUnits unit, EffectModifier modifier, double amt) {
        return create(List.of(unit), List.of(amt), List.of(modifier), null);
    }

    public static ResourceEntry of(ResourceUnits unit, EffectModifier modifier) {
        return create(List.of(unit), null, List.of(modifier), null);
    }

    public static ResourceEntry of(ResourceEntry entry1, ResourceEntry entry2, EffectModifier modifier) {
        ResourceEntry re = create(null, null, null, null);
        addAll(re, entry1);
        addAll(re, entry2);
        re.modifiers.add(modifier);
        return re;
    }

    private static void addAll(ResourceEntry target, ResourceEntry source) {
        target.resources.addAll(source.resources);
        target.modifiers.addAll(source.modifiers);
        target.values.addAll(source.values);
        target.referenceUnits.addAll(source.referenceUnits);
        target.subEntries.addAll(source.subEntries);
    }

    // Base template method - all others delegate to this
    public static ResourceEntry template(List<ResourceUnits> units, List<EffectModifier> modifiers, ResourceEntry entry) {
        ResourceEntry re = create(null, null, null, null);
        addAll(re, entry);
        if(units != null) re.resources.addAll(units);
        if(modifiers != null) re.modifiers.addAll(modifiers);
        return re;
    }

    public static ResourceEntry template(ResourceUnits unit, ResourceEntry entry) {
        return template(List.of(unit), null, entry);
    }

    public static ResourceEntry template(ResourceUnits unit, EffectModifier modifier, ResourceEntry entry) {
        return template(List.of(unit), List.of(modifier), entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return template(List.of(unit1, unit2), null, entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, EffectModifier effect, ResourceEntry entry) {
        return template(List.of(unit1, unit2), List.of(effect), entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, EffectModifier effect, ResourceEntry entry) {
        return template(List.of(unit1, unit2, unit3), List.of(effect), entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return template(List.of(unit1, unit2, unit3), null, entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return template(List.of(unit1, unit2, unit3, unit4), null, entry);
    }

    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceUnits unit5, ResourceEntry entry) {
        return template(List.of(unit1, unit2, unit3, unit4, unit5), null, entry);
    }

    // Base subtract method
    public static ResourceEntry subtract(ResourceEntry templateA, ResourceEntry templateB, Double... finalReq) {
        ResourceEntry result = create(null, null, null, null);
        addAll(result, templateA);
        addAll(result, templateB);
        result.modifiers.add(SUBTRACT);
        if(finalReq.length > 0) result.values.addAll(Arrays.asList(finalReq));
        if(finalReq.length > 1) result.modifiers.add(STAGED);
        return result;
    }

    public static ResourceEntry subtract(ResourceEntry templateA, ResourceEntry templateB, double finalReq) {
        return subtract(templateA, templateB, new Double[]{finalReq});
    }

    public static ResourceEntry per(ResourceEntry template) {
        template.modifiers = new ArrayList<>(template.modifiers);
        template.modifiers.add(PER);
        return template;
    }

    // Base modifier method - creates ResourceEntry with single modifier and amounts
    private static ResourceEntry withModifier(EffectModifier modifier, Double... amounts) {
        return create(null, amounts != null ? Arrays.asList(amounts) : null, List.of(modifier), null);
    }

    // Base unit-modifier method - creates ResourceEntry with units, amounts, and modifier
    private static ResourceEntry withUnitModifier(List<ResourceUnits> units, EffectModifier modifier, Double... amounts) {
        return create(units, amounts != null ? Arrays.asList(amounts) : null, List.of(modifier), null);
    }

    // Base reference method - creates ResourceEntry with reference units and modifier
    private static ResourceEntry withReference(EffectModifier modifier, List<ResourceUnits> reference, Double... amounts) {
        return create(null, amounts != null ? Arrays.asList(amounts) : null, List.of(modifier), reference);
    }

    // Methods for creating entries with subEntries
    public static ResourceEntry withSubEntries(EffectModifier modifier, ResourceEntry... subEntries) {
        return create(null, null, List.of(modifier), null, Arrays.asList(subEntries));
    }
    public static ResourceEntry withSubEntries(List<EffectModifier> modifier, ResourceEntry... subEntries) {
        return create(null, null, modifier, null, Arrays.asList(subEntries));
    }

    public static ResourceEntry withSubEntries(EffectModifier modifier, List<ResourceEntry> subEntries) {
        return create(null, null, List.of(modifier), null, subEntries);
    }

    // Modifier-only methods
    public static ResourceEntry choose(Double... amounts) { return withModifier(EffectModifier.CHOOSE, amounts); }
    public static ResourceEntry distinct(Double... amounts) { return withModifier(EffectModifier.DISTINCT, amounts); }
    public static ResourceEntry staged(Double... amounts) { return withModifier(EffectModifier.STAGED, amounts); }
    public static ResourceEntry exactly(Double... amounts) { return withModifier(EffectModifier.EXACTLY, amounts); }
    public static ResourceEntry total(Double... amounts) { return withModifier(EffectModifier.TOTAL, amounts); }
    public static ResourceEntry different(Double... amounts) { return withModifier(EffectModifier.DIFFERENT, amounts); }
    public static ResourceEntry each(Double... amounts) { return withModifier(EffectModifier.EACH, amounts); }
    public static ResourceEntry minOf(Double... amounts) { return withModifier(EffectModifier.MIN_OF, amounts); }
    public static ResourceEntry maxOf(Double... amounts) { return withModifier(MAX_OF, amounts); }
    public static ResourceEntry upTo(Double... amounts) { return withModifier(EffectModifier.UP_TO, amounts); }
    public static ResourceEntry reaction(ResourceUnits to) { return withModifier(EffectModifier.REACTION); }

    // Unit-modifier methods
    public static ResourceEntry subtract(Double amount1, Double amount2, Double amount3, ResourceUnits unit) {
        return withUnitModifier(List.of(unit), EffectModifier.SUBTRACT, amount1, amount2, amount3);
    }
    public static ResourceEntry subtract(ResourceUnits unit, Double amount1, Double amount2, Double amount3) {
        return withUnitModifier(List.of(unit), EffectModifier.REV_SUBTRACT, amount1, amount2, amount3);
    }
    public static ResourceEntry subtract(Double amount1, ResourceUnits unit) {
        return withUnitModifier(List.of(unit), EffectModifier.SUBTRACT, amount1);
    }
    public static ResourceEntry subtract(ResourceUnits unit, Double amount1) {
        return withUnitModifier(List.of(unit), EffectModifier.REV_SUBTRACT, amount1);
    }

    // Reference methods
    public static ResourceEntry moreThan(ResourceUnits reference) {
        return withReference(EffectModifier.MORE_THAN, List.of(reference));
    }
    public static ResourceEntry moreThan(ResourceUnits reference, Double... amounts) {
        return withReference(EffectModifier.MORE_THAN, List.of(reference), amounts);
    }
    public static ResourceEntry per(ResourceUnits reference) {
        return withReference(EffectModifier.PER, List.of(reference));
    }

    public static ResourceEntry exactly(ResourceUnits unit, Double... amounts) { return withUnitModifier(List.of(unit), EffectModifier.EXACTLY, amounts); }
    public static ResourceEntry different(ResourceUnits unit, Double... amounts) { return withUnitModifier(List.of(unit), DIFFERENT, amounts); }
    public static ResourceEntry each(ResourceUnits unit, Double... amounts) { return withUnitModifier(List.of(unit), EffectModifier.EACH, amounts); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, Double... amounts) { return withUnitModifier(List.of(unit1, unit2), EffectModifier.EACH, amounts); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Double... amounts) { return withUnitModifier(List.of(unit1, unit2, unit3), EffectModifier.EACH, amounts); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Double... amounts) { return withUnitModifier(List.of(unit1, unit2, unit3, unit4), EffectModifier.EACH, amounts); }

    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) { return template(unit1, unit2, EffectModifier.EACH, entry); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) { return template(List.of(unit1, unit2, unit3, unit4), List.of(EffectModifier.EACH), entry); }

    public static ResourceEntry choose(ResourceUnits unit1, Double amt) { return withUnitModifier(List.of(unit1), EffectModifier.CHOOSE, amt); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, Double... amounts) { return withUnitModifier(List.of(unit1,unit2), EffectModifier.CHOOSE, amounts); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Double... amounts) { return withUnitModifier(List.of(unit1,unit2,unit3), EffectModifier.CHOOSE, amounts); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Double... amounts) { return withUnitModifier(List.of(unit1,unit2,unit3,unit4), EffectModifier.CHOOSE, amounts); }

    public static ResourceEntry upTo(ResourceUnits unit, Double... amounts) { return withUnitModifier(List.of(unit), EffectModifier.UP_TO, amounts); }

    public static ResourceEntry same(ResourceUnits unit, Double... amounts) { 
        ResourceEntry re = withUnitModifier(List.of(unit), EffectModifier.SAME, amounts);
        if (amounts != null && amounts.length > 0) re.modifiers.add(STAGED);
        return re;
    }

    public static ResourceEntry staged(ResourceUnits unit, Double... amounts) { return withUnitModifier(List.of(unit), STAGED, amounts); }

    public static ResourceEntry maxOf(ResourceUnits unit1) { return of(unit1, MAX_OF); }
    public static ResourceEntry minOf(ResourceUnits unit1) { return of(unit1, MIN_OF); }
    public static ResourceEntry maxOf(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, MAX_OF, reference); }
    public static ResourceEntry minOf(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, MIN_OF, reference); }
    public static ResourceEntry total(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, TOTAL, reference); }
    public static ResourceEntry total(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry reference) { return template(unit1,unit2, unit3, TOTAL, reference); }
    public static ResourceEntry maxOf(ResourceUnits unit, ResourceEntry reference) { return template(unit, MAX_OF, reference); }
    public static ResourceEntry minOf(ResourceUnits unit, ResourceEntry reference) { return template(unit, MIN_OF, reference); }
    public static ResourceEntry atMost(Double... amounts) { return withModifier(EffectModifier.UP_TO, amounts); }
    public static ResourceEntry per(ResourceUnits reference, double multiplier) { return withReference(EffectModifier.PER, List.of(reference), (double)(multiplier * 100)); }
    public static ResourceEntry not(ResourceUnits reference) { return withReference(EffectModifier.NOT, List.of(reference)); }

    public static ResourceEntry max(ResourceUnits... units) {
        return withUnitModifier(Arrays.asList(units), EffectModifier.MAX_OF);
    }
    public static ResourceEntry maxOf(ResourceEntry re) { re.modifiers.add(MAX_OF); return re; }

    public static ResourceEntry minOf(ResourceEntry re, double min) {
        re.modifiers.add(MIN_OF);
        re.values.add(min);
        return re;
    }

    public static ResourceEntry total(ResourceEntry entry, double totalCount) {
        entry.modifiers.add(TOTAL);
        entry.values.add(totalCount);
        return entry;
    }

    public static ResourceEntry total(ResourceUnits resourceUnits, EffectModifier effectModifier, ResourceEntry per, ResourceEntry resourceEntry) {
        ResourceEntry re = create(List.of(resourceUnits), null, List.of(effectModifier, TOTAL), null);
        addAll(re, per);
        addAll(re, resourceEntry);
        return re;
    }

    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources) {
        resources.referenceUnits.add(reference);
        resources.modifiers.add(ON);
        return resources;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceUnits resources, Double... amounts) {
        ResourceEntry r = template(resources, on(reference));
        r.values = Arrays.asList(amounts);
        if(amounts.length > 1)
            r.modifiers.add(STAGED);
        return r;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources, Double... amounts) {
        resources.referenceUnits.add(reference);
        resources.modifiers.add(ON);
        resources.values = Arrays.asList(amounts);
        if(amounts.length > 1)
            resources.modifiers.add(STAGED);
        return resources;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources, EffectModifier modifier, Double... amounts) {
        resources.referenceUnits.add(reference);
        resources.modifiers.add(ON);
        resources.modifiers.add(modifier);

        resources.values = new ArrayList<>(resources.values);
        resources.values.addAll(Arrays.asList(amounts));
        if(amounts.length > 1)
            resources.modifiers.add(STAGED);
        return resources;
    }
    public static ResourceEntry on(ResourceUnits reference) {
        return withReference(ON, List.of(reference));
    }
    public static ResourceEntry exchange(ResourceUnits from, ResourceUnits to, double amount) {
        return withUnitModifier(List.of(from, to), EffectModifier.EACH, amount);
    }
    public static ResourceEntry moreThan(ResourceUnits reference, double... amounts) {
        Double[] doubleAmounts = new Double[amounts.length];
        for (int i = 0; i < amounts.length; i++) {
            doubleAmounts[i] = (double)(amounts[i] * 100);
        }
        return withReference(EffectModifier.MORE_THAN, List.of(reference), doubleAmounts);
    }
    public static ResourceEntry upTo(ResourceUnits unit, double amount) {
        return withUnitModifier(List.of(unit), EffectModifier.UP_TO, amount);
    }
    public static ResourceEntry of(ResourceUnits unit1, double amt1, ResourceUnits unit2, double amt2, ResourceUnits unit3, double amt3) {
        return create(List.of(unit1, unit2, unit3), List.of(amt1, amt2, amt3), null, null);
    }
    public static ResourceEntry of(ResourceUnits unit1, double amt1, ResourceUnits unit2, double amt2, ResourceUnits unit3, double amt3, ResourceUnits unit4, double amt4) {
        return create(List.of(unit1, unit2, unit3, unit4), List.of(amt1, amt2, amt3, amt4), null, null);
    }
    public static ResourceEntry of(ResourceUnits unit1, double amt1, ResourceUnits unit2, double amt2, ResourceUnits unit3, double amt3, ResourceUnits unit4, double amt4, ResourceUnits unit5, double amt5) {
        return create(List.of(unit1, unit2, unit3, unit4, unit5), List.of(amt1, amt2, amt3, amt4, amt5), null, null);
    }
    public static ResourceEntry of(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, double amt) {
        return create(List.of(unit1, unit2, unit3, unit4), List.of(amt), null, null);
    }
    public static ResourceEntry of(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry amt) {
        return template(unit1, unit2, unit3, unit4, amt);
    }
}