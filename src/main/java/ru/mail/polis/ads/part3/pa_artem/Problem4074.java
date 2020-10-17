package ru.mail.polis.ads.part3.pa_artem;

import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;

public final class Problem4074 {
    private Problem4074() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        Heap left = new Heap(), right = new Heap(Comparator.comparing(i -> -i));
        int median = -1;
        boolean even = false;

        while (in.hasNext()) {
            int n = in.nextInt();

            if (even) {
                if (n < median) {
                    left.put(n);
                    right.put(median);
                } else {
                    right.put(n);
                    left.put(median);
                }
            } else {
                if (left.count() > 0 && left.peek() > n) {
                    median = left.poll();
                    left.put(n);
                } else if (right.count > 0 && right.peek() < n) {
                    median = right.poll();
                    right.put(n);
                } else {
                    median = n;
                }
            }

            out.write(Integer.toString(even ? (left.peek() + right.peek()) / 2 : median) + '\n');
            even = !even;
        }
    }

    private static class Heap {
        static final int MIN_HEIGHT = 2;

        // 0-th element is unused
        int[] data = new int[1 << MIN_HEIGHT];
        int count;
        Comparator<Integer> comparator;

        // max heap by default
        Heap() {
            this(Integer::compare);
        }

        Heap(Comparator<Integer> comparator) {
            this.comparator = comparator;
        }

        void put(int value) {
            grow(1);
            count++;
            data[count] = value;
            swim(count);
        }

        int poll() {
            swap(data, 1, count);
            int value = data[count];
            count--;
            if (count > 1) {
                sink(1);
            }
            shrink();
            return value;
        }

        int peek() {
            return data[1];
        }

        int count() {
            return count;
        }

        void sink(int i) {
            while (2 * i <= count) {
                int maxChildIndex = 2 * i;
                if (2 * i + 1 <= count && less(2 * i, 2 * i + 1)) {
                    maxChildIndex = 2 * i + 1;
                }
                if (!less(i, maxChildIndex)) {
                    break;
                }
                swap(data, i, maxChildIndex);
                i = maxChildIndex;
            }
        }

        void swim(int i) {
            while (i > 1 && less(i / 2, i)) {
                swap(data, i / 2, i);
                i /= 2;
            }
        }

        void grow(int by) {
            if (count + by + 1 > data.length) {
                int floorLog2 = 31 - Integer.numberOfLeadingZeros(count + by);
                int newSize = 1 << floorLog2 + 1;
                reallocate(newSize);
            }
        }

        void shrink() {
            if (2 * data.length >= (1 << MIN_HEIGHT) && count + 1 <= data.length / 4) {
                int floorLog2 = 31 - Integer.numberOfLeadingZeros(count);
                int newSize = 1 << floorLog2 + 1;
                reallocate(Math.max(1 << MIN_HEIGHT, newSize));
            }
        }

        void reallocate(int newSize) {
            int[] newData = new int[newSize];
            System.arraycopy(data, 0, newData, 0, count + 1);
            data = newData;
        }

        boolean less(int i, int j) {
            return comparator.compare(data[i], data[j]) < 0;
        }

        static void swap(int[] array, int i, int j) {
            int swap = array[i];
            array[i] = array[j];
            array[j] = swap;
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