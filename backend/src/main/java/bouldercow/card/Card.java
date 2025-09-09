package bouldercow.card;

import bouldercow.flow.effects.CauseAndEffect;
import bouldercow.symbols.ImageRef;
import bouldercow.symbols.SymbolicDisplay;

public class Card {
    public String title;
    public CauseAndEffect causeAndEffect;
    public ImageRef cardImage;
    public String description;
    public SymbolicDisplay symbols;

    public Card(){
    }
    public Card(String title){
        this.title = title;
    }
}
