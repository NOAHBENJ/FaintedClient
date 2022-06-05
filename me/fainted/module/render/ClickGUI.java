package me.fainted.module.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.fainted.Fainted;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;


public class ClickGUI extends Module{

        public ClickGUI() {
        super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
    }

    @Override
    public void setup() {
        ArrayList<String> options = new ArrayList<>();
        options.add("New");
        options.add("JellyLike");
        Fainted.instance.settingsManager.rSetting(new Setting("Design", this, "New", options));
        Fainted.instance.settingsManager.rSetting(new Setting("Sound", this, false));
        Fainted.instance.settingsManager.rSetting(new Setting("GuiRed", this, 255, 0, 255, true));
        Fainted.instance.settingsManager.rSetting(new Setting("GuiGreen", this, 26, 0, 255, true));
        Fainted.instance.settingsManager.rSetting(new Setting("GuiBlue", this, 42, 0, 255, true));
    }

    @Override
    public void onEnable() {
        super.onEnable();

        mc.displayGuiScreen(Fainted.instance.clickGUI);
        toggle();
    }
}