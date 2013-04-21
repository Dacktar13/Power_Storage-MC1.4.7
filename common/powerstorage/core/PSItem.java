package powerstorage.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import buildcraft.core.utils.StringUtil;


public class PSItem extends Item{


	public PSItem(int i) {
		super(i);
		
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return StringUtil.localize(getItemDisplayName(itemstack));
	}
	         
}