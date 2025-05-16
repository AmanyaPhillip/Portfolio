package com.datastructures;

import java.util.AbstractList; 

/**
 * A double-ended queue (deque) implementation using a circular array.
 * @param <T> the type of elements held in this deque
 */
public class phillsArrayDeque<T> extends AbstractList<T> {
    private Factory<T> f; // Factory for creating arrays of type T
    private T[] a;        // The underlying array for storage
    private int d;        // Index of the logical start (front) of the deque
    private int n;        // Number of elements in the deque

    /**
     * Resizes the underlying array to fit the current number of elements.
     * Copies elements to the new array in logical order.
     */
    private void resize(){ 
        T[] b = f.newArray(Math.max(2*n,1));
        for (int l=0; l < n; l++)
            b[l] = a[(d + l ) % a.length];
        a = b; 
        d = 0 ; 
    }

    /**
     * Constructs a new deque for the given element type.
     * @param t the class of the element type
     */
    public phillsArrayDeque(Class<T> t){
        f = new Factory<>(t);
        a = f.newArray(1);
        n = 0; 
        d = 0; 
    }

    /**
     * Returns the number of elements in the deque.
     * @return the size of the deque
     */
    public int size(){ 
        return n; 
    }
    
    /**
     * Gets the element at the specified index.
     * @param i the index
     * @return the element at index i
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int i){ 
        if (i<0 || i>n-1) throw new IndexOutOfBoundsException(); 
        return a[(d+i)% a.length] ;
    }

    /**
     * Sets the element at the specified index to a new value.
     * @param i the index
     * @param x the new value
     * @return the previous value at index i
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T set( int i , T x){ 
        if (i<0 || i>n-1) throw new IndexOutOfBoundsException();
        T y = a[(d + i ) % a.length];
        a[(d+i)% a.length] = x ; 
        return y; 
    }

    /**
     * Inserts an element at the specified index.
     * Shifts elements as necessary to maintain order.
     * @param i the index to insert at
     * @param x the element to insert
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void add(int i, T x ){
        if (i < 0 || i >n ) throw new IndexOutOfBoundsException();
        if ( n+1 > a.length) resize();
        if ( i<n/2){ 
            d = (d==0) ? a.length-1 : d - 1; 
            for (int k=0 ; k<= i-1;k ++){
                a[(d+k)%a.length]= a[(d+k+1)%a.length];
            }
        }else { 
            for (int k=n; k>i;k--){
                a[(d+k)%a.length]= a[(d+k-1)%a.length];
            }
        }
        a[(d+i)%a.length] = x ; 
        n++; 
    }

    /**
     * Removes and returns the element at the specified index.
     * Shifts elements as necessary to maintain order.
     * @param i the index to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T remove(int i){ 
        if ( i<0||i>n-1) throw new IndexOutOfBoundsException();
        T x = a[(d+i)%a.length];
        if(i<n/2){
            for (int k = i; k>0; k--){
                a[(d+k)%a.length] = a[(d+k-1)%a.length];
            }
            d = (d+1)%a.length;
        }else{
            for(int k = i; k<n-1;k++){
                a[(d+k)%a.length] = a[(d+k+1)%a.length];
            }
        }
        n--; 
        if (3*n < a.length) resize();
        return x; 
    }

    /**
     * Removes all elements from the deque and resizes the array.
     */
    public void clear(){ 
        n=0;
        resize();
    }

}
