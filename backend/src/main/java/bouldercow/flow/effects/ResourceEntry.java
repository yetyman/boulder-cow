package bouldercow.flow.effects;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static bouldercow.flow.effects.EffectModifier.*;

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

    public static ResourceEntry of(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2) {
        return new ResourceEntry(List.of(unit1, unit2), List.of(amt1, amt2), null);
    }
    public static ResourceEntry of(ResourceUnits unit, int amt) {
        return new ResourceEntry(List.of(unit), List.of(amt), null);
    }
    public static ResourceEntry of(ResourceUnits unit, EffectModifier modifier, int amt) {
        return new ResourceEntry(List.of(unit), List.of(amt), List.of(modifier));
    }
    public static ResourceEntry of(ResourceUnits unit, EffectModifier modifier) {
        return new ResourceEntry(List.of(unit), (List<Integer>)null, List.of(modifier));
    }
    public static ResourceEntry of(ResourceEntry entry1, ResourceEntry entry2, EffectModifier modifier) {
        ResourceEntry re = new ResourceEntry(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addAll(re, entry1);
        addAll(re, entry2);

        re.modifiers.add(modifier);

        return re;
    }

    private static void addAll(ResourceEntry re, ResourceEntry other) {
        if (other.resources != null)
            re.resources.addAll(other.resources);

        if (other.modifiers != null)
            re.modifiers.addAll(other.modifiers);

        if (other.values != null)
            re.values.addAll(other.values);

        if (other.referenceUnits != null)
            re.referenceUnits.addAll(other.referenceUnits);
    }

    // Static helper methods - return ResourceEntry with modifier and values
    public static ResourceEntry template(List<ResourceUnits> units, List<EffectModifier> modifiers, ResourceEntry entry) {
        ResourceEntry re = new ResourceEntry(new ArrayList<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addAll(re, entry);

        if(units != null)
            re.resources.addAll(units);

        if(modifiers != null)
            re.modifiers.addAll(modifiers);

        return re;
    }
    public static ResourceEntry template(ResourceUnits unit1, ResourceEntry entry) {
        return template(List.of(unit1), null, entry);
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

    public static ResourceEntry subtract(ResourceEntry templateA, ResourceEntry templateB, int finalReq) {
        ResourceEntry subtract = new ResourceEntry(new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addAll(subtract, templateA);
        addAll(subtract, templateB);

        subtract.modifiers.add(SUBTRACT);
        subtract.values.add(finalReq);
    }

    public static ResourceEntry subtract(ResourceEntry templateA, ResourceEntry templateB, Integer... finalReq) {
        ResourceEntry subtract = new ResourceEntry(new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        addAll(subtract, templateA);
        addAll(subtract, templateB);

        subtract.modifiers.add(SUBTRACT);
        if(finalReq.length > 0)
            subtract.values.addAll(Arrays.asList(finalReq));
        if(finalReq.length > 1)
            subtract.modifiers.add(STAGED);

        return subtract;
    }
    public static ResourceEntry per(ResourceEntry template) {
        template.modifiers = new ArrayList<>(template.modifiers);
        template.modifiers.add(PER);

        return template;
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
    public static ResourceEntry maxOf(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(MAX_OF)); }
    public static ResourceEntry upTo(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.UP_TO)); }

    public static ResourceEntry reaction(ResourceUnits to) { return new ResourceEntry(null, (Integer[]) null, List.of(EffectModifier.REACTION)); }
    public static ResourceEntry moreThan(ResourceUnits reference) { return new ResourceEntry(null, null, List.of(EffectModifier.MORE_THAN), List.of(reference)); }
    public static ResourceEntry moreThan(ResourceUnits reference, Integer... amounts) { return new ResourceEntry(null, List.of(amounts), List.of(EffectModifier.MORE_THAN), List.of(reference)); }
    public static ResourceEntry per(ResourceUnits reference) { return new ResourceEntry(null, null, List.of(EffectModifier.PER), List.of(reference)); }

    public static ResourceEntry exactly(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.EXACTLY)); }
    public static ResourceEntry different(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(DIFFERENT)); }
    public static ResourceEntry each(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2, unit3), amounts, List.of(EffectModifier.EACH)); }
    public static ResourceEntry each(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Integer... amounts) { return new ResourceEntry(List.of(unit1, unit2, unit3, unit4), amounts, List.of(EffectModifier.EACH)); }

    public static ResourceEntry choose(ResourceUnits unit1, Integer amt) { return new ResourceEntry(List.of(unit1), List.of(amt), List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2,unit3), amounts, List.of(EffectModifier.CHOOSE)); }
    public static ResourceEntry choose(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, Integer... amounts) { return new ResourceEntry(List.of(unit1,unit2,unit3,unit4), amounts, List.of(EffectModifier.CHOOSE)); }

    public static ResourceEntry upTo(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.UP_TO)); }

    public static ResourceEntry same(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(EffectModifier.SAME, amounts.length>0?STAGED:null)); }
   //TODO: make sure other entries get appropriately set to staged if multiple amounts is relavent to staged

    public static ResourceEntry staged(ResourceUnits unit, Integer... amounts) { return new ResourceEntry(List.of(unit), amounts, List.of(STAGED)); }

    public static ResourceEntry maxOf(ResourceUnits unit1) { return of(unit1, MAX_OF); }
    public static ResourceEntry minOf(ResourceUnits unit1) { return of(unit1, MIN_OF); }
    public static ResourceEntry maxOf(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, MAX_OF, reference); }
    public static ResourceEntry minOf(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, MIN_OF, reference); }
    public static ResourceEntry total(ResourceUnits unit1, ResourceUnits unit2, ResourceEntry reference) { return template(unit1,unit2, TOTAL, reference); }
    public static ResourceEntry total(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceEntry reference) { return template(unit1,unit2, unit3, TOTAL, reference); }
    public static ResourceEntry maxOf(ResourceUnits unit, ResourceEntry reference) { return template(unit, MAX_OF, reference); }
    public static ResourceEntry minOf(ResourceUnits unit, ResourceEntry reference) { return template(unit, MIN_OF, reference); }
    public static ResourceEntry atMost(Integer... amounts) { return new ResourceEntry(null, amounts, List.of(EffectModifier.UP_TO)); }
    public static ResourceEntry per(ResourceUnits reference, double multiplier) { return new ResourceEntry(null, List.of((int)(multiplier * 100)), List.of(EffectModifier.PER), List.of(reference)); }
    public static ResourceEntry not(ResourceUnits reference) { return new ResourceEntry(null,null, List.of(EffectModifier.NOT), List.of(reference)); }

    public static ResourceEntry max(ResourceUnits... units) {
        return new ResourceEntry(List.of(units), (List<Integer>)null, List.of(EffectModifier.MAX_OF));
    }
    public static ResourceEntry maxOf(ResourceEntry re) { re.modifiers.add(MAX_OF); return re; }

    private static ResourceEntry each(ResourceUnits resourceUnits, int amount1, ResourceUnits resourceUnits1, int amount2) {
        ResourceEntry re = new ResourceEntry(List.of(resourceUnits, resourceUnits1), List.of(amount1, amount2), List.of(EffectModifier.EACH));
        return re;
    }


    public static ResourceEntry total(ResourceEntry entry, int totalCount) {
        entry.modifiers.add(TOTAL);
        entry.values.add(totalCount);
        return entry;
    }

    public static ResourceEntry total(ResourceUnits resourceUnits, EffectModifier effectModifier, ResourceEntry per, ResourceEntry resourceEntry) {
        ResourceEntry re = new ResourceEntry(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        re.resources.add(resourceUnits);
        re.modifiers.add(effectModifier);
        re.modifiers.add(TOTAL);
        addAll(re, per);
        addAll(re, resourceEntry);
        return re;
    }

    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources) {
        resources.referenceUnits.add(reference);
        resources.modifiers.add(ON);
        return resources;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceUnits resources, Integer... amounts) {
        ResourceEntry r = template(resources, on(reference));
        r.values = Arrays.asList(amounts);
        if(amounts.length > 1)
            r.modifiers.add(STAGED);
        return r;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources, Integer... amounts) {
        resources.referenceUnits.add(reference);
        resources.modifiers.add(ON);
        resources.values = Arrays.asList(amounts);
        if(amounts.length > 1)
            resources.modifiers.add(STAGED);
        return resources;
    }
    public static ResourceEntry on(ResourceUnits reference, ResourceEntry resources, EffectModifier modifier, Integer... amounts) {
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
        return new ResourceEntry(null, null, List.of(ON), List.of(reference));
    }
    public static ResourceEntry exchange(ResourceUnits from, ResourceUnits to, int amount) {
        return new ResourceEntry(List.of(from, to), List.of(amount), List.of(EffectModifier.EACH));
    }
    public static ResourceEntry moreThan(ResourceUnits reference, double... amounts) {
        List<Integer> intAmounts = new ArrayList<>();
        for (double amt : amounts) {
            intAmounts.add((int)(amt * 100));
        }
        return new ResourceEntry(null, intAmounts, List.of(EffectModifier.MORE_THAN), List.of(reference));
    }
    public static ResourceEntry upTo(ResourceUnits unit, int amount) {
        return new ResourceEntry(List.of(unit), List.of(amount), List.of(EffectModifier.UP_TO));
    }
    public static ResourceEntry of(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3) {
        return new ResourceEntry(List.of(unit1, unit2, unit3), List.of(amt1, amt2, amt3), null);
    }
    public static ResourceEntry of(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3, ResourceUnits unit4, int amt4) {
        return new ResourceEntry(List.of(unit1, unit2, unit3, unit4), List.of(amt1, amt2, amt3, amt4), null);
    }
    public static ResourceEntry of(ResourceUnits unit1, int amt1, ResourceUnits unit2, int amt2, ResourceUnits unit3, int amt3, ResourceUnits unit4, int amt4, ResourceUnits unit5, int amt5) {
        return new ResourceEntry(List.of(unit1, unit2, unit3, unit4, unit5), List.of(amt1, amt2, amt3, amt4, amt5), null);
    }
    public static ResourceEntry of(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, int amt) {
        return new ResourceEntry(List.of(unit1, unit2, unit3, unit4), List.of(amt), null);
    }
    public static ResourceEntry of(ResourceUnits unit1, ResourceUnits unit2, ResourceUnits unit3, ResourceUnits unit4, ResourceEntry amt) {
        return template(unit1, unit2, unit3, unit4, amt);
    }

    private static List<EffectModifier> concat(EffectModifier... effects) {
        List<EffectModifier> list = new ArrayList<>(effects.length);
        for (EffectModifier effect : effects) {
            if(effect!=null)
                list.add(effect);
        }
        return list;
    }

}