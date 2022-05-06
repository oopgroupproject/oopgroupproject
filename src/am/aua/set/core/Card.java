package am.aua.set.core;
import java.util.ArrayList;
//file i/o
//inheritance
public class Card {

    public enum PossibleColors {RED, GREEN, PURPLE}
    public enum PossibleShadings {EMPTY, FILLED, STRIPED}
    public enum PossibleShapes {DIAMOND, WAVE, OVAL}
    public enum PossibleNumbers {ONE, TWO, THREE}

    private PossibleColors color;
    private PossibleShadings shading;
    private PossibleShapes shape;
    private PossibleNumbers number;

    public Card(){
        this.color = PossibleColors.RED;
        this.shape = PossibleShapes.DIAMOND;
        this.shading = PossibleShadings.EMPTY;
        this.number = PossibleNumbers.ONE;
    }

    public Card(PossibleColors color, PossibleShadings shading, PossibleShapes shape, PossibleNumbers number) {
        this.color = color;
        this.shading = shading;
        this.shape = shape;
        this.number = number;
    }

    //not sure if needed
    public Card(Card copy) {
        Card newDeck = new Card();
        newDeck.color = copy.color;
        newDeck.shape = copy.shape;
        newDeck.shading = copy.shading;
        newDeck.number = copy.number;
    }

    //method that compares 3 cards and checks whether they form a set
    public boolean isSet(Card first, Card second, Card third) {
        if((first.color.ordinal() == second.color.ordinal() && first.color.ordinal() == third.color.ordinal() && second.color.ordinal() == third.color.ordinal()
        || (first.color.ordinal() != second.color.ordinal() && first.color.ordinal() != third.color.ordinal() && second.color.ordinal() != third.color.ordinal()
        ))){
            if((first.shading.ordinal() == second.shading.ordinal() && first.shading.ordinal() == third.shading.ordinal() && second.shading.ordinal() == third.shading.ordinal()
                    || (first.shading.ordinal() != second.shading.ordinal() && first.shading.ordinal() != third.shading.ordinal() && second.shading.ordinal() != third.shading.ordinal()
            ))){
                if((first.shape.ordinal() == second.shape.ordinal() && first.shape.ordinal() == third.shape.ordinal() && second.shape.ordinal() == third.shape.ordinal()
                        || (first.shape.ordinal() != second.shape.ordinal() && first.shape.ordinal() != third.shape.ordinal() && second.shape.ordinal() != third.shape.ordinal()
                ))){
                    if((first.number.ordinal() == second.number.ordinal() && first.number.ordinal() == third.number.ordinal() && second.number.ordinal() == third.number.ordinal()
                            || (first.number.ordinal() != second.number.ordinal() && first.number.ordinal() != third.number.ordinal() && second.number.ordinal() != third.number.ordinal()
                    ))){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    //method to check if there is a set present in the cards on the board
    public boolean isSetPresent(ArrayList<Card> cardsOnBoard){
        for(int firstCard = 0; firstCard <= cardsOnBoard.size() - 3;firstCard++) {
            for(int secondCard = firstCard+1; secondCard <= cardsOnBoard.size() - 2; secondCard++) {
                for(int thirdCard = secondCard+1; thirdCard <= cardsOnBoard.size() - 1; thirdCard++){
                    if(isSet(cardsOnBoard.get(firstCard), cardsOnBoard.get(secondCard), cardsOnBoard.get(thirdCard))){
                        return true;
                    }

                }

            }
        }
        return false;
    }
}