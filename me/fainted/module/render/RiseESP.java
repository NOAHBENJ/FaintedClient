package me.fainted.module.render;

import me.fainted.module.Category;
import me.fainted.module.Module;
import me.fainted.util.RenderUtil;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;

import java.awt.Color;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class RiseESP extends Module{

	public RiseESP() {
		super("RiseESP", Keyboard.KEY_NONE, Category.RENDER);
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onRender() {
        if (!this.isToggled())
            return;
 
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glPushMatrix();

        int amount = 0;
        for (final TileEntity tileEntity : mc.theWorld.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityChest || tileEntity instanceof TileEntityEnderChest) {
                render(amount, tileEntity);
                amount++;
            }
        }

        GL11.glPopMatrix();
        GL11.glPopAttrib();
               
    }

    private void render(final int amount, final TileEntity p) {
        GL11.glPushMatrix();

        final RenderManager renderManager = mc.getRenderManager();

        final double x = (p.getPos().getX() + 0.5) - renderManager.renderPosX;
        final double y = p.getPos().getY() - renderManager.renderPosY;
        final double z = (p.getPos().getZ() + 0.5) - renderManager.renderPosZ;

        GL11.glTranslated(x, y, z);

        GL11.glRotated(-renderManager.playerViewY, 0.0D, 1.0D, 0.0D);
        GL11.glRotated(renderManager.playerViewX, mc.gameSettings.thirdPersonView == 2 ? -1.0D : 1.0D, 0.0D, 0.0D);

        final float scale = 1 / 100f;
        GL11.glScalef(-scale, -scale, scale);

        final Color c = Color.blue;

        final float offset = renderManager.playerViewX * 0.5f;

        RenderUtil.lineNoGl(-50, offset, 50, offset, c);
        RenderUtil.lineNoGl(-50, -95 + offset, -50, offset, c);
        RenderUtil.lineNoGl(-50, -95 + offset, 50, -95 + offset, c);
        RenderUtil.lineNoGl(50, -95 + offset, 50, offset, c);

        GL11.glPopMatrix();
    }
	
}
