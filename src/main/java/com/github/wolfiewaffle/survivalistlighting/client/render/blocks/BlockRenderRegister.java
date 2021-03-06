package com.github.wolfiewaffle.survivalistlighting.client.render.blocks;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class BlockRenderRegister {
	public static String modid = SurvivalistLighting.MODID;

    public static void registerBlockRenderer() {
    	//reg(ModBlocks.torch_burnt, "torch_burnt");
    	//reg(ModBlocks.torch_lit, "torch_lit");
    	//reg(ModBlocks.torch_unlit, "torch_unlit");
    	//reg(ModBlocks.torch_coke_lit, "torch_coke_lit");
    	//reg(ModBlocks.torch_coke_unlit, "torch_coke_unlit");
    	//reg(ModBlocks.test_block, "test_block");
    }

	public static void reg(Block block, String name) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation("survivalistlighting:" + name, "inventory"));
	}
}
