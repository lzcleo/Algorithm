package leetcode.Linkedlist;

/**
 * @author leolu
 * @create 2020-03-30-10:19
 **/
public class K个一组翻转链表 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end.next != null) {
            for (int i = 0; i < k && end != null; i ++) {
                end = end.next;
            }
            if (end == null) break;
            ListNode nextTemp = end.next;
            ListNode start = pre.next;
            end.next = null;
            pre.next = reverse(start); //这一行代码总是写少了
            start.next = nextTemp;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp ;
        }
        return pre;
    }
}
