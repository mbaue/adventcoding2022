package com.adv2022.day09;

public class Position {
    int x;
    int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incrX() {
        this.x++;
    }

    public void decrX() {
        this.x--;
    }

    public void incrY() {
        this.y++;
    }

    public void decrY() {
        this.y--;
    }
}