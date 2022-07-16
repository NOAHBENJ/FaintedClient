package me.fainted.gui.ingame;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;

public class CustomIngameGui extends GuiIngame{

	protected static final Minecraft mc = Minecraft.getMinecraft();
	
	public CustomIngameGui() {
		super(mc);
	}
	
}
