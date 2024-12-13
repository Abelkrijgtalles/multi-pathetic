package de.metaphoriker.pathetic.platform;

import com.google.common.base.Preconditions;
import net.minecraft.server.level.ServerLevel;

public class Chunk {

  // this code has been copied from the papermc project and modified to work with vanilla

  private final ServerLevel worldServer;
  private final int x;
  private final int z;

  // TODO: check if it needs to stay
  public Chunk(net.minecraft.world.level.chunk.LevelChunk chunk) {
    this.worldServer = (ServerLevel) chunk.getLevel();
    this.x = chunk.getPos().x;
    this.z = chunk.getPos().z;
  }

  // TODO: check if it needs to stay
  public Chunk(ServerLevel worldServer, int x, int z) {
    this.worldServer = worldServer;
    this.x = x;
    this.z = z;
  }

  public int getX() {
    return this.x;
  }

  public int getZ() {
    return this.z;
  }

  public String toString() {
    return "CraftChunk{" + "x=" + this.getX() + "z=" + this.getZ() + '}';
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;

    Chunk that = (Chunk) o;

    if (this.x != that.x) return false;
    if (this.z != that.z) return false;
    return this.worldServer.equals(that.worldServer);
  }

  public int hashCode() {
    int result = this.worldServer.hashCode();
    result = 31 * result + this.x;
    result = 31 * result + this.z;
    return result;
  }

  static void validateChunkCoordinates(int minY, int maxY, int x, int y, int z) {
    Preconditions.checkArgument(0 <= x && x <= 15, "x out of range (expected 0-15, got %s)", x);
    Preconditions.checkArgument(minY <= y && y <= maxY, "y out of range (expected %s-%s, got %s)", minY, maxY, y);
    Preconditions.checkArgument(0 <= z && z <= 15, "z out of range (expected 0-15, got %s)", z);
  }

}
