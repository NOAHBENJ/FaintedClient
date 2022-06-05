package me.fainted;

import org.lwjgl.opengl.Display;

import me.fainted.alt.AltManager;
import me.fainted.extensions.DiscordRP;
import me.fainted.extensions.FileManager;
import me.fainted.gui.clickgui.ClickGUI;
import me.fainted.gui.settings.SettingsManager;
import me.fainted.module.ModuleManager;

public class Fainted {

	public static Fainted instance = new Fainted();
	
	public static String name = "FaintedClient", version = "b1.0", creator = "NOAHBENJ";
	public static String clientStr = name + " " + version + " by " + creator;
			
	public static SettingsManager settingsManager;
	public static ModuleManager moduleManager;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static FileManager fileManager;
	
	public static DiscordRP discordRP = new DiscordRP();
	
	public static void stopClient() {
		Fainted.instance.fileManager.saveMods();
	}
	
	public static void startClient() {
		settingsManager = new SettingsManager();
		moduleManager = new ModuleManager();
		clickGUI = new ClickGUI();
		altManager = new AltManager();
		fileManager = new FileManager();
		
		
		
		fileManager.init();
		discordRP.start();
		Display.setTitle(clientStr);
	}

	public static DiscordRP getDiscordRP() {
		return discordRP;
		
	} 
	
}
