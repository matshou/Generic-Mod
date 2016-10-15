package com.user.genericmod.network;

import com.user.genericmod.common.CommonProxy;
import com.user.genericmod.common.GenericMod;
import com.user.genericmod.init.ModItems;
import com.user.genericmod.item.ColoredFeather;
import com.user.genericmod.item.SimpleApple;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
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
		
		/* 
		 * This is where we register our custom item model files.
		 * If you do not register the model file for your item, the item will not be properly displayed in-game,
		 * because Minecraft will have instructions on how to display it.
		 * 
		 * The ModelResourceLocation points to a blockstate json file (denoted by the resource domain and file path) and then a variant inside it. 
		 * The blockstate json now specifies how this variant should be rendered (base model, texture overrides, transformations, etc.).
		 * 
         * A special case is added for item models, if the variant is named "inventory" the game will first look into models/item to load 
         * an item model from there, ignoring any blockstate json.
         * 
         * For items with custom item colors assigned by the ColorHandler; register all subtypes with the same model.
         * The instruction on how to color the textures will come from the handler not the model file.
		 */
		
		ModelLoader.setCustomModelResourceLocation(ModItems.COLORED_FEATHER, 0, new ModelResourceLocation(ModItems.COLORED_FEATHER.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.COLORED_FEATHER, 1, new ModelResourceLocation(ModItems.COLORED_FEATHER.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.COLORED_FEATHER, 2, new ModelResourceLocation(ModItems.COLORED_FEATHER.getRegistryName().toString()));	
		
		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_APPLE, 0, new ModelResourceLocation(ModItems.SIMPLE_APPLE.getRegistryName().toString()));
		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_APPLE, 1, new ModelResourceLocation(prefix + SimpleApple.AppleType.getTypeNameByMeta(1)));
		ModelLoader.setCustomModelResourceLocation(ModItems.SIMPLE_APPLE, 2, new ModelResourceLocation(prefix + SimpleApple.AppleType.getTypeNameByMeta(2)));
	}
	
	@Override
	public void init(FMLInitializationEvent event) 
	{
		/*
		 *  Register color handler here and associate it with our mod item.
	     */
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new ColoredFeather.ColorHandler(), ModItems.COLORED_FEATHER);
	}
}