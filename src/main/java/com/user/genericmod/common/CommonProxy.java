package com.user.genericmod.common;

import com.user.genericmod.init.ModItems;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	/** Called by {@link GenericMod#preInit } in the preInit phase of mod loading. */
	public void preInit(FMLPreInitializationEvent event) 
	{	
		// Read your config, create blocks, items, etc, and register them with the GameRegistry.
		
		registerCommons();
	}
	
	/** 
	 *  Register all items and blocks from the resource library with Forge. <br>
	 *  This is called when the mod is passing the pre-initialization phase.
	 */
	private void registerCommons() 
	{
		GenericMod.logger.info("Preparing to register item and block instances...");
		
		GenericMod.logger.info("Finished registering object instances. ");
	}
	
	/** 
	 * Register your mod item with Forge.
	 * 
	 * @param <T> making sure the object passed is an Item
	 * @param item instance of your mod item you want to register
	 * @param name String this item will be registered under
	 */
	private static <T extends net.minecraft.item.Item> T registerItem(T item, String name) 
	{
		item.setUnlocalizedName(name);
		item.setRegistryName(name);
		return GameRegistry.register(item);
	}
	
	/** Called by {@link GenericMod#init } in the init phase of mod loading. */
	public void init(FMLInitializationEvent event) 
	{
		// Build whatever data structures you care about. Register recipes.
	}
}
