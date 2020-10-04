package ru.mail.polis.ads.part2.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem3738 {
    private Problem3738() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int length = in.nextInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = in.nextInt();
        }
        quickSort(array, 0, array.length);
        for (int i = 0; i < length; i++) {
            out.write(array[i] + " ");
        }
    }

    private static void quickSort(int[] array, int lo, int hi) {
        if (hi - lo <= 1) {
            return;
        }
        int middle = partition(array, lo, hi);
        quickSort(array, lo, middle + 1);
        quickSort(array, middle + 1, hi);
    }

    private static int partition(int[] array, int lo, int hi) {
        int i = lo, j = hi - 1;
        int pivot = array[lo + (int) ((hi - lo) * Math.random())];
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i >= j) {
                break;
            }
            int swap = array[i];
            array[i] = array[j];
            array[j] = swap;
            i++;
            j--;
        }
        return j;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) throws IOException {
        final FastScanner in = new FastScanner(System.in);
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
            out.flush();
        }
    }
}
