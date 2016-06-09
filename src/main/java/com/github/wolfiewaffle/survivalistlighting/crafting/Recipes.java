package com.github.wolfiewaffle.survivalistlighting.crafting;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.init.ModItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes {

	private static Item[] toVanillaTorch = {
		ModItems.torch_lit, 
		ModItems.torch_unlit,
		ModItems.torch_coke_lit, 
		ModItems.torch_coke_unlit};
	private static Item[] toStoneTorch = {
		ModItems.torch_stone_lit, 
		ModItems.torch_stone_unlit,
		ModItems.torch_stone_coke_lit, 
		ModItems.torch_stone_coke_unlit};
	private static Item[] toBoneTorch = {
		ModItems.torch_bone_lit, 
		ModItems.torch_bone_unlit,
		ModItems.torch_bone_coke_lit, 
		ModItems.torch_bone_coke_unlit};
	private final static ItemStack coal = new ItemStack(Items.COAL, 1, OreDictionary.WILDCARD_VALUE);
	private final static ItemStack glowstone = new ItemStack(Items.GLOWSTONE_DUST);
	private final static ItemStack bone = new ItemStack(Items.BONE);
	private final static Item torchBone = Item.getItemFromBlock(ForgeRegistries.BLOCKS.getValue(new ResourceLocation("bonetorch:boneTorch")));
	private final static Item torchStone = Item.getItemFromBlock(slimeknights.tconstruct.gadgets.TinkerGadgets.stoneTorch);
	private final static Item torch = Item.getItemFromBlock(Blocks.TORCH);
	private final static int wc = OreDictionary.WILDCARD_VALUE;

	public static void init() {

		RecipesIgniting.init();
		RecipesUnlitTorches.init();

		// Remove old recipes
		RecipeRemover.removeAnyRecipe(Item.getItemFromBlock((Blocks.TORCH)));

		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
			RecipeRemover.removeAnyRecipe(torchStone);
		}

		if (ModConfig.configBoneTorches && SurvivalistLighting.isBonetorchInstalled) {
			RecipeRemover.removeAnyRecipe(torchBone);
		}

		// Add glowstone vanilla torch recipes
		GameRegistry.addRecipe(new ShapedOreRecipe(
				new ItemStack(torch), 
				"AAA", " B ", " C ",
				'A', glowstone, 
				'B', coal, 
				'C', "stickWood"
			));

		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled) {
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(torchStone), 
					"AAA", " B ", " C ",
					'A', glowstone, 
					'B', coal, 
					'C', "rodStone"
				));
		}

		if (ModConfig.configBoneTorches) {
			GameRegistry.addRecipe(new ShapedOreRecipe(
					new ItemStack(torchBone), 
					"AAA", " B ", " C ",
					'A', glowstone, 
					'B', coal, 
					'C', bone
				));
		}

		// Unlit to vanilla torch recipes
		for (Item item : toVanillaTorch) {
		 	GameRegistry.addRecipe(new ShapelessOreRecipe(
					new ItemStack(torch), 
					glowstone, 
					glowstone, 
					glowstone, 
					new ItemStack(item, 1, wc)
				));
		}

		if (ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled)
		for (Item item : toStoneTorch) {
		 	GameRegistry.addRecipe(new ShapelessOreRecipe(
					new ItemStack(torchStone), 
					glowstone, 
					glowstone, 
					glowstone, 
					new ItemStack(item, 1, wc)
				));
		}

		if (ModConfig.configBoneTorches)
		for (Item item : toBoneTorch) {
		 	GameRegistry.addRecipe(new ShapelessOreRecipe(
					new ItemStack(torchBone), 
					glowstone, 
					glowstone, 
					glowstone, 
					new ItemStack(item, 1, wc)
				));
		}
	}
}