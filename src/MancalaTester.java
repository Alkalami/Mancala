import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MancalaTester
{
	static Mancala game;
	static JFrame gameFrame;
	static int width = 500;
	static int height = 300;
	static JLabel player;
	static JButton undoButton;
	static int move = 0;
	
	public static void main(String[] args)
	{	
		MDialog popup = new MDialog();
		while (!popup.start()) {};
		
		MancalaGUI gui = new MancalaGUI();
		
		game = new Mancala(popup.stoneNumber());
      gameFrame = new JFrame("Team Edward Mancala Game");
      gameFrame.setSize(width,height);
      
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(450,225));
      
      player = new JLabel(game.getPlayer());
      player.setPreferredSize(new Dimension(300,10));//350,10));
      
      JButton test = new JButton("Test");
      test.addActionListener(fakeMove());
      
		undoButton = new JButton("Undo: "+game.getUndoCount());
		undoButton.addActionListener(undo());
		
		gameFrame.setLayout(new FlowLayout());
		gameFrame.add(panel);
		gameFrame.add(player);
		gameFrame.add(test);
      gameFrame.add(undoButton);
      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      gameFrame.setVisible(true);
      gameFrame.setResizable(false);
	}
	
	public static ActionListener undo()
   {
      return new
      ActionListener()
      {
			public void actionPerformed(ActionEvent event)
         {
            game.undo();
            player.setText(game.getPlayer());
            undoButton.setText("Undo: "+game.getUndoCount());
         }
      };
   }
	
	public static ActionListener fakeMove()
   {
      return new
      ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            game.move(game.getActive(),move++);
            player.setText(game.getPlayer());
            undoButton.setText("Undo: "+game.getUndoCount());
         }
      };
   }
}
