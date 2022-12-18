package com.adv2022.day09;

import java.util.ArrayList;
import java.util.List;

public class Rope2 {

    List<Position> knots;
    public static final int KNOTS_NUMBER = 10;

    public Rope2() {
        this.knots = new ArrayList<>(10);
        for (int i = 0; i < KNOTS_NUMBER; i++) {
            this.knots.add(i, new Position(0, 0));
        }
    }

    public Position getTail() {
        return this.knots.get(9);
    }

    void moveHeadRight(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.knots.get(0).incrX();
            checkRestOfRope();
        }
    }

    void moveHeadLeft(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.knots.get(0).decrX();
            checkRestOfRope();
        }
    }

    void moveHeadUp(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.knots.get(0).incrY();
            checkRestOfRope();
        }
    }

    void moveHeadDown(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.knots.get(0).decrY();
            checkRestOfRope();
        }
    }

    void checkRestOfRope() {
        for (int i = 1; i < KNOTS_NUMBER; i++) {
            adjust(i);
        }
        Part2.visited.add(this.knots.get(9).toString());
    }

    void adjust(int knotOrder) {
        int difX = knots.get(knotOrder - 1).getX() - knots.get(knotOrder).getX();
        int difY = knots.get(knotOrder - 1).getY() - knots.get(knotOrder).getY();
        boolean x2 = false;
        boolean y2 = false;
        if (difX == 2 || difX == -2) {
            knots.get(knotOrder).setX(knots.get(knotOrder).getX() + (difX / 2));
            x2 = true;
        }
        if (difY == 2 || difY == -2) {
            knots.get(knotOrder).setY(knots.get(knotOrder).getY() + (difY / 2));
            y2 = true;
        }
        if ((difY == 1 || difY == -1) && x2) {
            knots.get(knotOrder).setY(knots.get(knotOrder - 1).getY());
        }
        if ((difX == 1 || difX == -1) && y2) {
            knots.get(knotOrder).setX(knots.get(knotOrder - 1).getX());
        }
    }
}