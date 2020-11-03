package ru.mail.polis.ads.part5.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem264 {
    private Problem264() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        int maxLength = 1;
        int[] d = new int[n];
        d[0] = 1;
        for (int i = 1; i < n; i++) {
            int jMax = -1;
            for (int j = 0; j < i; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && (jMax == -1 || d[jMax] < d[j])) {
                    jMax = j;
                }
            }
            if (jMax == -1) {
                d[i] = 1;
            } else {
                d[i] = d[jMax] + 1;
            }
            if (d[i] > maxLength) {
                maxLength = d[i];
            }
        }

        out.write(Integer.toString(maxLength));
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
