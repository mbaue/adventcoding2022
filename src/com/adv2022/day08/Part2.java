package com.adv2022.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/8
 * --- Day 8: Treetop Tree House ---
 * --- Part Two ---
 * Content with the amount of tree cover available, the Elves just need to know the best spot to build their
 * tree house: they would like to be able to see a lot of trees.
 *
 * To measure the viewing distance from a given tree, look up, down, left, and right from that tree; stop if you
 * reach an edge or at the first tree that is the same height or taller than the tree under consideration. (If a tree
 * is right on the edge, at least one of its viewing distances will be zero.)
 *
 * The Elves don't care about distant trees taller than those found by the rules above; the proposed tree house has
 * arge eaves to keep it dry, so they wouldn't be able to see higher than the tree house anyway.
 *
 * In the example above, consider the middle 5 in the second row:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 * Looking up, its view is not blocked; it can see 1 tree (of height 3).
 * Looking left, its view is blocked immediately; it can see only 1 tree (of height 5, right next to it).
 * Looking right, its view is not blocked; it can see 2 trees.
 * Looking down, its view is blocked eventually; it can see 2 trees (one of height 3, then the tree of height 5
 * that blocks its view).
 * A tree's scenic score is found by multiplying together its viewing distance in each of the four directions.
 * For this tree, this is 4 (found by multiplying 1 * 1 * 2 * 2).
 *
 * However, you can do even better: consider the tree of height 5 in the middle of the fourth row:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 * Looking up, its view is blocked at 2 trees (by another tree with a height of 5).
 * Looking left, its view is not blocked; it can see 2 trees.
 * Looking down, its view is also not blocked; it can see 1 tree.
 * Looking right, its view is blocked at 2 trees (by a massive tree of height 9).
 * This tree's scenic score is 8 (2 * 2 * 1 * 2); this is the ideal spot for the tree house.
 *
 * Consider each tree on your map. What is the highest scenic score possible for any tree?
 *
 * Your puzzle answer was 201600.
 */

public class Part2 {

    public static final int LINE_LENGTH = 99; // x
    public static final int LINES_COUNT = 99; // y

    public static byte[][] stromy = new byte[LINE_LENGTH][LINES_COUNT];

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv08.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int lineNum = 0;
            int highestScenicScore = 0;

            // fill data about trees
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    stromy[i][lineNum] = Byte.parseByte(line.substring(i, i + 1));
                }
                lineNum++;
            }

            // count scenic score for each tree
            for (int x = 0; x < LINE_LENGTH; x++) {
                for (int y = 0; y < LINES_COUNT; y++) {
                    int tempScore = getScenicScore(x, y);
                    if (tempScore > highestScenicScore) {
                        highestScenicScore = tempScore;
                    }
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + highestScenicScore);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static int getScenicScore(int x, int y) {
        if (x == 0 || y == 0 || x == LINE_LENGTH - 1 || y == LINES_COUNT - 1) {
            return 0;
        }
        return getLeftScore(x, y) * getRightScore(x, y) * getTopScore(x, y) * getBottomScore(x, y);
    }

    static int getLeftScore(int x, int y) {
        int index = x - 1;
        int leftScore = 0;
        while (index >= 0) {
            //System.out.println("left index "+ index );
            leftScore++;
            if (stromy[index][y] >= stromy[x][y]) {
                break;
            }
            index--;
        }
        return leftScore;
    }

    static int getRightScore(int x, int y) {
        int index = x + 1;
        int rightScore = 0;
        while (index < LINE_LENGTH) {
            rightScore++;
            if (stromy[index][y] >= stromy[x][y]) {
                break;
            }
            index++;
        }
        return rightScore;
    }

    static int getTopScore(int x, int y) {
        int index = y - 1;
        int topScore = 0;
        while (index >= 0) {
            topScore++;
            if (stromy[x][index] >= stromy[x][y]) {
                break;
            }
            index--;
        }
        return topScore;
    }

    static int getBottomScore(int x, int y) {
        int index = y + 1;
        int bottomScore = 0;
        while (index < LINES_COUNT) {
            bottomScore++;
            if (stromy[x][index] >= stromy[x][y]) {
                break;
            }
            index++;
        }
        return bottomScore;
    }
}