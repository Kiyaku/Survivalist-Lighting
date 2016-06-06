package com.github.wolfiewaffle.survivalistlighting.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTorchUnlit extends TileEntity {
	public static final String publicName = "tileEntityTorchUnlit";
	private int fuel = 0;

	/**
	 * @return The current fuel value of the TileEntity
	 */
	public int getFuelAmount() {
		return this.fuel;
	}

	/**
	 * Sets the fuel value of the TileEntity
	 * @param f The new fuel value
	 */
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}

	// Needed for NBT
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("torchFuelNBT", getFuelAmount());
		return super.writeToNBT(compound);
	}

	// Needed for NBT
	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		this.fuel = par1.getInteger("torchFuelNBT");
	}
}
