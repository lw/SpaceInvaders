/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.util.Iterator;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Space extends JPanel {
	// Members
	private int count = 0;
	
	// Constructor
	public Space () {
		super ();
		
		setOpaque (true);
		setBackground (new Color (0, 0, 0));
	}
	
	// Helper
	private Player getPlayer () {
		return Arcade.getPlayer ();
	}
	
	// Draw
	public void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
		g2d.setRenderingHint (RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		super.paintComponent (g);
		
		// Fading
		if (Arcade.getEnemyCount () == 0) {
			if (count > 0)
				count --;
		}
		else {
			if (count < Animation.FADE_TIME)
				count ++;
		}
		
		int alpha = count * 255 / Animation.FADE_TIME;
		
		// Player
		getPlayer ().move ();
		getPlayer ().shoot ();
		getPlayer ().paint (g2d, alpha);
		
		// Enemies
		Iterator<Enemy> enemies = Arcade.getEnemies ().iterator ();
		while (enemies.hasNext ()) {
			Enemy enemy = enemies.next ();
			if (count == Animation.FADE_TIME) {
				enemy.move ();
				enemy.shoot ();
			}
			enemy.paint (g2d, alpha);
		}
		
		// Shots
		g2d.setColor (new Color (255, 255, 255));
		Iterator<int[]> shots = Arcade.getShots ().iterator ();
		while (shots.hasNext ()) {
			int[] shot = shots.next ();
			shot[1] += shot[2];
			Rectangle2D shot_2d = new Rectangle2D.Double (shot[0] - 1, shot[1], 2, Math.abs (shot[2]));
			
			if (shot[2] < 0) { // Player shot
				boolean hit = false;
				enemies = Arcade.getEnemies ().iterator ();
				Enemy next = null;
				
				while (enemies.hasNext () && !hit) {
					next = enemies.next ();
					hit = next.hit (shot_2d);
				}
				
				if (next != null && hit)
					Arcade.getPlayer ().addPoints (next.getHitPoints ());
				
				if (shot[1] < 0 || hit)
					shots.remove ();
				else
					g2d.fillRect (shot[0] - 1, shot[1], 2, 4);
			}
			else { // Enemy shot
				if (shot[1] > Arcade.getHeight () || (Arcade.getEnemyCount () != 0 && Arcade.getPlayer ().hit (shot_2d)))
					shots.remove ();
				else
					g2d.fillRect (shot[0] - 1, shot[1] - 4, 2, 4);
			}
		}
	}
}
