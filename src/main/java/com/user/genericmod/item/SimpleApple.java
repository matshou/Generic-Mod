package com.user.genericmod.item;

import java.util.List;

import com.user.genericmod.common.GenericMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SimpleApple extends ItemFood 
{
	public SimpleApple(int amount, float saturation, boolean isWolfFood) 
	{
		super(amount, saturation, isWolfFood);
		this.setHasSubtypes(true);
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + AppleType.getTypeNameByMeta(stack.getMetadata());
    }
	
	 /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
    	subItems.add(new ItemStack(itemIn));           // APPLE_RED
        subItems.add(new ItemStack(itemIn, 1, 1));     // APPLE_GREEN
        subItems.add(new ItemStack(itemIn, 1, 2));     // APPLE_YELLOW
    } 
    
    
    public static enum AppleType
    {
    	APPLE_RED(0, "apple_red"),
    	APPLE_GREEN(1, "apple_green"),
    	APPLE_GOLD(2, "apple_yellow");
    	
    	private final int meta;
    	private final String typeName;
    	
    	private AppleType(int meta, String name)
    	{
    		this.meta = meta;
    		typeName = name;
    	}
    	
    	/** Get apple type name from metadata value. */
    	public static String getTypeNameByMeta(int meta)
    	{
    		for (AppleType type : AppleType.values())
    		{
    			if (type.meta == meta)
    				return type.typeName;
    		}
    		
    		GenericMod.logger.warn("Tried to get type name with an unregistered metadata value, "
    				+ "returning default value instead.", new IllegalArgumentException());
    		
    		return APPLE_RED.typeName;
    	}
    }
}
