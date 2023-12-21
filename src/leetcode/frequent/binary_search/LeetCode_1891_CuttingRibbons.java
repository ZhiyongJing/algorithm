package leetcode.frequent.binary_search;

/**
 *@Question:  1891. Cutting Ribbons
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 31.15%
 *@Time  Complexity: O(NlogK)
 *@Space Complexity: O(1)
 */

/**
 * **算法思路：**
 *
 * 这个问题的核心思想是使用二分查找来找到满足条件的最大长度。具体步骤如下：
 *
 * 1. **初始化边界：** 将二分查找的左边界 `l` 设为1，右边界 `r` 设为10^5 + 1。因为任何绳子的长度都不会超过这个范围。
 *
 * 2. **二分查找：** 在每一次迭代中，计算中间长度 `mid`，并调用 `isCutPossible` 函数判断是否可以按照给定长度进行切割。
 * 如果不能，说明长度过长，将右边界 `r` 更新为 `mid`；如果可以，说明长度足够，将左边界 `l` 更新为 `mid + 1`。
 *
 * 3. **返回结果：** 当 `l` 和 `r` 相遇时，说明找到了满足条件的最大长度，返回 `l - 1`。
 *
 * 4. **判断是否可以按照给定长度进行切割：** `isCutPossible` 函数用于判断是否可以按照给定长度 `length` 进行切割。
 * 遍历绳子数组，计算每根绳子可以切割出的段数，累加得到总段数 `count`。如果 `count` 大于等于需要的绳子数量 `k`，说明可以切割，返回 `true`；否则，返回 `false`。
 *
 * **时间复杂度：**
 *
 * - 二分查找的时间复杂度为 O(logN)，其中 N 是二分查找的搜索范围（最大绳子长度的上限）。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度为 O(1)，没有使用额外的数据结构，只使用了常数级别的额外空间。
 */
public class LeetCode_1891_CuttingRibbons {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 计算满足要求的最大长度
         * @param ribbons 给定的绳子数组
         * @param k 需要的绳子数量
         * @return 满足要求的最大长度
         */
        public int maxLength(int[] ribbons, int k) {
            int l = 1;
            int r = (int) 1e5 + 1;
            while (l < r) {
                int mid = l + ((r - l) >> 1);

                if (!isCutPossible(ribbons, mid, k)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            return l - 1;
        }

        /**
         * 检查是否可以按照给定长度进行切割
         * @param ribbons 给定的绳子数组
         * @param length 给定的长度
         * @param k 需要的绳子数量
         * @return 是否可以按照给定长度进行切割
         */
        public boolean isCutPossible(int[] ribbons, int length, int k) {
            int count = 0;
            for (int ribbon : ribbons) {
                count += (ribbon / length);
            }
            return count >= k;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1891_CuttingRibbons().new Solution();

        // 测试代码
        int[] testRibbons = {1, 2, 3, 4, 9};
        int k = 5;
        int result = solution.maxLength(testRibbons, k);

        System.out.println("满足要求的最大长度是：" + result);
    }
}

/**
You are given an integer array ribbons, where ribbons[i] represents the length 
of the iᵗʰ ribbon, and an integer k. You may cut any of the ribbons into any 
number of segments of positive integer lengths, or perform no cuts at all. 

 
 For example, if you have a ribbon of length 4, you can: 
 

 
 Keep the ribbon of length 4, 
 Cut it into one ribbon of length 3 and one ribbon of length 1, 
 Cut it into two ribbons of length 2, 
 Cut it into one ribbon of length 2 and two ribbons of length 1, or 
 Cut it into four ribbons of length 1. 
 
 


 Your goal is to obtain k ribbons of all the same positive integer length. You 
are allowed to throw away any excess ribbon as a result of cutting. 

 Return the maximum possible positive integer length that you can obtain k 
ribbons of, or 0 if you cannot obtain k ribbons of the same length. 

 
 Example 1: 

 
Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5. 

 Example 2: 

 
Input: ribbons = [7,5,9], k = 4
Output: 4
Explanation:
- Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
- Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
- Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
Now you have 4 ribbons of length 4.
 

 Example 3: 

 
Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.
 

 
 Constraints: 

 
 1 <= ribbons.length <= 10⁵ 
 1 <= ribbons[i] <= 10⁵ 
 1 <= k <= 10⁹ 
 

 Related Topics Array Binary Search 👍 541 👎 37

*/
