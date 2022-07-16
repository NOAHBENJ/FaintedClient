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
import net.minecraft.network.NetworkManager;

public class Velocity extends Module{

	
	//public NumberSetting x = new NumberSetting("X", 0, 0, 100, 10);
	//public NumberSetting y = new NumberSetting("Y", 0, 0, 100, 10);
	
	public Velocity() {
		super("Velocity", Keyboard.KEY_Z, Category.COMBAT);
		// this.addSettings(x, y);
	}
	
	public void onEnable() {
		
	}
	
	
}
