package me.fainted.module.render;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class FullBright extends Module{

	public FullBright() {
		super("FullBright", Keyboard.KEY_M, Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
	}
	
}
