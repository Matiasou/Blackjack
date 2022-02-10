/* 
 * ArrayBag.java
 *
 * A blueprint class for objects that represent a *bag* of other objects --
 * i.e., a collection of items in which the items do not have a position.
 * 
 * Computer Science 112
 *
 * modified by: Matias Ou, matiasou@bu.edu
 */

import java.util.*;

/**
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }
    

    // #1
    public int capacity(){
        return items.length;
    }


    // #2
    public boolean isEmpty() {
        return numItems == 0;
    }


    // #3
    public boolean shrink(int decrease){
        if (decrease == 0){
            return true;
        }
        else if (decrease < 0){
            throw new IllegalArgumentException();
        }
        else if (this.numItems() > (this.capacity() - decrease)){
            return false;
        }else{
            Object[] newArray = new Object [this.capacity() - decrease];
            for(int i = 0; i < (this.capacity() - decrease); i++){
                newArray[i] = items[i];
            } 
            items = newArray;
            return true;
    }
}



    // #4
    public boolean addItems(ArrayBag other){
        if (other.capacity() == 0){
            return true;
        }
        else if (other == null){
            throw new IllegalArgumentException();
        }
        else if (capacity() >= this.numItems + other.numItems()){
            for (int i = 0; i<other.numItems; i++){
                add(other.items[i]);
            }
            return true;
        }
        return false; 
    }


    // #5
    public ArrayBag unionWith(ArrayBag other){
        if (other == null){
            throw new IllegalArgumentException();
        }
        if (this.isEmpty() && other.isEmpty()){
            return this;
        }

        ArrayBag unionArray = new ArrayBag(capacity() + other.capacity());
        
        if (this.isEmpty() || other.isEmpty()){
            if(this.isEmpty()){
                unionArray.addItems(this);
            }
            else if(other.isEmpty()){
                unionArray.addItems(other);
            }    
        }

        for(int i = 0; i < other.numItems; i++){
            unionArray.add(other.items[i]);
        }
        
        for(int i = 0; i < numItems; i++){
            unionArray.add(items[i]);   
        }
        // have a array with all the items together but now have to remove the ones that repeat
        ArrayBag oneOccurenceArray = new ArrayBag(capacity() + other.capacity()); 
        // new one because if do it over unionArray the number of items in one occurence will be less than the two combined
        for(int i = 0; i < unionArray.numItems; i++){
            if(!oneOccurenceArray.contains(unionArray.items[i])){
                oneOccurenceArray.add(unionArray.items[i]);
            }
        }
        return oneOccurenceArray;
    }
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per
        // line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        

    }
}
