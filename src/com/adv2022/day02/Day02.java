package com.adv2022.day02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/2
 * --- Day 2: Rock Paper Scissors ---
 * The Elves begin to set up camp on the beach. To decide whose tent gets to be closest to the snack storage,
 * a giant Rock Paper Scissors tournament is already in progress.
 *
 * Rock Paper Scissors is a game between two players. Each game contains many rounds; in each round, the players
 * each simultaneously choose one of Rock, Paper, or Scissors using a hand shape. Then, a winner for that round
 * is selected: Rock defeats Scissors, Scissors defeats Paper, and Paper defeats Rock. If both players choose
 * the same shape, the round instead ends in a draw.
 *
 * Appreciative of your help yesterday, one Elf gives you an encrypted strategy guide (your puzzle input) that
 * they say will be sure to help you win. "The first column is what your opponent is going to play: A for Rock,
 * B for Paper, and C for Scissors. The second column--" Suddenly, the Elf is called away to help with someone's tent.
 *
 * The second column, you reason, must be what you should play in response: X for Rock, Y for Paper, and Z for
 * Scissors. Winning every time would be suspicious, so the responses must have been carefully chosen.
 *
 * The winner of the whole tournament is the player with the highest score. Your total score is the sum of your
 * scores for each round. The score for a single round is the score for the shape you selected (1 for Rock, 2 for
 * Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw,
 * and 6 if you won).
 *
 * Since you can't be sure if the Elf is trying to help you or trick you, you should calculate the score you would
 * get if you were to follow the strategy guide.
 *
 * For example, suppose you were given the following strategy guide:
 *
 * A Y
 * B X
 * C Z
 * This strategy guide predicts and recommends the following:
 *
 * In the first round, your opponent will choose Rock (A), and you should choose Paper (Y). This ends in a win
 * for you with a score of 8 (2 because you chose Paper + 6 because you won).
 * In the second round, your opponent will choose Paper (B), and you should choose Rock (X). This ends in a loss
 * for you with a score of 1 (1 + 0).
 * The third round is a draw with both players choosing Scissors, giving you a score of 3 + 3 = 6.
 * In this example, if you were to follow the strategy guide, you would get a total score of 15 (8 + 1 + 6).
 *
 * What would your total score be if everything goes exactly according to your strategy guide?
 *
 * Your puzzle answer was 10310.
 */

public class Day02 {

    static final int[][] tab = { // tab[elf's gesture][my gesture] -> poinst for lose/draw/win
            {3, 6, 0},
            {0, 3, 6},
            {6, 0, 3}};

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
                System.out.println(iElf);
                System.out.println(iMe);

                System.out.println("points result=" + tab[iElf][iMe] + " + my_gesture=" + (iMe + 1));
                score = score
                        + tab[iElf][iMe] // result of the game from "table"
                        + iMe + 1; // points for my gesture (columns start from 0, so +1
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
