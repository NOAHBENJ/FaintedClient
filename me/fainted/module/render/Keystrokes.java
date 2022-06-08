package me.fainted.module.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class Keystrokes extends Module{

	public Keystrokes() {
		super("Keystrokes", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if(this.isToggled()) {
			ScaledResolution sr = new ScaledResolution(mc);
			
			int wAlpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()) ? 125 : 50);
			int aAlpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()) ? 125 : 50);
			int sAlpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()) ? 125 : 50);
			int dAlpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()) ? 125 : 50);
			
			int lAlpha = (Mouse.isButtonDown(0) ? 125 : 50);
			int rAlpha = (Mouse.isButtonDown(1) ? 125 : 50);
			
			int _alpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()) ? 125 : 50);
			int salpha = (Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.getKeyCode()) ? 125 : 50);
			
			Gui.drawRect(sr.getScaledWidth() - 29 - 29, sr.getScaledHeight() - 4 - 25 - 29 - 29, sr.getScaledWidth() - 4 - 29, sr.getScaledHeight() - 4 - 29 - 29, new Color(0, 0, 0, wAlpha).getRGB());
			Gui.drawRect(sr.getScaledWidth() - 29 - 29 - 29, sr.getScaledHeight() - 4 - 25 - 29, sr.getScaledWidth() - 4 - 29 - 29, sr.getScaledHeight() - 4 - 29, new Color(0, 0, 0, aAlpha).getRGB());
			Gui.drawRect(sr.getScaledWidth() - 29 - 29, sr.getScaledHeight() - 4 - 25 - 29, sr.getScaledWidth() - 4 - 29, sr.getScaledHeight() - 4 - 29, new Color(0, 0, 0, sAlpha).getRGB());
			Gui.drawRect(sr.getScaledWidth() - 29, sr.getScaledHeight() - 4 - 25 - 29, sr.getScaledWidth() - 4, sr.getScaledHeight() - 4 - 29, new Color(0, 0, 0, dAlpha).getRGB());
			
			Gui.drawRect(sr.getScaledWidth() - 29 - 29 - 29, sr.getScaledHeight() - 4 - 25, sr.getScaledWidth() - 4 - 29 - 29, sr.getScaledHeight() - 4, new Color(0, 0, 0, lAlpha).getRGB());
			Gui.drawRect(sr.getScaledWidth() - 29, sr.getScaledHeight() - 4 - 25, sr.getScaledWidth() - 4, sr.getScaledHeight() - 4, new Color(0, 0, 0, rAlpha).getRGB());
			
			Gui.drawRect(sr.getScaledWidth() - 29 - 29, sr.getScaledHeight() - 4 - 25, sr.getScaledWidth() - 4 - 29, sr.getScaledHeight() - 4 - 13.5f, new Color(0, 0, 0, _alpha).getRGB());
			Gui.drawRect(sr.getScaledWidth() - 29 - 29, sr.getScaledHeight() - 4 - 11.5f, sr.getScaledWidth() - 4 - 29, sr.getScaledHeight() - 4, new Color(0, 0, 0, salpha).getRGB());
			
			mc.fontRendererObj.drawString("W", sr.getScaledWidth() - 48, sr.getScaledHeight() - 49 - 29, 0xffffffff);
			mc.fontRendererObj.drawString("A", sr.getScaledWidth() - 77, sr.getScaledHeight() - 20 - 29, 0xffffffff);
			mc.fontRendererObj.drawString("S", sr.getScaledWidth() - 48.5, sr.getScaledHeight() - 20 - 29, 0xffffffff);
			mc.fontRendererObj.drawString("D", sr.getScaledWidth() - 19, sr.getScaledHeight() - 20 - 29, 0xffffffff);
			
			mc.fontRendererObj.drawString("L", sr.getScaledWidth() - 77, sr.getScaledHeight() - 20, 0xffffffff);
			mc.fontRendererObj.drawString("R", sr.getScaledWidth() - 19, sr.getScaledHeight() - 20, 0xffffffff);
			
			mc.fontRendererObj.drawString("-", sr.getScaledWidth() - 48.5, sr.getScaledHeight() - 20 - 5.8f, 0xffffffff);
			mc.fontRendererObj.drawString("^", sr.getScaledWidth() - 48.5, sr.getScaledHeight() - 12, 0xffffffff);
		}
		
	}
	
}
