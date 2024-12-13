package de.metaphoriker.pathetic.provider.v1_20_R2;

import de.metaphoriker.pathetic.platform.ChunkSnapshot;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.DataPaletteBlock;
import de.metaphoriker.pathetic.api.snapshot.ChunkDataProvider;

import java.lang.reflect.Field;

public final class v1_20_R2ChunkDataProviderImpl implements ChunkDataProvider {

  private static final Field blockIDField;

  static {
    try {
      blockIDField = CraftChunk.class.getDeclaredField("emptyBlockIDs");
      blockIDField.setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }


  @SuppressWarnings("unchecked")
  public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
    try {

      WorldServer server = ((CraftWorld) world).getHandle();
      CraftChunk newCraftChunk = new CraftChunk(server, chunkX, chunkZ);

      server.k().a(chunkX, chunkZ, ChunkStatus.n, true);
      DataPaletteBlock<IBlockData> dataDataPaletteBlock =
        (DataPaletteBlock<IBlockData>) blockIDField.get(newCraftChunk);

      dataDataPaletteBlock.b();
      dataDataPaletteBlock.a();
      ChunkSnapshot chunkSnapshot = newCraftChunk.getChunkSnapshot();
      dataDataPaletteBlock.b();

      return chunkSnapshot;

    } catch (IllegalAccessException e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public BlockState getBlockState(ChunkSnapshot snapshot, int x, int y, int z) {
    return snapshot.getBlockData(x, y, z).createBlockState();
  }
}
