package com.taxidispatch;

/**
 * Represents a Taxi in the dispatch system.
 * Each taxi has a unique plate number, availability status, destination, 
 * and an estimated time to reach its destination.
 */
public class Taxi {
    private int plateNumber; // Unique identifier for the taxi
    private boolean available; // Indicates whether the taxi is available for dispatch
    private String destination; // Current destination of the taxi
    private int estimatedTimeToDest; // Estimated time (in minutes) to reach the destination

    // Getter for the taxi's plate number
    public int getPlateNumber() { return plateNumber; }

    // Getter for the taxi's availability status
    public boolean getAvailable() { return available; }

    // Getter for the taxi's current destination
    public String getDestination() { return destination; }

    // Getter for the estimated time to destination
    public int getEstimatedTimeToDest() { return estimatedTimeToDest; }

    // Setter to update the taxi's availability status
    public void setAvailable(boolean avail) { available = avail; }

    // Setter to update the taxi's destination
    public void setDestination(String d) { destination = d; }

    // Setter to update the estimated time to destination
    public void setEstimatedTimeToDest(int t) { estimatedTimeToDest = t; }

    /**
     * Decreases the estimated time to destination by 1 minute.
     * This method is typically called during periodic updates in the simulation.
     */
    public void decreaseEstimatedTimeToDest() {
        estimatedTimeToDest--;
    }

    /**
     * Constructor to initialize a Taxi object with a unique plate number.
     * The taxi is initially available, with no destination and zero estimated time.
     *
     * @param plate The unique plate number of the taxi.
     */
    public Taxi(int plate) {
        plateNumber = plate;
        available = true;
        destination = "";
        estimatedTimeToDest = 0;
    }

    /**
     * Returns a string representation of the taxi.
     * If the taxi is available, it shows the plate number and "available".
     * If the taxi is busy, it shows the plate number and the estimated time to destination.
     *
     * @return A string representation of the taxi.
     */
    public String toString() {
        if (available)
            return plateNumber + " (available)";
        return plateNumber + "(" + estimatedTimeToDest + ")";
    }

    /**
     * Compares this taxi with another taxi based on their plate numbers.
     *
     * @param x The taxi to compare with.
     * @return True if the plate numbers are the same, false otherwise.
     */
    public boolean equals(Taxi x) {
        if (this.getPlateNumber() == x.getPlateNumber()) {
            return true;
        }
        return false;
    }
}