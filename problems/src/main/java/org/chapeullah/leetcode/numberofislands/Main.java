package org.chapeullah.leetcode.numberofislands;

public class Main {
    public static void main(String[] args) {
        // Answer: 1
        char[][] grid1 = {
                { '1','1','1','1','0' },
                { '1','1','0','1','0' },
                { '1','1','0','0','0' },
                { '0','0','0','0','0' }
        };
        // Answer: 3
        char[][] grid2 = {
                { '1','1','0','0','0' },
                { '1','1','0','0','0' },
                { '0','0','1','0','0' },
                { '0','0','0','1','1' }
        };
        // Answer: 1
        char[][] grid = {
                { '1','0','1','1','1' },
                { '1','0','1','0','1' },
                { '1','1','1','0','1' }
        };
        Solution s = new Solution();
        System.out.println(s.numIslands(grid1));
        System.out.println(s.numIslands(grid2));
    }
}
