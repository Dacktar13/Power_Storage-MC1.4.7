package powerstorage.factory;

import powerstorage.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public  class MercuryCubeEmpty extends Block {
	
	int topTexture;
	int sideTexture;

        public MercuryCubeEmpty (int id, int texture, Material material) {
                super(id, texture, Material.iron);
                
                
        }
        
        @Override
        public String getTextureFile () {
                return CommonProxy.TEXTURE_BLOCKS;
               
        }
}