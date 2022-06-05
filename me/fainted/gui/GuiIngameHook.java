package me.fainted.gui;

import me.fainted.Fainted;
import me.fainted.module.Module;
import me.fainted.util.ColourUtil;
import me.fainted.util.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class GuiIngameHook extends GuiIngame{

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}

	public void renderGameOverlay(float p_175180_1_) {
		super.renderGameOverlay(p_175180_1_);
		if (!mc.gameSettings.showDebugInfo) {
		
			drawRect(2, 2, 95, 14, 0x80000000);
			Wrapper.fr.drawString("Fainted: ", 4, 4, 0x3300FF);
			Wrapper.fr.drawString("FPS: " + mc.getDebugFPS(), 50, 4, -1);
			
			renderArrayList();
		}
	}
	
	public void renderArrayList() {
		int yCount = 10;
		int index = 0;
		long x = 0;
		for(Module m : Fainted.instance.moduleManager.getModules()) {
			m.onRender();
			
			if (m.isToggled()) {
				Wrapper.fr.drawString(m.getName(), 5, yCount + 5, ColourUtil.rainbowEffect(index + x*2000000000L, 1.0F).getRGB());
				yCount += 10;
				index++;
				x++;
			}
		}
	}
	
}
