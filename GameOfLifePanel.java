
/**
 * Created by: 龍ONE
 * Date Created: October 16, 2018
 * Date Edited: May 4, 2019
 * Purpose: Run the backend code for Conway's Game of Life.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Timer;

public class GameOfLifePanel extends JPanel {

  private static final long serialVersionUID = 26399321;
  private int[][] individualCells = new int[140][76];
  private static final double FIFTY_PERCENT = 0.5;
  private static final int REPEAT = 100;
  private static final int CELL_SIZE = 10;
  private static final int CELL_KILL_CONDITION = 4;
  private static final int CELL_REVIVE_CONDITION = 3;

  public GameOfLifePanel() {
    setBackground(Color.ORANGE); // background color
    Timer timer = new Timer(); // create timer object
    timer.schedule(new determineFate(), 0, REPEAT); // run determineFate every 100 milliseconds

    // initialize individual cells
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        double randomNum = Math.random();
        if (randomNum >= FIFTY_PERCENT) {
          individualCells[row][col] = 1;
        } else {
          individualCells[row][col] = 0;
        }
      }
    }
  }

  // draw cells on the panel
  private void drawGame(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // draw cells
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        if (individualCells[row][col] == 1) {
          g2d.setPaint(Color.WHITE);
          g2d.fillRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
          g2d.setPaint(Color.BLACK);
          g2d.drawRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
      }
    }
  }

  // override method from JPanel
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawGame(g);
  }

  // alter cell status every 100 milliseconds
  private class determineFate extends java.util.TimerTask {
    public void run() {
      checkCellStatus();
      GameOfLifePanel.this.repaint();
    }
  }

  // checks cell status and updates the values
  private void checkCellStatus() {
    int numAlive;
    for (int row = 0; row < individualCells.length; row++) {
      for (int col = 0; col < individualCells[row].length; col++) {
        numAlive = 0;
        if (row != 0 && col != 0 && individualCells[row - 1][col - 1] == 1) {
          numAlive++;
        }
        if (col != 0 && individualCells[row][col - 1] == 1) {
          numAlive++;
        }
        if (row != individualCells.length - 1 && col != 0 && individualCells[row + 1][col - 1] == 1) {
          numAlive++;
        }
        if (row != individualCells.length - 1 && individualCells[row + 1][col] == 1) {
          numAlive++;
        }
        if (row != individualCells.length - 1 && col != individualCells[row].length - 1
            && individualCells[row + 1][col + 1] == 1) {
          numAlive++;
        }
        if (col != individualCells[row].length - 1 && individualCells[row][col + 1] == 1) {
          numAlive++;
        }
        if (row != 0 && col != individualCells[row].length - 1 && individualCells[row - 1][col + 1] == 1) {
          numAlive++;
        }
        if (row != 0 && individualCells[row - 1][col] == 1) {
          numAlive++;
        }
        // kill the cell given the conditions below
        if ((numAlive <= 1 || numAlive >= CELL_KILL_CONDITION)) {
          individualCells[row][col] = 0;
        }
        // revive the cell given the conditions below
        if (numAlive == CELL_REVIVE_CONDITION) {
          individualCells[row][col] = 1;
        }
      }
    }
  }
}
