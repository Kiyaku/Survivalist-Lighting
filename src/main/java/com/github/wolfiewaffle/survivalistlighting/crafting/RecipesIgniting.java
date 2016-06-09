package com.github.wolfiewaffle.survivalistlighting.crafting;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipesIgniting {

	private static Item[] unlitTorches = {ModItems.torch_unlit, 
			ModItems.torch_coke_unlit, 
			ModItems.torch_stone_unlit, 
			ModItems.torch_stone_coke_unlit,
			ModItems.torch_bone_unlit,
			ModItems.torch_bone_coke_unlit};
	private final static int wc = OreDictionary.WILDCARD_VALUE;

	public static void init() {
		// Light in inventory torch recipes
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
	
				// Bone torches
				if (ModConfig.configBoneTorches) {
					GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
							new ItemStack(ModItems.torch_bone_lit), 
							new ItemStack(ModItems.torch_bone_unlit, 1, wc), 
							itemStack
						));
	
					// Bone coke torches
					if (ModConfig.configCokeTorches) {
						GameRegistry.addRecipe(new ShapelessReuseRecipe(unlitTorches, 
								new ItemStack(ModItems.torch_bone_coke_lit), 
								new ItemStack(ModItems.torch_bone_coke_unlit, 1, wc), 
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
	
					// Bone torches
					if (ModConfig.configBoneTorches) {
						GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
								new ItemStack(ModItems.torch_bone_lit), 
								new ItemStack(ModItems.torch_bone_unlit, 1, wc), 
								itemStack
							));
	
						// Bone coke torches
						if (ModConfig.configCokeTorches) {
							GameRegistry.addRecipe(new ShapelessDamageRecipe(unlitTorches, 
									new ItemStack(ModItems.torch_bone_coke_lit), 
									new ItemStack(ModItems.torch_bone_coke_unlit, 1, wc), 
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
	
					// Bone torches
					if (ModConfig.configBoneTorches) {
						GameRegistry.addRecipe(new ShapelessOreRecipe(
								new ItemStack(ModItems.torch_bone_lit), 
								new ItemStack(ModItems.torch_bone_unlit, 1, wc), 
								itemStack
							));
	
						// Bone coke torches
						if (ModConfig.configBoneTorches) GameRegistry.addRecipe(new ShapelessOreRecipe(
								new ItemStack(ModItems.torch_bone_coke_lit), 
								new ItemStack(ModItems.torch_bone_coke_unlit, 1, wc), 
								itemStack
							));
					}
				}
			}
		}
	}
}
