package de.metaphoriker.pathetic.platform;

import de.metaphoriker.pathetic.platform.block.data.BlockData;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.PalettedContainer;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import net.minecraft.world.level.levelgen.Heightmap;

public class ChunkSnapshot {

  // this code has been copied from the papermc project and modified to work with vanilla

  private final int minHeight, maxHeight;
  private final PalettedContainer<BlockState>[] blockids;

  ChunkSnapshot(int x, int z, int minHeight, int maxHeight, int seaLevel, String wname, long wtime, PalettedContainer<BlockState>[] sectionBlockIDs, byte[][] sectionSkyLights, byte[][] sectionEmitLights, boolean[] sectionEmpty, Heightmap hmap, Registry<net.minecraft.world.level.biome.Biome> biomeRegistry, PalettedContainerRO<Holder<net.minecraft.world.level.biome.Biome>>[] biome) {
    this.minHeight = minHeight;
    this.maxHeight = maxHeight;
    this.blockids = sectionBlockIDs;
  }

  public Block getBlockType(int x, int y, int z) {
    this.validateChunkCoordinates(x, y, z);

    return this.blockids[this.getSectionIndex(y)].get(x, y & 0xF, z).getBlock();
  }

  public final BlockData getBlockData(int x, int y, int z) {
    this.validateChunkCoordinates(x, y, z);

    return BlockData.fromData(this.blockids[this.getSectionIndex(y)].get(x, y & 0xF, z));
  }

  private int getSectionIndex(int y) {
    return (y - this.minHeight) >> 4;
  }

  private void validateChunkCoordinates(int x, int y, int z) {
      Chunk.validateChunkCoordinates(this.minHeight, this.maxHeight, x, y, z);
  }

}
