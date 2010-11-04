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
		undoPits = pits;
		undoMancalas = mancalas;
	}

	public void move(int side, int pit)
	{
		if (side != activePlayer)
			throw new IllegalArgumentException("Player not currently active.");
<<<<<<< local
		int hand = pits[side][pit];
		pits[side][pit] = 0;
=======
		int hand = pits[side,pit];
		pits[side,pit] = 0;
>>>>>>> other
		while (hand > 0)
		{
			pit = nextPit(pit);
			if (pit == 0)
			{
<<<<<<< local
				// Check for mancala placement here.
				// Then check hand == 0, if so current side gets next turn.
				side = nextPlayer(side);
=======
				if (side == activePlayer)
				{
					++mancalas[side];
					if (hand <= 0)
					{
						activePlayer = side;
						return;
					}
				}
				side = nextSide();
>>>>>>> other
			}
<<<<<<< local
			++pits[side][pit];
=======
			++pits[side,pit];
>>>>>>> other
			--hand;
		}
		endTurn(side, pit)
	}

	public void undo()
	{
		if (undoCount >= UNDO_MAX)
			return;
		pits = undoPits;
		mancalas = undoMancalas;
		++undoCount;
	}

	private int nextPit(int pit)
	{
		if (++pit >= BOARD_LENGTH)
			pit = 0;
		return pit;
	}

<<<<<<< local
	private int nextPlayer(int side)
=======
	private int nextSide(int side)
>>>>>>> other
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

	private static final int N_PLAYERS = 2;
	private static final int BOARD_LENGTH = 6;
	private static final int UNDO_MAX = 3;
}
