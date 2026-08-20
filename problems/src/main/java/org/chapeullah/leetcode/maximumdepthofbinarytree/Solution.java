package org.chapeullah.leetcode.maximumdepthofbinarytree;

class Solution {
    public int maxDepth(TreeNode root) {
        return recursive(root);
    }

    private int recursive(TreeNode node) {
        if (node == null) return 0;

        System.out.println(node.val);

        int leftDepth = recursive(node.left);
        int rightDepth = recursive(node.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }
}