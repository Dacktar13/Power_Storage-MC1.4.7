package powerstorage.client;

import net.minecraftforge.client.MinecraftForgeClient;
import powerstorage.CommonProxy;

public class ClientProxy extends CommonProxy {
        
        @Override
        public void registerRenderers() {
                MinecraftForgeClient.preloadTexture(TEXTURE_ITEMS);
                MinecraftForgeClient.preloadTexture(TEXTURE_BLOCKS);
        }
        
}