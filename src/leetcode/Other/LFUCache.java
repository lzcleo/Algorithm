package leetcode.Other;

import java.util.*;

//需要借助LinkedHashSet，因为迭代的时候会按照加入的顺序删除
public class LFUCache {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        // 返回 1
        System.out.println(cache.get(1));
        cache.put(3, 3);    // 去除 key 2
        // 返回 -1 (未找到key 2)
        System.out.println(cache.get(2));
        // 返回 3
        System.out.println(cache.get(3));
        cache.put(4, 4);    // 去除 key 1
        // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(1));
        // 返回 3
        System.out.println(cache.get(3));
        // 返回 4
        System.out.println(cache.get(4));
    }

    //缓存 cache
    private Map<Integer,Node> cache;
    //存储频次和对应双向链表关系的map
    private Map<Integer, LinkedHashSet<Node>> freqMap;
    private int capacity;
    private int size;
    //存储最小频次值
    private int min;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) return -1;
        //若找到当前元素，则频次加1
        freqIncrease(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        Node node = cache.get(key);
        if(node != null){
            node.value = value;
            freqIncrease(node);
        }else{
            if(size == capacity){
                Node deadNode = removeNode();
                cache.remove(deadNode.key);
                size --;
            }
            Node newNode = new Node(key,value);
            cache.put(key,newNode);
            addNode(newNode);
            size++;
        }
    }

    //处理频次map
    private void freqIncrease(Node node){
        //从原来的频次对应的链表中删除当前node
        LinkedHashSet<Node> set = freqMap.get(node.freq);
        if(set != null)
            set.remove(node);
        //如果当前频次是最小频次，并且移除元素后，链表为空，则更新min值
        if(node.freq == min && set.size() == 0){
            min = node.freq + 1;
        }
        //添加到新的频次对应的链表
        node.freq ++;
        LinkedHashSet<Node> newSet = freqMap.get(node.freq);
        //如果高频次链表还未存在，则初始化一条
        if(newSet == null){
            newSet = new LinkedHashSet<Node>();
            freqMap.put(node.freq,newSet);
        }
        newSet.add(node);
    }

    //添加元素，更新频次
    private void addNode(Node node){
        //添加新元素，肯定是需要加入到频次为1的链表中的
        LinkedHashSet<Node> set = freqMap.get(1);
        if(set == null){
            set = new LinkedHashSet<>();
            freqMap.put(1,set);
        }
        set.add(node);
        //更新最小频次为1
        min = 1;
    }

    //删除频次最小，最久未访问的元素
    private Node removeNode(){
        //找到最小频次对应的 LinkedHashSet
        LinkedHashSet<Node> set = freqMap.get(min);
        //迭代到的第一个元素就是最久未访问的元素，移除之
        Node node = set.iterator().next();
        set.remove(node);
        //如果当前node的频次等于最小频次，并且移除元素之后，set为空，则 min 加1
        if(node.freq == min && set.size() == 0){
            min ++;
        }
        return node;
    }

    private class Node {
        int key;
        int value;
        int freq = 1;

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }

        public Node(){

        }
    }
}