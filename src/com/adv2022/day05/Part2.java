package com.adv2022.day05;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Part2 {

    public static void main(String[] args) {
        try {

            File file = new File("resources/adv05.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            Stack<String> stackLines = new Stack<>();
            boolean instFlg = false;
            Stack<Character> sta1 = new Stack<>();
            Stack<Character> sta2 = new Stack<>();
            Stack<Character> sta3 = new Stack<>();
            Stack<Character> sta4 = new Stack<>();
            Stack<Character> sta5 = new Stack<>();
            Stack<Character> sta6 = new Stack<>();
            Stack<Character> sta7 = new Stack<>();
            Stack<Character> sta8 = new Stack<>();
            Stack<Character> sta9 = new Stack<>();
            List<Stack<Character>> lst = new ArrayList<>();
            lst.add(sta1);
            lst.add(sta2);
            lst.add(sta3);
            lst.add(sta4);
            lst.add(sta5);
            lst.add(sta6);
            lst.add(sta7);
            lst.add(sta8);
            lst.add(sta9);


            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (!instFlg) {
                    if (line.indexOf('[') >= 0) {
                        stackLines.add(line);
                    } else if (line.equals("")) {
                        instFlg = true;
                    } else {
                        while (!stackLines.empty()) {
                            String s = stackLines.pop();
                            char ch;
                            ch = s.charAt(1);
                            if (ch != ' ') {
                                sta1.add(ch);
                            }
                            ch = s.charAt(5);
                            if (ch != ' ') {
                                sta2.add(ch);
                            }
                            ch = s.charAt(9);
                            if (ch != ' ') {
                                sta3.add(ch);
                            }
                            ch = s.charAt(13);
                            if (ch != ' ') {
                                sta4.add(ch);
                            }
                            ch = s.charAt(17);
                            if (ch != ' ') {
                                sta5.add(ch);
                            }
                            ch = s.charAt(21);
                            if (ch != ' ') {
                                sta6.add(ch);
                            }
                            ch = s.charAt(25);
                            if (ch != ' ') {
                                sta7.add(ch);
                            }
                            ch = s.charAt(29);
                            if (ch != ' ') {
                                sta8.add(ch);
                            }
                            ch = s.charAt(33);
                            if (ch != ' ') {
                                sta9.add(ch);
                            }
                        }
                    }
                } else {
                    String[] x = line.split(" ");
                    Stack<Character> pom = new Stack<>();
                    int amount = Integer.parseInt(x[1]);
                    int from = Integer.parseInt(x[3]);
                    int to = Integer.parseInt(x[5]);
                    for (int i = amount; i > 0; i--) {
                        Stack<Character> stckFrom = lst.get(from - 1);
                        pom.push(stckFrom.pop());
                    }
                    for (int i = amount; i > 0; i--) {
                        Stack<Character> stckTo = lst.get(to - 1);
                        stckTo.push(pom.pop());
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for (Stack<Character> s : lst) {
                char a = s.peek();
                sb.append(a);
            }

            System.out.println("-------------------SOLUTION-------------------------------------");
            System.out.println("my score is = " + sb);
            System.out.println("----------------------------------------------------------------");
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}