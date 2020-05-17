package leetcode.Linkedlist;

/**
 * @author leolu
 * @create 2020-03-18-15:48
 * 这道题很容易会想到两种思路：
 * 1 暴力枚举 分别遍历两个链表 找到第一个相同的
 * 2 使用两个栈 分别把两个链表装入栈 然后读出最后一个相同的节点
 * 以下这种方式比较巧妙
 * 同时代码也相当简洁
 * 遗憾的是对链表的相关代码经常在next上出错
 **/
public class 两个链表的第一个公共结点 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
//            l1 = l1.next == null ? headB : l1.next;
//            l2 = l2.next == null ? headA : l2.next;
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

}
