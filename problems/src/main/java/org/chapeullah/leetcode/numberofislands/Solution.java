package org.chapeullah.leetcode.numberofislands;

/*
https://leetcode.com/problems/number-of-islands
 */

class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int numberOfIslands = 0;
        int[][] gridCopy = new int[n][m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '1') {
                    recursive(grid, i, j);
                    numberOfIslands++;
                }
            }
        }

        return numberOfIslands;
    }

    private void recursive(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') return;

        grid[i][j] = '0';

        if (i + 1 < grid.length) recursive(grid, i + 1, j);
        if (i - 1 >= 0) recursive(grid, i - 1, j);
        if (j + 1 < grid[0].length) recursive(grid, i, j + 1);
        if (j - 1 >= 0) recursive(grid, i, j - 1);
    }
}