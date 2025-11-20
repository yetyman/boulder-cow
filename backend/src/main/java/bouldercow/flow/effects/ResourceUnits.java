package bouldercow.flow.effects;

public enum ResourceUnits {
    //Individual resources
    rye,
    flax,
    barley,
    hops,
    meat,
    milk,
    wool,
    clay,
    leather,

    sheep,
    vp,
    jewelry,
    boulder,
    worker,
    cock,
    tool,
    fieldAnyLvl,//for requirements

    //more specific resources, for simplifying effects
    fieldLvl2,
    fieldLvl3,
    fieldLvl4,
    fieldLvl5,
    fieldLvl5WithCrop,
    fieldWithCrop,//other resource in set reps required crop on field since no card requires separate resources and fields
    fieldWithoutCrop,
    gatewayCard,
    farmyardCard,
    bonusCard,
    pointCard,
    workerSupply,//refers to how many workers are gained each round
    workersRemaining,//refers to how many workers are left this round
    differentCrops,
    anyGoods,
    anyCrops,
    actionSpace,
    actionSpaceFullyOccupied,
    sheepMovementDifferent,//replace with SHEEP UPGRADE DIFFERENT. or CHOOSE SHEEP X UPGRADE
    farmyardCardsPlayed,
    sheepOnSameFarmyard,//replace with on(farmyardCard, sheep, maxOf())
    sheepOnFarmyard,//replace with on(farmyardCard, sheep, total())
    nextTurnSheepCard,
    anyCard,
    hand,
    emptyFarmyardCards,
    cardsPlayed,
    nursery,
    roundNumber,
    quadrant,
    craftBuilding,
    row2,
    row3,
    fullTopRow,
    level,

    //ACTION VALUES
    removeWorkersAction,
    harvestAction,
    sheepMovement,//replace with sheep UPGRADE
    sowAnyAction,
    sowSpecificAction,//other resource in set reps required crop
    fieldUpgrade,
    justBeforeLastAction,
    sheepRemoval,
    workersRemoved,
    craftBuildingAdvanced,
    butcheryActionUsed,
    toolsObtained,
    farmingActionUsed,
    workersExchangedForTools,
    cardPlayedAfterGotten,
    justHarvested,
    jewelrySpent,
    clearActionSpaces,
    useActionSpace,
    sowDoubleCrop,
    moveSheepBackward,
    doubleHarvest,
    harvest,
    harvestTotal,//selects total of harvest. replace with on(harvest, total(anyCrop))
    harvestedSameTypeCrops,
    exchange,
    workerPlacementRequirement, deckTile, resourceTile, activeCards, homeBoard, treasureChest, turnTracker, resourceTracker;

}
