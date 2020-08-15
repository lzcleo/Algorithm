package leetcode.Linkedlist;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author leolu
 * @create 2020-03-18-12:27
 * 这道题在处理的时候需要想到归并排序，能够降低合并的次数
 * 同时在调主函数的出现了一定的失误，lists.length - 1
 * 这道题可以说是分治思想的体现
 **/

public class 合并K个有序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) queue.add(p.next);
        }
        return dummy.next;
    }

    //考虑递归的过程，第一轮合并(K/2)组，每组有两个链表，所以每一组内的时间复杂度为O(2n),第二轮合并(K/4)，每组时间复杂度为O(4n)，所以总的时间复杂度度为O(kn*logk)，空间复杂度即为递归高度logk
    public ListNode mergeKListsSecond(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        int mid = (left + right) / 2;
        ListNode lnode = merge(lists, left, mid);
        ListNode rnode = merge(lists, mid + 1, right);
        return mergeTwoLists(lnode, rnode);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return pre.next;
    }

}
