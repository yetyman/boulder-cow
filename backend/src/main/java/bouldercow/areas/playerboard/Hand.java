package bouldercow.areas.playerboard;



import bouldercow.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand  {

    public final List<Card> cards = new ArrayList<>();

    public Hand(){
        for(int i = 0; i < 5; i++)
            cards.add(new Card());

        //TODO: probably add hover rise effect
    }
}
