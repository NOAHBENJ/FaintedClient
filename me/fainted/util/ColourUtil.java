package me.fainted.util;

import java.awt.Color;

public class ColourUtil {

	public static Color rainbowEffect(long offset, float fade) {
		float hue = (float) (System.nanoTime() + offset) / 1.0E10F % 1.0f;
		long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0f, 1.0f)).intValue()), 16);;
		Color c = new Color((int) color);
		return new Color(c.getRed()/255.0F*fade, c.getGreen()/255.0F*fade, c.getBlue()/255.0F*fade, c.getAlpha()/255.0F);
	}
	
}
