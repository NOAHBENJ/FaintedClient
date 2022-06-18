package me.fainted.module.combat;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class AntiBot extends Module
{
	public static List<Entity> bots = new ArrayList<>();
	public Minecraft mc = Minecraft.getMinecraft();
	
	
	public AntiBot() {
		super("AntiBot", Keyboard.KEY_NONE, Category.COMBAT);
		// TODO Auto-generated constructor stub
	}

	
}
