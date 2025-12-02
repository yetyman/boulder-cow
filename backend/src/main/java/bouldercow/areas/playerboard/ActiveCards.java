package bouldercow.areas.playerboard;

import bouldercow.card.Card;
import java.util.ArrayList;
import java.util.List;

public class ActiveCards {
    public final List<Card> cards = new ArrayList<>();

    public ActiveCards(){
        for(int i = 0; i < 5; i++)
            cards.add(new Card(i,"A"+i));
    }
}
