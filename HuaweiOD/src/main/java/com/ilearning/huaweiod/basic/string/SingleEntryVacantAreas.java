package com.ilearning.huaweiod.basic.string;

/**
 * 查找单入口空线区域
 *
 * @author yuwenbo
 * @date 2023/3/1 6:35
 */

/**
 * 题目描述:
 * 【查找单入口空闲区域】
 * 给定一个 m x n 的矩阵，由若干字符 ‘X’ 和 ‘O’构成，’X’表示该处已被占据，’O’表示该处空闲，请找到最大的单入口空闲区域。
 *
 * 解释：
 * 空闲区域是由连通的’O’组成的区域，位于边界的’O’可以构成入口，单入口空闲区域即有且只有一个位于边界的’O’作为入口的由连通的’O’组成的区域。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“连通”的。
 *
 * 示例1 输入输出示例仅供调试，后台判题数据一般不包含示例
 *
 * 输入:
 * 4 4
 * X X X X
 * X O O X
 * X O O X
 * X O X X
 *
 * 输出:
 * 3 1 5
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 【思路】：
 * 1.遍历矩阵，找到所有位于边界的’O’，并将它们标记为’-'。
 * 2.再次遍历矩阵，找到所有未被标记的’O’，并将它们标记为’X’。
 * 3.再次遍历矩阵，找到所有被标记为’-‘的’O’，并将它们还原为’O’。
 * 4.再次遍历矩阵，找到最大的单入口空闲区域，并记录其大小和入口位置。
 */
public class SingleEntryVacantAreas {
    public static void main(String[] args) {
        // 创建一个Scanner对象，用于读取输入
        Scanner sc = new Scanner(System.in);
        // 读取矩阵的行数和列数
        int m = sc.nextInt();
        int n = sc.nextInt();
        // 创建一个二维字符数组，用于存储矩阵
        char[][] matrix = new char[m][n];
        // 读取矩阵的每个元素，并存入数组中
        for (int i = 0; i < m; i++) {
            String line = sc.next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        // 关闭Scanner对象
        sc.close();

        // 定义最大空闲区域的大小和入口位置，初始值为0和-1
        int maxArea = 0;
        int entranceRow = -1;
        int entranceCol = -1;

        // 遍历矩阵，将边界上的'O'标记为'-'，并使用广度优先搜索将与之连通的'O'也标记为'-'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'O' && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
//                    mark(matrix, i, j);
                }
            }
        }

// 遍历矩阵，将未被标记的'O'变成'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 'O') {
                    matrix[i][j] = 'X';
                }
            }
        }

// 遍历矩阵，将被标记为'-'的'O'还原，并找到最大的单入口空闲区域，并输出其大小和入口位置
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '-' && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    // 初始化当前空闲区域的大小为1，入口位置为(i,j)
                    int curArea = 1;
                    int curRow = i;
                    int curCol = j;
                    // 使用队列进行广度优先搜索，将与之连通的'O'也计入当前空闲区域，并标记为'*'
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    matrix[i][j] = '*';
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int x = cur[0];
                        int y = cur[1];
                        // 检查上下左右四个方向是否有'O'
                        if (x > 0 && matrix[x - 1][y] == '-') {
                            curArea++;
                            matrix[x - 1][y] = '*';
                            queue.offer(new int[]{x - 1, y});
                        }
                        if (x < m - 1 && matrix[x + 1][y] == '-') {
                            curArea++;
                            matrix[x + 1][y] = '*';
                            queue.offer(new int[]{x + 1, y});
                        }
                        if (y > 0 && matrix[x][y - 1] == '-') {
                            curArea++;
                            matrix[x][y - 1] = '*';
                            queue.offer(new int[]{x, y - 1});
                        }
                        if (y < n - 1 && matrix[x][y + 1] == '-') {
                            curArea++;
                            matrix[x][y + 1] = '*';
                            queue.offer(new int[]{x, y + 1});
                        }
                    }
                    // 如果当前空闲区域的大小大于最大空闲区域的大小
                    if (curArea > maxArea) {
                        // 更新最大空闲区域的大小和入口位置
                        maxArea = curArea;
                        entranceRow = curRow;
                        entranceCol = curCol;
                    }
                }
            }
        }

// 输出最大空闲区域的大小和入口位置
        System.out.println(maxArea);
        System.out.println(entranceRow);
        System.out.println(entranceCol);

    }
}
