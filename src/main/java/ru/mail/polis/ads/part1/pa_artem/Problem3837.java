package ru.mail.polis.ads.part1.pa_artem;

import java.io.*;
import java.util.*;

public final class Problem3837 {
    private Problem3837() {
        // Should not be instantiated
    }

    private static class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
        }

        Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // The task boils down to converting postorder traversal into level-by-level
    // from bottom to top from right to left tree traversal.
    private static void solve(final FastScanner in, final BufferedWriter out) throws IOException {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String expr = in.next();
            StringBuilder sb = new StringBuilder();

            Node root = buildTree(expr);
            Queue<Node> queue = new ArrayDeque<>();
            // breadth first search
            queue.add(root);
            while (queue.size() != 0) {
                Node current = queue.poll();
                sb.append(current.value);
                if (current.right != null) {
                    queue.offer(current.right);
                    queue.offer(current.left);
                }
            }

            out.write(sb.reverse().toString() + "\n");
        }
    }

    private static Node buildTree(String expr) {
        Deque<Node> stack = new ArrayDeque<>();

        for (int j = 0; j < expr.length(); j++) {
            char literal = expr.charAt(j);
            if (literal >= 'a' && literal <= 'z') {
                stack.push(new Node(literal));
            } else {
                Node operand1 = stack.pop();
                Node operand2 = stack.pop();
                stack.push(new Node(literal, operand1, operand2));
            }
        }
        return stack.pop();
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
