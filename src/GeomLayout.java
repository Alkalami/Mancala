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

	public void redraw(Graphics2D g, int[][] pits, int[] mancalas)
	{
		System.out.println("DEBUG: Layout redraw action");
		for (int[] ints : pits)
			for (int i : ints)
				System.out.print(i + ", ");
		System.out.println();

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
					g.draw(new Ellipse2D.Double((int)pitRects[r][c].getX() + width / 32 * stone,(int)pitRects[r][c].getY(), width / 32, width / 32 ));
					//g.draw(makeAStone((int)pitRects[r][c].getX(),(int)pitRects[r][c].getY()));
		}

	}

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

	private Ellipse2D.Double makeAStone(int xorigin, int yorigin)
	{
		double a = rand.nextDouble() * 2 * Math.PI;
		int stoneR = width / 32;
		int ringCenterX = xorigin + stoneR;
		int ringCenterY = yorigin + stoneR;
		int stoneCenterX = (int)(ringCenterX + stoneR * Math.cos(a));
		int stoneCenterY = (int)(ringCenterY + stoneR * Math.sin(a));
		return new Ellipse2D.Double(stoneCenterX - stoneCenterX * Math.sqrt(2),
				stoneCenterY - stoneCenterY * Math.sqrt(2), stoneR, stoneR);
	}

	private Rectangle2D.Double[] mRects;
	private int nPlayers;
	private int boardLength;
	private static Random rand = new Random();
}


