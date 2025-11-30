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
        ResourceEntry re = new ResourceEntry();
        if (row1.playerId != null) {
            re.resources.add(ResourceUnits.worker);
            re.values.add(1.0);
        }
        if (row2.playerId != null) {
            re.resources.add(ResourceUnits.worker);
            re.values.add(2.0);
        }
        return re;
    }

    @Override
    public String canModifyResource(ResourceEntry resource) {
        if (resource.resources.contains(ResourceUnits.worker)) {
            int workerIndex = resource.resources.indexOf(ResourceUnits.worker);
            double workerCount = resource.values.get(workerIndex);
            
            if (workerCount == 1 && row1.playerId == null) return null;
            if (workerCount == 2 && row2.playerId == null) return null;
            if (workerCount < 0) {
                if (workerCount == -1 && row1.playerId != null) return null;
                if (workerCount == -2 && row2.playerId != null) return null;
            }
            return "Invalid worker placement";
        }
        if (deck.isEmpty()) return "No cards remaining";
        return null;
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        ResourceEntry removed = new ResourceEntry();
        if (resource.resources.contains(ResourceUnits.worker)) {
            int workerIndex = resource.resources.indexOf(ResourceUnits.worker);
            double workerCount = resource.values.get(workerIndex);
            
            if (workerCount == 1) row1.playerId = 1;
            else if (workerCount == 2) row2.playerId = 1;
            else if (workerCount == -1) {
                row1.playerId = null;
                removed.resources.add(ResourceUnits.worker);
                removed.values.add(1.0);
            }
            else if (workerCount == -2) {
                row2.playerId = null;
                removed.resources.add(ResourceUnits.worker);
                removed.values.add(2.0);
            }
        }
        return removed;
    }
}
