package me.fainted.module.combat;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Ordering;

import me.fainted.module.Module;
import me.fainted.module.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;

public class AntiBot extends Module
{
	public static List<EntityPlayer> bots = new ArrayList<EntityPlayer>();
	public Minecraft mc = Minecraft.getMinecraft();
	
	public AntiBot() {
		super("AntiBot", Keyboard.KEY_NONE, Category.COMBAT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onUpdate() {
		for (int k = 0; k < mc.theWorld.playerEntities.size(); k++) {
			EntityPlayer ent = mc.theWorld.playerEntities.get(k);
			List<EntityPlayer> tabList = this.getTabPlayerList();
			if (!bots.contains(ent) && !tabList.contains(ent)) {
				bots.add(ent);
				continue;
			} else if (bots.contains(ent) && tabList.contains(ent)) {
				bots.remove(ent);
			}
		}
	}
	
	private List<EntityPlayer> getTabPlayerList() {
		final List<EntityPlayer> list;
		(list = new ArrayList<EntityPlayer>()).clear();
		Ordering<NetworkPlayerInfo> field_175252_a = field_175252_a();
		if (field_175252_a == null) {
			return list;
		}
		final List playes = field_175252_a.sortedCopy(mc.thePlayer.sendQueue.getPlayerInfoMap());
		for (final Object o : playes) {
			final NetworkPlayerInfo info = (NetworkPlayerInfo) o;
			if (info == null) {
				continue;
			}
			list.add(mc.theWorld.getPlayerEntityByName(info.getGameProfile().getName()));
		}
		return list;
	}
	
	private Ordering<NetworkPlayerInfo> field_175252_a() {
		try {
			final Class<GuiPlayerTabOverlay> c = GuiPlayerTabOverlay.class;
			final Field f = c.getField("field_175252_a");
			f.setAccessible(true);
			return (Ordering<NetworkPlayerInfo>)f.get(GuiPlayerTabOverlay.class);
		} catch(Exception e) {
			return null;
		}
	}
}
