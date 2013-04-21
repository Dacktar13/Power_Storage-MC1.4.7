package powerstorage.core;

	import java.util.Random;

import powerstorage.PowerStorage;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

	public class MercuryOre extends Block {

	public MercuryOre(int id,  Material material) {
		super(id, material);
                
                setHardness(4.0F); // 33% harder than diamond
                setStepSound(Block.soundStoneFootstep);
                setUnlocalizedName("mercuryOre");
                setCreativeTab(CreativeTabs.tabBlock);
        }
        
    
        @Override
        public int idDropped(int par1, Random random, int par2) {
                return PowerStorage.mercuryOre.blockID;
        }
}