package leetcode.Other;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 * @author leolu
 * @create 2020-03-11-23:31
 **/   
public class LRUCache<K, V> implements Serializable {
    private int size;
    private HashMap<K, Node> map;
    private Node head;
    private Node tail;

    LRUCache(int size) {
        this.size = size;
        map = new HashMap<>();
    }

    /**
     * 添加元素
     * 1.元素存在，将元素移动到队尾
     * 2.不存在，判断链表是否满。
     * 如果满，则删除队首元素，放入队尾元素，删除更新哈希表
     * 如果不满，放入队尾元素，更新哈希表
     */
    public void put(K key, V value) {
        Node node = map.get(key);
        if (node != null) {
            //更新值
            node.v = value;
            moveNodeToTail(node);
        } else {
            Node newNode = new Node(key, value);
            //链表满，需要删除首节点
            if (map.size() == size) {
                Node delHead = removeHead();
                map.remove(delHead.k);
            }
            addLast(newNode);
            map.put(key, newNode);
        }
    }

    public V get(K key) {
        Node node = map.get(key);
        if (node != null) {
            moveNodeToTail(node);
            return node.v;
        }
        return null;
    }

    public void addLast(Node newNode) {
        if (newNode == null) {
            return;
        }
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            //连接新节点
            tail.next = newNode;
            newNode.pre = tail;
            //更新尾节点指针为新节点
            tail = newNode;
        }
    }

    public void moveNodeToTail(Node node) {
        if (tail == node) {
            return;
        }
        if (head == node) {
            head = node.next;
            head.pre = null;
        } else {
            //调整双向链表指针
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        node.pre = tail;
        node.next = null;
        tail.next = node;
        tail = node;
    }

    public Node removeHead() {
        if (head == null) {
            return null;
        }
        Node res = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = res.next;
            head.pre = null;
            res.next = null;
        }
        return res;
    }

    class Node {
        K k;
        V v;
        Node pre;
        Node next;

        Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
}

class LRU {
    /**
     * 为什么我们需要在链表中同时存储key和val
     * 当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映 射到该节点的 key 同时删除，而这个 key 只能由 Node 得到。如果 Node 结 构中只存储 val，那么我们就无法得知 key 是什么，就无法删除 map 中的 键，造成错误
     */
    class Node{
        Integer k;
        Integer v;
        Node(Integer k, Integer v) {
            this.k = k;
            this.v = v;
        }
    }

    private int size;
    private LinkedList<Node> list;
    private HashMap<Integer, Node> map;

    public LRU(int size) {
        this.size = size;
        list = new LinkedList<>();
        map = new HashMap<>();
    }

    private void put(int k, int v) {
        if (map.containsKey(k)) {
            Node node = map.get(k);
            node.v = v;
            list.remove(node);
            list.offerLast(node);
            map.put(k, node);
        } else {
            if (list.size() < size) {
                Node node = new Node(k, v);
                list.offerLast(node);
                map.put(k, node);
            } else {
                Node node1 = list.peekFirst();
                map.remove(node1.k, map.get(node1.k));
                list.removeFirst();
                Node node = new Node(k, v);
                list.offerLast(node);
                map.put(k, node);
            }
        }
    }

    private int get(int k) {
        if (!map.containsKey(k))
            return  -1;
        Node node = map.get(k);
        list.remove(node);
        list.offerLast(node);
        return node.v;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(2);
        lru.put(1, 1);
        lru.put(2, 3);
        lru.put(3, 5);
        System.out.println(lru.get(2));
    }
}

class LruCach extends LinkedHashMap<Integer, Integer> {
    private int capacity;
    public LruCach(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}
