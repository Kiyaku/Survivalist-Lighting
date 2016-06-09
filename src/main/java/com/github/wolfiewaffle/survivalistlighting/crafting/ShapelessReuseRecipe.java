package com.github.wolfiewaffle.survivalistlighting.crafting;

import java.util.Arrays;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.ForgeHooks;

/**
 * A shapeless recipe that keep specified items in the grid.
 *
 * @author Choonster (edited by Wolfie_Wafle)
 */
public class ShapelessReuseRecipe extends ShapelessRecipes {

	private Item[] unlitTorches;
	private ItemStack output;
	private int outputDamage;

	public ShapelessReuseRecipe(Item[] unlitTorches, ItemStack output, ItemStack... input) {
		super(output, Arrays.asList(input));
		this.unlitTorches = unlitTorches;
		this.output = output;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
		// Crafting result array
		final ItemStack[] remainingItems = new ItemStack[inventoryCrafting.getSizeInventory()];

		// For each item in the crafting grid...
		for (int i = 0; i < remainingItems.length; ++i) {
			// Get the ItemStack
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			// If the ItemStack is in the config for freeLightItems
			if (itemstack != null && ModConfig.freeLightItems.contains(itemstack.getItem().getRegistryName())) {
				remainingItems[i] = itemstack.copy(); // Don't consume the item
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