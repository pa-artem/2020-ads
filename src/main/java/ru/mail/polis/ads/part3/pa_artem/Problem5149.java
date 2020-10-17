package ru.mail.polis.ads.part3.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem5149 {
    private Problem5149() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] array = new int[n];
        int k = in.nextInt();
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        // left - min possible min distance, right - boundary on max possible min distance
        int left = 1, right = array[n - 1] - array[0] + 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (canArrange(array, mid, k)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        out.write(Integer.toString(left - 1));
    }

    private static boolean canArrange(int[] array, int minDistance, int k) {
        int last = array[0];
        k--;
        for (int i = 1; i < array.length && k > 0; i++) {
            if (array[i] - last >= minDistance) {
                last = array[i];
                k--;
            }
        }
        return k == 0;
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;
        private String prefetchedLine;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            prefetchedLine = reader.readLine();
        }

        String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                if (prefetchedLine == null) {
                    return null;
                }

                String currentLine = prefetchedLine;
                prefetchedLine = reader.readLine();
                tokenizer = new StringTokenizer(currentLine);
            }

            return tokenizer.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        boolean hasNext() {
            return tokenizer != null && tokenizer.hasMoreTokens() || prefetchedLine != null;
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