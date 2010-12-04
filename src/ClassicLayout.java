import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;

public class ClassicLayout extends BoardLayout
{
	public ClassicLayout(int nPlayers, int boardLength)
	{
		super(nPlayers, boardLength);
		this.nPlayers = nPlayers;
		this.boardLength = boardLength;
		mRects = new Rectangle2D.Double[nPlayers];
		try {
		bg = ImageIO.read(new File("resources/classic_bg.png"));
		System.out.println("DEBUG: Image load was success!");
		}
		catch (Exception e) { bg = null; }
	}

	@Override
	public void redraw(Graphics g, Board b, int[][] pits, int[] mancalas)
	{
		System.out.println("DEBUG: Drawing Image");
		

		Graphics2D g2 = (Graphics2D) g;
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
			g2.draw(r);
		for (int i = 0; i < pitRects.length; i++)
			for (Rectangle2D.Double r : pitRects[i])
				g2.draw(r);
			// Draw the stones.
		for (int r = 0; r < pits.length; r++)
		{
			for (int stone = 0; stone < mancalas[r]; stone++)
			{
				g2.draw(rotaryStone((int)mRects[r].getX(),
								(int)mRects[r].getY(), (int)mRects[r].getWidth(),
								(int)mRects[r].getHeight()));
			}
			for (int c = 0; c < pits[r].length; c++)
				for (int stone = pits[r][c]; stone > 0; stone--) {
					// reverses the stones on player 2's side
					int temp = c;
					if (r==1) {
						c = boardLength-1-c;
					}
					g2.draw(rotaryStone((int)pitRects[r][c].getX(),
								(int)pitRects[r][c].getY(), (int)pitRects[r][c].getWidth(),
								(int)pitRects[r][c].getHeight()));
					c = temp;
				}
		}

	}

	@Override
	public void setSize(int w, int h)
	{
		super.setSize(w,h);
		mRects[0] = new Rectangle2D.Double(width * 7 / 8, 0, width / 8, height);
		mRects[1] = new Rectangle2D.Double(0, 0 , width / 8, height);
		for (int c = 0; c < boardLength; c++)
			pitRects[1][c] = new Rectangle2D.Double(width / 8 * (c + 1),
					0, width / 8, height / 2);
		int s = boardLength - 1; /* Hack var to reverse direction */
		for (int c = 0; c < boardLength; c++)
		{
			pitRects[0][c] = new Rectangle2D.Double(width / 8 * (c + 1),
					height / 2, width / 8, height / 2);
			--s;
		}
	}

	@Override
	public String getName() { return "Classic Layout"; }

	/**
	 * Creates an ellipse to use as a stone.
	 * @param xorigin the x coordinate of the mancala or pit
	 * @param yorigin the y coordinate of the mancala or pit
	 * @param boxwidth the width of the mancala or pit
	 * @param boxheight the height of the mancala or pit
	 * @param n an integer to aid in seeding the random number generator
	 */
	private Ellipse2D.Double rotaryStone(int xorigin, int yorigin,
			int boxwidth, int boxheight)
	{
		double a = rand.nextDouble() * 2 * Math.PI;
		int stoneR = boxwidth / 8;
		int ringCenterX = xorigin + boxwidth / 2;
		int ringCenterY = yorigin + boxheight / 2;
		int stoneCenterX = (int)(ringCenterX + stoneR * Math.cos(a));
		int stoneCenterY = (int)(ringCenterY + stoneR * Math.sin(a));
		System.out.println("DEBUG: R: " + stoneR + " stoneX: " + stoneCenterX +
				" stoneY: " + stoneCenterY + " a: " + a);
		return new Ellipse2D.Double(stoneCenterX - stoneR * Math.sqrt(2),
				stoneCenterY - stoneR * Math.sqrt(2), stoneR * 2, stoneR * 2);
	}

	private Rectangle2D.Double[] mRects;
	private int nPlayers;
	private int boardLength;
	private Image bg;
	private Image pit;
	private Image stone;
	private Image mancala;
	private static Random rand = new Random();
}
