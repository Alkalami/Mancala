import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class Board extends JComponent
{
	public Board(BoardLayout layout)
	{
		this.layout = layout;
	}

	public Rectangle2D.Double[][] getPitRectangles()
	{
		return layout.getPitRects();
	}

	public void paintComponent(Graphics g)
	{
		/* Do we need this I sort of think we don't. */
		// super.paintComponent(g);
		layout.redraw((Graphics2D) g, pits, mancalas);
	}

	private int[][] pits;
	private int[] mancalas;
	private BoardLayout layout;
}
