package com.datastructures;

/**
 * A simple stopwatch utility for measuring elapsed time in seconds.
 */
public class StopWatch {
    protected long start, stop; // Variables to store start and stop times in nanoseconds
        
    /**
     * Starts the stopwatch by recording the current time.
     */
    public void start() {
        start = System.nanoTime();
    }
        
    /**
     * Stops the stopwatch by recording the current time.
     */
    public void stop() {
        stop = System.nanoTime();
    }
        
    /**
     * Returns the elapsed time in seconds between start and stop.
     * @return elapsed time in seconds
     */
    public double elapsedSeconds() {
        return (stop-start)*1e-9;
    }
    
    
}
