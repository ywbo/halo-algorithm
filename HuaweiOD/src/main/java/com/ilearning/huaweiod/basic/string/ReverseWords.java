package com.ilearning.huaweiod.basic.string;

/**
 * 单词倒序
 *
 * @author yuwenbo
 * @date 2023/3/3 6:31
 **/

import java.util.Scanner;

/**
 * 题目描述： 输入单行英文句子，里面包含英文字母，空格以及,.? 三种标点符号，请将句子内每个单词进行倒序，并输出倒序后的语句
 *
 * 输入：
 * "I love Java programming."
 *
 * 输出：
 * "I evol avaJ gnimmargorp."
 */
public class ReverseWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入英文句子：");
        String sentence = sc.nextLine();
        String[] words = sentence.split(" "); // 以空格为分隔符，将句子拆分成单词数组
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            String reversed = reverse(word); // 对于每个单词进行翻转
            sb.append(reversed); // 将翻转后的单词添加到结果字符串中
            if (i != words.length - 1) {
                sb.append(" "); // 如果不是最后一个单词，添加一个空格
            }
        }
        System.out.println(sb.toString() + "."); // 输出翻转后的语句
    }

    private static String reverse(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }
}
