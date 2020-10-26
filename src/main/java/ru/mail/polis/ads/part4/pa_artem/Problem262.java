package ru.mail.polis.ads.part4.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem262 {
    private Problem262() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] steps = new int[n + 2];
        for (int i = 0; i < n; i++) {
            steps[i + 1] = in.nextInt();
        }
        int k = in.nextInt();

        int[] d = new int[n + 2];
        for (int i = 1; i < n + 2; i++) {
            int max = d[i - 1];
            for (int j = i - 2; j >= 0 && i - j <= k; j--) {
                if (max < d[j]) {
                    max = d[j];
                }
            }
            d[i] = steps[i] + max;
        }

        out.write(Integer.toString(d[n + 1]));
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
