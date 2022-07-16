package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module{

	public static float flyHackSpeed = 0.1F;
	
	public NoFall() {
		super("NoFall", Keyboard.KEY_N, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if(mc.thePlayer.fallDistance > 2f) {
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
			}
			super.onUpdate();
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}
