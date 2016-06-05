package com.github.wolfiewaffle.survivalistlighting;

import com.github.wolfiewaffle.survivalistlighting.crafting.Recipes;
import com.github.wolfiewaffle.survivalistlighting.proxy.CommonProxy;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = SurvivalistLighting.MODID, name = SurvivalistLighting.MODNAME, version = SurvivalistLighting.VERSION)
public class SurvivalistLighting {
	public static final String MODID = "survivalistlighting";
	public static final String MODNAME = "Survivalist Lighting";
	public static final String VERSION = "@VERSION@";

	public static boolean isTconInstalled = false;

	@SidedProxy(clientSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ClientProxy", serverSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance so we can refer to the mod later
	@Instance
	public static SurvivalistLighting instance = new SurvivalistLighting();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		isTconInstalled = Loader.isModLoaded("tconstruct");
		if (ModConfig.configDebug) System.out.printf("SURVIVALIST_LIGHTING: Is Tcon installed? %b\n", isTconInstalled);

		SurvivalistLighting.proxy.preInit(event);
	}

	@EventHandler
	public void Init(FMLInitializationEvent event) {
		SurvivalistLighting.proxy.init(event);

		Recipes.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		SurvivalistLighting.proxy.postInit(event);
	}
}
