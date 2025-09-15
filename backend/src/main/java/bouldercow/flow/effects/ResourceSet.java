package bouldercow.flow.effects;

import java.util.Map;

public record ResourceSet(Map<ResourceUnits, Integer> map, boolean or, boolean total, boolean exactly) {
    public int get(ResourceUnits units) {
        return map.getOrDefault(units, 0);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), false, false, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), false, false, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), false, false, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num3, resourceUnits2, num3), false, false, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, ResourceUnits resourceUnits4, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num3, resourceUnits2, num3, resourceUnits4, num3), false, false, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, ResourceUnits resourceUnits3, ResourceUnits resourceUnits4, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num3, resourceUnits2, num3, resourceUnits3, num3, resourceUnits4, num3), false, false, false);
    }
    public static ResourceSet either(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), true, false, false);
    }
    public static ResourceSet either(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), true, false, false);
    }
    public static ResourceSet either(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), true, false, false);
    }
    public static ResourceSet either(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3, ResourceUnits resourceUnits4, int num4) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3, resourceUnits4, num4), true, false, false);
    }
    public static ResourceSet either(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, ResourceUnits resourceUnits3, ResourceUnits resourceUnits4, int num4) {
        return new ResourceSet(Map.of(resourceUnits, num4, resourceUnits2, num4, resourceUnits3, num4, resourceUnits4, num4), true, false, false);
    }
    public static ResourceSet exactly(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), false, false, true);//exactly means you possess exactly that many.
    }
    public static ResourceSet exactly(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), false, false, true);//exactly means you possess exactly that many.
    }
    public static ResourceSet total(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), true, true, false);
    }
    public static ResourceSet total(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, int num) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num), true, true, false);
    }
    public static ResourceSet total(ResourceUnits resourceUnits, ResourceUnits resourceUnits2, ResourceUnits resourceUnits3, int num) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num, resourceUnits3, num), true, true, false);
    }
}
