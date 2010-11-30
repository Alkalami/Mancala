import java.awt.*;

public class Board extends JComponent
{
	public Board(BoardLayout layout)
	{
		this.layout = layout;
	}

  public void refresh(int[][] pits, int[] manacalas)
  {
    for (int r = 0; r < pits.length(); r++)
    {
      drawMancala(mancalas[r]);
      for (int c = 0; c < pits[r].length(); c++)
      {
        drawPit(pits[r][c]);
      }
    }
  }

	private BoardLayout layout;
}
