package me.fainted.module.combat;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.GuiIngameHook;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.Timer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class KillAura extends Module{ 

	private GuiIngameHook hook = new GuiIngameHook(mc);
	public Timer timer = new Timer();
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_K, Category.COMBAT);
	}
	
	@Override
    public void setup() {
        Fainted.instance.settingsManager.rSetting(new Setting("Reach", this, 3, 2, 8, true));
        Fainted.instance.settingsManager.rSetting(new Setting("APS", this, 13, 10, 20, true));
    }
	
	public void onEnable() {
		//Fainted.addChatMessage("Toggled KillAura");
	}
	
	  @Override
	    public void onUpdate() {
	     
	        if(!this.isToggled())
	            return;
	       
	        for(Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
	            Object theObject = entities.next();
	            if(theObject instanceof EntityLivingBase) {
	                EntityLivingBase entity = (EntityLivingBase) theObject;
	               
	                if(entity instanceof EntityPlayerSP) continue;
	               
	                if(mc.thePlayer.getDistanceToEntity(entity) <= 5F) {
	                    if(entity.isEntityAlive()) {
	                        mc.playerController.attackEntity(mc.thePlayer, entity);
	                        mc.thePlayer.swingItem();
	                        continue;
	                    }
	                }
	            }
	        }
	        
	        
	       
	        super.onUpdate();
	    }
	
	
}
