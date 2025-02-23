package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1726. Tuple with Same Product
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 68.36%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N^2)
 */
/**
 * 题目描述：
 * 1726. Tuple with Same Product
 * 给定一个由唯一正整数组成的数组 nums。我们需要找出所有 (a, b, c, d) 的元组，使得：
 *   - a * b = c * d
 *   - a、b、c 和 d 是数组中的不同元素
 *   - (a, b) 和 (c, d) 作为一对数对，顺序无关
 *   - 需要计算这样的元组的总数
 *
 * 示例 1：
 * 输入: nums = [2,3,4,6]
 * 输出: 8
 * 解释:
 * 可能的数对：
 * (2,6) 和 (3,4) → 2*6 = 3*4
 * 这两个数对可以生成 8 个元组: (2,6,3,4), (2,6,4,3), (6,2,3,4), (6,2,4,3), (3,4,2,6), (3,4,6,2), (4,3,2,6), (4,3,6,2)
 *
 * 示例 2：
 * 输入: nums = [1,2,4,5,10]
 * 输出: 16
 *
 * 解题思路：
 * 1. **统计所有数对的乘积**
 *    - 遍历数组中的所有两两组合，计算它们的乘积。
 *    - 用 HashMap 记录乘积的出现次数，即 `pairProductsFrequency[product]` 代表有多少对数乘积相等。
 *    - 例如：
 *      - 对于 `nums = [2,3,4,6]`
 *      - 计算乘积：
 *        - (2,3) → 6
 *        - (2,4) → 8
 *        - (2,6) → 12
 *        - (3,4) → 12
 *        - (3,6) → 18
 *        - (4,6) → 24
 *      - 生成的 HashMap 为：
 *        `{6:1, 8:1, 12:2, 18:1, 24:1}`
 *        - 其中 `12` 出现了两次，意味着有 2 组不同的 (a, b) 数对，它们的乘积相同。
 *
 * 2. **计算具有相同乘积的数对数量**
 *    - 对于相同乘积的数对，我们需要计算可以从这些数对中选出两对的方式数。
 *    - 选择两对的方式数公式：C(n, 2) = (n * (n-1)) / 2，其中 `n` 是该乘积的数对个数。
 *    - 例如：
 *      - `nums = [2,3,4,6]`
 *      - 乘积 `12` 出现了 `2` 次，可选的不同数对有 `(2,6)` 和 `(3,4)`
 *      - 选择两对的方式数为：C(2,2) = (2 * 1) / 2 = 1
 *
 * 3. **计算最终的元组数量**
 *    - 每个符合要求的两对 (a, b) 和 (c, d) 可以形成 8 种不同的排列：
 *      - (a, b, c, d)
 *      - (a, b, d, c)
 *      - (b, a, c, d)
 *      - (b, a, d, c)
 *      - (c, d, a, b)
 *      - (c, d, b, a)
 *      - (d, c, a, b)
 *      - (d, c, b, a)
 *    - 因此，最终结果为：
 *      `totalNumberOfTuples += 8 * pairsOfEqualProduct`
 *    - 例如：
 *      - `nums = [2,3,4,6]`
 *      - 计算出的 pairsOfEqualProduct = 1
 *      - 最终的元组数：8 * 1 = 8
 *
 * 时间复杂度分析：
 * - 计算所有数对的乘积需要 O(N^2)。
 * - 统计乘积频率需要 O(N^2)。
 * - 遍历 HashMap 计算元组总数需要 O(N^2)（最多存储 N^2 个键值对）。
 * - 总体时间复杂度为 **O(N^2)**。
 *
 * 空间复杂度分析：
 * - 使用 HashMap 记录数对的乘积频率，最多存储 O(N^2) 个键值对。
 * - 额外变量开销 O(1)。
 * - 总体空间复杂度为 **O(N^2)**。
 */

public class LeetCode_1726_TupleWithSameProduct{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public int tupleSameProduct(int[] nums) {
            // 获取数组的长度
            int numsLength = nums.length;

            // 使用 HashMap 记录每对数乘积出现的频率
            Map<Integer, Integer> pairProductsFrequency = new HashMap<>();

            // 记录最终的元组总数
            int totalNumberOfTuples = 0;

            // 遍历数组中的每对数字
            for (int firstIndex = 0; firstIndex < numsLength; firstIndex++) {
                for (
                        int secondIndex = firstIndex + 1;
                        secondIndex < numsLength;
                        secondIndex++
                ) {
                    // 计算当前两个数的乘积
                    int product = nums[firstIndex] * nums[secondIndex];
                    // 将该乘积的频率加 1
                    pairProductsFrequency.put(
                            product,
                            pairProductsFrequency.getOrDefault(product, 0) + 1
                    );
                }
            }

            // 遍历 HashMap 统计具有相同乘积的不同数对
            for (int productValue : pairProductsFrequency.keySet()) {
                // 获取该乘积对应的数对出现次数
                int productFrequency = pairProductsFrequency.get(productValue);
                // 计算可以从这些数对中选出两对的方式数 (C(n, 2) = n * (n-1) / 2)
                int pairsOfEqualProduct =
                        ((productFrequency - 1) * productFrequency) / 2;

                // 每对数对 (a, b) 和 (c, d) 可以形成 8 个不同的元组
                totalNumberOfTuples += 8 * pairsOfEqualProduct;
            }

            // 返回最终的元组总数
            return totalNumberOfTuples;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1726_TupleWithSameProduct().new Solution();

        // 测试样例
        int[] test1 = {2, 3, 4, 6};
        System.out.println(solution.tupleSameProduct(test1)); // 预期输出: 8

        int[] test2 = {1, 2, 4, 5, 10};
        System.out.println(solution.tupleSameProduct(test2)); // 预期输出: 16

        int[] test3 = {2, 3, 4, 6, 8, 12};
        System.out.println(solution.tupleSameProduct(test3)); // 预期输出: 40
    }
}

/**
Given an array nums of distinct positive integers, return the number of tuples (
a, b, c, d) such that a * b = c * d where a, b, c, and d are elements of nums, 
and a != b != c != d. 

 
 Example 1: 

 
Input: nums = [2,3,4,6]
Output: 8
Explanation: There are 8 valid tuples:
(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)
 

 Example 2: 

 
Input: nums = [1,2,4,5,10]
Output: 16
Explanation: There are 16 valid tuples:
(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
 

 
 Constraints: 

 
 1 <= nums.length <= 1000 
 1 <= nums[i] <= 10⁴ 
 All elements in nums are distinct. 
 

 Related Topics Array Hash Table Counting 👍 1326 👎 57

*/