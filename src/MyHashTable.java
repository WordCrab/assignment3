import java.util.*;

public class MyHashTable<K, V> {
    private LinkedList<HashNode<K, V>>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new LinkedList[M];
        for (int i = 0; i < M; i++) {
            chainArray[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        int i = hash(key);
        for (HashNode<K, V> x : chainArray[i]) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }
        chainArray[i].addFirst(new HashNode<K, V>(key, value));
        size++;
    }

    public V get(K key) {
        int i = hash(key);
        for (HashNode<K, V> x : chainArray[i]) {
            if (key.equals(x.key)) {
                return x.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int i = hash(key);
        for (HashNode<K, V> x : chainArray[i]) {
            if (key.equals(x.key)) {
                V value = x.value;
                chainArray[i].remove(x);
                size--;
                return value;
            }
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> x : chainArray[i]) {
                if (value.equals(x.value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            for (HashNode<K, V> x : chainArray[i]) {
                if (value.equals(x.value)) {
                    return x.key;
                }
            }
        }
        return null;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            System.out.println("Bucket " + i + ": " + chainArray[i].size() + " elements");
        }
    }

    private class HashNode<K, V> {
        private K key;
        private V value;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
}

