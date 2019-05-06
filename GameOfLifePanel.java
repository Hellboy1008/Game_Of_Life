
/**
 * Created by: 龍ONE
 * Date Created: October 16, 2018
 * Date Edited: May 5, 2019
 * Purpose: Run the backend code for Conway's Game of Life.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Timer;

/**
 * This class runs the backend to Conway's Game of Life.
 */
public class GameOfLifePanel extends JPanel {

  // millisecond between each rule check for the cells
  private static final int REPEAT = 100;
  // the size of the cell in pixels
  private static final int CELL_SIZE = 10;

  // the serial ID of the Serializable class
  private static final long serialVersionUID = 26399321;

  // 2D array holding the cells in the game
  private Cell[][] individualCells = new Cell[140][76];

  /**
   * The constructor sets the color of the background, the timer for determining
   * the fate of the cell, and the initial values of the cells.
   */
  public GameOfLifePanel() {
    // sets the background color to orange
    setBackground(Color.ORANGE);

    // initialize individual cells
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        individualCells[row][col] = new Cell();
      }
    }

    // run the determineFate class every 100 milliseconds
    Timer timer = new Timer();
    timer.schedule(new determineFate(), 0, REPEAT);

  }

  /**
   * This method checks the cell status and updates the values.
   * 
   * @param None
   * @return None
   */
  private void checkCellStatus() {

    // check the neighbor values and determine fate of cells
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        individualCells[row][col].setNeighborsAlive(0);
        // check top, top left, and top right
        checkNeighbor(row, col, row - 1, col);
        checkNeighbor(row, col, row - 1, col - 1);
        checkNeighbor(row, col, row - 1, col + 1);
        // check left and right
        checkNeighbor(row, col, row, col - 1);
        checkNeighbor(row, col, row, col + 1);
        // check bottom, bottom left, and bottom right
        checkNeighbor(row, col, row + 1, col);
        checkNeighbor(row, col, row + 1, col - 1);
        checkNeighbor(row, col, row + 1, col + 1);
        // kill or revive cell if needed
        if (individualCells[row][col].getAlive() == true) {
          individualCells[row][col].killCell();
        } else {
          individualCells[row][col].reviveCell();
        }
      }
    }

    // change the value of the cells if needed
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        if (individualCells[row][col].getFutureAlive() == 0) {
          individualCells[row][col].setAlive(false);
          individualCells[row][col].setFutureAlive(-1);
        } else if (individualCells[row][col].getFutureAlive() == 1) {
          individualCells[row][col].setAlive(true);
          individualCells[row][col].setFutureAlive(-1);
        }
      }
    }
  }

  /**
   * This method checks to see if a neighbor cell is alive.
   * 
   * @param cellRow  The row of the original cell
   * @param cellCol  The column of the original cell
   * @param checkRow The row of the cell being checked
   * @param checkCol The column of the cell being checked
   * @return None
   */
  private void checkNeighbor(int cellRow, int cellCol, int checkRow, int checkCol) {
    // avoid index out of bound errors for edge cells
    if (checkRow < 0 || checkRow >= individualCells.length || checkCol < 0 || checkCol >= individualCells[0].length) {
      return;
    }

    // check neighbor status
    if (individualCells[checkRow][checkCol].getAlive() == true) {
      individualCells[cellRow][cellCol].addNeighborsAlive(1);
    }
  }

  /**
   * This method draws the cells on the JPanel.
   * 
   * @param g The graphics class that allows drawing of components
   * @return None
   */
  private void drawGame(Graphics g) {
    // create an instance of the graphics 2D class
    Graphics2D g2d = (Graphics2D) g;

    // draw cells
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        if (individualCells[row][col].getAlive() == true) {
          // fill the rectangle
          g2d.setPaint(Color.WHITE);
          g2d.fillRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
          // draw the border of the rectangle
          g2d.setPaint(Color.BLACK);
          g2d.drawRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
      }
    }
  }

  @Override
  /**
   * This method paints the components of the cells on the window
   * 
   * @param g The graphics class that allows drawing of components
   * @return None
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawGame(g);
  }

  /**
   * This class alters the cell status every 100 milliseconds
   */
  private class determineFate extends java.util.TimerTask {
    /**
     * This method runs the cell status method to change cell status.
     * 
     * @param None
     * @return None
     */
    public void run() {
      checkCellStatus();
      GameOfLifePanel.this.repaint();
    }
  }
}
