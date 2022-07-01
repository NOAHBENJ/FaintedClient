package me.fainted.module.combat;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.google.common.collect.Ordering;

import me.fainted.Fainted;
import me.fainted.events.Event;
import me.fainted.events.listeners.EventMotion;
import me.fainted.gui.GuiIngameHook;
import me.fainted.gui.font.FontUtil;
import me.fainted.gui.settings.Setting;
import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.module.render.TargetHUD;
import me.fainted.util.Timer;
import me.fainted.util.UrlTextureUtil;
import me.fainted.util.UrlTextureUtil.ResourceLocationCallback;
import me.fainted.util.gui.RoundedUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.play.client.C0APacketAnimation;

public class KillAura extends Module{ 

	//public boolean downloaded = false;
	public double reach;
	//private GuiIngameHook hook = new GuiIngameHook(mc);
	//public Timer timer = new Timer();
	//public static int height, width;
	public boolean attacking = false;
	EntityLivingBase entity1;
	public EntityLivingBase entity;
	public static List<Entity> bots = new ArrayList<Entity>();
	//public ResourceLocation img;
	//public int color = -1;
	
	public KillAura() {
		super("KillAura", Keyboard.KEY_K, Category.COMBAT);
	}
	
	@Override
    public void setup() {
        Fainted.instance.settingsManager.rSetting(new Setting("KA.Reach", this, 3, 2, 8, true));
        Fainted.instance.settingsManager.rSetting(new Setting("KA.APS", this, 10, 1, 20, true));
        Fainted.instance.settingsManager.rSetting(new Setting("KA.TargetHUD", this, true));
        Fainted.instance.settingsManager.rSetting(new Setting("KA.AntiBot", this, true));
        Fainted.instance.settingsManager.rSetting(new Setting("KA.NoSwing", this, false));
	} 
	
	
	
	public void setDisplaySettingg(String s) {
		super.setDisplaySetting(s);
	}
	
	@Override
	public void onRender() {
		
		boolean toggled = Fainted.instance.settingsManager.getSettingByName("KA.TargetHUD").getValBoolean();
		String reachh = Integer.toString((int)Fainted.instance.settingsManager.getSettingByName("KA.Reach").getValDouble());
		this.displaySetting = reachh;
		//System.out.println("Set displaySetting to: " + reachh);
		//DEBUG: System.out.println(toggled);
		
		if (this.isToggled() && toggled == true) {
			
			
			if (entity == null || bots.contains(entity)) {
				return;
			}
			/*EntityLivingBase entity1 = entity;
			ScaledResolution sr = new ScaledResolution(mc);
			if(entity.getHealth() == 0 || mc.thePlayer.getDistanceToEntity(entity1) >= reach * 1.7f || entity1 == null) {
				return;
			}
			
			int halfWidth = sr.getScaledWidth() - (sr.getScaledWidth() / 2);
			int halfHeight = sr.getScaledHeight() - (sr.getScaledHeight() / 2);
			
			Gui.drawRect(halfWidth, halfHeight, halfWidth + 70 - 8, halfHeight + 25 - 3.75f, 0x90000000);
			mc.fontRendererObj.drawStringWithShadow(entity1.getName(), halfWidth + 2, halfHeight + 2, color);
			mc.fontRendererObj.drawStringWithShadow(Math.round(entity1.getHealth()) + "§c \u2764", halfWidth + 2, halfHeight + 12, -1);
			GuiInventory.drawEntityOnScreen(halfWidth + mc.fontRendererObj.getStringWidth(entity1.getName()) * ((int)1.25f) + 22, halfHeight + 20, 10, 50, 50, entity1);
			*/
			
			EntityLivingBase entity1 = entity;
			
			if(entity.getHealth() == 0 || mc.thePlayer.getDistanceToEntity(entity1) >= reach * 1.7f || entity1 == null) {
				//downloaded = false;
				return;
			}
			
			ScaledResolution sr = new ScaledResolution(mc);
			int xHalf = sr.getScaledWidth() - (sr.getScaledWidth() / 2);
	        int yHalf = sr.getScaledHeight() - (sr.getScaledHeight() / 2);
	        //EntityPlayer entity = mc.thePlayer;
	        float health = entity.getHealth();
	        float increment = 80 / entity.getMaxHealth();
	        
	        RoundedUtils.drawSmoothRoundedRect(xHalf + 5, yHalf + 5, xHalf + 100 - 5, yHalf + 40, 10, new Color(0, 0, 0, 170).getRGB());
	        Gui.drawHorizontalLine(xHalf + 10, xHalf + 9 + increment * health + 1, yHalf + 60 - 25 , 2, health == 20 ? 0xff00ff00 : health > 16 ? 0xffB4F718 : health > 12 ? 0xffF7F118 : health > 8 ? 0xffF7B718 : health > 4 ? 0xffEE6E04 : 0xffff0000);
	        Gui.drawHorizontalLine(sr.getScaledWidth() + 1, sr.getScaledWidth() + 2, sr.getScaledHeight() + 2 , 1, new Color(255,255,255,255).getRGB());
	        
	        FontUtil.normal.drawString(entity.getName(), xHalf + 10, yHalf + 10, 0xffffffff);
	        FontUtil.normal.drawString("Health: §c" + Integer.toString(Math.round(entity.getHealth())), xHalf + 10, yHalf + 15 + FontUtil.normal.getHeight(), 0xffffffff);
	       	   
		}
		
		
	}
	
	@Override
	public void onEvent(Event e) {
		if (!this.isToggled())
			return;
		
		if (e instanceof EventMotion) {
			boolean antiBot = Fainted.instance.settingsManager.getSettingByName("KA.AntiBot").getValBoolean();
			
			EventMotion event = (EventMotion) e;
			
			if (antiBot) {
				/*
				for (int k = 0; kr ent = mc.theWorld.playerEntities.get(k);
					List<EntityPlayer> tabList = this.getTabPlayerList();
					if (!bots.contains(ent) && !tabList.contains(ent)) {
						bots.add(ent);
						continue;
					} else if (bots.contains(ent) && tabList.contains(ent)) {
						bots.remove(ent);
					}
				} */
				
				for (final Entity entity : mc.theWorld.loadedEntityList) {
					if (entity instanceof EntityLivingBase) {
			            if (entity != mc.thePlayer && !bots.contains(entity)) {
					
							final String valid = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890_";
							final String[] invalid = {"\"", ":", ";", "{", "}", "[", "]", "(", ")", "*", "&", "^", "%", "$", "#", "@", "!", "~", "`", "?", "/", ".", ">", ",", "<","|"}; 
							
						    final String name = entity.getName();
						
						    if (stringContainsItemFromList(name, invalid)) { 
						    	bots.add(entity);
						    }
						
			            }
					}
				}
			}

			double aps = Fainted.instance.settingsManager.getSettingByName("KA.TargetHUD").getValDouble();
			double range = Fainted.instance.settingsManager.getSettingByName("KA.Reach").getValDouble();
			boolean noSwing = Fainted.instance.settingsManager.getSettingByName("KA.NoSwing").getValBoolean();
			
			
			ScaledResolution sr = new ScaledResolution(mc);
			for (Iterator<Entity> entities = mc.theWorld.loadedEntityList.iterator(); entities.hasNext();) {
				Object theObject = entities.next();
				if (theObject instanceof EntityLivingBase) {
					entity1 = (EntityLivingBase) theObject;
					
					
					
					if (entity1 instanceof EntityPlayerSP) continue;
					
					reach = Fainted.instance.settingsManager.getSettingByName("KA.Reach").getValDouble();
					if(mc.thePlayer.getDistanceToEntity(entity1) <= reach * 1.4f) {
	                    if(entity1.isEntityAlive()) {
	                    	if (mc.currentScreen != null || mc.gameSettings.showDebugInfo) {
	                            return;
	                        }           
	                    	
		                	entity = entity1;
		                	
		                	if (antiBot) {
		                		if(bots.contains(entity) || entity instanceof EntityVillager) {
			                		return;
			                	}	
		                	}
		                	
		                	event.setYaw(getRotations(entity)[0]);
							event.setPitch(getRotations(entity)[1]);
		                	
		                    attacking = true;
		                    if(noSwing) {
								mc.thePlayer.sendQueue.addToSendQueue(new C0APacketAnimation());
							} else {
								mc.thePlayer.swingItem();
							}
		            		mc.thePlayer.sendQueue.addToSendQueue(new C02PacketUseEntity(entity, Action.ATTACK));
		            	
		            		
	                    	continue;
	                    }
	                }
				}
			}
			super.onUpdate();
		}
		
	}
	
	
	// TODO: HELPERS BELOW
	
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
	
	public static boolean stringContainsItemFromList(String inputStr, String[] items) {
	    return Arrays.stream(items).anyMatch(inputStr::contains);
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX, 
			   deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
			   deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
			   distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
				pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
			
		if (deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		} else if (deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}
		
		return new float[] { yaw, pitch };
	}
	
}

