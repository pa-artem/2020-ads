package ru.mail.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBst<Key extends Comparable<Key>, Value>
        implements Bst<Key, Value> {
    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
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
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return value;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        return floorNode == null ? null : floorNode.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        return ceilNode == null ? null : ceilNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
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

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        Node leftCeil = ceil(x.left, key);
        return leftCeil == null ? x : leftCeil;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        Node rightFloor = floor(x.right, key);
        return rightFloor == null ? x : rightFloor;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        return balance(x);
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            x = innerRemove(x);
        }
        if (x == null) {
            return null;
        }
        fixHeight(x);
        return balance(x);
    }

    private Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node swap = x;
        x = min(swap.right);
        x.right = removeMin(swap.right);
        x.left = swap.left;
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }

    private Node balance(Node root) {
        if (factor(root) == 2) {
            // left-right case
            if (factor(root.left) < 0) {
                root.left = rotateLeft(root.left);
            }
            return rotateRight(root);
        }
        if (factor(root) == -2) {
            // right-left case
            if (factor(root.right) > 0) {
                root.right = rotateRight(root.right);
            }
            return rotateLeft(root);
        }
        return root;
    }

    private Node rotateLeft(Node root) {
        Node pivot = root.right;
        root.right = pivot.left;
        pivot.left = root;
        fixHeight(root);
        fixHeight(pivot);
        return pivot;
    }

    private Node rotateRight(Node root) {
        Node pivot = root.left;
        root.left = pivot.right;
        pivot.right = root;
        fixHeight(root);
        fixHeight(pivot);
        return pivot;
    }

    private int factor(Node x) {
        return height(x.left) - height(x.right);
    }
}
