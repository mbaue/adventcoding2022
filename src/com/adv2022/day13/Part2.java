package com.adv2022.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://adventofcode.com/2022/day/13
 * --- Part Two ---
 * Now, you just need to put all of the packets in the right order. Disregard the blank lines in your list
 * of received packets.
 *
 * The distress signal protocol also requires that you include two additional divider packets:
 *
 * [[2]]
 * [[6]]
 * Using the same rules as before, organize all packets - the ones in your list of received packets as well as
 * the two divider packets - into the correct order.
 *
 * For the example above, the result of putting the packets in the correct order is:
 *
 * []
 * [[]]
 * [[[]]]
 * [1,1,3,1,1]
 * [1,1,5,1,1]
 * [[1],[2,3,4]]
 * [1,[2,[3,[4,[5,6,0]]]],8,9]
 * [1,[2,[3,[4,[5,6,7]]]],8,9]
 * [[1],4]
 * [[2]]
 * [3]
 * [[4,4],4,4]
 * [[4,4],4,4,4]
 * [[6]]
 * [7,7,7]
 * [7,7,7,7]
 * [[8,7,6]]
 * [9]
 * Afterward, locate the divider packets. To find the decoder key for this distress signal, you need to determine
 * the indices of the two divider packets and multiply them together. (The first packet is at index 1, the second
 * packet is at index 2, and so on.) In this example, the divider packets are 10th and 14th, and so the decoder key
 * is 140.
 *
 * Organize all of the packets into the correct order. What is the decoder key for the distress signal?
 *
 * Your puzzle answer was 21836.
 */

public class Part2 {

    public static List<String> myList = new ArrayList<>();

    public static final String DIV1 = "[[2]]";
    public static final String DIV2 = "[[6]]";

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv13.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            myList.add(DIV1);
            myList.add(DIV2);

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (!line.isEmpty()) {
                    myList.add(line);
                }
            }

            Comparator<String> myComp = ((s1, s2) -> Day13.compareLists(s2, s1));
            myList.sort(myComp);

            int i1 = myList.indexOf(DIV1) + 1;
            int i2 = myList.indexOf(DIV2) + 1;

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + (i1 * i2));
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}