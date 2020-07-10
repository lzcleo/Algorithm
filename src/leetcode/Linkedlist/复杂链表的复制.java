package leetcode.Linkedlist;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.LinkedList;
import java.util.Queue;

/**
 * User: leo
 * Date: 2020/6/28
 * Time: 11:53
 */
public class 复杂链表的复制 {
    public RandomListNode copyRandomList(RandomListNode pHead) {
        if (pHead == null)
            return null;
        // 插入新节点
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.val);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        // 建立 random 链接 cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            //注意判空，小心空指针
            if (cur.random != null)
                clone.random = cur.random.next;
            cur = clone.next;
        }
        // 拆分
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }

    static class RandomListNode {
        int val;
        RandomListNode next;
        RandomListNode random;

        public RandomListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    String Serialize(TreeNode root) {
        if (root == null)
            return null;
        String result = "";
        Queue<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; i ++) {
                TreeNode res = queue.poll();
                if (res == null) {
                    result += "#";
                } else {
                    result += queue.poll().val + "";
                }
                result += "!";
                if (res != null) {
                    queue.offer(res.left);
                    queue.offer(res.right);
                }
            }
        }
        return result;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;

    }
    static String serialize(TreeNode root) {
        if (root == null)
            return null;
        String result = "";
        Queue<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int i = 0; i < num; i ++) {
                TreeNode res = queue.poll();
                if (res == null) {
                    result += "#";
                } else {
                    result += queue.poll().val + "";
                }
                result += "!";
                if (res != null) {
                    queue.offer(res.left);
                    queue.offer(res.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode ll = new TreeNode(4);
        TreeNode root = new TreeNode(1);
        TreeNode l = new TreeNode(2);
        root.left = l;
        l.left = ll;
        TreeNode r = new TreeNode(3);
        TreeNode rl = new TreeNode(5);
        TreeNode rr = new TreeNode(6);
        root.right = r;
        r.left = rl;
        r.right = rr;
        System.out.println(serialize(root));

    }
}

