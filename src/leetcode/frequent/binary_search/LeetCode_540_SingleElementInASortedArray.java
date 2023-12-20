package leetcode.frequent.binary_search;

/**
  *@Question:  540. Single Element in a Sorted Array     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 74.67%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

/**
 *### 算法思路：
 *
 * 这个算法利用二分查找法，通过比较中间元素与相邻元素的关系，来判断单一元素在数组的哪一侧。
 *
 * 1. 初始化左边界 `lo` 为 0，右边界 `hi` 为数组长度减 1。
 *
 * 2. 进入二分查找循环，直到左边界小于右边界：
 *    - 计算中间值 `mid` 为 `(lo + hi) / 2`。
 *    - 使用 `halvesAreEven` 来判断当前 mid 到 hi 的元素个数是否为偶数，判断左右半部分元素个数的奇偶性。
 *
 * 3. 检查 mid 与相邻元素的关系：
 *    - 如果 `nums[mid + 1] == nums[mid]`，说明单一元素在右侧。
 *       - 如果右侧元素个数为偶数，将 `lo` 调整至 `mid + 2`。
 *       - 如果右侧元素个数为奇数，将 `hi` 调整至 `mid - 1`。
 *    - 如果 `nums[mid - 1] == nums[mid]`，说明单一元素在左侧。
 *       - 如果左侧元素个数为偶数，将 `hi` 调整至 `mid - 2`。
 *       - 如果左侧元素个数为奇数，将 `lo` 调整至 `mid + 1`。
 *    - 如果 `nums[mid + 1] != nums[mid]` 且 `nums[mid - 1] != nums[mid]`，说明当前元素就是单一元素，直接返回。
 *
 * 4. 返回 `nums[lo]`，即找到的单一元素。
 *
 * ### 时间复杂度：
 *
 * - 每一步都将搜索范围缩小一半，因此时间复杂度为 O(logN)，其中 N 为输入数组的长度。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_540_SingleElementInASortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 找出有序数组中只出现一次的元素
         *
         * @param nums 有序数组，每个元素都出现两次，只有一个元素出现一次
         * @return 只出现一次的元素
         */
        public int singleNonDuplicate(int[] nums) {
            int lo = 0;
            int hi = nums.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                boolean halvesAreEven = (hi - mid) % 2 == 0;

                //[1,1,2,3,3,4,4,8,8]
                //[1,1,2,2,4,4,5,8,8]
                if (nums[mid + 1] == nums[mid]) {
                    if (halvesAreEven) {
                        // 如果右半部分元素个数为偶数，说明单一元素在右半部分
                        lo = mid + 2;
                    } else {
                        // 如果右半部分元素个数为奇数，说明单一元素在左半部分
                        hi = mid - 1;
                    }
                } else if (nums[mid - 1] == nums[mid]) {
                    if (halvesAreEven) {
                        // 如果左半部分元素个数为偶数，说明单一元素在左半部分
                        hi = mid - 2;
                    } else {
                        // 如果左半部分元素个数为奇数，说明单一元素在右半部分
                        lo = mid + 1;
                    }
                } else {
                    // 如果两边都没有相邻相等的元素，说明当前元素就是单一元素
                    return nums[mid];
                }
            }
            // 返回单一元素
            return nums[lo];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_540_SingleElementInASortedArray().new Solution();

        // 测试用例1
        // 预期输出: 2
        int[] nums1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println(solution.singleNonDuplicate(nums1));

        // 测试用例2
        // 预期输出: 10
        int[] nums2 = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(solution.singleNonDuplicate(nums2));
    }
}

/**
You are given a sorted array consisting of only integers where every element 
appears exactly twice, except for one element which appears exactly once. 

 Return the single element that appears only once. 

 Your solution must run in O(log n) time and O(1) space. 

 
 Example 1: 
 Input: nums = [1,1,2,3,3,4,4,8,8]
Output: 2
 
 Example 2: 
 Input: nums = [3,3,7,7,10,11,11]
Output: 10
 
 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 0 <= nums[i] <= 10⁵ 
 

 Related Topics Array Binary Search 👍 10589 👎 166

*/
