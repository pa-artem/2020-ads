package ru.mail.polis.ads.part4.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem1618 {
    private Problem1618() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] s1 = new int[n];
        for (int i = 0; i < n; i++) {
            s1[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] s2 = new int[m];
        for (int i = 0; i < m; i++) {
            s2[i] = in.nextInt();
        }

        int[][] d = new int[n][m];

        boolean found = false;
        for (int i = 0; i < n; i++) {
            if (s1[i] == s2[0]) {
                found = true;
            }
            d[i][0] = found ? 1 : 0;
        }
        found = false;
        for (int i = 0; i < m; i++) {
            if (s1[0] == s2[i]) {
                found = true;
            }
            d[0][i] = found ? 1 : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s1[i] == s2[j]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        out.write(Integer.toString(d[n - 1][m - 1]));
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
