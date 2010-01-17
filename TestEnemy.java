/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class TestEnemy extends Enemy {
	// Punti
	private int HIT_POINTS = 1;
	public int KILL_POINTS = 5;
	
	// Campi locali
	private int x;
	private int y;
	private int move_counter = 0;
	private boolean forward = true;
	private boolean vertical;
	private boolean destroyed = false;
	private int destruction_count = 0;
	
	// Costruttore
	public TestEnemy (boolean movement) {
		super ();
		vertical = movement;
		
		x = Arcade.getWidth() / 2;
		y = Arcade.getHeight() / 2;
	}
	
	// Punti
	
	public int getHitPoints () {
		return HIT_POINTS;
	}
	
	public int getKillPoints () {
		return KILL_POINTS;
	}
	
	// Movimento
	
	public void move () {
		x = Arcade.getWidth() / 2;
		y = Arcade.getHeight() / 2;
		
		if (vertical)
			y += move_counter / 2;
		else
			x += move_counter / 2;
		
		if (forward) {
			move_counter ++;
			if (move_counter >= 100)
				forward = false;
		}
		else {
			move_counter --;
			if (move_counter <= -100)
				forward = true;
		}
	}
	
	// Disegno
	
	public void paint (Graphics2D g, int alpha) {
		g.setColor (new Color (255, 105, 180, alpha));
		if (!destroyed) {
			g.fillRect (x - 20, y - 20, 40, 40);
		}
		else {
			if (destruction_count < 25) {
				g.fillRect (x - 20 - destruction_count / 2, y - 20 - destruction_count / 2, 20, 20);
				g.fillRect (x + destruction_count / 2, y - 20 - destruction_count / 2, 20, 20);
				g.fillRect (x - 20 - destruction_count / 2, y + destruction_count / 2, 20, 20);
				g.fillRect (x + destruction_count / 2, y + destruction_count / 2, 20, 20);
				destruction_count ++;
			}
			else {
				Arcade.removeEnemy (this);
			}
		}
	}
	
	// Sparo
	
	public void shoot () {
		if ((int)(Math.random() * 20) == 10)
			Arcade.shoot (x, y + 20, 5);
	}
	
	// Colpito?
	
	public boolean hit (Rectangle2D shot) {
		Rectangle2D shape = new Rectangle2D.Double (x - 20, y - 20, 40, 40);
		if (shape.intersects (shot)) {
			if (!destroyed) {
				Arcade.killEnemy (this);
				destroyed = true;
				return true;
			}
		}
		return false;
	}
}
