package de.metaphoriker.pathetic.provider.v1_19_R3;

import de.metaphoriker.pathetic.platform.ChunkSnapshot;
import de.metaphoriker.pathetic.platform.block.data.BlockData;
import java.lang.reflect.Field;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.DataPaletteBlock;
import de.metaphoriker.pathetic.api.snapshot.ChunkDataProvider;

public class v1_19_R3ChunkDataProviderImpl implements ChunkDataProvider {

  private static final Field blockIDField;

  static {
    try {
      blockIDField = CraftChunk.class.getDeclaredField("emptyBlockIDs");
      blockIDField.setAccessible(true);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
    try {

      WorldServer server = ((CraftWorld) world).getHandle();
      CraftChunk newCraftChunk = new CraftChunk(server, chunkX, chunkZ);

      server.k().a(chunkX, chunkZ, ChunkStatus.o, true);
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
    BlockData data = snapshot.getBlockData(x, y, z);
    IBlockData state = ((CraftBlockData) data).getState();
    return CraftBlockStates.getBlockState(state, null);
  }
}
