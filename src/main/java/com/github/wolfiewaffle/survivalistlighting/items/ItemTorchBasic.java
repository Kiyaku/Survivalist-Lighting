package com.github.wolfiewaffle.survivalistlighting.items;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
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

	// Handles torch lighting
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

		// Get the block the player is looking at
		RayTraceResult rtr = this.rayTrace(worldIn, playerIn, true);

		if (rtr == null) {
			return ActionResult.newResult(EnumActionResult.FAIL, itemStackIn);

		} else if (rtr.typeOfHit == RayTraceResult.Type.BLOCK) {

			// Get the registry name of the right clicked block
			BlockPos hitPos = rtr.getBlockPos();
			ResourceLocation rl = worldIn.getBlockState(hitPos).getBlock().getRegistryName();

			// If the registry name matches on in the config
			if (ModConfig.inWorldLightItems.contains(rl)) {

				// If sneaking, just place the block normally
				if (!playerIn.isSneaking()) {
					lightTorch(playerIn, hand);

					return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
				}
			}
		}

		return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
	}

	// Must override or onItemRightClick may not be called
	public EnumActionResult onItemUse(ItemStack itemStackIn, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		ResourceLocation rl = worldIn.getBlockState(pos).getBlock().getRegistryName();

		if (ModConfig.inWorldLightItems.contains(rl)) {

			// If sneaking, just place the block normally
			if (!playerIn.isSneaking()) {
				return EnumActionResult.PASS;
			}
		}

		return super.onItemUse(itemStackIn, playerIn, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}

	/** Lights a torch in the inventory */
	public ItemStack lightTorch(EntityPlayer playerIn, EnumHand hand) {
		if (playerIn.getHeldItem(hand) != null) {

			ItemStack heldTorch = playerIn.getHeldItem(hand);

			int oldFuel = heldTorch.getItemDamage();
			int count = 0;

			Item torch;
			Item oldTorch;

			// Decide what torch to use
			if (((ItemTorchBasic) heldTorch.getItem()).getLitVariant() != null) {

				torch = ((ItemTorchBasic) heldTorch.getItem()).getLitVariant();
				oldTorch = heldTorch.getItem();

				// Get the amount of held items
				count = playerIn.getHeldItem(hand).stackSize;

				// If there is only one torch, just light it
				if (count == 1) {
					playerIn.setHeldItem(hand, new ItemStack(torch, count, oldFuel));

					return new ItemStack(torch, count, oldFuel);
				} else if (count > 1) {
					// Subtract one torch from the stack
					playerIn.setHeldItem(hand, new ItemStack(oldTorch, count-1, oldFuel));

					// Give a lit torch to the player
					if (!playerIn.inventory.addItemStackToInventory(new ItemStack(torch, 1, oldFuel))) {
						playerIn.dropItem(torch, 1);
					}

					return new ItemStack(torch, count, oldFuel);
				}
			}
		}

		return null;
	}

	public Item getLitVariant() {
		return litVariant;
	}
}
