package de.metaphoriker.pathetic.provider.v1_17;

import de.metaphoriker.pathetic.platform.ChunkSnapshot;
import de.metaphoriker.pathetic.platform.block.data.BlockData;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.ChunkStatus;
import de.metaphoriker.pathetic.api.snapshot.ChunkDataProvider;

public class v1_17ChunkDataProviderImpl implements ChunkDataProvider {

  @Override
  public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
    try {

      WorldServer server = ((CraftWorld) world).getHandle();
      CraftChunk newCraftChunk = new CraftChunk(server, chunkX, chunkZ);

      server.getChunkProvider().getChunkAt(chunkX, chunkZ, ChunkStatus.m, true);
      return newCraftChunk.getChunkSnapshot();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public BlockState getBlockState(ChunkSnapshot snapshot, int x, int y, int z) {
    BlockData data = snapshot.getBlockData(x, y, z);
    IBlockData state = ((CraftBlockData) data).getState();
    return CraftBlockStates.getBlockState(state, null);
  }
}
