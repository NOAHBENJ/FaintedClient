package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnable() {
		mc.thePlayer.setSprinting(true);
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(false);
	}
	
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.thePlayer.moveForward > 0 && !mc.thePlayer.isUsingItem() && !mc.thePlayer.isSneaking() && !mc.thePlayer.isCollidedHorizontally) {
				mc.thePlayer.setSprinting(true);
			} else {
				mc.thePlayer.setSprinting(false);
			}
		}
	}
}
