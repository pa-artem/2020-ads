package ru.mail.polis.ads.part4.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem1087 {
    private Problem1087() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        String bracesString = in.next();
        if (bracesString == null || bracesString.length() == 0) {
            return;
        }

        char[] braces = bracesString.toCharArray();
        final int n = braces.length;
        String[][] d = new String[n][n];

        for (int length = 1; length <= n; length++) {
            int i = 0, j = length - 1;
            while (j < n) {
                if (length == 1) {
                    switch (braces[i]) {
                        case '(':
                        case ')':
                            d[i][j] = "()";
                            break;
                        case '[':
                        case ']':
                            d[i][j] = "[]";
                            break;
                    }
                } else {
                    String min = d[i][i] + d[i + 1][j];
                    if (braces[i] == '(' && braces[j] == ')' || braces[i] == '[' && braces[j] == ']') {
                        String str = braces[i] + (i + 1 <= j - 1 ? d[i + 1][j - 1] : "") + braces[j];
                        if (min.length() > str.length()) {
                            min = str;
                        }
                    }

                    for (int k = 1; k < j - i; k++) {
                        String str = d[i][i + k] + d[i + k + 1][j];
                        if (min.length() > str.length()) {
                            min = str;
                        }
                    }
                    d[i][j] = min;
                }
                i++;
                j++;
            }
        }

        out.write(d[0][n - 1]);
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
