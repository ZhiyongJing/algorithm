package leetcode.frequent.binary_search;

/**
  *@Question:  69. Sqrt(x)     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 73.91%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O()
 */

/**
 * 这个算法使用了二分查找法来寻找一个非负整数 `x` 的平方根，并返回其整数部分。以下是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. 如果 `x` 小于 2，那么其平方根就是 `x` 本身，直接返回。
 *
 * 2. 初始化左边界 `left` 为 2，右边界 `right` 为 `x / 2`。因为 `x` 的平方根不会大于 `x / 2`。
 *
 * 3. 进入二分查找循环，直到左边界大于右边界：
 *    - 计算中间值 `pivot` 为 `(left + right) / 2`。
 *    - 计算 `pivot` 的平方 `num`，并与 `x` 比较。
 *
 * 4. 如果 `num` 大于 `x`，说明平方根在左侧，将 `right` 缩小至 `pivot - 1`。
 *    如果 `num` 小于 `x`，说明平方根在右侧，将 `left` 增大至 `pivot + 1`。
 *    如果 `num` 等于 `x`，说明找到了平方根，直接返回 `pivot`。
 *
 * 5. 返回 `right`，即找到的平方根的整数部分。这里选择返回 `right` 是因为题目要求返回平方根的整数部分，
 * 而 `right` 在循环结束时总是比 `left` 小的最大整数。
 *
 * ### 时间复杂度：
 *
 * - 由于每一步都将搜索范围缩小一半，因此时间复杂度为 O(logN)，其中 N 为输入整数 `x`。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_69_Sqrtx {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算 x 的平方根
         *
         * @param x 目标值
         * @return x 的平方根的整数部分
         */
        public int mySqrt(int x) {
            // 如果 x 小于 2，则平方根为 x
            if (x < 2) return x;

            long num;
            int pivot, left = 1, right = x / 2;

            // 使用二分查找法查找平方根
            while (left <= right) {
                pivot = left + (right - left) / 2;
                num = (long) pivot * pivot;

                if (num > x) {
                    // 如果平方大于 x，缩小搜索范围至左侧
                    right = pivot - 1;
                } else if (num < x) {
                    // 如果平方小于 x，缩小搜索范围至右侧
                    left = pivot + 1;
                } else {
                    // 如果平方等于 x，返回平方根的整数部分
                    return pivot;
                }
            }

            // 返回平方根的整数部分（向下取整）
            return right;
        }

        public int mySqrt2(int x) {
            // 如果 x 小于 2，则平方根为 x
            if (x < 2) return x;

            long num;
            int pivot, left = 1, right = x / 2;
            // 使用二分查找法查找平方根
            while (left + 1 < right) {
                pivot =  left + (right - left) / 2;
                num = (long) pivot * pivot;
                if (num > x) {
                    // 如果平方大于 x，缩小搜索范围至左侧
                    right = pivot;
                } else {
                    left = pivot;
                }
            }
            System.out.println(left +"="+ right);
            if( right <= x/right) return right;
            return left;
        }

        public int mySqrt3(int x) {
            // 如果 x 小于 2，则平方根为 x
            if (x < 2) return x;

            long num;
            int pivot, left = 1, right = x / 2;
            // 使用二分查找法查找平方根
            while (left < right) {
                pivot =  (left + right + 1) / 2;
                num = (long) pivot * pivot;
                if (num > x) {
                    // 如果平方大于 x，缩小搜索范围至左侧
                    right = pivot - 1;
                } else {
                    left = pivot;
                }
            }
            return left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_69_Sqrtx().new Solution();

        // 测试代码
        // 预期输出: 2
        System.out.println(solution.mySqrt(4));

        // 预期输出: 2
        System.out.println(solution.mySqrt(8));
    }
}

/**
Given a non-negative integer x, return the square root of x rounded down to the 
nearest integer. The returned integer should be non-negative as well. 

 You must not use any built-in exponent function or operator. 

 
 For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python. 
 

 
 Example 1: 

 
Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.
 

 Example 2: 

 
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to 
the nearest integer, 2 is returned.
 

 
 Constraints: 

 
 0 <= x <= 2³¹ - 1 
 

 Related Topics Math Binary Search 👍 7675 👎 4392

*/
