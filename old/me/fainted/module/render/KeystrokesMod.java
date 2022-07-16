package me.fainted.module.render;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.ColourUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class KeystrokesMod extends Module{

	/* 
	 * This code was written all by NOAHBENJ (unlike the rest of the client which is a skid)
	 * This is all mine - its free to use, I would like some credit tho - no need to be open source
	 * Just credit me in the files or like in a credits section. Thanks, -Noah.
	 * @author NOAHBENJ
	 * */
	
	private FontRenderer fr = mc.fontRendererObj;
	private ScaledResolution sr = new ScaledResolution(mc);
	
	private int[][] poss = {
			{sr.getScaledWidth() - 70, sr.getScaledHeight() - (sr.getScaledHeight() - 5), sr.getScaledWidth() - 45, 30},
			{sr.getScaledWidth() - 70, sr.getScaledHeight() - (sr.getScaledHeight() - 5), sr.getScaledWidth() - 45, 55},
			{sr.getScaledWidth() - 70, sr.getScaledHeight() - (sr.getScaledHeight() - 5), sr.getScaledWidth() - 45, 80},
			{sr.getScaledWidth() - 70, sr.getScaledHeight() - (sr.getScaledHeight() - 5), sr.getScaledWidth() - 45, 105},
	};
	
	public KeystrokesMod() {
		super("Keystrokes", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			FontRenderer fr = mc.fontRendererObj;
			ScaledResolution sr = new ScaledResolution(mc);
			
			// TODO: Gui.drawRect(whereTheStartIs, whereTheEndIs, whereTheTopIs, whereTheBottomEnds, Colour(Hexadecimal LONG form eg 0x00000000))
			
			int colour = 0x90000000, colourPressed = 0x90ffffff;
			
			if (mc.gameSettings.keyBindForward.isKeyDown())  {
				Gui.drawRect(poss[0][0], poss[0][1], poss[0][2], poss[0][3], colourPressed);// W
			} else {
				Gui.drawRect(poss[0][0], poss[0][1], poss[0][2], poss[0][3], 0x90000000);// W
			}
			if (mc.gameSettings.keyBindLeft.isKeyDown())  {
				Gui.drawRect(poss[1][0], poss[1][1], poss[1][2], poss[1][3], colourPressed);// A
			} else {
				Gui.drawRect(poss[1][0], poss[1][1], poss[1][2], poss[1][3], colour);// A
			}
			if (mc.gameSettings.keyBindBack.isKeyDown())  {
				Gui.drawRect(poss[2][0], poss[2][1], poss[2][2], poss[2][3], colourPressed);// S
			} else {
				Gui.drawRect(poss[2][0], poss[2][1], poss[2][2], poss[2][3], colour);// S
			}
			if (mc.gameSettings.keyBindRight.isKeyDown())  {
				Gui.drawRect(poss[3][0], poss[3][1], poss[3][2], poss[3][3], colourPressed);// D
			} else {
				Gui.drawRect(poss[3][0], poss[3][1], poss[3][2], poss[3][3], colour);// D
			}
			Gui.drawRect(sr.getScaledWidth() - 5, sr.getScaledHeight() - (sr.getScaledHeight() - 5), sr.getScaledWidth() - 5, 30, colourPressed);
		
			
			fr.drawStringWithShadow("Keystrokes", 10, 10 * 16, -1);
				
				
			
			
		}
	}
	
}
