package powerstorage.liquid;

import powerstorage.CommonProxy;
import powerstorage.PowerStorage;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import buildcraft.core.utils.StringUtil;

public class ItemBucketMercury extends ItemBucket {

	public ItemBucketMercury(int i) {
		super(i, PowerStorage.mercuryMoving.blockID);
		iconIndex = 1;
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return StringUtil.localize(getItemNameIS(itemstack));
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.TEXTURE_ITEMS;
	}

}