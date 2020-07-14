package leetcode.Other;

import java.util.Random;

/**
 * @author leolu
 * @create 2020-07-14-7:40
 **/
public class 链表随机节点 {
    /* 返回链表中一个随机节点的值 */
    int getRandom(ListNode head) {
        Random r = new Random();
        int i = 0, res = 0;
        ListNode p = head;
        // while 循环遍历链表
        while (p != null) {
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (r.nextInt(++i) == 0) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }

    class ListNode{
        private int val;
        private ListNode next;
        public ListNode(int x) {
            val = x;
        }
    }
}
