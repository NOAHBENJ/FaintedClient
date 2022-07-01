package me.fainted.module.render;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.fainted.extensions.FileManager;
import me.fainted.gui.font.FontUtil;
import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.FileUtils;
import me.fainted.util.IMinecraftReference;
import me.fainted.util.UrlTextureUtil;
import me.fainted.util.UrlTextureUtil.ResourceLocationCallback;
import me.fainted.util.gui.GuiUtils;
import me.fainted.util.gui.RoundedUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class TestingRoundedRect extends Module{

	public boolean hasTriedToDownload = false;
	public ResourceLocation img = null;
	
	public TestingRoundedRect() {
		super("TestRound", Keyboard.KEY_NONE, Category.MISC);
		toggled = true;
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			ScaledResolution sr = new ScaledResolution(mc);
			int xHalf = sr.getScaledWidth() - (sr.getScaledWidth() / 2);
	        int yHalf = sr.getScaledHeight() - (sr.getScaledHeight() / 2);
	        EntityPlayer entity = mc.thePlayer;
	        float health = entity.getHealth();
	        float increment = 80 / entity.getMaxHealth();
	        
	        RoundedUtils.drawSmoothRoundedRect(xHalf + 5, yHalf + 5, xHalf + 100 - 5, yHalf + 40, 10, new Color(0, 0, 0, 170).getRGB());
	        Gui.drawHorizontalLine(xHalf + 10, xHalf + 9 + increment * health + 1, yHalf + 60 - 25 , 2, health == 20 ? 0xff00ff00 : health > 16 ? 0xffB4F718 : health > 12 ? 0xffF7F118 : health > 8 ? 0xffF7B718 : health > 4 ? 0xffEE6E04 : 0xffff0000);
	        Gui.drawHorizontalLine(sr.getScaledWidth() + 1, sr.getScaledWidth() + 2, sr.getScaledHeight() + 2 , 1, new Color(255,255,255,255).getRGB());
	        
	        FontUtil.normal.drawString(entity.getName(), xHalf + 10, yHalf + 10, 0xffffffff);
	        FontUtil.normal.drawString("Health: §c" + Integer.toString(Math.round(entity.getHealth())), xHalf + 10, yHalf + 15 + FontUtil.normal.getHeight(), 0xffffffff);
	        if(img == null && !hasTriedToDownload) {
				hasTriedToDownload = true;
				UrlTextureUtil.downloadAndSetTexture("https://minotar.net/helm/" + entity.getName().toLowerCase() + "/32.png", new ResourceLocationCallback() {
					@Override
					public void onTextureLoaded(ResourceLocation rl) {
						img = rl;
					}
				});
			}
	        
	        if(img != null) {
				GL11.glPushMatrix();
				mc.getTextureManager().bindTexture(img);
				
				Gui.drawModalRectWithCustomSizedTexture(xHalf + 100 - 35, yHalf + 60 - 52, 0,0,25,25,25,25);
				GL11.glPopMatrix();
			}
	   
		}
	}
	
}
