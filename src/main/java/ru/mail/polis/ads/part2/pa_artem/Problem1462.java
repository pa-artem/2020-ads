package ru.mail.polis.ads.part2.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem1462 {
    private Problem1462() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int length = in.nextInt();
        int[][] array = new int[10][100];
        int[] count = new int[10];
        for (int i = 0; i < length; i++) {
            int n = in.nextInt();
            int arrayIndex = n % 10;

            int j = count[arrayIndex];
            while (j > 0 && n < array[arrayIndex][j - 1]) {
                array[arrayIndex][j] = array[arrayIndex][j - 1];
                j--;
            }
            array[arrayIndex][j] = n;
            count[arrayIndex]++;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                out.write(array[i][j] + " ");
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
