package bouldercow.card;

import bouldercow.flow.effects.CauseAndEffect;
import bouldercow.flow.effects.ResourceUnits;
import bouldercow.symbols.ImageRef;
import bouldercow.symbols.SymbolicDisplay;

public class Card {
    public int appendixIndex;
    public String title;
    public CauseAndEffect causeAndEffect;
    public ImageRef cardImage;
    public String description;
    public SymbolicDisplay symbols;
    public ResourceUnits cardType;

    public Card(){
    }
    public Card(int appendixIndex, String title){
        this.appendixIndex = appendixIndex;
        this.title = title;
    }

    public static String calculateDescription(Card card) {
        return "";
    }

    public static SymbolicDisplay calculateSymbols(Card card) {
        return null;
    }
}
