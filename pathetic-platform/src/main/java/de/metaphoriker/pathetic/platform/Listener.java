package de.metaphoriker.pathetic.platform;

import de.metaphoriker.pathetic.platform.block.Block;

public interface Listener {

  public void onBlockBurn(Block block);

  public void onBlockExplode(Block block);

  public void onBlockFade(Block block);

  public void onBlockFromTo(Block blockFrom, Block blockTo);

  public void onBlockGrow(Block block);

  public void onPistonRetract(Block block);

  public void onPistonExtend(Block block);

  public void onBlockPlace(Block block);

  public void onBlockBreak(Block block);

  public void onLeaveDecay(Block block);

}
