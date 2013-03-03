package powerstorage.core;

import powerstorage.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import buildcraft.core.utils.StringUtil;


public class PSItem extends Item{


	public PSItem(int i) {
		super(i);
		setTextureFile(CommonProxy.ITEMS_PNG);
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return StringUtil.localize(getItemNameIS(itemstack));
	}
	         
}