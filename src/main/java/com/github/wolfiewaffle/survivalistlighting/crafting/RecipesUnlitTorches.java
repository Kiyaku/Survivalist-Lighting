package com.github.wolfiewaffle.survivalistlighting.crafting;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.init.ModBlocks;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class RecipesUnlitTorches {

	private final static ItemStack coal = new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE);
	private final static ItemStack bone = new ItemStack(Items.BONE);

	public static void init() {

		// Basic torches
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Item.getItemFromBlock(ModBlocks.torch_unlit)), 
				"A", "B", 
				'A', coal, 
				'B', "stickWood"
			));

		// Coke torches
		if (ModConfig.configCokeTorches) GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(Item.getItemFromBlock(ModBlocks.torch_coke_unlit)), 
				"A", "B", 
				'A', "fuelCoke", 
				'B', "stickWood"
			));

		// Stone torches
		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_unlit)), 
					"A", "B", 
					'A', coal, 
					'B', "rodStone"
				));

			// Stone coke torches
			if (ModConfig.configCokeTorches) {

				GameRegistry.addRecipe(new ShapedOreRecipe(
						new ItemStack(Item.getItemFromBlock(ModBlocks.torch_stone_coke_unlit)), 
						"A", "B", 
						'A', "fuelCoke", 
						'B', "rodStone"
					));
			}
		}

		// Bone torches
		if (ModConfig.configBoneTorches) {

			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(Item.getItemFromBlock(ModBlocks.torch_bone_unlit)), 
					"A", "B", 
					'A', coal, 
					'B', bone
				));

			// Stone coke torches
			if (ModConfig.configCokeTorches) {

				GameRegistry.addRecipe(new ShapedOreRecipe(
						new ItemStack(Item.getItemFromBlock(ModBlocks.torch_bone_coke_unlit)), 
						"A", "B", 
						'A', "fuelCoke", 
						'B', bone
					));
			}
		}
	}
}
