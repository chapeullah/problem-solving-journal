package org.chapeullah.leetcode.linkedlistcycle;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        return recurse(head, set);
    }

    private void recurse(ListNode node, Set<ListNode> set) {
        if (node == null) {
            return;
        }
        if (set.contains(node)) {
            return;
        }

        System.out.println(node.val);

        set.add(node);
        recurse(node.next, set);
    }
}
