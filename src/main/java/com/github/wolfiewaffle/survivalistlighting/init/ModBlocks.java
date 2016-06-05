package com.github.wolfiewaffle.survivalistlighting.init;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.blocks.BlockTorchBasicBurnt;
import com.github.wolfiewaffle.survivalistlighting.blocks.BlockTorchBasicLit;
import com.github.wolfiewaffle.survivalistlighting.blocks.BlockTorchBasicUnlit;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import slimeknights.mantle.client.CreativeTab;

public final class ModBlocks {

	private static final int fuelReg = ModConfig.configTorchFuel;
	private static final int fuelCoke = ModConfig.configTorchCokeFuel;

	private static boolean REG_COKE = ModConfig.configCokeTorches;
	private static boolean REG_STONE = ModConfig.configStoneTorches && SurvivalistLighting.isTconInstalled;
	private static boolean REG_STONE_COKE = REG_STONE && REG_COKE;

	// Torches
	public static Block torch_burnt;
	public static Block torch_lit;
	public static Block torch_unlit;

	// Coke torches
	public static Block torch_coke_lit;
	public static Block torch_coke_unlit;

	// Stone torches
	public static Block torch_stone_burnt;
	public static Block torch_stone_lit;
	public static Block torch_stone_unlit;

	// Stone coke torches
	public static Block torch_stone_coke_lit;
	public static Block torch_stone_coke_unlit;

	public static final CreativeTabs tabTorches = new CreativeTab("tabTorches", new ItemStack(Blocks.torch));

	public static void createBlocks() {

		System.out.println();
		if (ModConfig.configDebug) System.out.printf("SURVIVALIST_LIGHTING: REG_STONE: " + REG_STONE);
		System.out.println();

		// Torches
		GameRegistry.register(torch_burnt = new BlockTorchBasicBurnt("torch_burnt", true));
		GameRegistry.register(torch_lit = new BlockTorchBasicLit("torch_lit", fuelReg, torch_burnt, torch_unlit, true));
		GameRegistry.register(torch_unlit = new BlockTorchBasicUnlit("torch_unlit", fuelReg, torch_lit, true));

		// Coke torches
		GameRegistry.register(torch_coke_lit = new BlockTorchBasicLit("torch_coke_lit", fuelCoke, torch_burnt, torch_coke_unlit, REG_COKE));
		GameRegistry.register(torch_coke_unlit = new BlockTorchBasicUnlit("torch_coke_unlit", fuelCoke, torch_coke_lit, REG_COKE));

		// Stone torches
		GameRegistry.register(torch_stone_burnt = new BlockTorchBasicBurnt("torch_stone_burnt", REG_STONE));
		GameRegistry.register(torch_stone_lit = new BlockTorchBasicLit("torch_stone_lit", fuelReg, torch_stone_burnt, torch_stone_unlit, REG_STONE));
		GameRegistry.register(torch_stone_unlit = new BlockTorchBasicUnlit("torch_stone_unlit", fuelReg, torch_stone_lit, REG_STONE));

		// Stone coke torches
		GameRegistry.register(torch_stone_coke_lit = new BlockTorchBasicLit("torch_stone_coke_lit", fuelCoke, torch_stone_burnt, torch_stone_coke_unlit, REG_STONE_COKE));
		GameRegistry.register(torch_stone_coke_unlit = new BlockTorchBasicUnlit("torch_stone_coke_unlit", fuelCoke, torch_stone_coke_lit, REG_STONE_COKE));
	}
}
