package bouldercow.flow;

import bouldercow.areas.board.SharedBoard;
import bouldercow.areas.playerboard.PlayerArea;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public SharedBoard sharedBoard = new SharedBoard();
    public List<PlayerArea> playerAreas = new ArrayList<>();
    public int currentPlayerIndex = 0;

    public Table(){
    }

}
