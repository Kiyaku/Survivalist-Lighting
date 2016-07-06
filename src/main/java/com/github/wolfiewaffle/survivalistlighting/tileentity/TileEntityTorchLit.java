package com.github.wolfiewaffle.survivalistlighting.tileentity;

import com.github.wolfiewaffle.survivalistlighting.ModConfig;
import com.github.wolfiewaffle.survivalistlighting.blocks.BlockTorchBasicLit;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchLit extends TileEntity implements net.minecraft.util.ITickable {
	public static final String publicName = "tileEntityTorchLit";
	private int tickCounter = 0; // Used to count seconds
	private int fuel = 0;

	/**
	 * @return The current fuel value of the TileEntity
	 */
	public int getFuel() {
		return this.fuel;
	}

	/**
	 * Sets the fuel value of the TileEntity
	 * @param f The new fuel value
	 */
	public void setFuel(int f) {
		this.fuel = f;
	}

	// Needed for NBT
	@Override
	public NBTTagCompound func_189515_b(NBTTagCompound p_189515_1_) {
		p_189515_1_.setInteger("torchFuelNBT", getFuel());
		return super.func_189515_b(p_189515_1_);
	}

	// Needed for NBT
	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		this.fuel = par1.getInteger("torchFuelNBT");
	}

	@Override
	public void update() {
		// Don't update on the client
		if (worldObj.isRemote) {
			return;
		}

		tickCounter++;

		// If one second has passed
		if (tickCounter == 20) {
			fuel --;

			//Mark that the value has changed
			markDirty();

			//If the new fuel value is less than 0, replace the block with a Burnt Torch.
			if (fuel < 0) {
				if (ModConfig.configDebug)
					System.out.printf("Torch at %d, %d, %d has burnt (fuel %d)\n", pos.getX(), pos.getY(), pos.getZ(), fuel);
				((BlockTorchBasicLit) getBlockType()).burnOut(worldObj, pos);
			}

			tickCounter = 0;
		}
	}
}
