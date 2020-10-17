package ru.mail.polis.ads.part3.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem9016 {
    private Problem9016() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] array = new int[n];
        int q = in.nextInt();
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            int left = 0, right = array.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (x > array[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left < array.length && array[left] == x) {
                out.write("YES\n");
            } else {
                out.write("NO\n");
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