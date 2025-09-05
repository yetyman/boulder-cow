package bouldercow.board;

import bouldercow.card.Card;
import bouldercow.card.CardPlacementLocation;

import java.util.ArrayList;
import java.util.List;

public class DeckTile  {
    CardPlacementLocation img = new CardPlacementLocation();
    List<Card> deck = new ArrayList<>();
    TitleText title = new TitleText();
    public WorkerPlacementLocation[] row2 = new WorkerPlacementLocation[2];
    public WorkerPlacementLocation[] row1 = new WorkerPlacementLocation[1];

    public DeckTile() {
        row2[0]=w();
        row2[1]=w();

        row1[0]=w();
    }

    private WorkerPlacementLocation w() {
        return new WorkerPlacementLocation();
    }
}
