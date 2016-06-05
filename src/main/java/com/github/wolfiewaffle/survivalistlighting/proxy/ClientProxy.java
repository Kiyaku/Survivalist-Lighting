package com.github.wolfiewaffle.survivalistlighting.proxy;

import com.github.wolfiewaffle.survivalistlighting.client.render.blocks.BlockRenderRegister;
import com.github.wolfiewaffle.survivalistlighting.client.render.items.ItemRenderRegister;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    	super.preInit(e);

        ItemRenderRegister.registerItemRenderer();
        BlockRenderRegister.registerBlockRenderer();
    }

    public void init(FMLInitializationEvent e) {
    	super.init(e);
    }

    public void postInit(FMLPostInitializationEvent e) {
    	super.postInit(e);
    }
}
