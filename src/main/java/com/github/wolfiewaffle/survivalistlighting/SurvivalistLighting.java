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

@Mod(modid = SurvivalistLighting.MODID, name = SurvivalistLighting.MODNAME, version = SurvivalistLighting.VERSION, dependencies = SurvivalistLighting.DEPENDENCIES)
public class SurvivalistLighting {
	// This is the 1.9.4 branch
	public static final String MODID = "survivalistlighting";
	public static final String MODNAME = "Survivalist Lighting";
	public static final String VERSION = "@VERSION@";
	public static final String DEPENDENCIES = "required-after:tconstruct;required-after:bonetorch";

	// Mod compat
	public static boolean isTconInstalled = false;
	public static boolean isBonetorchInstalled = false;

	// Proxy
	@SidedProxy(clientSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ClientProxy", serverSide = "com.github.wolfiewaffle.survivalistlighting.proxy.ServerProxy")
	public static CommonProxy proxy;

	// Instance so we can refer to the mod later
	@Instance
	public static SurvivalistLighting instance = new SurvivalistLighting();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		isTconInstalled = Loader.isModLoaded("tconstruct");
		isBonetorchInstalled = Loader.isModLoaded("bonetorch");

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
