package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @Question: 93. Restore IP Addresses
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 62.66%
 * @Time Complexity: O(n*m^n) separate the input string into N integers, each integer is at most M digits
 * @Space Complexity: O()
 */

public class LeetCode_93_RestoreIpAddresses {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 验证当前位置的子串是否是合法的 IP 地址整数
        private boolean valid(String s, int start, int length) {
            return length == 1 ||
                    (s.charAt(start) != '0' &&
                            (length < 3 ||
                                    s.substring(start, start + length).compareTo("255") <= 0));
        }

        // 递归辅助方法，用于生成合法的 IP 地址
        private void helper(String s, int startIndex, List<Integer> dots, List<String> ans) {
            final int remainingLength = s.length() - startIndex;
            final int remainingNumberOfIntegers = 4 - dots.size();
            if (remainingLength > remainingNumberOfIntegers * 3 ||
                    remainingLength < remainingNumberOfIntegers) {
                return;
            }
            if (dots.size() == 3) {
                // 已经插入了 3 个点，判断剩余的字符串是否满足合法条件，是则加入结果集
                if (valid(s, startIndex, remainingLength)) {
                    StringBuilder sb = new StringBuilder();
                    int last = 0;
                    for (Integer dot : dots) {
                        sb.append(s.substring(last, last + dot));
                        last += dot;
                        sb.append('.');
                    }
                    sb.append(s.substring(startIndex));
                    ans.add(sb.toString());
                }
                return;
            }
            for (int curPos = 1; curPos <= 3 && curPos <= remainingLength; ++curPos) {
                // 在当前位置插入点
                dots.add(curPos);
                // 尝试生成所有可能的组合
                if (valid(s, startIndex, curPos)) {
                    helper(s, startIndex + curPos, dots, ans);
                }
                System.out.println(dots);
                // 回溯，即移除点以在下一个位置尝试插入
                dots.remove(dots.size() - 1);
            }
        }

        // 主方法，调用 helper 开始递归，返回生成的合法 IP 地址列表
        public List<String> restoreIpAddresses(String s) {
            List<String> ans = new ArrayList<>();
            helper(s, 0, new ArrayList<>(), ans);
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_93_RestoreIpAddresses().new Solution();
        // TO TEST
        // Example usage:
        // String input = "25525511135";
        // List<String> result = solution.restoreIpAddresses(input);
        // System.out.println(result);
    }
}

/**
A valid IP address consists of exactly four integers separated by single dots.
Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.


 For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.2
55.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.


 Given a string s containing only digits, return all possible valid IP
addresses that can be formed by inserting dots into s. You are not allowed to reorder or
remove any digits in s. You may return the valid IP addresses in any order.


 Example 1:


Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]


 Example 2:


Input: s = "0000"
Output: ["0.0.0.0"]


 Example 3:


Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]



 Constraints:


 1 <= s.length <= 20
 s consists of digits only.


 Related Topics String Backtracking 👍 5023 👎 773

*/
