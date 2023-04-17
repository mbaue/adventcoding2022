package com.adv2022.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/8
 * --- Day 8: Treetop Tree House ---
 * The expedition comes across a peculiar patch of tall trees all planted carefully in a grid. The Elves explain
 * that a previous expedition planted these trees as a reforestation effort. Now, they're curious if this would be a
 * good location for a tree house.
 *
 * First, determine whether there is enough tree cover here to keep a tree house hidden. To do this, you need to count
 * the number of trees that are visible from outside the grid when looking directly along a row or column.
 *
 * The Elves have already launched a quadcopter to generate a map with the height of each tree (your puzzle input).
 * For example:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 * Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.
 *
 * A tree is visible if all of the other trees between it and an edge of the grid are shorter than it. Only consider
 * trees in the same row or column; that is, only look up, down, left, or right from any given tree.
 *
 * All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees
 * to block the view. In this example, that only leaves the interior nine trees to consider:
 *
 * The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of
 * height 5 are in the way.)
 * The top-middle 5 is visible from the top and right.
 * The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of
 * height 0 between it and an edge.
 * The left-middle 5 is visible, but only from the right.
 * The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at
 * most height 2 between it and an edge.
 * The right-middle 3 is visible from the right.
 * In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
 * With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in
 * this arrangement.
 *
 * Consider your map; how many trees are visible from outside the grid?
 *
 * Your puzzle answer was 1849.
 */

public class Day08 {

    public static final int LINE_LENGTH = 99; // x
    public static final int LINES_COUNT = 99; // y

    public static byte[][] stromy = new byte[LINE_LENGTH][LINES_COUNT];

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int lineNum = 0;
            int count = 0;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    stromy[i][lineNum] = Byte.parseByte(line.substring(i, i + 1));
                }
                lineNum++;
            }

            for (int x = 0; x < LINE_LENGTH; x++) {
                for (int y = 0; y < LINES_COUNT; y++) {
                    System.out.println("\nchecking [" + x + "][" + y + "]");
                    System.out.println("value=" + stromy[x][y]);
                    if (isTreeVisible(x, y)) {
                        count++;
                        System.out.println("visible   " + count);
                    }
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + count);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean isTreeVisible(int x, int y) {
        return checkLeft(x, y) || checkRight(x, y) || checkUp(x, y) || checkDown(x, y);
    }

    static boolean checkLeft(int x, int y) {
        byte vyskaNasehoStromu = getHeight(x, y);
        if (x == 0) {
            return true;
        } else {
            for (int i = x - 1; i >= 0; i--) {
                if (vyskaNasehoStromu <= getHeight(i, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkRight(int x, int y) {
        byte vyskaNasehoStromu = getHeight(x, y);
        if (x == LINE_LENGTH) {
            return true;
        } else {
            for (int i = x + 1; i < LINE_LENGTH; i++) {
                if (vyskaNasehoStromu <= getHeight(i, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkUp(int x, int y) {
        byte vyskaNasehoStromu = getHeight(x, y);
        if (y == 0) {
            return true;
        } else {
            for (int i = y - 1; i >= 0; i--) {
                if (vyskaNasehoStromu <= getHeight(x, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkDown(int x, int y) {
        byte vyskaNasehoStromu = getHeight(x, y);
        if (y == 0) {
            return true;
        } else {
            for (int i = y + 1; i < LINES_COUNT; i++) {
                if (vyskaNasehoStromu <= getHeight(x, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    static byte getHeight(int x, int y) {
        return (byte) (stromy[x][y] & 111);
    }
}