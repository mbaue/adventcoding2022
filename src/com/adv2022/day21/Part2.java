package com.adv2022.day21;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *  https://adventofcode.com/2022/day/21
 *  --- Day 21: Monkey Math ---
 *  --- Part Two ---
 * Due to some kind of monkey-elephant-human mistranslation, you seem to have misunderstood a few key details
 * about the riddle.
 *
 * First, you got the wrong job for the monkey named root; specifically, you got the wrong math operation.
 * The correct operation for monkey root should be =, which means that it still listens for two numbers (from
 * the same two monkeys as before), but now checks that the two numbers match.
 *
 * Second, you got the wrong monkey for the job starting with humn:. It isn't a monkey - it's you. Actually, you got
 * the job wrong, too: you need to figure out what number you need to yell so that root's equality check passes.
 * (The number that appears after humn: in your input is now irrelevant.)
 *
 * In the above example, the number you need to yell to pass root's equality test is 301. (This causes root to get
 * the same number, 150, from both of its monkeys.)
 *
 * What number do you yell to pass root's equality test?
 *
 * Your puzzle answer was 3032671800353.
 */

public class Part2 {


    static Map<String, Long> assignments = new HashMap<>();
    static Set<String> targets = new HashSet<>();
    static List<String> operations = new ArrayList<>();

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv21.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    if (!parts[0].equals("humn:")) {
                        putInAssignments(parts[0], Long.parseLong(parts[1]));
                    }
                } else {
                    if (parts[0].equals("root:")) {
                        targets.add(parts[1]);
                        targets.add(parts[3]);
                    } else if (assignments.containsKey(parts[1]) && assignments.containsKey(parts[3])) {
                        long a = assignments.get(parts[1]);
                        long b = assignments.get(parts[3]);
                        switch (parts[2]) {
                            case "+" -> putInAssignments(parts[0], (a + b));
                            case "-" -> putInAssignments(parts[0], (a - b));
                            case "*" -> putInAssignments(parts[0], (a * b));
                            case "/" -> putInAssignments(parts[0], (a / b));
                        }
                    } else {
                        operations.add(line);
                    }
                }
            }
            System.out.println("end of reading input file");
            System.out.println("pocet hotovych = " + assignments.size());
            System.out.println("pocet nehotovych = " + operations.size());
            System.out.println("=======================================================");

            System.out.println("start of processing operations using assignments");
            boolean change;
            do {
                change = false;
                ListIterator<String> iter = operations.listIterator();
                while (iter.hasNext()) {
                    String s = iter.next();
                    if (check(s)) {
                        iter.remove();
                        change = true;
                    }
                }
                System.out.println("--------------------------------");
                System.out.println("pocet hotovych = " + assignments.size());
                System.out.println("pocet nehotovych = " + operations.size());
            } while (operations.size() > 0 && change);

            System.out.println("end of processing part 1");
            System.out.println("=======================================================");
            System.out.println("start of processing derived rules");

            do {
                change = false;
                ListIterator<String> iter = operations.listIterator();
                while (iter.hasNext()) {
                    String s = iter.next();
                    if (checkPart2(s)) {
                        iter.remove();
                        change = true;
                    }
                }
                System.out.println("--------------------------------");
                System.out.println("pocet hotovych = " + assignments.size());
                System.out.println("pocet nehotovych = " + operations.size());
            } while (operations.size() > 0 && change);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + assignments.get("humn"));
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean check(String s) {
        String[] parts = s.split(" ");
        if (assignments.containsKey(parts[1]) && assignments.containsKey(parts[3])) {
            long a = assignments.get(parts[1]);
            long b = assignments.get(parts[3]);
            switch (parts[2]) {
                case "+" -> putInAssignments(parts[0], (a + b));
                case "-" -> putInAssignments(parts[0], (a - b));
                case "*" -> putInAssignments(parts[0], (a * b));
                case "/" -> putInAssignments(parts[0], (a / b));
            }
            return true;
        }
        return false;
    }

    static void putInAssignments(String key, Long value) {
        String x = key.replace(":", "");
        if (targets.contains(x)) {
            for (String a : targets) {
                assignments.put(a, value);
            }
        } else {
            assignments.put(x, value);
        }
    }

    static String getComplementOperator(String s) {
        switch (s) {
            case "+" -> {
                return "-";
            }
            case "-" -> {
                return "+";
            }
            case "*" -> {
                return "/";
            }
            case "/" -> {
                return "*";
            }
        }
        return "";
    }

    public static boolean checkPart2(String s) {
        String[] parts = s.split(" ");
        String rule = "";
        String bezDvojtecky = parts[0].replace(":", "");
        if (assignments.containsKey(bezDvojtecky)) {
            if (assignments.containsKey(parts[1])) {
                if (parts[2].equals("+") || parts[2].equals("*")) {
                    rule = parts[3] + ": " + bezDvojtecky + " " + getComplementOperator(parts[2]) + " " + parts[1];
                } else {
                    rule = parts[3] + ": " + parts[1] + " " + parts[2] + " " + bezDvojtecky;
                }
            } else if (assignments.containsKey(parts[3])) {
                if (parts[2].equals("+") || parts[2].equals("*")) {
                    rule = parts[1] + ": " + bezDvojtecky + " " + getComplementOperator(parts[2]) + " " + parts[3];
                } else {
                    rule = parts[1] + ": " + parts[3] + " " + getComplementOperator(parts[2]) + " " + bezDvojtecky;
                }
            }
            System.out.println("nove pravidlo - " + rule);
            return check(rule);
        }
        return false;
    }
}