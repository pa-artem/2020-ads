package ru.mail.polis.ads.part1.pa_artem;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public final class Problem5327 {
    private Problem5327() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        char[] expr = in.next().toCharArray();
        out.write(isCorrectExpression(expr) ? "YES" : "NO");
    }

    private static boolean isCorrectExpression(char[] expr) {
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : expr) {
            if (c == '(') {
                stack.push('(');
            } else if (stack.isEmpty() || stack.pop() != '(') {
                return false;
            }
        }

        return stack.isEmpty();
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
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
