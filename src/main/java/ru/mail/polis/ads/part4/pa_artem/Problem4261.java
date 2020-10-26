package ru.mail.polis.ads.part4.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem4261 {
    private Problem4261() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        out.write(Integer.toString(sort(a, new int[n], 0, n)));
    }

    private static int sort(int[] a, int[] buff, int lo, int hi) {
        if (hi - lo <= 1) {
            return 0;
        }

        int mid = (lo + hi) / 2;
        int count = 0;
        count += sort(a, buff, lo, mid);
        count += sort(a, buff, mid, hi);
        count += merge(a, buff, lo, mid, hi);
        return count;
    }

    private static int merge(int[] a, int[] buff, int lo, int mid, int hi) {
        System.arraycopy(a, lo, buff, lo, hi - lo);

        int count = 0;
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i >= mid) {
                a[k] = buff[j++];
            } else if (j >= hi) {
                a[k] = buff[i++];
            } else if (buff[i] <= buff[j]) {
                a[k] = buff[i++];
            } else {
                a[k] = buff[j++];
                count += mid - i;
            }
        }
        return count;
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
