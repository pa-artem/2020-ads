package ru.mail.polis.ads.part5.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem3969 {
    private Problem3969() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        long w = in.nextInt(), h = in.nextInt(), n = in.nextInt();

        long lo = Math.max(w, h), hi = n * Math.max(w, h);
        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if ((mid / w) * (mid / h) >= n) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        out.write(Long.toString(lo));
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
