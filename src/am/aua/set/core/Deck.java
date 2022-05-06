package am.aua.set.core;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
     private ArrayList<Card> fullDeck;

    public Deck(){
        this.fullDeck = new ArrayList<Card>();
    }

    //method that creates a deck of 81 unique cards

    public  ArrayList<Card> createDeck() {
        for (Card.PossibleColors col : Card.PossibleColors.values()) {
            for (Card.PossibleShadings shd : Card.PossibleShadings.values()) {
                for (Card.PossibleShapes shp : Card.PossibleShapes.values()) {
                    for (Card.PossibleNumbers num : Card.PossibleNumbers.values()) {
                        fullDeck.add(new Card(col, shd, shp, num));
                    }
                }
            }
        }
        return fullDeck;
    }

    //method that shuffles the deck
    public ArrayList<Card> shuffle(ArrayList<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    public ArrayList<Card> createCardsOnBoard (ArrayList<Card> deck) {
        ArrayList<Card> cardsOnBoard = (ArrayList<Card>) deck.subList(0, 12);
        return cardsOnBoard;
    }
}

