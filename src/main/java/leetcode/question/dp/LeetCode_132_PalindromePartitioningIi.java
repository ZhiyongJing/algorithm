package leetcode.question.dp;

/**
  *@Question:  132. Palindrome Partitioning II
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 30.010000000000005%
  *@Time  Complexity: O(N^ 3) for solution1， O(N^2) for solution2
  *@Space Complexity: O(N^2) for both
 */

/**
 * 这道题目是「132. Palindrome Partitioning II」，目标是找到使给定字符串变成回文串的最小切割次数。
 * 这里分别使用了自顶向下（Top-Down）和自底向上（Bottom-Up）两种动态规划的方法。
 *
 * ### 解题思路
 *
 * #### Solution1: Top-Down
 * 1. **递归函数：** 使用递归函数 `findMinimumCut` 来找到从字符串的某一起始位置到终止位置的最小切割次数。
 * 2. **基本情况：** 如果当前起始位置和终止位置相同，或者当前部分是回文串，返回0。
 * 3. **递归调用：** 对于每一个可能的切割点，递归调用函数，并更新最小切割次数。
 * 4. **记忆化搜索：** 使用 memoization 记录已经计算过的结果，避免重复计算。
 *
 * #### Solution2: Bottom-Up
 * 1. **动态规划数组：** 使用数组 `cutsDp` 记录从字符串开始到每个位置的最小切割次数。
 * 2. **构建回文串动态规划数组：** 使用数组 `palindromeDp` 记录字符串中每个子串是否是回文串。
 * 3. **动态规划更新：** 遍历字符串中每个位置，考虑以当前位置为结束的子串，更新最小切割次数。
 *
 * ### 时间和空间复杂度
 *
 * #### Solution1: Top-Down
 * - **时间复杂度：** 在最坏情况下，需要考虑每个可能的切割点，所以时间复杂度为 O(n^2)，其中 n 是字符串的长度。
 * - **空间复杂度：** 使用了 memoization 数组来存储已计算过的结果，空间复杂度也是 O(n^2)。
 *
 * #### Solution2: Bottom-Up
 * - **时间复杂度：** 需要遍历字符串的每个位置，并对每个位置考虑所有可能的切割点，因此时间复杂度为 O(n^2)。
 * - **空间复杂度：** 使用了两个动态规划数组，cutsDp 和 palindromeDp，每个都需要 O(n^2) 的空间，所以空间复杂度是 O(n^2)。
 *
 * 这两种方法都利用了动态规划的思想，通过子问题的最优解来构建整体问题的解。在实际应用中，
 * Bottom-Up 方法通常更为高效，因为它避免了递归调用的开销，并且具有更好的时间和空间复杂度。
 */

public class LeetCode_132_PalindromePartitioningIi{

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //Solution1: Top-Down
    private Integer memoCuts[][];
    private Boolean memoPalindrome[][];

    public int minCut1(String s) {
        memoCuts = new Integer[s.length()][s.length()];
        memoPalindrome = new Boolean[s.length()][s.length()];
        return findMinimumCut(s, 0, s.length() - 1, s.length() - 1);
    }

    private int findMinimumCut(String s, int start, int end, int minimumCut) {
        // base case
        if (start == end || isPalindrome(s, start, end)) {
            return 0;
        }
        // check for results in memoCuts
        if (memoCuts[start][end] != null) {
            return memoCuts[start][end];
        }
        for (int currentEndIndex = start; currentEndIndex <= end; currentEndIndex++) {
            if (isPalindrome(s, start, currentEndIndex)) {
                minimumCut = Math
                        .min(minimumCut, 1 + findMinimumCut(s, currentEndIndex + 1, end, minimumCut));
            }
        }
        return memoCuts[start][end] = minimumCut;
    }

    private boolean isPalindrome(String s, int start, int end) {
        if (start >= end) {
            return true;
        }
        // check for results in memoPalindrome
        if (memoPalindrome[start][end] != null) {
            return memoPalindrome[start][end];
        }
        return memoPalindrome[start][end] = (s.charAt(start) == s.charAt(end))
                && isPalindrome(s, start + 1, end - 1);
    }

    //Solution2: Bottom-Up
    private Integer cutsDp[];
    private boolean palindromeDp[][];

    public int minCut(String s) {
        cutsDp = new Integer[s.length()];
        palindromeDp = new boolean[s.length()][s.length()];
        // build the palindrome cutsDp for all susbtrings
        buildPalindromeDp(s, s.length());

        for (int end = 0; end < s.length(); end++) {
            int minimumCut = end;
            for (int start = 0; start <= end; start++) {
                if (palindromeDp[start][end]) {
                    minimumCut = start == 0 ? 0 : Math.min(minimumCut, cutsDp[start - 1] + 1);
                }
            }
            cutsDp[end] = minimumCut;
        }
        return cutsDp[s.length() - 1];
    }

    private void buildPalindromeDp(String s, int n) {
        for (int end = 0; end < s.length(); end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) && (end - start <= 2 ||
                        palindromeDp[start + 1][end - 1])) {
                    palindromeDp[start][end] = true;
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_132_PalindromePartitioningIi.Solution solution =
                new LeetCode_132_PalindromePartitioningIi().new Solution();
        // 测试代码
        String testString1 = "aab";
        System.out.println("测试1：" + solution.minCut1(testString1));  // 输出：1

        String testString2 = "ab";
        System.out.println("测试2：" + solution.minCut(testString2));  // 输出：1

        String testString3 = "a";
        System.out.println("测试3：" + solution.minCut(testString3));  // 输出：0
    }
}
/**
Given a string s, partition s such that every substring of the partition is a 
palindrome. 

 Return the minimum cuts needed for a palindrome partitioning of s. 

 
 Example 1: 

 
Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 
cut.
 

 Example 2: 

 
Input: s = "a"
Output: 0
 

 Example 3: 

 
Input: s = "ab"
Output: 1
 

 
 Constraints: 

 
 1 <= s.length <= 2000 
 s consists of lowercase English letters only. 
 

 Related Topics String Dynamic Programming 👍 5287 👎 139

*/