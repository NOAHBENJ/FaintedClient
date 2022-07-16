package me.fainted.module.player;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;

public class Timer extends Module {

	

	public Timer() {
		super("Timer", Keyboard.KEY_NONE, Category.PLAYER);
	}

	@Override
	public void setup() {
		Fainted.instance.settingsManager.rSetting(new Setting("T.Timer", this, 1, 0.1, 5, false));
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			double val = Fainted.instance.settingsManager.getSettingByName("T.Timer").getValDouble();
			
			net.minecraft.util.Timer.timerSpeed = (float)val;
		}
	}
	@Override
	public void onDisable(){
		net.minecraft.util.Timer.timerSpeed = 1.0f;
	}

}