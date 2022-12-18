package com.adv2022.day09;

public class Rope {
    Position head;
    Position tail;

    public Rope() {
        this.head = new Position(0, 0);
        this.tail = new Position(0, 0);
    }

    public Position getTail() {
        return tail;
    }

    void moveHeadRight(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.head.incrX();
            checkTail();
        }
    }

    void moveHeadLeft(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.head.decrX();
            checkTail();
        }
    }

    void moveHeadUp(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.head.incrY();
            checkTail();
        }
    }

    void moveHeadDown(int distance) {
        for (int i = 1; i <= distance; i++) {
            this.head.decrY();
            checkTail();
        }
    }

    void checkTail() {
        boolean change = false;
        int difX = head.getX() - tail.getX();
        int difY = head.getY() - tail.getY();
        if (difX == 2 || difX == -2) {
            tail.setX(tail.getX() + (difX / 2));
            change = true;
            if (difY != 0) {
                tail.setY(head.getY());
            }
        } else if (difY == 2 || difY == -2) {
            tail.setY(tail.getY() + (difY / 2));
            change = true;
            if (difX != 0) {
                tail.setX(head.getX());
            }
        }
        System.out.println("head na pozici " + head);
        System.out.println("tail na pozici " + tail);
        System.out.println("------------------------");
        if (change) {
            Day09.visited.add(tail.toString());
        }
    }
}