package powerstorage.core;

import java.util.Random;
import powerstorage.PowerStorage;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import cpw.mods.fml.common.IWorldGenerator;

public class PSWorldGen implements IWorldGenerator {

    @Override
    public void generate (Random random, int chunkX, int chunkZ, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        compass(random, chunkX * 16, chunkZ * 16, world, chunkGenerator, chunkProvider);
    }

    private void compass (Random random, int x, int z, World world,
            IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        world.setBlock(x, 28, z, PowerStorage.mercuryOre.blockID);
    }
    

}