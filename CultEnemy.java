import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Path2D;
import java.awt.geom.Path2D.Double;
import java.awt.geom.AffineTransform;

public abstract class CultEnemy extends Enemy {
	protected AffineTransform translation;
	protected Path2D state_1;
	protected Path2D state_2;
	protected Path2D explosion;
	
	protected int x;
	protected int y;
	protected int count = 0;
	protected int state = 1;
	
	protected int center;
	protected int spanning;
	protected boolean forward = true;
	
	protected static final int SPANNING = 40;
	protected static final int TOP = 160;
	protected static int enemy_count = 0;
	protected static boolean matrix[][] = new boolean[8][5];
	protected int row;
	protected int column;
	
	protected static int shoot_count = 0;
	
	protected static final int X_SPEED = 1;
	protected static final int Y_SPEED = 8;
	
	protected CultEnemy () {
		row = enemy_count / 8;
		column = enemy_count % 8;
		y = TOP + SPANNING * row;
		x = center = Arcade.getWidth() / 2 - SPANNING * 35 / 10 + SPANNING * column;
		matrix[column][row] = true;
		enemy_count ++;
		
		translation = new AffineTransform ();
		state_1 = new Path2D.Double ();
		state_2 = new Path2D.Double ();
		explosion = new Path2D.Double ();
	}
	
	public void move () {
		translation.setToIdentity ();
		if (forward) {
			translation.translate (X_SPEED, 0);
			x += X_SPEED;
			if (x > center + SPANNING) {
				translation.translate (-1 * X_SPEED, Y_SPEED);
				x -= X_SPEED;
				y += Y_SPEED;
				forward = false;
			}
		}
		else {
			translation.translate (-1 * X_SPEED, 0);
			x -= X_SPEED;
			if (x < center - SPANNING) {
				translation.translate (X_SPEED, Y_SPEED);
				x += X_SPEED;
				y += Y_SPEED;
				forward = true;
			}
		}
		if (y >= Arcade.getHeight ()) {
			Arcade.survivedEnemy (this);
			Arcade.removeEnemy (this);
			enemy_count --;
			matrix[column][row] = false;
		}
		state_1.transform (translation);
		state_2.transform (translation);
		explosion.transform (translation);
	}
	
	public void paint (Graphics2D g, int alpha) {
		count ++;
		if (count == 20) {
			if (state == 1)
				state = 2;
			else if (state == 2)
				state = 1;
			else {
				Arcade.removeEnemy (this);
				enemy_count --;
				matrix[column][row] = false;
			}
			count = 0;
		}
		
		g.setColor (new Color (255, 255, 255, alpha));
		if (state == 1)
			g.fill (state_1);
		else if (state == 2)
			g.fill (state_2);
		else
			g.fill (explosion);
	}
	
	public void shoot () {
		boolean shooting = false;
		for (int i = 4; i >= 0; i--) {
			if (matrix[column][i])
			{
				if (i == row)
					shooting = true;
				break;
			}
		}
		if (state != 3 && shooting)
		{
			if (shoot_count == 0 && Math.abs (x - Arcade.getPlayerPosition ()) <= 20) {
				Arcade.shoot (x, y + 8, 5);
				shoot_count = 30 * 8;
			}
			if (shoot_count > 0) {
				shoot_count --;
			}
		}
	}
	
	public boolean hit (Rectangle2D shot) {
		if ((state == 1 && state_1.intersects (shot)) ||
		    (state == 2 && state_2.intersects (shot))) {
			state = 3;
			Arcade.killEnemy (this);
			count = 0;
			return true;
		}
		return false;
	}
}
