package ru.mail.polis.ads.part1.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public final class Problem6124 {
    private Problem6124() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        Stack stack = new Stack();

        while (true) {
            String input = in.next();
            if (input.equals("exit")) {
                out.write("bye\n");
                break;
            }
            switch (input) {
                case "push":
                    int n = in.nextInt();
                    stack.push(n);
                    out.write("ok\n");
                    break;
                case "pop":
                    if (stack.size() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(stack.pop() + "\n");
                    }
                    break;
                case "back":
                    if (stack.size() == 0) {
                        out.write("error\n");
                    } else {
                        out.write(stack.back() + "\n");
                    }
                    break;
                case "clear":
                    stack.clear();
                    out.write("ok\n");
                    break;
                case "size":
                    out.write(stack.size() + "\n");
                    break;
            }
        }
    }

    public static class Stack {
        private static final int MIN_CAPACITY = 8;

        private int[] data;
        private int size;

        public Stack() {
            data = new int[MIN_CAPACITY];
            size = 0;
        }

        public void push(int n) {
            grow(1);
            data[size++] = n;
        }

        public int pop() {
            int value = data[--size];
            shrink();
            return value;
        }

        public int back() {
            return data[size - 1];
        }

        public void clear() {
            size = 0;
            shrink();
        }

        public int size() {
            return size;
        }

        private void grow(int by) {
            if (size + by > data.length) {
                reallocate(max(size + by, max(MIN_CAPACITY, 3 * data.length / 2)));
            }
        }

        private void shrink() {
            if (data.length > MIN_CAPACITY && size <= data.length / 4) {
                reallocate(max(MIN_CAPACITY, 2 * size));
            }
        }

        private void reallocate(int newSize) {
            int[] newData = new int[newSize];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
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
