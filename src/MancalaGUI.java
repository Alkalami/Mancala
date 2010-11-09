import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MancalaGUI
{
	static Mancala game;
	static int stoneCount = 3;
	static JFrame frame;
	static JFrame gameFrame;
	static int width = 500;
	static int height = 300;
	
	public static void main(String[] args)
	{		
		frame = new JFrame();
		frame.setSize(width,height);
		JPanel stonePanel = new JPanel();
		JPanel layoutPanel = new JPanel();

      JLabel chooseStones = new JLabel("Initial stone count: ");
      
		JRadioButton three = new JRadioButton("Three", true);
		JRadioButton four = new JRadioButton("Four");
		ButtonGroup stoneGroup = new ButtonGroup();
		
		stonePanel.add(three);
		stoneGroup.add(three);
		stonePanel.add(four);
		stoneGroup.add(four);
		
		JLabel chooseLayout = new JLabel("Choose a layout: ");
		
		JRadioButton layout1 = new JRadioButton("Icon", true);
		JRadioButton layout2 = new JRadioButton("Icon");
		ButtonGroup layoutGroup = new ButtonGroup();
		
		layoutPanel.add(layout1);
		layoutGroup.add(layout1);
		layoutPanel.add(layout2);
		layoutGroup.add(layout2);
		
		JButton start = new JButton("Start Game");
		
		three.addActionListener(setStoneCount(3));
		four.addActionListener(setStoneCount(4));
		start.addActionListener(setGame(stoneCount));
		
		
		frame.setLayout(new FlowLayout());
		frame.add(chooseStones);
		frame.add(stonePanel);
		frame.add(chooseLayout);
		frame.add(layoutPanel);
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
            
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(450,225));
            
            JLabel player = new JLabel(game.getPlayer());
            player.setPreferredSize(new Dimension(350,10));
            
      		JButton undoButton = new JButton("Undo: "+game.getUndoCount());
      		undoButton.addActionListener(undo());
      		
      		gameFrame.setLayout(new FlowLayout());
      		gameFrame.add(panel);
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
