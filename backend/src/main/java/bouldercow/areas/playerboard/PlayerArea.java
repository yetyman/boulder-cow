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
    public String canModifyResource(ResourceEntry resource) {
        int onIndex = resource.modifiers.indexOf(EffectModifier.ON);
        if (onIndex == -1) return "No location specified";
        
        ResourceUnits location = resource.referenceUnits.get(onIndex);
        
        return switch (location) {
            case homeBoard -> homeBoard.canModifyResource(resource);
            case treasureChest -> treasureChest.canModifyResource(resource);
            case turnTracker -> turnTracker.canModifyResource(resource);
            case resourceTracker -> resourceTracker.canModifyResource(resource);
            case activeCards, hand -> null; // Cards can always be modified
            default -> "Invalid location";
        };
    }

    @Override
    public ResourceEntry modifyResource(ResourceEntry resource) {
        String canModify = canModifyResource(resource);
        if (canModify != null) return ResourceEntry.empty();
        
        int onIndex = resource.modifiers.indexOf(EffectModifier.ON);
        ResourceUnits location = resource.referenceUnits.get(onIndex);

        return switch (location) {
            case homeBoard -> homeBoard.modifyResource(resource);
            case treasureChest -> treasureChest.modifyResource(resource);
            case turnTracker -> turnTracker.modifyResource(resource);
            case resourceTracker -> resourceTracker.modifyResource(resource);
            case activeCards, hand -> ResourceEntry.empty(); // TODO: implement card modifications
            default -> ResourceEntry.empty();
        };
    }
}
