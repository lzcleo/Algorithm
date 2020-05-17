package leetcode.Linkedlist;

/**
 * @author leolu
 * @create 2020-04-15-23:42
 * 其实有点像一道找规律的题，主要是边界条件的编写，虽然调错调了一段时间，但是用自己的代码提交感觉还是非常nice的
 **/
public class 环形链表II {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode l1 = head.next, l2 = head.next.next;
        while (l1 != l2) {
            if (l2 == null || l2.next == null) {
                return null;
            }
//            if (l1 == null || l2 == null) {
//                return null;
//            } 错误写法
            l1 = l1.next;
            l2 = l2.next.next;
        }
        l2 = head;
        while (l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1;
    }
}
