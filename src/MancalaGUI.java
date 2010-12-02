import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaGUI extends JFrame implements MouseListener, ChangeListener
{
	private Mancala game;
	private Board board;
	private JLabel player;
	private JButton undoButton;
	private int width = 500;
	private int height = 300;
	int move = 0; // delete when clicking is implemented
	
	/**
	 * The view and frame that shows the mancala game visually
	 * @param stones the initial stone count
	 * @param layout the layout for the board to use
	 */
	public MancalaGUI(int stones, BoardLayout layout)
	{
		game = new Mancala(stones);
		board = new Board(layout);
		game.addChangeListener(this);
		
		setSize(width,height);
		board.setPreferredSize(new Dimension(450,225));
		
		// Displays the active player
      player = new JLabel(game.getPlayer());
      player.setPreferredSize(new Dimension(300,10));//350,10));
      
      JButton test = new JButton("Test");
      test.addActionListener(move());
      
      // Displays the undo count 
		undoButton = new JButton("Undo: "+game.getUndoCount());
		undoButton.addActionListener(undo());
		
		setLayout(new FlowLayout());
		add(board);
		add(player);
		add(test);
      add(undoButton);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
	}
	
	/**
	 * A listener to undo the last move made 
	 * @return an anonymous ActionListener class
	 */
	public ActionListener undo()
   {
      return new
      ActionListener()
      {
			public void actionPerformed(ActionEvent event)
         {
            game.undo();
            player.setText(game.getPlayer());
            undoButton.setText("Undo: "+game.getUndoCount());
         }
      };
   }
	
	// REMOVE WHEN CLICKING IS ENABLED
	public ActionListener move()
   {
      return new
      ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            game.move(game.getActive(),move++);
            player.setText(game.getPlayer());
            undoButton.setText("Undo: "+game.getUndoCount());
         }
      };
   }
	
	/**
	 * Checks to see if the player made a valid move
	 * if yes then the board will be updated
	 * if no then an warning message will be displayed
	 */
	public void mouseClicked(MouseEvent e)
	{
		Rectangle2D.Double[][] rects = board.getPitRectangles();
		for (int row = 0; row < Mancala.N_PLAYERS; row++)
			for (int col = 0; col < Mancala.BOARD_LENGTH; col++)
			{
				if (rects[row][col].contains(e.getPoint()))
					try { game.move(row,col); }
					catch (IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								"Invalid Move", JOptionPane.WARNING_MESSAGE);
					}
			}
	}

	/**
	 * When a change has been made in the data, then the board
	 * will be updated and repainted
	 */
	public void stateChanged(ChangeEvent event)
	{
		//repaint board
		if (game.isGameOver()) {
			// popup dialog and state winner
		}
	}
	
	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mousePressed(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }

}
