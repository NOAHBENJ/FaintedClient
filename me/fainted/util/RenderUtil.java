package me.fainted.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import java.awt.Color;

public class RenderUtil {

	public static void color(final double red, final double green, final double blue, final double alpha) {
		GL11.glColor4d(red, green, blue, alpha);
		    }
		public void color(final double red, final double green, final double blue) {
		color(red, green, blue, 1);
		    }
		public static void color(Color color) {
		if (color == null)
		color = Color.white;
		color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
		    }
		public void color(Color color, final int alpha) {
		if (color == null)
		color = Color.white;
		color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.5);
		    }
	
}
