/**
 * Created by: 龍ONE
 * Date Created: May 5, 2019
 * Date Edited: May 5, 2019
 * Purpose: Holds each cell in Conway's Game of Life.
　*/

/**
 * This class contains the values for each individual cell in the game
 */
public class Cell {

    // the future value of cell status after checking neighbors (-1 for null, 0 for
    // dead, 1 for alive)
    private int futureAlive = -1;
    // the number of neighbors alive
    private int neighborsAlive = 0;
    // the number of neighboring cells required for the cell to die
    private static final int CELL_KILL_CONDITION = 4;
    // the number of neighboring cells required for the cell to revive
    private static final int CELL_REVIVE_CONDITION = 3;

    // used to determine randomness
    private static final double FIFTY_PERCENT = 0.5;

    // the value of whether the cell is alive
    private boolean alive;

    /**
     * The constructor initializes the values of whether the cell is alive.
     */
    public Cell() {
        // initialize the individual cells with a random value
        if (Math.random() > FIFTY_PERCENT) {
            alive = true;
        } else {
            alive = false;
        }
    }

    /**
     * This method adds the value given to the method to the value of
     * neighborsAlive.
     * 
     * @param num The value to be added
     * @return None
     */
    public void addNeighborsAlive(int num) {
        neighborsAlive += num;
    }

    /**
     * This method returns whether the cell is alive.
     * 
     * @param None
     * @return True if the cell is alive, false if not
     */
    public boolean getAlive() {
        return alive;
    }

    /**
     * This method returns whether the future value of cell is alive, dead, or not
     * set.
     * 
     * @param None
     * @return -1 if futureAlive is not set, 0 if dead, 1 if alive
     */
    public int getFutureAlive() {
        return futureAlive;
    }

    /**
     * This method returns the number of neighboring cells alive.
     * 
     * @param None
     * @return The number of neighbors alive
     */
    public int getNeighborsAlive() {
        return neighborsAlive;
    }

    /**
     * This method kills the cell if it the cell is alive and has more than three
     * neighbors or less than two neighbors alive.
     * 
     * @param None
     * @return None
     */
    public void killCell() {
        if (alive == true && neighborsAlive >= CELL_KILL_CONDITION) {
            futureAlive = 0;
        } else if (alive == true && neighborsAlive <= 1) {
            futureAlive = 0;
        }
    }

    /**
     * This method revives the cell if it is dead and has exactly three neighbors
     * alive.
     * 
     * @param None
     * @return None
     */
    public void reviveCell() {
        if (alive == false && neighborsAlive == CELL_REVIVE_CONDITION) {
            futureAlive = 1;
        }
    }

    /**
     * This method changes the cell status to the parameter given.
     * 
     * @param alive Whether the cell is alive
     * @return None
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * This method changes the future cell status to the parameter given.
     * 
     * @param futureAlive The new value of the futureAlive
     * @return None
     */
    public void setFutureAlive(int futureAlive) {
        this.futureAlive = futureAlive;
    }

    /**
     * This method changes the number of neighbors alive to the parameter given.
     * 
     * @param neighborsAlive Number of neighbors alive
     * @return None
     */
    public void setNeighborsAlive(int neighborsAlive) {
        this.neighborsAlive = neighborsAlive;
    }

}