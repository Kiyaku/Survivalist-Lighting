package com.github.wolfiewaffle.survivalistlighting.crafting;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.init.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {

	private static Item[] unlitTorches = {ModItems.torch_unlit, 
									ModItems.torch_coke_unlit, 
									ModItems.torch_stone_unlit, 
									ModItems.torch_stone_coke_unlit};
	private final static ItemStack coal = new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE);
	private final static ItemStack glowstone = new ItemStack(Items.GLOWSTONE_DUST);
	private final static int wc = OreDictionary.WILDCARD_VALUE;

	public static void init() {

		/*
		 * Remove old recipes
		 */
		RecipeRemover.removeAnyRecipe(Item.getItemFromBlock((Blocks.TORCH)));

		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
			RecipeRemover.removeAnyRecipe(Item.getItemFromBlock(slimeknights.tconstruct.gadgets.TinkerGadgets.stoneTorch));
		}

		/*
		 * Add glowstone vanilla torch recipes
		 */
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Item.getItemFromBlock(Blocks.TORCH)), 
				"AAA", " B ", " C ",
				'A', glowstone, 
				'B', coal, 
				'C', "stickWood"
			));

		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(Item.getItemFromBlock(slimeknights.tconstruct.gadgets.TinkerGadgets.stoneTorch)), 
					"AAA", " B ", " C ",
					'A', glowstone, 
					'B', coal, 
					'C', "rodStone"
				));
		}
		/*
		 * Add new torch recipes
		 */
		// Basic torches
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Item.getItemFromBlock(ModBlocks.torch_unlit)), 
				"A", "B", 
				'A', coal, 
				'B', "stickWood"
			));

		// If coke torches are enabled
		if (ModConfig.configCokeTorches) GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Item.getItemFromBlock(ModBlocks.torch_coke_unlit)), 
				"A", "B", 
				'A', "fuelCoke", 
				'B', "stickWood"
			));

		// If stone torches are enabled (and TCon is installed)
		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_unlit)), 
					"A", "B", 
					'A', coal, 
					'B', 
					"rodStone"
				));

			// If stone coke torches are also enabled, add those recipes
			if (ModConfig.configCokeTorches) {

				GameRegistry.addRecipe(new ShapedOreRecipe(
						new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_coke_unlit)), 
						"A", "B", 
						'A', "fuelCoke", 
						'B', "rodStone"
					));
			}
		}


		/*
		 * Light in inventory torch recipes
		 */
		if (ModConfig.configLightInInventory) {

			// Free lighter items
			for (String string : ModConfig.configFreeLightItems) {
				// Shorten names
				ItemStack itemStack = new ItemStack(Item.getByNameOrId(string), 1, wc);

				// Torches
				GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
						new ItemStack(ModItems.torch_lit), 
						new ItemStack(ModItems.torch_unlit, 1, wc), 
						itemStack
					));

				// Coke torches
				if (ModConfig.configCokeTorches) {
					GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
							new ItemStack(ModItems.torch_coke_lit), 
							new ItemStack(ModItems.torch_coke_unlit, 1, wc), 
							itemStack
						));
				}

				// Stone torches
				if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
					GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
							new ItemStack(ModItems.torch_stone_lit), 
							new ItemStack(ModItems.torch_stone_unlit, 1, wc), 
							itemStack
						));

					// Stone coke torches
					if (ModConfig.configCokeTorches) {
						GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
								new ItemStack(ModItems.torch_stone_coke_lit), 
								new ItemStack(ModItems.torch_stone_coke_unlit, 1, wc), 
								itemStack
							));
					}
				}
			}

			// Consumed or damaged lighter items
			for (String string : ModConfig.configLightItems) {
				// Shorten names
				ItemStack itemStack = new ItemStack(Item.getByNameOrId(string), 1, wc);

				if (itemStack.getItem().isDamageable()) {

					// Torches
					GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
							new ItemStack(ModItems.torch_lit), 
							new ItemStack(ModItems.torch_unlit, 1, wc), 
							itemStack
						));

					// Coke torches
					if (ModConfig.configCokeTorches) GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
							new ItemStack(ModItems.torch_coke_lit), 
							new ItemStack(ModItems.torch_coke_unlit, 1, wc), 
							itemStack
						));

					// Stone torches
					if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
						GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
								new ItemStack(ModItems.torch_stone_lit), 
								new ItemStack(ModItems.torch_stone_unlit, 1, wc), 
								itemStack
							));

						// Stone coke torches
						if (ModConfig.configCokeTorches) {
							GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
									new ItemStack(ModItems.torch_stone_coke_lit), 
									new ItemStack(ModItems.torch_stone_coke_unlit, 1, wc), 
									itemStack
								));
						}
					}

				// Item is not damageable
				} else {

					// Torches
					GameRegistry.addRecipe(new ShapelessOreRecipe(
							new ItemStack(ModItems.torch_lit), 
							new ItemStack(ModItems.torch_unlit, 1, wc), 
							itemStack
						));

					// Coke torches
					if (ModConfig.configCokeTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(
							new ItemStack(ModItems.torch_coke_lit), 
							new ItemStack(ModItems.torch_coke_unlit, 1, wc), 
							itemStack
						));

					// Stone torches
					if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
						GameRegistry.addRecipe(new ShapelessOreRecipe(
								new ItemStack(ModItems.torch_stone_lit), 
								new ItemStack(ModItems.torch_stone_unlit, 1, wc), 
								itemStack
							));

						// Stone coke torches
						if (ModConfig.configCokeTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(
								new ItemStack(ModItems.torch_stone_coke_lit), 
								new ItemStack(ModItems.torch_stone_coke_unlit, 1, wc), 
								itemStack
							));
					}
				}
			}
		}
	}
}