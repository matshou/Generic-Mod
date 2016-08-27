package com.user.genericmod.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy 
{
	/** Called by {@link GenericMod#preInit } in the preInit phase of mod loading. */
	public void preInit(FMLPreInitializationEvent event) 
	{	
		// Read your config, create blocks, items, etc, and register them with the GameRegistry.
	}
	
	/** Called by {@link GenericMod#init } in the init phase of mod loading. */
	public void init(FMLInitializationEvent event) 
	{
		// Build whatever data structures you care about. Register recipes.
	}
}
