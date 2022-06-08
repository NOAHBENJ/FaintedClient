package me.fainted.module;

import java.util.ArrayList;

import me.fainted.Fainted;
import me.fainted.module.combat.*;
import me.fainted.module.movement.*;
import me.fainted.module.render.*;

public class ModuleManager {

	private static ArrayList<Module> mods;
	
	public ModuleManager() {
		mods = new ArrayList<Module>();
		//COMBAT
		newMod(new AntiBot());
		newMod(new AutoClicker());
		newMod(new FastBow());
		newMod(new KillAura());		
		
		//MOVEMENT
		newMod(new AutoWalk());
		newMod(new Dolphin());
		newMod(new Flight());
		newMod(new Glide());
		newMod(new Jetpack());
		newMod(new NoFall());
		newMod(new Parkour());
		newMod(new Scaffold());
		newMod(new Sneak());
		newMod(new Speed());
		newMod(new Spider());
		newMod(new Sprint());
		newMod(new Step());
		//PLAYER
		
		//RENDER
		
		newMod(new ChinaHat());
		newMod(new ClickGUI());
		newMod(new ESP());
		newMod(new FullBright());
		newMod(new Keystrokes());
		newMod(new RiseESP());
		newMod(new Tracers());
		
		//MISC
		
	}
	
	public static void newMod(Module m) {
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules() {
		return mods;
	}
	
	public static void onUpdate() {
		for (Module m : mods) {
			m.onUpdate();
		}
	}
	
	public static void onRender() {
		for (Module m : mods) {
			m.onRender();
		}
	}
	
	public static void onKey(int k) {
		for (Module m : mods) {
			if (m.getKey() == k) {
				m.toggle();
			} else {
				m.onKey(k);
			}
		}
	}
	
	public static Module getModuleByName(String moduleName) {
		for(Module m : Fainted.instance.moduleManager.getModules()) {
			if(!m.getName().trim().equalsIgnoreCase(moduleName) && !m.toString().equalsIgnoreCase(moduleName.trim())) {continue;}
			return m;
		}
		return null;
	}
	
}
