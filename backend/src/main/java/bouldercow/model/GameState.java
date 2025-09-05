package bouldercow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import bouldercow.flow.Table;

import java.util.List;
import java.util.ArrayList;

public class GameState {
    private Table table = new Table();
    private long version = 0L;

    public Table getTable() { return table; }
    public void setTable(Table table) { this.table = table; }

    public long getVersion() {
        return version;
    }
    public void setVersion(long version) {
        this.version = version;
    }

    public void incrementVersion() {
        this.version++;
    }
}
