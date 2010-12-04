import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GeomLayout extends BoardLayout
{
	public GeomLayout(int nPlayers, int boardLength)
	{
		super(nPlayers, boardLength);
		this.nPlayers = nPlayers;
		this.boardLength = boardLength;
		mRects = new Rectangle2D.Double[nPlayers];
	}

	@Override
	public void redraw(Graphics2D g, int[][] pits, int[] mancalas)
	{
		int side = 0;
		System.out.println("DEBUG: Layout redraw action");
		for (int[] ints : pits)
		{
			for (int i : ints)
				System.out.print(i + ", ");
			System.out.print("**"+mancalas[side++]);
			System.out.println();
		}

		// Draw the bounding boxes.
		for (Rectangle2D.Double r : mRects)
			g.draw(r);
		for (int i = 0; i < pitRects.length; i++)
			for (Rectangle2D.Double r : pitRects[i])
				g.draw(r);
			// Draw the stones.
		for (int r = 0; r < pits.length; r++)
		{
			// Draw into mancalas
			for (int c = 0; c < pits[r].length; c++)
				for (int stone = pits[r][c]; stone > 0; stone--)
					g.draw(rotaryStone((int)pitRects[r][c].getX(),
								(int)pitRects[r][c].getY(), (int)pitRects[r][c].getWidth(),
								(int)pitRects[r][c].getHeight(), stone));
		}

	}

	@Override
	public void setSize(int w, int h)
	{
		super.setSize(w,h);
		mRects[0] = new Rectangle2D.Double(0, 0 , width / 8, height);
		mRects[1] = new Rectangle2D.Double(width * 7 / 8, 0, width / 8, height);
			for (int r = 0; r < nPlayers; r++)
				for (int c = 0; c < boardLength; c++)
					pitRects[r][c] = new Rectangle2D.Double(width / 8 * (c + 1),
							r * height / 2, width / 8, height / 2);
	}

	/**
	 * Creates an ellipse to use as a stone.
	 * @param xorigin the x coordinate of the mancala or pit
	 * @param yorigin the y coordinate of the mancala or pit
	 * @param boxwidth the width of the mancala or pit
	 * @param boxheight the height of the mancala or pit
	 * @param n an integer to aid in seeding the random number generator
	 */
	private Ellipse2D.Double rotaryStone(int xorigin, int yorigin,
			int boxwidth, int boxheight, int n)
	{
		rand.setSeed((xorigin + 1) * (yorigin + 1) * n);
		double a = rand.nextDouble() * 2 * Math.PI;
		int stoneR = width / 32;
		int ringCenterX = xorigin + boxwidth / 2;
		int ringCenterY = yorigin + boxheight / 2;
		int stoneCenterX = (int)(ringCenterX + stoneR * Math.cos(a));
		int stoneCenterY = (int)(ringCenterY + stoneR * Math.sin(a));
		System.out.println("DEBUG: R: " + stoneR + " stoneX: " + stoneCenterX +
				" stoneY: " + stoneCenterY + " n: " + n + " a: " + a);
		return new Ellipse2D.Double(stoneCenterX - stoneR * Math.sqrt(2),
				stoneCenterY - stoneR * Math.sqrt(2), stoneR * 2, stoneR * 2);
	}

	private Rectangle2D.Double[] mRects;
	private int nPlayers;
	private int boardLength;
	private static Random rand = new Random();
}


