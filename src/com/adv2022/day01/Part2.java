package com.adv2022.day01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/1
 * --- Day 1: Calorie Counting ---
 * --- Part Two ---
 * By the time you calculate the answer to the Elves' question, they've already realized that the Elf
 * carrying the most Calories of food might eventually run out of snacks.
 *
 * To avoid this unacceptable situation, the Elves would instead like to know the total Calories carried
 * by the top three Elves carrying the most Calories. That way, even if one of those Elves runs out of snacks,
 * they still have two backups.
 *
 * In the example above, the top three Elves are the fourth Elf (with 24000 Calories), then the third Elf
 * (with 11000 Calories), then the fifth Elf (with 10000 Calories). The sum of the Calories carried by
 * these three elves is 45000.
 *
 * Find the top three Elves carrying the most Calories. How many Calories are those Elves carrying in total?
 *
 * Your puzzle answer was 210367.
 */

public class Part2 {

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv01.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int max1Calories = 0;
            int max2Calories = 0;
            int max3Calories = 0;
            int elfCalories = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);

                if (line.equals("")) {
                    if (elfCalories > max1Calories) {
                        max3Calories = max2Calories;
                        max2Calories = max1Calories;
                        max1Calories = elfCalories;
                    } else if (elfCalories > max2Calories) {
                        max3Calories = max2Calories;
                        max2Calories = elfCalories;
                    } else if (elfCalories > max3Calories) {
                        max3Calories = elfCalories;
                    }
                    elfCalories = 0;
                    System.out.println("max 1 elf " + max1Calories);
                    System.out.println("max 2 elf " + max2Calories);
                    System.out.println("max 3 elf " + max3Calories);
                } else {
                    elfCalories += Integer.parseInt(line);
                }
            }

            if (elfCalories > max1Calories) {
                max3Calories = max2Calories;
                max2Calories = max1Calories;
                max1Calories = elfCalories;
            } else if (elfCalories > max2Calories) {
                max3Calories = max2Calories;
                max2Calories = elfCalories;
            } else if (elfCalories > max3Calories) {
                max3Calories = elfCalories;
            }
            System.out.println("max 1 elf " + max1Calories);
            System.out.println("max 2 elf " + max2Calories);
            System.out.println("max 3 elf " + max3Calories);

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("max cal = " + (max1Calories + max2Calories + max3Calories));
            System.out.println("----------------------------------------------------------------");

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
