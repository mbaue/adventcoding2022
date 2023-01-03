package com.adv2022.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://adventofcode.com/2022/day/5
 * --- Day 5: Supply Stacks ---
 * The expedition can depart as soon as the final supplies have been unloaded from the ships. Supplies are stored
 * in stacks of marked crates, but because the needed supplies are buried under many other crates, the crates need
 * to be rearranged.
 *
 * The ship has a giant cargo crane capable of moving crates between stacks. To ensure none of the crates get
 * crushed or fall over, the crane operator will rearrange them in a series of carefully-planned steps. After
 * the crates are rearranged, the desired crates will be at the top of each stack.
 *
 * The Elves don't want to interrupt the crane operator during this delicate procedure, but they forgot to ask
 * her which crate will end up where, and they want to be ready to unload them as soon as possible so they can embark.
 *
 * They do, however, have a drawing of the starting stacks of crates and the rearrangement procedure
 * (your puzzle input). For example:
 *
 *     [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 *
 * move 1 from 2 to 1
 * move 3 from 1 to 3
 * move 2 from 2 to 1
 * move 1 from 1 to 2
 * In this example, there are three stacks of crates. Stack 1 contains two crates: crate Z is on the bottom, and
 * crate N is on top. Stack 2 contains three crates; from bottom to top, they are crates M, C, and D. Finally,
 * stack 3 contains a single crate, P.
 *
 * Then, the rearrangement procedure is given. In each step of the procedure, a quantity of crates is moved from one
 * stack to a different stack. In the first step of the above rearrangement procedure, one crate is moved from
 * stack 2 to stack 1, resulting in this configuration:
 *
 * [D]
 * [N] [C]
 * [Z] [M] [P]
 *  1   2   3
 * In the second step, three crates are moved from stack 1 to stack 3. Crates are moved one at a time, so the first
 * crate to be moved (D) ends up below the second and third crates:
 *
 *         [Z]
 *         [N]
 *     [C] [D]
 *     [M] [P]
 *  1   2   3
 * Then, both crates are moved from stack 2 to stack 1. Again, because crates are moved one at a time, crate C ends up
 * below crate M:
 *
 *         [Z]
 *         [N]
 * [M]     [D]
 * [C]     [P]
 *  1   2   3
 * Finally, one crate is moved from stack 1 to stack 2:
 *
 *         [Z]
 *         [N]
 *         [D]
 * [C] [M] [P]
 *  1   2   3
 * The Elves just need to know which crate will end up on top of each stack; in this example, the top crates are C
 * in stack 1, M in stack 2, and Z in stack 3, so you should combine these together and give the Elves the message CMZ.
 *
 * After the rearrangement procedure completes, what crate ends up on top of each stack?
 *
 * Your puzzle answer was VJSFHWGFT.
 */

public class Day05 {

    public static void main(String[] args) {
        try {

            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Stack<String> stackLines = new Stack<>();
            boolean instFlg = false;
            Stack<Character> sta1 = new Stack<>();
            Stack<Character> sta2 = new Stack<>();
            Stack<Character> sta3 = new Stack<>();
            Stack<Character> sta4 = new Stack<>();
            Stack<Character> sta5 = new Stack<>();
            Stack<Character> sta6 = new Stack<>();
            Stack<Character> sta7 = new Stack<>();
            Stack<Character> sta8 = new Stack<>();
            Stack<Character> sta9 = new Stack<>();
            List<Stack<Character>> lst = new ArrayList<>();
            lst.add(sta1);
            lst.add(sta2);
            lst.add(sta3);
            lst.add(sta4);
            lst.add(sta5);
            lst.add(sta6);
            lst.add(sta7);
            lst.add(sta8);
            lst.add(sta9);


            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (!instFlg) {
                    if (line.indexOf('[') >= 0) {
                        stackLines.add(line);
                    } else if (line.equals("")) {
                        instFlg = true;
                    } else {
                        while (!stackLines.empty()) {
                            String s = stackLines.pop();
                            char ch;
                            ch = s.charAt(1);
                            if (ch != ' ') {
                                sta1.add(ch);
                            }
                            ch = s.charAt(5);
                            if (ch != ' ') {
                                sta2.add(ch);
                            }
                            ch = s.charAt(9);
                            if (ch != ' ') {
                                sta3.add(ch);
                            }
                            ch = s.charAt(13);
                            if (ch != ' ') {
                                sta4.add(ch);
                            }
                            ch = s.charAt(17);
                            if (ch != ' ') {
                                sta5.add(ch);
                            }
                            ch = s.charAt(21);
                            if (ch != ' ') {
                                sta6.add(ch);
                            }
                            ch = s.charAt(25);
                            if (ch != ' ') {
                                sta7.add(ch);
                            }
                            ch = s.charAt(29);
                            if (ch != ' ') {
                                sta8.add(ch);
                            }
                            ch = s.charAt(33);
                            if (ch != ' ') {
                                sta9.add(ch);
                            }
                        }
                    }
                } else {
                    String[] x = line.split(" ");
                    int amount = Integer.parseInt(x[1]);
                    int from = Integer.parseInt(x[3]);
                    int to = Integer.parseInt(x[5]);
                    for (int i = amount; i > 0; i--) {
                        Stack<Character> stckFrom = lst.get(from - 1);
                        Stack<Character> stckTo = lst.get(to - 1);
                        stckTo.push(stckFrom.pop());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Stack<Character> s : lst) {
                char a = s.peek();
                sb.append(a);
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + sb);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}