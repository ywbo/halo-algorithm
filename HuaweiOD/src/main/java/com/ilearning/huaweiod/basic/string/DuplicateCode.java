package com.ilearning.huaweiod.basic.string;

/**
 * 检查重复代码
 *
 * @author yuwenbo
 * @date 2023/3/1 6:16
 **/

/**
 * 小明负责维护项目下的代码，需要查找出重复代码，用以支撑后续的代码优化，请你帮助小明找出重复的代码，。
 * 重复代码查找方法：以字符串形式给定两行代码（字符串长度 1 < length <= 100，由英文字母、数字和空格组成），找出两行代码中的最长公共子串。
 * 注： 如果不存在公共子串，返回空字符串
 */
public class DuplicateCode {
    // 定义一个方法，输入两个字符串，返回最长公共子串
    public static String longestCommonSubstring(String s1, String s2) {
        // 如果有一个字符串为空，直接返回空字符串
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        // 定义一个二维数组，dp[i][j]表示以s1[i-1]和s2[j-1]结尾的最长公共子串的长度
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // 定义一个变量，记录最长公共子串的长度
        int maxLen = 0;
        // 定义一个变量，记录最长公共子串的结束位置
        int end = -1;
        // 遍历两个字符串，更新dp数组和maxLen和end
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                // 如果当前字符相同，那么dp[i][j]等于左上角的值加一
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // 如果当前值大于maxLen，更新maxLen和end
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        end = i - 1;
                    }
                } else {
                    // 如果当前字符不同，那么dp[i][j]等于零
                    dp[i][j] = 0;
                }
            }
        }
        // 如果maxLen为零，说明没有公共子串，返回空字符串
        if (maxLen == 0) {
            return "";
        } else {
            // 否则根据end和maxLen截取s1中的最长公共子串并返回
            return s1.substring(end - maxLen + 1, end + 1);
        }
    }

    public static void main(String[] args) {
        // 测试用例
        String s11 = "public class Solution {";
        String s12 = "public class Test {";

        String s21 = "int a = b + c;";
        String s22 = "int d = e + f;";

        String s31 = "System.out.println(\"Hello World!\");";
        String s32 = "System.out.println(\"Hello Java!\");";


        System.out.println(longestCommonSubstring(s11, s12)); // 输出 public class
        System.out.println(longestCommonSubstring(s21, s22)); // 输出 int
        System.out.println(longestCommonSubstring(s31, s32)); // 输出 System.out.println("
    }
}
