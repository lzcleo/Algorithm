package leetcode.Other;

import java.util.HashMap;
import java.util.List;

/**
 * @author leolu
 * @create 2020-04-03-17:45
 **/
public class UnionFindSet {
    public HashMap<Node, Node> fatherMap;
    public HashMap<Node, Integer> sizeMap;

    public UnionFindSet() {
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    public void makeSets(List<Node> nodes) {
        fatherMap.clear();
        sizeMap.clear();
        for (Node node : nodes) {
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    private Node findHead(Node node) {
        Node father = fatherMap.get(node);
        if (father != node) {
            father = findHead(father);
        }
        fatherMap.put(node, father);
        return father;
    }

    public boolean isSameSet(Node a, Node b) {
        return findHead(a) == findHead(b);
    }

    public void union(Node a, Node b) {
        if (a == null || b == null) {
            return;
        }
        Node aHead = findHead(a);
        Node bHead = findHead(b);
        if (aHead != bHead) {
            int aSetSize= sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            if (aSetSize <= bSetSize) {
                fatherMap.put(aHead, bHead);
                sizeMap.put(bHead, aSetSize + bSetSize);
            } else {
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, aSetSize + bSetSize);
            }
        }
    }
    class Node{
        //根据需要定义节点
    }
}
