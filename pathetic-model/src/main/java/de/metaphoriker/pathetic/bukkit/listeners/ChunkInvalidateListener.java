package de.metaphoriker.pathetic.bukkit.listeners;

import de.metaphoriker.pathetic.model.snapshot.FailingSnapshotManager;
import de.metaphoriker.pathetic.platform.Listener;
import de.metaphoriker.pathetic.platform.block.Block;

public class ChunkInvalidateListener implements Listener {

  @Override
  public void onBlockBurn(Block block) {
    handleEvent(block);
  }

  @Override
  public void onBlockExplode(Block block) {
    handleEvent(block);
  }

  @Override
  public void onBlockFade(Block block) {
    handleEvent(block);
  }

  @Override
  public void onBlockFromTo(Block blockFrom, Block blockTo) {
    handleEvent(blockFrom, blockTo);
  }

  @Override
  public void onBlockGrow(Block block) {
    handleEvent(block);
  }

  @Override
  public void onPistonRetract(Block block) {
    handleEvent(block);
  }

  @Override
  public void onPistonExtend(Block block) {
    handleEvent(block);
  }

  @Override
  public void onBlockPlace(Block block) {
    handleEvent(block);
  }

  @Override
  public void onBlockBreak(Block block) {
    handleEvent(block);
  }

  @Override
  public void onLeaveDecay(Block block) {
    handleEvent(block);
  }

  private void handleEvent(Block... blocks) {
    for (Block block : blocks)
      FailingSnapshotManager.invalidateChunk(
        block.getWorld().getUID(), block.getChunk().getX(), block.getChunk().getZ());
  }

}
