package me.fainted.module.player;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.item.ItemBlock;

public class FastBreak extends Module{

	public FastBreak() {
		super("FastBreak", 0, Category.PLAYER);
	}
	
	@Override
    public void onUpdate() {
		if(!this.isToggled()) {
			return;
		}
		 mc.playerController.blockHitDelay = 0;

	        if (mc.playerController.curBlockDamageMP > 1 - 0.5f)
	            mc.playerController.curBlockDamageMP = 1;
    
    }
	
}
