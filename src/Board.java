import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Board extends JComponent
{
	public Board(BoardLayout layout)
	{
		this.layout = layout;
	}

	public void refresh(int[][] pits, int[] mancalas)
	{
		for (int r = 0; r < pits.length; r++)
		{
			drawMancala(mancalas[r]);
			for (int c = 0; c < pits[r].length; c++)
			{
				drawPit(pits[r][c]);
			}
		}
	}

	private void drawPit(int i)
	{
		
	}

	private void drawMancala(int i)
	{
		
	}
	
	public Rectangle2D.Double[][] getPitRectangles()
	{
		return layout.getPitRectangles();
	}

	private BoardLayout layout;
}
