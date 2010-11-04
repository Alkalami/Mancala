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
		for (int i = 0; i < undoCount.length; i++)
		   undoCount[i] = 0;
		undoPits = pits.clone();
		undoMancalas = mancalas.clone();
	}

	public void move(int side, int pit)
	{
		if (side != activePlayer)
			throw new IllegalArgumentException("Player not currently active.");
		
		if (undoCount[side] == 0)
		{
		   undoMancalas = mancalas.clone();
		   undoPits = pits.clone();
	      //resets other player's undo count
	      undoCount[nextSide(side)] = 0;
		}

		
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

	public void undo(int side)
	{
		if (undoCount[side] >= UNDO_MAX)
			return;
		pits = undoPits;
		mancalas = undoMancalas;
		++undoCount[side];
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
			pits[side][pit] = 0;
			pits[nextSide(side)][BOARD_LENGTH - pit - 1] = 0;
		} else {
		   activePlayer = nextSide(activePlayer);
		   //somethingChanged();
		}

      // checks if either side is empty and ends the game
		int[][] checkPits = getPits();
		int empty;
		for (int i = 0; i < N_PLAYERS; i++) {
		   empty = 0;
		   for (int j = 0; j < BOARD_LENGTH; j++) {
		      if (pits[i][j] == 0)
		         empty++;
		   }

		   if (empty == BOARD_LENGTH) {
		      endGame(nextSide(i));
		      break;
		   }
		}
	}
	
	private int endGame(int side)
	{
	   int[] winner = getMancalas();
	   int[][] checkPits = getPits();
	   
	   // empties the remainder of the board
	   for (int i = 0; i < BOARD_LENGTH; i++) {
	      winner[side] += checkPits[side][i];
	      checkPits[side][i] = 0;
	   }
	   
	   // determines the winner
	   if (winner[activePlayer] > winner[nextSide(activePlayer)])
	      return activePlayer;
	   else
	      return nextSide(activePlayer);
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
	private int[] undoCount;

	public static final int N_PLAYERS = 2;
	public static final int BOARD_LENGTH = 6;
	private static final int UNDO_MAX = 3;
}
