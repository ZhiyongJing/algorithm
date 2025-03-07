package leetcode.question.map_set;

import java.util.HashSet;
import java.util.Set;

/**
 *@Question:  1980. Find Unique Binary String
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.68%
 *@Time  Complexity: O(n^2) for solution1 and solution2, O(N) for solution3
 *@Space Complexity: O(N^2) for solution1, O(N) for solution2, O(1) for solution3
 */

public class LeetCode_1980_FindUniqueBinaryString{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int n;  // 记录二进制字符串的长度
        Set<String> numsSet = new HashSet();  // 用于存储输入的二进制字符串集合

        /**
         * 递归生成可能的二进制字符串，并检查是否已存在于集合中
         * @param curr 当前生成的二进制字符串
         * @return 返回找到的不同二进制字符串，否则返回空字符串
         */
        private String generate(String curr) {
            // 如果当前字符串长度等于 n
            if (curr.length() == n) {
                // 检查该字符串是否在输入集合中
                if (!numsSet.contains(curr)) {
                    return curr;  // 找到不在集合中的二进制字符串，返回它
                }
                return "";  // 该字符串已存在，返回空字符串
            }

            // 递归尝试添加 '0'
            String addZero = generate(curr + "0");
            if (addZero.length() > 0) {
                return addZero;
            }

            // 递归尝试添加 '1'
            return generate(curr + "1");
        }

        /**
         * 方法 1：使用深度优先搜索（DFS）寻找唯一的二进制字符串
         * @param nums 给定的二进制字符串数组
         * @return 返回一个与输入不同的二进制字符串
         */
        public String findDifferentBinaryString1(String[] nums) {
            n = nums.length;  // 记录输入数组的长度
            for (String s : nums) {
                numsSet.add(s);  // 将所有输入的二进制字符串存入哈希集合
            }

            return generate("");  // 递归生成可能的二进制字符串
        }

        /**
         * 方法 2：使用整数转换检查唯一二进制字符串
         * @param nums 给定的二进制字符串数组
         * @return 返回一个与输入不同的二进制字符串
         */
        public String findDifferentBinaryString(String[] nums) {
            Set<Integer> integers = new HashSet();  // 存储所有二进制字符串转换后的整数值
            for (String num : nums) {
                integers.add(Integer.parseInt(num, 2));  // 将二进制字符串转换为整数，并存入集合
            }

            int n = nums.length;
            // 尝试 0 到 n 的所有整数
            for (int num = 0; num <= n; num++) {
                if (!integers.contains(num)) {  // 如果该整数不存在于集合中
                    String ans = Integer.toBinaryString(num);  // 转换为二进制字符串
                    while (ans.length() < n) {  // 补齐前导零，使长度与输入相同
                        ans = "0" + ans;
                    }
                    return ans;  // 返回找到的二进制字符串
                }
            }

            return "";  // 理论上不会走到这里
        }

        /**
         * 方法 3：Cantor's Diagonal Argument（对角线方法）
         * 直接构造不同的二进制字符串
         * @param nums 给定的二进制字符串数组
         * @return 返回一个与输入不同的二进制字符串
         */
        public String findDifferentBinaryString3(String[] nums) {
            StringBuilder ans = new StringBuilder();  // 用于存储生成的二进制字符串
            for (int i = 0; i < nums.length; i++) {
                Character curr = nums[i].charAt(i);  // 取每个字符串的第 i 个字符
                ans.append(curr == '0' ? '1' : '0');  // 取反（'0' 变 '1'，'1' 变 '0'）
            }

            return ans.toString();  // 返回生成的不同二进制字符串
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1980_FindUniqueBinaryString().new Solution();

        // 测试样例 1
        String[] nums1 = {"01", "10"};
        System.out.println("测试样例 1 结果：" + solution.findDifferentBinaryString(nums1)); // 预期输出："00" 或 "11"

        // 测试样例 2
        String[] nums2 = {"00", "01"};
        System.out.println("测试样例 2 结果：" + solution.findDifferentBinaryString(nums2)); // 预期输出："10" 或 "11"

        // 测试样例 3
        String[] nums3 = {"111", "000", "101"};
        System.out.println("测试样例 3 结果：" + solution.findDifferentBinaryString(nums3)); // 预期输出："010" 或 "110"

        // 测试方法 1（DFS）
        System.out.println("方法 1 (DFS) 结果：" + solution.findDifferentBinaryString1(nums1));

        // 测试方法 3（Cantor's 对角线方法）
        System.out.println("方法 3 (对角线法) 结果：" + solution.findDifferentBinaryString3(nums1));
    }
}

/**
Given an array of strings nums containing n unique binary strings each of 
length n, return a binary string of length n that does not appear in nums. If there 
are multiple answers, you may return any of them. 

 
 Example 1: 

 
Input: nums = ["01","10"]
Output: "11"
Explanation: "11" does not appear in nums. "00" would also be correct.
 

 Example 2: 

 
Input: nums = ["00","01"]
Output: "11"
Explanation: "11" does not appear in nums. "10" would also be correct.
 

 Example 3: 

 
Input: nums = ["111","011","001"]
Output: "101"
Explanation: "101" does not appear in nums. "000", "010", "100", and "110" 
would also be correct.
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 16 
 nums[i].length == n 
 nums[i] is either '0' or '1'. 
 All the strings of nums are unique. 
 

 Related Topics Array Hash Table String Backtracking 👍 2497 👎 87

*/