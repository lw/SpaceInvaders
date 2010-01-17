import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.geom.AffineTransform;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class Player implements MouseListener, MouseMotionListener {
	private AffineTransform transformation;
	private Path2D shape;
	
	private static final int SPEED = 10;
	private static final int PADDING = 50;
	
	private int destination;
	
	private boolean is_shooting = false;
	
	private static final int SHOOT_INTERVAL = 25;
	private int shoot_count = 0;
	
	private int lifes = 3;
	private int points = 0;
	
	private static final int BLINK_TIME = 10;
	private static final int SPAWN_TIME = 20;
	
	private boolean blinking = false;
	private boolean spawning = false;
	private int count = 0;
	
	// Constructor
	public Player () {
		destination = Arcade.getWidth () / 2;
		
		transformation = new AffineTransform ();
		shape = new Path2D.Double ();
		
		shape.moveTo (-6.0, 15.0);
		shape.lineTo (-4.0, 11.0);
		shape.lineTo (-12.0, 11.0);
		shape.lineTo (-13.5, 14.0);
		shape.lineTo (-15.0, 11.0);
		shape.lineTo (-15.0, 1.0);
		shape.lineTo (-13.5, -2.0);
		shape.lineTo (-12.0, 1.0);
		shape.lineTo (-4.0, -2.0);
		shape.lineTo (-4.0, -7.0);
//		shape.lineTo (-3.0, -8.0);
		shape.lineTo (-2.0, -14.0);
		shape.lineTo (-1.0, -15.0);
		shape.lineTo (1.0, -15.0);
		shape.lineTo (2.0, -14.0);
//		shape.lineTo (3.0, -8.0);
		shape.lineTo (4.0, -7.0);
		shape.lineTo (4.0, -2.0);
		shape.lineTo (12.0, 1.0);
		shape.lineTo (13.5, -2.0);
		shape.lineTo (15.0, 1.0);
		shape.lineTo (15.0, 11.0);
		shape.lineTo (13.5, 14.0);
		shape.lineTo (12.0, 11.0);
		shape.lineTo (4.0, 11.0);
		shape.lineTo (6.0, 15.0);
		shape.closePath ();
		
		shape.transform (AffineTransform.getScaleInstance (1.4, 1.4)); // Diventa 21x21
		
		transformation.translate (Arcade.getWidth () / 2 - shape.getBounds ().getCenterX (),
		                          Arcade.getHeight () - PADDING - shape.getBounds ().getCenterY ());
		shape.transform (transformation);
	}
	
	// Return position
	public int getPosition () {
		return (int)shape.getBounds ().getCenterX ();
	}
	
	// Return lifes
	public int getLifes () {
		return lifes;
	}
	
	// Loose life
	public void looseLife () {
		if (!(blinking || spawning)) {
			blinking = true;
			count = 4 * BLINK_TIME;
		}
	}
	
	// Return points
	public int getPoints () {
		return points;
	}
	
	// Add points
	public void addPoints (int _points) {
		points += _points;
	}
	
	// Move
	public void move () {
		transformation.setToIdentity ();
		if (blinking) {
			if (count % BLINK_TIME == 0) {
				if (count / BLINK_TIME % 2 == 0)
					transformation.translate (0, PADDING + shape.getBounds ().height);
				else
					transformation.translate (0, -1 * (PADDING + shape.getBounds ().height));
			}
			
			count --;			
			if (count <= 0) {
				blinking = false;
				lifes --;
				if (lifes < 0) {
					Arcade.clear ();
					Arcade.setAnimation (new GameOver ());
					lifes = 3;
					points = 0;
				}
			}
		}
		else if (spawning) {
			if (count == 0) {
				transformation.translate (Arcade.getWidth () / 2 - shape.getBounds ().getCenterX (),
				                          PADDING + shape.getBounds ().height);
				destination = Arcade.getWidth () / 2;	
			}
			if (count >= 2 * BLINK_TIME && count < 2 * BLINK_TIME + SPAWN_TIME)
				transformation.translate (0, -1.0 * (PADDING + shape.getBounds ().height) / SPAWN_TIME);
			if (count >= 5 * BLINK_TIME + SPAWN_TIME)
				spawning = false;
			count ++;
		}
		
		if (!spawning || (!blinking && count > 2 * BLINK_TIME + SPAWN_TIME)) {
			if (destination > shape.getBounds ().getCenterX ()) {
				if (destination > shape.getBounds ().getCenterX () + SPEED)
					transformation.translate (SPEED, 0);
				else
					transformation.translate (destination - shape.getBounds ().getCenterX (), 0);
			}
			if (destination < shape.getBounds ().getCenterX ()) {
				if (destination < shape.getBounds ().getCenterX () - SPEED)
					transformation.translate (-1 * SPEED, 0);
				else
					transformation.translate (-1 * (shape.getBounds ().getCenterX () - destination), 0);
			}
		}
		
		shape.transform (transformation);
	}
	
	// Paint
	public void paint (Graphics2D g, int alpha) {
		g.setColor (new Color (150, 150, 150, alpha));
		g.fill (shape);
	}
	
	// Shoot
	public void shoot () {
		if (!(blinking || spawning) && is_shooting && shoot_count <= 0) {
			Arcade.shoot ((int)shape.getBounds ().getCenterX (), (int)shape.getBounds ().getMinY (), -12);
			shoot_count = SHOOT_INTERVAL;
		}
		if (shoot_count > 0)
			shoot_count --;
	}
	
	// Hit
	public boolean hit (Rectangle2D shot) {
		if (lifes >= 0 && !(blinking || spawning) && shape.intersects (shot)) {
			looseLife ();
			spawning = true;
			return true;
		}
		else {
			return false;
		}
	}
	
	// Mouse inside
	public void mouseEntered (MouseEvent e) {}
	
	// Mouse outside
	public void mouseExited (MouseEvent e) {}
	
	// Mouse pressed
	public void mousePressed (MouseEvent e) {
		is_shooting = true;
	}
	
	// Mouse released
	public void mouseReleased (MouseEvent e) {
		is_shooting = false;
	}
	
	// Mouse clicked
	public void mouseClicked (MouseEvent e) {}
	
	// Mouse dragged
	public void mouseDragged (MouseEvent e) {
		destination = e.getX();
		if (destination > Arcade.getWidth () - shape.getBounds ().width / 2)
			destination = Arcade.getWidth () - shape.getBounds ().width / 2;
		if (destination < shape.getBounds ().width / 2)
			destination = shape.getBounds ().width / 2;
	}
	
	// Mouse moved
	public void mouseMoved (MouseEvent e) {
		destination = e.getX();
		if (destination > Arcade.getWidth () - shape.getBounds ().width / 2)
			destination = Arcade.getWidth () - shape.getBounds ().width / 2;
		if (destination < shape.getBounds ().width / 2)
			destination = shape.getBounds ().width / 2;
	}
}
