package bouldercow.flow.effects;

import java.util.Map;

public record ResourceSet(Map<ResourceUnits, Integer> resourceMap, boolean or) {
    public static ResourceSet of(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), false);
    }
    public static ResourceSet of(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), false);
    }
    public static ResourceSet of(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), false);
    }
    public static ResourceSet any(ResourceUnits resourceUnits, int num) {
        return new ResourceSet(Map.of(resourceUnits, num), true);
    }
    public static ResourceSet any(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2), true);
    }
    public static ResourceSet any(ResourceUnits resourceUnits, int num, ResourceUnits resourceUnits2, int num2, ResourceUnits resourceUnits3, int num3) {
        return new ResourceSet(Map.of(resourceUnits, num, resourceUnits2, num2, resourceUnits3, num3), true);
    }
}
