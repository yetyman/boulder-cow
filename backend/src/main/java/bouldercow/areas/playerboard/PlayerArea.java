package bouldercow.areas.playerboard;

import bouldercow.card.Card;
import bouldercow.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerArea {
    public Player player = new Player();
    public final List<Card> activeCards = new ArrayList<>();
    public HomeBoard homeBoard = new HomeBoard();
    public TreasureChest treasureChest = new TreasureChest();
    public TurnTracker turnTracker = new TurnTracker();
    public ResourceTracker resourceTracker = new ResourceTracker();
    public final List<Card> hand = new ArrayList<>();

    public PlayerArea() {}
    public PlayerArea(String name){
        this.player.name = name;
    }
}
