import java.util.*;

public class Dealer extends Player{
    private boolean firstCard;
    
    public Dealer(){
        super("dealer");
        this.firstCard = false; 
    }

    public void revealFirstCard(){
        this.firstCard = true;

    }

    public void printHand(){
        if (!firstCard){
            System.out.print("XX  ");
            for(int i = 1; i< super.getNumCards(); i++){
                System.out.print(super.getCard(i) + "  ");
            }
            // System.out.print("\n");
        }else{
            super.printHand();
        }
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

