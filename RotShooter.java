import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.AffineTransform;

public class RotShooter extends Enemy {
	protected AffineTransform transf;
	protected Path2D shape;
	
	protected int x;
	protected int y;
	protected int count = 0;
	protected int state = 1;
	
	protected static int shoot_count = 0;
	
	protected static final int ROT_SPEED = 2; // In PI / 48
	protected static final int SPEED = 2;
	
	public RotShooter (int _x, int _y) {
		x = _x;
		y = _y;
				
		transf = new AffineTransform ();
		shape = new Path2D.Double ();
		
		shape.moveTo (4, 0);
		shape.lineTo (4, 20);
		shape.lineTo (12, 20);
		shape.lineTo (12, 28);
		shape.lineTo (-12, 28);
		shape.lineTo (-12, 20);
		shape.lineTo (-4, 20);
		shape.lineTo (-4, 0);
		shape.closePath ();
		
		Path2D base = new Path2D.Double (shape);
		
		transf.rotate (Math.PI * 2 / 3);
		shape.append (transf.createTransformedShape (base), false);
		transf.rotate (Math.PI * 2 / 3);
		shape.append (transf.createTransformedShape (base), false);
		
		transf.setToTranslation (x, y);
		shape.transform (transf);
	}
	
	public int getKillPoints () {
		return 30;
	}
	
	public int getHitPoints () {
		return 10;
	}
	
	public void move () {
		if (state == 1 || state == 2) {
			transf.setToTranslation (-x, -y);
			shape.transform (transf);
			
			transf.setToRotation (Math.PI * ROT_SPEED / 48);
			shape.transform (transf);
			
			if (state == 1)
				y += SPEED;
			
			transf.setToTranslation (x, y);
			shape.transform (transf);
			
			if (shape.getBounds().getMinY() > Arcade.getHeight ()) {
				Arcade.survivedEnemy (this);
				Arcade.removeEnemy (this);
			}
		}
	}
	
	public void paint (Graphics2D g, int alpha) {
		g.setColor (new Color (255, 255, 255, alpha));
		
		if (state == 1 || state == 2) {
			g.fill (shape);
		}
		else {
			count --;
			if (count > 0) {
				Area circle = new Area (new Ellipse2D.Double (x-count, y-count, 2*count, 2*count));
				Area object = new Area (shape);
				object.intersect (circle);
				g.fill (object);
			}
			else {
				Arcade.removeEnemy (this);
			}
		}
	}
	
	public void shoot () {
		if (state == 1 || state == 2) {
			count ++;
			
			if (count * ROT_SPEED >= 64) {
				if (shape.getBounds().getMaxY() > 0)
				Arcade.shoot ((int)shape.getBounds().getCenterX(), (int)shape.getBounds().getMaxY(), 10);
				count = 0;
			}
		}
	}
	
	public boolean hit (Rectangle2D shot) {
		if (shape.intersects (shot)) {
			if (state == 1) {
				state = 2;
			}
			else {
				state = 3;
				count = 32;
				Arcade.killEnemy (this);
			}
			return true;
		}
		return false;
	}
}
