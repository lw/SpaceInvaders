/*  
 *  Copyright (C) 2010  Luca Wehrstedt
 *
 *  This file is released under the GPLv2
 *  Read the file 'COPYING' for more information
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Boss extends Enemy {
	protected AffineTransform at;
	
	private Path2D leg_1;
	private Path2D leg_2;
	private Path2D leg_3;
	private Path2D leg_4;
	private Path2D body;
	private Path2D eyes;
	
	private Area dead;
	
	private double leg_1_x;
	private double leg_1_y;
	private double leg_2_x;
	private double leg_2_y;
	private double leg_3_x;
	private double leg_3_y;
	private double leg_4_x;
	private double leg_4_y;

	private Point2D leg_1_shoot;
	private Point2D leg_2_shoot;
	private Point2D leg_3_shoot;
	private Point2D leg_4_shoot;
	
	protected static int shoot_count = 0;
	
	public Boss () {
				
		at = new AffineTransform ();
		leg_1 = new Path2D.Double ();

		leg_1.moveTo (158.562, 851.499);
		leg_1.curveTo (150.519, 869.947, 142.935, 887.822, 121.556, 899.808);
		leg_1.curveTo (105.572, 908.77, 83.8549, 897.914, 59.0689, 896.312);
		leg_1.curveTo (43.8526, 895.328, 1.8417, 918.24, 0.02075, 954.089);
		leg_1.curveTo (-0.12079, 956.92, 0.42532, 961.72, 2.78726, 959.161);
		leg_1.curveTo (5.17903, 945.89, 35.9995, 906.84, 79.9376, 919.209);
		leg_1.curveTo (143.026, 936.969, 162.629, 927.174, 191.06, 853.923);
		leg_1.curveTo (196.703, 839.384, 198.22, 835.079, 202.162, 824.206);
		leg_1.curveTo (208.715, 799.263, 178.835, 791.792, 171.422, 815.183);
		leg_1.curveTo (166.872, 829.982, 164.706, 835.6, 158.562, 851.499);
		leg_1.closePath ();

		leg_1.transform (AffineTransform.getScaleInstance (0.6, 0.6));
		leg_1.transform (AffineTransform.getTranslateInstance (70, -700));
		leg_1_x = 183;
		leg_1_y = -210;
		leg_1_shoot = new Point2D.Double (71, 238);

		leg_2 = new Path2D.Double ();

		leg_2.moveTo (255.902, 829.338);
		leg_2.lineTo (209.157, 808.004);
		leg_2.curveTo (184.683, 842.607, 179.321, 872.876, 176.273, 894.929);
		leg_2.curveTo (174.111, 910.569, 181.109, 945.096, 166.12, 964.487);
		leg_2.curveTo (154.912, 978.986, 143.725, 984.543, 124.105, 989.994);
		leg_2.curveTo (109.414, 994.077, 83.6877, 1013.87, 96.0294, 1047.58);
		leg_2.curveTo (97.004, 1050.24, 99.3854, 1054.44, 100.559, 1051.16);
		leg_2.curveTo (105.194, 1011.62, 122.175, 1010.71, 155.664, 999.818);
		leg_2.curveTo (218.421, 979.401, 190.924, 915.941, 228.499, 876.101);
		leg_2.curveTo (239.2, 864.754, 259.798, 842.744, 255.902, 829.338);
		leg_2.closePath ();
		
		leg_2.transform (AffineTransform.getScaleInstance (0.6, 0.6));
		leg_2.transform (AffineTransform.getTranslateInstance (70, -700));
		leg_2_x = 202;
		leg_2_y = -197;
		leg_2_shoot = new Point2D.Double (129, 293);
		
		leg_3 = new Path2D.Double ();

		leg_3.moveTo (224.499, 800.277);
		leg_3.lineTo (190.428, 820.754);
		leg_3.curveTo (220.016, 852.759, 227.914, 875.096, 228.069, 893.98);
		leg_3.curveTo (227.451, 908.543, 227.805, 932.73, 237.067, 951.479);
		leg_3.curveTo (246.211, 969.991, 256.331, 979.001, 271.752, 983.931);
		leg_3.curveTo (310.697, 996.383, 304.711, 1004.82, 311.345, 1027.61);
		leg_3.curveTo (317.005, 1026.43, 322.749, 993.699, 292.997, 974.856);
		leg_3.curveTo (247.216, 945.861, 267.657, 914.38, 263.197, 860.596);
		leg_3.curveTo (261.908, 845.053, 259.632, 803.986, 224.499, 800.277);
		leg_3.closePath ();

		leg_3.transform (AffineTransform.getScaleInstance (0.6, 0.6));
		leg_3.transform (AffineTransform.getTranslateInstance (70, -700));
		leg_3_x = 206;
		leg_3_y = -204;
		leg_3_shoot = new Point2D.Double (258, 278);

		leg_4 = new Path2D.Double ();

		leg_4.moveTo (273.012, 806.387);
		leg_4.curveTo (257.437, 785.507, 226.33, 808.061, 244.362, 828.188);
		leg_4.curveTo (260.313, 845.993, 266.652, 855.635, 274.219, 867.44);
		leg_4.curveTo (292.776, 896.387, 311.099, 945.428, 361.64, 936.942);
		leg_4.curveTo (384.475, 933.107, 399.337, 941.708, 402.463, 956.791);
		leg_4.curveTo (404.789, 968.008, 403.001, 969.438, 405.808, 964.939);
		leg_4.curveTo (412.092, 954.869, 396.767, 918.293, 368.803, 922.643);
		leg_4.curveTo (314.475, 931.094, 311.993, 866.378, 286.657, 824.854);
		leg_4.curveTo (281.648, 816.643, 278.025, 813.107, 273.012, 806.387);
		leg_4.closePath ();

		leg_4.transform (AffineTransform.getScaleInstance (0.6, 0.6));
		leg_4.transform (AffineTransform.getTranslateInstance (70, -700));
		leg_4_x = 224;
		leg_4_y = -211;
		leg_4_shoot = new Point2D.Double (312, 242);

		body = new Path2D.Double ();

		body.moveTo (221.565, 627.044);
		body.curveTo (192.844, 627.024, 165.255, 644.358, 176.628, 693.575);
		body.curveTo (180.538, 710.496, 183.278, 726.493, 179.378, 750.856);
		body.curveTo (177.958, 759.727, 168.395, 767.85, 168.097, 778.669);
		body.curveTo (167.826, 788.484, 171.389, 791.445, 177.753, 801.2);
		body.curveTo (131.041, 873.536, 322.033, 868.894, 267.909, 800.168);
		body.curveTo (273.657, 792.965, 276.856, 787.47, 276.94, 777.137);
		body.curveTo (277.045, 764.223, 265.69, 758.359, 265.69, 749.137);
		body.curveTo (265.69, 712.253, 269.981, 693.355, 274.128, 661.075);
		body.curveTo (276.528, 642.392, 248.546, 627.062, 221.565, 627.044);
		body.closePath ();

		body.transform (AffineTransform.getScaleInstance (0.6, 0.6));
		body.transform (AffineTransform.getTranslateInstance (70, -700));
		
		eyes = new Path2D.Double ();
		eyes.append (new Ellipse2D.Double (173.64, -243.1, 12.42, 21.52), false);
		eyes.append (new Ellipse2D.Double (221.37, -243.1, 12.42, 21.52), false);
	}
	
	public int getKillPoints () {
		return 100;
	}
	
	public int getHitPoints () {
		return 10;
	}

	private double PULSE_ANGLE = Math.PI / 172;
	private double pulse_phase = 0;
	
	private double MOVE_STEP = 2.5;
	private double move_phase = 0;
	
	public void move () {
		if (dead != null)
			return;
		
		if (body.getBounds ().getMinY() < 40) {
			at.setToTranslation (0, 2);
			leg_1.transform (at);
			leg_2.transform (at);
			leg_3.transform (at);
			leg_4.transform (at);
			body.transform (at);
			eyes.transform (at);
			leg_1_y += 2;
			leg_2_y += 2;
			leg_3_y += 2;
			leg_4_y += 2;
		}
		else {
			move_phase += Math.PI / 128;
			while (move_phase > 2 * Math.PI)
				move_phase -= 2 * Math.PI;
			
			at.setToTranslation (Math.cos (move_phase) * MOVE_STEP, 0);
			leg_1.transform (at);
			leg_2.transform (at);
			leg_3.transform (at);
			leg_4.transform (at);
			body.transform (at);
			eyes.transform (at);
			leg_1_x += Math.cos (move_phase) * MOVE_STEP;
			leg_2_x += Math.cos (move_phase) * MOVE_STEP;
			leg_3_x += Math.cos (move_phase) * MOVE_STEP;
			leg_4_x += Math.cos (move_phase) * MOVE_STEP;
			leg_1_shoot = at.transform (leg_1_shoot, null);
			leg_2_shoot = at.transform (leg_2_shoot, null);
			leg_3_shoot = at.transform (leg_3_shoot, null);
			leg_4_shoot = at.transform (leg_4_shoot, null);
		}
		
		pulse_phase += Math.PI / 28;
		while (pulse_phase > 2 * Math.PI)
			pulse_phase -= 2 * Math.PI;
		
		at.setToTranslation (-leg_1_x, -leg_1_y);
		leg_1.transform (at);
		leg_1_shoot = at.transform (leg_1_shoot, null);
		at.setToRotation (Math.cos (pulse_phase) * PULSE_ANGLE * 1.2);
		leg_1.transform (at);
		leg_1_shoot = at.transform (leg_1_shoot, null);
		at.setToTranslation (leg_1_x, leg_1_y);
		leg_1.transform (at);
		leg_1_shoot = at.transform (leg_1_shoot, null);
		
		at.setToTranslation (-leg_2_x, -leg_2_y);
		leg_2.transform (at);
		leg_2_shoot = at.transform (leg_2_shoot, null);
		at.setToRotation (Math.cos (pulse_phase) * PULSE_ANGLE * 0.7);
		leg_2.transform (at);
		leg_2_shoot = at.transform (leg_2_shoot, null);
		at.setToTranslation (leg_2_x, leg_2_y);
		leg_2.transform (at);
		leg_2_shoot = at.transform (leg_2_shoot, null);
		
		at.setToTranslation (-leg_3_x, -leg_3_y);
		leg_3.transform (at);
		leg_3_shoot = at.transform (leg_3_shoot, null);
		at.setToRotation (Math.cos (pulse_phase) * PULSE_ANGLE * -0.8);
		leg_3.transform (at);
		leg_3_shoot = at.transform (leg_3_shoot, null);
		at.setToTranslation (leg_3_x, leg_3_y);
		leg_3.transform (at);
		leg_3_shoot = at.transform (leg_3_shoot, null);
		
		at.setToTranslation (-leg_4_x, -leg_4_y);
		leg_4.transform (at);
		leg_4_shoot = at.transform (leg_4_shoot, null);
		at.setToRotation (Math.cos (pulse_phase) * PULSE_ANGLE * -1.1);
		leg_4.transform (at);
		leg_4_shoot = at.transform (leg_4_shoot, null);
		at.setToTranslation (leg_4_x, leg_4_y);
		leg_4.transform (at);
		leg_4_shoot = at.transform (leg_4_shoot, null);
	}
	
	public void paint (Graphics2D g, int alpha) {
		if (dead != null) {
			g.setColor (new Color (hit_count * 255 / 50, hit_count * 255 / 50, hit_count * 255 / 50));
			
			g.fill (dead);
			
			hit_count --;
			if (hit_count <= 0) {
				Arcade.removeEnemy (this);
			}
		}
		else {
			g.setColor (new Color (255, 255, 255, alpha));
			
			g.fill (leg_1);
			g.fill (leg_2);
			g.fill (leg_3);
			g.fill (leg_4);
			g.fill (body);
			
			g.setColor (new Color (hit_count * 255 / MAX_HITS, 0, 0, alpha));
			
			g.fill (eyes);
		}
	}
	
	public void shoot () {
		if (dead == null && shoot_count <= 0 && body.getBounds ().getMinY() >= 40) {
			Arcade.shoot ((int)leg_1_shoot.getX(), (int)leg_1_shoot.getY(), 2);
			Arcade.shoot ((int)leg_2_shoot.getX(), (int)leg_2_shoot.getY(), 2);
			Arcade.shoot ((int)leg_3_shoot.getX(), (int)leg_3_shoot.getY(), 2);
			Arcade.shoot ((int)leg_4_shoot.getX(), (int)leg_4_shoot.getY(), 2);
			shoot_count = 10;
		}
		shoot_count --;
	}
	
	private int MAX_HITS = 10;
	private int hit_count = MAX_HITS;
	
	public boolean hit (Rectangle2D shot) {
		if (body.getBounds ().getMinY() >= 40 &&
		    (body.intersects (shot) || leg_1.intersects (shot)
		     || leg_2.intersects (shot) || leg_3.intersects (shot) || leg_4.intersects (shot))) {
			hit_count --;
			if (hit_count < 0) {
				dead = new Area (body);
				dead.add (new Area (leg_1));
				dead.add (new Area (leg_2));
				dead.add (new Area (leg_3));
				dead.add (new Area (leg_4));
				hit_count = 50;
				Arcade.killEnemy (this);
			}
			return true;
		}
		return false;
	}
}

		
