package me.fainted;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import me.fainted.alt.AltManager;
import me.fainted.events.Event;
import me.fainted.extensions.DiscordRP;
import me.fainted.extensions.FileManager;
import me.fainted.gui.HUD;
import me.fainted.gui.clickgui.ClickGUI;
import me.fainted.gui.settings.SettingsManager;
import me.fainted.module.Module;
import me.fainted.module.ModuleManager;
import me.fainted.module.combat.KillAura;
import me.fainted.util.ColourUtil;
import me.fainted.util.PlayerUtils;
import me.fainted.util.gui.GuiUtils;
import me.fainted.gui.font.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Fainted {

	public static Fainted instance = new Fainted();
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static String name = "FaintedClient", version = "b1.0", creator = "NOAHBENJ";
	public static String clientStr = name + " " + version + " by " + creator;
			
	public static SettingsManager settingsManager;
	public static GuiUtils guiUtils;
	public static ModuleManager moduleManager;
	public static ColourUtil colourUtil;
	public static HUD hud;
	public static ClickGUI clickGUI;
	public static AltManager altManager;
	public static FileManager fileManager;
	public static PlayerUtils playerUtils;
	public static ArrayList<Module> modules;
	
	
	public static DiscordRP discordRP = new DiscordRP();
	
	public static void stopClient() {
		Fainted.instance.fileManager.saveMods();
	}
	
	public static void onEvent(Event e) {
		for (Module m : moduleManager.getModules()) {
			if (!m.toggled) {
				return;
			}
			m.onEvent(e);
		}
	}
	
	public static void startClient() {
		settingsManager = new SettingsManager();
		guiUtils = new GuiUtils();
		moduleManager = new ModuleManager();
		colourUtil = new ColourUtil();
		modules = moduleManager.getModules();
		hud = new HUD();
		clickGUI = new ClickGUI();
		altManager = new AltManager();
		fileManager = new FileManager();
		playerUtils = new PlayerUtils();
		
		FontUtil.bootstrap();
		
		fileManager.init();
		discordRP.start();
		Display.setTitle(clientStr);
	}

	public static DiscordRP getDiscordRP() {
		return discordRP;
		
	} 
	
	public static void addChatMessage(String message) {
		message = "\247c[" +  name + "]\2477: " + message;
		mc.thePlayer.addChatMessage(new ChatComponentText(message));
	}

	
}
