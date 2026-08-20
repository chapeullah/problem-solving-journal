package org.chapeullah.leetcode.maximumdepthofbinarytree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                3,
                new TreeNode(9),
                new TreeNode(
                        20,
                        new TreeNode(15),
                        new TreeNode(7)));
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(root));
    }
}
