package com.datastructures;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a generic table using a list of lists.
 * @param <T> the type of elements stored in the table
 */
public class PhilsTable<T> implements AbstractTble<T> {
    private int numCols;                // Number of columns in the table
    private int numRows;                // Number of rows in the table
    private List<List<T>> table;        // The table data structure

    /**
     * Constructs an empty table.
     * @param t the class of the element type (not used, but kept for compatibility)
     */
    public PhilsTable(Class<T> t){
        table = new ArrayList<List<T>>();
        numCols = 0 ; 
        numRows = 0 ; 
    }

    /**
     * Returns the number of rows in the table.
     * @return number of rows
     */
    public int rows() {
        return numRows;
    }

    /**
     * Returns the number of columns in the table.
     * @return number of columns
     */
    public int cols(){
        return numCols;
    }

    /**
     * Gets the value at the specified row and column.
     * @param i the row index
     * @param j the column index
     * @return the value at (i, j)
     * @throws IndexOutOfBoundsException if indices are out of range
     */
    public T get(int i, int j){
        if(i<0 || i > rows() - 1 || j < 0 || j > cols() - 1 ){
            throw new IndexOutOfBoundsException();
        } 
        return table.get(i).get(j);
    }

    /**
     * Sets the value at the specified row and column.
     * @param i the row index
     * @param j the column index
     * @param x the value to set
     * @return the previous value at (i, j)
     * @throws IndexOutOfBoundsException if indices are out of range
     */
    public T set(int i, int j, T x ){
        if(i<0 || i > rows() - 1 || j < 0 || j > cols() - 1 ){
            throw new IndexOutOfBoundsException();
        }
        return table.get(i).set(j, x);
    }

    /**
     * Adds a new row at the specified index, filled with null values.
     * @param i the index to insert the row at
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void addRows(int i){
        if(i<0 || i > rows()){
            throw new IndexOutOfBoundsException();
        } 
        List<T> newRow = new ArrayList<T>();
        for (int k = 0 ; k < cols() ; k++){
            newRow.add(null);
        }
        table.add(i,newRow);
        numRows++;
    }

    /**
     * Removes the row at the specified index.
     * @param i the index of the row to remove
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void removeRows(int i ) { 
        if(i<0 || i > rows() - 1){
            throw new IndexOutOfBoundsException();
        } 
        table.remove(i);
        numRows--;
    }

    /**
     * Adds a new column at the specified index, filled with null values.
     * @param j the index to insert the column at
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void addCols(int j ){ 
        if(j < 0 || j > cols()){
            throw new IndexOutOfBoundsException();
        }
        for(int i =0 ; i < rows(); i++){
            table.get(i).add(j, null);
        }
        numCols++;
    }

    /**
     * Removes the column at the specified index.
     * @param j the index of the column to remove
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void removeCols(int j){
        if(j < 0 || j > cols() - 1) throw new IndexOutOfBoundsException();
        for (int u = 0; u<rows(); u++){
            table.get(u).remove(j);
        }
        numCols--; 
    }

    /**
     * Returns a string representation of the table.
     * @return string representation of the table
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; j < cols(); j++) {
                sb.append(String.valueOf(get(i, j)));
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Main method for testing the PhilsTable class.
     */
    public static void main(String[] args) {
        int nrows = 9, ncols = 6;
        PhilsTable<Integer> t = new PhilsTable<Integer>(Integer.class);
        // Add columns
        for (int i = 0; i < ncols; i++) {
            t.addCols(t.cols());
        }
        // Add rows
        for (int i = 0; i < nrows; i++) {
            t.addRows(t.rows());
        }
        // Set some values
        for (int i = 1; i <= nrows; i++) {
            t.set(i-1, (i-1)%t.cols(), 1111*i);
        }
        System.out.println(t.toString());
        // Add a column and a row
        t.addCols(2);
        t.addRows(3);
        System.out.println(t.toString());
    }
}