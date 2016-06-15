package com.github.wolfiewaffle.survivalistlighting.items;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;

public class ItemTorchBasic extends ItemBlock {

	private Item litVariant;

	/**
	 * Constructor for a basic torch item.
	 * @param block 		The base block
	 * @param fuel 			The fuel of the torch.
	 * @param stacksize 	The max stack size of the item.
	 * @param litVariant	The lit variant of the torch. Null if cannot be lit.
	 */
	public ItemTorchBasic(Block block, String name, int fuel, int stacksize, Item litVariant) {
		this(block, name, fuel, litVariant);
		this.setMaxStackSize(stacksize);
	}

	/**
	 * Constructor for a basic torch item without stacksize.
	 * @param block 		The base block
	 * @param fuel 			The fuel of the torch.
	 * @param litVariant	The lit variant of the torch. Null if cannot be lit.
	 */
	public ItemTorchBasic(Block block, String name, int fuel, Item litVariant) {
		super(block);
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());
		this.litVariant = litVariant;
		if (fuel > 0) {
			this.setHasSubtypes(hasSubtypes);
			this.setMaxDamage(fuel);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {

		RayTraceResult rayTrace = itemStackIn.getItem().rayTrace(worldIn, playerIn, true);
		System.out.println(rayTrace.typeOfHit);
		if (rayTrace.typeOfHit == Type.BLOCK) {
			ResourceLocation rl = worldIn.getBlockState(rayTrace.getBlockPos()).getBlock().getRegistryName();

			// If the activated block is a lighter block
			if (ModConfig.inWorldLightItems.contains(rl)) {

				// If sneaking, just place the block normally
				if (!playerIn.isSneaking()) {
					lightTorch(playerIn, hand);
				}
			}
		}

		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	/** Lights a torch in the inventory */
	public void lightTorch(EntityPlayer playerIn, EnumHand hand) {

		Item heldTorch = playerIn.inventory.getCurrentItem().getItem();

		int oldFuel = playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem).getItemDamage();
		int count = 0;
		int slot = playerIn.inventory.currentItem;

		Item torch1;
		Item torch2;

		// Decide what torch to use
		if (((ItemTorchBasic) heldTorch).getLitVariant() != null) {

			torch1 = ((ItemTorchBasic) heldTorch).getLitVariant();
			torch2 = heldTorch;

			// Get the amount of held items
			if(playerIn.getHeldItem(hand) != null) {
				count = playerIn.getHeldItem(hand).stackSize;
			}

			// If there is only one torch, just light it
			if (count == 1) {
				playerIn.inventory.setInventorySlotContents(slot, new ItemStack(torch1, count, oldFuel));

			} else if (count > 1) {
				// Subtract one torch from the stack
				playerIn.inventory.setInventorySlotContents(slot, new ItemStack(torch2, count-1, oldFuel));

				// Give a lit torch to the player
				if (!playerIn.inventory.addItemStackToInventory(new ItemStack(torch1, 1, oldFuel))) {
					playerIn.dropItem(torch1, 1);
				}
			}
		}
	}

	public Item getLitVariant() {
		return litVariant;
	}
}
