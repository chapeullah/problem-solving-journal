package org.chapeullah.leetcode.reverselinkedlist;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode result = solution.reverseList(head);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
