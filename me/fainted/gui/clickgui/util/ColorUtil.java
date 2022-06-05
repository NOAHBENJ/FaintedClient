package me.fainted.gui.clickgui.util;

import java.awt.Color;

//Deine Imports
import me.fainted.Fainted;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int)Fainted.instance.settingsManager.getSettingByName("GuiRed").getValDouble(), (int)Fainted.instance.settingsManager.getSettingByName("GuiGreen").getValDouble(), (int)Fainted.instance.settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
