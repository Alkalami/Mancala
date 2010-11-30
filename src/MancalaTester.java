import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MancalaTester
{
	public static void main(String[] args)
	{
		MDialog popup = new MDialog(null);
		String userChoice = popup.showDialog();
		
		MancalaGUI gui = new MancalaGUI(popup.stoneNumber(), popup.layoutNumber());
	}
}
