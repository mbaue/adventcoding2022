package com.adv2022.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/13
 * --- Day 13: Distress Signal ---
 * You climb the hill and again try contacting the Elves. However, you instead receive a signal you weren't
 * expecting: a distress signal.
 *
 * Your handheld device must still not be working properly; the packets from the distress signal got decoded
 * out of order. You'll need to re-order the list of received packets (your puzzle input) to decode the message.
 *
 * Your list consists of pairs of packets; pairs are separated by a blank line. You need to identify how many pairs
 * of packets are in the right order.
 *
 * For example:
 *
 * [1,1,3,1,1]
 * [1,1,5,1,1]
 *
 * [[1],[2,3,4]]
 * [[1],4]
 *
 * [9]
 * [[8,7,6]]
 *
 * [[4,4],4,4]
 * [[4,4],4,4,4]
 *
 * [7,7,7,7]
 * [7,7,7]
 *
 * []
 * [3]
 *
 * [[[]]]
 * [[]]
 *
 * [1,[2,[3,[4,[5,6,7]]]],8,9]
 * [1,[2,[3,[4,[5,6,0]]]],8,9]
 * Packet data consists of lists and integers. Each list starts with [, ends with ], and contains zero or
 * more comma-separated values (either integers or other lists). Each packet is always a list and appears on
 * its own line.
 *
 * When comparing two values, the first value is called left and the second value is called right. Then:
 *
 * If both values are integers, the lower integer should come first. If the left integer is lower than the right
 * integer, the inputs are in the right order. If the left integer is higher than the right integer, the inputs
 * are not in the right order. Otherwise, the inputs are the same integer; continue checking the next part of the input.
 * If both values are lists, compare the first value of each list, then the second value, and so on. If the left
 * list runs out of items first, the inputs are in the right order. If the right list runs out of items first, the
 * inputs are not in the right order. If the lists are the same length and no comparison makes a decision about the
 * order, continue checking the next part of the input.
 * If exactly one value is an integer, convert the integer to a list which contains that integer as its only value,
 * then retry the comparison. For example, if comparing [0,0,0] and 2, convert the right value to [2] (a list
 * containing 2); the result is then found by instead comparing [0,0,0] and [2].
 * Using these rules, you can determine which of the pairs in the example are in the right order:
 *
 * == Pair 1 ==
 * - Compare [1,1,3,1,1] vs [1,1,5,1,1]
 * - Compare 1 vs 1
 * - Compare 1 vs 1
 * - Compare 3 vs 5
 * - Left side is smaller, so inputs are in the right order
 *
 * == Pair 2 ==
 * - Compare [[1],[2,3,4]] vs [[1],4]
 * - Compare [1] vs [1]
 * - Compare 1 vs 1
 * - Compare [2,3,4] vs 4
 * - Mixed types; convert right to [4] and retry comparison
 * - Compare [2,3,4] vs [4]
 * - Compare 2 vs 4
 * - Left side is smaller, so inputs are in the right order
 *
 * == Pair 3 ==
 * - Compare [9] vs [[8,7,6]]
 * - Compare 9 vs [8,7,6]
 * - Mixed types; convert left to [9] and retry comparison
 * - Compare [9] vs [8,7,6]
 * - Compare 9 vs 8
 * - Right side is smaller, so inputs are not in the right order
 *
 * == Pair 4 ==
 * - Compare [[4,4],4,4] vs [[4,4],4,4,4]
 * - Compare [4,4] vs [4,4]
 * - Compare 4 vs 4
 * - Compare 4 vs 4
 * - Compare 4 vs 4
 * - Compare 4 vs 4
 * - Left side ran out of items, so inputs are in the right order
 *
 * == Pair 5 ==
 * - Compare [7,7,7,7] vs [7,7,7]
 * - Compare 7 vs 7
 * - Compare 7 vs 7
 * - Compare 7 vs 7
 * - Right side ran out of items, so inputs are not in the right order
 *
 * == Pair 6 ==
 * - Compare [] vs [3]
 * - Left side ran out of items, so inputs are in the right order
 *
 * == Pair 7 ==
 * - Compare [[[]]] vs [[]]
 * - Compare [[]] vs []
 * - Right side ran out of items, so inputs are not in the right order
 *
 * == Pair 8 ==
 * - Compare [1,[2,[3,[4,[5,6,7]]]],8,9] vs [1,[2,[3,[4,[5,6,0]]]],8,9]
 * - Compare 1 vs 1
 * - Compare [2,[3,[4,[5,6,7]]]] vs [2,[3,[4,[5,6,0]]]]
 * - Compare 2 vs 2
 * - Compare [3,[4,[5,6,7]]] vs [3,[4,[5,6,0]]]
 * - Compare 3 vs 3
 * - Compare [4,[5,6,7]] vs [4,[5,6,0]]
 * - Compare 4 vs 4
 * - Compare [5,6,7] vs [5,6,0]
 * - Compare 5 vs 5
 * - Compare 6 vs 6
 * - Compare 7 vs 0
 * - Right side is smaller, so inputs are not in the right order
 * What are the indices of the pairs that are already in the right order? (The first pair has index 1, the second pair
 * has index 2, and so on.) In the above example, the pairs in the right order are 1, 2, 4, and 6; the sum of these
 * indices is 13.
 *
 * Determine which pairs of packets are already in the right order. What is the sum of the indices of those pairs?
 *
 * Your puzzle answer was 4734.
 */

public class Day13 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv13.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            int counter = 0;
            String s1 = "";
            String s2 = "";
            int countIndices = 0;
            int index = 0;

            while ((line = br.readLine()) != null) {
                counter++;
                if (counter % 3 == 1) {
                    System.out.println("-----------------------------");
                    System.out.println(line);
                    s1 = line;
                    index++;
                } else if (counter % 3 == 2) {
                    System.out.println(line);
                    s2 = line;
                } else if (counter % 3 == 0) {
                    if (line.isEmpty()) {
                        System.out.println("index=" + index);
                        int res = compareLists(s1, s2);
                        if (res == 1) {
                            countIndices += index;
                            System.out.println("valid, indices=" + countIndices);
                        }
                    }
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + countIndices);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int compareLists(String levy, String pravy) {
        System.out.println("comparing " + levy + " and " + pravy);
        levy = stripBracket(levy);
        pravy = stripBracket(pravy);
        while (!levy.isEmpty() || !pravy.isEmpty()) {
            String levyElem = getElem(levy);
            String pravyElem = getElem(pravy);
            levy = levy.substring(levyElem.length());
            if (levy.startsWith(",")) {
                levy = levy.replaceFirst(",", "");
            }
            pravy = pravy.substring(pravyElem.length());
            if (pravy.startsWith(",")) {
                pravy = pravy.replaceFirst(",", "");
            }
            int res;
            if (isList(levyElem) && isList(pravyElem)) {
                res = compareLists(levyElem, pravyElem);
            } else if (isList(levyElem)) {
                if (pravyElem.isEmpty()) {
                    res = -1;
                } else {
                    pravyElem = makeList(pravyElem);
                    res = compareLists(levyElem, pravyElem);
                }
            } else if (isList(pravyElem)) {
                if (levyElem.isEmpty()) {
                    res = 1;
                } else {
                    levyElem = makeList(levyElem);
                    res = compareLists(levyElem, pravyElem);
                }
            } else {
                res = compareInts(levyElem, pravyElem);
            }
            if (res == 1) {
                return 1;
            } else if (res == -1) {
                return -1;
            }
        }
        return 0;
    }

    public static int compareInts(String levy, String pravy) {
        if (levy.isEmpty() && !pravy.isEmpty()) {
            return 1;
        } else if (!levy.isEmpty() && pravy.isEmpty()) {
            return -1;
        }
        int l = Integer.parseInt(levy);
        int p = Integer.parseInt(pravy);
        System.out.println("comparing " + l + " with " + p);
        if (l < p) {
            return 1;
        } else if (l > p) {
            return -1;
        }
        return 0;
    }

    public static String stripBracket(String s) {
        if (s.length() >= 3) {
            return s.substring(1, s.length() - 1);
        } else return "";
    }

    public static String getElem(String s) {
        System.out.print("     first elem from " + s + " is ");
        if (s.isEmpty()) {
            System.out.println("empty");
            return "";
        }
        if (s.charAt(0) == '[') { // list
            System.out.print("list ");
            int bracketCnt = 1;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '[') {
                    bracketCnt++;
                } else if (s.charAt(i) == ']') {
                    bracketCnt--;
                    if (bracketCnt == 0) {
                        System.out.println(s.substring(0, i + 1));
                        return s.substring(0, i + 1);
                    }
                }
            }
        } else { // integer
            System.out.print("integer ");
            int i = s.indexOf(',');
            if (i <= 0) {
                System.out.println(s);
                return s;
            } else {
                System.out.println(s.substring(0, i));
                return s.substring(0, i);
            }
        }
        return "";
    }

    public static boolean isList(String s) {
        return s.startsWith("[");
    }

    public static String makeList(String s) {
        return ("[" + s + "]");
    }
}