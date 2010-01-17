/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class GameOver extends Animation {
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
	};
	
	private int pixel_size;
	private int count = -1 * FADE_TIME;
	private int state = 0;
	
	private static final int ALIEN_DELAY = 58;
	private static final int DELAY = 20;
		
	// Constructor
	public GameOver () {
		createFont ();
		startFadeIn ();
	}
	
	// Draw
	public void paint (Graphics2D g) {
		if (count >= FADE_TIME + ALIEN_DELAY + 2 * DELAY)
			Arcade.setAnimation (new Title ());

		if (count < ALIEN_DELAY) {
			if (count >= 0) {
				pixel_size = (int)(Math.pow (count / 4.0, 2) + 6);
				if (pixel_size % 2 != 0)
					pixel_size ++;
			}
			else
				pixel_size = 6;
			
			int start_x = Arcade.getWidth () / 2 - pixel_size * 11 / 2;
			int start_y = Arcade.getHeight () / 2 - pixel_size * 4;
			
			int alpha = 255;
			if (count < 0)
				alpha = 255 + count * 255 / FADE_TIME;
			if (count > 26 && count < ALIEN_DELAY)
				alpha = 255 - (int)Math.pow ((count - 26) / 2.0, 2);
		
			int index;
			
			if (count / 10 % 2 == 0)
				index = 0;
			else
				index = 1;
			
			g.setColor (new Color (204, 0, 0, alpha));
		
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 11; j++) {
					if (enemy[index][i][j] == 1)
						g.fillRect (start_x + j * pixel_size, start_y + i * pixel_size, pixel_size, pixel_size);
				}
			}
		}
		
		count ++;
		
		if (count > ALIEN_DELAY - FADE_TIME) {
			int alpha = getFadeAlpha ();
			g.setColor (new Color (237, 212, 0, alpha));
			g.setFont (big_font);
		
			int top = 225 - getBigFontHeight (g, "GAME") / 2;
			int left = 200 - getBigFontWidth (g, "GAME") / 2;
			g.drawString ("GAME", left, top);
			top = 275 - getBigFontHeight (g, "OVER") / 2;
			left = 200 - getBigFontWidth (g, "OVER") / 2;
			g.drawString ("OVER", left, top);
			
			if (count > ALIEN_DELAY + DELAY && count <= ALIEN_DELAY + 2 * DELAY) {
				g.setColor (new Color (255, 255, 255));
				g.setFont (small_font);
				
				top = 425 - getSmallFontHeight (g, "PRESS ENTER TO RETRY") / 2;
				left = 200 - getSmallFontWidth (g, "PRESS ENTER TO RETRY") / 2;
				g.drawString ("PRESS ENTER TO RETRY", left, top);
			}
			
			if (count == ALIEN_DELAY + 2 * DELAY)
				count = ALIEN_DELAY;
		}
	}
	
	// Key pressed
	public void enterPressed () {
		if (count > ALIEN_DELAY && count <= ALIEN_DELAY + 2 * DELAY) {
			count = ALIEN_DELAY + 2 * DELAY;
			startFadeOut ();
		}
	}
}
