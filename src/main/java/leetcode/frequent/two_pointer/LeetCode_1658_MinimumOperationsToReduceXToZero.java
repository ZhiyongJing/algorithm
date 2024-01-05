package leetcode.frequent.two_pointer;

/**
  *@Question:  1658. Minimum Operations to Reduce X to Zero     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 73.99%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * **问题描述：**
 *
 * 给定一个整数数组 `nums` 和一个目标值 `x`，我们可以执行一系列操作，将数组中的元素减小，使得数组的和等于目标值 `x`。
 * 操作数是指执行减法操作的次数。返回将数组减小到目标值 `x` 所需的最小操作数。如果无法减小数组到目标值 `x`，返回 -1。
 这个问题的主要思路是使用双指针来维护一个窗口，通过移动窗口的左右边界，找到满足条件的子数组。这两种解法在具体实现上有一些差异，下面将详细讲解两种解法的思路和复杂度分析。

 ### 解法 1

 1. **计算总和：** 首先，遍历整个数组，计算出数组中所有元素的总和 `total`。
 2. **双指针移动窗口：** 使用两个指针，`left` 和 `right`，分别表示窗口的左右边界。遍历数组，通过移动右指针扩大窗口，
 同时检查当前窗口的和是否大于 `total - x`。如果大于，就移动左指针缩小窗口，直到窗口的和等于 `total - x`。
 3. **更新最大窗口大小：** 在窗口和等于 `total - x` 的情况下，更新最大窗口大小 `maxi`，即右指针减去左指针的距离。
 4. **计算最终结果：** 最终结果为数组长度减去最大窗口大小，即 `n - maxi`。如果不存在符合条件的子数组，返回 -1。

 ### 解法 2

 1. **计算总和：** 同样地，遍历整个数组，计算出数组中所有元素的总和 `current`。
 2. **双指针移动窗口：** 使用两个指针，`left` 和 `right`，分别表示窗口的左右边界。遍历数组，通过移动右指针扩大窗口，
 同时检查当前窗口的和是否小于 `x`。如果小于，就移动左指针缩小窗口，直到窗口的和等于 `x`。
 3. **更新最小操作次数：** 在窗口和等于 `x` 的情况下，更新最小操作次数 `mini`，即右指针距离数组末尾的距离加上左指针的位置。
 4. **计算最终结果：** 最终结果为最小操作次数 `mini`。如果不存在符合条件的子数组，返回 -1。

 ### 时间和空间复杂度

 **解法 1：**
 - 时间复杂度：O(n)，其中 n 为数组的长度。因为我们只对数组进行一次遍历。
 - 空间复杂度：O(1)，只使用常数额外空间。

 **解法 2：**
 - 时间复杂度：O(n)，其中 n 为数组的长度。因为我们只对数组进行一次遍历。
 - 空间复杂度：O(1)，只使用常数额外空间。

 这两种解法都是通过一次遍历数组来找到满足条件的子数组，因此时间复杂度是线性的。在空间上也没有使用额外的数据结构，所以空间复杂度是常数级别的。
 */
public class LeetCode_1658_MinimumOperationsToReduceXToZero {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 解法 1
        public int minOperations2(int[] nums, int x) {
            int total = 0;
            // 计算数组所有元素的和
            for (int num : nums) {
                total += num;
            }
            int n = nums.length;
            int maxi = -1;
            int left = 0;
            int current = 0;

            for (int right = 0; right < n; right++) {
                // 计算当前窗口的和
                current += nums[right];
                // 如果当前窗口的和大于 total - x，将左指针右移，缩小窗口
                while (current > total - x && left <= right) {
                    current -= nums[left];
                    left += 1;
                }
                // 如果当前窗口的和等于 total - x，更新最大窗口大小
                if (current == total - x) {
                    maxi = Math.max(maxi, right - left + 1);
                }
            }
            // 如果存在可行的窗口，返回数组剩余部分的长度，否则返回 -1
            return maxi != -1 ? n - maxi : -1;
        }

        // 解法 2
        public int minOperations(int[] nums, int x) {
            int current = 0;
            // 计算数组所有元素的和
            for (int num : nums) {
                current += num;
            }
            int n = nums.length;
            int mini = Integer.MAX_VALUE;
            int left = 0;

            // 使用双指针来维护窗口
            for (int right = 0; right < n; right++) {
                // 从当前窗口减去右边的元素 current = sum[0-left) + sum(right-n]
                current -= nums[right];
                // 如果当前和小于 x，将左指针右移，增加窗口的大小
                while (current < x && left <= right) {
                    current += nums[left];
                    left += 1;
                }
                // 如果当前和等于 x，更新最小操作次数
                if (current == x) {
                    mini = Math.min(mini, (n - 1 - right) + left);
                }
            }
            // 如果最小操作次数未被更新，说明无法将数组减到 x，返回 -1
            return mini != Integer.MAX_VALUE ? mini : -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1658_MinimumOperationsToReduceXToZero().new Solution();
        // 测试代码
        int[] nums = {1, 1, 4, 2, 3};
        int x = 5;
        System.out.println(solution.minOperations(nums, x));  // 应该输出 2
    }
}


/**
You are given an integer array nums and an integer x. In one operation, you can 
either remove the leftmost or the rightmost element from the array nums and 
subtract its value from x. Note that this modifies the array for future operations. 


 Return the minimum number of operations to reduce x to exactly 0 if it is 
possible, otherwise, return -1. 

 
 Example 1: 

 
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce 
x to zero.
 

 Example 2: 

 
Input: nums = [5,6,7,8,9], x = 4
Output: -1
 

 Example 3: 

 
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the 
first two elements (5 operations in total) to reduce x to zero.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 1 <= nums[i] <= 10⁴ 
 1 <= x <= 10⁹ 
 

 Related Topics Array Hash Table Binary Search Sliding Window Prefix Sum 👍 5309
 👎 113

*/
