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

	public void setBoardSize(int w, int h)
	{
		setPreferredSize(new Dimension(w,h));
		layout.setSize(w,h);
	}

	public void paintComponent(Graphics g)
	{
		layout.redraw((Graphics2D) g, pits, mancalas);
	}

	public void setData(int[][] pits, int[] mancalas)
	{
		this.pits = pits;
		this.mancalas = mancalas;
	}

	private int[][] pits;
	private int[] mancalas;
	private BoardLayout layout;
}
