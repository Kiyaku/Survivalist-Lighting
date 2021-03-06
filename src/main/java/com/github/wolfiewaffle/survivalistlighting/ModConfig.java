package com.github.wolfiewaffle.survivalistlighting;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ModConfig {

	// Config options
	public static boolean configDebug;
	public static int configTorchFuel;
	public static boolean configCokeTorches;
	public static boolean configStoneTorches;
	public static boolean configBoneTorches;
	public static int configTorchCokeFuel;
	public static String[] configLightItems;
	public static String[] configFreeLightItems;
	public static String[] configInWorldLightItems;
	public static int configTorchDropMode;
	public static boolean configLightInInventory;
	public static float configSmolderPercent;

	// Set of resource locations to store for crafting
	public static Set<ResourceLocation> lightItems = new HashSet<ResourceLocation>();
	public static Set<ResourceLocation> freeLightItems = new HashSet<ResourceLocation>();
	public static Set<ResourceLocation> inWorldLightItems = new HashSet<ResourceLocation>();

	// Event to create config file
	public static void init(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + "/survivalistlighting.cfg"));

		config.load();

		// Clear the set
		freeLightItems.clear();

		// Get config options
		configDebug = config.getBoolean("debug output", Configuration.CATEGORY_GENERAL, false, "Output debug info. Not useful for players.");
		configTorchFuel = config.getInt("regular torch fuel", Configuration.CATEGORY_GENERAL, 2000, 1, Integer.MAX_VALUE, "The max duration of a basic torch in seconds. Might not update already placed torches.");
		configTorchCokeFuel = config.getInt("coke torch fuel", Configuration.CATEGORY_GENERAL, 4000, 1, Integer.MAX_VALUE, "The max duration of a coke torch in seconds. Might not update already placed torches. (Requires enable coke torches: true)");
		configCokeTorches = config.getBoolean("enable coke torches", Configuration.CATEGORY_GENERAL, false, "Enable coke torches. Requires a mod that adds oreDictionary coal coke.");
		configStoneTorches = config.getBoolean("enable stone torches", Configuration.CATEGORY_GENERAL, true, "Enable stone torches. Requires Tinker's Construct.");
		configBoneTorches = config.getBoolean("enable bone torches", Configuration.CATEGORY_GENERAL, true, "Enable bone torches. Requires SBM Bone Torch Mod.");
		configLightItems = config.getStringList("consumed lighter items", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:flint", "minecraft:flint_and_steel", "minecraft:fire_charge"}, "A list of items that can be used to light a torch. If the item is not damageable, it will be consumed.");
		configFreeLightItems = config.getStringList("free lighter items", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:torch", "minecraft:lava_bucket", "survivalistlighting:torch_lit", "survivalistlighting:torch_coke_lit", "survivalistlighting:torch_stone_lit", "survivalistlighting:torch_stone_coke_lit", "tconstruct:stone_torch", "survivalistlighting:torch_bone_lit", "survivalistlighting:torch_bone_coke_lit", "bonetorch:boneTorch"}, "A list of items that can be used to light a torch. These will not be damaged or consumed.");
		configInWorldLightItems = config.getStringList("in world lighter items", Configuration.CATEGORY_GENERAL, new String[] {"minecraft:torch", "survivalistlighting:torch_lit", "survivalistlighting:torch_coke_lit", "survivalistlighting:torch_stone_lit", "survivalistlighting:torch_stone_coke_lit", "tconstruct:stone_torch", "survivalistlighting:torch_bone_lit", "survivalistlighting:torch_bone_coke_lit", "bonetorch:boneTorch", "toughasnails:campfire"}, "A list of blocks that can be used to light a torch by right clicking them.");
		configTorchDropMode = config.getInt("torch drop mode", Configuration.CATEGORY_GENERAL, 0, 0, 2, "0: Torches drop as lit torches when broken\n1: Torches drop as unlit torches when broken\n2: Torches burn out completely when broken");
		configLightInInventory = config.getBoolean("light torches in inventory", Configuration.CATEGORY_GENERAL, true, "Allow lighting torches in the crafting grid.");
		configSmolderPercent = config.getFloat("smolder percent", Configuration.CATEGORY_GENERAL, 0.25F, Float.MIN_NORMAL, Float.MAX_VALUE, "The percent of fuel at which torches will dim. 0.25 is 25%.");		

		// Add all the items to the resource location set
		for (String string : configLightItems) {
			ResourceLocation rl = new ResourceLocation(string);
			lightItems.add(rl);
		}

		// Add all the items to the resource location set
		for (String string : configFreeLightItems) {
			ResourceLocation rl = new ResourceLocation(string);
			freeLightItems.add(rl);
		}

		// Add all the items to the resource location set
		for (String string : configInWorldLightItems) {
			ResourceLocation rl = new ResourceLocation(string);
			inWorldLightItems.add(rl);
		}

		config.save();
	}
}
