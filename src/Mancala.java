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

	public void move(int player, int pit)
	{
		if (player != activePlayer)
			throw new IllegalArgumentException("Player not currently active.");
		int hand = pits[player,pit];
		pits[player,pit] = 0;
		while (hand > 0)
		{
			pit = nextPit();
			if (pit == 0)
			{
				// Check for mancala placement here.
				// Then check hand == 0, if so current player gets next turn.
				player = nextPlayer();
			}
			++pits[player,pit];
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

	private int nextPlayer(int player)
	{
		if (++player >= N_PLAYERS)
			player = 0;
		return player;
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
