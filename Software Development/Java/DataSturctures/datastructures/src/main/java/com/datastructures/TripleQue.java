package com.datastructures;

import java.util.*;

/**
 * TripleQue is a list-like data structure that uses two phillsArrayDeque instances
 * to efficiently support operations at both ends and in the middle.
 * @param <T> the type of elements stored in the TripleQue
 */
public class TripleQue<T> extends AbstractList<T> {
    protected phillsArrayDeque<T> right ; // Deque for the right half
    protected phillsArrayDeque<T> left ;  // Deque for the left half

    /**
     * Constructs an empty TripleQue.
     * @param t the class of the element type
     */
    public TripleQue(Class<T> t){
        right = new phillsArrayDeque<T>(t);
        left = new phillsArrayDeque<T>(t);
    }

    /**
     * Returns the total number of elements in the TripleQue.
     * @return the size of the TripleQue
     */
    public int size(){
        return right.size() + left.size();
    }

    /**
     * Gets the element at the specified index.
     * @param i the index
     * @return the element at index i
     */
    public T get(int i){
        if ( i< right.size()) return right.get(i);
        else return left.get(i- right.size());
    }

    /**
     * Sets the element at the specified index to a new value.
     * @param i the index
     * @param x the new value
     * @return the previous value at index i
     */
    public T set(int i, T x ){ 
        if ( i<right.size()) return right.set(i, x);
        else return left.set(i- right.size(),x);
    }

    /**
     * Inserts an element at the specified index.
     * @param i the index to insert at
     * @param x the element to insert
     */
    public void add (int i, T x) {
        if ( i< right.size())right.add(i,x);
        else left.add(i - right.size(),x);
        reBalance();
    }

    /**
     * Removes and returns the element at the specified index.
     * @param i the index to remove
     * @return the removed element
     */
    public T remove(int i){ 
        T x;
        if(i<right.size()) x = right.remove(i);
        else x = left.remove(i - right.size());
        reBalance();
        return x; 
    }

    /**
     * Rebalances the left and right deques to keep their sizes within 1 of each other.
     */
    protected void reBalance(){ 
        if (right.size()==left.size() +2 ){
            left.add(0,right.remove(right.size()-1));
        }else if (left.size()==right.size()+2){
            right.add(right.size(),left.remove(0));
        }
    }

    /**
     * Removes all elements from the TripleQue.
     */
    public void clear(){
         right.clear();
         left.clear();
    }

    /**
     * Main method for benchmarking and testing the TripleQue.
     */
    public static void main(String[] args) {
        List<Integer> tr = new TripleQue<Integer>(Integer.class);
        int K = 1000000;
        StopWatch s = new StopWatch();

        // Appending K items to the end
        System.out.print("Appending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Prepending K items to the front
        System.out.print("Prepending " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(0, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Inserting K items in the middle
        System.out.print("Midpending(?!) " + K + " items...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.add(tr.size() / 2, i);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Removing K items from the left (end)
        System.out.print("Removing " + K + " items from the left...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() - 1);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Removing K items from the right (front)
        System.out.print("Removing " + K + " items from the right...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(0);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");

        // Removing K items from the middle
        System.out.print("Removing " + K + " items from the middle...");
        System.out.flush();
        s.start();
        for (int i = 0; i < K; i++) {
            tr.remove(tr.size() / 2);
        }
        s.stop();
        System.out.println("done (" + s.elapsedSeconds() + "s)");
    }
}
