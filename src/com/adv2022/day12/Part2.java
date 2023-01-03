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
 * --- Part Two ---
 * As you walk up the hill, you suspect that the Elves will want to turn this into a hiking trail. The
 * beginning isn't very scenic, though; perhaps you can find a better starting point.
 *
 * To maximize exercise while hiking, the trail should start as low as possible: elevation a. The goal is still
 * the square marked E. However, the trail should still be direct, taking the fewest steps to reach its goal.
 * So, you'll need to find the shortest path from any square at elevation a to the square marked E.
 *
 * Again consider the example from above:
 *
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 * Now, there are six choices for starting position (five marked a, plus the square marked S that counts as being
 * at elevation a). If you start at the bottom-left square, you can reach the goal most quickly:
 *
 * ...v<<<<
 * ...vv<<^
 * ...v>E^^
 * .>v>>>^^
 * >^>>>>>^
 * This path reaches the goal in only 29 steps, the fewest possible.
 *
 * What is the fewest steps required to move starting from any square with elevation a to the location that
 * should get the best signal?
 *
 * Your puzzle answer was 375.
 */

public class Part2 {

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

            Node startingNode = new Node(null, 88, 20, getEdgeValue(88, 20));

            List<Node> visited = new ArrayList<>();
            List<Node> unvisited = new ArrayList<>();

            startingNode.setDist(0);
            unvisited.add(startingNode);
            while (!unvisited.isEmpty()) {
                Node processedNode = Collections.min(unvisited);
                System.out.println("hledam sousedy uzlu " + processedNode);
                List<Node> neighborNodes = processedNode.getNeighborVerticesBack();
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

            int min = Integer.MAX_VALUE;
            for (Node n : visited) {
                    if (n.getVal() == 'a' || n.getVal() == 'S' ) {
                        if (n.getDist() < min) {
                            min = n.getDist();
                        }
                    }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + min);
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