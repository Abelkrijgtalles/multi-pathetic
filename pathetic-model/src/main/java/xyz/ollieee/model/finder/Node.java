package xyz.ollieee.model.finder;

import xyz.ollieee.api.wrapper.PathLocation;

import java.util.Objects;

public class Node implements Comparable<Node> {

    private final Integer depth;
    private final PathLocation location;
    private final PathLocation target;
    private final PathLocation start;
    private final Double length;
    
    private Node parent;

    Node(PathLocation location, PathLocation start, PathLocation target, Integer depth) {
        
        this.location = location;
        this.target = target;
        this.start = start;
        this.depth = depth;
        
        this.length = this.start.distance(this.target);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
    
    public Node getParent() {
        return this.parent;
    }
    
    public Integer getDepth() {
        return this.depth;
    }

    public PathLocation getLocation() {
        return this.location.clone();
    }
    
    public double getPriorityKey() {
        return (this.target.distance(this.location) + this.start.distance(this.location)) * (Math.pow(10, (this.target.distance(this.location)/ this.length) - 1) + 0.9);
    }
    
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PathLocation nodeLocation = ((Node) o).getLocation();
        return nodeLocation.getBlockX() == this.location.getBlockX() && nodeLocation.getBlockY() == this.location.getBlockY() && nodeLocation.getBlockZ() == this.location.getBlockZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.location);
    }

    @Override
    public int compareTo(Node otherNode) {
        return (int) Math.signum(this.getPriorityKey() - otherNode.getPriorityKey());
    }
}
