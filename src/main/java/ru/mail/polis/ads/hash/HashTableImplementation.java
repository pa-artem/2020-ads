package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableImplementation<Key, Value> implements HashTable<Key, Value> {
    private static final double MAX_LOAD_FACTOR = 0.75;
    private static final int MIN_CAPACITY = 15;

    // capacity = 2^log2Capacity - 1
    private int log2Capacity;
    private int capacity;
    private int size;
    private Node<Key, Value>[] buckets;

    @SuppressWarnings("unchecked")
    public HashTableImplementation(int initialCapacity) {
        if (initialCapacity <= MIN_CAPACITY) {
            log2Capacity = ceilLog2(MIN_CAPACITY);
            capacity = MIN_CAPACITY;
        } else {
            log2Capacity = ceilLog2(initialCapacity);
            capacity = (1 << log2Capacity) - 1;
        }
        buckets = new Node[capacity];
    }

    public HashTableImplementation() {
        this(15);
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        Node<Key, Value> currentNode = buckets[bucketIndex(key.hashCode())];
        while (currentNode != null && !currentNode.key.equals(key)) {
            currentNode = currentNode.next;
        }
        return currentNode == null ? null : currentNode.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        int bucketIndex = bucketIndex(key.hashCode());
        Node<Key, Value> currentNode = buckets[bucketIndex];
        if (currentNode == null) {
            buckets[bucketIndex] = new Node<>(key, value);
            size++;
        } else {
            while (true) {
                if (currentNode.key.equals(key)) {
                    currentNode.value = value;
                    break;
                }
                if (currentNode.next == null) {
                    currentNode.next = new Node<>(key, value);
                    size++;
                    break;
                }
                currentNode = currentNode.next;
            }
        }
        if ((double) size / capacity > MAX_LOAD_FACTOR) {
            rehash();
        }
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        int bucketIndex = bucketIndex(key.hashCode());
        Node<Key, Value> parentNode = null, currentNode = buckets[bucketIndex];
        if (currentNode == null) {
            return null;
        }
        while (true) {
            if (currentNode.key.equals(key)) {
                Value value = currentNode.value;
                if (parentNode == null) {
                    buckets[bucketIndex] = currentNode.next;
                } else {
                    parentNode.next = currentNode.next;
                }
                size--;
                return value;
            }
            if (currentNode.next == null) {
                return null;
            }
            parentNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Node<Key, Value>[] oldBuckets = buckets;
        size = 0;
        log2Capacity++;
        capacity = (1 << log2Capacity) - 1;
        buckets = new Node[capacity];
        for (Node<Key, Value> node : oldBuckets) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int bucketIndex(int hashCode) {
        return (hashCode & 0x7fffffff) % capacity;
    }

    private static class Node<Key, Value> {
        Key key;
        Value value;
        Node<Key, Value> next;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private static int ceilLog2(int n) {
        if (n <= 0) {
            return 0;
        }
        int trailingZerosCount = Integer.numberOfTrailingZeros(n);
        if (n == 1 << trailingZerosCount) {
            return trailingZerosCount;
        }
        return Integer.SIZE - Integer.numberOfLeadingZeros(n);
    }
}
