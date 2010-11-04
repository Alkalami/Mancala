import java.util.*;

public class MancalaCLI
{
	public static void main(String[] args)
	{
		System.out.println("Mancala CLI");
		MancalaCLI cli = new MancalaCLI(3);
		return;
	}

	public MancalaCLI(int stones)
	{
		game = new Mancala(stones);
		refresh();
		kimDraw();
		game.move(0,3);
		refresh();
		kimDraw();
		game.move(1,4);
		refresh();
		kimDraw();
		game.undo(1);
		refresh();
		kimDraw();
	}

	private void kimDraw()
	{
		System.out.print("  ");
		for (int i = Mancala.BOARD_LENGTH - 1; i >= 0; i--)
			System.out.print(pits[0][i] + " ");
		System.out.println();
		System.out.println(mancalas[0] + " a b c d e f " + mancalas[1]);
		System.out.print("  ");
		for (int i = 0; i < Mancala.BOARD_LENGTH; i++)
			System.out.print(pits[1][i] + " ");
		System.out.println("\n");
			
	}
	
	private void refresh()
	{
		pits = game.getPits();
		mancalas = game.getMancalas();
	}

	private Mancala game;
	private int[][] pits;
	private int[] mancalas;
}

