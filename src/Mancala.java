import java.util.*;

import javax.swing.event.*;

/**
 * An object modeling a game of mancala.
 * With hooks for GUI or CLI representation.
 * @author Team Edward.
 *
 */
public class Mancala
{
	/**
	 * Contructs a Mancala object
	 * @param stones number of stones(3 or 4)
	 */
	public Mancala(int stones)
	{
		pits = new int[N_PLAYERS][BOARD_LENGTH];
		mancalas = new int[N_PLAYERS];
		undoCount = new int[N_PLAYERS];
		undoPits = new int[N_PLAYERS][];
		listeners = new ArrayList<ChangeListener>();
		gameOver = false;
		for (int p = 0; p < N_PLAYERS; p++)
		{
			mancalas[p] = 0;
			for (int col = 0; col < BOARD_LENGTH; col++)
				pits[p][col] = stones;
		}
		activePlayer = 0;
		activeUndoPlayer = 0;
		for (int i = 0; i < undoCount.length; i++)
			 undoCount[i] = 0;
		setUndoBuffer();
	}
	
	/**
	 * Move stones on the board
	 * @param side a player
	 * @param pit a pit
	 */
	public void move(int side, int pit)
	{
		if (side != activePlayer)
			throw new IllegalArgumentException("Player not currently active.");
		
		if (undoCount[side] == 0)
		{
			setUndoBuffer();
			//resets other player's undo count
			undoCount[nextSide(side)] = 0;
			activeUndoPlayer = side;
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

	/**
	 * Offer an undo of what the previous player has just done.
	 */
	public void undo()
	{
		if (isGameOver())
			return;
		if (activePlayer == activeUndoPlayer && getUndoCount() == UNDO_MAX)
			return;
		if (activePlayer == activeUndoPlayer && undoCount[nextSide(activePlayer)] == 0)
			return;
		if (undoCount[activeUndoPlayer] == UNDO_MAX)
			return;
		pits = undoPits;
		mancalas = undoMancalas;
		undoCount[nextSide(activePlayer)]++;
		activePlayer = nextSide(activePlayer);
		somethingChanged();
	}

	/**
	 * Attaches a listener to the mancala to update changes on the board
	 * @param listener a listener to update changes
	 */
	public void addChangeListener(ChangeListener listener)
   {
      listeners.add(listener);
			/* Update the newly added Controller */
			somethingChanged();
   }
	
	/**
	 * Updates the listeners so the view knows that a change has been made
	 */
	public void somethingChanged()
	{
		System.out.println("DEBUG: Change Event Emitted");
		for (ChangeListener listener : listeners)
      {
         listener.stateChanged(new ChangeEvent(this));

      }
	}

	/**
	 * Get the player's pits
	 * @return a 2-dimensional array of player pits
	 */
	public int[][] getPits()
	{
		return pits.clone();
	}

	/**
	 * Get Mancala
	 * @return a single array of player Mancalas of stones
	 */
	public int[] getMancalas()
	{
		return mancalas.clone();
	}

	/**
	 * Set the status of the board back as before a player makes a selection
	 * of a pit.
	 */
	private void setUndoBuffer()
	{
		undoMancalas = mancalas.clone();
		for (int i = 0; i < N_PLAYERS; i++)
			undoPits[i] = pits[i].clone();
	}

	/**
	 * Check if the last stone that a player drops is in his own empty pit.
	 * If so, the player will take that stone and all of his opponent's
	 * adjacent stones and put them in his Mancala. Also, checks if one
	 * side of the board is empty
	 * @param side which player
	 * @param pit a pit
	 */
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
			//pits[side][pit] = 0;
			pits[nextSide(side)][BOARD_LENGTH - pit - 1] = 0;
		} 
		else
		{
			 activePlayer = nextSide(activePlayer);
		}

      // checks if either side is empty and ends the game
		int[][] checkPits = getPits();
		int empty;
		for (int i = 0; i < N_PLAYERS; i++)
		{
			 empty = 0;
			 for (int j = 0; j < BOARD_LENGTH; j++)
			 {
					if (checkPits[i][j] == 0)
						 empty++;
			 }

			 if (empty == BOARD_LENGTH)
			 {
					endGame(nextSide(i));
					break;
			 }
		}
		somethingChanged();
	}
	
	/**
	 * Determines if the game is over
	 * @return whether the game is over or not
	 */
	public boolean isGameOver()
	{
		return gameOver;
	}
	
	/**
	 * Empties the pits and moves them to the corresponding mancala
	 * afterwards it sets the winner as the active player
	 */
	private void endGame(int side)
	{
		gameOver = true;
		 
		// empties the remainder of the board
		for (int i = 0; i < BOARD_LENGTH; i++) {
			mancalas[side] += pits[side][i];
			pits[side][i] = 0;
		}
		
		// sets the winner as the active player
		if (mancalas[activePlayer] < mancalas[nextSide(activePlayer)])
			activePlayer = nextSide(activePlayer);
	}

	/**
	 * Go to next pit
	 * @param pit a pit
	 * @return the next pit
	 */
	private int nextPit(int pit)
	{
		if (++pit >= BOARD_LENGTH)
			pit = 0;
		return pit;
	}

	/**
	 * Get the next size of the players
	 * @param side a size of the players
	 * @return the next size of the current size of the players
	 */
	private int nextSide(int side)
	{
		if (++side >= N_PLAYERS)
			side = 0;
		return side;
	}
	
	/**
	 * Gets the active player
	 * @return the active player
	 */
	public int getActive()
	{
		return activePlayer;
	}

	/**
	 * Checks for the active player
	 * @return the string representation of the active player
	 */
	public String getPlayer()
	{
		return "Player "+(activePlayer+1)+"'s Move";
	}

	/**
	 * Checks how many undo moves is possible
	 * @return undo count
	 */
	public int getUndoCount()
	{
		return UNDO_MAX - undoCount[activeUndoPlayer];
	}

	private int[][] pits;
	private int[][] undoPits;
	private int[] mancalas;
	private int[] undoMancalas;
	private int activePlayer; // Change to turn checking var name;
	private int activeUndoPlayer;
	private int[] undoCount;
	private ArrayList<ChangeListener> listeners;
	private boolean gameOver;

	public static final int N_PLAYERS = 2;
	public static final int BOARD_LENGTH = 6;
	private static final int UNDO_MAX = 3;
}
