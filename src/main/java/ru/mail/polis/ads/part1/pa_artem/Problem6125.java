package ru.mail.polis.ads.part1.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public final class Problem6125 {
    private Problem6125() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        Queue queue = new Queue();

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
        private static final int MIN_CAPACITY = 8;

        // data[hi] is always unused
        private int[] data;
        private int lo, hi;

        public Queue() {
            data = new int[MIN_CAPACITY];
            lo = 0;
            hi = 0;
        }

        public void push(int n) {
            grow(1);
            data[hi] = n;
            hi = inc(hi);
        }

        public int pop() {
            int value = data[lo];
            lo = inc(lo);
            shrink();
            return value;
        }

        public int front() {
            return data[lo];
        }

        public void clear() {
            lo = 0;
            hi = 0;
            shrink();
        }

        public int size() {
            if (lo == hi) {
                return 0;
            }
            return hi > lo ? hi - lo : hi + data.length - lo;
        }

        private void grow(int by) {
            int size = size();
            if (size + by + 1 > data.length) {
                reallocate(max(size + by + 1, max(MIN_CAPACITY, 3 * data.length / 2)));
            }
        }

        private void shrink() {
            int size = size();
            if (data.length > MIN_CAPACITY && size <= data.length / 4) {
                reallocate(max(MIN_CAPACITY, 2 * size));
            }
        }

        private void reallocate(int newSize) {
            int[] newData = new int[newSize];
            if (hi != lo) {
                if (hi > lo) {
                    System.arraycopy(data, lo, newData, 0, hi - lo);
                } else {
                    System.arraycopy(data, lo, newData, 0, data.length - lo);
                    System.arraycopy(data, 0, newData, data.length - lo, hi);
                }
            }
            hi = size();
            lo = 0;
            data = newData;
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
