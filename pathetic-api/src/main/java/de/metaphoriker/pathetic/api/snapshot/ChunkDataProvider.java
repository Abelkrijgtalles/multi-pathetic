package de.metaphoriker.pathetic.api.snapshot;

import de.metaphoriker.pathetic.platform.ChunkSnapshot;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

/** This is for internal purpose only and is used to receive a ChunkSnapshot version-independent. */
public interface ChunkDataProvider {

  /**
   * Returns a {@link ChunkSnapshot} of the chunk at the given coordinates.
   *
   * @param world The {@link Level} to get the {@link ChunkSnapshot} from
   * @param chunkX The x-coordinate of the chunk
   * @param chunkZ The z-coordinate of the chunk
   * @return The {@link ChunkSnapshot} of the chunk at the given coordinates
   */
  ChunkSnapshot getSnapshot(Level world, int chunkX, int chunkZ);

  /** Get the block state from a chunk snapshot at the given coordinates */
  BlockState getBlockState(ChunkSnapshot snapshot, int x, int y, int z);
}
