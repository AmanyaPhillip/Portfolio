package com.taxidispatch;

/**
 * Represents a client request in the taxi dispatch system.
 * Each request contains a pickup location and a drop-off location.
 */
public class ClientRequest {
    private String pickupLocation; // The location where the client wants to be picked up
    private String dropOffLocation; // The location where the client wants to be dropped off

    /**
     * Getter for the pickup location.
     *
     * @return The pickup location as a string.
     */
    public String getPickupLocation() { 
        return pickupLocation; 
    }

    /**
     * Getter for the drop-off location.
     *
     * @return The drop-off location as a string.
     */
    public String getDropoffLocation() { 
        return dropOffLocation; 
    }

    /**
     * Constructor to initialize a ClientRequest object.
     *
     * @param p The pickup location.
     * @param d The drop-off location.
     */
    public ClientRequest(String p, String d) {
        pickupLocation = p;
        dropOffLocation = d;
    }

    /**
     * Returns a string representation of the client request.
     * The format is "pickupLocation ==> dropOffLocation".
     *
     * @return A string representation of the client request.
     */
    public String toString() {
        return pickupLocation + " ==> " + dropOffLocation;
    }
}