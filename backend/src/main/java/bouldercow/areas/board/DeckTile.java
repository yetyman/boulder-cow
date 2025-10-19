package bouldercow.areas.board;

import bouldercow.card.Card;
import bouldercow.flow.effects.ReqAndEffect;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;

import java.util.ArrayList;
import java.util.List;

import static bouldercow.flow.effects.ReqAndEffectBuilder.consume;
import static bouldercow.flow.effects.ResourceEntry.per;

public class DeckTile  {
    public DeckImage img = new DeckImage();
    public List<Card> deck = new ArrayList<>();
    public TitleText title = new TitleText();
    public WorkerPlacementLocation row2 = new WorkerPlacementLocation(2);
    public WorkerPlacementLocation row1 = new WorkerPlacementLocation(1);
    public ReqAndEffect reqAndEffect = consume(ResourceUnits.worker, per(ResourceUnits.workerPlacementRequirement)).give(ResourceUnits.bonusCard, 1, ResourceUnits.cock, 1).build();

    public DeckTile() {
    }
}
