package powerstorage.core;

import powerstorage.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MercuryIngot extends Item {

	public MercuryIngot(int id) {
		super(id);
		
		// Constructor Config
		maxStackSize = 64;
        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName("mercuryIngot");

	}

	public String getTextureFile() {
		return CommonProxy.TEXTURE_ITEMS;
	}
}
