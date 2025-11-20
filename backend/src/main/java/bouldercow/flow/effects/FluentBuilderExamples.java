package bouldercow.flow.effects;

import static bouldercow.flow.effects.FluentReqAndEffectBuilder.*;
import static bouldercow.flow.effects.ResourceUnits.*;
import static bouldercow.flow.effects.EffectModifier.*;

public class FluentBuilderExamples {
    
    public static void examples() {
        // Current: require(barley, 7, hops, 2).give(sheep, 1)
        // Fluent: 
        require().units(barley, hops).amounts(7, 2)
            .give().units(sheep).amount(1).complete();

        // Current: require(on(fieldAnyLvl, exactly(hops, 2))).give(barley, 2, rye, 1, bonusCard, 1)
        // Fluent:
        require().units(hops).amount(2).mod(EXACTLY).on(fieldAnyLvl)
            .give().units(barley, rye, bonusCard).amounts(2, 1, 1).complete();

        // Current: require(sheep, staged(1, 2, 4, 7)).give(hops, meat, choose(1, 2, 3, 4))
        // Fluent:
        require().units(sheep).mod(STAGED, 1, 2, 4, 7)
            .give().units(hops, meat).mod(CHOOSE, 1, 2, 3, 4).complete();

        // Current: require(fieldAnyLvl, staged(2, 4, 6), jewelry, each(1)).give(each(farmyardCard, 0, 1, 2), each(clay, bonusCard, 1))
        // Fluent:
        require().units(fieldAnyLvl).mod(STAGED, 2, 4, 6)
            .and().units(jewelry).amount(1).mod(EACH)
            .give().units(farmyardCard).mod(EACH, 0, 1, 2)
            .and().units(clay, bonusCard).amount(1).mod(EACH).complete();

        // Current: require(timing(actions), stagedReq(worker, 1, 2, 3)).give(fieldUpgrade, 1, bonusCard, 1)
        // Fluent:
        require().timing(actions).units(worker).mod(STAGED, 1, 2, 3)
            .give().units(fieldUpgrade, bonusCard).amounts(1, 1).complete();
    }
}