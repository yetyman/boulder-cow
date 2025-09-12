package bouldercow.card;

import bouldercow.flow.Phase;
import bouldercow.flow.effects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bouldercow.flow.effects.Requirement.*;
import static bouldercow.flow.effects.ResourceUnits.*;
import static bouldercow.flow.effects.ResourceSet.*;
import static bouldercow.flow.effects.ReqAndEffectBuilder.*;

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
        addCard(beginnerDeck, 1, require(stagedReq(sheep, 2, 3, 4)).give(stagedEff(sheepMovement, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 2, require(either(barley, 7, hops, 2)).give(sheep, 1), gatewayCard);
        addCard(beginnerDeck, 3, require(wool, 1, sheep, 3).give(sheep, 1), gatewayCard);
        addCard(beginnerDeck, 4, require(moreXThanY(clay, workerSupply)).give(ResourceSet.all(fieldLvl4, 1)), gatewayCard);
        addCard(beginnerDeck, 5, require(sheep, 4).give(wool, 1, fieldLvl2, 1), gatewayCard);
        addCard(beginnerDeck, 6, require(barley, 4, hops, 4).give(tool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 7, require(stagedReq(meat, 1, 2, 5)).give(stagedEff(tool, 0, 1, 2, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 8, require(flax, 3, wool, 2).give(tool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 9, require(actionSpaceFullyOccupied, 1).give(jewelry, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 10, require(either(leather, 2, meat, 2)).give(clay, 2, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 11, require(milk, 1, wool, 1).give(clay, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 12, require(tool, 4).give(clay, 1, milk, 1, wool, 1), gatewayCard);
        addCard(beginnerDeck, 13, require(sheep, 3).give(clay, 1, sowAnyAction, 1), gatewayCard);
        addCard(beginnerDeck, 14, require(stagedReq(fieldLvl5, 1, 2, 4, 6)).give(stagedEff(sowAnyAction, 0, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 15, require(stagedReq(fieldAnyLvl, 4, 5, 6, 7)).give(stagedEff(differentCrops, 1, 2, 3, 4)), gatewayCard);
        addCard(beginnerDeck, 16, require(tool, 3, hops, 3).give(either(barley, 2, wheat, 2), bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 17, require(ResourceSet.all(fieldWithCrop, 2, hops, 2)).give(barley, 2, wheat, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 18, require(moreXThanY(barley, workerSupply)).give(flax, 2, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 19, require(fieldAnyLvl, 6).give(wheat, 1, leather, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 20, require(and(Requirement.special(jewelrySpent,1), Requirement.of(exactly(jewelry, 0), false))).give(hops, 1, milk, 1), gatewayCard);//this ones tough to define you "just" spent one jewelry down to 0. so spent 1 and have 0, but not consume it for this effect, just "just spent it" so this indicates some kind of history or a triggered response for the user to respond to
        addCard(beginnerDeck, 21, require(stagedReq(sheep, 1, 2, 4, 7)).give(stagedEff(either(hops,1,meat,1), either(hops,2,meat,2), either(hops,3,meat,3), either(hops,4,meat,4))), gatewayCard);
        addCard(beginnerDeck, 22, require(stagedReq(exactly(fieldWithCrop, 1), exactly(fieldWithCrop, 2))).give(harvestAction, 1, bonusCard, 1), gatewayCard);//the staging here actually does nothing. but saves us from figuring out either(exactly, exactly) of the same resource. should give the right symbology too
        addCard(beginnerDeck, 23, require(exactly(fieldWithoutCrop, 0)).give(fieldUpgrade, 1), gatewayCard);
        addCard(beginnerDeck, 24, require(stagedReq(wheat, 6, 9, 12)).give(stagedEff(milk, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 25, require(stagedReq(clay, 4, 5, 7)).give(stagedEff(milk, 1, 2, 3)), gatewayCard);
        addCard(beginnerDeck, 26, require(tool, 3, sheep, 2).give(milk, 1, wool, 1, bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 27, require(exactly(fieldWithoutCrop, 1)).give(ResourceSet.all(sowSpecificAction, 1, wool, 1), bonusCard, 1), gatewayCard);
        addCard(beginnerDeck, 28, require(stagedReq(jewelry, 2, 3, 4)).give(stagedEff(leather, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(beginnerDeck, 29, require(stagedReq(total(bonusCard, pointCard, farmyardCard, 2),total(bonusCard, pointCard, farmyardCard, 4),total(bonusCard, pointCard, farmyardCard, 6))).give(stagedEff(meat, 1, 2, 3)), gatewayCard);
        addCard(beginnerDeck, 30, require(and(stagedReq(fieldAnyLvl, 2, 4, 6), jewelry, 1)).give(and(stagedEff(farmyardCard, 0, 1, 2), clay, 1, bonusCard, 1)), gatewayCard);

        addCard(advancedDeck, 101, require(stagedReq(flax, 4, 6, 8)).give(stagedEff(sheepMovementDifferent, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 102, require(either(flax, 8, barley, 8, wheat, 8, hops, 8)).give(sheep, 1), gatewayCard);
        addCard(advancedDeck, 103, require(leather, 3, meat, 3, wool, 3).give(sheep, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 104, require(stagedReq(hops, 2, 3, 4, 5)).give(stagedEff(ResourceSet.all(fieldLvl2, 1), ResourceSet.all(fieldLvl3, 1), ResourceSet.all(fieldLvl4, 1), ResourceSet.all(fieldLvl5, 1))), gatewayCard);
        addCard(advancedDeck, 105, require(moreXThanY(sheep, fieldAnyLvl)).give(fieldLvl3, 1), gatewayCard);
        addCard(advancedDeck, 106, require(actionSpaceFullyOccupied, 1).give(tool, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 107, require(stagedReq(leather, 1, 2, 4)).give(stagedEff(tool, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 108, require(stagedReq(barley, 6, 11, 16)).give(stagedEff(tool, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 109, require(stagedReq(either(bonusCard, farmyardCard, pointCard, gatewayCard, 3),either(bonusCard, farmyardCard, pointCard, gatewayCard, 7), either(bonusCard, farmyardCard, pointCard, gatewayCard, 11))).give(stagedEff(jewelry, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 110, require(stagedReq(all(flax, 2, barley, 2, wheat, 2), all(flax, 5, barley, 5, wheat, 5), all(flax, 7, barley, 7, wheat, 7))).give(and(stagedEff(bonusCard, 0, 1, 2), hops, 1, clay, 1)), gatewayCard);
        addCard(advancedDeck, 111, require(stagedReq(tool, 3, 4, 6)).give(and(stagedEff(bonusCard, 0, 1, 2), wheat, 1, clay, 1)), gatewayCard);
        addCard(advancedDeck, 112, require(exactly(fieldWithoutCrop, 1)).give(ResourceSet.all(sowSpecificAction, 1, clay, 1), bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 113, require(exactly(fieldWithoutCrop, 1)).give(hops, 1, sowAnyAction, 1), gatewayCard);
        addCard(advancedDeck, 114, require(stagedReq(fieldAnyLvl, 5, 6, 7)).give(and(either(barley, 1, flax, 1, hops, 1, wheat, 1), stagedEff(sowAnyAction, 0, 1, 2, bonusCard, 1))), gatewayCard);
        addCard(advancedDeck, 115, require(fieldAnyLvl, 4, tool, 3).give(either(barley, 3, flax, 3, hops, 3, wheat, 3)), gatewayCard);
        addCard(advancedDeck, 116, require(moreXThanY(flax, workerSupply)).give(wheat, 3, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 117, require(stagedReq(hops, 2, 4, 6)).give(stagedEff(barley, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 118, require(actionSpaceFullyOccupied, 1).give(barley, 2, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 119, require(barley, 6, wheat, 6).give(flax, 1, hops, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 120, require(fieldLvl5WithCrop, 3).give(hops, 1, wool, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 121, require(Phase.phase4, stagedReq(worker, 1, 2, 3)).give(fieldUpgrade, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 122, require(exactly(fieldWithCrop, 2)).give(fieldUpgrade, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 123, require(stagedReq(tool, 1, 3, 4, 6)).give(stagedEff(milk, 0, 1, 2, 3, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 124, require(stagedReq(meat, 1, 2, 3)).give(stagedEff(milk, 1, 2, 3)), gatewayCard);
        addCard(advancedDeck, 125, require(wool, 2).give(milk, 1, bonusCard, 1), gatewayCard);
        addCard(advancedDeck, 126, require(stagedReq(farmyardCardsPlayed, 1, 3, 4)).give(stagedEff(meat, 0, 2, 3, milk, 2)), gatewayCard);
        addCard(advancedDeck, 127, require(stagedReq(fieldWithCrop, 3, 4, 5, 6, 7)).give(stagedEff(wool, 0, 1, 2, 3, 4, bonusCard, 1)), gatewayCard);
        addCard(advancedDeck, 128, require(tool, 4).give(flax, 1, leather, 2), gatewayCard);
        addCard(advancedDeck, 129, require(moreXThanY(total(leather, wool, 1), workerSupply)).give(meat, 3), gatewayCard);
        addCard(advancedDeck, 130, require(leather, 1, meat, 1).give(either(hops, 1, clay, 1), farmyardCard, 1), gatewayCard);

        addCard(expertDeck, 201, , gatewayCard);
        addCard(expertDeck, 201, , gatewayCard);
        addCard(expertDeck, 202, , gatewayCard);
        addCard(expertDeck, 203, , gatewayCard);
        addCard(expertDeck, 204, , gatewayCard);
        addCard(expertDeck, 205, , gatewayCard);
        addCard(expertDeck, 206, , gatewayCard);
        addCard(expertDeck, 207, , gatewayCard);
        addCard(expertDeck, 208, , gatewayCard);
        addCard(expertDeck, 209, , gatewayCard);
        addCard(expertDeck, 210, , gatewayCard);
        addCard(expertDeck, 211, , gatewayCard);
        addCard(expertDeck, 212, , gatewayCard);
        addCard(expertDeck, 213, , gatewayCard);
        addCard(expertDeck, 214, , gatewayCard);
        addCard(expertDeck, 215, , gatewayCard);
        addCard(expertDeck, 216, , gatewayCard);
        addCard(expertDeck, 217, , gatewayCard);
        addCard(expertDeck, 218, , gatewayCard);
        addCard(expertDeck, 219, , gatewayCard);
        addCard(expertDeck, 220, , gatewayCard);
        addCard(expertDeck, 221, , gatewayCard);
        addCard(expertDeck, 222, , gatewayCard);
        addCard(expertDeck, 223, , gatewayCard);
        addCard(expertDeck, 224, , gatewayCard);
        addCard(expertDeck, 225, , gatewayCard);
        addCard(expertDeck, 226, , gatewayCard);
        addCard(expertDeck, 227, , gatewayCard);
        addCard(expertDeck, 228, , gatewayCard);
        addCard(expertDeck, 229, , gatewayCard);
        addCard(expertDeck, 230, , gatewayCard);

        addCard(masterDeck, 301, , gatewayCard);
        addCard(masterDeck, 301, , gatewayCard);
        addCard(masterDeck, 302, , gatewayCard);
        addCard(masterDeck, 303, , gatewayCard);
        addCard(masterDeck, 304, , gatewayCard);
        addCard(masterDeck, 305, , gatewayCard);
        addCard(masterDeck, 306, , gatewayCard);
        addCard(masterDeck, 307, , gatewayCard);
        addCard(masterDeck, 308, , gatewayCard);
        addCard(masterDeck, 309, , gatewayCard);
        addCard(masterDeck, 310, , gatewayCard);
        addCard(masterDeck, 311, , gatewayCard);
        addCard(masterDeck, 312, , gatewayCard);
        addCard(masterDeck, 313, , gatewayCard);
        addCard(masterDeck, 314, , gatewayCard);
        addCard(masterDeck, 315, , gatewayCard);
        addCard(masterDeck, 316, , gatewayCard);
        addCard(masterDeck, 317, , gatewayCard);
        addCard(masterDeck, 318, , gatewayCard);
        addCard(masterDeck, 319, , gatewayCard);
        addCard(masterDeck, 320, , gatewayCard);
        addCard(masterDeck, 321, , gatewayCard);
        addCard(masterDeck, 322, , gatewayCard);
        addCard(masterDeck, 323, , gatewayCard);
        addCard(masterDeck, 324, , gatewayCard);
        addCard(masterDeck, 325, , gatewayCard);
        addCard(masterDeck, 326, , gatewayCard);
        addCard(masterDeck, 327, , gatewayCard);
        addCard(masterDeck, 328, , gatewayCard);
        addCard(masterDeck, 329, , gatewayCard);
        addCard(masterDeck, 330, , gatewayCard);

        addCard(hopsDeck, 401, , farmyardCard);
        addCard(hopsDeck, 401, , farmyardCard);
        addCard(hopsDeck, 402, , farmyardCard);
        addCard(hopsDeck, 403, , farmyardCard);
        addCard(hopsDeck, 404, , farmyardCard);
        addCard(hopsDeck, 405, , farmyardCard);
        addCard(hopsDeck, 406, , farmyardCard);
        addCard(hopsDeck, 407, , farmyardCard);
        addCard(hopsDeck, 408, , farmyardCard);
        addCard(hopsDeck, 409, , farmyardCard);
        addCard(hopsDeck, 410, , farmyardCard);
        addCard(hopsDeck, 411, , farmyardCard);
        addCard(hopsDeck, 412, , farmyardCard);
        addCard(hopsDeck, 413, , farmyardCard);
        addCard(hopsDeck, 414, , farmyardCard);
        addCard(hopsDeck, 415, , farmyardCard);
        addCard(hopsDeck, 416, , farmyardCard);
        addCard(hopsDeck, 417, , farmyardCard);
        addCard(hopsDeck, 418, , farmyardCard);
        addCard(hopsDeck, 419, , farmyardCard);
        addCard(hopsDeck, 420, , farmyardCard);
        addCard(hopsDeck, 421, , farmyardCard);
        addCard(hopsDeck, 422, , farmyardCard);
        addCard(hopsDeck, 423, , farmyardCard);
        addCard(hopsDeck, 424, , farmyardCard);
        addCard(hopsDeck, 425, , farmyardCard);
        addCard(hopsDeck, 426, , farmyardCard);
        addCard(hopsDeck, 427, , farmyardCard);
        addCard(hopsDeck, 428, , farmyardCard);
        addCard(hopsDeck, 429, , farmyardCard);
        addCard(hopsDeck, 430, , farmyardCard);
        addCard(hopsDeck, 431, , farmyardCard);
        addCard(hopsDeck, 432, , farmyardCard);
        addCard(hopsDeck, 433, , farmyardCard);
        addCard(hopsDeck, 434, , farmyardCard);
        addCard(hopsDeck, 435, , farmyardCard);

        addCard(sheepDeck, 501, , farmyardCard);
        addCard(sheepDeck, 501, , farmyardCard);
        addCard(sheepDeck, 502, , farmyardCard);
        addCard(sheepDeck, 503, , farmyardCard);
        addCard(sheepDeck, 504, , farmyardCard);
        addCard(sheepDeck, 505, , farmyardCard);
        addCard(sheepDeck, 506, , farmyardCard);
        addCard(sheepDeck, 507, , farmyardCard);
        addCard(sheepDeck, 508, , farmyardCard);
        addCard(sheepDeck, 509, , farmyardCard);
        addCard(sheepDeck, 510, , farmyardCard);
        addCard(sheepDeck, 511, , farmyardCard);
        addCard(sheepDeck, 512, , farmyardCard);
        addCard(sheepDeck, 513, , farmyardCard);
        addCard(sheepDeck, 514, , farmyardCard);
        addCard(sheepDeck, 515, , farmyardCard);
        addCard(sheepDeck, 516, , farmyardCard);
        addCard(sheepDeck, 517, , farmyardCard);
        addCard(sheepDeck, 518, , farmyardCard);
        addCard(sheepDeck, 519, , farmyardCard);
        addCard(sheepDeck, 520, , farmyardCard);
        addCard(sheepDeck, 521, , farmyardCard);
        addCard(sheepDeck, 522, , farmyardCard);
        addCard(sheepDeck, 523, , farmyardCard);
        addCard(sheepDeck, 524, , farmyardCard);
        addCard(sheepDeck, 525, , farmyardCard);
        addCard(sheepDeck, 526, , farmyardCard);
        addCard(sheepDeck, 527, , farmyardCard);
        addCard(sheepDeck, 528, , farmyardCard);
        addCard(sheepDeck, 529, , farmyardCard);
        addCard(sheepDeck, 530, , farmyardCard);
        addCard(sheepDeck, 531, , farmyardCard);
        addCard(sheepDeck, 532, , farmyardCard);
        addCard(sheepDeck, 533, , farmyardCard);
        addCard(sheepDeck, 534, , farmyardCard);
        addCard(sheepDeck, 535, , farmyardCard);

        addCard(fieldDeck, 601, , farmyardCard);
        addCard(fieldDeck, 601, , farmyardCard);
        addCard(fieldDeck, 602, , farmyardCard);
        addCard(fieldDeck, 603, , farmyardCard);
        addCard(fieldDeck, 604, , farmyardCard);
        addCard(fieldDeck, 605, , farmyardCard);
        addCard(fieldDeck, 606, , farmyardCard);
        addCard(fieldDeck, 607, , farmyardCard);
        addCard(fieldDeck, 608, , farmyardCard);
        addCard(fieldDeck, 609, , farmyardCard);
        addCard(fieldDeck, 610, , farmyardCard);
        addCard(fieldDeck, 611, , farmyardCard);
        addCard(fieldDeck, 612, , farmyardCard);
        addCard(fieldDeck, 613, , farmyardCard);
        addCard(fieldDeck, 614, , farmyardCard);
        addCard(fieldDeck, 615, , farmyardCard);
        addCard(fieldDeck, 616, , farmyardCard);
        addCard(fieldDeck, 617, , farmyardCard);
        addCard(fieldDeck, 618, , farmyardCard);
        addCard(fieldDeck, 619, , farmyardCard);
        addCard(fieldDeck, 620, , farmyardCard);
        addCard(fieldDeck, 621, , farmyardCard);
        addCard(fieldDeck, 622, , farmyardCard);
        addCard(fieldDeck, 623, , farmyardCard);
        addCard(fieldDeck, 624, , farmyardCard);
        addCard(fieldDeck, 625, , farmyardCard);
        addCard(fieldDeck, 626, , farmyardCard);
        addCard(fieldDeck, 627, , farmyardCard);
        addCard(fieldDeck, 628, , farmyardCard);
        addCard(fieldDeck, 629, , farmyardCard);
        addCard(fieldDeck, 630, , farmyardCard);
        addCard(fieldDeck, 631, , farmyardCard);
        addCard(fieldDeck, 632, , farmyardCard);
        addCard(fieldDeck, 633, , farmyardCard);
        addCard(fieldDeck, 634, , farmyardCard);
        addCard(fieldDeck, 635, , farmyardCard);

        addCard(jewelryDeck, 701, , farmyardCard);
        addCard(jewelryDeck, 701, , farmyardCard);
        addCard(jewelryDeck, 702, , farmyardCard);
        addCard(jewelryDeck, 703, , farmyardCard);
        addCard(jewelryDeck, 704, , farmyardCard);
        addCard(jewelryDeck, 705, , farmyardCard);
        addCard(jewelryDeck, 706, , farmyardCard);
        addCard(jewelryDeck, 707, , farmyardCard);
        addCard(jewelryDeck, 708, , farmyardCard);
        addCard(jewelryDeck, 709, , farmyardCard);
        addCard(jewelryDeck, 710, , farmyardCard);
        addCard(jewelryDeck, 711, , farmyardCard);
        addCard(jewelryDeck, 712, , farmyardCard);
        addCard(jewelryDeck, 713, , farmyardCard);
        addCard(jewelryDeck, 714, , farmyardCard);
        addCard(jewelryDeck, 715, , farmyardCard);
        addCard(jewelryDeck, 716, , farmyardCard);
        addCard(jewelryDeck, 717, , farmyardCard);
        addCard(jewelryDeck, 718, , farmyardCard);
        addCard(jewelryDeck, 719, , farmyardCard);
        addCard(jewelryDeck, 720, , farmyardCard);
        addCard(jewelryDeck, 721, , farmyardCard);
        addCard(jewelryDeck, 722, , farmyardCard);
        addCard(jewelryDeck, 723, , farmyardCard);
        addCard(jewelryDeck, 724, , farmyardCard);
        addCard(jewelryDeck, 725, , farmyardCard);
        addCard(jewelryDeck, 726, , farmyardCard);
        addCard(jewelryDeck, 727, , farmyardCard);
        addCard(jewelryDeck, 728, , farmyardCard);
        addCard(jewelryDeck, 729, , farmyardCard);
        addCard(jewelryDeck, 730, , farmyardCard);
        addCard(jewelryDeck, 731, , farmyardCard);
        addCard(jewelryDeck, 732, , farmyardCard);
        addCard(jewelryDeck, 733, , farmyardCard);
        addCard(jewelryDeck, 734, , farmyardCard);
        addCard(jewelryDeck, 735, , farmyardCard);

        addCard(bonusDeck, 801, , bonusCard);
        addCard(bonusDeck, 801, , bonusCard);
        addCard(bonusDeck, 802, , bonusCard);
        addCard(bonusDeck, 803, , bonusCard);
        addCard(bonusDeck, 804, , bonusCard);
        addCard(bonusDeck, 805, , bonusCard);
        addCard(bonusDeck, 806, , bonusCard);
        addCard(bonusDeck, 807, , bonusCard);
        addCard(bonusDeck, 808, , bonusCard);
        addCard(bonusDeck, 809, , bonusCard);
        addCard(bonusDeck, 810, , bonusCard);
        addCard(bonusDeck, 811, , bonusCard);
        addCard(bonusDeck, 812, , bonusCard);
        addCard(bonusDeck, 813, , bonusCard);
        addCard(bonusDeck, 814, , bonusCard);
        addCard(bonusDeck, 815, , bonusCard);
        addCard(bonusDeck, 816, , bonusCard);
        addCard(bonusDeck, 817, , bonusCard);
        addCard(bonusDeck, 818, , bonusCard);
        addCard(bonusDeck, 819, , bonusCard);
        addCard(bonusDeck, 820, , bonusCard);
        addCard(bonusDeck, 821, , bonusCard);
        addCard(bonusDeck, 822, , bonusCard);
        addCard(bonusDeck, 823, , bonusCard);
        addCard(bonusDeck, 824, , bonusCard);
        addCard(bonusDeck, 825, , bonusCard);
        addCard(bonusDeck, 826, , bonusCard);
        addCard(bonusDeck, 827, , bonusCard);
        addCard(bonusDeck, 828, , bonusCard);
        addCard(bonusDeck, 829, , bonusCard);
        addCard(bonusDeck, 830, , bonusCard);
        addCard(bonusDeck, 831, , bonusCard);
        addCard(bonusDeck, 832, , bonusCard);
        addCard(bonusDeck, 833, , bonusCard);
        addCard(bonusDeck, 834, , bonusCard);
        addCard(bonusDeck, 835, , bonusCard);
        addCard(bonusDeck, 836, , bonusCard);
        addCard(bonusDeck, 837, , bonusCard);
        addCard(bonusDeck, 838, , bonusCard);
        addCard(bonusDeck, 839, , bonusCard);
        addCard(bonusDeck, 840, , bonusCard);
        addCard(bonusDeck, 841, , bonusCard);
        addCard(bonusDeck, 842, , bonusCard);
        addCard(bonusDeck, 843, , bonusCard);
        addCard(bonusDeck, 844, , bonusCard);
        addCard(bonusDeck, 845, , bonusCard);

        addCard(pointDeck, 901, , pointCard);
        addCard(pointDeck, 901, , pointCard);
        addCard(pointDeck, 902, , pointCard);
        addCard(pointDeck, 903, , pointCard);
        addCard(pointDeck, 904, , pointCard);
        addCard(pointDeck, 905, , pointCard);
        addCard(pointDeck, 906, , pointCard);
        addCard(pointDeck, 907, , pointCard);
        addCard(pointDeck, 908, , pointCard);
        addCard(pointDeck, 909, , pointCard);
        addCard(pointDeck, 910, , pointCard);
        addCard(pointDeck, 911, , pointCard);
        addCard(pointDeck, 912, , pointCard);
        addCard(pointDeck, 913, , pointCard);
        addCard(pointDeck, 914, , pointCard);
        addCard(pointDeck, 915, , pointCard);
        addCard(pointDeck, 916, , pointCard);
        addCard(pointDeck, 917, , pointCard);
        addCard(pointDeck, 918, , pointCard);
        addCard(pointDeck, 919, , pointCard);
        addCard(pointDeck, 920, , pointCard);
        addCard(pointDeck, 921, , pointCard);
        addCard(pointDeck, 922, , pointCard);
        addCard(pointDeck, 923, , pointCard);
        addCard(pointDeck, 924, , pointCard);
        addCard(pointDeck, 925, , pointCard);

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
