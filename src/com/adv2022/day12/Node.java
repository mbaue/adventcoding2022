package com.adv2022.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node implements Comparable<Node> {

    private int dist;
    private Node predecessor;
    private final int x;
    private final int y;
    private final char val;

    public Node(Node predecessor, int x, int y, char val) {
        this.dist = Integer.MAX_VALUE;
        this.predecessor = predecessor;
        this.x = x;
        this.y = y;
        this.val = val;
    }

    public List<Node> getNeighborVertices() {
        List<Node> set = new ArrayList<>();
        int x = this.x;
        int y = this.y;
        char val = Day12.getEdgeValue(x, y);
        if (x - 1 >= 0) {
            char newVal = Day12. getEdgeValue(x-1, y);
            int dif = newVal - val;
            if ( dif <= 1 ) {
                Node n = new Node(this, x - 1, y, newVal);
                set.add(n);
            }
        }
        if (x + 1 < Day12.LINE_LENGTH) {
            char newVal = Day12. getEdgeValue(x+1, y);
            int dif = newVal - val;
            if ( dif <= 1) {
                Node n = new Node(this, x + 1, y, newVal);
                set.add(n);
            }
        }
        if (y - 1 >= 0) {
            char newVal = Day12. getEdgeValue(x, y-1);
            int dif = newVal - val;
            if ( dif <= 1) {
                Node n = new Node(this, x, y - 1, newVal);
                set.add(n);
            }
        }
        if (y + 1 < Day12.LINES_COUNT ) {
            char newVal = Day12. getEdgeValue(x, y+1);
            int dif = newVal - val;
            if ( dif <= 1) {
                Node n = new Node(this, x, y + 1, newVal);
                set.add(n);
            }
        }
        return set;
    }

    public char getVal() {
        return val;
    }

    public List<Node> getNeighborVerticesBack() {
        List<Node> set = new ArrayList<>();
        int x = this.x;
        int y = this.y;
        char val = Part2.getEdgeValue(x, y);
        if (x - 1 >= 0) {
            char newVal = Part2. getEdgeValue(x-1, y);
            int dif = val - newVal;
            if ( dif <= 1 ) {
                Node n = new Node(this, x - 1, y, newVal);
                set.add(n);
            }
        }
        if (x + 1 < Part2.LINE_LENGTH) {
            char newVal = Part2. getEdgeValue(x+1, y);
            int dif = val - newVal;
            if ( dif <= 1) {
                Node n = new Node(this, x + 1, y, newVal);
                set.add(n);
            }
        }
        if (y - 1 >= 0) {
            char newVal = Part2. getEdgeValue(x, y-1);
            int dif = val - newVal;
            if ( dif <= 1) {
                Node n = new Node(this, x, y - 1, newVal);
                set.add(n);
            }
        }
        if (y + 1 < Part2.LINES_COUNT ) {
            char newVal = Part2. getEdgeValue(x, y+1);
            int dif = val - newVal;
            if ( dif <= 1) {
                Node n = new Node(this, x, y + 1, newVal);
                set.add(n);
            }
        }
        return set;
    }

    public boolean setDist(int dist) {
        if (dist < this.getDist()) {
            this.dist = dist;
            return true;
        }
        return false;
    }

    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDist() {
        return dist;
    }

    @Override
    public int compareTo(Node o) {
        return this.dist - o.dist;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node n) {
            return this.x == n.x && this.y == n.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Node [" + x +   ", " + y + "] dist " + dist + ", val " + val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}