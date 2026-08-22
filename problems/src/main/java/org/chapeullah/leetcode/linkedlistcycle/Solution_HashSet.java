package org.chapeullah.leetcode.linkedlistcycle;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/linked-list-cycle/
 */

public class Solution_HashSet {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        return recurse(head, set);
    }

    private boolean recurse(ListNode node, Set<ListNode> set) {
        if (node == null) return false;
        if (set.contains(node)) return true;
        set.add(node);
        return recurse(node.next, set);
    }
}
