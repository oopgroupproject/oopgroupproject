package am.aua.set.cli;

import am.aua.set.core.Card;
import am.aua.set.core.Deck;

import java.util.Scanner;

//public class SetConsole {
    /*public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name.");
        String name = sc.nextLine();
        int point = 0;
        Deck deckClass = new Deck();
        Card cardClass = new Card();

        Card[] cardsOnBoard = cardClass.createCardsOnBoard(deck);
        for(int i = 0; i < cardsOnBoard.length; i++ ) {
            System.out.println(cardsOnBoard[i]);
        }
        //remove 12 elements from board
        while(deck.length != 0 && cardClass.isSetPresent(cardsOnBoard) == true) {
            int firstCard = sc.nextInt();
            int secondCard = sc.nextInt();
            int thirdCard = sc.nextInt();
            if(cardClass.isSet(cardsOnBoard[firstCard], cardsOnBoard[secondCard], cardsOnBoard[thirdCard]) == false) {
                point--;
                System.out.println("Not a set, try again.");
            }else{
                point++;
                //method to remove three cards from the cardsOnBoard
                //method to add three new cards from the deck to cardsOnBoard
            }
        }
        System.out.println("Game over. " + name +" has" + point + " points.");
    }
}
*/