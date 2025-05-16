package com.datastructures;

/**
 * Interface representing a generic table structure.
 * @param <T> the type of elements stored in the table
 */
public interface AbstractTble<T> {
    /**
     * Returns the number of rows in the table.
     * @return number of rows
     */
    int rows();

    /**
     * Returns the number of columns in the table.
     * @return number of columns
     */
    int cols();

    /**
     * Gets the value at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @return the value at (row, col)
     */
    T get(int row, int col);

    /**
     * Sets the value at the specified row and column.
     * @param row the row index
     * @param col the column index
     * @param value the value to set
     * @return the previous value at (row, col)
     */
    T set(int row, int col, T value);

    /**
     * Adds the specified number of rows to the table.
     * @param rows number of rows to add
     */
    void addRows(int rows);

    /**
     * Adds the specified number of columns to the table.
     * @param cols number of columns to add
     */
    void addCols(int cols);

    /**
     * Removes the specified number of rows from the table.
     * @param rows number of rows to remove
     */
    void removeRows(int rows);

    /**
     * Removes the specified number of columns from the table.
     * @param cols number of columns to remove
     */
    void removeCols(int cols);

    /**
     * Returns a string representation of the table.
     * @return string representation
     */
    String toString();
}
