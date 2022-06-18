package me.fainted.module.misc;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.ColourUtil;

public class ColourUtilModule extends Module{

	String hudColour;
	String textColour;
	String titleColour;
	
	public ColourUtilModule() {
		super("ColourUtil", Keyboard.KEY_NONE, Category.MISC);
	}
	
	@Override
	public void setup() {
		ArrayList<String> hudOptions = new ArrayList<>();
		hudOptions.add("RGB");
		hudOptions.add("White");
		hudOptions.add("Red");
		hudOptions.add("Green");
		hudOptions.add("Blue");
        Fainted.instance.settingsManager.rSetting(new Setting("CU.HUDColour", this, "rgb", hudOptions));
        
        ArrayList<String> textOptions = new ArrayList<>();
        textOptions.add("RGB");
        textOptions.add("White");
        textOptions.add("Red");
        textOptions.add("Green");
        textOptions.add("Blue");
        Fainted.instance.settingsManager.rSetting(new Setting("CU.TextColour", this, "white", textOptions));
        
        ArrayList<String> titleOptions = new ArrayList<>();
        titleOptions.add("RGB");
        titleOptions.add("White");
        titleOptions.add("Red");
        titleOptions.add("Green");
        titleOptions.add("Blue");
        Fainted.instance.settingsManager.rSetting(new Setting("CU.TitleColour", this, "rgb", titleOptions));
	}
	
	@Override
	public void onRender() {
		
		hudColour = Fainted.instance.settingsManager.getSettingByName("CU.HUDColour").getValString();
		textColour = Fainted.instance.settingsManager.getSettingByName("CU.TextColour").getValString();
		titleColour = Fainted.instance.settingsManager.getSettingByName("CU.TitleColour").getValString();
		//new ColourUtil(hudColour, textColour, titleColour);
		
		Fainted.instance.colourUtil.setHud(hudColour);
		Fainted.instance.colourUtil.setTitle(titleColour);
		Fainted.instance.colourUtil.setText(textColour);
	}

}
