/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class StartLevel extends Animation {
	private int level;
	private int count = 0;
	private int state = 0;
		
	private static final int DELAY = 20;
	
	// Constructor
	public StartLevel () {
		level = LevelManager.getLevel () + 1;
		
		createFont ();
		startFadeIn ();
	}
	
	// Draw
	public void paint (Graphics2D g) {
		if (state == 2 && count >= FADE_TIME)
			Arcade.setAnimation (new Stats ());
		
		count ++;
		
		if (state == 0 && count >= DELAY + FADE_TIME) {
			state = 1;
			count = 0;
		}
		else if (state == 1 && count >= 2 * DELAY) {
			count = 0;
		}
		
		int alpha = getFadeAlpha ();

		g.setColor (new Color (237, 212, 0, alpha));
		g.setFont (big_font);
		
		int top = 225 - getBigFontHeight (g, "STARTING") / 2;
		int left = 200 - getBigFontWidth (g, "STARTING") / 2;
		g.drawString ("STARTING", left, top);		
		top = 275 - getBigFontHeight (g, "LEVEL "+level) / 2;
		left = 200 - getBigFontWidth (g, "LEVEL "+level) / 2;
		g.drawString ("LEVEL "+level, left, top);
		
		if (state == 1 && count >= DELAY) {
			g.setColor (new Color (255, 255, 255));
			g.setFont (small_font);
			
			top = 425 - getSmallFontHeight (g, "PRESS ENTER TO CONTINUE") / 2;
			left = 200 - getSmallFontWidth (g, "PRESS ENTER TO CONTINUE") / 2;
			g.drawString ("PRESS ENTER TO CONTINUE", left, top);
		}
	}
	
	// Enter pressed
	public void enterPressed () {
		if (state == 1) {
			LevelManager.nextLevel ();
			state = 2;
			count = 0;
			startFadeOut ();
		}
	}
}
