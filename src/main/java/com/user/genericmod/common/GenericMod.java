package com.user.genericmod.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = GenericMod.MODID, version = GenericMod.VERSION, name= GenericMod.NAME, acceptedMinecraftVersions = "[1.10,1.10.2]")

public class GenericMod 
{
	public static final String MODID = "genericmod";
	public static final String NAME = "Generic Mod";
    public static final String VERSION = "1.0.0";
    
    // Use this thing to print in the console:
    public static final Logger logger = LogManager.getLogger(MODID);
    
    /** The instance of our mod that Forge uses. */
	@Mod.Instance(MODID)
	public static GenericMod instance;

    public static CommonProxy proxy;
	
	/** Run before anything else. Read your config, create blocks, items, etc, and register them with the GameRegistry. */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		proxy.preInit(event);   
	}
	
	/** Do your mod setup. Build whatever data structures you care about. Register recipes. */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		proxy.init(event);
	}
	
	/** Handle interaction with other mods, register event handlers, complete your setup based on this. */
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		// Register event handler here.
	}
}
