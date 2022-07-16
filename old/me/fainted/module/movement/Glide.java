package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Glide extends Module{
	
	public Glide() {
		super("Glide", Keyboard.KEY_NONE, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		double oldY = mc.thePlayer.motionY;
		float oldJ = mc.thePlayer.jumpMovementFactor;
		if (this.isToggled()) {
			if((mc.thePlayer.motionY < 0.0D) 
				&& (mc.thePlayer.isAirBorne) 
				&& (!mc.thePlayer.isInWater()) 
				&& (!mc.thePlayer.isOnLadder()) 
				&& (!mc.thePlayer.isInLava())) {
					mc.thePlayer.motionY = -.125D;
					mc.thePlayer.jumpMovementFactor *= 1.12337f;
			}
		} else {
			mc.thePlayer.motionY = oldY;
			mc.thePlayer.jumpMovementFactor = oldJ;
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
}
