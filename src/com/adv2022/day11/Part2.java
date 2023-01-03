package com.adv2022.day11;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://adventofcode.com/2022/day/11
 * --- Day 11: Monkey in the Middle ---
 * --- Part Two ---
 * You're worried you might not ever get your items back. So worried, in fact, that your relief that a monkey's
 * inspection didn't damage an item no longer causes your worry level to be divided by three.
 *
 * Unfortunately, that relief was all that was keeping your worry levels from reaching ridiculous levels. You'll
 * need to find another way to keep your worry levels manageable.
 *
 * At this rate, you might be putting up with these monkeys for a very long time - possibly 10000 rounds!
 *
 * With these new rules, you can still figure out the monkey business after 10000 rounds. Using the same example above:
 *
 * == After round 1 ==
 * Monkey 0 inspected items 2 times.
 * Monkey 1 inspected items 4 times.
 * Monkey 2 inspected items 3 times.
 * Monkey 3 inspected items 6 times.
 *
 * == After round 20 ==
 * Monkey 0 inspected items 99 times.
 * Monkey 1 inspected items 97 times.
 * Monkey 2 inspected items 8 times.
 * Monkey 3 inspected items 103 times.
 *
 * == After round 1000 ==
 * Monkey 0 inspected items 5204 times.
 * Monkey 1 inspected items 4792 times.
 * Monkey 2 inspected items 199 times.
 * Monkey 3 inspected items 5192 times.
 *
 * == After round 2000 ==
 * Monkey 0 inspected items 10419 times.
 * Monkey 1 inspected items 9577 times.
 * Monkey 2 inspected items 392 times.
 * Monkey 3 inspected items 10391 times.
 *
 * == After round 3000 ==
 * Monkey 0 inspected items 15638 times.
 * Monkey 1 inspected items 14358 times.
 * Monkey 2 inspected items 587 times.
 * Monkey 3 inspected items 15593 times.
 *
 * == After round 4000 ==
 * Monkey 0 inspected items 20858 times.
 * Monkey 1 inspected items 19138 times.
 * Monkey 2 inspected items 780 times.
 * Monkey 3 inspected items 20797 times.
 *
 * == After round 5000 ==
 * Monkey 0 inspected items 26075 times.
 * Monkey 1 inspected items 23921 times.
 * Monkey 2 inspected items 974 times.
 * Monkey 3 inspected items 26000 times.
 *
 * == After round 6000 ==
 * Monkey 0 inspected items 31294 times.
 * Monkey 1 inspected items 28702 times.
 * Monkey 2 inspected items 1165 times.
 * Monkey 3 inspected items 31204 times.
 *
 * == After round 7000 ==
 * Monkey 0 inspected items 36508 times.
 * Monkey 1 inspected items 33488 times.
 * Monkey 2 inspected items 1360 times.
 * Monkey 3 inspected items 36400 times.
 *
 * == After round 8000 ==
 * Monkey 0 inspected items 41728 times.
 * Monkey 1 inspected items 38268 times.
 * Monkey 2 inspected items 1553 times.
 * Monkey 3 inspected items 41606 times.
 *
 * == After round 9000 ==
 * Monkey 0 inspected items 46945 times.
 * Monkey 1 inspected items 43051 times.
 * Monkey 2 inspected items 1746 times.
 * Monkey 3 inspected items 46807 times.
 *
 * == After round 10000 ==
 * Monkey 0 inspected items 52166 times.
 * Monkey 1 inspected items 47830 times.
 * Monkey 2 inspected items 1938 times.
 * Monkey 3 inspected items 52013 times.
 * After 10000 rounds, the two most active monkeys inspected items 52166 and 52013 times. Multiplying these together,
 * the level of monkey business in this situation is now 2713310158.
 *
 * Worry levels are no longer divided by three after each item is inspected; you'll need to find another way to keep
 * your worry levels manageable. Starting again from the initial state in your puzzle input, what is the level
 * of monkey business after 10000 rounds?
 *
 * Your puzzle answer was 19457438264.
 */

public class Part2 {
    static List<Monkey> listOpic = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv11.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            Monkey actualMonkey = null;
            long product = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(line);

                if (line.startsWith("Monkey")) {
                    String[] parts = line.split(" ");
                    actualMonkey = new Monkey(parts[1].replace(":", ""));
                    listOpic.add(actualMonkey);
                } else if (line.startsWith("  Starting")) {
                    String[] parts = line.replace("  Starting items: ", "").split(", ");
                    List<Long> temp = new ArrayList<>();
                    for (String s : parts) {
                        temp.add(Long.parseLong(s));
                    }
                    actualMonkey.setItems(temp);
                } else if (line.startsWith("  Operation")) {
                    String[] parts = line.trim().split(" ");
                    if (parts[5].equals("old")) {
                        actualMonkey.setOperator("pow");
                    } else {
                        actualMonkey.setOperator(parts[4]);
                        actualMonkey.setOperand(Integer.parseInt(parts[5]));
                    }
                } else if (line.startsWith("  Test")) {
                    String[] parts = line.trim().split(" ");
                    product *= Integer.parseInt(parts[3]);
                    actualMonkey.setDivisor(Integer.parseInt(parts[3]));
                } else if (line.startsWith("    If true")) {
                    String[] parts = line.trim().split(" ");
                    actualMonkey.setIfTrueMonkey("monkey" + parts[5]);
                } else if (line.startsWith("    If false")) {
                    String[] parts = line.trim().split(" ");
                    actualMonkey.setIfFalseMonkey("monkey" + parts[5]);
                }
            }
            System.out.println("prod " + product);
            System.out.println("----------------------------");
            for (int cyc = 1; cyc <= 10000; cyc++) {
                System.out.println("---------round: " + cyc + "---------------");
                for (Monkey m : listOpic) {
                    System.out.println(m);
                    while (m.getItems().size() > 0) {
                        long item = m.getNextItem();
                        // incr worry
                        int num = m.getOperand();
                        switch (m.getOperator()) {
                            case "+" -> item = item + num;
                            case "*" -> item = item * num;
                            case "pow" -> item = item * item;
                        }
                        m.incrThrowNumber();
                        // decr worry
                        item = item % product;
                        // test cond
                        if (item % m.getDivisor() == 0) {
                            // true
                            int i = Integer.parseInt(m.getIfTrueMonkey().replace("monkey", ""));
                            listOpic.get(i).addNewItem(item);
                        } else {
                            // false
                            int i = Integer.parseInt(m.getIfFalseMonkey().replace("monkey", ""));
                            listOpic.get(i).addNewItem(item);
                        }
                    }
                }
            }

            System.out.println("--------------------------------------------");
            List<Long> inspections = new ArrayList<>();
            for (Monkey m : listOpic) {
                System.out.println(m.getName() + " inspected item " + m.getThrowNum() + " times");
                inspections.add(m.getThrowNum());
            }
            Collections.sort(inspections);
            Collections.reverse(inspections);
            long solution = inspections.get(0) * inspections.get(1);
            System.out.println("soucin delitelu = " + product);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("solution = " + solution);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}