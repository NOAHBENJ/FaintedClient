package me.fainted.gui;

import java.io.IOException;

import me.fainted.Fainted;
import me.fainted.alt.GuiAltManager;
import me.fainted.util.gui.AnimatedButton;
import me.fainted.util.gui.AnimatedResourceLocation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen{

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		AnimatedResourceLocation location = new AnimatedResourceLocation("bg", 1, 15);
		location.update();
		
		mc.getTextureManager().bindTexture(location.getTexture());
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, this.width, this.height, this.width, this.height);
		
		Gui.drawRect(0, 0, 223, this.height, 0x90000000);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void initGui() {
		this.buttonList.add(new GuiButton(1, 10, height / 2 - 50, "Singleplayer"));
		this.buttonList.add(new GuiButton(2, 10, height / 2 - 25, "Multiplayer"));
		this.buttonList.add(new GuiButton(3, 10, height / 2, "Alt Manager"));
		this.buttonList.add(new GuiButton(4, 10, height / 2 + 25, "Options"));
		this.buttonList.add(new GuiButton(5, 10, height / 2 + 50, "Rage Quit"));
		
		
		super.initGui();
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		
		if (button.id == 1) {
			mc.displayGuiScreen(new GuiSelectWorld(this));
		}
		if (button.id == 2) {
			mc.displayGuiScreen(new GuiMultiplayer(this));
		}
		if (button.id == 3) {
			mc.displayGuiScreen(new GuiAltManager());
		}
		if (button.id == 4) {
			mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
		}
		if (button.id == 5) {
			mc.shutdown();
		}
		
		super.actionPerformed(button);
	}
	
}
