import java.util.*;

public class Mancala
{
	public Mancala(int stones)
	{
		pits = new int[N_PLAYERS][BOARD_LENGTH];
		mancalas = new int[N_PLAYERS];
		for (int p = 0; p < N_PLAYERS; p++)
		{
			mancalas[p] = 0;
			for (int col = 0; col < BOARD_LENGTH; col++)
				pits[p][col] = stones;
		}
		activePlayer = 0;
		undoCount = 0;
		undoPits = pits.clone();
		undoMancalas = mancalas.clone();
	}

	public void move(int side, int pit)
	{
		if (side != activePlayer)
			throw new IllegalArgumentException("Player not currently active.");
		undoMancalas = mancalas.clone();
		undoPits = pits.clone();

		int hand = pits[side][pit];
		pits[side][pit] = 0;
		while (hand > 0)
		{
			pit = nextPit(pit);
			if (pit == 0)
			{
				// Check for mancala placement here.
				// Then check hand == 0, if so current side gets next turn.
				if (side == activePlayer)
				{
					++mancalas[side];
					--hand;
					if (hand <= 0)
					{
						activePlayer = nextSide(side);
						return;
					}
				}
				side = nextSide(side);
			}
			++pits[side][pit];
			--hand;
		}
		endTurn(side, pit);
	}

	public void undo()
	{
		if (undoCount >= UNDO_MAX)
			return;
		pits = undoPits;
		mancalas = undoMancalas;
		//pits[0] = new int[] { 0, 0, 0, 0, 0, 0 };
		//pits[1] = new int[] { 0, 0, 0, 0, 0, 0 };
		//mancalas[0] = 0;
		//mancalas[1] = 0;
		++undoCount;
		somethingChanged();
	}

	public void /*ChangeEvent*/ somethingChanged()
	{ return; }

	public int[][] getPits()
	{
		return pits.clone();
	}

	public int[] getMancalas()
	{
		return mancalas.clone();
	}

	private void endTurn(int side, int pit)
	{
		/* What happens at the end of a turn?
		 *	A: The case of ending in a mancala is handled in move().
		 *	B: Ending in an empty pit on your own side allows you to
		 *		take that stone and all stones in the adjacent pit.
		 *	C: Your turn ends when your hand is empty.
		 */
		if (side == activePlayer && pits[side][pit] == 1)
		{
			mancalas[side] += 1 + pits[nextSide(side)][BOARD_LENGTH - pit - 1];
			pits[nextSide(side)][BOARD_LENGTH - pit - 1] = 0;
		}
		activePlayer = nextSide(activePlayer);
		somethingChanged();
	}

	private int nextPit(int pit)
	{
		if (++pit >= BOARD_LENGTH)
			pit = 0;
		return pit;
	}
	
	private int nextSide(int side)
	{
		if (++side >= N_PLAYERS)
			side = 0;
		return side;
	}

	private int[][] pits;
	private int[][] undoPits;
	private int[] mancalas;
	private int[] undoMancalas;
	private int activePlayer; // Change to turn checking var name;
	private int undoCount;

	public static final int N_PLAYERS = 2;
	public static final int BOARD_LENGTH = 6;
	private static final int UNDO_MAX = 3;
}
