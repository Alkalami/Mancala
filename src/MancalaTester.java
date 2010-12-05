import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MancalaTester
{
	public static void main(String[] args)
	{

		BoardLayout[] layouts = {
				new ClassicLayout(Mancala.N_PLAYERS, Mancala.BOARD_LENGTH),
				new GeomLayout(Mancala.N_PLAYERS, Mancala.BOARD_LENGTH),
				new GeomLayout(Mancala.N_PLAYERS, Mancala.BOARD_LENGTH)};
		MancalaGUI gui = new MancalaGUI(layouts);
	}
}
