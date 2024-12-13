package de.metaphoriker.pathetic.provider.paper;

import de.metaphoriker.pathetic.api.snapshot.ChunkDataProvider;
import de.metaphoriker.pathetic.platform.ChunkSnapshot;

public class PaperChunkDataProvider implements ChunkDataProvider {

  public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
    if (Bukkit.isPrimaryThread()) {
      return world.getChunkAt(chunkX, chunkZ).getChunkSnapshot();
    } else {
      return world.getChunkAtAsyncUrgently(chunkX, chunkZ).join().getChunkSnapshot();
    }
  }

  public BlockState getBlockState(ChunkSnapshot snapshot, int x, int y, int z) {
    return snapshot.getBlockData(x, y, z).createBlockState();
  }
}
