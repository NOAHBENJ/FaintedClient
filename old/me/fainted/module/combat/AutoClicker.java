package me.fainted.module.combat;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.google.common.eventbus.Subscribe;

import me.fainted.module.Category;
import me.fainted.module.Module;
//import me.fainted.settings.NumberSetting;
//import me.fainted.util.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class AutoClicker extends Module{
	
	private long lastClick;
	private long hold;
	
	private double speed;
	private double holdLength;
	private double min;
	private double max;
	
	//private final NumberSetting minCps = new NumberSetting("Min CPS", 10, 1, 20, 1);
    //private final NumberSetting maxCps = new NumberSetting("Max CPS", 14, 1, 20, 1);
	
	 public AutoClicker(){
	        super("AutoClicker", Keyboard.KEY_NONE, Category.COMBAT);
	        //his.addSettings(minCps,maxCps);
	    }

	 
	@Override
	public void onUpdate() {
		if(this.isToggled()) {
		
			if (Mouse.isButtonDown(0)) {
				
				if (System.currentTimeMillis() - lastClick > speed * 1000) {
					lastClick = System.currentTimeMillis();
					if (hold < lastClick) {
						hold = lastClick;
					}
					int key = mc.gameSettings.keyBindAttack.getKeyCode();
					KeyBinding.setKeyBindState(key, true);
					KeyBinding.onTick(key);
					this.updateVals();
				} else if (System.currentTimeMillis() - hold > holdLength * 1000) {
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindAttack.getKeyCode(), false);
					this.updateVals();
				}
			}
			else if (Mouse.isButtonDown(1)) {
				
				if (System.currentTimeMillis() - lastClick > speed * 1000) {
					lastClick = System.currentTimeMillis();
					if (hold < lastClick) {
						hold = lastClick;
					}
					int key = mc.gameSettings.keyBindUseItem.getKeyCode();
					KeyBinding.setKeyBindState(key, true);
					KeyBinding.onTick(key);
					this.updateVals();
				} else if (System.currentTimeMillis() - hold > holdLength * 1000) {
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindUseItem.getKeyCode(), false);
					this.updateVals();
				}
			}
		}
	}
	
	
	public void onEnable() {
		
	}
	
	private void updateVals() {
		min = 14;
		max = 20;
		
		if (min >= max) {
			max = min + 1;
		}
		
		speed = 1.0 / ThreadLocalRandom.current().nextDouble(min - 0.2, max);
		holdLength = speed / ThreadLocalRandom.current().nextDouble(min, max);
	}
	 
}
