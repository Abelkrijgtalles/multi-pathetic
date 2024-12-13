package de.metaphoriker.pathetic.platform.block.data;

import com.google.common.base.Function;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockData implements Cloneable {

  // this code has been copied from the papermc project and modified to work with vanilla

  protected BlockData(net.minecraft.world.level.block.state.BlockState state) {
    this.state = state;
  }
  private net.minecraft.world.level.block.state.BlockState state;
  
  public BlockData clone() {
    try {
      return (BlockData) super.clone();
    } catch (CloneNotSupportedException ex) {
      throw new AssertionError("Clone not supported", ex);
    }
  }
  
  public boolean equals(Object obj) {
    return obj instanceof BlockData && this.state.equals(((BlockData) obj).state);
  }

  public int hashCode() {
    return this.state.hashCode();
  }

  public static BlockData fromData(net.minecraft.world.level.block.state.BlockState data) {
    // TODO: check if this works or not
    return createData(data);
  }

  public static BlockData createData(net.minecraft.world.level.block.state.BlockState data) {

    return BlockData.MAP.getOrDefault(data.getBlock().getClass(), BlockData::new).apply(data);
  }

  private static final Map<Class<? extends Block>, Function<BlockState, BlockData>> MAP = new HashMap<>();

  @NotNull
  public BlockState createBlockState() {
    // TODO: check if this works or not
    return this.state;
  }

}
