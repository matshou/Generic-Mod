package com.user.genericmod.init;

import com.user.genericmod.item.*;

/** 
 *  All custom items are initialized and stored here. <br>
 *  If you need an instance of an item, get it from here.
 */
public class ModItems 
{
	public static final SimpleApple SIMPLE_APPLE;

	static
	{
		SIMPLE_APPLE = new SimpleApple(4, 0.3F, false);   // created with same values as a vanilla apple
	}
}
