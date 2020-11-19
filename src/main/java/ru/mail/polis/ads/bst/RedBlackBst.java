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
        if (root == null) {
            return null;
        }
        Value value = get(key);
        if (value == null) {
            return null;
        }
        root = remove(root, key);
        if (root != null) {
            root.color = BLACK;
        }
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

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        // assuming y is always red
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        int compare = key.compareTo(x.key);
        if (compare < 0) {
            x.left = put(x.left, key, value);
        } else if (compare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    // In case there is a 3-node on the right, borrows a value from there to make
    // the left child a 3-node. If there is none, just makes a 3-node by flipping colors.
    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = removeMin(x.left);
        return fixUp(x);
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare < 0 && x.left != null) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = remove(x.left, key);
        } else {
            if (isRed(x.left)) {
                // make sure there is an outgoing red link
                x = rotateRight(x);
                x = remove(x.right, key);
            }
            if (compare > 0) {
                if (!isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                x.right = remove(x.right, key);
            } else {
                if (x.right == null) {
                    // at the bottom of the tree inside a 3-node with the target key
                    return null;
                }
                // intermediate node case
                Node min = min(x.right);
                x.key = min.key;
                x.value = min.value;
                x.right = removeMin(x.right);
            }
        }
        return fixUp(x);
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
