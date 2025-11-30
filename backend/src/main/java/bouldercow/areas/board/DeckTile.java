package bouldercow.areas.board;

import bouldercow.card.Card;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ReqAndEffect;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

import java.util.ArrayList;
import java.util.List;

import static bouldercow.flow.effects.ReqAndEffectBuilder.consume;
import static bouldercow.flow.effects.ResourceEntry.per;

public class DeckTile implements IHoldsResources {
    public DeckImage img = new DeckImage();
    public List<Card> deck = new ArrayList<>();
    public TitleText title = new TitleText();
    public WorkerPlacementLocation row2 = new WorkerPlacementLocation(2);
    public WorkerPlacementLocation row1 = new WorkerPlacementLocation(1);
    public ReqAndEffect reqAndEffect = consume(ResourceUnits.worker, per(ResourceUnits.workerPlacementRequirement)).give(ResourceUnits.bonusCard, 1, ResourceUnits.cock, 1).build();

    public DeckTile() {
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
