
//龍ONE

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Timer;

public class GameOfLifePanel extends JPanel {

  private int[][] individualCells = new int[140][76];
  private int[] cornerCellValues = new int[3];
  private int[] sideCellValues = new int[5];
  private int[] middleCellValues = new int[8];
  private static final double FIFTY_PERCENT = 0.5;
  private static final int REPEAT_TIMER = 100;
  private static final int CELL_SIZE = 10;
  private static final int CELL_ELEM_TWO = 2;
  private static final int CELL_ELEM_THREE = 3;
  private static final int CELL_ELEM_FOUR = 4;
  private static final int CELL_ELEM_FIVE = 5;
  private static final int CELL_ELEM_SIX = 6;
  private static final int CELL_ELEM_SEVEN = 7;
  private static final int CELL_KILL_CONDITION = 4;
  private static final int CELL_REVIVE_CONDITION = 3;

  public GameOfLifePanel() {
    setBackground(Color.orange); // background color
    Timer timer = new Timer(); // create timer object
    timer.schedule(new determineFate(), 0, REPEAT_TIMER); // run determineFate every 100 milliseconds

    // initialize individual cells
    for (int i = 0; i < individualCells.length; i++) {
      for (int j = 0; j < individualCells[i].length; j++) {
        double randomNum = Math.random();
        if (randomNum >= FIFTY_PERCENT) {
          individualCells[i][j] = 1;
        } else {
          individualCells[i][j] = 0;
        }
      }
    }
  }

  // draw cells on the panel
  private void drawGame(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // draw cells
    for (int i = 0; i < individualCells.length; i++) {
      for (int j = 0; j < individualCells[i].length; j++) {
        if (individualCells[i][j] == 1) {
          g2d.setPaint(Color.white);
          g2d.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
          g2d.setPaint(Color.black);
          g2d.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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
    for (int i = 0; i < individualCells.length; i++) {
      for (int j = 0; j < individualCells[i].length; j++) {
        int numberOfAlive = 0;
        if (i == 0 || i == individualCells.length - 1 || j == 0 || j == individualCells[0].length - 1) {
          // check border blocks
          if (i == 0 && j == 0) {
            cornerCellValues[0] = individualCells[i + 1][j];
            cornerCellValues[1] = individualCells[i][j + 1];
            cornerCellValues[CELL_ELEM_TWO] = individualCells[i + 1][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 0 && j == individualCells[0].length - 1) {
            cornerCellValues[0] = individualCells[i + 1][j];
            cornerCellValues[1] = individualCells[i][j - 1];
            cornerCellValues[CELL_ELEM_TWO] = individualCells[i + 1][j - 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == individualCells.length - 1 && j == 0) {
            cornerCellValues[0] = individualCells[i - 1][j];
            cornerCellValues[1] = individualCells[i][j + 1];
            cornerCellValues[CELL_ELEM_TWO] = individualCells[i - 1][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == individualCells.length - 1 && j == individualCells[0].length - 1) {
            cornerCellValues[0] = individualCells[i - 1][j];
            cornerCellValues[1] = individualCells[i][j - 1];
            cornerCellValues[CELL_ELEM_TWO] = individualCells[i - 1][j - 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 0) {
            sideCellValues[0] = individualCells[i][j - 1];
            sideCellValues[1] = individualCells[i + 1][j - 1];
            sideCellValues[CELL_ELEM_TWO] = individualCells[i + 1][j];
            sideCellValues[CELL_ELEM_THREE] = individualCells[i + 1][j + 1];
            sideCellValues[CELL_ELEM_FOUR] = individualCells[i][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (j == 0) {
            sideCellValues[0] = individualCells[i - 1][j];
            sideCellValues[1] = individualCells[i - 1][j + 1];
            sideCellValues[CELL_ELEM_TWO] = individualCells[i][j + 1];
            sideCellValues[CELL_ELEM_THREE] = individualCells[i + 1][j + 1];
            sideCellValues[CELL_ELEM_FOUR] = individualCells[i + 1][j];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == individualCells.length - 1) {
            sideCellValues[0] = individualCells[i][j - 1];
            sideCellValues[1] = individualCells[i - 1][j - 1];
            sideCellValues[CELL_ELEM_TWO] = individualCells[i - 1][j];
            sideCellValues[CELL_ELEM_THREE] = individualCells[i - 1][j + 1];
            sideCellValues[CELL_ELEM_FOUR] = individualCells[i][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (j == individualCells[0].length - 1) {
            sideCellValues[0] = individualCells[i - 1][j];
            sideCellValues[1] = individualCells[i - 1][j - 1];
            sideCellValues[CELL_ELEM_TWO] = individualCells[i][j - 1];
            sideCellValues[CELL_ELEM_THREE] = individualCells[i + 1][j - 1];
            sideCellValues[CELL_ELEM_FOUR] = individualCells[i + 1][j];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          }
        } else {
          // check middle blocks
          middleCellValues[0] = individualCells[i - 1][j - 1];
          middleCellValues[1] = individualCells[i][j - 1];
          middleCellValues[CELL_ELEM_TWO] = individualCells[i + 1][j - 1];
          middleCellValues[CELL_ELEM_THREE] = individualCells[i + 1][j];
          middleCellValues[CELL_ELEM_FOUR] = individualCells[i + 1][j + 1];
          middleCellValues[CELL_ELEM_FIVE] = individualCells[i][j + 1];
          middleCellValues[CELL_ELEM_SIX] = individualCells[i - 1][j + 1];
          middleCellValues[CELL_ELEM_SEVEN] = individualCells[i - 1][j];
          // count number of neighbours that are alive
          for (int counter = 0; counter < middleCellValues.length; counter++) {
            if (middleCellValues[counter] == 1) {
              numberOfAlive++;
            }
          }
        }
        // kill the cell given the conditions below
        if ((numberOfAlive <= 1 || numberOfAlive >= CELL_KILL_CONDITION) && individualCells[i][j] == 1) {
          individualCells[i][j] = 0;
        }
        // revive the cell given the conditions below
        if (numberOfAlive == CELL_REVIVE_CONDITION && individualCells[i][j] == 0) {
          individualCells[i][j] = 1;
        }
      }
    }
  }

}
