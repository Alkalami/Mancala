import java.awt.*;
import java.awt.geom.*;

public abstract class BoardLayout(int nPlayers, int boardLength)
{
	public BoardLayout(int width, int height, int nPlayers, int boardLength)
	{
		this.width = width;
		this.height = height;
		pitRects = new Rectangle2D.Double[nPlayers][boardLength];
	}

	public abstract void redraw(Graphics2D g2, int[][] pits, int[] mancalas);

	public Rectangle2D.Double[][] getPitRects() { return pitRects; }

	protected Rectangle2D.Double[][] pitRects;
	protected int width;
	protected int height;
}

