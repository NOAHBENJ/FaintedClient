package me.fainted.module.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;

public class Speed extends Module{

	public Speed() {
		super("Speed", Keyboard.KEY_R, Category.MOVEMENT);
		
	}
	
	private double speedX = Fainted.instance.settingsManager.getSettingByName("S.speedX").getValDouble();
	private double speedY = Fainted.instance.settingsManager.getSettingByName("S.speedY").getValDouble();
	private double speedZ = Fainted.instance.settingsManager.getSettingByName("S.speedZ").getValDouble();
	
	@Override
	public void setup() {
		ArrayList<String> options = new ArrayList<String>();
		options.add("S.speedX");
		options.add("S.speedY");
		options.add("S.speedZ");
		Fainted.instance.settingsManager.rSetting(new Setting("S.speedX", this, speedX, 1, 20, false));
		Fainted.instance.settingsManager.rSetting(new Setting("S.speedY", this, speedY, 1, 5, false));
		Fainted.instance.settingsManager.rSetting(new Setting("S.speedZ", this, speedZ, 1, 20, false));
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	@Override
	public void onUpdate()
	{
		if(this.isToggled()) {
			// return if sneaking or not walking
			
			speedX = Fainted.instance.settingsManager.getSettingByName("S.speedX").getValDouble();
			speedY = Fainted.instance.settingsManager.getSettingByName("S.speedY").getValDouble();
			speedZ = Fainted.instance.settingsManager.getSettingByName("S.speedZ").getValDouble();
			
			if(mc.thePlayer.isSneaking()
				|| mc.thePlayer.moveForward == 0 && mc.thePlayer.
				moveStrafing == 0)
				return;
			
			// activate sprint if walking forward
			if(mc.thePlayer.moveForward > 0 && !mc.thePlayer.
					isCollidedHorizontally)
				mc.thePlayer.setSprinting(true);
			
			// activate mini jump if on ground
			if(mc.thePlayer.onGround)
			{
				mc.thePlayer.motionY += speedY / 10;
				mc.thePlayer.motionX *= speedX / 2;
				mc.thePlayer.motionZ *= speedZ / 2;
				double currentSpeed = Math.sqrt(Math.pow(mc.thePlayer.motionX, 2)
					+ Math.pow(mc.thePlayer.motionZ, 2));
				
				// limit speed to highest value that works on NoCheat+ version
				// 3.13.0-BETA-sMD5NET-b878
				// UPDATE: Patched in NoCheat+ version 3.13.2-SNAPSHOT-sMD5NET-b888
				double maxSpeed = 0.66F;
				if(currentSpeed > maxSpeed)
				{
					mc.thePlayer.motionX = mc.thePlayer.motionX / currentSpeed
							* maxSpeed;
					mc.thePlayer.motionZ = mc.thePlayer.motionZ / currentSpeed
							* maxSpeed;
				}
			}
		}
	}
	

}
