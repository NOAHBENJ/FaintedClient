package me.fainted.module.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.RenderUtil;

// import org.lwjgl.util.Color;
import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends Module{
	
	public Tracers() {
		super("Tracers", Keyboard.KEY_X, Category.RENDER);
		// TODO Auto-generated constructor stub
	}

	Minecraft mc = Minecraft.getMinecraft();
	
	@Override
    public void onRender() {
    	if(this.isToggled()) {
		    for (final EntityPlayer playerEntity : mc.theWorld.playerEntities) {
		        if (playerEntity != mc.thePlayer && !playerEntity.isDead && !playerEntity.isInvisible()) // Distance check to fix a bug where it renders players far away that have been rendered before
		            drawToPlayer(playerEntity);
		    }
		    RenderUtil.color(Color.WHITE);
    	}
    }

    public void drawToPlayer(final EntityLivingBase entity) {

        final float red = 0F;
        final float green = 255F;
        final float blue = 0F;

        final double xPos = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks) - mc.getRenderManager().viewerPosX;
        final double yPos = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks) - mc.getRenderManager().viewerPosY;
        final double zPos = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks) - mc.getRenderManager().viewerPosZ;

        render(red, green, blue, xPos, yPos, zPos);
    }

    public void render(final float red, final float green, final float blue,
                       final double x, final double y, final double z) {
        drawTracerLine(x, y, z, red, green, blue, 0.5F, 1.5F);
    }

    public void drawTracerLine(final double x, final double y, final double z,
                                      final float red, final float green, final float blue,
                                      final float alpha, final float lineWidth) {
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        mc.entityRenderer.orientCamera(mc.timer.renderPartialTicks);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        // GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        GL11.glVertex3d(0.0D, mc.thePlayer.getEyeHeight(), 0.0D);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        // GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
