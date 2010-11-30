import java.awt.*;

public class Board extends JComponent implements MouseListener
{
	public Board(BoardLayout layout)
	{
		this.layout = layout;
	}

	public void refresh(int[][] pits, int[] manacalas);

	public mouseClicked(MouseEvent e)
	{
		for (int i = 0; i < Mancala.N_PLAYERS;
	}
}
