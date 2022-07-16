package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.entity.Entity;

public class Parkour extends Module{

	public Parkour() {
		super("Parkour", 0, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.thePlayer.onGround && !mc.thePlayer.isSneaking() && !this.mc.gameSettings.keyBindSneak.pressed &&
					this.mc.theWorld.getCollidingBoundingBoxes((Entity)mc.thePlayer, mc.thePlayer.getEntityBoundingBox().offset(0.0D, -0.5D, 0.0D).expand(-0.01D, 0.0D, -0.01D)).isEmpty()) {
				mc.thePlayer.jump();
			}
		}
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
