package com.adv2022.day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://adventofcode.com/2022/day/3
 * --- Day 3: Rucksack Reorganization ---
 * --- Part Two ---
 * As you finish identifying the misplaced items, the Elves come to you with another issue.
 *
 * For safety, the Elves are divided into groups of three. Every Elf carries a badge that identifies their group.
 * For efficiency, within each group of three Elves, the badge is the only item type carried by all three Elves.
 * That is, if a group's badge is item type B, then all three Elves will have item type B somewhere in their rucksack,
 * and at most two of the Elves will be carrying any other item type.
 *
 * The problem is that someone forgot to put this year's updated authenticity sticker on the badges. All of the
 * badges need to be pulled out of the rucksacks so the new authenticity stickers can be attached.
 *
 * Additionally, nobody wrote down which item type corresponds to each group's badges. The only way to tell which
 * item type is the right one is by finding the one item type that is common between all three Elves in each group.
 *
 * Every set of three lines in your list corresponds to a single group, but each group can have a different badge
 * item type. So, in the above example, the first group's rucksacks are the first three lines:
 *
 * vJrwpWtwJgWrhcsFMMfFFhFp
 * jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
 * PmmdzqPrVvPwwTWBwg
 * And the second group's rucksacks are the next three lines:
 *
 * wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
 * ttgJtRGJQctTZtZT
 * CrZsJsPPZsGzwwsLwLmpwMDw
 * In the first group, the only item type that appears in all three rucksacks is lowercase r; this must be their
 * badges. In the second group, their badge item type must be Z.
 *
 * Priorities for these items must still be found to organize the sticker attachment efforts: here, they are 18 (r)
 * for the first group and 52 (Z) for the second group. The sum of these is 70.
 *
 * Find the item type that corresponds to the badges of each three-Elf group. What is the sum of the priorities
 * of those item types?
 *
 * Your puzzle answer was 2604.
 */

public class Part2 {

    public static String LOWER = "abcdefghijklmnopqrstuvwxyz";
    public static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv03.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String elf1, elf2, elf3;
            int sum = 0;

            while ((line = br.readLine()) != null) {
                System.out.println("-------------------------------------");
                elf1 = line;
                elf2 = br.readLine();
                elf3 = br.readLine();
                System.out.println(elf1);
                System.out.println(elf2);
                System.out.println(elf3);
                char inter = intersection(elf1, elf2, elf3);
                System.out.println("inters = " + inter);

                int l = LOWER.indexOf(inter);
                if (l >= 0) {
                    l++;
                    System.out.println("male " + l);
                } else {
                    l = UPPER.indexOf(inter);
                    l += 27;
                    System.out.println("velke " + l);
                }
                sum += l;

            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + sum);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static char intersection(String s1, String s2, String s3) {
        char[] cha1 = s1.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : cha1) {
            set.add(c);
        }
        List<Character> inters = set.stream().filter(c -> s2.contains(c.toString())).collect(Collectors.toList());
        inters = inters.stream().filter(c -> s3.contains(c.toString())).collect(Collectors.toList());
        return inters.toString().charAt(1);
    }
}