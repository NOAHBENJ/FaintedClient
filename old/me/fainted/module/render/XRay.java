package me.fainted.module.render;

import java.util.ArrayList;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class XRay extends Module{
	
	public static boolean enabled;
	private float oldGamme;
	private ArrayList<Block> xrayBlocks = new ArrayList<Block>();
	
	public XRay() {
		super("XRay", 0, Category.RENDER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable() {
		XRay.enabled = true;
		this.oldGamme = mc.gameSettings.gammaSetting;
		mc.gameSettings.gammaSetting = 10.0f;
		mc.gameSettings.ambientOcclusion = 0;
		mc.renderGlobal.loadRenderers();
	}
	
	@Override
	public void onDisable() {
		XRay.enabled = false;
		mc.gameSettings.gammaSetting = this.oldGamme;
		mc.gameSettings.ambientOcclusion = 0;
		mc.renderGlobal.loadRenderers();
	}
	
	public boolean shouldXRayBlock(Block blockid) {
		if(this.xrayBlocks.contains(blockid)) return true; return false;
	}
	
	@Override
	public void onRender() {
		if(!this.isToggled()) {
			return;
		}
		
	}
	
}
