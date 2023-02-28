package com.ilearning.huaweiod.basic.string;

/**
 * 猜字谜
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 猜字谜
 * 题目设计：
 * 小王设计了一个简单的猜字谜游戏，游戏的谜面是一个错误的单词，比如：nesw，玩家需要猜出谜底库中正确的单词。猜中的要求如下：
 *      对于某个谜面和谜底单词，满足下面任一条件都表示猜中：
 *      1）变换顺序以后一样的，比如通过变换 w 和 e 的顺序，“nwes” 跟 “news” 是可以完全对应的；
 *      2）字母去重以后是一样的，比如 “woood” 和 “wood” 是一样的，他去重后都是 “wod”；
 * 请你写一个程序帮忙再谜底库中找到正确的谜底。谜面是多个单词，都需要找到对应的谜底，如果找不到的话，返回 “not found”。
 *
 * 输入描述：
 * 1. 谜面单词列表，以“，”分隔；
 * 2. 谜底库单词列表，以“，”分隔；
 *
 * 输出提示：
 * 匹配到正确的单词列表，以“，”分隔；
 * 如果找不到，返回 “not found”；
 *
 * 示例1：
 * conection
 * connection,today
 * 输出：
 * connection
 *
 * 示例2：
 * bdni, wooood
 * bind, wrong, wood
 * 输出：
 * bind, wood
 *
 */
public class Charade {
    public static void main(String[] args) {
        Scanner scan =  new Scanner(System.in);
        String[] mm = scan.nextLine().split(",");
        String[] md = scan.nextLine().split(",");

        List<String> result = new ArrayList<>();
        for (int i = 0; i < mm.length; i++) {
            // 谜面
            String m = mm[i];

            // 是否在谜底中
            boolean isFound = false;

            for (int j = 0; j < md.length; j++) {
                String d = md[j];
                if (sort(m, d)){
                    result.add(d);
                    isFound = true;
                } else if (deduplicate(m,d)){
                    result.add(d);
                    isFound = true;
                }
            }

            // 谜面不在谜底中，not found
            if (!isFound){
                result.add("not found");
            }
        }

        String resStr = "";
        for (int i = 0; i < result.size(); i++) {
            resStr += result.get(i) + ",";
        }
        System.out.println(resStr.substring(0, resStr.length() - 1));
    }

    /**
     * 排序后谜面和谜底是否一致
     *
     * @param mm 谜面
     * @param md 谜底
     * @return boolean
     */
    public static boolean sort(String mm, String md){
        String[] mStr = mm.split("");
        Arrays.sort(mStr);

        String[] dStr = md.split("");
        Arrays.sort(dStr);

        if (Arrays.equals(mStr, dStr)){
            return true;
        }

        return false;
    }

    /**
     * 去重后谜面和谜底是否一致
     *
     * @param mm 谜面
     * @param md 谜底
     * @return boolean
     */
    public static boolean deduplicate(String mm, String md){
        // 去重谜面
        List<Character> mList = new ArrayList<>();
        for (int i = 0; i < mm.length(); i++) {
            char c = mm.charAt(i);
            if (!mList.contains(c)){
                mList.add(c);
            }
        }

        // 去重谜底
        List<Character> dList = new ArrayList<>();
        for (int i = 0; i < md.length(); i++) {
            char c = md.charAt(i);
            if (!dList.contains(c)){
                dList.add(c);
            }
        }

        // 谜面和谜底一样
        if (mList.equals(dList)){
            return true;
        }
        return false;
    }

}
