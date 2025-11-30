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
        int onIndex = resource.modifiers.indexOf(EffectModifier.ON);
        if (onIndex == -1) return "No location specified";
        
        ResourceUnits location = resource.referenceUnits.get(onIndex);
        double tileIndex = resource.values.get(onIndex);
        
        if (location == ResourceUnits.deckTile) {
            int row = (int)(tileIndex / deckTiles[0].length);
            int col = (int)(tileIndex % deckTiles[0].length);
            if (row >= deckTiles.length || col >= deckTiles[0].length) return "Invalid deck tile index";
            return deckTiles[row][col].canModifyResource(resource);
        } else if (location == ResourceUnits.resourceTile) {
            int row = (int)(tileIndex / resourceTiles[0].length);
            int col = (int)(tileIndex % resourceTiles[0].length);
            if (row >= resourceTiles.length || col >= resourceTiles[0].length) return "Invalid resource tile index";
            return resourceTiles[row][col].canModifyResource(resource);
        }
        return "Invalid location";
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        int onIndex = resource.modifiers.indexOf(EffectModifier.ON);
        ResourceUnits location = resource.referenceUnits.get(onIndex);
        double tileIndex = resource.values.get(onIndex);
        
        if (location == ResourceUnits.deckTile) {
            int row = (int)(tileIndex / deckTiles[0].length);
            int col = (int)(tileIndex % deckTiles[0].length);
            return deckTiles[row][col].modifyResource(resource);
        } else if (location == ResourceUnits.resourceTile) {
            int row = (int)(tileIndex / resourceTiles[0].length);
            int col = (int)(tileIndex % resourceTiles[0].length);
            return resourceTiles[row][col].modifyResource(resource);
        }
        return ResourceEntry.empty();
    }
}
