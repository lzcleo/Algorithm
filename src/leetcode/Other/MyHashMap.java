package leetcode.Other;

/**
 * @author leolu
 * @create 2020-03-25-17:55
 **/
public class MyHashMap<K, V> implements MyMap<K, V> {
    Node<K, V>[] nodes = null;
    private static int defaultLength = 16;
    private static double defaultFactor = 0.75F;
    private int size;

    @Override
    public V put(K key, V value) {
        if (nodes == null) {
            nodes = new Node[defaultLength];
        }
        int index = position(key, defaultLength);
        if (defaultLength * defaultFactor < size) {
            resize();
        }
        Node<K, V> node = nodes[index];
        if (node == null) {
            nodes[index] = new Node<K, V>(key, value, null);
            size ++;
        } else {
            if (key.equals(node.getKey()) || key == node.getKey()) {
                return node.setValue(value);

            } else {
                nodes[index] = new Node<K, V>(key, value, node);
                size ++;
            }

        }
        return null;
    }

    private void resize() {

        Node<K, V>[] newnodes = new Node[defaultLength << 1];
        Node<K, V> node= null;
        for (int i = 0; i < nodes.length; i ++) {
            node = nodes[i];
            while (node != null) {
                int index = position(node.getKey(), newnodes.length);
                Node<K, V> nextNode = node.next;
                node.next = newnodes[index];
                newnodes[index] = node;
                node = nextNode;
            }
        }
        nodes = newnodes;
        defaultLength = defaultLength << 1;
        newnodes = null;

    }

    @Override
    public V get(K key) {
        if (nodes != null) {
            int index = position(key, defaultLength);
            Node<K, V> node = nodes[index];
            while (node != null) {
                if (node.getKey() == key) {
                    return node.getValue();
                } else {
                    node = node.next;
                }
            }
        }
        return null;

    }

    private int position(K keY, int length) {
        return keY.hashCode() & (length - 1);
    }

    public int size(){
        return size;
    }

    class Node<K, V> implements Entry<K,V>{
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V v) {

            this.value = v;
            return  this.value;
        }
    }
}

interface MyMap<K, V>{
    V put(K key, V value);

    V get(K key);

    interface Entry<K, V>{
        K getKey();

        V getValue();

        V setValue(V v);
    }
}
