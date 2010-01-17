import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class EndLevel extends Animation {
	private int level;
	private int count = 0;
	
	private static final int DELAY = 40;
	
	// Constructor
	public EndLevel () {
		level = LevelManager.getLevel ();
		
		createFont ();
		startFadeIn ();
	}
	
	// Draw
	public void paint (Graphics2D g) {
		count ++;
		
		if (count >= DELAY + 2 * FADE_TIME)
			if (LevelManager.isLastLevel ())
				Arcade.setAnimation (new EndGame ());
			else
				Arcade.setAnimation (new StartLevel ());
		
		if (count == DELAY + FADE_TIME)
			startFadeOut ();
				
		int alpha = getFadeAlpha ();
		
		g.setColor (new Color (237, 212, 0, alpha));
		g.setFont (big_font);
		
		int top = 225 - getBigFontHeight (g, "LEVEL "+level) / 2;
		int left = 200 - getBigFontWidth (g, "LEVEL "+level) / 2;
		g.drawString ("LEVEL "+level, left, top);
		top = 275 - getBigFontHeight (g, "CLEARED") / 2;
		left = 200 - getBigFontWidth (g, "CLEARED") / 2;
		g.drawString ("CLEARED", left, top);
	}
	
	public void enterPressed () {
		// Do nothing...
	}
}
