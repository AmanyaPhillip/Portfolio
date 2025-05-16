package com.datastructures;

import java.util.AbstractList;
import java.util.List;

/**
 * A double-ended queue (deque) implementation using two RootishArrayStacks.
 * Balances elements between front and back stacks for efficient operations.
 * @param <T> the type of elements stored in the deque
 */
public class RootArrayDeque<T> extends AbstractList<T> {
    private RootishArrayStack<T> front; // Stack for the front half of the deque
    private RootishArrayStack<T> back;  // Stack for the back half of the deque
    private Class<T> t;                 // Class type for elements

    /**
     * Constructs an empty RootArrayDeque.
     * @param r the class of the element type
     */
    public RootArrayDeque(Class<T> r){
        front = new RootishArrayStack<>(r);
        back = new RootishArrayStack<>(r);
        t = r ;
    }

    /**
     * Gets the element at the specified index.
     * @param i the index
     * @return the element at index i
     */
    public T get(int i){
        if(i< front.size()){ 
            // Access from the front stack (reverse order)
            return front.get(front.size()-i -1); 
        }else{ 
            // Access from the back stack
            return back.get(i - front.size()); 
        }
    }

    /**
     * Sets the element at the specified index to a new value.
     * @param i the index
     * @param x the new value
     * @return the previous value at index i
     */
    public T set(int i , T x) { 
        if (i<front.size()){
            return front.set(front.size()-i-1, x);
        }else{
            return back.set(i - front.size(), x);
        }
    }

    /**
     * Inserts an element at the specified index.
     * @param i the index to insert at
     * @param x the element to insert
     */
    public void add(int i , T x){ 
        if(i < front.size()){
            // Insert into the front stack (reverse order)
            front.add(front.size() - i , x); 
        }else { 
            // Insert into the back stack
            back.add(i - front.size(),x);
        }
        balance();
    }

    /**
     * Removes and returns the element at the specified index.
     * @param i the index to remove
     * @return the removed element
     */
    public T remove(int i ){ 
        T x; 
        if( i < front.size()){ 
            x = front.remove(front.size()-i-1);
            balance();
            return x; 
        }else { 
            x = back.remove(i - front.size()); 
            balance(); 
            return x; 
        }
    }

    /**
     * Balances the front and back stacks to ensure neither is too large.
     * Moves elements between stacks if necessary.
     */
    public void balance(){ 
        int n = size(); 
        // If back is much larger than front, move elements from back to front
        if (3*front.size()<back.size()){
            int s = (int)Math.ceil(n/2 - front.size());
            RootishArrayStack<T> frontT = new RootishArrayStack<>(t); 
            RootishArrayStack<T> backT = new RootishArrayStack<>(t);
            frontT.addAll(back.subList(0,s)); 
            frontT.addAll(front);
            backT.addAll(back.subList(s,back.size()));
            front = frontT; 
            back = backT;
        // If front is much larger than back, move elements from front to back
        }else if (3*back.size()<front.size()){
            int s = front.size() - n/2;
            RootishArrayStack<T> frontT = new RootishArrayStack<>(t);
            RootishArrayStack<T> backT = new RootishArrayStack<>(t);
            frontT.addAll(front.subList(s, front.size()));
            backT.addAll(front.subList(0, s));
            backT.addAll(back); 
            front = frontT; 
            back = backT;
        }
    }

    /**
     * Returns the total number of elements in the deque.
     * @return the size of the deque
     */
    public int size(){ return front.size() + back.size();}

    /**
     * Main method for benchmarking and testing the RootArrayDeque.
     */
    public static void main(String[] args) {
        // Create a RootArrayDeque of Integers
        List<Integer> rad = new RootArrayDeque<Integer>(Integer.class);
        int K = 1000000;
        StopWatch s = new StopWatch();

        // Test appending K items
        System.out.print("Appending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.add(i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Test prepending K items
        System.out.print("Prepending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.add(0, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Test removing K items from the back
        System.out.print("Removing " + K + " items from the back...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.remove(rad.size()-1);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Test removing K items from the front
        System.out.print("Removing " + K + " items from the front...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            rad.remove(0);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");
    }
    
}