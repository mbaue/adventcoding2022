package com.adv2022.day06;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/6
 * --- Day 6: Tuning Trouble ---
 * --- Part Two ---
 * Your device's communication system is correctly detecting packets, but still isn't working. It looks like it
 * also needs to look for messages.
 *
 * A start-of-message marker is just like a start-of-packet marker, except it consists of 14 distinct characters
 * rather than 4.
 *
 * Here are the first positions of start-of-message markers for all of the above examples:
 *
 * mjqjpqmgbljsphdztnvjfqwrcgsmlb: first marker after character 19
 * bvwbjplbgvbhsrlpgdmjqwftvncz: first marker after character 23
 * nppdvjthqldpwncqszvftbrmjlhg: first marker after character 23
 * nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg: first marker after character 29
 * zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw: first marker after character 26
 * How many characters need to be processed before the first start-of-message marker is detected?
 *
 * Your puzzle answer was 2447.
 */

public class Part2 {

    static final int LENGTH_OF_STRING = 14;

    public static void main(String[] args) {
        try {

            File file = new File("resources/adv06.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            int solution = 0;

            for (int i = 0; i < line.length()-LENGTH_OF_STRING; i++){
                String s = line.substring(i, i+LENGTH_OF_STRING);
                if (check(s)) {
                    solution = i+LENGTH_OF_STRING;
                    break;
                }
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my solutin is = " + solution);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean check(String s) {
        for (int i = 0; i < LENGTH_OF_STRING-1; i++) {
            char ch = s.charAt(i);
            if (s.substring(i+1, LENGTH_OF_STRING).indexOf(ch)>=0){
                return false;
            }
        }
        return true;
    }
}