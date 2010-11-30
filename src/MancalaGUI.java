import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MancalaGUI implements MouseListener
{
	Mancala game;
	Board layout;
	
	public void gameSetup(int stones, int layoutChoice)
	{
		game = new Mancala(stones);
		switch (layoutChoice) {
			case 1: layout = new Board(BoardLayout layout1);
					  break;
			case 2: layout = new Board(BoardLayout layout2);
					  break;
			case 3: layout = new Board(BoardLayout layout3);
					  break;
			case 4: layout = new Board(BoardLayout layout4);
					  break;
		}
	}
	
	public void mouseClicked(MouseEvent e)
	{
		rects = board.getPitRectangles();
		for (int row = 0; row < Mancala.N_PLAYERS; row++)
			for (int col = 0; col < Mancala.BOARD_LENGTH; col++)
			{
				if rect[row][col].contains(e.getPoint())
					try { game.move(row,col); }
					catch (IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								"Invalid Move", JOptionPane.WARNING_MESSAGE);
					}
			}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

