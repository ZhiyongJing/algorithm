package leetcode.frequent.binary_search;

/**
  *@Question:  162. Find Peak Element     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 79.53%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * 这个算法是用于在给定数组中寻找峰值的解决方案。峰值是指数组中的一个元素，其值大于或等于相邻元素的值。这个问题可以使用二分查找来解决。
 *
 * ### 算法思路：
 *
 * 1. 初始化左指针 `l` 为 0，右指针 `r` 为数组的长度减一。
 *
 * 2. 进行二分查找，计算中间位置 `mid`。
 *
 *    - 如果 `nums[mid] > nums[mid + 1]`，说明峰值在当前位置或者在当前位置的左侧，将右指针 `r` 移动到 `mid`。
 *
 *    - 否则，说明峰值在当前位置的右侧，将左指针 `l` 移动到 `mid + 1`。
 *
 * 3. 不断缩小搜索范围，直到左指针 `l` 等于右指针 `r`。此时，峰值的索引即为 `l` 或 `r`。
 *
 * ### 时间复杂度：
 *
 * - 由于每次比较都将搜索范围减半，因此时间复杂度为 O(logN)，其中 N 是数组的长度。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_162_FindPeakElement {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 寻找峰值
         *
         * @param nums 包含 n 个整数的数组
         * @return 峰值的索引
         */
        public int findPeakElement(int[] nums) {
            int l = 0, r = nums.length - 1;
            if(l == r) return l;
            if(r -l ==1) return nums[l] > nums[r] ? l: r;

            while (l < r) {
                int mid = (l + r) / 2;

                // 比较当前位置和下一个位置的元素大小
                if (nums[mid] > nums[mid + 1]) {
                    // 如果当前位置元素大于下一个位置元素，说明峰值在当前位置或左侧
                    r = mid;
                } else {
                    // 如果当前位置元素小于等于下一个位置元素，说明峰值在右侧
                    l = mid + 1;
                }
            }

            // 最终 l == r，返回其中一个即可
            return l;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_162_FindPeakElement().new Solution();

        // 测试代码
        int[] nums = {1, 2, 1, 3, 5, 6, 4};

        // 预期输出: 5 (6是峰值)
        System.out.println(solution.findPeakElement(nums));
    }
}

/**
A peak element is an element that is strictly greater than its neighbors. 

 Given a 0-indexed integer array nums, find a peak element, and return its 
index. If the array contains multiple peaks, return the index to any of the peaks. 

 You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is 
always considered to be strictly greater than a neighbor that is outside the array.
 

 You must write an algorithm that runs in O(log n) time. 

 
 Example 1: 

 
Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index 
number 2. 

 Example 2: 

 
Input: nums = [1,2,1,3,5,6,4]
Output: 5
Explanation: Your function can return either index number 1 where the peak 
element is 2, or index number 5 where the peak element is 6. 

 
 Constraints: 

 
 1 <= nums.length <= 1000 
 -2³¹ <= nums[i] <= 2³¹ - 1 
 nums[i] != nums[i + 1] for all valid i. 
 

 Related Topics Array Binary Search 👍 11079 👎 4526

*/
