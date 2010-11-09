import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MancalaGUI
{
	static Mancala game;
	static int stoneCount;
	static JFrame frame;
	static JFrame gameFrame;
	static int width = 500;
	static int height = 300;
	
	public static void main(String[] args)
	{		
		frame = new JFrame();
		frame.setSize(width,height);

      JLabel chooseStones = new JLabel("Choose the initial stone count");
      
		JButton three = new JButton("Three");
		JButton four = new JButton("Four");
		JButton start = new JButton("Start Game");
		
		three.addActionListener(setStoneCount(3));
		four.addActionListener(setStoneCount(4));
		start.addActionListener(setGame(stoneCount));
		
		frame.setLayout(new FlowLayout());
		frame.add(chooseStones);
		frame.add(three);
		frame.add(four);
		frame.add(start);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //frame.pack();
      frame.setVisible(true);
	}
	
	public static ActionListener setStoneCount(final int number)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				stoneCount = number;
			}
		};
	}
	
	public static ActionListener setGame(final int stones)
   {
      return new
      ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            game = new Mancala(stones);
            frame.setVisible(false);
            gameFrame = new JFrame();
            gameFrame.setSize(width,height);
            
            JLabel player = new JLabel(game.getPlayer());
            
      		JButton undoButton = new JButton("Undo: "+game.getUndoCount());
      		undoButton.addActionListener(undo());
      		
      		gameFrame.setLayout(new FlowLayout());
      		gameFrame.add(player);
            gameFrame.add(undoButton);
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.pack();
            gameFrame.setVisible(true);
         }
      };
   }
	
	public static ActionListener undo()
   {
      return new
      ActionListener()
      {
         public void actionPerformed(ActionEvent event)
         {
            game.undo();
         }
      };
   }
	
}
