
package com.mazegener;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
public class Maze {
    private static final byte OPEN = 0;
    private static final byte WALL = 1;
    private static final byte ViSITED = 2;

    private int rows, columns;
    private byte[][] grid;

    //Maze constructor
    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new byte[rows][columns];
        for (int r=0; r< rows; r++) {
            for (int c=0; c < columns; c++) {
                grid[r][c] = WALL;
            }
        }
    }

    public int getRows() {
        return rows;
    }
    public int getColumns() {
        return columns;
    }

    //returns true if a wall is at the given location, false otherwise
    public boolean wallAt(int row, int column) {
        return grid[row][column] == WALL;
    }

    //returns true if this location has been visited, false otherwise
    public boolean visitedAt(int row, int column) {
        return grid[row][column] == ViSITED;
    }

    //Put a visit marker at the given location
    public void placeVisitAt(int row, int column) {
        grid[row][column] = ViSITED;
    }

    //removes the visit marker at the given location
    public void removeVisitAt(int row, int column) {
        grid[row][column] = OPEN;
    }

    //put a wall at the given location
    public void placeWallAt(int row, int column) {
        grid[row][column] = WALL;
    }

    //removes the wall at the given location
    public void removeWallAt(int row, int column) {
        grid[row][column] = OPEN;
    }

    //carve out the maze 
    public void carve() {
        int startRow = (int) (Math.random() * (rows-2))+1;
        int startColumn = (int) (Math.random() * (columns-2))+1;
        carve(startRow, startColumn);
    }
    
    //recursive method to carve out the maze
    public void carve(int r, int c) {
        ArrayList<Integer> rowOffsets = new ArrayList<Integer>(Arrays.asList(-1, 1, 0, 0));
        ArrayList<Integer> colOffsets = new ArrayList<Integer>(Arrays.asList(0, 0, -1, 1));
        if (r==0 || r==rows-1 ||c==0||c==columns-1){
            return;
        }else if (wallAt(r,c)) {
            int count=0;
            if (wallAt(r+1,c)){count++;}
            if (wallAt(r-1,c)){count++;}
            if (wallAt(r,c+1)){count++;}
            if (wallAt(r,c-1)){count++;}
            if (count>=3) {
                removeWallAt(r, c);
                int random = new Random().nextInt(4);

                if (random == 0) {
                    carve(r + rowOffsets.get(0), c + colOffsets.get(0));
                    carve(r + rowOffsets.get(1), c + colOffsets.get(1));
                    carve(r + rowOffsets.get(2), c + colOffsets.get(2));
                    carve(r + rowOffsets.get(3), c + colOffsets.get(3));
                } else if (random == 1) {
                    carve(r + rowOffsets.get(1), c + colOffsets.get(1));
                    carve(r + rowOffsets.get(2), c + colOffsets.get(2));
                    carve(r + rowOffsets.get(3), c + colOffsets.get(3));
                    carve(r + rowOffsets.get(0), c + colOffsets.get(0));
                } else if (random == 2) {
                    carve(r + rowOffsets.get(2), c + colOffsets.get(2));
                    carve(r + rowOffsets.get(3), c + colOffsets.get(3));
                    carve(r + rowOffsets.get(0), c + colOffsets.get(0));
                    carve(r + rowOffsets.get(1), c + colOffsets.get(1));
                } else if (random == 3) {
                    carve(r + rowOffsets.get(3), c + colOffsets.get(3));
                    carve(r + rowOffsets.get(0), c + colOffsets.get(0));
                    carve(r + rowOffsets.get(1), c + colOffsets.get(1));
                    carve(r + rowOffsets.get(2), c + colOffsets.get(2));
                }
            }
        }else{
            return;
        }
    }

    //Determine the longest path in the maze from the given start point
    public ArrayList<Point2D> longestPath() {
        int highest = 0 ; 
        ArrayList<Point2D> returnVariable = new ArrayList<Point2D>();
        for (int i=0; i<2;i++){
            for (int r=0; r<rows;r++){
                for(int c=0; c<columns; c++){
                    if (i==0){
                        int temp = longestPathfrom(r,c).size();
                        if (temp>highest){
                            highest=temp;
                        }
                    }else{
                        if(highest==longestPathfrom(r,c).size()){
                            returnVariable=longestPathfrom(r,c);
                        }
                    }
                }
            }
        }
        return returnVariable; 
     }

     //Determine the longest path from the given start point
     public ArrayList<Point2D> longestPathfrom(int row, int column) {
        ArrayList<Point2D> path = new ArrayList<Point2D>();
        if (visitedAt(row, column)|| wallAt(row, column)) {
            return path;
        }
        placeVisitAt(row, column);
        ArrayList<Point2D> temp1=longestPathfrom(row-1,column);
        ArrayList<Point2D> temp2=longestPathfrom(row+1,column);
        ArrayList<Point2D> temp3=longestPathfrom(row,column-1);
        ArrayList<Point2D> temp4=longestPathfrom(row,column+1);
        
        int MAxlength=Math.max(temp1.size(),Math.max(temp2.size(),Math.max(temp3.size(),temp4.size())));

        if (MAxlength==temp1.size()){temp1.add(new Point2D(row,column));removeVisitAt(row,column);return temp1;}

        if (MAxlength==temp2.size()){temp2.add(new Point2D(row,column));removeVisitAt(row,column);return temp2;}

        if (MAxlength==temp3.size()){temp3.add(new Point2D(row,column));removeVisitAt(row,column);return temp3;}

        if (MAxlength==temp4.size()){temp4.add(new Point2D(row,column));removeVisitAt(row,column);return temp4;}
    
        return path;
     }

     
}
