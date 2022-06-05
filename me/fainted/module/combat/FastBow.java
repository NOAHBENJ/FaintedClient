package me.fainted.module.combat;

import org.lwjgl.input.Keyboard;

import me.fainted.module.Category;
import me.fainted.module.Module;
import net.minecraft.item.ItemBow;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C07PacketPlayerDigging.Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class FastBow extends Module{
	
	public FastBow() {
		super("FastBow", Keyboard.KEY_J, Category.COMBAT);
	}
	
	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			if(mc.thePlayer.getHealth() > 0 && (mc.thePlayer.onGround 
					|| mc.thePlayer.capabilities.isCreativeMode) 
					&& mc.thePlayer.inventory.getCurrentItem() != null 
					&& mc.thePlayer.inventory.getCurrentItem().getItem() instanceof ItemBow 
					&& mc.gameSettings.keyBindUseItem.pressed) {
				mc.playerController.sendUseItem(mc.thePlayer, mc.theWorld, mc.thePlayer.inventory.getCurrentItem()
						);
				mc.thePlayer.inventory.getCurrentItem().getItem().onItemRightClick(mc.thePlayer.inventory.getCurrentItem(), mc.theWorld, mc.thePlayer);
				
				for (int i = 0; i < 20; i++) {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
				}
				mc.getNetHandler().addToSendQueue(new C07PacketPlayerDigging(Action.RELEASE_USE_ITEM, new BlockPos(0, 0, 0), EnumFacing.DOWN));
				mc.thePlayer.inventory.getCurrentItem().getItem().onPlayerStoppedUsing(mc.thePlayer.inventory.getCurrentItem(), mc.theWorld, mc.thePlayer, 10);
			}
		}
	}
}
