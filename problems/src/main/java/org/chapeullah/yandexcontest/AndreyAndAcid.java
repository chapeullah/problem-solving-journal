package org.chapeullah.yandexcontest;

import java.util.Scanner;

/*
https://contest.yandex.ru/contest/28412/problems/A/
 */

public class AndreyAndAcid {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int result = -1;
        int n = scanner.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextLong();
        }

        long max = a[0], min = a[0], temp = a[0];
        int maxIndex = 0;

        for (int i = 1; i < a.length; ++i) {
            if (temp > a[i]) {
                System.out.println(result);
                return;
            }
            if (max < a[i]) {
                max = a[i];
                maxIndex = i;
            }
            min = Math.min(min, a[i]);
            temp = a[i];
        }

        result = Math.toIntExact(max - min);

        System.out.println(result);
    }
}