package me.fainted.module.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Module;
import me.fainted.module.Category;
//import me.fainted.settings.NumberSetting;
//import me.fainted.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class Velocity extends Module{

	
	//public NumberSetting x = new NumberSetting("X", 0, 0, 100, 10);
	//public NumberSetting y = new NumberSetting("Y", 0, 0, 100, 10);
	
	public Velocity() {
		super("Velocity", Keyboard.KEY_Z, Category.COMBAT);
		// this.addSettings(x, y);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.motionX *=  100 / 100;
		mc.thePlayer.motionY *=  100 / 100;
		mc.thePlayer.motionZ *=  100 / 100;
	}
	
	public void onUpdate() {
		if(this.isToggled()) {
			mc.thePlayer.motionX *= 0;
			mc.thePlayer.motionY *= 0;
			mc.thePlayer.motionZ *= 0;
		}
	}
}
