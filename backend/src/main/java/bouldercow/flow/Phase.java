package bouldercow.flow;

public enum Phase {
    removeWorkers,//remove workers from board
    newWorkers,//new workers by building number
    income,//card income
    actions,//main phase of the game
    newCard,//card from turn tracker(kills sheep)
    fallowFields,//move up empty fields
    harvest,//harvest from planted fields
    milking,//JELK
    buildings,//pay to upgrade buildings
    boulders;//reset boulder positions
}
