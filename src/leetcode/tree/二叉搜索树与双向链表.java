package leetcode.tree;

/**
 * @author leolu
 * @create 2020-04-05-13:17
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 **/
public class 二叉搜索树与双向链表 {
    public Node pre;
    public Node head;
    public void process(Node root){
        if(root == null){
            return;
        }
        process(root.left);
        root.left = pre;
        if(pre == null){
            head = root;
        }else{
            pre.right = root;
        }
        pre = root;
        process(root.right);
    }
    public Node treeToDoublyList(Node root) {
        if(root == null){
            return null;
        }
        process(root);
        pre.right = head;
        head.left = pre;
        return head;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
