package leetcode.question.binary_search;

/**
 *@Question:  611. Valid Triangle Number
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.25%
 *@Time  Complexity: O(N^2 * logN)
 *@Space Complexity: O(logN)
 */

/**
 * ### 解题思路
 *
 * 题目要求找出数组中能够组成三角形的三元组的数量。为了解决这个问题，我们可以利用排序和双指针的方法来解决。
 *
 * #### 步骤详解
 *
 * 1. **排序**：
 *    - 首先，对数组进行排序，将数组从小到大排列。
 *
 * 2. **三角形条件**：
 *    - 对于三角形来说，任意两边之和大于第三边。
 *    - 在排序后的数组中，我们固定一个较小的边 `nums[i]`，然后通过双指针来找到第二个较大的边 `nums[j]` 和第三个较大的边 `nums[k]`。
 *    - 如果 `nums[j] + nums[k] > nums[i]` 成立，那么就说明可以组成三角形，此时 `j` 和 `k` 之间的所有数都满足这个条件。
 *    - 我们用 `count` 来记录这个符合条件的数量。
 *
 * 3. **双指针**：
 *    - 我们使用双指针来遍历数组，保证第一个较小的边 `nums[i]`。
 *    - 从当前位置 `i` 开始，初始化第三个较大的边 `k` 为 `i + 2`，然后通过二分搜索找到第一个大于等于 `nums[i] + nums[j]` 的位置，并将符合条件的数量加到 `count` 中。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 排序的时间复杂度为 `O(nlogn)`，其中 `n` 是数组的长度。
 * - 在最坏情况下，双指针遍历数组的时间复杂度为 `O(n^2)`，因为每个指针最多遍历一次数组。
 *
 * #### 空间复杂度
 * - 排序的空间复杂度为 `O(logn)`，由于常数级的额外空间使用很少，我们可以忽略不计。
 */
public class LeetCode_611_ValidTriangleNumber{

//leetcode submit region begin(Prohibit modification and deletion)

    // 定义Solution类
    public class Solution {
        // 定义binarySearch方法，用于在有序数组中查找第一个大于等于x的元素的位置
        int binarySearch(int nums[], int l, int r, int x) {
            while (r >= l && r < nums.length) {
                int mid = (l + r) / 2; // 计算中间元素的索引
                if (nums[mid] >= x)
                    r = mid - 1; // 如果中间元素大于等于x，调整r的值为mid-1
                else
                    l = mid + 1; // 如果中间元素小于x，调整l的值为mid+1
            }
            return l; // 返回l的值，即第一个大于等于x的元素的位置
        }
        // 定义triangleNumber方法，用于计算可以组成三角形的三元组的数量
        public int triangleNumber(int[] nums) {
            int count = 0; // 初始化计数器为0
            Arrays.sort(nums); // 对数组进行排序
            for (int i = 0; i < nums.length - 2; i++) { // 遍历数组，保证第一个较小的边
                int k = i + 2; // 初始化第三个边的索引
                for (int j = i + 1; j < nums.length - 1 && nums[i] != 0; j++) { // 遍历数组，保证第二个较长的边，并且第一个边不为0
                    k = binarySearch(nums, k, nums.length - 1, nums[i] + nums[j]); // 在剩余部分中找到第一个大于等于nums[i] + nums[j]的位置
                    count += k - j - 1; // 更新计数器
                }
            }
            return count; // 返回计数器的值，即可以组成三角形的三元组的数量
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_611_ValidTriangleNumber().new Solution();
        // TO TEST
        //solution.
    }
}

/**
Given an integer array nums, return the number of triplets chosen from the 
array that can make triangles if we take them as side lengths of a triangle. 

 
 Example 1: 

 
Input: nums = [2,2,3,4]
Output: 3
Explanation: Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
 

 Example 2: 

 
Input: nums = [4,2,3,4]
Output: 4
 

 
 Constraints: 

 
 1 <= nums.length <= 1000 
 0 <= nums[i] <= 1000 
 

 Related Topics Array Two Pointers Binary Search Greedy Sorting 👍 3789 👎 215

*/