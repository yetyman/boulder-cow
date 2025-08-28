package bouldercow.player;

import bouldercow.playerboard.PlayerArea;

public class Player {
    private String name;
    private PlayerArea resources;

    public Player() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public PlayerArea getResources() { return resources; }
    public void setResources(PlayerArea resources) { this.resources = resources; }
}