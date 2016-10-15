package com.user.genericmod.item;

import java.awt.Color;

import com.user.genericmod.common.GenericMod;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This is a custom version of the vanilla feather in Minecraft. <p>
 * 
 * It contains three item subtypes that share the same model and texture file. <br>
 * Each subtype is assigned a different color value that is used to display them <br>
 * with a differently colored texture in-game.
 */
public class ColoredFeather extends Item
{
	public ColoredFeather()
	{
		this.setCreativeTab(CreativeTabs.MATERIALS);
		this.setHasSubtypes(true);
	}
	
	/**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT. <p>
     * 
     * With the use of this we can have different names for different subtypes without having separate model files.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        return "item." + FeatherType.getTypeNameByMeta(stack.getMetadata());
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, java.util.List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn));           // FEATHER_RED
        subItems.add(new ItemStack(itemIn, 1, 1));     // FEATHER_GREEN
        subItems.add(new ItemStack(itemIn, 1, 2));     // FEATHER_BLUE
	}
    
	/** 
	 * Register an instance of this class with Minecraft and associate it with this item <br>
	 * to get your colors to apply to different subtypes.
     */
	public static class ColorHandler implements IItemColor 
	{
		/** 
		 * Return a color value you wish the stack be colored with.<br>
		 * It will start getting called from loading the world onwards.
		 * 
		 * @param tintIndex index of the layer <i>(model file property)</i> to be colored
		 */
		@Override
		public int getColorFromItemstack(ItemStack stack, int tintIndex) 
		{
			return tintIndex > 0 ? -1 : FeatherType.getTypeColorByMeta(stack.getMetadata());
		}
	}
    
    /**
     * This is where we store information about feather subtypes. <br>
     * Each subtype has a metadata value and a name.
     */
    public static enum FeatherType
    {    	
    	/*
    	 * On the MinecraftForge forum it was suggested to me that I should
    	 * use HEX instead of RGB color values, as it improves readability.
    	 * 
    	 * Personally I found this to be the opposite, so it's just a matter of taste.
    	 * If you find HEX values more pleasing to the eye, you may use them instead.
    	 */
    	
    	FEATHER_RED(0, new Color(160, 36, 36), "feather_red"),
    	FEATHER_GREEN(1, new Color(36, 160, 36), "feather_green"),
    	FEATHER_BLUE(2, new Color(36, 36, 160), "feather_blue");
    	
    	private final int meta;
    	private final Color color;
    	private final String typeName;
    	
    	private FeatherType(int meta, Color color, String name)
    	{
    		this.meta = meta;
    		this.color = color;
    		typeName = name;
    	}
    	
    	/** 
    	 * Get a <i>decimal</i> expression of the type's <i>(found by meta)</i> RGB color value.
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @return color value of <code>FEATHER_RED</code> if <code>meta</code> was an unregistered value.
    	 */
    	private static int getTypeColorByMeta(int meta)
    	{
    		for (FeatherType type : FeatherType.values())
    		{
    			if (type.meta == meta)
    				return type.color.getRGB();
    		}
    		
    		GenericMod.logger.warn("Tried to get type color with an unregistered metadata value, "
    				+ "returning default value instead.", new IllegalArgumentException());
    		
    		return FEATHER_RED.color.getRGB();
    	}
    	
    	/** 
    	 * Get apple type name from metadata value. <br>
    	 * 
    	 * @param meta used to distinguish different item subtypes.  
    	 * @return name of <code>FEATHER_RED</code> if <code>meta</code> was an unregistered value.
    	 */
    	public static String getTypeNameByMeta(int meta)
    	{
    		for (FeatherType type : FeatherType.values())
    		{
    			if (type.meta == meta)
    				return type.typeName;
    		}
    		
    		GenericMod.logger.warn("Tried to get type name with an unregistered metadata value, "
    				+ "returning default value instead.", new IllegalArgumentException());
    		
    		return FEATHER_RED.typeName;
    	}
    }
}