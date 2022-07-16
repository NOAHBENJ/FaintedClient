package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class AutoWalk extends Module{

	public AutoWalk() {
		super("AutoWalk", 0, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			mc.gameSettings.keyBindForward.pressed = true;
		}
	}

	@Override
	public void onDisable() {
		mc.gameSettings.keyBindForward.pressed = false;
		super.onDisable();
	}
	
}
