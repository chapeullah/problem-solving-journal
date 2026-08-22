package org.chapeullah.leetcode.linkedlistcycle;

public class Main {
    public static void main(String[] args) {
        Solution_HashSet s_hs = new Solution_HashSet();
        Solution_TwoPointers s_tp = new Solution_TwoPointers();

        int[] values = {
                -21, 10, 17, 8, 4,
                26, 5, 35, 33, -7,
                -16, 27, -12, 6, 29,
                -12, 5, 9, 20, 14,
                14, 2, 13, -24, 21,
                23, -21, 5
        };

        // [-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5], pos = -1; Output: false
        ListNode head = new ListNode(values[0]);
        ListNode current = head;

        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }

        // [3,2,0,-4], pos = 1; Output: true
        ListNode head1 = new ListNode(3);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(0);
        head1.next.next.next = new ListNode(-4);
        head1.next.next.next.next = head1.next;

        // [1,2], pos = 0; Output: true
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2;

        // [1], pos = -1; Output: false
        ListNode head3 = new ListNode(1);

        System.out.println(s_hs.hasCycle(head1)); // true
        System.out.println(s_hs.hasCycle(head2)); // true
        System.out.println(s_hs.hasCycle(head3)); // false

        System.out.println(s_tp.hasCycle(head)); // false
    }
}