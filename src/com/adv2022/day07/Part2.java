package com.adv2022.day07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * https://adventofcode.com/2022/day/7
 * --- Day 7: No Space Left On Device ---
 * --- Part Two ---
 * Now, you're ready to choose a directory to delete.
 *
 * The total disk space available to the filesystem is 70000000. To run the update, you need unused space of
 * at least 30000000. You need to find a directory you can delete that will free up enough space to run the update.
 *
 * In the example above, the total size of the outermost directory (and thus the total amount of used space)
 * is 48381165; this means that the size of the unused space must currently be 21618835, which isn't quite
 * the 30000000 required by the update. Therefore, the update still requires a directory with total size of
 * at least 8381165 to be deleted before it can run.
 *
 * To achieve this, you have the following options:
 *
 * Delete directory e, which would increase unused space by 584.
 * Delete directory a, which would increase unused space by 94853.
 * Delete directory d, which would increase unused space by 24933642.
 * Delete directory /, which would increase unused space by 48381165.
 * Directories e and a are both too small; deleting them would not free up enough space. However,
 * directories d and / are both big enough! Between these, choose the smallest: d, increasing unused
 * space by 24933642.
 *
 * Find the smallest directory that, if deleted, would free up enough space on the filesystem to run the update.
 * What is the total size of that directory?
 *
 * Your puzzle answer was 10475598.
 */

public class Part2 {
    public static final  int  FILE_SYSTEM_SIZE = 70000000;
    public static final  int  REQURED_FOR_UPDATE_SIZE = 30000000;
    public static  int TOTAL;

    public static void main(String[] args) {

        try {
            File file = new File("resources/adv07.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            MyTree tree = new MyTree();
            Node aktualniUzel = null;

            while ((line = br.readLine()) != null) {
                String[] part = line.split(" ");
                if (part[0].equals("$")) { // prikaz
                    if (part[1].equals("cd")) {
                        if (part[2].equals("..")) {
                            aktualniUzel = aktualniUzel.getParent();
                            tree.changeIndUp();
                        } else {
                            // vytvor uzel nebo se na nej presun / po
                            Node n = new Node(part[2], aktualniUzel);
                            tree.addNode(n);
                            aktualniUzel = n;
                        }
                    }
                } else { // soubor nebo dir
                    if (!part[0].equals("dir")) {
                        int size = Integer.parseInt(part[0]);
                        tree.getIndicator().increaseSize(size);
                    }
                }
            }

            TOTAL = tree.countSize(tree.getTop());

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("smallest to delete = " + tree.getSmallestSufficient());
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}