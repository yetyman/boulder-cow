package bouldercow.flow.effects;

public record ResourceSet(int meat, int milk, int wheat, int hops, int sheep, int magicBerry, int flax, int leather, int victoryPoints) {

    public static ResourceSet ofMeat(int meat) {
        return new ResourceSet(meat, 0, 0, 0, 0, 0, 0, 0, 0);
    }
    
    public static ResourceSet ofMilk(int milk) {
        return new ResourceSet(0, milk, 0, 0, 0, 0, 0, 0, 0);
    }
    
    public static ResourceSet ofWheat(int wheat) {
        return new ResourceSet(0, 0, wheat, 0, 0, 0, 0, 0, 0);
    }
    
    public static ResourceSet ofHops(int hops) {
        return new ResourceSet(0, 0, 0, hops, 0, 0, 0, 0, 0);
    }
    
    public static ResourceSet ofSheep(int sheep) {
        return new ResourceSet(0, 0, 0, 0, sheep, 0, 0, 0, 0);
    }
    
    public static ResourceSet ofMagicBerry(int magicBerry) {
        return new ResourceSet(0, 0, 0, 0, 0, magicBerry, 0, 0, 0);
    }
    
    public static ResourceSet ofFlax(int flax) {
        return new ResourceSet(0, 0, 0, 0, 0, 0, flax, 0, 0);
    }
    
    public static ResourceSet ofLeather(int leather) {
        return new ResourceSet(0, 0, 0, 0, 0, 0, 0, leather, 0);
    }

    public static ResourceSet ofVictoryPoints(int victoryPoints) {
        return new ResourceSet(0, 0, 0, 0, 0, 0, 0, 0, victoryPoints);
    }
}
