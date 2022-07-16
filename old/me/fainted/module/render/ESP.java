package me.fainted.module.render;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
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
	}
	
	@Override
	public void setup() {
		Fainted.instance.settingsManager.rSetting(new Setting("E.Chest", this, true));
		Fainted.instance.settingsManager.rSetting(new Setting("E.Player", this, true));
		Fainted.instance.settingsManager.rSetting(new Setting("E.Animal", this, true));
		Fainted.instance.settingsManager.rSetting(new Setting("E.Mob", this, true));
	}
	
	@Override
	public void onRender() {
		boolean chestToggled = Fainted.instance.settingsManager.getSettingByName("E.Chest").getValBoolean();
		boolean playerToggled = Fainted.instance.settingsManager.getSettingByName("E.Player").getValBoolean();
		boolean animalToggled = Fainted.instance.settingsManager.getSettingByName("E.Animal").getValBoolean();
		boolean mobToggled = Fainted.instance.settingsManager.getSettingByName("E.Mob").getValBoolean();
		
		if (this.isToggled()) {
			for(Object o : mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntityChest && chestToggled && o != mc.thePlayer) {
					ChestESPUtils.blockESPBox(((TileEntityChest)o).getPos());
				}
			}
			for(Object e : mc.theWorld.loadedEntityList) {
				if(e instanceof EntityPlayer && playerToggled && e != mc.thePlayer) {
					MobESPUtils.entityESPBox(((Entity)e), 0);
				}
			}
			for(Object h : mc.theWorld.loadedEntityList) {
				if(h instanceof EntityMob && animalToggled && h != mc.thePlayer) {
					MobESPUtils.entityESPBox(((Entity)h), 0);
				}
			}
			for(Object f : mc.theWorld.loadedEntityList) {
				if(f instanceof EntityAnimal && mobToggled && f != mc.thePlayer) {
					MobESPUtils.entityESPBox(((Entity)f), 0);
				}
			}
		}
	}

}
