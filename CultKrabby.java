/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

public class CultKrabby extends CultEnemy {
	public int getHitPoints () {
		return 0;
	}
	public int getKillPoints () {
		return 10;
	}
	
	// Constructor
	public CultKrabby () {
		super ();
		
		state_1.moveTo (2.0, -4.0);
		state_1.lineTo (2.0, -3.0);
		state_1.lineTo (5.0, -3.0);
		state_1.lineTo (5.0, -2.0);
		state_1.lineTo (6.0, -2.0);
		state_1.lineTo (6.0, 1.0);
		state_1.lineTo (4.0, 1.0);
		state_1.lineTo (4.0, 2.0);
		state_1.lineTo (5.0, 2.0);
		state_1.lineTo (5.0, 3.0);
		state_1.lineTo (4.0, 3.0);
		state_1.lineTo (4.0, 4.0);
		state_1.lineTo (2.0, 4.0);
		state_1.lineTo (2.0, 3.0);
		state_1.lineTo (3.0, 3.0);
		state_1.lineTo (3.0, 2.0);
		state_1.lineTo (1.0, 2.0);
		state_1.lineTo (1.0, 0.0);
		state_1.lineTo (3.0, 0.0);
		state_1.lineTo (3.0, -1.0);
		state_1.lineTo (1.0, -1.0);
		state_1.lineTo (1.0, 1.0);
		state_1.lineTo (-1.0, 1.0);
		state_1.lineTo (-1.0, -1.0);
		state_1.lineTo (-3.0, -1.0);
		state_1.lineTo (-3.0, 0.0);
		state_1.lineTo (-1.0, 0.0);
		state_1.lineTo (-1.0, 2.0);
		state_1.lineTo (-3.0, 2.0);
		state_1.lineTo (-3.0, 3.0);
		state_1.lineTo (-2.0, 3.0);
		state_1.lineTo (-2.0, 4.0);
		state_1.lineTo (-4.0, 4.0);
		state_1.lineTo (-4.0, 3.0);
		state_1.lineTo (-5.0, 3.0);
		state_1.lineTo (-5.0, 2.0);
		state_1.lineTo (-4.0, 2.0);
		state_1.lineTo (-4.0, 1.0);
		state_1.lineTo (-6.0, 1.0);
		state_1.lineTo (-6.0, -2.0);
		state_1.lineTo (-5.0, -2.0);
		state_1.lineTo (-5.0, -3.0);
		state_1.lineTo (-2.0, -3.0);
		state_1.lineTo (-2.0, -4.0);
		state_1.closePath ();
		state_1.moveTo (1.0, 2.0);
		state_1.lineTo (1.0, 3.0);
		state_1.lineTo (-1.0, 3.0);
		state_1.lineTo (-1.0, 2.0);
		state_1.closePath ();
		
		state_2.moveTo (2.0, -4.0);
		state_2.lineTo (2.0, -3.0);
		state_2.lineTo (5.0, -3.0);
		state_2.lineTo (5.0, -2.0);
		state_2.lineTo (6.0, -2.0);
		state_2.lineTo (6.0, 1.0);
		state_2.lineTo (3.0, 1.0);
		state_2.lineTo (3.0, 2.0);
		state_2.lineTo (4.0, 2.0);
		state_2.lineTo (4.0, 4.0);
		state_2.lineTo (6.0, 4.0);
		state_2.lineTo (6.0, 3.0);
		state_2.lineTo (2.0, 3.0);
		state_2.lineTo (2.0, 2.0);
		state_2.lineTo (1.0, 2.0);
		state_2.lineTo (1.0, 0.0);
		state_2.lineTo (3.0, 0.0);
		state_2.lineTo (3.0, -1.0);
		state_2.lineTo (1.0, -1.0);
		state_2.lineTo (1.0, 1.0);
		state_2.lineTo (-1.0, 1.0);
		state_2.lineTo (-1.0, -1.0);
		state_2.lineTo (-3.0, -1.0);
		state_2.lineTo (-3.0, 0.0);
		state_2.lineTo (-1.0, 0.0);
		state_2.lineTo (-1.0, 2.0);
		state_2.lineTo (-2.0, 2.0);
		state_2.lineTo (-2.0, 3.0);
		state_2.lineTo (-6.0, 3.0);
		state_2.lineTo (-6.0, 4.0);
		state_2.lineTo (-4.0, 4.0);
		state_2.lineTo (-4.0, 2.0);
		state_2.lineTo (-3.0, 2.0);
		state_2.lineTo (-3.0, 1.0);
		state_2.lineTo (-6.0, 1.0);
		state_2.lineTo (-6.0, -2.0);
		state_2.lineTo (-5.0, -2.0);
		state_2.lineTo (-5.0, -3.0);
		state_2.lineTo (-2.0, -3.0);
		state_2.lineTo (-2.0, -4.0);
		state_2.closePath ();
		state_2.moveTo (1.0, 2.0);
		state_2.lineTo (1.0, 3.0);
		state_2.lineTo (-1.0, 3.0);
		state_2.lineTo (-1.0, 2.0);
		state_2.closePath ();
		
		explosion.moveTo (0.5, -2.0);
		explosion.lineTo (1.5, -2.0);
		explosion.lineTo (1.5, -4.0);
		explosion.lineTo (2.5, -4.0);
		explosion.lineTo (2.5, -3.0);
		explosion.lineTo (0.5, -3.0);
		explosion.closePath ();
		explosion.moveTo (1.5, 0.0);
		explosion.lineTo (2.5, 0.0);
		explosion.lineTo (2.5, -2.0);
		explosion.lineTo (4.5, -2.0);
		explosion.lineTo (4.5, -3.0);
		explosion.lineTo (3.5, -3.0);
		explosion.lineTo (3.5, -1.0);
		explosion.lineTo (1.5, -1.0);
		explosion.closePath ();
		explosion.moveTo (3.5, 0.0);
		explosion.lineTo (5.5, 0.0);
		explosion.lineTo (5.5, 1.0);
		explosion.lineTo (3.5, 1.0);
		explosion.closePath ();
		explosion.moveTo (0.5, 2.0);
		explosion.lineTo (0.5, 3.0);
		explosion.lineTo (4.5, 3.0);
		explosion.lineTo (4.5, 4.0);
		explosion.lineTo (3.5, 4.0);
		explosion.lineTo (3.5, 2.0);
		explosion.closePath ();
		explosion.moveTo (1.5, 1.0);
		explosion.lineTo (2.5, 1.0);
		explosion.lineTo (2.5, 4.0);
		explosion.lineTo (1.5, 4.0);
		explosion.closePath ();
		explosion.moveTo (-0.5, -2.0);
		explosion.lineTo (-1.5, -2.0);
		explosion.lineTo (-1.5, -4.0);
		explosion.lineTo (-2.5, -4.0);
		explosion.lineTo (-2.5, -3.0);
		explosion.lineTo (-0.5, -3.0);
		explosion.closePath ();
		explosion.moveTo (-1.5, 0.0);
		explosion.lineTo (-2.5, 0.0);
		explosion.lineTo (-2.5, -2.0);
		explosion.lineTo (-4.5, -2.0);
		explosion.lineTo (-4.5, -3.0);
		explosion.lineTo (-3.5, -3.0);
		explosion.lineTo (-3.5, -1.0);
		explosion.lineTo (-1.5, -1.0);
		explosion.closePath ();
		explosion.moveTo (-3.5, 0.0);
		explosion.lineTo (-5.5, 0.0);
		explosion.lineTo (-5.5, 1.0);
		explosion.lineTo (-3.5, 1.0);
		explosion.closePath ();
		explosion.moveTo (-0.5, 2.0);
		explosion.lineTo (-0.5, 3.0);
		explosion.lineTo (-4.5, 3.0);
		explosion.lineTo (-4.5, 4.0);
		explosion.lineTo (-3.5, 4.0);
		explosion.lineTo (-3.5, 2.0);
		explosion.closePath ();
		explosion.moveTo (-1.5, 1.0);
		explosion.lineTo (-2.5, 1.0);
		explosion.lineTo (-2.5, 4.0);
		explosion.lineTo (-1.5, 4.0);
		explosion.closePath ();
		
		translation.setToScale (3.0, 3.0);
		state_1.transform (translation);
		state_2.transform (translation);
		explosion.transform (translation);
		translation.setToTranslation (x, y);
		state_1.transform (translation);
		state_2.transform (translation);
		explosion.transform (translation);
	}
}
