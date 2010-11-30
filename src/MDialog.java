import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

public class MDialog extends JDialog
{
	private static Container frame;
	private static int stoneCount = 3;
	private static BoardLayout[] layouts;
	private static BoardLayout layout;
	private int width = 500;
	private int height = 300;
	
	public MDialog(Frame owner)
	{
		super(owner, true);
		layouts = {new BoardLayout(), new BoardLayout(), new BoardLayout(), new BoardLayout()};
		
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
		
		JRadioButton layout1 = new JRadioButton("Layout 1", true);
		JRadioButton layout2 = new JRadioButton("Layout 2");
		JRadioButton layout3 = new JRadioButton("Layout 3");
		JRadioButton layout4 = new JRadioButton("Layout 4");
		ButtonGroup layoutGroup = new ButtonGroup();
	 
		layoutPanel.add(layout1);
		layoutGroup.add(layout1);
		layoutPanel.add(layout2);
		layoutGroup.add(layout2);
		layoutPanel.add(layout3);
		layoutGroup.add(layout3);
		layoutPanel.add(layout4);
		layoutGroup.add(layout4);
		
		JButton start = new JButton("Start Game");
		
		three.addActionListener(setStoneCount(3));
		four.addActionListener(setStoneCount(4));
		layout1.addActionListener(setLayout(1));
		layout2.addActionListener(setLayout(2));
		layout3.addActionListener(setLayout(3));
		layout4.addActionListener(setLayout(4));
		start.addActionListener(new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
		      frame.setVisible(false);
		      dispose();
			}
		});
		
		Box box1 = Box.createHorizontalBox();
		box1.add(chooseStones);
		box1.add(Box.createHorizontalStrut(215));
	   box1.add(stonePanel);
	   
	   Box box2 = Box.createHorizontalBox();
	   box2.add(chooseLayout);
	   box2.add(Box.createHorizontalStrut(0));
	   box2.add(layoutPanel);
	   
	   Box box3 = Box.createHorizontalBox();
	   box3.add(Box.createVerticalStrut(330));
	   box3.add(Box.createHorizontalStrut(350));
	   box3.add(start);
		
		frame.setLayout(new FlowLayout());
		frame.add(box1);
		frame.add(box2);
		frame.add(box3);
      setResizable(false);
	}
	
	public String showDialog()
	{
		setVisible(true);
		return ""+stoneCount;//? button1.getText() : button2.getText();
	}
	
	public static ActionListener setStoneCount(final int stoneNumber)
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
	
	public static ActionListener setLayout(final int layoutNumber)
	{
		return new
		ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				layout = layouts[layoutNumber];
			}
		};
	}
	
	public int stoneNumber()
	{
		return stoneCount;
	}
	
	public BoardLayout layoutNumber()
	{
		return layout;
	}
}
