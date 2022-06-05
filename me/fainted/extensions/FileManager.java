package me.fainted.extensions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.fainted.Fainted;
import me.fainted.module.render.ClickGUI;
import me.fainted.module.Module;
import me.fainted.module.combat.KillAura;
import me.fainted.module.movement.Flight;
import me.fainted.module.movement.Speed;
import me.fainted.util.JSONUtils;

public class FileManager {

	public static File ROOT_DIR = new File("FaintedClient-1.8.8");
	public static File modules = new File(ROOT_DIR, "modules.json");
	// public static File alts = new File(ROOT_DIR, "alts.txt");
	
	public void init() {
		
		//Make Root Directory
		if(!ROOT_DIR.exists()) { ROOT_DIR.mkdirs(); }
		
		//Handles Modules
		if(!modules.exists()) {
			saveMods();
		} else {
			loadMods();
		}
	}
	
	// Save Toggled State
	public void saveMods() {
		try {
			JsonObject json = new JsonObject();
			for (Module mod : Fainted.instance.moduleManager.getModules()) {
				JsonObject jsonMod = new JsonObject();
				jsonMod.addProperty("enabled", mod.isToggled());
				json.add(mod.getName(), jsonMod);
			}
			PrintWriter save = new PrintWriter(new FileWriter(modules));
			save.println(JSONUtils.prettyGson.toJson(json));
			save.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Blacklist of Mods
	private HashSet<String> modBlacklist = Sets.newHashSet(
			ClickGUI.class.getName(),
			KillAura.class.getName(),
			Speed.class.getName(),
			Flight.class.getName()
			);
	
	public boolean isModBlacklisted(Module m) {
		return modBlacklist.contains(m.getClass().getName());
	}
	
	// Read && Load Toggle States
	public void loadMods() {
		try {
			BufferedReader load = new BufferedReader(new FileReader(modules));
			JsonObject json = (JsonObject)JSONUtils.jsonParser.parse(load);
			load.close();
			Iterator<Entry<String, JsonElement>> itr = json.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<String, JsonElement> entry = itr.next();
				Module mod = Fainted.instance.moduleManager.getModuleByName(entry.getKey());
				if (mod != null && modBlacklist.contains(mod.getClass().getName())) {
					JsonObject jsonModule = (JsonObject)entry.getValue();
					boolean enabled = jsonModule.get("enabled").getAsBoolean();
					if (enabled) {
						mod.toggle();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
 