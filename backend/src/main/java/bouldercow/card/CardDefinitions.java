package bouldercow.card;

import bouldercow.flow.Phase;
import bouldercow.flow.effects.*;

import java.util.*;

import static bouldercow.flow.Phase.*;
import static bouldercow.flow.effects.FluentReqAndEffectBuilder.consume;
import static bouldercow.flow.effects.ResourceUnits.*;
import static bouldercow.flow.effects.EffectModifier.*;
import static bouldercow.flow.effects.FluentReqAndEffectBuilder.require;
import static bouldercow.flow.effects.ResourceUnits.harvest;

public class CardDefinitions {
    public Map<ResourceUnits, List<Card>> cardsByType;
    public Map<String, List<Card>> recommendedDecks;

    static {
        List<Card> beginnerDeck = new ArrayList<>();//gateway cards 0-30
        List<Card> advancedDeck = new ArrayList<>();//gateway cards 101-130
        List<Card> expertDeck = new ArrayList<>();//gateway cards 201-230
        List<Card> masterDeck = new ArrayList<>();//gateway cards 301-330
        List<Card> hopsDeck = new ArrayList<>();//farmyard cards 401-435
        List<Card> sheepDeck = new ArrayList<>();//farmyard cards 501-535
        List<Card> fieldDeck = new ArrayList<>();//farmyard cards 601-635
        List<Card> jewelryDeck = new ArrayList<>();//farmyard cards 701-735
        List<Card> bonusDeck = new ArrayList<>();//bonus cards 801-845
        List<Card> pointDeck = new ArrayList<>();//point cards 901-925

        //the rules show 30 of each card type
        addCard(beginnerDeck, 1, require().u(sheep).m(STAGED, 2, 3, 4).give().u(sheepMovement).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 2, require().u(barley, hops).a(7, 2).give().u(sheep).a(1), gatewayCard);
        addCard(beginnerDeck, 3, require().u(wool, sheep).a(1, 3).give().u(sheep).a(1), gatewayCard);
        addCard(beginnerDeck, 4, require().u(clay).m(MORE_THAN, workerSupply).give().u(fieldLvl4).a(1), gatewayCard);
        addCard(beginnerDeck, 5, require().u(sheep).a(4).give().u(wool, fieldLvl2).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 6, require().u(barley, hops).a(4, 4).give().u(tool, bonusCard).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 7, require().u(meat).m(STAGED, 1, 2, 5).give().u(tool).m(STAGED, 0, 1, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 8, require().u(flax, wool).a(3, 2).give().u(tool, bonusCard).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 9, require().u(actionSpaceFullyOccupied).a(1).give().u(jewelry, bonusCard).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 10, require().u(leather, meat).a(2, 2).give().u(clay, bonusCard).a(2, 1), gatewayCard);
        addCard(beginnerDeck, 11, require().u(milk, wool).a(1, 1).give().u(clay, bonusCard).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 12, require().u(tool).a(4).give().u(clay, milk, wool).a(1, 1, 1), gatewayCard);
        addCard(beginnerDeck, 13, require().u(sheep).a(3).give().u(clay, sowAnyAction).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 14, require().u(fieldLvl5).m(STAGED, 1, 2, 4, 6).give().u(sowAnyAction).m(STAGED, 0, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 15, require().u(fieldAnyLvl).m(STAGED, 4, 5, 6, 7).give().u(differentCrops).m(STAGED, 1, 2, 3, 4), gatewayCard);
        addCard(beginnerDeck, 16, require().u(tool, hops).a(3, 3).give().u(barley, rye).m(CHOOSE, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 17, require().u(hops).a(2).m(EXACTLY, fieldAnyLvl).give().u(barley, rye, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(beginnerDeck, 18, require().u(barley).m(MORE_THAN, workerSupply).give().u(flax, bonusCard).a(2, 1), gatewayCard);
        addCard(beginnerDeck, 19, require().u(fieldAnyLvl).a(6).give().u(rye, leather, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(beginnerDeck, 20, require().t(jewelrySpent).u(jewelry).a(0).m(EXACTLY).give().u(hops, milk).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 21, require().u(sheep).m(STAGED, 1, 2, 4, 7).give().u(hops, meat).m(CHOOSE, 1, 2, 3, 4), gatewayCard);
        addCard(beginnerDeck, 22, require().u(anyCrops).m(EXACTLY, 1, 2).m(ON, fieldAnyLvl).give().u(harvestAction, bonusCard).a(1, 1), gatewayCard);
        addCard(beginnerDeck, 23, require().u(anyCrops).a(0).m(EXACTLY, fieldAnyLvl).give().u(fieldUpgrade).a(1), gatewayCard);
        addCard(beginnerDeck, 24, require().u(rye).m(STAGED, 6, 9, 12).give().u(milk).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 25, require().u(clay).m(STAGED, 4, 5, 7).give().u(milk).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(beginnerDeck, 26, require().u(tool, sheep).a(3, 2).give().u(milk, wool, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(beginnerDeck, 27, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(sowSpecificAction, wool, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(beginnerDeck, 28, require().u(jewelry).m(STAGED, 2, 3, 4).give().u(leather).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(beginnerDeck, 29, require().u(bonusCard, pointCard, farmyardCard).m(TOTAL, 2, 4, 6).give().u(meat).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(beginnerDeck, 30, require().u(fieldAnyLvl).m(STAGED, 2, 4, 6).and().u(jewelry).a(1).m(EACH).give().u(farmyardCard).m(EACH, 0, 1, 2).and().u(clay, bonusCard).a(1).m(EACH), gatewayCard);

        addCard(advancedDeck, 101, require().u(flax).m(STAGED, 4, 6, 8).give().u(sheepMovementDifferent).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 102, require().u(flax, barley, rye, hops).m(CHOOSE, 8).give().u(sheep).a(1), gatewayCard);
        addCard(advancedDeck, 103, require().u(leather, meat, wool).a(3, 3, 3).give().u(sheep, bonusCard).a(1, 1), gatewayCard);
        addCard(advancedDeck, 104, require().u(hops).m(STAGED, 2, 3, 4, 5).give().u(fieldLvl2).m(STAGED, 1, 1, 1, 1).and().u(fieldLvl3).m(STAGED, 0, 1, 1, 1).and().u(fieldLvl4).m(STAGED, 0, 0, 1, 1).and().u(fieldLvl5).m(STAGED, 0, 0, 0, 1), gatewayCard);
        addCard(advancedDeck, 105, require().u(sheep).m(MORE_THAN, fieldAnyLvl).give().u(fieldLvl3).a(1), gatewayCard);
        addCard(advancedDeck, 106, require().u(actionSpaceFullyOccupied).a(1).give().u(tool, hops, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(advancedDeck, 107, require().u(leather).m(STAGED, 1, 2, 4).give().u(tool).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(advancedDeck, 108, require().u(barley).m(STAGED, 6, 11, 16).give().u(tool).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 109, require().u(bonusCard, farmyardCard, pointCard, gatewayCard).m(CHOOSE, 3, 7, 11).give().u(jewelry).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(advancedDeck, 110, require().u(flax, barley, rye).m(STAGED, 2, 5, 7).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(hops, clay).a(1, 1), gatewayCard);
        addCard(advancedDeck, 111, require().u(tool).m(STAGED, 3, 4, 6).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(rye, clay).a(1, 1), gatewayCard);
        addCard(advancedDeck, 112, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(sowSpecificAction, clay, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(advancedDeck, 113, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(hops, sowAnyAction).a(1, 1), gatewayCard);
        addCard(advancedDeck, 114, require().u(fieldAnyLvl).m(STAGED, 5, 6, 7).give().u(barley, flax, hops, rye).m(CHOOSE, 1).and().u(sowAnyAction).m(STAGED, 0, 1, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 115, require().u(fieldAnyLvl, tool).a(4, 3).give().u(barley, flax, hops, rye).m(CHOOSE, 3), gatewayCard);
        addCard(advancedDeck, 116, require().u(flax).m(MORE_THAN, workerSupply).give().u(rye, bonusCard).a(3, 1), gatewayCard);
        addCard(advancedDeck, 117, require().u(hops).m(STAGED, 2, 4, 6).give().u(barley).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 118, require().u(actionSpaceFullyOccupied).a(1).give().u(barley, hops, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(advancedDeck, 119, require().u(barley, rye).a(6, 6).give().u(flax, hops, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(advancedDeck, 120, require().u(fieldLvl5).a(3).give().u(hops, wool, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(advancedDeck, 121, require().t(actions).u(worker).m(STAGED, 1, 2, 3).give().u(fieldUpgrade, bonusCard).a(1, 1), gatewayCard);
        addCard(advancedDeck, 122, require().u(fieldAnyLvl).a(2).m(EXACTLY).give().u(fieldUpgrade, bonusCard).a(1, 1), gatewayCard);
        addCard(advancedDeck, 123, require().u(tool).m(STAGED, 1, 3, 4, 6).give().u(milk).m(STAGED, 0, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 124, require().u(meat).m(STAGED, 1, 2, 3).give().u(milk).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(advancedDeck, 125, require().u(wool).a(2).give().u(milk, bonusCard).a(1, 1), gatewayCard);
        addCard(advancedDeck, 126, require().u(farmyardCardsPlayed).m(STAGED, 1, 3, 4).give().u(meat).m(STAGED, 0, 2, 3).and().u(milk).a(2), gatewayCard);
        addCard(advancedDeck, 127, require().u(fieldWithCrop).m(STAGED, 3, 4, 5, 6, 7).give().u(wool).m(STAGED, 0, 1, 2, 3, 4).and().u(bonusCard).a(1), gatewayCard);
        addCard(advancedDeck, 128, require().u(tool).a(4).give().u(flax, leather).a(1, 2), gatewayCard);
        addCard(advancedDeck, 129, require().u(leather, wool).m(MORE_THAN, workerSupply).give().u(meat).a(3), gatewayCard);
        addCard(advancedDeck, 130, require().u(leather, meat).a(1, 1).give().u(hops, clay).m(CHOOSE, 1).and().u(farmyardCard).a(1), gatewayCard);

        addCard(expertDeck, 201, require().u(sheep).m(MORE_THAN, fieldAnyLvl).give().u(sheepMovementDifferent, milk, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(expertDeck, 202, require().u(actionSpaceFullyOccupied).a(1).give().u(milk, sheep, bonusCard).a(1, 1, 1), gatewayCard);
        addCard(expertDeck, 203, require().u(fieldLvl2, fieldLvl3).a(0).m(EXACTLY).give().u(sheep).a(1), gatewayCard);
        addCard(expertDeck, 204, require().u(sheep).m(STAGED, 2, 3, 4, 5).give().u(fieldLvl2).m(STAGED, 1, 1, 1, 1).and().u(fieldLvl3).m(STAGED, 0, 1, 1, 1).and().u(fieldLvl4).m(STAGED, 0, 0, 1, 1).and().u(fieldLvl5).m(STAGED, 0, 0, 0, 1), gatewayCard);
        addCard(expertDeck, 205, require().u(fieldAnyLvl).m(STAGED, 6, 7).give().u(fieldLvl2).m(STAGED, 1, 1).and().u(fieldLvl5).m(STAGED, 0, 1), gatewayCard);
        addCard(expertDeck, 206, require().u(jewelry).m(STAGED, 2, 3, 5).give().u(meat).m(STAGED, 1, 1, 1).and().u(tool, meat).m(STAGED, 0, 1, 1).m(CHOOSE).and().u(sheep, tool, meat).m(STAGED, 0, 0, 1).m(CHOOSE), gatewayCard);
        addCard(expertDeck, 207, require().u(hops).m(STAGED, 0, 2, 4).and().u(fieldAnyLvl).a(5).give().u(tool).m(STAGED, 0, 1, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(expertDeck, 208, require().t(Phase.harvest).u(harvestTotal).a(16).give().u(tool, bonusCard).a(1, 1), gatewayCard);
        addCard(expertDeck, 209, require().u(sheepOnSameFarmyard).m(STAGED, 3, 5, 6).give().u(jewelry).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(expertDeck, 210, require().u(hops).a(2).give().u(removeWorkersAction, bonusCard).a(1, 1), gatewayCard);
        addCard(expertDeck, 211, require().u(clay).a(3).m(CHOOSE).and().u(hops).a(3).m(CHOOSE).give().u(hops, clay).m(CHOOSE, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(expertDeck, 212, require().u(tool).m(STAGED, 5, 6, 7).give().u(clay).a(12, 13, 14).m(SUBTRACT, workerSupply).m(STAGED), gatewayCard);
        addCard(expertDeck, 213, require().u(hops).a(1).m(ON, fieldAnyLvl).give().u(milk, clay).m(CHOOSE, 1).and().u(sowAnyAction).a(1), gatewayCard);
        addCard(expertDeck, 214, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(barley, flax, hops, rye).m(CHOOSE, 2).and().u(sowSpecificAction, bonusCard).a(1, 1), gatewayCard);
        addCard(expertDeck, 215, require().u(bonusCard, pointCard, farmyardCard).m(TOTAL, 3, 4, 5, 6).give().u(differentCrops).m(STAGED, 1, 2, 3, 4).and().u(bonusCard).a(1), gatewayCard);
        addCard(expertDeck, 216, require().u(tool, jewelry).m(TOTAL, 7).give().u(rye).m(PER, fieldWithCrop), gatewayCard);
        addCard(expertDeck, 217, require().u(jewelry).a(3).give().u(barley).m(PER, sheep).m(MAX_OF).m(ON, farmyardCard).and().u(bonusCard).a(1), gatewayCard);
        addCard(expertDeck, 218, require().u(rye).m(MORE_THAN, workerSupply).give().u(barley, hops, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(expertDeck, 219, require().u(milk).a(5).give().u(flax).a(13).m(SUBTRACT, workerSupply), gatewayCard);
        addCard(expertDeck, 220, require().u(fieldWithCrop).m(STAGED, 3, 4, 6).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(hops, meat).a(1, 1), gatewayCard);
        addCard(expertDeck, 221, require().u(fieldWithCrop, tool).a(2, 3).give().u(harvestAction, bonusCard).a(2, 1), gatewayCard);
        addCard(expertDeck, 222, require().t(actions, justBeforeLastAction).u(fieldWithCrop).a(0).m(EXACTLY).give().u(fieldUpgrade, bonusCard).a(3, 1), gatewayCard);
        addCard(expertDeck, 223, require().u(sheep, jewelry).m(STAGED, 1, 2, 3).give().u(milk).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(expertDeck, 224, require().u(leather, meat, milk, wool).m(STAGED, 1, 2, 3).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(milk).a(3), gatewayCard);
        addCard(expertDeck, 225, require().u(sheepOnFarmyard).m(DIFFERENT, 2).give().u(meat, milk, wool, bonusCard).a(1, 1, 1, 1), gatewayCard);
        addCard(expertDeck, 226, require().u(sheep, jewelry).a(3, 3).give().u(flax, leather, milk, wool).a(1, 1, 1, 1), gatewayCard);
        addCard(expertDeck, 227, require().u(bonusCard, pointCard, farmyardCard).m(TOTAL, 3, 5, 6).give().u(wool).m(STAGED, 0, 1, 2).and().u(bonusCard).a(1), gatewayCard);
        addCard(expertDeck, 228, require().u(fieldWithCrop, barley, rye).m(TOTAL, 4).give().u(leather, bonusCard).a(2, 1), gatewayCard);
        addCard(expertDeck, 229, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(sowSpecificAction, meat).a(1, 1), gatewayCard);
        addCard(expertDeck, 230, require().u(sheep).m(STAGED, 1, 2, 3, 4).give().u(hops).m(STAGED, 0, 1, 2, 3).and().u(farmyardCard).a(1), gatewayCard);

        addCard(masterDeck, 301, require().u(sheep).a(1).m(EXACTLY).m(ON, nextTurnSheepCard).give().u(sheepMovementDifferent, bonusCard).a(3, 1), gatewayCard);
        addCard(masterDeck, 303, require().t(removeWorkers, workersRemoved).u(worker).a(6, 7, 8, 9, 10).m(REMOVED).m(ON, quadrant).give().u(rye).m(STAGED, 0, 1, 2, 3, 5).and().u(sheep).a(1), gatewayCard);
        addCard(masterDeck, 304, require().u(tool).m(STAGED, 3, 7, 10).give().u(sheep).m(STAGED, 0, 1, 2).and().u(fieldLvl2).a(1), gatewayCard);
        addCard(masterDeck, 305, require().t(buildings, craftBuildingAdvanced).u(craftBuildingAdvanced).m(STAGED, 3, 4, 5).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(fieldLvl5).a(1), gatewayCard);
        addCard(masterDeck, 306, require().u(jewelry, sheep).m(MAX_OF).m(MORE_THAN, fieldAnyLvl).give().u(fieldLvl2, sowAnyAction).a(1, 1), gatewayCard);
        addCard(masterDeck, 307, require().u(jewelry).m(STAGED, 1, 3, 6).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(clay, tool).a(2, 1), gatewayCard);
        addCard(masterDeck, 308, require().u(cardsPlayed).m(STAGED, 1, 3, 7, 12).give().u(tool).m(STAGED, 1, 2, 3, 4), gatewayCard);
        addCard(masterDeck, 309, require().t(useActionSpace).u(worker).a(9, 11, 13).m(TOTAL).m(ON, quadrant).give().u(barley).m(STAGED, 1, 2, 3).and().u(tool).a(1), gatewayCard);
        addCard(masterDeck, 310, require().t(toolsObtained).u(tool).m(STAGED, 2, 3, 4).give().u(hops).m(STAGED, 1, 2, 3).and().u(tool).a(1), gatewayCard);
        addCard(masterDeck, 311, require().u(anyCrops).a(7, 14, 21).m(SAME).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(jewelry).a(1), gatewayCard);
        addCard(masterDeck, 312, require().t(actions).u(fieldAnyLvl).m(STAGED, 6, 7, 8).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(craftBuilding).a(1).m(UPGRADE), gatewayCard);
        addCard(masterDeck, 313, require().t(actions).u(worker).a(1).m(EXACTLY).give().u(worker).a(1).m(ON, actionSpace).m(ON, row2).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 314, require().u(worker).a(3).m(EXACTLY).m(ON, actionSpace).m(MIN_OF, 2).give().u(worker).a(3, 0).m(ON, actionSpace).m(TO).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 315, require().t(actions).u(jewelry).a(4).m(EACH).give().u(worker).m(EACH, workersRemaining).m(ON, actionSpace).m(ON, fullTopRow).m(TO), gatewayCard);
        addCard(masterDeck, 316, require().u(roundNumber).m(STAGED, 1, 2, 3).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(boulder).a(3).m(UPGRADE), gatewayCard);
        addCard(masterDeck, 317, require().u(fieldWithCrop).a(1).m(EACH).give().u(clay).m(PER, fieldWithoutCrop), gatewayCard);
        addCard(masterDeck, 318, require().u(craftBuilding).m(MAX_OF).m(SUBTRACT, craftBuilding).m(MIN_OF).a(3, 4, 5).give().u(clay).m(STAGED, 3, 4, 5).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 319, require().u(hops).m(STAGED, 1, 3, 6).give().u(clay).m(STAGED, 1, 2, 3).and().u(rye, bonusCard).a(1, 1), gatewayCard);
        addCard(masterDeck, 320, require().u(tool).m(STAGED, 2, 4, 6, 7).give().u(barley).m(STAGED, 0, 1, 2, 3).and().u(flax, bonusCard).a(2, 1), gatewayCard);
        addCard(masterDeck, 321, require().u(barley).a(10).m(SUBTRACT, jewelry).give().u(flax, hops, bonusCard).a(2, 1, 1), gatewayCard);
        addCard(masterDeck, 322, require().t(farmingActionUsed).give().u(barley, flax, hops, rye).a(1).m(CHOOSE).and().u(sowSpecificAction).a(1), gatewayCard);
        addCard(masterDeck, 323, require().u(fieldWithoutCrop).a(1).m(EXACTLY).give().u(farmyardCard, sowAnyAction).a(4).m(UPGRADE, TO, SAME).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 324, require().u(workerSupply).m(MORE_THAN, roundNumber).m(STAGED, 5, 6, 7).give().u(milk).m(STAGED, 1, 2, 3), gatewayCard);
        addCard(masterDeck, 325, require().u(anyCrops).m(STAGED, 0, 10, 16, 23).give().u(milk).m(STAGED, 0, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 326, require().u(sheep).a(4, 1, 2, 3).m(ON, LEVEL).m(MORE_THAN, farmyardCard).m(STAGED).give().u(wool).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 327, require().u(sheep).m(STAGED, 2, 3, 7).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(leather).m(PER, emptyFarmyardCards), gatewayCard);
        addCard(masterDeck, 328, consume().t(butcheryActionUsed).u(jewelry).a(1).give().u(leather, bonusCard).a(2, 1), gatewayCard);
        addCard(masterDeck, 329, require().u(worker).a(1, 3, 6).m(ON, nursery).give().u(meat).m(STAGED, 0, 2, 7).and().u(bonusCard).a(1), gatewayCard);
        addCard(masterDeck, 330, require().t(actions, toolsObtained).u(toolsObtained).m(STAGED, 1, 3, 5).give().u(gatewayCard).m(STAGED, 0, 1, 2).and().u(bonusCard).a(1), gatewayCard);

        addCard(hopsDeck, 401, consume().u(barley, rye).a(1).m(CHOOSE).give().u(sheepMovementDifferent, bonusCard).a(2, 1), farmyardCard);
        addCard(hopsDeck, 402, require().u(fieldLvl2, fieldLvl3, fieldLvl4).a(0).m(EXACTLY).give().u(milk, sheep).a(1, 1), farmyardCard);
        addCard(hopsDeck, 403, require().u(gatewayCard).a(5).m(ON, cardsPlayed).give().u(sheep).a(1), farmyardCard);
        addCard(hopsDeck, 404, consume().u(rye).a(8).m(SUBTRACT, fieldAnyLvl).give().u(sheep, bonusCard).a(1, 1), farmyardCard);
        addCard(hopsDeck, 405, consume().u(barley, flax, hops, rye).a(1).m(EACH).give().u(milk, sheep, bonusCard).a(1).m(EACH).and().u(wool).a(2).m(EACH), farmyardCard);
        addCard(hopsDeck, 406, consume().u(hops).a(4).give().u(hops, sheep, bonusCard).a(2, 1, 1), farmyardCard);
        addCard(hopsDeck, 407, require().u(fieldAnyLvl).a(7).m(EACH).give().u(fieldLvl3, bonusCard).a(1, 1), farmyardCard);
        addCard(hopsDeck, 408, consume().u(sheep).a(1).give().u(fieldLvl5, bonusCard).a(1, 1), farmyardCard);
        addCard(hopsDeck, 409, consume().u(clay, fieldLvl5).a(2, 1).give().u(fieldLvl2).a(3).m(UP_TO), farmyardCard);
        addCard(hopsDeck, 410, consume().u(jewelry).a(1).give().u(fieldLvl2).a(2).m(UP_TO), farmyardCard);
        addCard(hopsDeck, 411, consume().u(hops).a(4).give().u(fieldLvl3).a(2).m(UP_TO), farmyardCard);
        addCard(hopsDeck, 412, consume().u(hops).a(3, 5, 7).m(CHOOSE).give().u(hops).m(STAGED, 0, 3, 6).and().u(fieldLvl4, bonusCard).a(1, 1), farmyardCard);
        addCard(hopsDeck, 413, consume().u(sheep).a(1, 2, 3).m(CHOOSE).give().u(tool).m(STAGED, 1, 2, 3).and().u(bonusCard).m(STAGED, 1, 2, 3), farmyardCard);
        addCard(hopsDeck, 414, consume().u(anyCard).a(2).m(ON, hand).give().u(tool, bonusCard).a(1, 1), farmyardCard);
        addCard(hopsDeck, 415, consume().u(tool).a(7).give().u(tool, jewelry).a(3, 2), farmyardCard);
        addCard(hopsDeck, 416, require().u(sheep).a(11).m(EACH).and().u(fieldAnyLvl).a(8).m(EACH).give().u(jewelry).a(1), farmyardCard);
        addCard(hopsDeck, 417, require().u(wool).m(MORE_THAN, workerSupply).give().u(jewelry).a(1), farmyardCard);
        addCard(hopsDeck, 418, consume().u(hops).a(5, 6, 8).m(CHOOSE).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(jewelry).a(2), farmyardCard);
        addCard(hopsDeck, 419, consume().t(actions, true).u(jewelry).a(1).give().u(useActionSpace).a(1), farmyardCard);
        addCard(hopsDeck, 420, consume().u(tool).a(2).give().u(clay, bonusCard).a(2, 2), farmyardCard);
        addCard(hopsDeck, 421, consume().u(fieldAnyLvl).a(1).give().u(clay).a(5), farmyardCard);
        addCard(hopsDeck, 422, require().u(hops).a(1, 2, 3).m(EXACTLY).m(ON, fieldAnyLvl).give().u(barley).m(STAGED, 0, 2, 4).and().u(bonusCard).a(1), farmyardCard);
        addCard(hopsDeck, 423, require().u(meat).a(5).m(EACH).and().u(wool).a(3).m(EACH).give().u(flax, bonusCard).a(3, 1), farmyardCard);
        addCard(hopsDeck, 424, consume().u(hops).a(3).give().u(rye, bonusCard).a(5, 1), farmyardCard);
        addCard(hopsDeck, 425, consume().u(hops).a(8).give().u(hops, barley, bonusCard).a(3, 3, 2), farmyardCard);
        addCard(hopsDeck, 426, require().t(Phase.harvest).u(tool).a(6).m(EACH).give().u(doubleHarvest).a(1), farmyardCard);
        addCard(hopsDeck, 427, consume().t(Phase.harvest, true).u(milk).a(0, 1, 3).m(CHOOSE).give().u(sowAnyAction).m(STAGED, 1, 2, 3), farmyardCard);
        addCard(hopsDeck, 428, require().u(fieldAnyLvl, jewelry).m(TOTAL).m(MORE_THAN, workerSupply).give().u(leather, milk, wool, bonusCard).a(1, 1, 1, 1), farmyardCard);
        addCard(hopsDeck, 429, require().u(sheep).m(MORE_THAN, fieldAnyLvl).give().u(leather, wool).a(2, 1), farmyardCard);
        addCard(hopsDeck, 430, consume().u(tool).a(1).give().u(milk, bonusCard).a(2, 1), farmyardCard);
        addCard(hopsDeck, 431, consume().u(milk).a(1, 3, 5).m(CHOOSE).give().u(leather).m(STAGED, 1, 3, 5).and().u(bonusCard).a(1), farmyardCard);
        addCard(hopsDeck, 432, consume().u(hops).a(1, 2, 3, 4).m(CHOOSE).give().u(leather, wool).a(0, 1, 2, 3).m(CHOOSE).and().u(bonusCard).a(1), farmyardCard);
        addCard(hopsDeck, 433, consume().u(jewelry).a(1).give().u(meat, milk, bonusCard).a(2, 2, 1), farmyardCard);
        addCard(hopsDeck, 434, consume().u(fieldAnyLvl).a(2).give().u(meat).a(6), farmyardCard);
        addCard(hopsDeck, 435, consume().u(barley).a(1, 3, 6).m(CHOOSE).give().u(gatewayCard).m(STAGED, 1, 2, 3), farmyardCard);

        addCard(sheepDeck, 501, require().u(wool).m(STAGED, 5, 7, 10).give().u(leather).m(STAGED, 0, 1, 2).and().u(sheep).a(1), farmyardCard);
        addCard(sheepDeck, 502, require().u(jewelry).m(STAGED, 4, 5, 6, 8).give().u(rye).m(STAGED, 0, 1, 2, 3).and().u(sheep).a(1), farmyardCard);
        addCard(sheepDeck, 503, require().u(fieldAnyLvl, jewelry).m(MIN_OF).m(STAGED, 3, 5, 7).give().u(sheep).m(STAGED, 0, 1, 2).and().u(meat).a(2), farmyardCard);
        addCard(sheepDeck, 504, consume().u(fieldAnyLvl).a(1).give().u(leather, sheep).a(1, 1), farmyardCard);
        addCard(sheepDeck, 505, consume().u(jewelry).a(1).give().u(sheep, bonusCard).a(1, 1), farmyardCard);
        addCard(sheepDeck, 506, consume().u(clay).a(8).m(SUBTRACT, fieldAnyLvl).give().u(milk, sheep, bonusCard).a(1, 1, 1), farmyardCard);
        addCard(sheepDeck, 507, consume().u(rye).a(10).m(SUBTRACT, jewelry).give().u(sheep, bonusCard).a(2, 1), farmyardCard);
        addCard(sheepDeck, 508, consume().u(flax).m(PER, sheep).m(ON, farmyardCard).give().u(sheep).a(1), farmyardCard);
        addCard(sheepDeck, 509, require().u(fieldAnyLvl).m(TOTAL, 4, 2, 1, 0).give().u(fieldLvl2).m(STAGED, 1, 1, 1, 1).m(EACH).and().u(fieldLvl3).m(STAGED, 0, 1, 1, 1).m(EACH).and().u(fieldLvl4).m(STAGED, 0, 0, 1, 1).m(EACH).and().u(fieldLvl5).m(STAGED, 0, 0, 0, 1).m(EACH).and().u(bonusCard).m(STAGED, 0, 1, 2, 2), farmyardCard);
        addCard(sheepDeck, 510, consume().u(hops).a(2).give().u(fieldLvl4, bonusCard).a(1, 1), farmyardCard);
        addCard(sheepDeck, 511, consume().u(barley, flax, hops, rye).a(4).m(CHOOSE).give().u(fieldLvl2, bonusCard).a(1, 2), farmyardCard);
        addCard(sheepDeck, 512, consume().u(fieldLvl5).a(1).give().u(fieldLvl3).a(2).m(UP_TO), farmyardCard);
        addCard(sheepDeck, 513, consume().u(jewelry).a(1).give().u(fieldLvl5, bonusCard).a(1, 1), farmyardCard);
        addCard(sheepDeck, 514, consume().u(anyGoods).a(2).m(SAME).give().u(tool, fieldLvl2).a(1, 1), farmyardCard);
        addCard(sheepDeck, 515, consume().u(barley).a(8).m(SUBTRACT, fieldAnyLvl).give().u(tool, bonusCard).a(2, 1), farmyardCard);
        addCard(sheepDeck, 516, consume().u(anyCrops).a(1).m(ON, fieldAnyLvl).give().u(tool, bonusCard).a(2, 1), farmyardCard);
        addCard(sheepDeck, 517, consume().u(sheep).a(3).give().u(tool, jewelry, bonusCard).a(2, 2, 2), farmyardCard);
        addCard(sheepDeck, 518, consume().u(fieldAnyLvl).a(1, 2, 4).m(CHOOSE).give().u(jewelry).m(STAGED, 1, 2, 3), farmyardCard);
        addCard(sheepDeck, 519, consume().u(clay).m(PER, fieldAnyLvl).give().u(jewelry).a(2), farmyardCard);
        addCard(sheepDeck, 520, consume().u(hops).a(3).give().u(jewelry, bonusCard).a(1, 1), farmyardCard);
        addCard(sheepDeck, 521, consume().u(hops).a(1, 2, 3).m(CHOOSE).give().u(clay).m(STAGED, 1, 3, 5), farmyardCard);
        addCard(sheepDeck, 522, consume().u(tool).a(1).give().u(sowSpecificAction, clay).a(1).m(EACH), farmyardCard);
        addCard(sheepDeck, 523, require().u(tool).a(4).m(EACH).and().u(fieldAnyLvl).a(5).m(EACH).give().u(barley, flax, hops, rye).a(4).m(CHOOSE), farmyardCard);
        addCard(sheepDeck, 524, require().u(hops).a(1, 2, 3).m(ON, fieldAnyLvl).give().u(barley).m(STAGED, 2, 3, 4).and().u(bonusCard).m(STAGED, 0, 1, 2), farmyardCard);
        addCard(sheepDeck, 525, require().u(fieldAnyLvl, tool).m(TOTAL, 9, 10, 11, 12).give().u(flax).m(STAGED, 2, 3, 4, 5), farmyardCard);
        addCard(sheepDeck, 526, consume().t(Phase.harvest, true).u(milk).a(2).give().u(sowAnyAction).a(3).m(UP_TO), farmyardCard);
        addCard(sheepDeck, 527, require().u(milk).m(MORE_THAN, workerSupply).give().u(rye, leather, meat, bonusCard).a(1, 1, 1, 1), farmyardCard);
        addCard(sheepDeck, 528, require().u(anyCrops).a(4, 5, 7).m(ON, fieldAnyLvl).give().u(bonusCard).m(STAGED, 1, 2, 3).and().u(wool).a(1), farmyardCard);
        addCard(sheepDeck, 529, require().u(anyCrops).a(10).m(SAME).give().u(meat, bonusCard).a(2, 1), farmyardCard);
        addCard(sheepDeck, 530, consume().u(hops).a(1).give().u(milk).a(3), farmyardCard);
        addCard(sheepDeck, 531, consume().u(sheep).a(2).m(UPGRADE, -1).m(ON, farmyardCard).give().u(wool, jewelry).a(4, 1).m(CHOOSE).and().u(bonusCard).a(1), farmyardCard);
        addCard(sheepDeck, 532, consume().u(sheep).a(2).give().u(leather, meat, bonusCard).a(3, 6, 1), farmyardCard);
        addCard(sheepDeck, 533, consume().u(sheep).a(1).give().u(leather, bonusCard).a(4, 1), farmyardCard);
        addCard(sheepDeck, 534, consume().u(jewelry).a(1).give().u(leather, meat, milk, wool).a(5).m(CHOOSE), farmyardCard);
        addCard(sheepDeck, 535, consume().u(clay).a(1, 2, 4).m(CHOOSE).give().u(pointCard).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), farmyardCard);

        addCard(fieldDeck, 601, require().u(fieldAnyLvl).a(6).m(EACH).give().u(sheep).a(1), farmyardCard);
        addCard(fieldDeck, 602, require().u(wool).a(2).m(EACH).and().u(tool).a(4).m(EACH).give().u(sheep).a(1), farmyardCard);
        addCard(fieldDeck, 603, consume().u(fieldLvl5).a(1).give().u(sheep).a(2), farmyardCard);
        addCard(fieldDeck, 604, consume().u(flax).a(8).m(SUBTRACT, fieldAnyLvl).give().u(sheep).a(2), farmyardCard);
        addCard(fieldDeck, 605, consume().t(milking, true).u(milk).a(3).give().u(sheep, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 606, require().u(fieldWithCrop).a(3).m(SAME).give().u(fieldLvl3, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 607, require().u(tool, jewelry).a(6, 4).m(EACH).give().u(fieldAnyLvl).m(LEVEL).m(PER, jewelry).m(UP_TO, fieldAnyLvl).a(2), farmyardCard);
        addCard(fieldDeck, 608, require().u(meat).m(STAGED, 1, 2, 5, 7).give().u(wool).m(STAGED, 0, 1, 2, 3).and().u(fieldLvl4).a(1), farmyardCard);
        addCard(fieldDeck, 609, require().u(sheep, jewelry).m(TOTAL).m(MORE_THAN, workerSupply).give().u(tool, fieldLvl5).a(1, 1), farmyardCard);
        addCard(fieldDeck, 610, consume().u(hops).a(3).give().u(fieldLvl5, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 611, consume().u(hops).a(1, 3, 5).m(CHOOSE).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(fieldLvl4).a(1), farmyardCard);
        addCard(fieldDeck, 612, consume().u(flax).a(2).give().u(fieldLvl3, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 613, consume().u(tool).a(2).give().u(fieldLvl4, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 614, consume().u(sheep).a(2).give().u(fieldLvl4).a(2).m(UP_TO).and().u(bonusCard).a(1), farmyardCard);
        addCard(fieldDeck, 615, require().u(jewelry).m(STAGED, 5, 6, 7, 8).give().sub().u(tool).a(2).parent().sub().u(fieldLvl2).m(UP_TO, 2).parent().sub().u(fieldLvl5).m(UP_TO, 2).parent().sub().u(sheep).a(3).parent().m(STAGED, CHOOSE), farmyardCard);
        addCard(fieldDeck, 616, require().u(leather).m(STAGED, 1, 2, 5, 7).give().u(meat).m(STAGED, 1, 2, 3, 4).and().u(tool).a(1), farmyardCard);
        addCard(fieldDeck, 617, consume().u(jewelry).a(1).give().u(leather, tool, bonusCard).a(3, 1, 1), farmyardCard);
        addCard(fieldDeck, 618, consume().u(sheep).a(1).give().u(milk, tool, bonusCard).a(2, 2, 1), farmyardCard);
        addCard(fieldDeck, 619, require().u(wool).m(STAGED, 4, 9, 16).give().u(jewelry).m(STAGED, 1, 2, 3), farmyardCard);
        addCard(fieldDeck, 620, consume().u(milk).a(3, 8, 13).m(CHOOSE).give().u(milk).m(STAGED, 0, 6, 12).and().u(jewelry, bonusCard).a(1, 1), farmyardCard);
        addCard(fieldDeck, 621, consume().u(hops).a(1, 5, 10).m(CHOOSE).give().u(jewelry).m(STAGED, 1, 2, 3), farmyardCard);
        addCard(fieldDeck, 622, consume().u(hops).a(4).give().u(clay).a(7), farmyardCard);
        addCard(fieldDeck, 623, consume().u(fieldAnyLvl).a(2).give().u(clay).a(5), farmyardCard);
        addCard(fieldDeck, 624, require().u(sheep, fieldAnyLvl).m(TOTAL).m(MORE_THAN, workerSupply).give().u(flax, bonusCard).a(2, 1), farmyardCard);
        addCard(fieldDeck, 625, require().u(jewelry).m(MORE_THAN, fieldAnyLvl).give().u(barley, hops, rye, bonusCard).a(1, 1, 1, 1), farmyardCard);
        addCard(fieldDeck, 626, consume().u(fieldAnyLvl).a(1).give().u(barley, hops, rye).a(3, 1, 3), farmyardCard);
        addCard(fieldDeck, 627, require().u(tool).a(7).m(EACH).give().u(sowDoubleCrop).a(1), farmyardCard);
        addCard(fieldDeck, 628, require().u(anyCrops).a(4).m(DIFFERENT).m(ON, fieldAnyLvl).give().u(harvestAction).a(3).m(UP_TO).and().u(bonusCard).a(1), farmyardCard);
        addCard(fieldDeck, 629, require().u(sheep, jewelry).m(MIN_OF).m(STAGED, 3, 5, 6, 7).give().u(leather).m(STAGED, 1, 2, 3, 4).and().u(meat).m(STAGED, 1, 2, 3, 4), farmyardCard);
        addCard(fieldDeck, 630, require().u(anyCrops).a(6).m(ON, fieldAnyLvl).give().u(leather, meat, wool, bonusCard).a(1, 1, 1, 1), farmyardCard);
        addCard(fieldDeck, 631, consume().u(tool).a(2).give().u(leather).m(PER, tool).and().u(bonusCard).a(1), farmyardCard);
        addCard(fieldDeck, 632, consume().u(fieldAnyLvl).a(2).give().u(meat).a(14).m(SUBTRACT, workerSupply).and().u(bonusCard).a(2), farmyardCard);
        addCard(fieldDeck, 633, consume().u(sheep).a(1).give().u(leather, meat, wool).a(2, 2, 2), farmyardCard);
        addCard(fieldDeck, 634, consume().u(anyCard).a(2).m(ON, hand).give().u(wool, bonusCard).a(1, 2), farmyardCard);
        addCard(fieldDeck, 635, consume().u(pointCard).a(2).m(ON, hand).give().u(gatewayCard, bonusCard).a(1, 2), farmyardCard);

        addCard(jewelryDeck, 701, require().u(barley).a(11).m(EACH).give().u(sheep).a(1), farmyardCard);
        addCard(jewelryDeck, 702, require().t(actions, workersExchangedForTools).u(worker).a(3).m(EACH).give().u(sheep).a(1), farmyardCard);
        addCard(jewelryDeck, 703, require().u(actionSpaceFullyOccupied).a(1).give().u(sheep, fieldLvl2).a(1, 1), farmyardCard);
        addCard(jewelryDeck, 704, require().u(sheep).a(4).m(EACH).and().u(fieldAnyLvl).a(7, 5, 4, 3).m(UP_TO).give().u(fieldLvl2, fieldLvl3, fieldLvl4, fieldLvl5).m(STAGED, 1, 1, 1, 1), farmyardCard);
        addCard(jewelryDeck, 705, require().u(sheep).a(4).m(EACH).and().u(tool).a(7, 4, 1).m(UP_TO).give().u(tool).m(STAGED, 1, 2, 3).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 706, require().t(Phase.harvest, harvest).u(anyCrops).a(11).m(SAME).m(ON, harvest).give().u(tool, jewelry).a(1, 1), farmyardCard);
        addCard(jewelryDeck, 707, require().u(tool).m(STAGED, 2, 6, 10).give().u(jewelry).m(STAGED, 0, 1, 2).and().u(leather, bonusCard).a(1, 1), farmyardCard);
        addCard(jewelryDeck, 708, require().u(tool, fieldAnyLvl).m(TOTAL, 12).give().u(jewelry).a(1), farmyardCard);
        addCard(jewelryDeck, 709, require().u(craftBuilding).m(MAX_OF).m(SUBTRACT, craftBuilding).m(MIN_OF).a(4).give().u(jewelry).a(1), farmyardCard);
        addCard(jewelryDeck, 710, require().u(tool, sheep).m(TOTAL, 8, 11, 16).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(barley, hops, clay).a(2, 1, 2), farmyardCard);
        addCard(jewelryDeck, 711, require().u(tool, jewelry).m(TOTAL).m(MORE_THAN, workerSupply).give().u(rye, bonusCard).a(3, 1), farmyardCard);
        addCard(jewelryDeck, 712, require().u(jewelry).m(STAGED, 3, 5, 7).and(CHOOSE).u(tool).m(STAGED, 6, 8, 10).give().u(flax).m(STAGED, 2, 3, 4).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 713, require().t(cardPlayedAfterGotten).give().u(milk, bonusCard).a(1, 1).and().u(barley, flax, hops, rye).a(1).m(CHOOSE), farmyardCard);
        addCard(jewelryDeck, 714, require().u(flax).a(1, 2, 3).m(ON, fieldAnyLvl).give().u(wool).m(STAGED, 1, 3, 4).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 715, require().u(sheep).m(MORE_THAN, fieldAnyLvl).give().u(meat, bonusCard).a(2, 1), farmyardCard);
        addCard(jewelryDeck, 716, consume().u(jewelry).a(1, 2, 4, 5).m(CHOOSE).give().u(sheep).m(STAGED, 1, 2, 3, 4).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 717, consume().u(hops).a(2, 3, 5).m(CHOOSE).give().u(bonusCard).m(STAGED, 0, 1, 2).and().u(sheep).a(1), farmyardCard);
        addCard(jewelryDeck, 718, consume().u(leather).a(3).and(CHOOSE).u(hops).a(4).give().u(fieldLvl3).a(2).m(UP_TO).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 719, consume().u(jewelry).m(CHOOSE, 2, 7, 9).give().u(jewelry).m(STAGED, 0, 6, 9).and().u(fieldLvl3).a(2).m(UP_TO).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 720, consume().u(sheep).a(1).give().u(clay, tool, bonusCard).a(2, 2, 1), farmyardCard);
        addCard(jewelryDeck, 721, consume().u(jewelry).a(1).give().u(barley, flax, hops, rye).a(1).m(CHOOSE).and().u(tool, bonusCard).a(3, 1), farmyardCard);
        addCard(jewelryDeck, 722, consume().u(hops).a(3).give().u(tool, jewelry, bonusCard).a(1, 1, 1), farmyardCard);
        addCard(jewelryDeck, 723, consume().u(flax, leather).a(7, 0).and(CHOOSE).u(flax, leather).a(5, 1).and(CHOOSE).u(flax, leather).a(3, 2).and(CHOOSE).u(flax, leather).a(1, 3).and(CHOOSE).u(leather).a(4).give().u(jewelry, bonusCard).a(2, 1), farmyardCard);
        addCard(jewelryDeck, 724, consume().t(Phase.harvest, true).u(barley).m(PER, fieldWithoutCrop).give().u(jewelry).a(1), farmyardCard);
        addCard(jewelryDeck, 725, consume().u(tool).a(1).give().u(clearActionSpaces, bonusCard).a(2, 1), farmyardCard);
        addCard(jewelryDeck, 726, consume().t(actions, true).u(jewelry).a(1).give().u(useActionSpace).a(1), farmyardCard);
        addCard(jewelryDeck, 727, consume().u(jewelry).a(1).and(CHOOSE).u(tool).a(3).give().u(clay).a(7), farmyardCard);
        addCard(jewelryDeck, 728, consume().u(anyCrops).a(3).m(SUBTRACT).give().u(sowAnyAction).a(3).m(UP_TO).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 729, consume().u(jewelry).a(1).give().u(fieldLvl4, sowAnyAction).a(1, 1), farmyardCard);
        addCard(jewelryDeck, 730, consume().u(rye).m(PER, emptyFarmyardCards).give().u(milk, bonusCard).a(3, 1), farmyardCard);
        addCard(jewelryDeck, 731, consume().u(tool).a(1).give().u(barley, wool).m(PER, leather).a(.5).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 732, consume().u(jewelry).a(1).give().u(bonusCard).a(1).and(CHOOSE).u(leather).a(4).and(CHOOSE).u(wool).a(5), farmyardCard);
        addCard(jewelryDeck, 733, consume().u(barley).a(10).m(SUBTRACT, jewelry).give().u(leather, meat, bonusCard).a(6, 2, 1), farmyardCard);
        addCard(jewelryDeck, 734, consume().u(jewelry).a(2, 6, 9).m(CHOOSE).give().u(jewelry).m(STAGED, 0, 5, 9).and().u(meat).a(5).and().u(bonusCard).a(1), farmyardCard);
        addCard(jewelryDeck, 735, consume().u(jewelry).a(1).give().u(farmyardCard, bonusCard).a(2, 2), farmyardCard);

        addCard(bonusDeck, 801, consume().u(wool).a(1).give().u(meat).repeats(income).a(1).and().u(vp).a(1), bonusCard);
        addCard(bonusDeck, 802, consume().u(hops).a(1).give().u(barley).repeats(income).a(1).and().u(vp).a(2), bonusCard);
        addCard(bonusDeck, 803, consume().u(meat).a(2).give().u(sheep).repeats(income).a(1).and().u(vp).a(2), bonusCard);
        addCard(bonusDeck, 804, consume().u(meat).a(1).give().u(wool).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 805, consume().u(sheep).a(1).give().u(leather).repeats(income).a(2).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 806, consume().u(sheep).a(1).give().u(milk).repeats(income).a(2).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 807, consume().u(fieldAnyLvl).a(2).give().u(sheep).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 808, consume().u(leather).a(2).give().u(wool).repeats(income).a(2).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 809, consume().u(tool).a(2).give().u(barley, hops).repeats(income).a(1).m(EACH).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 810, consume().u(hops).a(2).give().u(rye).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 811, consume().t(justHarvested).u(flax).a(3).give().u(sowSpecificAction, flax).repeats(income).a(1).m(EACH).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 812, consume().t(justHarvested).u(barley).a(3).give().u(sowSpecificAction, barley).repeats(income).a(1).m(EACH).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 813, consume().u(hops).a(3).give().u(tool).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 814, consume().u(clay).a(3).give().u(rye, clay).repeats(income).a(1).m(EACH).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 815, consume().u(anyCard).a(3).m(ON, hand).give().u(gatewayCard).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 816, consume().t(justHarvested).u(rye).a(3).give().u(sowSpecificAction, rye).repeats(income).a(1).m(EACH).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 817, consume().u(jewelry).a(1).give().u(tool).repeats(income).a(1).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 818, consume().u(jewelry, tool).a(1, 1).give().u(flax, wool).repeats(income).a(1).m(EACH).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 819, consume().u(fieldAnyLvl).a(2).give().u(fieldLvl4).repeats(income).a(1).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 820, consume().u(tool).a(2).give().u(meat).repeats(income).a(1).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 821, consume().u(clay).a(4).give().u(clay).repeats(income).a(2).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 822, consume().u(barley, flax, hops, rye).a(3).m(CHOOSE).give().u(hops).repeats(income).a(1).and().u(vp).a(5), bonusCard);
        addCard(bonusDeck, 823, consume().u(sheep).a(2).give().u(sheep).repeats(income).a(1).and().u(vp).a(5), bonusCard);
        addCard(bonusDeck, 824, consume().u(anyCrops).a(1).m(ON, fieldAnyLvl).give().u(fieldLvl3).repeats(income).a(1).and().u(vp).a(6), bonusCard);
        addCard(bonusDeck, 825, consume().u(fieldAnyLvl).a(3).give().u(fieldLvl5).repeats(income).a(1).and().u(vp).a(6), bonusCard);
        addCard(bonusDeck, 826, consume().u(jewelry).a(2).give().u(barley).repeats(income).a(2).and().u(vp).a(7), bonusCard);
        addCard(bonusDeck, 827, consume().u(jewelry).a(3).give().u(jewelry).repeats(income).a(1).and().u(vp).a(8), bonusCard);
        addCard(bonusDeck, 828, require().u(fieldWithoutCrop).a(0).m(EXACTLY).give().u(hops).repeats(income).a(1).and().u(vp).a(2), bonusCard);
        addCard(bonusDeck, 829, require().u(sheep).a(5).give().u(wool).repeats(income).a(1).and().u(vp).a(2), bonusCard);
        addCard(bonusDeck, 830, require().u(clay).a(7).give().u(clay).repeats(income).a(1).and().u(vp).a(2), bonusCard);
        addCard(bonusDeck, 831, require().u(fieldLvl2, fieldLvl3, fieldLvl4).a(0).m(TOTAL).m(EXACTLY).give().u(sowAnyAction).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 832, require().u(fieldLvl2, fieldLvl3, fieldLvl4).a(0).m(TOTAL).m(EXACTLY).give().u(milk).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 833, require().u(milk).m(MORE_THAN, roundNumber).a(2).give().u(leather).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 834, require().u(leather).a(3).give().u(leather).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 835, require().u(jewelry).a(5).give().u(meat).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 836, require().u(hops).a(6).give().u(rye).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 837, require().u(wool).a(6).give().u(wool).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 838, require().u(flax).a(7).give().u(flax).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 839, require().u(tool).a(8).give().sub().repeats(income).u(tool, jewelry).m(TO).a(1).parent().and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 840, require().u(tool, sheep, jewelry).a(3, 3, 3).give().u(farmyardCard).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 841, require().u(barley).a(10).give().u(barley).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 842, require().u(barley, hops).a(7, 5).give().u(barley).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 843, require().u(milk, sheep).a(7, 6).give().u(sheep).repeats(income).a(1).and().u(vp).a(3), bonusCard);
        addCard(bonusDeck, 844, require().u(rye, meat).a(6, 5).give().u(rye, meat).repeats(income).a(1).m(EACH).and().u(vp).a(4), bonusCard);
        addCard(bonusDeck, 845, require().u(meat).a(6).give().u(meat).repeats(income).a(1).and().u(vp).a(6), bonusCard);

        addCard(pointDeck, 901, require().u(pointCard, bonusCard).a(1, 7).give().u(vp).a(2), pointCard);
        addCard(pointDeck, 902, require().u(pointCard).a(1).give().u(vp).a(3), pointCard);
        addCard(pointDeck, 903, consume().u(tool).a(3).give().u(vp).a(4), pointCard);
        addCard(pointDeck, 904, require().u(gatewayCard).a(6).give().u(vp).a(5), pointCard);
        addCard(pointDeck, 905, consume().u(tool).a(4).give().u(vp).a(6), pointCard);
        addCard(pointDeck, 906, require().u(farmyardCard).a(6).give().u(vp).a(6), pointCard);
        addCard(pointDeck, 907, consume().u(anyGoods).a(5).give().u(vp).a(7), pointCard);
        addCard(pointDeck, 908, consume().u(anyGoods).a(4).m(CHOOSE).and().u(anyGoods).a(4).m(CHOOSE).and().u(anyGoods).a(4).m(CHOOSE).give().u(vp).a(8), pointCard);
        addCard(pointDeck, 909, consume().u(leather, jewelry).a(2, 3).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 910, consume().u(meat).a(5).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 911, consume().u(barley, jewelry).a(6, 2).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 912, consume().u(hops).a(10).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 913, consume().u(barley, flax, hops, rye).a(11).m(CHOOSE).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 914, consume().u(flax, hops).a(6, 6).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 915, consume().u(hops, rye).a(6, 6).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 916, consume().u(clay).a(12).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 917, consume().u(wool).a(12).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 918, consume().u(wool, milk).m(CHOOSE,13, 16).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 919, consume().u(anyGoods).a(14).m(SAME).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 920, consume().u(milk).a(14).give().u(vp).a(10), pointCard);
        addCard(pointDeck, 921, consume().u(jewelry).a(4).give().u(vp).a(12), pointCard);
        addCard(pointDeck, 922, consume().u(fieldAnyLvl).a(7).m(NOT, fieldLvl2).give().u(vp).a(12), pointCard);
        addCard(pointDeck, 923, consume().u(sheep).a(8).give().u(vp).a(12), pointCard);
        addCard(pointDeck, 924, consume().u(fieldAnyLvl).a(8).give().u(vp).a(13), pointCard);
        addCard(pointDeck, 925, consume().u(jewelry, sheep).m(CHOOSE, 5,10).give().u(vp).a(14), pointCard);
    }


    // Convenience method for fluent builders
    private static void addCard(List<Card> deck, int id, FluentReqAndEffectBuilder.ReqAndEffectBuilder builder, ResourceUnits cardType) {
        addCard(deck, id, builder.complete(), cardType);
    }

    private static Card addCard(List<Card> deck, int appendixIndex, ReqAndEffect causeEffect, ResourceUnits cardType) {
        Card card = addCard(appendixIndex, causeEffect, cardType);
        deck.add(card);
        return card;
    }

    private static Card addCard(int appendixIndex, ReqAndEffect causeEffect, ResourceUnits cardType) {
        Card card = new Card(appendixIndex, "");
        card.reqAndEffect = causeEffect;
        card.cardType = cardType;
        card.description = Card.calculateDescription(card);
        card.symbols = Card.calculateSymbols(card);

        return card;
    }
}
