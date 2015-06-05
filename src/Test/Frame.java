package Test;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

/**
*
* @author Alexander Sopov
*/
public class Frame  extends JFrame {
		
	public final static int WIDTH = 1280;
	public final static int HEIGHT = 750;
		
	public Frame(){
		super("Test");
		final GridLayout layout = new GridLayout(0, 2);
		setLayout(layout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public int getFrameWidth(){
		return WIDTH;
		
	}
		
	public int getFrameHeight(){
		return HEIGHT;
	}
}

