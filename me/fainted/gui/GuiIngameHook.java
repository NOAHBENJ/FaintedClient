package me.fainted.gui;

import java.awt.Color;
import java.util.Comparator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import me.fainted.Fainted;
import me.fainted.gui.font.FontUtil;
import me.fainted.module.Module;
import me.fainted.util.ColourUtil;
import me.fainted.util.Wrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

public class GuiIngameHook extends GuiIngame{

	private Minecraft mc = Minecraft.getMinecraft();
	private FontRenderer font = mc.fontRendererObj;
	
	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}

	@Override
	public void renderGameOverlay(float partialTicks) {
		super.renderGameOverlay(partialTicks);
		if (!mc.gameSettings.showDebugInfo) {
		
			int tcolor = 0;
			String tc = Fainted.instance.colourUtil.getTitle();
			if (tc.equals("rgb")) { tcolor = ColourUtil.getRainbow(4, 1, 1, 1 * 150); } 
			if (tc.equals("white")) { tcolor = 0xffffffff; }
			if (tc.equals("red")) { tcolor = 0xffff0000; }
			if (tc.equals("green")) { tcolor = 0xff00ff00; }
			if (tc.equals("blue")) { tcolor = 0xff0000ff; }
			if (tc.equals("black")) { tcolor = 0xff000000; } 
			
			drawRect(2, 2, 95, 14, 0x80000000);
			FontUtil.normal.drawString("Fainted: ", 4, 4, tcolor);
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
			
			
			
		} 
		
	}
	
}
