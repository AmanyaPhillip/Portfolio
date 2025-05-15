package com.taxidispatch;

import java.util.*;

/**
 * The DispatchCenter class manages taxis and their assignments to client requests.
 * It tracks taxis by area, calculates travel times, and updates dispatch statistics.
 */
public class DispatchCenter {
    private HashMap<Integer, Taxi> taxis; // Maps taxi plate numbers to Taxi objects
    private HashMap<String, ArrayList<Taxi>> areas; // Maps area names to lists of taxis in those areas
    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"}; // Predefined area names

    private int[][] stats; // 2D array to track dispatch statistics between areas

    /**
     * Constructor to initialize the DispatchCenter.
     * It creates a set of random taxis and assigns them to random areas.
     */
    public DispatchCenter() {
        stats = new int[AREA_NAMES.length][AREA_NAMES.length]; // Initialize statistics array
        taxis = new HashMap<>();
        areas = new HashMap<>();

        // Create 50 random taxis and assign them to random areas
        for (int i = 0; i < 50; i++) {
            Taxi temp = new Taxi((int) (Math.random() * ((1000 - 100) + 1) + 100)); // Random plate number
            addTaxi(temp, AREA_NAMES[(int) (Math.random() * 6)]); // Assign to a random area
        }
    }

    /**
     * Returns the dispatch statistics.
     *
     * @return A 2D array of statistics.
     */
    public int[][] getStats() {
        return stats;
    }

    /**
     * Updates the dispatch statistics for a given pickup and drop-off area.
     *
     * @param pickup  The pickup area.
     * @param dropOff The drop-off area.
     */
    public void updateStats(String pickup, String dropOff) {
        for (int i = 0; i < AREA_NAMES.length; i++) {
            for (int j = 0; j < AREA_NAMES.length; j++) {
                if (AREA_NAMES[i].equals(pickup) && AREA_NAMES[j].equals(dropOff)) {
                    stats[i][j]++;
                }
            }
        }
    }

    /**
     * Computes the travel time between two areas.
     *
     * @param pickup  The pickup area.
     * @param dropOff The drop-off area.
     * @return The travel time in minutes.
     */
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
        int travel[][] = {
            {10, 40, 20, 20, 20, 20},
            {40, 10, 40, 40, 20, 60},
            {20, 40, 10, 40, 20, 20},
            {20, 40, 40, 10, 20, 20},
            {20, 20, 20, 20, 10, 40},
            {20, 60, 20, 20, 40, 10}
        };

        // Find the travel time between the specified areas
        for (int i = 0; i < AREA_NAMES.length; i++) {
            for (int j = 0; j < AREA_NAMES.length; j++) {
                if (AREA_NAMES[i].equals(pickup) && AREA_NAMES[j].equals(dropOff)) {
                    return travel[i][j];
                }
            }
        }
        return 0; // Default travel time if areas are not found
    }

    /**
     * Adds a taxi to the dispatch system and assigns it to a specific area.
     *
     * @param aTaxi The taxi to add.
     * @param area  The area to assign the taxi to.
     */
    public void addTaxi(Taxi aTaxi, String area) {
        if (!areas.containsKey(area)) {
            areas.put(area, new ArrayList<>());
        }
        taxis.put(aTaxi.getPlateNumber(), aTaxi);
        areas.get(area).add(aTaxi);
    }

    /**
     * Returns a list of all available taxis in a specific area.
     *
     * @param s The area name.
     * @return A list of available taxis in the area.
     */
    private ArrayList<Taxi> availableTaxisInArea(String s) {
        ArrayList<Taxi> result = new ArrayList<>();
        for (Taxi x : areas.get(s)) {
            if (x.getAvailable()) {
                result.add(x);
            }
        }
        return result;
    }

    /**
     * Returns a list of all busy taxis across all areas.
     *
     * @return A list of busy taxis.
     */
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<>();
        for (String s : areas.keySet()) {
            for (Taxi p : areas.get(s)) {
                if (!p.getAvailable()) {
                    result.add(p);
                }
            }
        }
        return result;
    }

    /**
     * Finds and dispatches a taxi to satisfy a client request.
     * If no taxis are available in the pickup area, it searches other areas.
     *
     * @param request The client request containing pickup and drop-off locations.
     * @return The dispatched taxi, or null if no taxi is available.
     */
    public Taxi sendTaxiForRequest(ClientRequest request) {
        String pickup = request.getPickupLocation();
        String dropoff = request.getDropoffLocation();
        Taxi send = null;

        // Check for available taxis in the pickup area
        if (!availableTaxisInArea(pickup).isEmpty()) {
            send = availableTaxisInArea(pickup).get(0);
            for (Taxi l : areas.get(pickup)) {
                if (l.equals(send)) {
                    areas.get(dropoff).add(l);
                    l.setEstimatedTimeToDest(computeTravelTimeFrom(pickup, dropoff));
                    l.setDestination(dropoff);
                    areas.get(pickup).remove(l);
                    l.setAvailable(false);
                    updateStats(pickup, dropoff);
                    return send;
                }
            }
        } else {
            // Search for available taxis in other areas
            for (String k : AREA_NAMES) {
                if (!availableTaxisInArea(k).isEmpty()) {
                    send = availableTaxisInArea(k).get(0);
                    for (Taxi l : areas.get(k)) {
                        if (l.equals(send)) {
                            areas.get(dropoff).add(l);
                            areas.get(k).remove(l);
                            l.setAvailable(false);
                            l.setEstimatedTimeToDest(computeTravelTimeFrom(k, pickup) + computeTravelTimeFrom(pickup, dropoff));
                            l.setDestination(dropoff);
                            updateStats(pickup, dropoff);
                            return send;
                        }
                    }
                }
            }
        }
        return null; // No taxi available
    }

    /**
     * Returns the mapping of areas to their respective taxis.
     *
     * @return A HashMap of areas and their taxis.
     */
    public HashMap<String, ArrayList<Taxi>> getAreas() {
        return areas;
    }
}