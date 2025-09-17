package bouldercow.flow.effects;

public enum EffectModifier {
    CHOOSE,      // choose(1) - player picks 1 from options
    DISTINCT,    // distinct(3) - up to 3 different types
    SUBTRACT,    // subtract(13, flax) - 13 minus flax count
    REV_SUBTRACT,// revSubtract(13, flax) - flax minus 13 count
    PER,         // per(farmyard) - amount per farmyard/field/etc
    STAGED,      // staged(1,2,3) - tiered amounts
    EXACTLY,     // exactly(4) - must be exact amount
    MORE_THAN,   // moreThan(sheep) - more than sheep count
    TOTAL,       // total(10) - combined total
    DIFFERENT,   // different(4) - 4 different types
    EACH,        // each(1) - 1 per item
    MIN_OF,      // minOf(sheep, jewelry) - minimum of two resources
    REACTION     // reaction(jewelrySpent) - triggered by event
}