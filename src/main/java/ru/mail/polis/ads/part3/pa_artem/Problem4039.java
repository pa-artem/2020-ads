package ru.mail.polis.ads.part3.pa_artem;

import java.io.*;
import java.util.StringTokenizer;

public final class Problem4039 {
    private Problem4039() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            int op = in.nextInt();
            if (op == 0) {
                heap.put(in.nextInt());
            } else {
                out.write(Integer.toString(heap.poll()) + '\n');
            }
        }
    }

    private static class Heap {
        static final int MIN_HEIGHT = 2;

        // 0-th element is unused
        int[] data = new int[1 << MIN_HEIGHT];
        int count;

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

        void sink(int i) {
            while (2 * i <= count) {
                int maxChildIndex = 2 * i;
                if (2 * i + 1 <= count && data[2 * i + 1] > data[2 * i]) {
                    maxChildIndex = 2 * i + 1;
                }
                if (data[i] >= data[maxChildIndex]) {
                    break;
                }
                swap(data, i, maxChildIndex);
                i = maxChildIndex;
            }
        }

        void swim(int i) {
            while (i > 1 && data[i / 2] < data[i]) {
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

        static void swap(int[] array, int i, int j) {
            int swap = array[i];
            array[i] = array[j];
            array[j] = swap;
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