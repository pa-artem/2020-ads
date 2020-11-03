package ru.mail.polis.ads.part5.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem991 {
    private Problem991() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        char[] template = in.next().toCharArray(), word = in.next().toCharArray();
        for (int i = 0; i < word.length; i++) {
            if (word[i] == '?' || word[i] == '*') {
                char[] swap = template;
                template = word;
                word = swap;
            }
        }

        // additional left row and top column to handle edge cases
        boolean[][] d = new boolean[word.length + 1][template.length + 1];
        // empty word matches empty pattern
        d[0][0] = true;

        for (int iWord = 1; iWord <= word.length; iWord++) {
            for (int iTemplate = 1; iTemplate <= template.length; iTemplate++) {
                if (template[iTemplate - 1] == word[iWord - 1] || template[iTemplate - 1] == '?') {
                    d[iWord][iTemplate] = d[iWord - 1][iTemplate - 1];
                } else if (template[iTemplate - 1] == '*') {
                    // 1) ignore the asterisk
                    // 2) the asterisk consumes a char from the word
                    // 3) asterisk matches with a single char from the word
                    d[iWord][iTemplate] = d[iWord][iTemplate - 1] || d[iWord - 1][iTemplate] || d[iWord - 1][iTemplate - 1];
                }
            }
        }

        out.write(d[word.length][template.length] ? "YES" : "NO");
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
