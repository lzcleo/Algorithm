package leetcode.Linkedlist;

/**
 * User: leo
 * Date: 2020/6/22
 * Time: 11:23
 */
public class 回文链表 {
    //以下解法时间复杂度为O(n),空间复杂度为O(1),第一时间能想到的解法应该是把链表存起来，进行操作，但是那样比较冗余
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;

        // 找中点的时候需要扣一扣细节
        ListNode firstHalfEnd = endOfFirstHalf(head);
        //这里有一个细节点，反转链表的时候反转第二部分，而不是第一部分
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        //这个地方写的非常巧妙，因为可能链表的个数为基数，这样写可以巧妙避开第一部分的最后一个
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list and return the result.
        //这里主要是为了不破坏链表结构
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    //太久没有写反转链表了，写的比较冗余
    private ListNode reverse(ListNode head) {
        if (head == null) return null;
        ListNode pre = null;
        ListNode cur = head;
        ListNode curNext = head.next;
        while (curNext != null) {
            cur.next = pre;
            pre = cur;
            cur = curNext;
            curNext = curNext.next;
        }
        cur.next = pre;
        return cur;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
