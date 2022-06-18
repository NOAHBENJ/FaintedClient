package me.fainted.util;

import java.awt.Color;

public class ColourUtil {

	public String hud = "RGB", text = "White", title = "Red";
	
	public ColourUtil(String arg0, String arg1, String arg2) {
		this.hud = arg0;
		this.text = arg1;
		this.title = arg2;
	}
	
	public ColourUtil() {
		// TODO Auto-generated constructor stub
	}

	public void setHud(String arg) {
		this.hud = arg;
	}
	public void setText(String arg) {
		this.text = arg;
	}
	public void setTitle(String arg) {
		this.title = arg;
	}
	
	public String getHud() {
		return hud;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int) (seconds * 1000)) / (float) (seconds * 1000f);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
	public static int getRainbow(float seconds, float saturation, float brightness, long index) {
		float hue = ((System.currentTimeMillis() + index) % (int) (seconds * 1000)) / (float) (seconds * 1000f);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
}
