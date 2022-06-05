package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class Jetpack extends Module{

	public Jetpack() {
		super("Jetpack", 0, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.gameSettings.keyBindJump.pressed) {
				mc.thePlayer.jump();
			}
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
