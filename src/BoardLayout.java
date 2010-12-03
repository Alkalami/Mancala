import java.awt.*;
import java.awt.geom.*;

public abstract class BoardLayout
{
	public BoardLayout(int nPlayers, int boardLength)
	{
		pitRects = new Rectangle2D.Double[nPlayers][boardLength];
	}

	public abstract void redraw(Graphics2D g2, int[][] pits, int[] mancalas);

	public Rectangle2D.Double[][] getPitRects() { return pitRects; }

	public void setSize(int w, int h)
	{
		width = w;
		height = h;
	}

	protected Rectangle2D.Double[][] pitRects;
	protected int width;
	protected int height;
}

