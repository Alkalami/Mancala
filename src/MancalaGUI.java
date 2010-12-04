import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The GUI for the mancala game which handles mouse clicks
 * and sets the board and game to begin playing
 * @author Team Edward.
 *
 */
public class MancalaGUI extends JFrame implements MouseListener, ChangeListener,
			 ActionListener
{
	private Mancala game;
	private Board board;
	private JLabel player;
	private JButton undoButton;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 400;
	private static final int BOARD_WIDTH = 450;
	private static final int BOARD_HEIGHT = 325;
	
	/**
	 * The view and frame that shows the mancala game visually
	 */
	public MancalaGUI(BoardLayout[] layouts)
	{
		makeDialog(layouts);
	}

	/**
	 * Initalize the data model and start the mancala game
	 * @param stones the initial stone count
	 * @param layout the layout for the board to use
	 */
	public void start(int stones, BoardLayout layout)
	{
		game = new Mancala(stones);
		board = new Board(layout);
		game.addChangeListener(this);
		
		setSize(WIDTH,HEIGHT);
		board.setBoardSize(BOARD_WIDTH,BOARD_HEIGHT);
		
		// Displays the active player
      player = new JLabel(game.getPlayer());
      player.setPreferredSize(new Dimension(550,10));
      
      // Displays the undo count 
		undoButton = new JButton("Undo: "+game.getUndoCount());
		undoButton.addActionListener(this);

		// Add Board's mouseListener
		board.addMouseListener(this);
		
		setLayout(new FlowLayout());
		add(board);
		add(player);
      add(undoButton);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
	}
	
	/**
	 * Checks to see if the player made a valid move
	 * if yes then the board will be updated
	 * if no then an warning message will be displayed
	 */
	public void mousePressed(MouseEvent e)
	{
		if (game.isGameOver())
			return;
		Rectangle2D.Double[][] rects = board.getPitRectangles().clone();
		
		//reverse player 2 pits
		Rectangle2D.Double[] temp = new Rectangle2D.Double[rects[1].length];
		for (int i = 0; i<Mancala.BOARD_LENGTH; i++)
			temp[Mancala.BOARD_LENGTH-1-i] = rects[1][i];
		rects[1] = temp;

		for (int row = 0; row < Mancala.N_PLAYERS; row++)
		{
			for (int col = 0; col < Mancala.BOARD_LENGTH; col++)
			{
				if (rects[row][col].contains(e.getPoint()))
					try {
						game.move(row,col);
						player.setText(game.getPlayer());
						undoButton.setText("Undo: "+game.getUndoCount());
			      }
					catch (IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								"Invalid Move", JOptionPane.WARNING_MESSAGE);
					}
			}
		}
	}

	/**
	 * When a change has been made in the data, then the board
	 * will be updated and repainted. If the game has ended
	 * a dialog will state the winner and freeze any moves
	 */
	public void stateChanged(ChangeEvent event)
	{
		System.out.println("DEBUG: Change Event Caught");
		board.setData(game.getPits(), game.getMancalas());
		board.repaint();
		if (game.isGameOver())
			JOptionPane.showMessageDialog(this, "Player "+(game.getActive()+1) +
					" is the winner!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	}

	private void makeDialog(BoardLayout[] layouts)
	{
		MDialog popup = new MDialog(this, layouts);
		popup.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		popup.showDialog();
		start(popup.stoneNumber(), popup.getSelectedLayout());
	}

	/**
	 * A listener to undo the last move made 
	 */
	public void actionPerformed(ActionEvent event)
	{
		game.undo();
		player.setText(game.getPlayer());
		undoButton.setText("Undo: "+game.getUndoCount());
 	}

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseClicked(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }

}
