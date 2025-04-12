package leetcode.question.dfs;
import java.util.*;

/**
 *@Question:  247. Strobogrammatic Number II
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 44.07%
 *@Time  Complexity: O(N * 5^(N/2 + 1)) for both
 *@Space Complexity: O(N * 5^(N/2))
 */
/**
 * ===============================================
 * LeetCode 247. Strobogrammatic Number II
 * ===============================================
 *
 * 【一、题目描述】
 * 给定一个整数 n，要求找出所有长度为 n 的 **Strobogrammatic 数字**。
 *
 * Strobogrammatic（对称数）是指：将数字旋转 180 度后仍然是有效数字，
 * 并且旋转后结果与原始数字相同。
 *
 * 合法的数对如下：
 * - '0' -> '0'
 * - '1' -> '1'
 * - '6' -> '9'
 * - '8' -> '8'
 * - '9' -> '6'
 *
 * 示例：
 * 输入: n = 2
 * 输出: ["11", "69", "88", "96"]
 *
 * 输入: n = 3
 * 输出: ["101", "609", "808", "906", "111", "619", "818", "916", "181", "689", "888", "986"]
 *
 *
 * 【二、解题思路】
 * 本题可通过两种方法解决：DFS 和 BFS。
 * 重点是构造回文结构，保持左右对称 + 数字合法。
 *
 * -------------------------------
 * 方法一：DFS 递归构造（generateStroboNumbers）
 * -------------------------------
 * 递归生成长度为 n 的 strobogrammatic 数字：
 * - 每次递归生成 n-2 长度的结果（去掉首尾各 1 位）；
 * - 然后从合法数对中构建当前长度的数字；
 * - 对于首尾不能使用 '0'（否则会有前导零）；
 * - 递归终止条件：
 *   - n == 0：返回空字符串 ""；
 *   - n == 1：返回 ["0", "1", "8"]（中间字符只允许这三种）；
 *
 * 举例解释（n = 3）：
 * - 先生成长度 1 的结果：["0", "1", "8"]
 * - 然后左右分别加合法数对：
 *   - "0" -> "101", "609", "808", "906"
 *   - "1" -> "111", "619", "818", "916"
 *   - "8" -> "181", "689", "888", "986"
 * - 共 12 个有效结果。
 *
 * -------------------------------
 * 方法二：BFS 层级构造（findStrobogrammatic2）
 * -------------------------------
 * - 初始化队列：当 n 是奇数，从 ["0", "1", "8"] 开始；当 n 是偶数，从 [""] 开始；
 * - 每次在队列中取出当前字符串，然后在首尾加合法字符对；
 * - 构造完 n 位后输出所有结果；
 * - 若当前位不是最外层，可以使用 '0'，否则跳过；
 *
 * 举例解释（n = 2）：
 * - 初始化：[""]
 * - 第一层添加：["00", "11", "69", "88", "96"]
 * - 去掉 "00"，最终结果为：["11", "69", "88", "96"]
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 时间复杂度：O(N × 5^(N/2))
 * - N 是数字长度；
 * - 每次添加一层时有 5 种合法数对；
 * - 每个数长为 N，构造需 O(N)；
 *
 * 空间复杂度：O(N × 5^(N/2))
 * - 最终结果数量为 O(5^(N/2)) 个字符串，每个长度为 N；
 * - DFS 递归也需 O(N) 栈空间；
 */


public class LeetCode_247_StrobogrammaticNumberIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 可逆的数字对，包括(0,0), (1,1), (6,9), (8,8), (9,6)
        public char[][] reversiblePairs = {
                {'0', '0'}, {'1', '1'},
                {'6', '9'}, {'8', '8'}, {'9', '6'}
        };

        // 解决方案1：深度优先搜索（DFS）
        public List<String> generateStroboNumbers(int n, int finalLength) {
            // 基本情况，0位数字的 strobogrammatic 数字为空字符串
            if (n == 0) {
                return new ArrayList<>(Arrays.asList(""));
            }

            // 基本情况，1位数字的 strobogrammatic 数字是 "0", "1", "8"
            if (n == 1) {
                return new ArrayList<>(Arrays.asList("0", "1", "8"));
            }

            // 递归调用生成 (n-2) 位的 strobogrammatic 数字
            List<String> prevStroboNums = generateStroboNumbers(n - 2, finalLength);
            List<String> currStroboNums = new ArrayList<>();

            // 遍历 (n-2) 位的数字
            for (String prevStroboNum : prevStroboNums) {
                // 遍历可逆对，拼接前后数字
                for (char[] pair : reversiblePairs) {
                    // 除了首位外，可以用 "0" 来填充
                    if (pair[0] != '0' || n != finalLength) {
                        // 拼接新的数字
                        currStroboNums.add(pair[0] + prevStroboNum + pair[1]);
                    }
                }
            }

            return currStroboNums;
        }

        // 外部接口，传入 n，返回所有长度为 n 的 strobogrammatic 数字
        public List<String> findStrobogrammatic(int n) {
            return generateStroboNumbers(n, n);
        }

        // 解决方案2：广度优先搜索（BFS）
        public List<String> findStrobogrammatic2(int n) {
            Queue<String> q = new LinkedList<>();
            int currStringsLength;

            // 当 n 为偶数时，递减直到 0
            if (n % 2 == 0) {
                currStringsLength = 0;
                q.add(""); // 空字符串作为起始状态
            } else {
                currStringsLength = 1;
                q.add("0"); // 1位 strobogrammatic 数字
                q.add("1");
                q.add("8");
            }

            // 扩展队列直到生成长度为 n 的 strobogrammatic 数字
            while (currStringsLength < n) {
                currStringsLength += 2;
                for (int i = q.size(); i > 0; --i) {
                    String number = q.poll();

                    // 遍历可逆对，构建新数字
                    for (char[] pair : reversiblePairs) {
                        // 除了首位外，可以用 "0" 来填充
                        if (currStringsLength != n || pair[0] != '0') {
                            q.add(pair[0] + number + pair[1]);
                        }
                    }
                }
            }

            // 最终返回生成的所有数字
            List<String> stroboNums = new ArrayList<>();
            while (!q.isEmpty()) {
                stroboNums.add(q.poll());
            }

            return stroboNums;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_247_StrobogrammaticNumberIi().new Solution();

        // 测试用例 1：查找长度为 2 的 strobogrammatic 数字
        List<String> result1 = solution.findStrobogrammatic(2);
        System.out.println("测试用例 1：长度为 2 的 strobogrammatic 数字：");
        System.out.println(result1); // 预期输出: ["11", "69", "96", "88"]

        // 测试用例 2：查找长度为 3 的 strobogrammatic 数字
        List<String> result2 = solution.findStrobogrammatic(3);
        System.out.println("测试用例 2：长度为 3 的 strobogrammatic 数字：");
        System.out.println(result2); // 预期输出: ["101", "111", "181", "609", "619", "689", "808", "818", "888", "906", "916", "986"]

        // 测试用例 3：查找长度为 4 的 strobogrammatic 数字
        List<String> result3 = solution.findStrobogrammatic(4);
        System.out.println("测试用例 3：长度为 4 的 strobogrammatic 数字：");
        System.out.println(result3); // 预期输出: ["1001", "1111", "1691", "1881", "1961", "6096", "6119", "6196", "6889", "6969", "8008", "8118", "8188", "8808", "8888", "8968"]
    }
}

/**
Given an integer n, return all the strobogrammatic numbers that are of length n.
 You may return the answer in any order. 

 A strobogrammatic number is a number that looks the same when rotated 180 
degrees (looked at upside down). 

 
 Example 1: 
 Input: n = 2
Output: ["11","69","88","96"]
 
 Example 2: 
 Input: n = 1
Output: ["0","1","8"]
 
 
 Constraints: 

 
 1 <= n <= 14 
 

 Related Topics Array String Recursion 👍 938 👎 260

*/