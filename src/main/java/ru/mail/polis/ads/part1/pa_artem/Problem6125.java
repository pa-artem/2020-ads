package ru.mail.polis.ads.part1.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem6125 {
    private Problem6125() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        Queue queue = new Queue(120);

        while (true) {
            String input = in.next();
            if (input.equals("exit")) {
                out.write("bye\n");
                break;
            }
            switch (input) {
                case "push":
                    int n = in.nextInt();
                    queue.push(n);
                    out.write("ok\n");
                    break;
                case "pop":
                    if (queue.size() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(queue.pop() + "\n");
                    }
                    break;
                case "front":
                    if (queue.size() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(queue.front() + "\n");
                    }
                    break;
                case "clear":
                    queue.clear();
                    out.write("ok\n");
                    break;
                case "size":
                    out.write(queue.size() + "\n");
                    break;
            }
        }
    }

    public static class Queue {
        // data[hi] is always unused
        private int[] data;
        private int lo, hi;

        public Queue(int capacity) {
            data = new int[capacity + 1];
            lo = 0;
            hi = 0;
        }

        public void push(int n) {
            data[hi] = n;
            hi = inc(hi);
        }

        public int pop() {
            int value = data[lo];
            lo = inc(lo);
            return value;
        }

        public int front() {
            return data[lo];
        }

        public void clear() {
            lo = 0;
            hi = 0;
        }

        public int size() {
            if (lo == hi) {
                return 0;
            }
            return hi > lo ? hi - lo : hi + data.length - lo;
        }

        private int inc(int i) {
            return (i + 1) % data.length;
        }
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
