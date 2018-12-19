
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

  public GameOfLifePanel() {
    setBackground(Color.orange); // background color
    Timer timer = new Timer(); // create timer object
    timer.schedule(new determineFate(), 0, 100); // run determineFate every 100 milliseconds

    // initialize individual cells
    for (int i = 0; i < individualCells.length; i++) {
      for (int j = 0; j < individualCells[i].length; j++) {
        double randomNum = Math.random();
        if (randomNum >= 0.5) {
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
          g2d.fillRect(i * 10, j * 10, 10, 10);
          g2d.setPaint(Color.black);
          g2d.drawRect(i * 10, j * 10, 10, 10);
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
        if (i == 0 || i == 139 || j == 0 || j == 75) {
          // check border blocks
          if (i == 0 && j == 0) {
            cornerCellValues[0] = individualCells[i + 1][j];
            cornerCellValues[1] = individualCells[i][j + 1];
            cornerCellValues[2] = individualCells[i + 1][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 0 && j == 75) {
            cornerCellValues[0] = individualCells[i + 1][j];
            cornerCellValues[1] = individualCells[i][j - 1];
            cornerCellValues[2] = individualCells[i + 1][j - 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 139 && j == 0) {
            cornerCellValues[0] = individualCells[i - 1][j];
            cornerCellValues[1] = individualCells[i][j + 1];
            cornerCellValues[2] = individualCells[i - 1][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 139 && j == 75) {
            cornerCellValues[0] = individualCells[i - 1][j];
            cornerCellValues[1] = individualCells[i][j - 1];
            cornerCellValues[2] = individualCells[i - 1][j - 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < cornerCellValues.length; counter++) {
              if (cornerCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 0) {
            sideCellValues[0] = individualCells[i][j - 1];
            sideCellValues[1] = individualCells[i + 1][j - 1];
            sideCellValues[2] = individualCells[i + 1][j];
            sideCellValues[3] = individualCells[i + 1][j + 1];
            sideCellValues[4] = individualCells[i][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (j == 0) {
            sideCellValues[0] = individualCells[i - 1][j];
            sideCellValues[1] = individualCells[i - 1][j + 1];
            sideCellValues[2] = individualCells[i][j + 1];
            sideCellValues[3] = individualCells[i + 1][j + 1];
            sideCellValues[4] = individualCells[i + 1][j];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (i == 139) {
            sideCellValues[0] = individualCells[i][j - 1];
            sideCellValues[1] = individualCells[i - 1][j - 1];
            sideCellValues[2] = individualCells[i - 1][j];
            sideCellValues[3] = individualCells[i - 1][j + 1];
            sideCellValues[4] = individualCells[i][j + 1];
            // count number of neighbours that are alive
            for (int counter = 0; counter < sideCellValues.length; counter++) {
              if (sideCellValues[counter] == 1) {
                numberOfAlive++;
              }
            }
          } else if (j == 75) {
            sideCellValues[0] = individualCells[i - 1][j];
            sideCellValues[1] = individualCells[i - 1][j - 1];
            sideCellValues[2] = individualCells[i][j - 1];
            sideCellValues[3] = individualCells[i + 1][j - 1];
            sideCellValues[4] = individualCells[i + 1][j];
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
          middleCellValues[2] = individualCells[i + 1][j - 1];
          middleCellValues[3] = individualCells[i + 1][j];
          middleCellValues[4] = individualCells[i + 1][j + 1];
          middleCellValues[5] = individualCells[i][j + 1];
          middleCellValues[6] = individualCells[i - 1][j + 1];
          middleCellValues[7] = individualCells[i - 1][j];
          // count number of neighbours that are alive
          for (int counter = 0; counter < middleCellValues.length; counter++) {
            if (middleCellValues[counter] == 1) {
              numberOfAlive++;
            }
          }
        }
        // kill the cell given the conditions below
        if ((numberOfAlive <= 1 || numberOfAlive >= 4) && individualCells[i][j] == 1) {
          individualCells[i][j] = 0;
        }
        // revive the cell given the conditions below
        if (numberOfAlive == 3 && individualCells[i][j] == 0) {
          individualCells[i][j] = 1;
        }
      }
    }
  }

}
