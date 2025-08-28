package bouldercow.board;


import bouldercow.symbols.ImageRef;
import bouldercow.symbols.SymbolicDisplay;

public class ResourceTile  {
    public ImageRef image;
    public TitleText title = new TitleText();
    public WorkerPlacementLocation[] row3 = new WorkerPlacementLocation[3];
    public WorkerPlacementLocation[] row2 = new WorkerPlacementLocation[2];
    public WorkerPlacementLocation[] row1 = new WorkerPlacementLocation[1];
    public SymbolicDisplay symbolicDisplay;//filling the empty corner left by the partial rows 1 & 2

    public ResourceTile() {
        TitleText title = new TitleText();
        row3[0] = w();
        row3[1] = w();
        row3[2] = w();

        row2[0] = w();
        row2[1] = w();

        row1[0] = w();
    }

    private WorkerPlacementLocation w() {
        return new WorkerPlacementLocation();
    }
}
