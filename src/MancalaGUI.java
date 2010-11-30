import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MancalaGUI implements MouseListener
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
		
		game = new Mancala(popup.stoneNumber());
      gameFrame = new JFrame("Team Edward Mancala Game");
      gameFrame.setSize(width,height);
      
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(450,225));
      
      player = new JLabel(game.getPlayer());
      player.setPreferredSize(new Dimension(300,10));//350,10));
      
      JButton test = new JButton("Test");
      test.addActionListener(move());
      
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
	
	public static ActionListener move()
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

	public void mouseClicked(MouseEvent e)
	{
		rects = board.getPitRectangles();
		for (int row = 0; row < Mancala.N_PLAYERS; row++)
			for (int col = 0; col < Mancala.BOARD_LENGTH; col++)
			{
				if rect[row][col].contains(e.getPoint())
					try { game.move(row,col); }
					catch (IllegalArgumentException ex)
					{
						JOptionPane.showMessageDialog(this, ex.getMessage(),
								"Invalid Move", JOptionPane.WARNING_MESSAGE);
					}
			}
	}
}

