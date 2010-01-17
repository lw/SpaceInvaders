/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class EndGame extends Animation {
	private int[][][] enemy = {
		{	{0,0,1,0,0,0,0,0,1,0,0},
			{0,0,0,1,0,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,0,0},
			{0,1,1,0,1,1,1,0,1,1,0},
			{1,1,1,1,1,1,1,1,1,1,1},
			{1,0,1,1,1,1,1,1,1,0,1},
			{1,0,1,0,0,0,0,0,1,0,1},
			{0,0,0,1,1,0,1,1,0,0,0}
		},
		{	{0,0,1,0,0,0,0,0,1,0,0},
			{1,0,0,1,0,0,0,1,0,0,1},
			{1,0,1,1,1,1,1,1,1,0,1},
			{1,1,1,0,1,1,1,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1},
			{0,1,1,1,1,1,1,1,1,1,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,1,0,0,0,0,0,0,0,1,0}
		},
		{	{0,0,0,1,0,0,0,1,0,0,0},
			{0,1,0,0,1,0,1,0,0,1,0},
			{0,0,1,0,0,0,0,0,1,0,0},
			{0,0,0,1,0,0,0,1,0,0,0},
			{1,1,0,0,0,0,0,0,0,1,1},
			{0,0,0,1,0,0,0,1,0,0,0},
			{0,0,1,0,1,0,1,0,1,0,0},
			{0,1,0,1,0,0,0,1,0,1,0}
		}
	};
	
	private int count = -1 * FADE_TIME;
	private int state = 0;
	
	private static final int DELAY = 20;
	private static final int PIXEL_SIZE = 12;
	
	// Constructor
	public EndGame () {
		createFont ();
		startFadeIn ();
	}
	
	// Draw
	public void paint (Graphics2D g) {
		if (count >= FADE_TIME + DELAY)
			Arcade.setAnimation (new Title ());

		int start_x = Arcade.getWidth () / 2 - PIXEL_SIZE * 11 / 2;
		int start_y = Arcade.getHeight () / 2 + 80 - PIXEL_SIZE * 4;
		int alpha = getFadeAlpha ();
		
		count ++;
		
		if (count == DELAY) {
			count = 0;
			state ++;
		}
		
		if (state == 8)
			state = 6;
		
		if (state < 6) {
			int index;
			
			if (state == 0 || state == 2 || state == 4)
				index = 0;
			else if (state == 1 || state == 3)
				index = 1;
			else
				index = 2;
			
			g.setColor (new Color (115, 210, 22, alpha));
		
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 11; j++) {
					if (enemy[index][i][j] == 1)
						g.fillRect (start_x + j * PIXEL_SIZE, start_y + i * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
				}
			}
		}
		
		g.setColor (new Color (237, 212, 0, alpha));
		g.setFont (big_font);
		
		int top = 225 - getBigFontHeight (g, "GAME") / 2;
		int left = 200 - getBigFontWidth (g, "GAME") / 2;
		g.drawString ("GAME", left, top);
		top = 275 - getBigFontHeight (g, "COMPLETED") / 2;
		left = 200 - getBigFontWidth (g, "COMPLETED") / 2;
		g.drawString ("COMPLETED", left, top);
		
		if (state == 7) {
			g.setColor (new Color (255, 255, 255, alpha));
			g.setFont (small_font);
			
			top = 425 - getSmallFontHeight (g, "PRESS ENTER TO CONTINUE") / 2;
			left = 200 - getSmallFontWidth (g, "PRESS ENTER TO CONTINUE") / 2;
			g.drawString ("PRESS ENTER TO CONTINUE", left, top);
		}
	}
	
	// Key pressed
	public void enterPressed () {
		if (state >= 6) {
			state = 6;
			count = DELAY + 1;
			startFadeOut ();
		}
	}
}
