package leetcode.Linkedlist;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author leolu
 * @create 2020-03-13-9:03
 **/
public class 两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //栈
        LinkedList<Integer> s1 = new LinkedList<>();
        LinkedList<Integer> s2 = new LinkedList<>();
        while (l1 != null) {
            s1.addFirst(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.addFirst(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode lastNode = null;
        while (!s1.isEmpty() || !s2.isEmpty() || !(carry == 0)) {
            int a1 = 0, a2 = 0;
            if (!s1.isEmpty()) {
                a1 = s1.removeFirst();
            }
            if (!s2.isEmpty()) {
                a2 = s2.removeFirst();
            }
            ListNode curNode = new ListNode((a1 + a2 + carry) % 10);
            carry = (a1 + a2 + carry) / 10;
            curNode.next = lastNode;
            lastNode = curNode;
        }
        // if(carry > 0) {
        //     ListNode curNode = new ListNode(carry);
        //     curNode.next = lastNode;
        //     lastNode = curNode;
        // }
        return lastNode;
    }

}

class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //栈
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        while (l1 != null) {
            queue1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            queue2.add(l2.val);
            l2 = l2.next;
        }
        ListNode head = new ListNode(-1);
        ListNode index = head;
        int count = 0;
        while (!queue1.isEmpty() || !queue2.isEmpty() || !(count == 0)) {
            int s1 = 0, s2 = 0;
            if (!queue1.isEmpty())
                s1 = queue1.poll();
            if (!queue2.isEmpty())
                s2 = queue2.poll();
            ListNode cur = new ListNode((s1 + s2 + count) % 10);
            count = (s1 + s2 + count) / 10;
            if (head.next == null)
                head.next = cur;
            cur.next = head.next;
            head.next = cur;
        }
        return head.next;
    }

}
