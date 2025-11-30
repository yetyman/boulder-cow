package bouldercow.areas.playerboard;

import bouldercow.card.Card;
import bouldercow.flow.effects.EffectModifier;
import bouldercow.flow.effects.IHoldsResources;
import bouldercow.flow.effects.ResourceEntry;
import bouldercow.flow.effects.ResourceUnits;
import bouldercow.player.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerArea implements IHoldsResources {
    public Player player = new Player();
    public final List<Card> activeCards = new ArrayList<>();
    public HomeBoard homeBoard = new HomeBoard();
    public TreasureChest treasureChest = new TreasureChest();
    public TurnTracker turnTracker = new TurnTracker();
    public ResourceTracker resourceTracker = new ResourceTracker();
    public final List<Card> hand = new ArrayList<>();

    public PlayerArea() {}
    public PlayerArea(String name){
        this.player.name = name;
        hand.addAll(List.of(new Card(1, "A"), new Card(2, "B"), new Card(3, "C"), new Card(4, "D"), new Card(1, "E"), new Card(1, "F"), new Card(1, "G")));
        activeCards.addAll(List.of(new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A")));
        activeCards.addAll(List.of(new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A")));
        activeCards.addAll(List.of(new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A"), new Card(1, "A")));
    }

    @Override
    public ResourceEntry allResources() {
        ResourceEntry re = new ResourceEntry();
        //deck tiles can hold the rooster

        re.subEntries.add(activeCardResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.activeCards);

        re.subEntries.add(homeBoard.allResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.homeBoard);

        re.subEntries.add(treasureChest.allResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.treasureChest);

        re.subEntries.add(turnTracker.allResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.turnTracker);

        re.subEntries.add(resourceTracker.allResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.resourceTracker);

        re.subEntries.add(handResources());
        re.modifiers.add(EffectModifier.ON);
        re.referenceUnits.add(ResourceUnits.hand);
        return re;
    }

    private ResourceEntry handResources() {
        ResourceEntry re = new ResourceEntry();
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            re.resources.add(card.cardType);
            re.values.add((double) card.appendixIndex);//index!
        }

        return re;
    }

    private ResourceEntry activeCardResources() {
        ResourceEntry re = new ResourceEntry();
        for (int i = 0; i < activeCards.size(); i++) {
            Card card = activeCards.get(i);
            re.resources.add(card.cardType);
            re.values.add((double) card.appendixIndex);//index!

            if(card.resources != null)
                re.subEntries.add(card.resources);
            else
                re.subEntries.add(null);//add null so that indexes always match
        }

        return re;
    }

    @Override
    public boolean modifyResource(ResourceEntry resource) {
        int onIndex = resource.modifiers.indexOf(EffectModifier.ON);
        ResourceUnits location = resource.referenceUnits.get(onIndex);

        switch (location) {
            case homeBoard ->  {
                //TODO: modified with building advancement, boulder pushes, turn reset, and sacrificing building levels
                throw new RuntimeException();
            }
            case treasureChest ->  {
                //TODO: added with jewels, removed by spending jewels
                throw new RuntimeException();
            }
            case turnTracker ->  {
                //TODO: added with sheep, removed by turn advancement, modified by sheep upgrades
                throw new RuntimeException();
            }
            case resourceTracker ->  {
                //TODO: added by many things, removed by spending
                throw new RuntimeException();
            }
            case activeCards ->  {
                //TODO: added from repeats modifier
                throw new RuntimeException();
            }
            case hand ->  {
                //TODO: added from card effect gives(), deckTiles usage, and turn tracker turn advancement
                //removed by playing a card or discarding
                throw new RuntimeException();
            }
        }
        return false;
    }
}
