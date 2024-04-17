package interview.company.amazon;

/**
 *@Question:  15. 3Sum
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 92.51%
 *@Time  Complexity: O(n^2)
 *@Space Complexity: O(n)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * **解题思路:**
 *
 * 这道题的目标是找到数组中所有不重复的三元组，使得三元组的和等于0。首先，我们对数组进行排序。接下来，我们可以使用双指针的方法来查找这些三元组。
 *
 * 1. **排序数组：** 对数组进行排序，这样可以方便我们使用双指针来查找三元组。
 *
 * 2. **遍历：** 遍历数组，对每个元素都尝试找到满足条件的三元组。
 *
 * 3. **双指针：** 使用两个指针分别指向当前元素的下一个元素和数组末尾，将三个元素的和与目标值0比较。
 *
 *    - 如果和小于0，说明需要增加和，将左指针右移。
 *    - 如果和大于0，说明需要减小和，将右指针左移。
 *    - 如果和等于0，说明找到了一个满足条件的三元组，将其加入结果集。然后，为避免重复，移动左指针到下一个不同的元素。
 *
 * 4. **避免重复：** 在遍历时，避免对相同元素重复计算，只处理相同元素中的第一个。
 *
 * **时间复杂度:**
 * - 对数组排序的时间复杂度是O(n log n)。
 * - 遍历数组并使用双指针的时间复杂度是O(n^2)。
 *
 * 总体时间复杂度为O(n^2)。
 *
 * **空间复杂度:**
 * - 我们只使用常量级的额外空间存储结果，因此空间复杂度是O(1)。
 */
public class LeetCode_15_ThreeSum {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            // 先对数组排序
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();

            // 遍历数组
            for (int i = 0; i < nums.length && nums[i] <= 0; ++i) {
                // 避免重复计算，只处理相同元素中的第一个
                if (i == 0 || nums[i - 1] != nums[i]) {
                    // 利用双指针法计算两数之和
                    twoSumII(nums, i, res);
                }
            }
            return res;
        }

        // 计算两数之和
        void twoSumII(int[] nums, int i, List<List<Integer>> res) {
            int lo = i + 1, hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum < 0) {
                    ++lo;
                } else if (sum > 0) {
                    --hi;
                } else {
                    // 找到符合条件的三元组，添加到结果集中
                    res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                    // 避免重复计算，跳过相同元素
                    while (lo < hi && nums[lo] == nums[lo - 1]) {
                        ++lo;
                    }
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        leetcode.question.two_pointer.LeetCode_15_ThreeSum.Solution solution = new leetcode.question.two_pointer.LeetCode_15_ThreeSum().new Solution();

        // 测试代码
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println("测试数组1的三元组：" + result1);

        int[] nums2 = {0, 1, 1};
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("测试数组2的三元组：" + result2);

        int[] nums3 = {0, 0, 0};
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println("测试数组3的三元组：" + result3);
    }
}

/**
 Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
 such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

 Notice that the solution set must not contain duplicate triplets.


 Example 1:


 Input: nums = [-1,0,1,2,-1,-4]
 Output: [[-1,-1,2],[-1,0,1]]
 Explanation:
 nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 The distinct triplets are [-1,0,1] and [-1,-1,2].
 Notice that the order of the output and the order of the triplets does not
 matter.


 Example 2:


 Input: nums = [0,1,1]
 Output: []
 Explanation: The only possible triplet does not sum up to 0.


 Example 3:


 Input: nums = [0,0,0]
 Output: [[0,0,0]]
 Explanation: The only possible triplet sums up to 0.



 Constraints:


 3 <= nums.length <= 3000
 -10⁵ <= nums[i] <= 10⁵


 Related Topics Array Two Pointers Sorting 👍 29387 👎 2670

 */
