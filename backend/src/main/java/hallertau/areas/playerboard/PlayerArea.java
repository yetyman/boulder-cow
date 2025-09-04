package bouldercow.playerboard;

import bouldercow.player.Player;

public class PlayerArea {
    public Player player = new Player();
    public ActiveCards activeCards = new ActiveCards();
    public HomeBoard homeBoard = new HomeBoard();
    public TreasureChest treasureChest = new TreasureChest();
    public TurnTracker turnTracker = new TurnTracker();
    public ResourceTracker resourceTracker = new ResourceTracker();
    public Hand hand = new Hand();

    public PlayerArea(String name){
        this.player.setName(name);
    }
}
