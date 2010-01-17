import java.io.File;
import java.io.FileInputStream;

import java.awt.Graphics2D;
import java.awt.Font;

public abstract class Animation {
	// Load custom font
	protected Font big_font;
	protected Font small_font;

	protected void createFont () {
		try {
			File file = new File ("unifont.ttf");
			FileInputStream fis = new FileInputStream (file);
			big_font = Font.createFont (Font.TRUETYPE_FONT, fis).deriveFont (64f);
			small_font = big_font.deriveFont (32f);
		}
		catch (java.io.IOException e) {
			System.out.println ("I/O error");
			big_font = new Font ("Monospaced", Font.PLAIN, 54);
			small_font = big_font.deriveFont (Font.PLAIN, 24);
		}
		catch (java.awt.FontFormatException e) {
			System.out.println ("File \"unifont.ttf\" not valid");
			big_font = new Font ("Monospaced", Font.PLAIN, 54);
			small_font = big_font.deriveFont (Font.PLAIN, 24);
		}
	}
	
	// Get String bounds
	protected int getBigFontWidth (Graphics2D g, String str) {
		return (int) big_font.getStringBounds (str, g.getFontRenderContext ()).getWidth ();
	}
	
	protected int getSmallFontWidth (Graphics2D g, String str) {
		return (int) small_font.getStringBounds (str, g.getFontRenderContext ()).getWidth ();
	}
	
	protected int getBigFontHeight (Graphics2D g, String str) {
		return (int) big_font.getStringBounds (str, g.getFontRenderContext ()).getHeight ();
	}
	
	protected int getSmallFontHeight (Graphics2D g, String str) {
		return (int) small_font.getStringBounds (str, g.getFontRenderContext ()).getHeight ();
	}
	
	// Fading
	public static final int FADE_TIME = 20;
	private short fading = 0;
	private int fade_count = FADE_TIME;
	
	protected void startFadeOut () {
		fading = -1;
		fade_count = FADE_TIME;
	}
	
	protected void startFadeIn () {
		fading = 1;
		fade_count = 0;
	}
	
	protected int getFadeAlpha () {
		if (fade_count + fading >= 0 && fade_count + fading <= FADE_TIME)
			fade_count += fading;
		return fade_count * 255 / FADE_TIME;
	}
	
	public abstract void paint (Graphics2D g);
	
	public abstract void enterPressed ();
}
