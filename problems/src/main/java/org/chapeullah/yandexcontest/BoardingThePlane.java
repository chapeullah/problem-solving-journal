package org.chapeullah.yandexcontest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
https://contest.yandex.ru/contest/28412/problems/B/
 */

public class BoardingThePlane {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        char[][] seats = new char[n][7];
        for (int i = 0; i < n; ++i)
            seats[i] = reader.readLine().toCharArray();

        int m = Integer.parseInt(reader.readLine());

        for (int i = 0; i < m; ++i) {
            String[] parts = reader.readLine().split(" ");

            int num = Integer.parseInt(parts[0]);
            String side = parts[1];
            String position = parts[2];

            int start, end, resultRow = -1;
            if (side.equals("left")){
                if (position.equals("window")) {
                    start = 0;
                    end = start + num - 1;
                } else {
                    end = 2;
                    start = end - num + 1;
                }
            } else {
                if (position.equals("window")) {
                    end = 6;
                    start = end - num + 1;
                } else {
                    start = 4;
                    end = start + num - 1;
                }
            }

            for (int row = 0; row < n; ++row) {
                boolean free = true;
                for (int ch = start; ch <= end; ++ch) {
                    if (seats[row][ch] == '#') {
                        free = false;
                        break;
                    }
                }
                if (free) {
                    resultRow = row;
                    break;
                }
            }

            char[] seatLetters = new char[] {'A', 'B', 'C', '_', 'D', 'E', 'F'};
            if (resultRow == -1) {
                System.out.println("Cannot fulfill passengers requirements");
            } else {
                System.out.print("Passengers can take seats: ");
                for (int ch = start; ch <= end; ++ch) {
                    seats[resultRow][ch] = 'X';

                    if (ch > start) System.out.print(" ");
                    System.out.print(resultRow + 1);
                    System.out.print(seatLetters[ch]);
                }
                System.out.println();
                for (char[] row : seats) {
                    System.out.println(row);
                }
                for (int ch = start; ch <= end; ++ch) {
                    seats[resultRow][ch] = '#';
                }
            }

        }
        reader.close();
    }
}
