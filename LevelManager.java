/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

public class LevelManager {
	private static int level = 1;
	private static final int NUM_LEVELS = 3;
	
	// Getters and detters
	public static int getLevel () {
		return level;
	}
	
	// Next level
	public static void nextLevel () {
		level ++;
		
		if (level == 1) {
			// Aggiungere il/i nemico/i in queste righe,
			// passandolo come parametro a Arcade.addEnemy
			// Esempio:
			Arcade.addEnemy (new TestEnemy (false));
		}
		else if (level == 2) {
			Arcade.addEnemy (new RotShooter (50, -90));
			Arcade.addEnemy (new RotShooter (125, -180));
			Arcade.addEnemy (new RotShooter (200, -30));
			Arcade.addEnemy (new RotShooter (275, -180));
			Arcade.addEnemy (new RotShooter (350, -90));
		}
		else if (level == 3) {
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			Arcade.addEnemy (new CultOctupus ());
			
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			Arcade.addEnemy (new CultBug ());
			
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
			Arcade.addEnemy (new CultKrabby ());
		}
		else
			reset ();
	}
	
	// Reset
	public static void reset () {
		level = 0;
	}
	
	// Last level?
	public static boolean isLastLevel () {
		return level == NUM_LEVELS;
	}
}
