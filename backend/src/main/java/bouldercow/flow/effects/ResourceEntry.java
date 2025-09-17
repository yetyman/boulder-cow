package bouldercow.flow.effects;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ResourceEntry {
    public List<ResourceUnits> resources = new ArrayList<>();
    public List<EffectModifier> modifiers = new ArrayList<>();
    public List<Integer> values;
    public List<ResourceUnits> referenceUnits;

    public ResourceEntry(List<ResourceUnits> resource, Integer[] values, List<EffectModifier> modifiers) {
        if(resources!=null)
            this.resources.addAll(resource);
        if(modifiers!=null)
            this.modifiers.addAll(modifiers);
        if(values!=null)
            this.values.addAll(Arrays.asList(values));
    }
    public ResourceEntry(List<ResourceUnits> resource, List<Integer> values, List<EffectModifier> modifiers) {
        if(resources!=null)
            this.resources.addAll(resource);
        if(modifiers!=null)
            this.modifiers.addAll(modifiers);
        if(values!=null)
            this.values.addAll(values);
    }
    public ResourceEntry(List<ResourceUnits> resource, List<Integer> values, List<EffectModifier> modifiers, List<ResourceUnits> referenceUnits) {
        if(resources!=null)
            this.resources.addAll(resource);
        if(modifiers!=null)
            this.modifiers.addAll(modifiers);
        if(values!=null)
            this.values.addAll(values);
        if(referenceUnits!=null)
            this.referenceUnits.addAll(referenceUnits);
    }

    // Static helper methods - return ResourceEntry with modifier and values
    public static ResourceEntry template(ResourceUnits unit1, ResourceEntry entry) {
        return new ResourceEntry(List.of(unit1), entry.values, entry.modifiers);
    }
    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry entry) {
        return new ResourceEntry(List.of(unit1, unit2), entry.values, entry.modifiers);
    }
    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry entry) {
        return new ResourceEntry(List.of(unit1, unit2, unit3), entry.values, entry.modifiers);
    }
    public static ResourceEntry template(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry entry) {
        return new ResourceEntry(List.of(unit1, unit2, unit3, unit4), entry.values, entry.modifiers);
    }
    public static ResourceEntry template(List<ResourceUnits> units, ResourceEntry entry) {
        return new ResourceEntry(units, entry.values, entry.modifiers);
    }

    public static ResourceEntry choose(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry distinct(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.DISTINCT)); }
    public static ResourceEntry subtract(Integer amount1, Integer amount2, Integer amount3, ResourceUnits unit) { return new ResourceEntry(List.of(unit), List.of(amount1, amount2, amount3), List.of(EffectModifier.SUBTRACT)); }
    public static ResourceEntry subtract(ResourceUnits unit, Integer amount1, Integer amount2, Integer amount3) { return new ResourceEntry(List.of(unit), List.of(amount1, amount2, amount3), List.of(EffectModifier.REV_SUBTRACT)); }
    public static ResourceEntry subtract(Integer amount1, ResourceUnits unit) { return new ResourceEntry(List.of(unit), List.of(amount1), List.of(EffectModifier.SUBTRACT)); }
    public static ResourceEntry subtract(ResourceUnits unit, Integer amount1) { return new ResourceEntry(List.of(unit), List.of(amount1), List.of(EffectModifier.REV_SUBTRACT)); }
    public static ResourceEntry staged(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.STAGED)); }
    public static ResourceEntry exactly(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.EXACTLY)); }
    public static ResourceEntry total(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.TOTAL)); }
    public static ResourceEntry different(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.DIFFERENT)); }
    public static ResourceEntry each(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry minOf(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.MIN_OF)); }
    public static ResourceEntry upTo(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.UP_TO)); }

    public static ResourceEntry reaction(ResourceUnits to) { return new ResourceEntry(null, (Integer[]) null, List.of(EffectModifier.REACTION)); }
    public static ResourceEntry moreThan(ResourceUnits reference) { return new ResourceEntry(null, null, List.of(EffectModifier.MORE_THAN), List.of(reference)); }
    public static ResourceEntry moreThan(ResourceUnits reference, Integer... amounts) { return new ResourceEntry(null, List.of(amounts), List.of(EffectModifier.MORE_THAN), List.of(reference)); }
    public static ResourceEntry per(ResourceUnits reference) { return new ResourceEntry(null, null, List.of(EffectModifier.PER), List.of(reference)); }

    public static ResourceEntry exactly(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.EXACTLY)); }
    public static ResourceEntry each(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2, unit3), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2, unit3, unit4), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry choose(ResourceUnits unit1, Integer... amounts) { return new ResourceEntry(List.of(unit1), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2,unit3), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2,unit3,unit4), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry upTo(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.UP_TO)); }

}