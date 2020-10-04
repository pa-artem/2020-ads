package ru.mail.polis.ads.part2.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem4741 {
    private Problem4741() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int length = in.nextInt();
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = in.nextInt();
        }

        int swapCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            // in bubble sort each element is swapped with
            // all the smaller elements on its right
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swapCount++;
                }
            }
        }

        out.write(swapCount + "");
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
