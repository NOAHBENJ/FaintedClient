package me.fainted.module.render;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.ESP.ChestESPUtils;
import me.fainted.util.ESP.MobESPUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class ESP extends Module{

	
	
	public ESP() {
		super("ESP", Keyboard.KEY_C, Category.RENDER);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			for(Object o : mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntityChest ) {
					ChestESPUtils.blockESPBox(((TileEntityChest)o).getPos());
				}
			}
			/*for(Object e : mc.theWorld.loadedTileEntityList) {
				if(e instanceof EntityPlayer) {
					MobESPUtils.entityESPBox(((Entity)e), 0);
				}
			}
			for(Object h : mc.theWorld.loadedTileEntityList) {
				if(h instanceof EntityMob) {
					MobESPUtils.entityESPBox(((Entity)h), 0);
				}
			}
			for(Object f : mc.theWorld.loadedTileEntityList) {
				if(f instanceof EntityAnimal) {
					MobESPUtils.entityESPBox(((Entity)f), 0);
				}
			} */ 
		}
	}

}
