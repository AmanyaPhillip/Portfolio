package com.datastructures;
import java.lang.reflect.Array;

/**
 * Generic Factory class for creating instances and arrays of a given type.
 * @param <T> the type of objects this factory creates
 */
public class Factory<T> {
    // Holds the Class object representing the type T
    Class<T> l; 

    /**
     * Returns the Class object for type T.
     * @return Class<T> representing the type
     */
    public Class<T> type(){
        return l; 
    }

    /**
     * Constructs a Factory for the given class type.
     * @param clazz the Class object for type T
     */
    public Factory(Class<T> clazz){
        l = clazz;
    }

    /**
     * Creates a new array of type T with the specified size.
     * @param size the length of the new array
     * @return a new array of type T
     */
    @SuppressWarnings({"unchecked"})
    protected T[] newArray(int size){
        return (T[]) Array.newInstance(l, size);
    }

    /**
     * Creates a new instance of type T using the default constructor.
     * @return a new instance of T, or null if instantiation fails
     */
    public T newInstance(){
        T x;
        try {
            x= l.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            x=null;
        }
        return x;
    }
}
