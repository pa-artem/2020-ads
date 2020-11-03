package ru.mail.polis.ads.part5.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem2169 {
    private Problem2169() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        while (true) {
            for (int i = 0; i < n; i++) {
                out.write(a[i] + " ");
            }
            out.write("\n");

            int iTail = n - 1;
            while (iTail > 0 && a[iTail - 1] > a[iTail]) {
                iTail--;
            }
            if (iTail == 0) {
                break;
            }

            int iNext = -1;
            for (int i = iTail; i < n; i++) {
                if (a[i] > a[iTail - 1] && (iNext == -1 || a[iNext] > a[i])) {
                    iNext = i;
                }
            }
            int swap = a[iNext];
            a[iNext] = a[iTail - 1];
            a[iTail - 1] = swap;

            for (int i = 0; i < (n - iTail) / 2; i++) {
                swap = a[iTail + i];
                a[iTail + i] = a[n - i - 1];
                a[n - i - 1] = swap;
            }
        }
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
