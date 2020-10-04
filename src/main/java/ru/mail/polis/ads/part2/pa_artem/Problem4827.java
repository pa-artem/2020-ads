package ru.mail.polis.ads.part2.pa_artem;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem4827 {
    private static int partition(BigInteger[] array, int lo, int hi) {
        int pivotIndex = lo + (int) ((hi - lo) * Math.random());
        {
            BigInteger swap = array[pivotIndex];
            array[pivotIndex] = array[lo];
            array[lo] = swap;
        }
        BigInteger pivot = array[lo];
        int j = lo;

        for (int i = lo + 1; i < hi; i++) {
            if (array[i].compareTo(pivot) >= 0) {
                j++;
                BigInteger swap = array[i];
                array[i] = array[j];
                array[j] = swap;
            }
        }

        BigInteger swap = array[lo];
        array[lo] = array[j];
        array[j] = swap;

        return j;
    }

    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int k = Integer.parseInt(in.nextLine());
        String[] stringNumbers = in.nextLine().split(" ");
        BigInteger[] array = new BigInteger[stringNumbers.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new BigInteger(stringNumbers[i]);
        }

        int lo = 0, hi = array.length;
        BigInteger kthLargestValue = null;
        while (true) {
            int middle = partition(array, lo, hi);
            if (middle + 1 == k) {
                kthLargestValue = array[middle];
                break;
            }
            if (middle + 1 > k) {
                lo = middle + 1;
            } else {
                hi = middle;
            }
        }

        System.out.println(kthLargestValue.toString());
    }
}
