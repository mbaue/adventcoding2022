package com.adv2022.day07;

import java.util.HashMap;
import java.util.Map;

public class MyTree {

    private Node top;
    private Node indicator;

    int atMostHundredThousand = 0;

    Map<String, Integer> map = new HashMap<>();

    public MyTree() {
        top = null;
        indicator = null;
    }

    public void addNode(Node n) {
        if (top == null) {
            top = n;
        } else {
            indicator.children.add(n);
            n.setParent(indicator);
        }
        indicator = n;
    }

    public void changeIndUp() {
        this.indicator = this.indicator.parent;
    }

    public Node getIndicator() {
        return indicator;
    }

    public int countSize(Node n) {
        int nodeSize = n.size;
        for (Node nd : n.children) {
            nodeSize += countSize(nd);
        }
        System.out.println(n + " size is " + nodeSize);
        map.put(n.name, nodeSize);
        if (nodeSize <= 100000) {
            atMostHundredThousand += nodeSize;
        }
        return nodeSize;
    }

    public Node getTop() {
        return top;
    }

    public int getSmallestSufficient() {
        int limit = Part2.REQURED_FOR_UPDATE_SIZE - Part2.FILE_SYSTEM_SIZE + Part2.TOTAL;
        int minim = Integer.MAX_VALUE;

        for (Integer x : map.values()) {
            if (x > limit) {
                if (x < minim) {
                    minim = x;
                }
            }
        }
        return minim;
    }
}