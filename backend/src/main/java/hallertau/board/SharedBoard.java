package bouldercow.board;

public class SharedBoard {
    public ResourceTile[][] resourceTiles = new ResourceTile[4][4];
    public DeckTile[][] deckTiles = new DeckTile[2][2];
    
    public SharedBoard() {
        for (int i = 0; i < deckTiles.length; i++) {
            for (int j = 0; j < deckTiles[i].length; j++) {
                deckTiles[i][j] = new DeckTile();
            }
        }
        for (int i = 0; i < resourceTiles.length; i++) {
            for (int j = 0; j < resourceTiles[i].length; j++) {
                resourceTiles[i][j] = new ResourceTile();
            }
        }
    }
}
