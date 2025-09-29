package bouldercow.flow.effects;

public enum ResourceUnits {
    meat,
    milk,
    wool,
    rye,
    clay,
    leather,
    flax,
    barley,
    hops,
    sheep,
    vp,
    jewelry,
    boulder,
    worker,
    cock,
    tool,
    fieldAnyLvl,//for requirements
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
    actionSpaceFullyOccupied,
    differentCrops,
    sheepMovementDifferent,//replace with SHEEP UPGRADE DIFFERENT. or CHOOSE SHEEP X UPGRADE
    farmyardCardsPlayed,
    sheepOnSameFarmyard,//replace with on(farmyardCard, sheep, maxOf())
    sheepOnFarmyard,//replace with on(farmyardCard, sheep, total())
    allCrops,
    anyGoods,
    nextTurnSheepCard,
    anyCrops,
    actionSpace,
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
    topFullRow,
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
    harvestedSameTypeCrops,
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
    exchange;
}
