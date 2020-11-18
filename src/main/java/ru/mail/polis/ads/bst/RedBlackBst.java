package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private Node root;
    private int size;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            int compare = key.compareTo(currentNode.key);
            if (compare < 0) {
                currentNode = currentNode.left;
            } else if (compare > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) {
            return null;
        }
        root = remove(root, key);
        root.color = BLACK;
        size--;
        return value;
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        return floorNode == null ? null : floorNode.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        return ceilNode == null ? null : ceilNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    private Node put(Node x, Key key, Value value) {
        return null;
    }

    private Node remove(Node x, Key key) {
        return null;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare == 0) {
            return x;
        }
        if (compare > 0) {
            return ceil(x.right, key);
        }
        Node leftCeil = ceil(x.left, key);
        return leftCeil == null ? x : leftCeil;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare == 0) {
            return x;
        }
        if (compare < 0) {
            return floor(x.left, key);
        }
        Node rightFloor = floor(x.right, key);
        return rightFloor == null ? x : rightFloor;
    }
}
