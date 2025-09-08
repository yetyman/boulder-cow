package bouldercow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import bouldercow.flow.Table;

import java.util.List;
import java.util.ArrayList;

public class GameState {
    public Table table = new Table();
    public long version = 0L;

    public void incrementVersion() {
        this.version++;
    }
}
