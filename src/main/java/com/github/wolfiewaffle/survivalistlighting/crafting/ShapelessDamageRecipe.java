package com.github.wolfiewaffle.survivalistlighting.crafting;

import java.util.Arrays;
import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * A shapeless recipe that damages specified ingredients.
 *
 * @author Choonster (eited by Wolfie_Waffle)
 */
public class ShapelessDamageRecipe extends ShapelessRecipes {

	private Item[] unlitTorches;
	private ItemStack output;
	private int outputDamage;
	private final Random random = new Random();

	public ShapelessDamageRecipe(Item[] unlitTorches, ItemStack output, ItemStack... input) {
		super(output, Arrays.asList(input));
		this.unlitTorches = unlitTorches;
		this.output = output;
	}

	private ItemStack damageItem(ItemStack stack) {
		if (stack.attemptDamageItem(1, random)) {
			ForgeEventFactory.onPlayerDestroyItem(ForgeHooks.getCraftingPlayer(), stack, null);
			return null;
		}

		return stack;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
		// Crafting result array
		final ItemStack[] remainingItems = new ItemStack[inventoryCrafting.getSizeInventory()];

		// For each item in the crafting grid...
		for (int i = 0; i < remainingItems.length; ++i) {
			// Get the ItemStack
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			// If the ItemStack is in the config for lightItems
			if (itemstack != null && ModConfig.lightItems.contains(itemstack.getItem().getRegistryName())) {
				remainingItems[i] = damageItem(itemstack.copy()); // Leave a damaged lighter in the grid
			} else {
				remainingItems[i] = ForgeHooks.getContainerItem(itemstack); // Consume the item
			}
		}

		return remainingItems;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {

		for (int i = 1; i < inv.getSizeInventory(); ++i) {
			final ItemStack itemstack = inv.getStackInSlot(i);

			// If the ItemStack is in the array of unlit torches
			if (itemstack != null && Arrays.asList(unlitTorches).contains(itemstack.getItem())) {
				outputDamage = itemstack.getItemDamage();
				if (ModConfig.configDebug) System.out.println(itemstack.getItem().getUnlocalizedName());
				if (ModConfig.configDebug) System.out.println(itemstack.getItemDamage());
				if (ModConfig.configDebug) System.out.println(i); 
			}
		}

		return new ItemStack(output.getItem(), 1, outputDamage);
	}
}