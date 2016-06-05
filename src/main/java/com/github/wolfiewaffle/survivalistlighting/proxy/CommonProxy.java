package com.github.wolfiewaffle.survivalistlighting.proxy;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.init.ModBlocks;
import com.github.wolfiewaffle.survivalistlighting.init.ModItems;
import com.github.wolfiewaffle.survivalistlighting.tileentity.ModTileEntities;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    	ModConfig.init(e);
    	ModBlocks.createBlocks();
    	ModItems.createItems();
    	ModTileEntities.init();
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
