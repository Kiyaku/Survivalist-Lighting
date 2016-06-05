package com.github.wolfiewaffle.survivalistlighting.blocks;

import java.util.Random;

import com.github.wolfiewaffle.survivalistlighting.init.ModBlocks;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorchBasicBurnt extends BlockTorch {

	public BlockTorchBasicBurnt(String name, boolean includeInCreative) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(this.getRegistryName().toString());

		if (includeInCreative) this.setCreativeTab(ModBlocks.tabTorches);
		else this.setCreativeTab(null);
	}

	// No particles
	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	}
}
