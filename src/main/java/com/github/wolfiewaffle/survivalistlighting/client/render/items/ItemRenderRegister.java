package com.github.wolfiewaffle.survivalistlighting.client.render.items;

import com.github.wolfiewaffle.survivalistlighting.SurvivalistLighting;
import com.github.wolfiewaffle.survivalistlighting.init.ModItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ItemRenderRegister {
	public static String modid = SurvivalistLighting.MODID;

	public static void registerItemRenderer() {
		// Torches
		reg(ModItems.torch_burnt, "torch_burnt");
		reg(ModItems.torch_lit, "torch_lit");
		reg(ModItems.torch_unlit, "torch_unlit");

		// Coke torches
		reg(ModItems.torch_coke_lit, "torch_coke_lit");
		reg(ModItems.torch_coke_unlit, "torch_coke_unlit");

		// Stone torches
		reg(ModItems.torch_stone_burnt, "torch_stone_burnt");
		reg(ModItems.torch_stone_lit, "torch_stone_lit");
		reg(ModItems.torch_stone_unlit, "torch_stone_unlit");

		// Stone coke torches
		reg(ModItems.torch_stone_coke_lit, "torch_stone_coke_lit");
		reg(ModItems.torch_stone_coke_unlit, "torch_stone_coke_unlit");

		// Bone torches
		reg(ModItems.torch_bone_burnt, "torch_bone_burnt");
		reg(ModItems.torch_bone_lit, "torch_bone_lit");
		reg(ModItems.torch_bone_unlit, "torch_bone_unlit");

		// Bone coke torches
		reg(ModItems.torch_bone_coke_lit, "torch_bone_coke_lit");
		reg(ModItems.torch_bone_coke_unlit, "torch_bone_coke_unlit");
	}

	public static void reg(Item item, String name) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation("survivalistlighting:" + name, "inventory"));
	}
}
