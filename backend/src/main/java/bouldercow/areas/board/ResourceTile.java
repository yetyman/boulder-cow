package bouldercow.areas.board;


import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.symbols.ImageRef;
import bouldercow.symbols.SymbolicDisplay;

public class ResourceTile implements IHoldsResources {
    public ImageRef image;
    public TitleText title = new TitleText();
    public WorkerPlacementLocation row3 = new WorkerPlacementLocation(3);
    public WorkerPlacementLocation row2 = new WorkerPlacementLocation(2);
    public WorkerPlacementLocation row1 = new WorkerPlacementLocation(1);
    public SymbolicDisplay symbolicDisplay;//filling the empty corner left by the partial rows 1 & 2

    public ResourceTile() {
    }

    @Override
    public ResourceEntry allResources() {
        //TODO: list workers placed on each row including empty rows
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO: check removing and adding workers and whether the deck has remaining cards. there need to be open rows and the offered worker count needs to match the row.
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        String canAdd = canModifyResource(resource);
        if(canAdd != null) {
            throw new RuntimeException(canAdd);
        }

        //TODO: handle adding and removing workers and removing cards from the deck
        throw new RuntimeException("Not implemented yet");
    }
}
