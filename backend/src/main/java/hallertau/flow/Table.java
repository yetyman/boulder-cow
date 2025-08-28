package bouldercow.flow;

import com.fasterxml.jackson.annotation.JsonIgnore;
import bouldercow.board.SharedBoard;
import bouldercow.player.Player;
import bouldercow.playerboard.PlayerArea;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public SharedBoard sharedBoard = new SharedBoard();
    public List<PlayerArea> playerAreas = new ArrayList<>();
    private int currentPlayerIndex = 0;

    public Table(){
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
}
