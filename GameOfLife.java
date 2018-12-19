
//龍ONE

import java.awt.EventQueue;
import javax.swing.JFrame;

public class GameOfLife extends JFrame { // START DATE: August 20

  public GameOfLife() {
    add(new GameOfLifePanel()); // create an instance of the panel class
    setTitle("Game of Life"); // name of game
    setSize(1401, 783); // size of frame (actual screen size: 1400 x 760)
    setLocationRelativeTo(null); // centers the game on the frame
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exits the application when the close button is selected
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() { // create new runnable class

      @Override
      public void run() {
        GameOfLife game = new GameOfLife(); // create an object of the class itself
        game.setVisible(true); // make game visible
      }
    });
  }

}