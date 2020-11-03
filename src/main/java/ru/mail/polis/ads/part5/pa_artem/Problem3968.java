package ru.mail.polis.ads.part5.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem3968 {
    private Problem3968() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        final double C = Double.parseDouble(in.next());
        double lo = 0, hi = C;
        double x;
        while (true) {
            x = (lo + hi) / 2;
            double value = x * x + Math.sqrt(x) - C;
            if (value > 0) {
                hi = x;
            } else {
                lo = x;
            }
            if (Math.abs(value) <= 1e-6) {
                break;
            }
        }
        out.write(Double.toString(x));
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
