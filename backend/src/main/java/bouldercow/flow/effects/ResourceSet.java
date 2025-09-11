package bouldercow.flow.effects;

import java.util.Map;

public record ResourceSet(Map<ResourceUnits, Integer> resourceMap, boolean or, boolean total, boolean exactly) {
    public static ResourceSet of(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), false, false, false);
    }
    public static ResourceSet of(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), false, false, false);
    }
    public static ResourceSet of(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), false, false, false);
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
    public static ResourceSet all(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), false, true, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), false, true, false);
    }
    public static ResourceSet all(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), false, true, false);
    }
}
