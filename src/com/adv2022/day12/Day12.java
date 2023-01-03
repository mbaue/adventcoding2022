package com.adv2022.day12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://adventofcode.com/2022/day/12
 * --- Day 12: Hill Climbing Algorithm ---
 * You try contacting the Elves using your handheld device, but the river you're following must be too low to get
 * a decent signal.
 *
 * You ask the device for a heightmap of the surrounding area (your puzzle input). The heightmap shows the local area
 * from above broken into a grid; the elevation of each square of the grid is given by a single lowercase letter,
 * where a is the lowest elevation, b is the next-lowest, and so on up to the highest elevation, z.
 *
 * Also included on the heightmap are marks for your current position (S) and the location that should get the best
 * signal (E). Your current position (S) has elevation a, and the location that should get the best signal (E) has
 * elevation z.
 *
 * You'd like to reach E, but to save energy, you should do it in as few steps as possible. During each step, you
 * can move exactly one square up, down, left, or right. To avoid needing to get out your climbing gear, the
 * elevation of the destination square can be at most one higher than the elevation of your current square; that is,
 * if your current elevation is m, you could step to elevation n, but not to elevation o. (This also means that the
 * elevation of the destination square can be much lower than the elevation of your current square.)
 *
 * For example:
 *
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 * Here, you start in the top-left corner; your goal is near the middle. You could start by moving down or right,
 * but eventually you'll need to head toward the e at the bottom. From there, you can spiral around to the goal:
 *
 * v..v<<<<
 * >v.vv<<^
 * .>vv>E^^
 * ..v>>>^^
 * ..>>>>>^
 * In the above diagram, the symbols indicate whether the path exits each square moving up (^), down (v), left (<),
 * or right (>). The location that should get the best signal is still E, and . marks unvisited squares.
 *
 * This path reaches the goal in 31 steps, the fewest possible.
 *
 * What is the fewest steps required to move from your current position to the location that should get the best signal?
 *
 * Your puzzle answer was 380.
 */
public class Day12 {

    public static String[] arr;

    public static final int LINE_LENGTH = 113;
    public static final int LINES_COUNT = 41;

    public static int TARGET_X = 88;
    public static int TARGET_Y = 20;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv12.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            arr = new String[LINES_COUNT];
            int i = 0;

            while ((line = br.readLine()) != null) {
                arr[i] = line;
                i++;
            }

            Node startingNode = new Node(null, 0, 20, getEdgeValue(0, 20));

            List<Node> visited = new ArrayList<>();
            List<Node> unvisited = new ArrayList<>();
            int solution = 0;

            startingNode.setDist(0);
            unvisited.add(startingNode);
            while (!unvisited.isEmpty()) {
                Node processedNode = Collections.min(unvisited);
                System.out.println("hledam sousedy uzlu " + processedNode);
                List<Node> neighborNodes = processedNode.getNeighborVertices();
                System.out.println("                    nalezene uzly " + neighborNodes);
                while (!neighborNodes.isEmpty()) {
                    Node actualNode = neighborNodes.get(0);
                    if (actualNode.getX() == TARGET_X && actualNode.getY() == TARGET_Y) {
                        System.out.println("bacha");
                    }
                    neighborNodes.remove(0);
                    if (!visited.contains(actualNode)) {
                        int newDist = processedNode.getDist() + 1;
                        if (!unvisited.contains(actualNode)) {
                            // pridat do unvisited a nastavit dist
                            actualNode.setDist(newDist);
                            actualNode.setPredecessor(processedNode);
                            unvisited.add(actualNode);
                        } else {
                            // nastavit dist toho nalezeneho
                            Node foundNode = unvisited.get(unvisited.indexOf(actualNode));
                            if (foundNode.setDist(newDist)) {
                                foundNode.setPredecessor(processedNode);
                            }
                        }
                    }
                }
                visited.add(processedNode);
                unvisited.remove(processedNode);
            }

            for (Node n : visited) {
                if (n.getX() == TARGET_X && n.getY() == TARGET_Y) {
                    System.out.println(" mam reseni? ");
                    solution = n.getDist();
                    System.out.println(n);
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + solution);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static char getEdgeValue(int x, int y) {
        if (arr[y].charAt(x) == 'S') {
            System.out.println(" S");
            return 'a';
        }
        if (arr[y].charAt(x) == 'E') {
            System.out.println("sem v E");
            return 'z';
        }
        return arr[y].charAt(x);
    }
}