package de.metaphoriker.pathetic.api.wrapper;

import javax.annotation.Nullable;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Value;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

@Value
@ToString
@EqualsAndHashCode
public class BlockInformation {

  /** The material of the represented block */
  @NonNull
  Block material;

  /**
   * The block state of the represented block -- GETTER -- Gets the block state of the represented
   * block
   *
   * @api.Note This is only available in v. 1.13 or above and therefore nullable
   */
  @Nullable
  BlockState blockState;
}
