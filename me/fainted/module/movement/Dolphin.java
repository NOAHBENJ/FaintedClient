package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class Dolphin extends Module{

	public Dolphin() {
		super("Dolphin", 0, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if(mc.thePlayer.isInWater()) {
				mc.thePlayer.motionY += 0.04F;
			}
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
