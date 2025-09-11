package bouldercow.areas.board;

import bouldercow.card.Card;
import bouldercow.flow.effects.ReqAndEffect;
import bouldercow.flow.effects.ResourceSet;
import bouldercow.flow.effects.ResourceUnits;

import java.util.ArrayList;
import java.util.List;

public class DeckTile  {
    public DeckImage img = new DeckImage();
    public List<Card> deck = new ArrayList<>();
    public TitleText title = new TitleText();
    public WorkerPlacementLocation row2 = new WorkerPlacementLocation(2);
    public WorkerPlacementLocation row1 = new WorkerPlacementLocation(1);
    public ReqAndEffect reqAndEffect = ReqAndEffect.of(ResourceSet.all(ResourceUnits.worker,1), ResourceSet.all(ResourceUnits.bonusCard, 1, ResourceUnits.cock, 1), true, false);

    public DeckTile() {
    }
}
