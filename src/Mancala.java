public class Mancala
{
	public Mancala(int stones)
	{
		pits = new int[N_PLAYERS,BOARD_LENGTH];
		mancalas = new int[N_PLAYERS];
		for (p = 0; p < N_PLAYERS; p++)
		{
			mancalas[p] = 0;
			for (int col = 0; col < BOARD_LENGTH; col++)
				pits[col] = stones;
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
		int hand = pits[side,pit];
		pits[side,pit] = 0;
		while (hand > 0)
		{
			pit = nextPit();
			if (pit == 0)
			{
				if (side == activePlayer)
				{
					++mancalas[side];
					if (hand <= 0)
				// Then check hand == 0, if so current side gets next turn.
				side = nextPlayer();
			}
			++pits[side,pit];
			--hand;
		}
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

	private int nextSide(int side)
	{
		if (++side >= N_PLAYERS)
			side = 0;
		return side;
	}

	private int[,] pits;
	private int[,] undoPits;
	private int[] mancalas;
	private int[] undoMancalas;
	private int activePlayer; // Change to turn checking var name;
	private int undoCount;

	private static final int N_PLAYERS = 2;
	private static final int BOARD_LENGTH = 6;
}
