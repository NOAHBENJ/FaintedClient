package me.fainted.module.render;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class TargetHUD extends Module {

	FontRenderer fr = mc.fontRendererObj;
	
	public TargetHUD() {
		super("TargetHUDM", 0, Category.RENDER);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			EntityLivingBase target = (EntityLivingBase) mc.pointedEntity;
			ScaledResolution sr = new ScaledResolution(mc);
			if (target == null) 
				return;
			
			int halfWidth = sr.getScaledWidth() - (sr.getScaledWidth() / 2);
			int halfHeight = sr.getScaledHeight() - (sr.getScaledHeight() / 2);
			
			fr.drawStringWithShadow(target.getName(), halfWidth + 2, halfHeight + 2, -1);
			fr.drawStringWithShadow(Math.round(target.getHealth()) + " \u2764", halfWidth + 2, halfHeight + 12, -1);
			GuiInventory.drawEntityOnScreen(halfWidth + fr.getStringWidth(target.getName()) * ((int)1.25f) + 20, halfHeight + 15, 10, 50, 50, target);
		}
		
	}


}
