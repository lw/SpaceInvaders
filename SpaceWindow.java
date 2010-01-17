import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpaceWindow extends JFrame implements ActionListener {
	// Members
	private Space gamefield;
	private Overlay overlay;
	
	// Constructor
	public SpaceWindow () {
		super ();
		
		setTitle ("Space Invaders");
		setResizable (false);
		pack ();
		
		setSize (Arcade.getWidth () + getInsets ().left + getInsets ().right,
		         Arcade.getHeight () + getInsets ().top + getInsets ().bottom);
		
		getContentPane ().addMouseListener (Arcade.getPlayer ());
		getContentPane ().addMouseMotionListener (Arcade.getPlayer ());
		
		gamefield = new Space ();
		getContentPane ().add (gamefield);
		
		Timer timer = new Timer (40, this);
		timer.start ();
		
		overlay = new Overlay ();
		setGlassPane (overlay);
		getGlassPane ().setVisible (true);
		
		addKeyListener (overlay);
	}
	
	// New frame
	public void actionPerformed (ActionEvent e) {
		repaint ();
	}
}
