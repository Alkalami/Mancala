import java.awt.geom;

public class GeomBoard extends BoardLayout
{
	public GeomBoard(int width, int height, int nPlayers, int boardLength)
	{
		super(width, height, nPlayers, boardLength);
		mRects = new Rectangle2D.Double[nPlayers]
		mRects[0] = new Rectangle2D.Double(0, 0 , width / 8, height);
		mRects[1] = new Rectangle2D.Double(width * 7 / 8, 0, width / 8, height);
		for (int r = 0; r < nPlayers, r++)
			for (int c = 0; c < boardLength; c++)
				pitRects[r][c] = new Rectangle2D.Double(width / 8 * (c + 1),
						r * height / 2, width / 8, height / 2);
	}

	public void redraw(Graphics2D g, int[][] pits, int[] mancalas)
	{
		// Draw the bounding boxes.
		for (Rectangle2D.Double r : mRects)
			g.draw(r);
		for (Rectangle2D.Double r : pitRects)
			g.draw(r);
		// Draw the stones.


	}

	private Ellipse2D.Double makeAStone(int xorigin, int yorigin)
	{
		double a = rand.nextDouble() * 2 * Math.PI;
		int stoneR = width / 32;
		int ringCenterX = xorigin + stoneR;
		int ringCenterY = yorigin + stoneR;
		int stoneCenterX = ringCenterX + stoneR * Math.cos(a);
		int stoneCenterY = ringCenterY + stoneR * Math.sin(a);
		return new Ellipse2D.Double(stoneCenterX - stoneCenterX * Math.sqrt(2),
				stoneCenterY - stoneCenterY * Math.sqrt(2), stoneR, stoneR);
	}

	private Rectangle2D.Double[] mRects;
	private static Random rand = new Random();
}


