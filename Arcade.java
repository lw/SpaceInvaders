import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Arcade {
	// Constants
	private static final int HEIGHT = 600;
	private static final int WIDTH = 400;
	
	// Members
	private static Player player = new Player ();
	private static Animation animation = new Title ();
	private static Collection<Enemy> enemies = new ConcurrentLinkedQueue<Enemy>();
	private static Collection<int[]> shots = new ConcurrentLinkedQueue<int[]>();
	private static int enemy_count = 0;
	
	// Getters and setters
	public static Player getPlayer () {
		return player;
	}
	
	public static Animation getAnimation () {
		return animation;
	}
	
	public static void setAnimation (Animation _animation) {
		animation = _animation;
	}
	
	public static Collection<Enemy> getEnemies () {
		return enemies;
	}
	
	public static Collection<int[]> getShots () {
		return shots;
	}
	
	public static int getEnemyCount () {
		return enemy_count;
	}
	
	public static void clear () {
		shots.clear ();
		enemies.clear ();
		enemy_count = 0;
	}
	
	// Gamefield interface
	public static int getWidth () {
		return WIDTH;
	}
	
	public static int getHeight () {
		return HEIGHT;
	}
	
	public static int getPlayerPosition () {
		return player.getPosition ();
	}
	
	public static void shoot (int x, int y, int speed) {
		if (enemy_count > 0) {
			int[] shot = new int[3];
			shot[0] = x;
			shot[1] = y;
			shot[2] = speed;
			shots.add (shot);
		}
	}
	
	public static void addEnemy (Enemy enemy) {
		enemies.add (enemy);
		enemy_count ++;
	}
	
	public static void removeEnemy (Enemy enemy) {
		enemies.remove (enemy);
		enemy_count --;
		if (enemy_count <= 0)
			setAnimation (new EndLevel ());
	}
	
	public static void killEnemy (Enemy enemy) {
		player.addPoints (enemy.getKillPoints ());
	}
	
	public static void survivedEnemy (Enemy enemy) {
		player.looseLife ();
	}
}
