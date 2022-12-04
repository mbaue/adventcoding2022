package com.adv2022.day04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/4
 * --- Day 4: Camp Cleanup ---
 * --- Part Two ---
 * It seems like there is still quite a bit of duplicate work planned. Instead, the Elves would like to know
 * the number of pairs that overlap at all.
 *
 * In the above example, the first two pairs (2-4,6-8 and 2-3,4-5) don't overlap, while the remaining
 * four pairs (5-7,7-9, 2-8,3-7, 6-6,4-6, and 2-6,4-8) do overlap:
 *
 * 5-7,7-9 overlaps in a single section, 7.
 * 2-8,3-7 overlaps all of the sections 3 through 7.
 * 6-6,4-6 overlaps in a single section, 6.
 * 2-6,4-8 overlaps in sections 4, 5, and 6.
 * So, in this example, the number of overlapping assignment pairs is 4.
 *
 * In how many assignment pairs do the ranges overlap?
 *
 * Your puzzle answer was 874.
 */

public class Part2 {

    public static void main(String[] args) {
        try {
            File file = new File("resources/adv04.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int sum = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] assign = line.replace(",", "-").split("-");
                int e1low = Integer.parseInt(assign[0]);
                int e1high = Integer.parseInt(assign[1]);
                int e2low = Integer.parseInt(assign[2]);
                int e2high = Integer.parseInt(assign[3]);

                if ((e1low >= e2low && e1low <= e2high)
                        || (e1high >= e2low && e1high <= e2low)
                        || (e2low >= e1low && e2low <= e1high)
                        || (e2high >= e1low && e2high <= e1high)){
                    sum++;
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + sum);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
