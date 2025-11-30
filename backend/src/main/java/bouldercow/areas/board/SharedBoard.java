package bouldercow.areas.board;

import bouldercow.flow.effects.EffectModifier;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;
import org.apache.commons.lang3.NotImplementedException;

public class SharedBoard implements IHoldsResources {
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


    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        //deck tiles can hold the rooster

        for (int i = 0; i < deckTiles.length; i++) {
            for (int j = 0; j < deckTiles[i].length; j++) {
                re.subEntries.add(deckTiles[i][j].allResources());
                re.modifiers.add(EffectModifier.ON);
                re.values.add((double) (i*deckTiles[i].length+j));//index of deck tile
                re.referenceUnits.add(ResourceUnits.deckTile);
            }
        }
        for (int i = 0; i < resourceTiles.length; i++) {
            for (int j = 0; j < resourceTiles[i].length; j++) {
                re.subEntries.add(resourceTiles[i][j].allResources());
                re.modifiers.add(EffectModifier.ON);
                re.values.add((double) (i*resourceTiles[i].length+j));//index of resource tile
                re.referenceUnits.add(ResourceUnits.resourceTile);
            }
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        //TODO:check if tile is empty and the number of resources available could fill the earliest open row.
        throw new NotImplementedException();
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        //TODO: fill the row. returning the reward for this tile will be handled elsewhere
        return false;
    }
}
