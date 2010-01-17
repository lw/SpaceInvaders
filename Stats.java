/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class Stats extends Animation {
	// Constructor
	public Stats () {
		createFont ();
	}
	
	// Draw
	public void paint (Graphics2D g) {
		// Score
		g.setFont (small_font);
		
		g.setColor (new Color (129, 226, 212));
		int top = getSmallFontHeight (g, "SCORE") - 5;
		int left = 395 - getSmallFontWidth (g, "SCORE");
		g.drawString ("SCORE", left, top);
		
		g.setColor (new Color (255, 255, 255));
		top = 2 * getSmallFontHeight (g, Arcade.getPlayer ().getPoints ()+"") - 10;
		left = 395 - getSmallFontWidth (g, Arcade.getPlayer ().getPoints ()+"");
		g.drawString (Arcade.getPlayer ().getPoints ()+"", left, top);
		
		g.setColor (new Color (129, 226, 212));
		top = getSmallFontHeight (g, "LIFES") - 5;
		left = 5;
		g.drawString ("LIFES", left, top);
		
		g.setColor (new Color (255, 255, 255));
		top = 2 * getSmallFontHeight (g, Arcade.getPlayer ().getLifes ()+"") - 10;
		left = 5;
		g.drawString (Arcade.getPlayer ().getLifes ()+"", left, top);
	}
	
	public void enterPressed () {
		// Do nothing...
	}
}
