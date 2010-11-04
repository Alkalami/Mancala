import java.util.*;

public class MancalaCLI
{
	public static void main(String[] args)
	{
		System.out.println("Mancala CLI");
		MancalaCLI cli = new MancalaCLI(3);
		cli.drawBoard();
		return;
	}

	/* Sample Board */
	/*	M2{0} 6[3] 5[3] 4[3] 3[3] 2[3] 1[3]
	 *	      1[3] 2[3] 3[3] 4[3] 5[3] 6[3] M1{0}
	 */
	public MancalaCLI(int stones)
	{
		game = new Mancala(stones);
		pits = game.getPits();
		mancalas = game.getMancalas();
	}
	
	private Mancala game;
	private int[][] pits;
	private int[] mancalas;
}

