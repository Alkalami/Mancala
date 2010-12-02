import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MancalaGUI implements MouseListener
{
	Mancala game;
	Board board;
	int width = 500;
	int height = 300; 
	
	public MancalaGUI(int stones, BoardLayout layoutChoice)
	{
		game = new Mancala(stones);
		board = new Board(layoutChoice);
		
		ChangeListener listener = new
		ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				//repaint board
			}
		};
      game.addChangeListener(listener);
      
      JFrame frame = new JFrame();
      frame.setSize(width,height);
      frame.setLayout(new FlowLayout());
      frame.add(board);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
	}
	
	@Override
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
						JOptionPane.showMessageDialog(null, ex.getMessage(),
								"Invalid Move", JOptionPane.WARNING_MESSAGE);
					}
			}
	}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}
}

