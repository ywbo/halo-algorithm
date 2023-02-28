package com.ilearning.huaweiod.basic.string;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 木板
 *
 * @author yuwenbo
 * @date 2023/3/1 5:45
 **/

/**
 * 小明有n块木板，第i（1＜=i＜=n）块木板的长度为ai。
 * 小明买了一块长度为m的木料，这块木料可以切割成任意块，拼接到已有的木板上，用来加长木板。
 * 小明想让最短的木板尽量长。
 * 请问小明加长木板后，最短木板的长度最大可以为多少？
 */
public class Plank {
    //假设输入为n,m和一个长度为n的数组a，表示木板的长度
//输出为一个整数，表示加长后最短木板的长度

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        solve(n, m, a);
    }
    public static int solve(int n, int m, int[] a) {
        //如果没有木料可以切割，直接返回数组中的最小值
        if (m == 0) {
            return min(a);
        }
        //对数组进行排序，从小到大
        Arrays.sort(a);
        //定义一个变量left，表示当前可以切割的木料长度
        int left = m;
        //定义一个变量right，表示当前最短木板的长度
        int right = a[0];
        //遍历数组中除了第一个元素之外的其他元素
        for (int i = 1; i < n; i++) {
            //计算当前元素和前一个元素之间的差值
            int gap = a[i] - a[i - 1];
            //如果差值乘以i小于等于剩余木料长度，说明可以将前i个木板都加长到a[i]的长度
            if (gap * i <= left) {
                //更新剩余木料长度和最短木板长度
                left -= gap * i;
                right = a[i];
            } else {
                //否则，说明不能将所有木板都加长到a[i]的长度，只能将部分木板加长到相同的长度
                //计算每个木板可以增加多少长度，并更新最短木板长度
                right += left / i;
                break;
            }
        }
        //返回最终结果
        System.out.println(right);
        return right;
    }

    //辅助函数，求一个数组中的最小值
    public static int min(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int x : a) {
            if (x < min) {
                min = x;
            }
        }
        System.out.println(min);
        return min;
    }
}
