package de.metaphoriker.pathetic.platform.block;

import de.metaphoriker.pathetic.platform.Chunk;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class Block {

  // this code has been copied from the papermc project and modified to work with vanilla

  private final Level world;
  private final BlockPos position;

  public Block(Level world, BlockPos position) {
    this.world = world;
    this.position = position.immutable();
  }

//  public BlockPos getPosition() {
//    return this.position;
//  }
//
//  public LevelAccessor getHandle() {
//    return this.world;
//  }

  public Level getWorld() {
    return this.world;
  }

//  public int getX() {
//    return this.position.getX();
//  }
//
//  public int getY() {
//    return this.position.getY();
//  }
//
//  public int getZ() {
//    return this.position.getZ();
//  }

  public Chunk getChunk() {
    return this.getWorld().getChunkAt(this);
  }

//  public void setData(final byte data) {
//    this.setData(data, 3);
//  }

//  public void setData(final byte data, boolean applyPhysics) {
//    if (applyPhysics) {
//      this.setData(data, 3);
//    } else {
//      this.setData(data, 2);
//    }
//  }

//  private void setData(final byte data, int flag) {
//    this.world.setBlock(this.position, CraftMagicNumbers.getBlock(this.getType(), data), flag);
//  }

//  public void setType(Material type, boolean applyPhysics) {
//    Preconditions.checkArgument(type != null, "Material cannot be null");
//    this.setBlockData(type.createBlockData(), applyPhysics);
//  }

//  public void setBlockData(BlockData data) {
//    this.setBlockData(data, true);
//  }

//  public void setBlockData(BlockData data, boolean applyPhysics) {
//    Preconditions.checkArgument(data != null, "BlockData cannot be null");
//    this.setTypeAndData(((CraftBlockData) data).getState(), applyPhysics);
//  }

//  boolean setTypeAndData(final net.minecraft.world.level.block.state.BlockState blockData, final boolean applyPhysics) {
//    return CraftBlock.setTypeAndData(this.world, this.position, this.getNMS(), blockData, applyPhysics);
//  }

//  public Material getType() {
//    return this.world.getBlockState(this.position).getBukkitMaterial(); // Paper - optimise getType calls
//  }

//  public Block getRelative(BlockFace face, int distance) {
//    return this.getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
//  }

  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof CraftBlock other)) {
      return false;
    }

    return this.position.equals(other.position) && this.getWorld().equals(other.getWorld());
  }

  public int hashCode() {
    return this.position.hashCode() ^ this.getWorld().hashCode();
  }

//  public boolean isEmpty() {
//    return this.getNMS().isAir();
//  }

//  public boolean breakNaturally(ItemStack item) {
//    // Paper start
//    return this.breakNaturally(item, false);
//  }

//  public boolean breakNaturally(boolean triggerEffect, boolean dropExperience) {
//    return this.breakNaturally(null, triggerEffect, dropExperience);
//  }

//  public boolean breakNaturally(ItemStack item, boolean triggerEffect, boolean dropExperience) {
//    // Paper end
//    // Order matters here, need to drop before setting to air so skulls can get their data
//    net.minecraft.world.level.block.state.BlockState iblockdata = this.getNMS();
//    net.minecraft.world.level.block.Block block = iblockdata.getBlock();
//    net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
//    boolean result = false;
//
//    // Modelled off EntityHuman#hasBlock
//    if (block != Blocks.AIR && (item == null || !iblockdata.requiresCorrectToolForDrops() || nmsItem.isCorrectToolForDrops(iblockdata))) {
//      net.minecraft.world.level.block.Block.dropResources(iblockdata, this.world.getMinecraftWorld(), this.position, this.world.getBlockEntity(this.position), null, nmsItem, false); // Paper - Properly handle xp dropping
//      // Paper start - improve Block#breanNaturally
//      if (triggerEffect) {
//        if (iblockdata.getBlock() instanceof net.minecraft.world.level.block.BaseFireBlock) {
//          this.world.levelEvent(net.minecraft.world.level.block.LevelEvent.SOUND_EXTINGUISH_FIRE, this.position, 0);
//        } else {
//          this.world.levelEvent(net.minecraft.world.level.block.LevelEvent.PARTICLES_DESTROY_BLOCK, this.position, net.minecraft.world.level.block.Block.getId(iblockdata));
//        }
//      }
//      if (dropExperience) block.popExperience(this.world.getMinecraftWorld(), this.position, block.getExpDrop(iblockdata, this.world.getMinecraftWorld(), this.position, nmsItem, true));
//      // Paper end
//      result = true;
//    }
//
//    // SPIGOT-6778: Directly call setBlock instead of setTypeAndData, so that the tile entiy is not removed and custom remove logic is run.
//    // Paper start - improve breakNaturally
//    boolean destroyed = this.world.removeBlock(this.position, false);
//    if (destroyed) {
//      block.destroy(this.world, this.position, iblockdata);
//    }
//    if (result) {
//      // special cases
//      if (block instanceof net.minecraft.world.level.block.IceBlock iceBlock) {
//        iceBlock.afterDestroy(this.world.getMinecraftWorld(), this.position, nmsItem);
//      } else if (block instanceof net.minecraft.world.level.block.TurtleEggBlock turtleEggBlock) {
//        turtleEggBlock.decreaseEggs(this.world.getMinecraftWorld(), this.position, iblockdata);
//      }
//    }
//    return destroyed && result;
//    // Paper end
//  }

//  public Collection<ItemStack> getDrops(ItemStack item) {
//    return this.getDrops(item, null);
//  }

//  public Collection<ItemStack> getDrops(ItemStack item, Entity entity) {
//    net.minecraft.world.level.block.state.BlockState iblockdata = this.getNMS();
//    net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
//
//    // Modelled off EntityHuman#hasBlock
//    if (item == null || CraftBlockData.isPreferredTool(iblockdata, nms)) {
//      return net.minecraft.world.level.block.Block.getDrops(iblockdata, (ServerLevel) this.world.getMinecraftWorld(), this.position, this.world.getBlockEntity(this.position), entity == null ? null : ((CraftEntity) entity).getHandle(), nms)
//        .stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList());
//    } else {
//      return Collections.emptyList();
//    }
//  }

}
