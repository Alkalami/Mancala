import java.awt.*;

public interface Board
{
	public void repaint() {}
	public void getData() {}

	private Image stoneImage;
	private Image pitImage;
	private Image mancalaImage;
	private Image boardImage;
	private Rectangle[][] pitBoxes;
}
