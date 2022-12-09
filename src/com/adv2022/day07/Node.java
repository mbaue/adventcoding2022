package com.adv2022.day07;

import java.util.ArrayList;
import java.util.List;

public class Node {
    String name;
    Node parent;
    int size;
    List<Node> children;

    public Node(String name, Node parent) {
        this.name = name;
        this.parent = parent;
        this.size = 0;
        children = new ArrayList<>();
    }

    Node getParent() {
        return this.parent;
    }

    void setParent(Node n) {
        this.parent = n;
    }

    public void increaseSize(int size) {
        this.size += size;
    }

    @Override
    public String toString() {
        String x;
        if (parent == null) {
             x = "null";
        } else {
            x = parent.name;
        }
        return "Node{" +
                "name='" + name + '\'' +
                ", parent=" + x +
                ", size=" + size +
                '}';
    }

}