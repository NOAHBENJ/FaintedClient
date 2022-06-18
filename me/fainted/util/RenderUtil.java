package me.fainted.util;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;

import net.minecraft.client.renderer.GlStateManager;

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
		
		public static void enable(final int glTarget) {
	        GL11.glEnable(glTarget);
	    }

	    public static void disable(final int glTarget) {
	        GL11.glDisable(glTarget);
	    }
		
		public static void start() {
	        enable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        disable(GL11.GL_TEXTURE_2D);
	        disable(GL11.GL_CULL_FACE);
	        GlStateManager.disableAlpha();
	        GlStateManager.disableDepth();
	    }
		
		public static void lineWidth(final double width) {
		        GL11.glLineWidth((float) width);
		}
		
		public static void begin(final int glMode) {
	        GL11.glBegin(glMode);
	    }

	    public static void end() {
	        GL11.glEnd();
	    }

	    public static void vertex(final double x, final double y) {
	        GL11.glVertex2d(x, y);
	    }
		
	    public static void stop() {
	        GlStateManager.enableAlpha();
	        GlStateManager.enableDepth();
	        enable(GL11.GL_CULL_FACE);
	        enable(GL11.GL_TEXTURE_2D);
	        disable(GL11.GL_BLEND);
	        color(Color.white);
	    }
	    
		public static void lineNoGl(final double firstX, final double firstY, final double secondX, final double secondY, final Color color) {

	        start();
	        if (color != null)
	            color(color);
	        RenderUtil.lineWidth(1);
	        GL11.glEnable(GL11.GL_LINE_SMOOTH);
	        begin(GL11.GL_LINES);
	        {
	            vertex(firstX, firstY);
	            vertex(secondX, secondY);
	        }
	        end();
	        GL11.glDisable(GL11.GL_LINE_SMOOTH);
	        stop();
	    }

	
}
