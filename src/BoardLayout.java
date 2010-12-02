import java.awt.*;
import java.awt.geom.*;

public class BoardLayout
{
	public BoardLayout() {}

	public void setPitRectangles(Rectangle2D.Double[][] rects)
	{
		pitRects = rects;
	}

	public void setMancalaRectangles(Rectangle2D.Double[] rects)
	{
		mancalaRects = rects;
	}

	public void setStoneImage(Image img)
	{
		stoneImage = img;
	}

	public void setPitImage(Image img)
	{
		pitImage = img;
	}

	public void setMancalaImage(Image img)
	{
		mancalaImage = img;
	}

	public void setImages(Image pitImg, Image stoneImg, Image mancalaImg)
	{
		pitImage = pitImg;
		stoneImage = stoneImg;
		mancalaImage = mancalaImg;
	}
	
	public Image getStoneImage()
	{
		return stoneImage;
	}
	
	public Image getPitImage()
	{
		return pitImage;
	}
	
	public Image getMancalaImage()
	{
		return mancalaImage;
	}
	
	public Rectangle2D.Double[][] getPitRectangles()
	{
		return pitRects;
	}

	private Rectangle2D.Double[][] pitRects;
	private Rectangle2D.Double[] mancalaRects;
	private Image stoneImage;
	private Image pitImage;
	private Image mancalaImage;

}