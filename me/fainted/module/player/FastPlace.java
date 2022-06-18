package me.fainted.module.player;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.item.ItemBlock;

public class FastPlace extends Module{

	public FastPlace() {
		super("FastPlace", 0, Category.PLAYER);
	}
	
	@Override
    public void onUpdate() {
		if(!this.isToggled()) {
			return;
		}
        if (!(mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock))
            return;
        int delay = 0;
        if (mc.rightClickDelayTimer > delay) {
            mc.rightClickDelayTimer = delay;
        }
    }
}
