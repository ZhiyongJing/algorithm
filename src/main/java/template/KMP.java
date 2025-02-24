package template;

import java.util.Arrays;

public class KMP {
    public int[] buildKmpNext(String dest) {
        int[] next = new int[dest.length()];
        // 第一个字符的前缀和后缀都没有，所以不会有公共元素，因此必定为 0
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            /*
              ABCDA
              前缀：`A、AB、ABC、ABCD`
              后缀：`BCDA、CDA、DA、A`
              公共元素 A
              部分匹配值：1
             */
            // 当  dest.charAt(i) != dest.charAt(j) 时
            // 需要从 next[j-1] 中获取新的 j
            // 这步骤是 部分匹配表的 核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当相等时，表示有一个部分匹配值
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    public void kmpNextTest() {
//        String dest = "A";
//        System.out.println("字符串：" + dest);
//        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
//        System.out.println();
//        dest = "AA";
//        System.out.println("字符串：" + dest);
//        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
//        System.out.println();
//        dest = "AAA";
//        System.out.println("字符串：" + dest);
//        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
//        System.out.println();
//        dest = "AAAB";
//        System.out.println("字符串：" + dest);
//        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
//        System.out.println();
//        dest = "ABCDABD";
//        System.out.println("字符串：" + dest);
//        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
//        System.out.println();
        String dest = "A";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
        dest = "AB";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
        dest = "ABA";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
        dest = "ABAC";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
        dest = "ABACA";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
        dest = "ABACAB";
        System.out.println("字符串：" + dest);
        System.out.println("的部分匹配表为：" + Arrays.toString(buildKmpNext(dest)));
        System.out.println();
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();
        kmp.kmpNextTest();
        System.out.println(Arrays.toString(kmp.buildKmpNext("abacab")));


    }
}
