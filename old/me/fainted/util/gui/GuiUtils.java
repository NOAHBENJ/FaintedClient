package me.fainted.util.gui;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;

import me.fainted.extensions.FileManager;
import me.fainted.util.IMinecraftReference;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;

public class GuiUtils {

	public static GuiUtils getInstance() {
		return new GuiUtils();
	}
	
	//ResourceLocation headLocation = GuiUtils.getHeadLocation(IMinecraftReference.minecraft.getSession().getUsername());
    //IMinecraftReference.minecraft.getTextureManager().bindTexture(headLocation);
    //Gui.drawModalRectWithCustomSizedTexture(12, 9, 0,0,12,12, 12, 12);
	
	public static ThreadDownloadImageData getSkinHead(String name) {
		return new ThreadDownloadImageData( null, "https://minotar.net/helm/" + name + "/32.png", new ResourceLocation( "textures/entity/steve.png" ), null );
		
	}
	
	private static final Map<String, ResourceLocation> playerSkins = new HashMap<>();
	
	  public static ResourceLocation getHeadLocation(String displayName) {
	        ResourceLocation playerSkin = playerSkins.getOrDefault( displayName, new ResourceLocation( "textures/entity/" + displayName + ".png" ) );
	        if (!playerSkins.containsKey( displayName )) {
	            ThreadDownloadImageData skinData = new ThreadDownloadImageData( new File(FileManager.ROOT_DIR + "textures/entity/"), "https://minotar.net/helm/" + displayName + "/32.png", new ResourceLocation( "textures/entity/steve.png" ), null );
	            IMinecraftReference.minecraft.getTextureManager().loadTexture( playerSkin, skinData );
	            playerSkins.put( displayName, playerSkin );
	            SkinManager skinManager = Minecraft.getMinecraft().getSkinManager();
	            
	        }
	        return playerSkin;
	    } 
	
	public void roundedRect(final double x, final double y, double width, double height, final double edgeRadius, final Color color) {
        final double halfRadius = edgeRadius / 2;
        width -= halfRadius;
        height -= halfRadius;

        float sideLength = (float) edgeRadius;
        sideLength /= 2;
        start();
        if (color != null)
            color(color);
        begin(GL11.GL_TRIANGLE_FAN);

        {
            for (double i = 180; i <= 270; i++) {
                final double angle = i * (Math.PI * 2) / 360;
                vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
            }
            vertex(x + sideLength, y + sideLength);
        }

        end();
        stop();

        sideLength = (float) edgeRadius;
        sideLength /= 2;
        start();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        begin(GL11.GL_TRIANGLE_FAN);

        {
            for (double i = 0; i <= 90; i++) {
                final double angle = i * (Math.PI * 2) / 360;
                vertex(x + width + (sideLength * Math.cos(angle)), y + height + (sideLength * Math.sin(angle)));
            }
            vertex(x + width, y + height);
        }

        end();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        stop();

        sideLength = (float) edgeRadius;
        sideLength /= 2;
        start();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        begin(GL11.GL_TRIANGLE_FAN);

        {
            for (double i = 270; i <= 360; i++) {
                final double angle = i * (Math.PI * 2) / 360;
                vertex(x + width + (sideLength * Math.cos(angle)), y + (sideLength * Math.sin(angle)) + sideLength);
            }
            vertex(x + width, y + sideLength);
        }

        end();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        stop();

        sideLength = (float) edgeRadius;
        sideLength /= 2;
        start();
        if (color != null)
            color(color);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        begin(GL11.GL_TRIANGLE_FAN);

        {
            for (double i = 90; i <= 180; i++) {
                final double angle = i * (Math.PI * 2) / 360;
                vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + height + (sideLength * Math.sin(angle)));
            }
            vertex(x + sideLength, y + height);
        }

        end();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        stop();

        // Main block
        rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);

        // Horizontal bars
        rect(x, y + halfRadius, edgeRadius / 2, height - halfRadius, color);
        rect(x + width, y + halfRadius, edgeRadius / 2, height - halfRadius, color);

        // Vertical bars
        rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
        rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
    }
	

    public void start() {
        enable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        disable(GL11.GL_TEXTURE_2D);
        disable(GL11.GL_CULL_FACE);
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
    }

    public void stop() {
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        enable(GL11.GL_CULL_FACE);
        enable(GL11.GL_TEXTURE_2D);
        disable(GL11.GL_BLEND);
        color(Color.white);
    }
    
    public void enable(final int glTarget) {
        GL11.glEnable(glTarget);
    }

    public void disable(final int glTarget) {
        GL11.glDisable(glTarget);
    }
    
    public void color(final double red, final double green, final double blue, final double alpha) {
        GL11.glColor4d(red, green, blue, alpha);
    }

    public void color(final double red, final double green, final double blue) {
        color(red, green, blue, 1);
    }

    public void color(Color color) {
        if (color == null)
            color = Color.white;
        color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public void color(Color color, final int alpha) {
        if (color == null)
            color = Color.white;
        color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.5);
    }

    public void lineWidth(final double width) {
        GL11.glLineWidth((float) width);
    }

    public static void rect(final double x, final double y, final double width, final double height, final boolean filled, final Color color) {
    	getInstance().start();
        if (color != null)
        	getInstance().color(color);
        getInstance().begin(filled ? GL11.GL_TRIANGLE_FAN : GL11.GL_LINES);

        {
        	getInstance().vertex(x, y);
        	getInstance().vertex(x + width, y);
        	getInstance().vertex(x + width, y + height);
        	getInstance().vertex(x, y + height);
            if (!filled) {
            	getInstance().vertex(x, y);
            	getInstance().vertex(x, y + height);
            	getInstance().vertex(x + width, y);
            	getInstance().vertex(x + width, y + height);
            }
        }
        getInstance().end();
        getInstance().stop();
    }

    public void begin(final int glMode) {
        GL11.glBegin(glMode);
    }
    public void end() {
        GL11.glEnd();
    }
    public void vertex(final double x, final double y) {
        GL11.glVertex2d(x, y);
    }
    
    public void rect(final double x, final double y, final double width, final double height, final boolean filled) {
        rect(x, y, width, height, filled, null);
    }

    public void rect(final double x, final double y, final double width, final double height, final Color color) {
        rect(x, y, width, height, true, color);
    }

    public void rect(final double x, final double y, final double width, final double height) {
        rect(x, y, width, height, true, null);
    }

	
}
