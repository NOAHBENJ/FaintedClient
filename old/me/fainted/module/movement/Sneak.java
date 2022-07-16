package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class Sneak extends Module{

	public static float flyHackSpeed = 0.1F;
	
	public Sneak() {
		super("Sneak", Keyboard.KEY_NONE, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDisable() {
		mc.gameSettings.keyBindSneak.pressed = false;
		super.onDisable();
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			mc.gameSettings.keyBindSneak.pressed = true;
		}
	}
	
}
