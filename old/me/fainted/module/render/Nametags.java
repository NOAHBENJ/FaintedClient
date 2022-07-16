package me.fainted.module.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;

public final class Nametags extends Module {

    public Nametags() {
		super("Nametags", 0, Category.RENDER);
		// Renders nametags through walls
	}

	public static boolean enabled;


    // Uses Render.java line 106
    @Override
    public void onRender() {
    	if(!this.isToggled())
    		return;
    	
        int amount = 0;
        FontRenderer fr = mc.fontRendererObj;

        for (final EntityPlayer entity : mc.theWorld.playerEntities) {
            if (entity != null) {
                final String name = entity.getName();

                if ((!entity.isInvisible()) && !entity.isDead && entity != mc.thePlayer) {
                    //Changing size
                    final float scale = Math.max(0.02F, mc.thePlayer.getDistanceToEntity(entity) / 300);

                    final double x = (entity).lastTickPosX + ((entity).posX - (entity).lastTickPosX) * mc.timer.renderPartialTicks - (mc.getRenderManager()).renderPosX;
                    final double y = ((entity).lastTickPosY + ((entity).posY - (entity).lastTickPosY) * mc.timer.renderPartialTicks - (mc.getRenderManager()).renderPosY) + scale * 6;
                    final double z = (entity).lastTickPosZ + ((entity).posZ - (entity).lastTickPosZ) * mc.timer.renderPartialTicks - (mc.getRenderManager()).renderPosZ;

                    GL11.glPushMatrix();
                    GL11.glTranslated(x, y + 2.3, z);
                    GlStateManager.disableDepth();

                    GL11.glScalef(-scale, -scale, -scale);

                    GL11.glRotated(-mc.getRenderManager().playerViewY, 0.0D, 1.0D, 0.0D);
                    GL11.glRotated(mc.getRenderManager().playerViewX, mc.gameSettings.thirdPersonView == 2 ? -1.0D : 1.0D, 0.0D, 0.0D);

                    final float width = fr.getStringWidth(name) - 7;
                    final float progress = Math.min((entity).getHealth(), (entity).getMaxHealth()) / (entity).getMaxHealth();

                    final Color color = Color.WHITE;

                    Gui.drawRect((-width / 2.0F - 5.0F), -1, (width / 2.0F + 5.0F), 8, 0x40000000);
                    Gui.drawRect((-width / 2.0F - 5.0F), 7, (-width / 2.0F - 5.0F + (width / 2.0F + 5.0F - -width / 2.0F + 5.0F) * progress), 8, color.getRGB());

                    GL11.glScalef(1.0f, 1.0f, 1.0f);

                    fr.drawString(name, -width / 16.0F - (2.5f * name.length()), 0.4f, -1);

                    GL11.glScalef(1.9f, 1.9f, 1.9f);

                    GlStateManager.enableDepth();
                    GL11.glPopMatrix();
                    amount++;
                }
            }
        }
    }

    @Override
    public void onEnable() {
        enabled = true;
    }

    @Override
    public void onDisable() {
        enabled = false;
    }
}