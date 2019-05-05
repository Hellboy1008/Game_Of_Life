
/**
 * Created by: 龍ONE
 * Date Created: October 16, 2018
 * Date Edited: May 4, 2019
 * Purpose: Run Conway's Game of Life on Java graphics.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The class runs the main method that runs the JPanel GameOfLifePanel.java,
 * thus running Conway's Game of Life.
 */
public class GameOfLife extends JFrame {

  // the height of the window displaying the game
  private static final int FRAME_HEIGHT = 1401;
  // the width of the window displaying the game
  private static final int FRAME_WIDTH = 783;

  // the serial ID of the Serializable class
  private static final long serialVersionUID = 53525026;

  // the title of the game
  private static final String TITLE = "Game of Life";

  /**
   * The constructor adds the panel to the frame and sets the title, size,
   * location, and closing operation for the JFrame.
   * 
   * @param None
   * @return None
   */
  public GameOfLife() {
    // create an instance of the panel class
    add(new GameOfLifePanel());
    // name the game
    setTitle(TITLE);
    // set the size of the frame
    setSize(FRAME_HEIGHT, FRAME_WIDTH);
    // center the game on the frame
    setLocationRelativeTo(null);
    // sets the closing operation, allowing user to exit the game when the close
    // button is selected
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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