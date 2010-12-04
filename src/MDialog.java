import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

/**
 * A dialog to allow player to set initial stone count
 * and a board layout to be used for game play
 * @author Team Edward.
 *
 */

/* TODO: CHANGE THE WAY DIALOG CHOOSES LAYOUT ?DONE?*/

public class MDialog extends JDialog
{
	private Container frame;
	private int stoneCount;
	private BoardLayout[] layouts;
	private BoardLayout layout;
	private int width = 450;
	private int height = 200;
	
	/**
	 * Creates a popup dialog to choose number of stones and the layout
	 * @param owner a frame for the dialog
	 */
	public MDialog(Frame owner, BoardLayout[] layouts)
	{
		super(owner, true);
		stoneCount = 3;
		this.layouts = layouts;
		layout = layouts[0];
		
		frame = getContentPane();
		setSize(width,height);
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
		
		JRadioButton[] layoutButtons  = new JRadioButton[layouts.length];
		ButtonGroup layoutGroup = new ButtonGroup();
		for (int i = 0; i < layouts.length; i++)
		{
			layoutButtons[i] = new JRadioButton(layouts[i].getName(), i == 0);
			layoutPanel.add(layoutButtons[i]);
			layoutGroup.add(layoutButtons[i]);
			layoutButtons[i].addActionListener(setLayout(i));
		}
		
		JButton start = new JButton("Start Game");
		
		three.addActionListener(setStoneCount(3));
		four.addActionListener(setStoneCount(4));
		start.addActionListener(new
		ActionListener()
		{
			// closes the popup dialog
			public void actionPerformed(ActionEvent event)
			{
				frame.setVisible(false);
		      dispose();
			}
		});
		
		// stone choices
		Box box1 = Box.createHorizontalBox();
		box1.add(chooseStones);
		box1.add(Box.createHorizontalStrut(135));
	   box1.add(stonePanel);
	   
	   // layout choices
	   Box box2 = Box.createHorizontalBox();
	   box2.add(chooseLayout);
	   box2.add(Box.createHorizontalStrut(0));
	   box2.add(layoutPanel);
	   
	   // start button
	   Box box3 = Box.createHorizontalBox();
	   box3.add(Box.createVerticalStrut(100));
	   box3.add(Box.createHorizontalStrut(300));
	   box3.add(start);
		
		frame.setLayout(new FlowLayout());
		frame.add(box1);
		frame.add(box2);
		frame.add(box3);
      setResizable(false);
	}
	
	/**
	 * Allows the dialog to popup and be visible
	 * @return a string representation of the popup dialog
	 */
	public String showDialog()
	{
		setVisible(true);
		return "Startup Dialog";
	}
	
	/**
	 * A listener to choose the stone count
	 * @param stoneNumber the initial stone count
	 * @return an anonymous ActionListener class
	 */
	public ActionListener setStoneCount(final int stoneNumber)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				stoneCount = stoneNumber;
			}
		};
	}
	
	/**
	 * A listener to choose the layout
	 * @param layoutNumber the layout number
	 * @return an anonymous ActionListener class
	 */
	public ActionListener setLayout(final int layoutNumber)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.out.println("DEBUG: Setting layout to #: " + layoutNumber);
				layout = layouts[layoutNumber];
			}
		};
	}
	
	/**
	 * Gets the stone count
	 * @return the stone count
	 */
	public int stoneNumber()
	{
		return stoneCount;
	}
	
	/**
	 * Gets the layout selected in the dialog
	 * @return BoardLayout to use
	 */
	public BoardLayout getSelectedLayout()
	{
		return layout;
	}
}
