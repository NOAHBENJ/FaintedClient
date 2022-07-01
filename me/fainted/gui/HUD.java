package me.fainted.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;

import me.fainted.Fainted;
import me.fainted.gui.font.FontUtil;
import me.fainted.module.Module;
import me.fainted.util.ColourUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class HUD {
	Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			if (FontUtil.normal.getStringWidth(arg0.name) > FontUtil.normal.getStringWidth(arg1.name)) {
				return -1;
			}
			if (FontUtil.normal.getStringWidth(arg1.name) > FontUtil.normal.getStringWidth(arg0.name)) {
				return 1;
			}

			
			return 0;
		}
		
	}
	
	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		Collections.sort(Fainted.modules, new ModuleComparator());
		int count = 0;
		for (Module m : Fainted.instance.moduleManager.getModules()) {
			if (!m.isToggled()) {
				continue;
			}
			
			int color = 0;
			int tcolor = 0;
			ColourUtil colourUtil = Fainted.instance.colourUtil;
			double offset = count * (FontUtil.normal.getHeight() + 5);
			
			String cc = colourUtil.getHud();
			String tc = colourUtil.getText();
			if (cc.equals("rgb")) { color = ColourUtil.getRainbow(4, 1, 1, count * 150); } 
			if (cc.equals("white")) { color = 0xffffffff; }
			if (cc.equals("red")) { color = 0xffff0000; }
			if (cc.equals("green")) { color = 0xff00ff00; }
			if (cc.equals("blue")) { color = 0xff0000ff; }
			if (cc.equals("black")) { color = 0xff000000; } 
			if (tc.equals("rgb")) { tcolor = ColourUtil.getRainbow(4, 1, 1, count * 150); } 
			if (tc.equals("white")) { tcolor = 0xffffffff; }
			if (tc.equals("red")) { tcolor = 0xffff0000; }
			if (tc.equals("green")) { tcolor = 0xff00ff00; }
			if (tc.equals("blue")) { tcolor = 0xff0000ff; }
			if (tc.equals("black")) { tcolor = 0xff000000; } 
			
			//System.out.println(colourUtil.getHudToggled());
			//System.out.println(m.name.equals("KillAura") ? m.getDisplaySetting() : "n");
			if (colourUtil.getHudToggled()) {
				Gui.drawRect(sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 10, offset, sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 8, 5 + FontUtil.normal.getHeight() +  offset, color);
				Gui.drawRect(sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 8, offset, sr.getScaledWidth(), 5 + FontUtil.normal.getHeight() +  offset, 0x90000000);
				FontUtil.normal.drawStringWithShadow(m.getName(), sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 4, 3 + offset, tcolor);
			} else {
				if (m.displaySetting != null) {
					FontUtil.normal.drawStringWithShadow(m.getName(), sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName() + "" + m.displaySetting) - 4, 3 + offset, tcolor);
					FontUtil.normal.drawStringWithShadow(m.displaySetting, sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.displaySetting + " "), 3 + offset, 0xffffffff);
					
					
				} else {
					FontUtil.normal.drawStringWithShadow(m.getName(), sr.getScaledWidth() - FontUtil.normal.getStringWidth(m.getName()) - 4, 3 + offset, tcolor);
					
				}
			}

			count++;
			
		}
	}
	
}
