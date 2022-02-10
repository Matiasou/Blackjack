import java.util.*;
import java.util.Scanner;

public class Player {
    
private String name;
private int numCards; 
private Card[] hand; 

public Player(String name){
    this.name = name;
    this.hand = new Card[Blackjack.MAX_CARDS_PER_PLAYER];
    this.numCards = 0;
}

public String getName(){
    return this.name;
}

public int getNumCards(){
    return this.numCards;
}

public void addCard(Card card){
    if(card == null || this.numCards >= Blackjack.MAX_CARDS_PER_PLAYER){
        throw new IllegalArgumentException();
    } else{
        this.numCards++;
        for(int i = 0; i < this.hand.length; i++){
            if(this.hand[i] == null){
                this.hand[i] = card;
                return;
            }
        }
    }
}   

public Card getCard(int index){
    if(index < 0 || index >= Blackjack.MAX_CARDS_PER_PLAYER){
        throw new IllegalArgumentException();
    }
    return hand[index];
}

public int getHandValue(){
    int handValue = 0;
    for (int i =0; i<hand.length; i++)
        if(this.hand[i] != null){
        if (this.hand[i].isAce()){
            if (handValue > 10){
                handValue+=1;
            }
            else{
                handValue+=11; 
            }
     }
        handValue += this.hand[i].getValue();
    }
    return handValue;
} 


public void printHand(){ 
    for(int i = 0; i < hand.length; i++){
        if (this.hand[i] != null){
            System.out.print(this.hand[i].toString() + "  ");
        }
    }
System.out.print("(value = " + this.getHandValue() + ")");
}

public boolean hasBlackjack() {
    return (this.numCards == 2 && this.getHandValue() == 21); 
}

public boolean wantsHit(Scanner sc, Player o){
    System.out.println("Want another hit, " + name + "(y/n)?");
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y")){
            return true;
        }else{ 
            return false;
        }
}

public void discardCards(){
    this.numCards = 0;
    for (int i =0; i <this.hand.length; i++){
        this.hand[i] = null;
    }
}
}

