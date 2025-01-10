package leetcode.question.two_pointer;

/**
 *@Question:  556. Next Greater Element III
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 75.77%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 *@Question: 556. Next Greater Element III
 *
 * 1. 题目描述:
 * 给定一个正整数 n，找出一个与 n 中各个位数字相同但值比 n 大的整数。
 * 如果不存在这样的整数，则返回 -1。
 * 如果结果超出 32 位整数范围（即大于 2^31 - 1），也返回 -1。
 * 生成的整数应尽可能小。
 *
 * 示例:
 * 输入: n = 12, 输出: 21
 * 输入: n = 21, 输出: -1
 *
 * 2. 详细解题思路（结合举例说明）:
 * - **将整数转为字符数组**：
 *   - 将数字 n 转换为字符数组 a，方便逐位操作。
 *   - 例子: 输入 n = 1234，转为 a = ['1', '2', '3', '4']。
 *
 * - **寻找逆序点**：
 *   - 从右向左扫描，找到第一个位置 i，使得 a[i] < a[i+1]。
 *   - 如果找不到 i，说明数组完全降序排列，直接返回 -1。
 *   - 例子: a = ['1', '2', '3', '4']，逆序点 i = 2（a[2] = '3' < a[3] = '4'）。
 *
 * - **寻找右侧最小的大数**：
 *   - 从右向左扫描，找到第一个比 a[i] 大的数字 a[j]。
 *   - 例子: 在 a = ['1', '2', '3', '4'] 中，j = 3（a[3] = '4'）。
 *
 * - **交换 a[i] 和 a[j]**：
 *   - 交换 a[i] 和 a[j] 以确保新数字更大。
 *   - 例子: 交换后 a = ['1', '2', '4', '3']。
 *
 * - **反转后半部分**：
 *   - 将从位置 i+1 到数组末尾的部分进行反转，保证新数字是当前数字中最小的更大值。
 *   - 例子: 反转后半部分后，a = ['1', '2', '4', '3']（反转后无变化）。
 *
 * - **检查结果范围**：
 *   - 将修改后的字符数组转回整数。如果超出 32 位整数范围（大于 2^31 - 1），返回 -1。
 *   - 例子: 结果为 1243。
 *
 * - **无解情况说明**：
 *   - 如果输入 n = 4321，则字符数组 a = ['4', '3', '2', '1']，完全降序排列，无逆序点。
 *   - 此时返回 -1。
 *
 * 3. 时间和空间复杂度:
 * - **时间复杂度**：
 *   - 寻找逆序点：O(N)，N 为 n 的位数。
 *   - 寻找替换点：O(N)。
 *   - 反转后半部分：O(N)。
 *   - 总时间复杂度：O(N)。
 *
 * - **空间复杂度**：
 *   - 需要字符数组存储 n 的各位数字，空间复杂度为 O(N)。
 */

public class LeetCode_556_NextGreaterElementIii{

//leetcode submit region begin(Prohibit modification and deletion)

    public class Solution {
        public int nextGreaterElement(int n) {
            // 将整数转为字符数组以便于操作
            char[] a = ("" + n).toCharArray();

            // 从数组倒数第二位开始向前寻找第一对逆序的元素
            int i = a.length - 2;
            while (i >= 0 && a[i + 1] <= a[i]) {
                i--;
            }

            // 如果没有找到逆序对，说明不存在更大的排列，返回 -1
            if (i < 0)
                return -1;

            // 从数组末尾开始寻找第一个大于 a[i] 的元素
            int j = a.length - 1;
            while (j >= 0 && a[j] <= a[i]) {
                j--;
            }

            // 交换 a[i] 和 a[j]
            swap(a, i, j);

            // 反转从 i+1 到数组末尾的部分，使其成为升序
            reverse(a, i + 1);

            try {
                // 将字符数组转换为整数并返回结果
                return Integer.parseInt(new String(a));
            } catch (Exception e) {
                // 如果转换超出整数范围，返回 -1
                return -1;
            }
        }

        // 反转字符数组从指定起始位置到末尾的部分
        private void reverse(char[] a, int start) {
            int i = start, j = a.length - 1;
            while (i < j) {
                swap(a, i, j);
                i++;
                j--;
            }
        }

        // 交换字符数组中两个位置的元素
        private void swap(char[] a, int i, int j) {
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_556_NextGreaterElementIii().new Solution();

        // 测试样例
        int n1 = 1234; // 有下一个更大元素
        int n2 = 4321; // 没有下一个更大元素
        int n3 = 1999999999; // 边界情况（可能超出整数范围）

        System.out.println("Next greater element for " + n1 + ": " + solution.nextGreaterElement(n1)); // 1243
        System.out.println("Next greater element for " + n2 + ": " + solution.nextGreaterElement(n2)); // -1
        System.out.println("Next greater element for " + n3 + ": " + solution.nextGreaterElement(n3)); // -1
    }
}

/**
Given a positive integer n, find the smallest integer which has exactly the 
same digits existing in the integer n and is greater in value than n. If no such 
positive integer exists, return -1. 

 Note that the returned integer should fit in 32-bit integer, if there is a 
valid answer but it does not fit in 32-bit integer, return -1. 

 
 Example 1: 
 Input: n = 12
Output: 21
 
 Example 2: 
 Input: n = 21
Output: -1
 
 
 Constraints: 

 
 1 <= n <= 2³¹ - 1 
 

 Related Topics Math Two Pointers String 👍 3762 👎 480

*/