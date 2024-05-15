package interview.company.amazon;

import java.util.Arrays;

/**
 * **算法思路详解：**
 *
 * 1. **初始化：** 在构造函数中，首先计算权重数组的前缀和 `prefixSums` 和总和 `totalSum`。
 * 前缀和用于确定随机数在哪个权重区间，总和用于生成随机数的上限。
 *
 * 2. **随机选择：** 在 `pickIndex` 方法中，生成一个在 `[0, totalSum)` 范围内的随机数 `target`。
 *
 * 3. **二分查找：** 利用二分查找找到 `target` 在前缀和数组中的位置，返回该位置作为随机选择的索引。
 *
 * **时间复杂度：**
 *
 * - 构造函数中计算前缀和的时间复杂度为 O(N)，其中 N 是权重数组的长度。
 *
 * - `pickIndex` 方法中的二分查找的时间复杂度为 O(log N)。
 *
 * 因此，总体时间复杂度为 O(N + log N)，即 O(N)。
 *
 * **空间复杂度：**
 *
 * - 需要额外的空间存储前缀和数组 `prefixSums`，空间复杂度为 O(N)。
 *
 * - 需要常数级别的额外空间。
 *
 * 因此，总体空间复杂度为 O(N)。
 */

public class LeetCode_528_RandomPickWithWeight{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int[] prefixSums;
        private int totalSum;

        // 构造函数，计算前缀和
        public Solution(int[] w) {
            this.prefixSums = new int[w.length];
            System.out.println(Arrays.toString(w));

            int prefixSum = 0;
            for (int i = 0; i < w.length; ++i) {
                prefixSum += w[i];
                this.prefixSums[i] = prefixSum;
            }
            this.totalSum = prefixSum;
            System.out.println(Arrays.toString(prefixSums));

        }



        // 选择随机索引
        public int pickIndex() {
            double target = this.totalSum * Math.random();

            // 通过二分查找找到目标区间
            int low = 0, high = this.prefixSums.length;
            while (low < high) {
                // 避免溢出
                int mid = low + (high - low) / 2;
                if (target > this.prefixSums[mid])
                    low = mid + 1;
                else
                    high = mid;
            }
            return low;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        // 以示例中的权重数组 [1, 3, 2] 为例
        int[] weights = {1, 2, 4, 3};

        // 创建Solution对象
        leetcode.question.binary_search.LeetCode_528_RandomPickWithWeight.Solution solution = new leetcode.question.binary_search.LeetCode_528_RandomPickWithWeight().new Solution(weights);

        // 进行随机选择测试
        for (int i = 0; i < 10; i++) {
            int index = solution.pickIndex();
            System.out.println("Randomly picked index: " + index);
        }
    }
}

/**
 You are given a 0-indexed array of positive integers w where w[i] describes the
 weight of the iᵗʰ index.

 You need to implement the function pickIndex(), which randomly picks an index
 in the range [0, w.length - 1] (inclusive) and returns it. The probability of
 picking an index i is w[i] / sum(w).


 For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) =
 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75
 (i.e., 75%).



 Example 1:


 Input
 ["Solution","pickIndex"]
 [[[1]],[]]
 Output
 [null,0]

 Explanation
 Solution solution = new Solution([1]);
 solution.pickIndex(); // return 0. The only option is to return 0 since there
 is only one element in w.


 Example 2:


 Input
 ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 [[[1,3]],[],[],[],[],[]]
 Output
 [null,1,1,1,1,0]

 Explanation
 Solution solution = new Solution([1, 3]);
 solution.pickIndex(); // return 1. It is returning the second element (index = 1
 ) that has a probability of 3/4.
 solution.pickIndex(); // return 1
 solution.pickIndex(); // return 1
 solution.pickIndex(); // return 1
 solution.pickIndex(); // return 0. It is returning the first element (index = 0)
 that has a probability of 1/4.

 Since this is a randomization problem, multiple answers are allowed.
 All of the following outputs can be considered correct:
 [null,1,1,1,1,0]
 [null,1,1,1,1,1]
 [null,1,1,1,0,0]
 [null,1,1,1,0,1]
 [null,1,0,1,0,0]
 ......
 and so on.



 Constraints:


 1 <= w.length <= 10⁴
 1 <= w[i] <= 10⁵
 pickIndex will be called at most 10⁴ times.


 Related Topics Array Math Binary Search Prefix Sum Randomized 👍 1693 👎 710

 */


 
// Constraints:
//
//
// 1 <= w.length <= 10⁴
// 1 <= w[i] <= 10⁵
// pickIndex will be called at most 10⁴ times.
//
//
// Related Topics Array Math Binary Search Prefix Sum Randomized 👍 1826 👎 786
//
//*/