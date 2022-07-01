package me.fainted.module.movement;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;

public class Flight extends Module{

	public static float flyHackSpeed = 0.1F;
	
	public Flight() {
		super("Fly", Keyboard.KEY_F, Category.MOVEMENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		super.onDisable();
	}
	
	@Override
	public void setup() {
		Fainted.instance.settingsManager.rSetting(new Setting("F.Disabler", this, false));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			mc.thePlayer.capabilities.isFlying = true;
			
			
			
			if (mc.gameSettings.keyBindJump.isPressed()) {
				mc.thePlayer.motionY += 0.2f;
			}
			
			if (mc.gameSettings.keyBindSneak.isPressed()) {
				mc.thePlayer.motionY -= 0.2f;
			}
			
			if (mc.gameSettings.keyBindForward.isPressed()) {
				if (Fainted.instance.settingsManager.getSettingByName("F.Disabler").getValBoolean())
					mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ), 1, new ItemStack(Blocks.stone.getItem(mc.theWorld, new BlockPos(1, 1, 1))), 0, 0f, 0));
				mc.thePlayer.capabilities.setFlySpeed(flyHackSpeed);
			}
			
			
			
		}
	}
	
}
