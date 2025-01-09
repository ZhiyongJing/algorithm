package leetcode.question.binary_search;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  658. Find K Closest Elements
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.9%
 *@Time  Complexity: O(log(n-k) + k)
 *@Space Complexity: O(k)
 */
/**
 ### 题目解析
 658. **Find K Closest Elements**
 给定一个排序数组 `arr`、一个整数 `k` 和一个目标值 `x`，要求返回数组中最接近 `x` 的 `k` 个元素。结果需要按照原数组的顺序返回。

 #### 示例
 **输入：**
 `arr = [1, 2, 3, 4, 5]`, `k = 4`, `x = 3`
 **输出：**
 `[1, 2, 3, 4]`

 **输入：**
 `arr = [1, 2, 3, 4, 5]`, `k = 2`, `x = -1`
 **输出：**
 `[1, 2]`

 ---

 ### 解题思路

 #### 1. 问题分析
 - 数组是**有序的**，可以利用二分查找快速定位结果。
 - 我们需要一个长度为 `k` 的连续子数组，其元素到目标值 `x` 的距离最小。
 - 对于距离相等的情况，优先选择数组中较小的元素。

 #### 2. 解决方案
 **核心思想：**
 使用**二分查找**确定长度为 `k` 的子数组的起始位置。

 **详细步骤：**
 1. **定义二分查找范围**：
 - 定义左右指针 `left` 和 `right`，初始值为 `0` 和 `arr.length - k`。
 - `right` 的上界为 `arr.length - k`，因为子数组长度为 `k`，起点最多是 `arr.length - k`。

 2. **执行二分查找**：
 - 计算中点 `mid`。
 - 比较 `x - arr[mid]` 和 `arr[mid + k] - x`：
 - 如果 `x - arr[mid] > arr[mid + k] - x`，说明距离 `x` 更远的元素在左侧，应右移窗口，更新 `left = mid + 1`。
 - 否则保持或左移窗口，更新 `right = mid`。

 3. **提取结果**：
 - 二分查找结束后，`left` 即为结果子数组的起始位置。
 - 返回从 `arr[left]` 开始的 `k` 个元素。

 ---

 ### 时间复杂度分析

 1. **二分查找复杂度**：
 - 二分查找的范围是 `[0, arr.length - k]`，长度为 `n - k`。
 - 因此二分查找的时间复杂度为 \( O(\log(n - k)) \)。

 2. **结果提取复杂度**：
 - 提取 `k` 个连续元素，复杂度为 \( O(k) \)。

 **总时间复杂度：**
 \( O(\log(n - k) + k) \)

 ---

 ### 空间复杂度分析
 1. 返回的结果列表需要占用 \( O(k) \) 的空间。
 2. 二分查找在原数组上操作，没有额外空间开销。

 **总空间复杂度：**
 \( O(k) \)

 ---

 ### 总结
 - **方法**：二分查找 + 连续子数组提取
 - **时间复杂度**：\( O(\log(n - k) + k) \)
 - **空间复杂度**：\( O(k) \)

 这种方法利用了数组的有序性，用二分查找快速定位子数组的起点，然后提取结果，是非常高效的解决方式。
 */


public class LeetCode_658_FindKClosestElements {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // 初始化二分查找的左边界和右边界
            int left = 0;
            int right = arr.length - k; // 确保窗口大小为 k

            // 使用二分查找来定位最接近的 k 个元素的起始位置
            while (left < right) {
                int mid = (left + right) / 2; // 计算中间位置
                // 判断 x 到 arr[mid] 和 arr[mid + k] 的距离大小
                if (x - arr[mid] > arr[mid + k] - x) {
                    // 如果左侧距离更远，说明需要向右移动窗口
                    left = mid + 1;
                } else {
                    // 否则保持当前窗口或向左收缩右边界
                    right = mid;
                }
            }

            // 创建结果列表，将从 `left` 开始的 k 个元素加入结果
            List<Integer> result = new ArrayList<>();
            for (int i = left; i < left + k; i++) {
                result.add(arr[i]); // 将窗口内的元素添加到结果中
            }

            return result; // 返回结果列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_658_FindKClosestElements().new Solution();
        // 测试样例 1
        int[] arr1 = {1, 2, 3, 4, 5};
        int k1 = 4;
        int x1 = 3;
        System.out.println(solution.findClosestElements(arr1, k1, x1));
        // 输出: [1, 2, 3, 4]

        // 测试样例 2
        int[] arr2 = {1, 2, 3, 4, 5};
        int k2 = 4;
        int x2 = -1;
        System.out.println(solution.findClosestElements(arr2, k2, x2));
        // 输出: [1, 2, 3, 4]

        // 测试样例 3
        int[] arr3 = {1, 2, 3, 4, 5};
        int k3 = 2;
        int x3 = 4;
        System.out.println(solution.findClosestElements(arr3, k3, x3));
        // 输出: [3, 4]
    }
}

/**
Given a sorted integer array arr, two integers k and x, return the k closest 
integers to x in the array. The result should also be sorted in ascending order. 

 An integer a is closer to x than an integer b if: 

 
 |a - x| < |b - x|, or 
 |a - x| == |b - x| and a < b 
 

 
 Example 1: 

 
 Input: arr = [1,2,3,4,5], k = 4, x = 3 
 

 Output: [1,2,3,4] 

 Example 2: 

 
 Input: arr = [1,1,2,3,4,5], k = 4, x = -1 
 

 Output: [1,1,2,3] 

 
 Constraints: 

 
 1 <= k <= arr.length 
 1 <= arr.length <= 10⁴ 
 arr is sorted in ascending order. 
 -10⁴ <= arr[i], x <= 10⁴ 
 

 Related Topics Array Two Pointers Binary Search Sliding Window Sorting Heap (
Priority Queue) 👍 8442 👎 746

*/