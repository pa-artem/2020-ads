package ru.mail.polis.ads.part4.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem15 {
    private Problem15() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int m = in.nextInt(), n = in.nextInt();
        int[][] a = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        String[][] dPaths = new String[m][n];
        int[][] dCounts = new int[m][n];

        dPaths[m - 1][0] = "";
        dCounts[m - 1][0] = a[m - 1][0];

        for (int row = m - 2; row >= 0; row--) {
            int i = row, j = 0;
            while (i < m && j < n) {
                if (i + 1 < m && j - 1 >= 0 && dCounts[i + 1][j] > dCounts[i][j - 1] || i + 1 < m && j - 1 < 0) {
                    dPaths[i][j] = dPaths[i + 1][j] + 'F';
                    dCounts[i][j] = a[i][j] + dCounts[i + 1][j];
                } else {
                    dPaths[i][j] = dPaths[i][j - 1] + 'R';
                    dCounts[i][j] = a[i][j] + dCounts[i][j - 1];
                }
                i++;
                j++;
            }
        }
        for (int column = 1; column < n; column++) {
            int i = 0, j = column;
            while (i < m && j < n) {
                if (i + 1 < m && j - 1 >= 0 && dCounts[i + 1][j] > dCounts[i][j - 1] || i + 1 < m && j - 1 < 0) {
                    dPaths[i][j] = dPaths[i + 1][j] + 'F';
                    dCounts[i][j] = a[i][j] + dCounts[i + 1][j];
                } else {
                    dPaths[i][j] = dPaths[i][j - 1] + 'R';
                    dCounts[i][j] = a[i][j] + dCounts[i][j - 1];
                }
                i++;
                j++;
            }
        }

        out.write(dPaths[0][n - 1]);
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
