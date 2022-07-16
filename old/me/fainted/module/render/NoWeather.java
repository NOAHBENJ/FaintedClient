package me.fainted.module.render;

import me.fainted.module.Category;
import me.fainted.module.Module;

public class NoWeather extends Module{

	public NoWeather() {
		super("NoWeather", 0, Category.RENDER);
	}
	
	@Override
    public void onUpdate() {
		if(!this.isToggled())
			return;
		
        mc.theWorld.setThunderStrength(0);
        mc.theWorld.setRainStrength(0);
    }
	
}
