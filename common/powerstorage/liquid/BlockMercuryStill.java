package powerstorage.liquid;

import powerstorage.CommonProxy;
import powerstorage.PowerStorage;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;



public class BlockMercuryStill extends BlockStationary implements ILiquid {


	public BlockMercuryStill(int i, Material material) {
		super(i, material);

		setHardness(100F);
		setLightOpacity(3);
	}

	@Override
	public int getRenderType() {
		return PowerStorage.mercuryModel;
	}

	@Override
	public String getTextureFile() {
		return CommonProxy.TEXTURE_BLOCKS;
	}

	@Override
	public int stillLiquidId() {
		return PowerStorage.mercuryStill.blockID;
	}

	@Override
	public boolean isMetaSensitive() {
		return false;
	}

	@Override
	public int stillLiquidMeta() {
		return 0;
	}

	@Override
	public boolean isBlockReplaceable(World world, int i, int j, int k) {
		return true;
	}

}