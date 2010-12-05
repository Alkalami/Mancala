import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The class containing the main method as required for the project.
 * @author Team Edward
 */
public class MancalaTest
{
	public static void main(String[] args)
	{

		BoardLayout[] layouts = {
			new ClassicLayout(Mancala.N_PLAYERS, Mancala.BOARD_LENGTH),
	 		new OMGPoniesLayout(Mancala.N_PLAYERS, Mancala.BOARD_LENGTH)
		};

		MancalaGUI gui = new MancalaGUI(layouts);
	}
}
