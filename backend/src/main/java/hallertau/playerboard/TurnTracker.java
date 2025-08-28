package bouldercow.playerboard;



import bouldercow.card.Card;
import bouldercow.card.CardPlacementLocation;

public class TurnTracker  {
    public CardPlacementLocation[] turnLocations = new CardPlacementLocation[6];
    public Card[] cards = new Card[6];
    public SheepVictoryArea sheepVictoryArea = new SheepVictoryArea();

    public TurnTracker(){
        

        for (int i = 0; i < turnLocations.length; i++) {
            turnLocations[i] = new CardPlacementLocation();
        }


    }

}
