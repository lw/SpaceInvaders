/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Overlay extends JPanel implements KeyListener {
	// Constructor
	public Overlay () {
		super ();
		
		setOpaque (false);
		setBackground (new Color (0, 0, 0, 0));
	}
	
	public void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Animation overlay = Arcade.getAnimation ();
		if (overlay != null)
			overlay.paint (g2d);	
	}
	
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode () == KeyEvent.VK_ENTER) {
			Animation overlay = Arcade.getAnimation ();
			if (overlay != null)
				overlay.enterPressed ();
		}
	}
	
	public void keyReleased (KeyEvent e) {}
	
	public void keyTyped (KeyEvent e) {}
}
