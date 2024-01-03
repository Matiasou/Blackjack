import java.util.*;

public class Dealer extends Player{
    private boolean firstCard;
    
    public Dealer(){
        super("dealer");
        this.firstCard = false; 
    }

    // Constructor and other methods...

    private boolean firstCardHidden = true;

    // Constructor and other methods...

    public void revealFirstCard() {
        this.firstCardHidden = false;
    }

    @Override
    public void printHand() {
        int handValue = 0;

        if (firstCardHidden) {
            System.out.print("XX  ");
            // Start from the second card
            for (int i = 1; i < super.getNumCards(); i++) {
                System.out.print(super.getCard(i) + "  ");
                handValue += super.getCard(i).getValue();
            }
        } else {
            // If first card is not hidden, print all cards and calculate total value
            for (int i = 0; i < super.getNumCards(); i++) {
                System.out.print(super.getCard(i) + "  ");
                handValue += super.getCard(i).getValue();
            }
        }

        // Print the total value of the hand, adjusted for hidden card
        System.out.print("(value = " + handValue + ")");
    } 
    public boolean wantsHit(Scanner sc, Player o){
        if(o.getHandValue() < 17 && super.getHandValue() <= o.getHandValue()){
            return true; 

        }
        if (o.getHandValue() >= 17 && !o.hasBlackjack() && super.getHandValue() < o.getHandValue()){
            return true; 
        }
        return false;
    }

    public void discardsCards(){
        super.discardCards();
        this.firstCard = false; 
        
    }
}

