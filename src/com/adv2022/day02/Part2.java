package com.adv2022.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/2
 * --- Day 2: Rock Paper Scissors ---
 * --- Part Two ---
 * The Elf finishes helping with the tent and sneaks back over to you. "Anyway, the second column says how the
 * round needs to end: X means you need to lose, Y means you need to end the round in a draw, and Z means you
 * need to win. Good luck!"
 *
 * The total score is still calculated in the same way, but now you need to figure out what shape to choose so
 * the round ends as indicated. The example above now goes like this:
 *
 * In the first round, your opponent will choose Rock (A), and you need the round to end in a draw (Y), so you
 * also choose Rock. This gives you a score of 1 + 3 = 4.
 * In the second round, your opponent will choose Paper (B), and you choose Rock so you lose (X) with a
 * score of 1 + 0 = 1.
 * In the third round, you will defeat your opponent's Scissors with Rock for a score of 1 + 6 = 7.
 * Now that you're correctly decrypting the ultra top secret strategy guide, you would get a total score of 12.
 *
 * Following the Elf's instructions for the second column, what would your total score be if everything goes
 * exactly according to your strategy guide?
 *
 * Your puzzle answer was 14859.
 */

public class Part2 {
    static final int result[] = {0, 3, 6}; // points for result of the game lose/draw/win
    static final int[][] tab = { // tab[elf's gesture][lose/draw/win] -> points for my gesture necessary for desired result
            {3, 1, 2},
            {1, 2, 3},
            {2, 3, 1}};

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv02.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int score = 0;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String elf = line.split(" ")[0];
                String me = line.split(" ")[1];

                int iElf = convertGestureToNum(elf);
                int iMe = convertGestureToNum(me);

                System.out.println("points my_gesture=" + tab[iElf][iMe] + " + game_result=" + result[iMe]);
                score = score + tab[iElf][iMe] + result[iMe];

            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + score);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int convertGestureToNum(String s) {
        switch (s) {
            case "A", "X" -> {
                System.out.println("kamen");
                return 0;
            }
            case "B", "Y" -> {
                System.out.println("papir");
                return 1;
            }
            case "C", "Z" -> {
                System.out.println("nuzky");
                return 2;
            }
        }
        return -1;
    }
}