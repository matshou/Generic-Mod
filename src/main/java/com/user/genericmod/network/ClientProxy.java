package com.user.genericmod.network;

import com.user.genericmod.common.CommonProxy;
import com.user.genericmod.common.GenericMod;
import com.user.genericmod.init.ModItems;
import com.user.genericmod.item.SimpleApple;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy
{
	@Override
	@SideOnly(Side.CLIENT)
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		
		String prefix = GenericMod.MODID + ":";
	}
}
