package powerstorage.liquid;

import powerstorage.PowerStorage;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import buildcraft.core.utils.StringUtil;

public class ItemBucketMercury extends ItemBucket {

	public ItemBucketMercury(int i) {
		super(i, PowerStorage.mercuryMoving.blockID);
		
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return StringUtil.localize(getItemDisplayName(itemstack));
	}

	

}