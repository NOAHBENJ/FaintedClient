package me.fainted.util.ESP;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

public class MobESPUtils {
	public static final Minecraft mc = Minecraft.getMinecraft();
	public static final EntityPlayerSP player = mc.thePlayer;
	
	public static void entityESPBox(Entity entity, int mode) {
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glLineWidth(2.0f);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(false);
		
		if (mode == 0) //Enemy
		{
			GL11.glColor4d(1 - player.getDistanceToEntity(entity) / 40, 
				player.getDistanceToEntity(entity) / 40,
					0, 0.5f);
		} else if (mode == 1) // Friend 
		{
			GL11.glColor4d(0, 0, 1, 0.5f);
		}
		else if (mode == 2) // Other Player
		{
			GL11.glColor4d(1, 1, 0, 0.5f);
		}
		else if (mode == 3) // Target 
		{
			GL11.glColor4d(1, 0, 0, 0.5f);
		}
		else if (mode == 4) // Team 
		{
			GL11.glColor4d(0, 1, 0, 0.5f);
		}
		mc.getRenderManager();
		RenderGlobal.func_181561_a(
				new AxisAlignedBB(
						entity.boundingBox.minX 
							- 0.05
							- entity.posX
							+ (entity.posX - mc.getRenderManager().renderPosX),
						entity.boundingBox.minY 
							- 0.05
							- entity.posY
							+ (entity.posY - mc.getRenderManager().renderPosY),
						entity.boundingBox.minZ 
							- 0.05
							- entity.posZ
							+ (entity.posZ - mc.getRenderManager().renderPosZ),
						entity.boundingBox.maxX 
							+ 0.05
							- entity.posX
							+ (entity.posX - mc.getRenderManager().renderPosX),
						entity.boundingBox.maxY 
							+ 0.01
							- entity.posY
							+ (entity.posY - mc.getRenderManager().renderPosY),
						entity.boundingBox.maxZ
							+ 0.05
							- entity.posZ
							+ (entity.posZ - mc.getRenderManager().renderPosZ)));
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
}
