
/**
 * Created by: 龍ONE
 * Date Created: October 16, 2018
 * Date Edited: May 5, 2019
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

  /**
   * The main method runs Conway's Game of Life using a runnable class.
   * 
   * @param args The argument given to the main method
   * @return None
   */
  public static void main(String[] args) {
    // create a new runnable class to execute the game
    EventQueue.invokeLater(new Runnable() {

      @Override
      /**
       * This method runs the game by creating an object of the class.
       * 
       * @param None
       * @return None
       */
      public void run() {
        // create an instance of the class and make the game visible
        GameOfLife game = new GameOfLife();
        game.setVisible(true);
      }
    });
  }

}