package bouldercow.card;

import bouldercow.flow.Phase;
import bouldercow.flow.effects.*;
import bouldercow.model.GameState;
import bouldercow.player.Player;

import java.util.*;

import static bouldercow.flow.Phase.*;
import static bouldercow.flow.effects.ActionEnum.*;
import static bouldercow.flow.effects.Requirement.*;
import static bouldercow.flow.effects.ResourceUnits.*;
import static bouldercow.flow.effects.ResourceEntry.*;
import static bouldercow.flow.effects.ReqAndEffectBuilder.*;
import static bouldercow.flow.effects.TimingRequirement.*;

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
        addCard(beginnerDeck, 1, require(sheep, staged(2, 3, 4)).give(stagedEff(sheepMovement, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 2, require(barley, 7, hops, 2).give(sheep, 1), gatewayCard);
        addCard(beginnerDeck, 3, require(wool, 1, sheep, 3).give(sheep, 1), gatewayCard);
        addCard(beginnerDeck, 4, require(moreXThanY(clay, workerSupply)).give(ResourceEntry.each(fieldLvl4, 1)), gatewayCard);
        addCard(beginnerDeck, 5, require(sheep, 4).give(wool, 1, fieldLvl2, 1), gatewayCard);
        addCard(beginnerDeck, 6, require(barley, 4, hops, 4).give(tool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 7, require(stagedReq(meat, 1, 2, 5)).give(stagedEff(tool, 0, 1, 2, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 8, require(flax, 3, wool, 2).give(tool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 9, require(actionSpaceFullyOccupied, 1).give(jewelry, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 10, require(leather, 2, meat, 2).give(clay, 2, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 11, require(milk, 1, wool, 1).give(clay, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 12, require(tool, 4).give(clay, 1, milk, 1, wool, 1), gatewayCard);
        addCard(beginnerDeck, 13, require(sheep, 3).give(clay, 1, sowAnyAction, 1), gatewayCard);
        addCard(beginnerDeck, 14, require(fieldLvl5, staged(1, 2, 4, 6)).give(sowAnyAction, staged(0, 1, 2, 3), bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 15, require(stagedReq(fieldAnyLvl, 4, 5, 6, 7)).give(stagedEff(differentCrops, 1, 2, 3, 4)), gatewayCard);
        addCard(beginnerDeck, 16, require(tool, 3, hops, 3).give(barley, wheat, choose(2), bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 17, require(fieldWithCrop, hops, each(2)).give(barley, 2, wheat, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 18, require(moreXThanY(barley, workerSupply)).give(flax, 2, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 19, require(fieldAnyLvl, 6).give(wheat, 1, leather, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 20, require(timing(jewelrySpent), Requirement.of(exactly(jewelry, 0), false)).give(hops, 1, milk, 1), gatewayCard);//this ones tough to define you "just" spent one jewelry down to 0. so spent 1 and have 0, but not consume it for this effect, just "just spent it" so this indicates some kind of history or a triggered response for the user to respond to
        addCard(beginnerDeck, 21, require(sheep, staged(1, 2, 4, 7)).give(hops, meat, choose(1,2,3,4)), gatewayCard);
        addCard(beginnerDeck, 22, require(fieldWithCrop, exactly(1,2)).give(harvestAction, 1, bonusCard, 1), gatewayCard);//the staging here actually does nothing. but saves us from figuring out choose(exactly, exactly) of the same resource. should give the right symbology too
        addCard(beginnerDeck, 23, require(fieldWithoutCrop, exactly(0)).give(fieldUpgrade, 1), gatewayCard);
        addCard(beginnerDeck, 24, require(stagedReq(wheat, 6, 9, 12)).give(stagedEff(milk, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 25, require(stagedReq(clay, 4, 5, 7)).give(stagedEff(milk, 1, 2, 3)), gatewayCard);
        addCard(beginnerDeck, 26, require(tool, 3, sheep, 2).give(milk, 1, wool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 27, require(fieldWithoutCrop, exactly(1)).give(sowSpecificAction, 1, wool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 28, require(stagedReq(jewelry, 2, 3, 4)).give(stagedEff(leather, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 29, require(bonusCard, pointCard, farmyardCard, total(2, 4, 6)).give(stagedEff(meat, 1, 2, 3)), gatewayCard);
        addCard(beginnerDeck, 30, require(fieldAnyLvl, staged(2, 4, 6), jewelry, each(1)).give(each(farmyardCard, 0, 1, 2), each(clay, bonusCard, 1)), gatewayCard);

        addCard(advancedDeck, 101, require(stagedReq(flax, 4, 6, 8)).give(stagedEff(sheepMovementDifferent, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 102, require(flax, barley, wheat, hops, choose(8)).give(sheep, 1), gatewayCard);
        addCard(advancedDeck, 103, require(leather, 3, meat, 3, wool, 3).give(sheep, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 104, require(stagedReq(hops, 2, 3, 4, 5)).give(stagedEff(each(fieldLvl2, 1), ResourceEntry.each(fieldLvl3, 1), ResourceEntry.each(fieldLvl4, 1), ResourceEntry.each(fieldLvl5, 1))), gatewayCard);
        addCard(advancedDeck, 105, require(moreXThanY(sheep, fieldAnyLvl)).give(fieldLvl3, 1), gatewayCard);
        addCard(advancedDeck, 106, require(actionSpaceFullyOccupied, 1).give(tool, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 107, require(stagedReq(leather, 1, 2, 4)).give(stagedEff(tool, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 108, require(stagedReq(barley, 6, 11, 16)).give(stagedEff(tool, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 109, require(bonusCard, farmyardCard, pointCard, gatewayCard, choose(3, 7, 11)).give(stagedEff(jewelry, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 110, require(flax, barley, wheat, staged(2, 5, 7)).give(and(stagedEff(bonusCard, 0, 1, 2), hops, 1, clay, 1)), gatewayCard);
        addCard(advancedDeck, 111, require(stagedReq(tool, 3, 4, 6)).give(and(stagedEff(bonusCard, 0, 1, 2), wheat, 1, clay, 1)), gatewayCard);
        addCard(advancedDeck, 112, require(exactly(fieldWithoutCrop, 1)).give(sowSpecificAction, 1, clay, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 113, require(exactly(fieldWithoutCrop, 1)).give(hops, 1, sowAnyAction, 1), gatewayCard);
        addCard(advancedDeck, 114, require(stagedReq(fieldAnyLvl, 5, 6, 7)).give(and(choose(barley, flax, hops, wheat, 1), stagedEff(sowAnyAction, 0, 1, 2)), bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 115, require(fieldAnyLvl, 4, tool, 3).give(barley, flax, hops, wheat, choose(3)), gatewayCard);
        addCard(advancedDeck, 116, require(moreXThanY(flax, workerSupply)).give(wheat, 3, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 117, require(stagedReq(hops, 2, 4, 6)).give(stagedEff(barley, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 118, require(actionSpaceFullyOccupied, 1).give(barley, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 119, require(barley, 6, wheat, 6).give(flax, 1, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 120, require(fieldLvl5WithCrop, 3).give(hops, 1, wool, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 121, require(timing(phase4), stagedReq(worker, 1, 2, 3)).give(fieldUpgrade, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 122, require(fieldWithCrop, exactly(2)).give(fieldUpgrade, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 123, require(stagedReq(tool, 1, 3, 4, 6)).give(stagedEff(milk, 0, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 124, require(stagedReq(meat, 1, 2, 3)).give(stagedEff(milk, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 125, require(wool, 2).give(milk, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 126, require(stagedReq(farmyardCardsPlayed, 1, 3, 4)).give(stagedEff(meat, 0, 2, 3, milk, 2)), gatewayCard);
        addCard(advancedDeck, 127, require(stagedReq(fieldWithCrop, 3, 4, 5, 6, 7)).give(stagedEff(wool, 0, 1, 2, 3, 4, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 128, require(tool, 4).give(flax, 1, leather, 2), gatewayCard);
        addCard(advancedDeck, 129, require(leather, wool, moreThan(workerSupply)).give(meat, 3), gatewayCard);
        addCard(advancedDeck, 130, require(leather, 1, meat, 1).give(hops, clay, choose(1), farmyardCard, 1), gatewayCard);

        addCard(expertDeck, 201, require(moreXThanY(sheep, fieldAnyLvl)).give(sheepMovementDifferent, 2, milk, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 202, require(actionSpaceFullyOccupied, 1).give(milk, 1, sheep, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 203, require(fieldLvl2, fieldLvl3, exactly(0)).give(sheep, 1), gatewayCard);
        addCard(expertDeck, 204, require(sheep, staged(2, 3, 4, 5)).give(stagedEff(each(fieldLvl2, 1), each(fieldLvl3, 1), each(fieldLvl4, 1), each(fieldLvl5, 1))), gatewayCard);
        addCard(expertDeck, 205, require(stagedReq(fieldAnyLvl, 6, 7)).give(stagedEff(each(fieldLvl2, 1), each(fieldLvl5, 1))), gatewayCard);
        addCard(expertDeck, 206, require(stagedReq(jewelry, 2, 3, 5)).give(stagedEff(each(meat, 1), choose(tool, meat, 1), choose(sheep, tool, meat, 1))), gatewayCard);
        addCard(expertDeck, 207, require(and(stagedReq(hops, 0, 2, 4), fieldAnyLvl, 5)).give(stagedEff(tool, 0, 1, 2, bonusCard, 1)), gatewayCard);
        addCard(expertDeck, 208, require(timing(phase7), harvestTotal, 16).give(tool, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 209, require(stagedReq(sheepOnSameFarmyard, 3, 5, 6)).give(stagedEff(jewelry, 1, 2, 3)), gatewayCard);
        addCard(expertDeck, 210, require(hops, 2).give(removeWorkersAction, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 211, require(clay, hops, choose(3)).give(hops, clay, choose(2), bonusCard, 1), gatewayCard);
        addCard(expertDeck, 212, require(stagedReq(tool, 5, 6, 7)).give(clay, subtract(12, 13, 14, workerSupply)), gatewayCard);
        addCard(expertDeck, 213, require(fieldWithCrop, 1, hops, 1).give(milk, clay, choose(1), sowAnyAction, 1), gatewayCard);
        addCard(expertDeck, 214, require(exactly(fieldWithoutCrop, 1)).give(choose(barley, flax, hops, wheat, 2), sowSpecificAction, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 215, require(bonusCard, pointCard, farmyardCard, total(3, 4, 5, 6)).give(stagedEff(differentCrops, 1, 2, 3, 4, bonusCard, 1)), gatewayCard);
        addCard(expertDeck, 216, require(tool, jewelry, total(7)).give(wheat, per(fieldWithCrop)), gatewayCard);
        addCard(expertDeck, 217, require(jewelry, 3).give(and(Effect.of((rs, state, player)->each(barley, maxSheepOnSingleFarmyard(state, player))), bonusCard, 1)), gatewayCard);
        addCard(expertDeck, 218, require(moreXThanY(wheat, workerSupply)).give(barley, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 219, require(milk, 5).give(flax, subtract(13, workerSupply)), gatewayCard);
        addCard(expertDeck, 220, require(stagedReq(fieldWithCrop, 3, 4, 6)).give(and(stagedEff(bonusCard, 0, 1, 2), hops, 1, meat, 1)), gatewayCard);
        addCard(expertDeck, 221, require(fieldWithCrop, 2, tool, 3).give(harvestAction, 2, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 222, require(timing(phase4, justBeforeLastAction), of(exactly(fieldWithCrop, 0), false)).give(fieldUpgrade, 3, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 223, require(stagedReq(sheep, jewelry, 1, 2, 3)).give(stagedEff(milk, 1, 2, 3)), gatewayCard);
        addCard(expertDeck, 224, require(stagedReq(leather, meat, milk, wool, 1, 2, 3)).give(stagedEff(bonusCard, 0, 1, 2), milk, 3), gatewayCard);
        addCard(expertDeck, 225, require(sheepOnFarmyard, different(2)).give(meat, milk, wool, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 226, require(sheep, 3, jewelry, 3).give(flax, leather, milk, wool, 1), gatewayCard);
        addCard(expertDeck, 227, require(bonusCard, pointCard, farmyardCard, total(3, 5, 6)).give(stagedEff(wool, 0, 1, 2, bonusCard, 1)), gatewayCard);
        addCard(expertDeck, 228, require(fieldWithCrop, barley, wheat, total(4)).give(leather, 2, bonusCard, 1), gatewayCard);
        addCard(expertDeck, 229, require(fieldWithoutCrop, exactly(1)).give(sowSpecificAction, 1, meat, 1), gatewayCard);
        addCard(expertDeck, 230, require(stagedReq(sheep, 1, 2, 3, 4)).give(stagedEff(hops, 0, 1, 2, 3, farmyardCard, 1)), gatewayCard);

        addCard(masterDeck, 301, require(timing(phase2, sheepRemoval), sheepOnNextRemovalCard, each(1)).give(sheepMovementDifferent, 3, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 303, require(timing(phase1, workersRemoved), workersRemovedFromQuadrant, staged(6, 7, 8, 9, 10)).give(wheat, staged(0, 1, 2, 3, 5), sheep, 1), gatewayCard);
        addCard(masterDeck, 304, require(tool, staged(3, 7, 10)).give(sheep, staged(0, 1, 2), fieldLvl2, 1), gatewayCard);
        addCard(masterDeck, 305, require(timing(phase9, craftBuildingAdvanced), staged(3, 4, 5)).give(bonusCard, staged(0, 1, 2), fieldLvl5, 1), gatewayCard);
        addCard(masterDeck, 306, require(choose(jewelry, sheep), moreThan(fieldAnyLvl)).give(fieldLvl2, 1, sowAnyAction, 1), gatewayCard);
        addCard(masterDeck, 307, require(jewelry, staged(1, 3, 6)).give(bonusCard, staged(0, 1, 2), clay, 2, tool, 1), gatewayCard);
        addCard(masterDeck, 308, require(cardsPlayed, staged(1, 3, 7, 12)).give(tool, staged(1, 2, 3, 4)), gatewayCard);
        addCard(masterDeck, 309, require(timing(actionUsed), workersInQuadrant, staged(9, 11, 13)).give(barley, staged(1, 2, 3), tool, 1), gatewayCard);
        addCard(masterDeck, 310, require(timing(toolsObtained), tool, staged(2, 3, 4)).give(hops, staged(1, 2, 3), tool, 1), gatewayCard);
        addCard(masterDeck, 311, require(sameTypeCrops, staged(7, 14, 21)).give(bonusCard, staged(0, 1, 2), jewelry, 1), gatewayCard);
        addCard(masterDeck, 312, require(timing(phase4), fieldAnyLvl, staged(6, 7, 8)).give(bonusCard, staged(0, 1, 2), craftBuildingAdvance, 1), gatewayCard);
        addCard(masterDeck, 313, require(timing(phase4), worker, exactly(1)).give(workerPlaceSecondRow, 1, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 314, require(actionSpacesWith3Workers, each(2)).give(moveWorkersToThirdRow, 1, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 315, require(timing(phase4), jewelry, each(4)).give(removeWorkersToSupply, 1), gatewayCard);
        addCard(masterDeck, 316, require(roundNumber, staged(1, 2, 3)).give(bonusCard, staged(0, 1, 2), boulderMovement, 3), gatewayCard);
        addCard(masterDeck, 317, require(fieldWithCrop, each(1)).give(clay, per(fieldWithoutCrop)), gatewayCard);
        addCard(masterDeck, 318, require(craftBuildingGap, staged(3, 4, 5)).give(clay, staged(3, 4, 5), bonusCard, 1), gatewayCard);
        addCard(masterDeck, 319, require(hops, staged(1, 3, 6)).give(clay, staged(1, 2, 3), wheat, 1, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 320, require(tool, staged(2, 4, 6, 7)).give(barley, staged(0, 1, 2, 3), flax, 2, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 321, require(barley, subtract(10, jewelry)).give(flax, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 322, require(timing(farmingActionUsed)).give(barley, flax, hops, wheat, choose(1), sowSpecificAction, 1), gatewayCard);
        addCard(masterDeck, 323, require(fieldWithoutCrop, exactly(1)).give(fieldMoveToRow4, 1, sowAnyAction, 1, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 324, require(workerSupply, moreThan(roundNumber, 5, 6, 7)).give(milk, staged(1, 2, 3)), gatewayCard);
        addCard(masterDeck, 325, require(allCrops, staged(0, 10, 16, 23)).give(milk, staged(0, 1, 2, 3), bonusCard, 1), gatewayCard);
        addCard(masterDeck, 326, require(sheepOnLaterCards, staged(1, 2, 3)).give(wool, staged(1, 2, 3), bonusCard, 1), gatewayCard);
        addCard(masterDeck, 327, require(sheep, staged(2, 3, 7)).give(bonusCard, staged(0, 1, 2), leather, per(emptyFarmyardCards)), gatewayCard);
        addCard(masterDeck, 328, consume(timing(butcheryActionUsed), jewelry, 1).give(leather, 2, bonusCard, 1), gatewayCard);
        addCard(masterDeck, 329, require(workersOnNursery, staged(1, 3, 6)).give(meat, staged(0, 2, 7), bonusCard, 1), gatewayCard);
        addCard(masterDeck, 330, require(timing(phase4, toolsObtained), staged(1, 3, 5)).give(gatewayCard, staged(0, 1, 2), bonusCard, 1), gatewayCard);

        addCard(hopsDeck, 401, consume(barley, wheat, choose(1)).give(sheepMovementDifferent, 2, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 402, require(fieldLvl2, fieldLvl3, fieldLvl4, exactly(0)).give(milk, 1, sheep, 1), farmyardCard);
        addCard(hopsDeck, 403, require(gatewayCardsPlayed, each(5)).give(sheep, 1), farmyardCard);
        addCard(hopsDeck, 404, consume(wheat, subtract(8, fieldAnyLvl)).give(sheep, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 405, consume(each(barley, flax, hops, wheat, 1)).give(each(milk, sheep, bonusCard, 1), each(wool, 2)), farmyardCard);
        addCard(hopsDeck, 406, consume(hops, 4).give(hops, 2, sheep, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 407, require(fieldAnyLvl, each(7)).give(fieldLvl3, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 408, consume(sheep, 1).give(fieldLvl5, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 409, consume(clay, 2, fieldLvl5, 1).give(fieldLvl2, upTo(3)), farmyardCard);
        addCard(hopsDeck, 410, consume(jewelry, 1).give(fieldLvl2, upTo(2)), farmyardCard);
        addCard(hopsDeck, 411, consume(hops, 4).give(fieldLvl3, upTo(2)), farmyardCard);
        addCard(hopsDeck, 412, consume(hops, choose(3, 5, 7)).give(hops, staged(0, 3, 6), fieldLvl4, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 413, consume(sheep, choose(1, 2, 3)).give(tool, staged(1, 2, 3), bonusCard, staged(1, 2, 3)), farmyardCard);
        addCard(hopsDeck, 414, consume(cardsFromHand, 2).give(tool, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 415, consume(tool, 7).give(tool, 3, jewelry, 2), farmyardCard);
        addCard(hopsDeck, 416, require(sheep, each(11), fieldAnyLvl, each(8)).give(jewelry, 1), farmyardCard);
        addCard(hopsDeck, 417, require(moreXThanY(wool, workerSupply)).give(jewelry, 1), farmyardCard);
        addCard(hopsDeck, 418, consume(hops, choose(5, 6, 8)).give(bonusCard, staged(0, 1, 2), jewelry, 2), farmyardCard);
        addCard(hopsDeck, 419, consume(timing(phase4, true), jewelry, 1).give(useActionSpace, 1), farmyardCard);
        addCard(hopsDeck, 420, consume(tool, 2).give(clay, 2, bonusCard, 2), farmyardCard);
        addCard(hopsDeck, 421, consume(fieldAnyLvl, 1).give(clay, 5), farmyardCard);
        addCard(hopsDeck, 422, require(fieldWithCrop, hops, staged(1, 2, 3)).give(barley, staged(0, 2, 4), bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 423, require(meat, each(5), wool, each(3)).give(flax, 3, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 424, consume(hops, 3).give(wheat, 5, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 425, consume(hops, 8).give(hops, 3, barley, 3, bonusCard, 2), farmyardCard);
        addCard(hopsDeck, 426, require(timing(phase7), tool, each(6)).give(doubleHarvest, 1), farmyardCard);
        addCard(hopsDeck, 427, consume(timing(phase7, true), milk, choose(0, 1, 3)).give(sowAnyAction, staged(1, 2, 3)), farmyardCard);
        addCard(hopsDeck, 428, require(fieldAnyLvl, jewelry, total(), moreThan(workerSupply)).give(leather, 1, milk, 1, wool, 1, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 429, require(moreXThanY(sheep, fieldAnyLvl)).give(leather, 2, wool, 1), farmyardCard);
        addCard(hopsDeck, 430, consume(tool, 1).give(milk, 2, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 431, consume(milk, choose(1, 3, 5)).give(leather, staged(1, 3, 5), bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 432, consume(hops, choose(1, 2, 3, 4)).give(leather, wool, choose(0, 1, 2, 3), bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 433, consume(jewelry, 1).give(meat, 2, milk, 2, bonusCard, 1), farmyardCard);
        addCard(hopsDeck, 434, consume(fieldAnyLvl, 2).give(meat, 6), farmyardCard);
        addCard(hopsDeck, 435, consume(barley, choose(1, 3, 6)).give(gatewayCard, staged(1, 2, 3)), farmyardCard);

        addCard(sheepDeck, 501, require(wool, staged(5, 7, 10)).give(leather, staged(0, 1, 2), sheep, 1), farmyardCard);
        addCard(sheepDeck, 502, require(jewelry, staged(4, 5, 6, 8)).give(wheat, staged(0, 1, 2, 3), sheep, 1), farmyardCard);
        addCard(sheepDeck, 503, require(minOfTwo(fieldAnyLvl, jewelry), staged(3, 5, 7)).give(sheep, staged(0, 1, 2), meat, 2), farmyardCard);
        addCard(sheepDeck, 504, consume(fieldAnyLvl, 1).give(leather, 1, sheep, 1), farmyardCard);
        addCard(sheepDeck, 505, consume(jewelry, 1).give(sheep, 1, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 506, consume(clay, subtract(8, fieldAnyLvl)).give(milk, 1, sheep, 1, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 507, consume(wheat, subtract(10, jewelry)).give(sheep, 2, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 508, consume(flax, per(sheepOnFarmyards)).give(sheep, 1), farmyardCard);
        addCard(sheepDeck, 509, require(fieldAnyLvl, atMost(4, 2, 1, 0)).give(stagedEff(each(fieldLvl2,1), each(fieldLvl3), each(fieldLvl4), each(fieldLvl5)), template(bonusCard, staged(0, 1, 2, 2))), farmyardCard);
        addCard(sheepDeck, 510, consume(hops, 2).give(fieldLvl4, 1, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 511, consume(barley, flax, hops, wheat, choose(4)).give(fieldLvl2, 1, bonusCard, 2), farmyardCard);
        addCard(sheepDeck, 512, consume(fieldLvl5, 1).give(fieldLvl3, upTo(2)), farmyardCard);
        addCard(sheepDeck, 513, consume(jewelry, 1).give(fieldLvl5, 1, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 514, consume(sameTypeGoods, 2).give(tool, 1, fieldLvl2, 1), farmyardCard);
        addCard(sheepDeck, 515, consume(barley, subtract(8, fieldAnyLvl)).give(tool, 2, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 516, consume(plantedGoodFromField, 1).give(tool, 2, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 517, consume(sheep, 3).give(tool, 2, jewelry, 2, bonusCard, 2), farmyardCard);
        addCard(sheepDeck, 518, consume(fieldAnyLvl, choose(1, 2, 4)).give(jewelry, staged(1, 2, 3)), farmyardCard);
        addCard(sheepDeck, 519, consume(clay, per(fieldAnyLvl)).give(jewelry, 2), farmyardCard);
        addCard(sheepDeck, 520, consume(hops, 3).give(jewelry, 1, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 521, consume(hops, choose(1, 2, 3)).give(clay, staged(1, 3, 5)), farmyardCard);
        addCard(sheepDeck, 522, consume(tool, 1).give(each(sowSpecificAction, clay, 1)), farmyardCard);
        addCard(sheepDeck, 523, require(tool, each(4), fieldAnyLvl, each(5)).give(barley, flax, hops, wheat, choose(4)), farmyardCard);
        addCard(sheepDeck, 524, require(fieldWithCrop, hops, staged(1, 2, 3)).give(barley, staged(2, 3, 4), bonusCard, staged(0, 1, 2)), farmyardCard);
        addCard(sheepDeck, 525, require(fieldAnyLvl, tool, total(9, 10, 11, 12)).give(flax, staged(2, 3, 4, 5)), farmyardCard);
        addCard(sheepDeck, 526, consume(timing(phase7, true), milk, 2).give(sowAnyAction, upTo(3)), farmyardCard);
        addCard(sheepDeck, 527, require(moreXThanY(milk, workerSupply)).give(wheat, leather, meat, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 528, require(fieldWithCrop, staged(4, 5, 7)).give(bonusCard, staged(1, 2, 3), wool, 1), farmyardCard);
        addCard(sheepDeck, 529, require(sameTypeCrops, each(10)).give(meat, 2, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 530, consume(hops, 1).give(milk, 3), farmyardCard);
        addCard(sheepDeck, 531, consume(sheep, 2).give(sheepMovementBackward, 2, bonusCard, 1, wool, jewelry, choose(4, 1)), farmyardCard);
        addCard(sheepDeck, 532, consume(sheep, 2).give(leather, 3, meat, 6, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 533, consume(sheep, 1).give(leather, 4, bonusCard, 1), farmyardCard);
        addCard(sheepDeck, 534, consume(jewelry, 1).give(leather, meat, milk, wool, choose(5)), farmyardCard);
        addCard(sheepDeck, 535, consume(clay, choose(1, 2, 4)).give(pointCard, staged(1, 2, 3), bonusCard, 1), farmyardCard);

        addCard(fieldDeck, 601, require(fieldAnyLvl, each(6)).give(sheep, 1), farmyardCard);
        addCard(fieldDeck, 602, require(wool, each(2), tool, each(4)).give(sheep, 1), farmyardCard);
        addCard(fieldDeck, 603, consume(fieldLvl5, 1).give(sheep, 2), farmyardCard);
        addCard(fieldDeck, 604, consume(flax, subtract(8, fieldAnyLvl)).give(sheep, 2), farmyardCard);
        addCard(fieldDeck, 605, consume(timing(phase8, true), milk, 3).give(sheep, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 606, require(fieldWithSameCrop, each(3)).give(fieldLvl3, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 607, require(tool, each(6), jewelry, each(4)).give(fieldsWithRowSum, per(jewelry)), farmyardCard);
        addCard(fieldDeck, 608, require(meat, staged(1, 2, 5, 7)).give(wool, staged(0, 1, 2, 3), fieldLvl4, 1), farmyardCard);
        addCard(fieldDeck, 609, require(sheep, jewelry, total(), moreThan(workerSupply)).give(tool, 1, fieldLvl5, 1), farmyardCard);
        addCard(fieldDeck, 610, consume(hops, 3).give(fieldLvl5, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 611, consume(hops, choose(1, 3, 5)).give(bonusCard, staged(0, 1, 2), fieldLvl4, 1), farmyardCard);
        addCard(fieldDeck, 612, consume(flax, 2).give(fieldLvl3, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 613, consume(tool, 2).give(fieldLvl4, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 614, consume(sheep, 2).give(fieldLvl4, upTo(2), bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 615, require(jewelry, staged(5, 6, 7, 8)).give(stagedEff(each(tool, 2), choose(each(tool, 2), upTo(fieldLvl2, 2)), choose(each(tool, 2), upTo(fieldLvl2, 2), upTo(fieldLvl5, 2)), choose(each(tool, 2), upTo(fieldLvl2, 2), upTo(fieldLvl5, 2), each(sheep, 3)))), farmyardCard);
        addCard(fieldDeck, 616, require(leather, staged(1, 2, 5, 7)).give(meat, staged(1, 2, 3, 4), tool, 1), farmyardCard);
        addCard(fieldDeck, 617, consume(jewelry, 1).give(leather, 3, tool, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 618, consume(sheep, 1).give(milk, 2, tool, 2, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 619, require(wool, staged(4, 9, 16)).give(jewelry, staged(1, 2, 3)), farmyardCard);
        addCard(fieldDeck, 620, consume(milk, choose(3, 8, 13)).give(milk, staged(0, 6, 12), jewelry, 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 621, consume(hops, choose(1, 5, 10)).give(jewelry, staged(1, 2, 3)), farmyardCard);
        addCard(fieldDeck, 622, consume(hops, 4).give(clay, 7), farmyardCard);
        addCard(fieldDeck, 623, consume(fieldAnyLvl, 2).give(clay, 5), farmyardCard);
        addCard(fieldDeck, 624, require(sheep, fieldAnyLvl, total(), moreThan(workerSupply)).give(flax, 2, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 625, require(jewelry, moreThan(fieldAnyLvl)).give(barley, hops, wheat, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 626, consume(fieldAnyLvl, 1).give(barley, 3, hops, 1, wheat, 3), farmyardCard);
        addCard(fieldDeck, 627, require(tool, each(7)).give(sowDoubleCrop, 1), farmyardCard);
        addCard(fieldDeck, 628, require(allFourCropTypes, 1).give(harvestAction, upTo(3), bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 629, require(minOfTwo(sheep, jewelry), staged(3, 5, 6, 7)).give(leather, staged(1, 2, 3, 4), meat, staged(1, 2, 3, 4)), farmyardCard);
        addCard(fieldDeck, 630, require(fieldWithCrop, each(6)).give(leather, meat, wool, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 631, consume(tool, 2).give(leather, per(tool), 1, bonusCard, 1), farmyardCard);
        addCard(fieldDeck, 632, consume(fieldAnyLvl, 2).give(meat, subtract(14, workerSupply), bonusCard, 2), farmyardCard);
        addCard(fieldDeck, 633, consume(sheep, 1).give(leather, 2, meat, 2, wool, 2), farmyardCard);
        addCard(fieldDeck, 634, consume(cardsFromHand, 2).give(wool, 1, bonusCard, 2), farmyardCard);
        addCard(fieldDeck, 635, consume(pointCardsFromHand, 2).give(gatewayCard, 1, bonusCard, 2), farmyardCard);

        addCard(jewelryDeck, 701, require(barley, each(11)).give(sheep, 1), farmyardCard);
        addCard(jewelryDeck, 702, require(timing(phase4, workersExchangedForTools), worker, each(3)).give(sheep, 1), farmyardCard);
        addCard(jewelryDeck, 703, require(actionSpaceFullyOccupied, 1).give(sheep, 1, fieldLvl2, 1), farmyardCard);
        addCard(jewelryDeck, 704, require(sheep, each(4), fieldAnyLvl, atMost(7, 5, 4, 3)).give(fieldLvl2, fieldLvl3, fieldLvl4, fieldLvl5, staged(1, 1, 1, 1)), farmyardCard);
        addCard(jewelryDeck, 705, require(sheep, each(4), tool, atMost(7, 4, 1)).give(tool, staged(1, 2, 3), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 706, require(timing(phase7, harvestedSameTypeCrops), sameTypeCrops, each(11)).give(tool, 1, jewelry, 1), farmyardCard);
        addCard(jewelryDeck, 707, require(tool, staged(2, 6, 10)).give(jewelry, staged(0, 1, 2), leather, 1, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 708, require(tool, fieldAnyLvl, total(12)).give(jewelry, 1), farmyardCard);
        addCard(jewelryDeck, 709, require(craftBuildingGap, each(4)).give(jewelry, 1), farmyardCard);
        addCard(jewelryDeck, 710, require(tool, sheep, total(8, 11, 16)).give(bonusCard, staged(0, 1, 2), barley, 2, hops, 1, clay, 2), farmyardCard);
        addCard(jewelryDeck, 711, require(tool, jewelry, total(), moreXThanY(workerSupply)).give(wheat, 3, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 712, require(choose(each(jewelry, 3, 5, 7), each(tool, 6, 8, 10))).give(flax, staged(2, 3, 4), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 713, require(timing(cardPlayedAfterGotten)).give(milk, 1, bonusCard, 1, barley, flax, hops, wheat, choose(1)), farmyardCard);
        addCard(jewelryDeck, 714, require(fieldWithCrop, flax, staged(1, 2, 3)).give(wool, staged(1, 3, 4), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 715, require(sheep, moreThan(fieldAnyLvl)).give(meat, 2, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 716, consume(jewelry, choose(1, 2, 4, 5)).give(sheep, staged(1, 2, 3, 4), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 717, consume(hops, choose(2, 3, 5)).give(bonusCard, staged(0, 1, 2), sheep, 1), farmyardCard);
        addCard(jewelryDeck, 718, consume(choose(each(leather, 3), each(hops, 4)).give(fieldLvl3, upTo(2), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 719, consume(jewelry, choose(2, 7, 9)).give(jewelry, staged(0, 6, 9), fieldLvl3, upTo(2), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 720, consume(sheep, 1).give(clay, 2, tool, 2, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 721, consume(jewelry, 1).give(choose(barley, flax, hops, wheat, 1), tool, 3, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 722, consume(hops, 3).give(tool, 1, jewelry, 1, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 723, consume(choose(each(flax, 7, leather, 0), each(flax, 5, leather, 1), each(flax, 3, leather, 2), each(flax, 1, leather, 3), each(leather, 4)).give(jewelry, 2, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 724, consume(timing(phase7, true), barley, per(fieldWithoutCrop)).give(jewelry, 1), farmyardCard);
        addCard(jewelryDeck, 725, consume(tool, 1).give(clearActionSpaces, 2, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 726, consume(timing(phase4, true), jewelry, 1).give(useActionSpace, 1), farmyardCard);
        addCard(jewelryDeck, 727, consume(jewelry, 1, tool, 3, choose(1)).give(clay, 7), farmyardCard);
        addCard(jewelryDeck, 728, consume(allCrops, subtract(allCrops, 3)).give(sowAnyAction, upTo(3), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 729, consume(jewelry, 1).give(fieldLvl4, 1, sowAnyAction, 1), farmyardCard);
        addCard(jewelryDeck, 730, consume(wheat, per(emptyFarmyardCards)).give(milk, 3, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 731, consume(tool, 1).give(barley, wool, per(halfOfHides), bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 732, consume(jewelry, 1).give(bonusCard, 1, leather, 4, wool, 5, choose(1)), farmyardCard);
        addCard(jewelryDeck, 733, consume(barley, subtract(10, jewelry)).give(leather, 6, meat, 2, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 734, consume(jewelry, choose(2, 6, 9)).give(jewelry, staged(0, 5, 9), meat, 5, bonusCard, 1), farmyardCard);
        addCard(jewelryDeck, 735, consume(jewelry, 1).give(farmyardCard, 2, bonusCard, 2), farmyardCard);

        addCard(bonusDeck, 801, consume(wool, 1).give(recurring(phase3, meat, 1), vp, 1), bonusCard);
        addCard(bonusDeck, 802, consume(hops, 1).give(recurring(phase3, barley, 1), vp, 2), bonusCard);
        addCard(bonusDeck, 803, consume(meat, 2).give(recurring(phase3, sheep, 1), vp, 2), bonusCard);
        addCard(bonusDeck, 804, consume(meat, 1).give(recurring(phase3, wool, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 805, consume(sheep, 1).give(recurring(phase3, leather, 2), vp, 3), bonusCard);
        addCard(bonusDeck, 806, consume(sheep, 1).give(recurring(phase3, milk, 2), vp, 3), bonusCard);
        addCard(bonusDeck, 807, consume(fieldAnyLvl, 2).give(recurring(phase3, sheep, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 808, consume(leather, 2).give(recurring(phase3, wool, 2), vp, 3), bonusCard);
        addCard(bonusDeck, 809, consume(tool, 2).give(recurring(phase3, each(barley, hops, 1)), vp, 3), bonusCard);
        addCard(bonusDeck, 810, consume(hops, 2).give(recurring(phase3, wheat, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 811, consume(timing(justHarvested), flax, 3).give(recurring(phase3, each(sowSpecificAction, flax, 1)), vp, 3), bonusCard);
        addCard(bonusDeck, 812, consume(timing(justHarvested), barley, 3).give(recurring(phase3, each(sowSpecificAction, barley, 1)), vp, 3), bonusCard);
        addCard(bonusDeck, 813, consume(hops, 3).give(recurring(phase3, tool, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 814, consume(clay, 3).give(recurring(phase3, each(wheat, clay, 1)), vp, 3), bonusCard);
        addCard(bonusDeck, 815, consume(cardsFromHand, 3).give(recurring(phase3, gatewayCard, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 816, consume(timing(justHarvested), wheat, 3).give(recurring(phase3, each(sowSpecificAction, wheat, 1)), vp, 3), bonusCard);
        addCard(bonusDeck, 817, consume(jewelry, 1).give(recurring(phase3, tool, 1), vp, 4), bonusCard);
        addCard(bonusDeck, 818, consume(jewelry, 1, tool, 1).give(recurring(phase3, each(flax, wool, 1)), vp, 4), bonusCard);
        addCard(bonusDeck, 819, consume(fieldAnyLvl, 2).give(recurring(phase3, fieldLvl4, 1), vp, 4), bonusCard);
        addCard(bonusDeck, 820, consume(tool, 2).give(recurring(phase3, meat, 1), vp, 4), bonusCard);
        addCard(bonusDeck, 821, consume(clay, 4).give(recurring(phase3, clay, 2), vp, 4), bonusCard);
        addCard(bonusDeck, 822, consume(barley, flax, hops, wheat, choose(3)).give(recurring(phase3, hops, 1), vp, 5), bonusCard);
        addCard(bonusDeck, 823, consume(sheep, 2).give(recurring(phase3, sheep, 1), vp, 5), bonusCard);
        addCard(bonusDeck, 824, consume(fieldWithCrop, 1).give(recurring(phase3, fieldLvl3, 1), vp, 6), bonusCard);
        addCard(bonusDeck, 825, consume(fieldAnyLvl, 3).give(recurring(phase3, fieldLvl5, 1), vp, 6), bonusCard);
        addCard(bonusDeck, 826, consume(jewelry, 2).give(recurring(phase3, barley, 2), vp, 7), bonusCard);
        addCard(bonusDeck, 827, consume(jewelry, 3).give(recurring(phase3, jewelry, 1), vp, 8), bonusCard);
        addCard(bonusDeck, 828, require(fieldWithoutCrop, exactly(0)).give(recurring(phase3, hops, 1), vp, 2), bonusCard);
        addCard(bonusDeck, 829, require(sheep, each(5)).give(recurring(phase3, wool, 1), vp, 2), bonusCard);
        addCard(bonusDeck, 830, require(clay, each(7)).give(recurring(phase3, clay, 1), vp, 2), bonusCard);
        addCard(bonusDeck, 831, require(fieldLvl2, exactly(0), fieldLvl3, exactly(0), fieldLvl4, exactly(0)).give(recurring(phase3, sowAnyAction, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 832, require(fieldLvl2, exactly(0), fieldLvl3, exactly(0), fieldLvl4, exactly(0)).give(recurring(phase3, milk, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 833, require(milk, moreXThanY(doubleRoundNumber)).give(recurring(phase3, leather, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 834, require(leather, each(3)).give(recurring(phase3, leather, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 835, require(jewelry, each(5)).give(recurring(phase3, meat, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 836, require(hops, each(6)).give(recurring(phase3, wheat, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 837, require(wool, each(6)).give(recurring(phase3, wool, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 838, require(flax, each(7)).give(recurring(phase3, flax, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 839, require(tool, each(8)).give(recurring(phase3, toolToJewelryExchange, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 840, require(tool, each(3), sheep, each(3), jewelry, each(3)).give(recurring(phase3, farmyardCard, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 841, require(barley, each(10)).give(recurring(phase3, barley, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 842, require(barley, each(7), hops, each(5)).give(recurring(phase3, barley, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 843, require(milk, each(7), sheep, each(6)).give(recurring(phase3, sheep, 1), vp, 3), bonusCard);
        addCard(bonusDeck, 844, require(wheat, each(6), meat, each(5)).give(recurring(phase3, each(wheat, meat, 1)), vp, 4), bonusCard);
        addCard(bonusDeck, 845, require(meat, each(6)).give(recurring(phase3, meat, 1), vp, 6), bonusCard);

        addCard(pointDeck, 901, require(pointCard, 1, bonusCard, 7).give(vp, 2), pointCard);
        addCard(pointDeck, 902, require(pointCard, 1).give(vp, 3), pointCard);
        addCard(pointDeck, 903, consume(tool, 3).give(vp, 4), pointCard);
        addCard(pointDeck, 904, require(gatewayCard, 6).give(vp, 5), pointCard);
        addCard(pointDeck, 905, consume(tool, 4).give(vp, 6), pointCard);
        addCard(pointDeck, 906, require(farmyardCard, 6).give(vp, 6), pointCard);
        addCard(pointDeck, 907, consume(choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 5), choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 5)).give(vp, 7), pointCard);
        addCard(pointDeck, 908, consume(choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 4), choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 4), choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 4)).give(vp, 8), pointCard);
        addCard(pointDeck, 909, consume(hide, 2, jewelry, 3).give(vp, 10), pointCard);
        addCard(pointDeck, 910, consume(meat, 5).give(vp, 10), pointCard);
        addCard(pointDeck, 911, consume(barley, 6, jewelry, 2).give(vp, 10), pointCard);
        addCard(pointDeck, 912, consume(hops, 10).give(vp, 10), pointCard);
        addCard(pointDeck, 913, consume(choose(barley, flax, hops, rye, 11)).give(vp, 10), pointCard);
        addCard(pointDeck, 914, consume(flax, 6, hops, 6).give(vp, 10), pointCard);
        addCard(pointDeck, 915, consume(hops, 6, rye, 6).give(vp, 10), pointCard);
        addCard(pointDeck, 916, consume(clay, 12).give(vp, 10), pointCard);
        addCard(pointDeck, 917, consume(wool, 12).give(vp, 10), pointCard);
        addCard(pointDeck, 918, consume(choose(wool, 13, milk, 16)).give(vp, 10), pointCard);
        addCard(pointDeck, 919, consume(choose(barley, flax, hops, rye, wheat, wool, milk, clay, meat, leather, jewelry, 14)).give(vp, 10), pointCard);
        addCard(pointDeck, 920, consume(milk, 14).give(vp, 10), pointCard);
        addCard(pointDeck, 921, consume(jewelry, 4).give(vp, 12), pointCard);
        addCard(pointDeck, 922, consume(removeFieldsNotRow2, 7).give(vp, 12), pointCard);
        addCard(pointDeck, 923, consume(sheep, 8).give(vp, 12), pointCard);
        addCard(pointDeck, 924, consume(removeFields, 8).give(vp, 13), pointCard);
        addCard(pointDeck, 925, consume(choose(each(jewelry, 5), each(sheep, 10))).give(vp, 14), pointCard);
    }


    private static Effect recurring(Phase phase, ResourceUnits unit, int amount) {
        Effect e = Effect.of(each(unit, amount), true, phase);
        
        return e;
    }
    private static Effect recurring(Phase phase, ResourceEntry entry){
        Effect e = Effect.of(entry, true, phase);

        return e;
    }

    private static int maxSheepOnSingleFarmyard(GameState state, Player player) {
        return Arrays.stream(state.table.playerAreas.get(player.index).turnTracker.sheeps).mapToDouble(s->s.get(sheep)).max().getAsDouble();
    }

    private static Card addCard(List<Card> deck, int appendixIndex, ReqAndEffectBuilder builder, ResourceUnits cardType) {
        return addCard(deck, appendixIndex, builder.build(), cardType);
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
