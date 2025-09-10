package bouldercow.card;

import bouldercow.flow.effects.CauseAndEffect;
import bouldercow.flow.effects.ResourceSet;
import bouldercow.flow.effects.ResourceUnits;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bouldercow.flow.effects.ResourceUnits.*;
import static bouldercow.flow.effects.ResourceSet.*;
import static bouldercow.flow.effects.CauseAndEffect.*;

public class CardDefinitions {
    public Map<ResourceUnits, List<Card>> cardsByType;
    public Map<String, List<Card>> recommendedDecks;

    static {
        List<Card> beginnerDeck = new ArrayList<>();//gateway cards
        List<Card> advancedDeck = new ArrayList<>();//gateway cards
        List<Card> expertDeck = new ArrayList<>();//gateway cards
        List<Card> masterDeck = new ArrayList<>();//gateway cards
        List<Card> hopsDeck = new ArrayList<>();//farmyard cards
        List<Card> sheepDeck = new ArrayList<>();//farmyard cards
        List<Card> fieldDeck = new ArrayList<>();//farmyard cards
        List<Card> jewelryDeck = new ArrayList<>();//farmyard cards
        List<Card> bonusDeck = new ArrayList<>();//bonus cards
        List<Card> pointDeck = new ArrayList<>();//point cards

        //the rules show 30 of each card type
        addCard(beginnerDeck, 0, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(beginnerDeck, 1, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(beginnerDeck, 2, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(beginnerDeck, 30, of(of(sheep, 1), of(wheat, 1)), gatewayCard);

        addCard(advancedDeck, 101, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(advancedDeck, 1, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(advancedDeck, 130, of(of(sheep, 1), of(wheat, 1)), gatewayCard);

        addCard(expertDeck, 201, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(expertDeck, 1, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(expertDeck, 230, of(of(sheep, 1), of(wheat, 1)), gatewayCard);

        addCard(masterDeck, 301, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(masterDeck, 1, of(of(sheep, 1), of(wheat, 1)), gatewayCard);
        addCard(masterDeck, 330, of(of(sheep, 1), of(wheat, 1)), gatewayCard);

        addCard(hopsDeck, 401, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(hopsDeck, 1, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(hopsDeck, 435, of(of(sheep, 1), of(wheat, 1)), farmyardCard);

        addCard(sheepDeck, 501, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(sheepDeck, 1, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(sheepDeck, 535, of(of(sheep, 1), of(wheat, 1)), farmyardCard);

        addCard(fieldDeck, 601, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(fieldDeck, 1, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(fieldDeck, 635, of(of(sheep, 1), of(wheat, 1)), farmyardCard);

        addCard(jewelryDeck, 701, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(jewelryDeck, 1, of(of(sheep, 1), of(wheat, 1)), farmyardCard);
        addCard(jewelryDeck, 735, of(of(sheep, 1), of(wheat, 1)), farmyardCard);

        addCard(bonusDeck, 801, of(of(sheep, 1), of(wheat, 1)), bonusCard);
        addCard(bonusDeck, 1, of(of(sheep, 1), of(wheat, 1)), bonusCard);
        addCard(bonusDeck, 845, of(of(sheep, 1), of(wheat, 1)), bonusCard);

        addCard(pointDeck, 901, of(of(sheep, 1), of(wheat, 1)), pointCard);
        addCard(pointDeck, 1, of(of(sheep, 1), of(wheat, 1)), pointCard);
        addCard(pointDeck, 925, of(of(sheep, 1), of(wheat, 1)), pointCard);
    }

    private static Card addCard(List<Card> deck, int appendixIndex, CauseAndEffect causeEffect, ResourceUnits cardType) {
        Card card = addCard(appendixIndex, causeEffect, cardType);
        deck.add(card);

        return card;
    }
    private static Card addCard(int appendixIndex, CauseAndEffect causeEffect, ResourceUnits cardType) {
        Card card = new Card(appendixIndex, "");
        card.causeAndEffect = causeEffect;
        card.cardType = cardType;
        card.description = Card.calculateDescription(card);
        card.symbols = Card.calculateSymbols(card);

        return card;
    }
}
