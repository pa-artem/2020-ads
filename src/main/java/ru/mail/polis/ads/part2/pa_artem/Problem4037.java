package ru.mail.polis.ads.part2.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem4037 {
    private Problem4037() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int length = in.nextInt();
        int[][] array = new int[length][2], buffer = new int[length][2];
        for (int i = 0; i < length; i++) {
            array[i][0] = in.nextInt();
            array[i][1] = in.nextInt();
        }
        sort(array, 0, array.length, 0, buffer);
        for (int[] ints : array) {
            out.write(ints[0] + " " + ints[1] + "\n");
        }
    }

    private static void sort(int[][] array, int lo, int hi, int by, int[][] buffer) {
        if (hi - lo <= 1) {
            return;
        }

        int middle = (lo + hi) / 2;
        sort(array, lo, middle, by, buffer);
        sort(array, middle, hi, by, buffer);
        merge(array, lo, middle, hi, by, buffer);
    }

    private static void merge(int[][] array, int lo, int middle, int hi, int by, int[][] buffer) {
        System.arraycopy(array, lo, buffer, lo, hi - lo);

        int i = lo, j = middle;
        for (int k = lo; k < hi; k++) {
            if (i >= middle) {
                array[k] = buffer[j++];
            } else if (j >= hi) {
                array[k] = buffer[i++];
            } else if (buffer[i][by] <= buffer[j][by]) {
                array[k] = buffer[i++];
            } else {
                array[k] = buffer[j++];
            }
        }
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
