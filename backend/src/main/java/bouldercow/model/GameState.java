package bouldercow.model;


import bouldercow.flow.Table;

public class GameState {
    public Table table = new Table();
    public long version = 0L;

    public void incrementVersion() {
        this.version++;
    }
}
