package leetcode.question.binary_search;

import java.util.Arrays;
import java.util.HashMap;

/**
 *@Question:  350. Intersection of Two Arrays II
 *@Difficulty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.78%
 *@Time Complexity: O(n + m) for solution1, O(nlogN + mlogM) for solution2
 *@Space Complexity: O(min(n, m)) for solution1; from O(logn+logm) to O(n+m) for solution2, depending on the implementation of the sorting algorithm.
 */
/**
 * 题目描述：
 * LeetCode 350. Intersection of Two Arrays II
 * 给定两个整数数组 `nums1` 和 `nums2`，返回 **它们的交集**（即两个数组中都出现的元素）。
 * - 交集中每个元素的出现次数应等于该元素在两个数组中出现次数的最小值。
 * - 结果可以是任意顺序返回。
 *
 * **示例 1：**
 * ```
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * ```
 *
 * **示例 2：**
 * ```
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9] 或 [9,4] （顺序无关）
 * ```
 *
 * **示例 3：**
 * ```
 * 输入: nums1 = [1,2,3,4,5], nums2 = [6,7,8,9]
 * 输出: []
 * ```
 */

/**
 * 解题思路：
 * **方法 1：哈希表**
 * 1. **使用 HashMap 统计较短数组的元素频次**
 *    - 选择较短的数组 `nums1` 作为基准（减少空间消耗）。
 *    - 遍历 `nums1`，用 `HashMap<Integer, Integer>` 记录每个元素的出现次数。
 * 2. **遍历 `nums2`，找到交集**
 *    - 遍历 `nums2`，如果当前元素在 `HashMap` 中且次数 > 0，则添加到结果数组，并减少该元素的计数。
 *    - 这样保证了交集中元素的出现次数等于两个数组中最小的出现次数。
 * 3. **返回结果**
 *    - 由于交集的大小未知，使用 `nums1` 作为结果数组（因为 `nums1` 更短）。
 *    - 使用 `Arrays.copyOfRange(nums1, 0, k)` 截取有效部分返回结果。
 *
 * **举例分析**
 * ```
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 1. 统计 nums1:
 *    HashMap: {1:2, 2:2}
 * 2. 遍历 nums2:
 *    - 2 在 HashMap 中且次数 > 0，添加到结果，更新 {1:2, 2:1}
 *    - 2 在 HashMap 中且次数 > 0，添加到结果，更新 {1:2, 2:0}
 * 3. 结果: [2,2]
 * ```
 *
 * ---
 *
 * **方法 2：排序 + 双指针**
 * 1. **排序两个数组**
 *    - 先对 `nums1` 和 `nums2` 进行排序（`O(n log n) + O(m log m)`）。
 * 2. **使用双指针 `i, j` 遍历两个数组**
 *    - 设 `i = 0, j = 0`，初始指向 `nums1` 和 `nums2` 的开头。
 *    - 当 `nums1[i] == nums2[j]`，说明找到交集元素，添加到结果，`i++`，`j++`。
 *    - 当 `nums1[i] < nums2[j]`，说明 `nums1[i]` 不在交集中，`i++`。
 *    - 当 `nums1[i] > nums2[j]`，说明 `nums2[j]` 不在交集中，`j++`。
 * 3. **返回结果**
 *    - 结果数组存储在 `nums1` 中，`Arrays.copyOfRange(nums1, 0, k)` 截取有效部分返回。
 *
 * **举例分析**
 * ```
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 1. 排序:
 *    nums1 = [4,5,9]
 *    nums2 = [4,4,8,9,9]
 * 2. 双指针遍历:
 *    i=0, j=0 → nums1[0]=4, nums2[0]=4 (交集) → 添加 4 → i++, j++
 *    i=1, j=1 → nums1[1]=5, nums2[1]=4 → j++
 *    i=1, j=2 → nums1[1]=5, nums2[2]=8 → i++
 *    i=2, j=3 → nums1[2]=9, nums2[3]=9 (交集) → 添加 9 → i++, j++
 *    i=3, j=4 → 结束
 * 3. 结果: [4,9]
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * **方法 1：哈希表**
 * 1. **时间复杂度：O(n + m)**
 *    - 遍历 `nums1` 统计频次 `O(n)`。
 *    - 遍历 `nums2` 找交集 `O(m)`。
 *    - **总时间复杂度：O(n + m)**。
 *
 * 2. **空间复杂度：O(min(n, m))**
 *    - 哈希表存储 `nums1` 的元素，最坏情况下 `O(min(n, m))`。
 *    - **总空间复杂度：O(min(n, m))**。
 *
 * ---
 *
 * **方法 2：排序 + 双指针**
 * 1. **时间复杂度：O(n log n + m log m)**
 *    - 对 `nums1` 排序 `O(n log n)`，对 `nums2` 排序 `O(m log m)`。
 *    - 双指针遍历 `O(n + m)`。
 *    - **总时间复杂度：O(n log n + m log m)**。
 *
 * 2. **空间复杂度：O(1) ~ O(n + m)**
 *    - 取决于排序算法：
 *      - 若使用 **原地排序**（如快速排序），额外空间 `O(1)`。
 *      - 若排序算法需要额外存储，可能达到 `O(n + m)`。
 *    - **最佳情况下 `O(1)`，最坏情况下 `O(n + m)`**。
 */
/**
 * **为什么 `nums1[k++] = nums1[i++]; ++j;` 这样写？**
 *
 * 该代码出现在 **方法 2：排序 + 双指针** 中：
 * ```
 * while (i < nums1.length && j < nums2.length) {
 *     if (nums1[i] < nums2[j]) {
 *         ++i;
 *     } else if (nums1[i] > nums2[j]) {
 *         ++j;
 *     } else {
 *         nums1[k++] = nums1[i++];
 *         ++j;
 *     }
 * }
 * ```
 *
 * **作用：**
 * 1. 当 `nums1[i] == nums2[j]`，说明 `nums1[i]` **属于交集**，需要添加到结果数组中。
 * 2. `nums1[k++] = nums1[i++];`：
 *    - `nums1[i]` 是当前匹配的交集元素，将其存入 `nums1[k]`（存储交集的数组）。
 *    - `k++` 让 `k` 指向下一个存储交集的位置。
 *    - `i++` 让 `i` 指向 `nums1` 的下一个元素，以继续遍历。
 * 3. `++j;`
 *    - `nums2[j]` 已经匹配过了，因此 `j++`，让 `j` 指向 `nums2` 的下一个元素。
 *
 * **为什么存入 `nums1[k]` 而不是新建数组？**
 * - 由于交集元素个数未知，使用 `nums1` 作为结果数组（`nums1` 至少能容纳 `min(n, m)` 个交集元素）。
 * - **最终使用 `Arrays.copyOfRange(nums1, 0, k)` 提取有效部分**，避免浪费空间。
 *
 * **举例分析**
 * **输入**:
 * ```
 * nums1 = [4,5,9]
 * nums2 = [4,4,8,9,9]
 * ```
 * **排序后**:
 * ```
 * nums1 = [4,5,9]
 * nums2 = [4,4,8,9,9]
 * ```
 * **执行过程**:
 * ```
 * i=0, j=0 → nums1[0] == nums2[0] → nums1[k++] = nums1[i++] = 4 → j++
 * i=1, j=1 → nums1[1] > nums2[1] → j++
 * i=1, j=2 → nums1[1] > nums2[2] → j++
 * i=1, j=3 → nums1[1] < nums2[3] → i++
 * i=2, j=3 → nums1[2] == nums2[3] → nums1[k++] = nums1[i++] = 9 → j++
 * ```
 * **最终 nums1 = [4,9]，返回交集部分**。
 *
 * **总结**
 * - `nums1[k++] = nums1[i++]` 记录交集元素，并前进 `i` 继续匹配下一个元素。
 * - `++j;` 让 `j` 也前进，避免重复匹配相同元素。
 * - 最终 `Arrays.copyOfRange(nums1, 0, k)` 提取交集并返回。
 */



public class LeetCode_350_IntersectionOfTwoArraysIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法1：使用哈希表存储较短数组的元素频次
        public int[] intersect(int[] nums1, int[] nums2) {
            // 确保 nums1 是较短的数组，以减少哈希表的空间使用
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }
            // 使用哈希表记录 nums1 中每个元素的出现次数
            HashMap<Integer, Integer> m = new HashMap<>();
            for (int n : nums1) {
                m.put(n, m.getOrDefault(n, 0) + 1);
            }
            int k = 0;
            // 遍历 nums2，检查是否在哈希表中，并减少对应元素的次数
            for (int n : nums2) {
                int cnt = m.getOrDefault(n, 0);
                if (cnt > 0) {
                    nums1[k++] = n; // 直接存入 nums1，后续裁剪
                    m.put(n, cnt - 1);
                }
            }
            // 返回交集部分
            return Arrays.copyOfRange(nums1, 0, k);
        }

        // 方法2：排序+双指针
        public int[] intersect2(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int i = 0, j = 0, k = 0;
            // 使用双指针遍历两个有序数组
            while (i < nums1.length && j < nums2.length) {
                if (nums1[i] < nums2[j]) {
                    ++i;
                } else if (nums1[i] > nums2[j]) {
                    ++j;
                } else {
                    nums1[k++] = nums1[i++];
                    ++j;
                }
            }
            // 返回交集部分
            return Arrays.copyOfRange(nums1, 0, k);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_350_IntersectionOfTwoArraysIi().new Solution();

        // 测试用例 1
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        System.out.println(Arrays.toString(solution.intersect(nums1, nums2)));
        // 期望输出: [2,2]

        // 测试用例 2
        int[] nums3 = {4,9,5};
        int[] nums4 = {9,4,9,8,4};
        System.out.println(Arrays.toString(solution.intersect(nums3, nums4)));
        // 期望输出: [4,9] 或 [9,4]，顺序无关

        // 测试用例 3（使用排序+双指针方法）
        int[] nums5 = {1,2,2,1};
        int[] nums6 = {2,2};
        System.out.println(Arrays.toString(solution.intersect2(nums5, nums6)));
        // 期望输出: [2,2]

        // 测试用例 4
        int[] nums7 = {1,2,3,4,5};
        int[] nums8 = {6,7,8,9};
        System.out.println(Arrays.toString(solution.intersect(nums7, nums8)));
        // 期望输出: [] (无交集)
    }
}

/**
Given two integer arrays nums1 and nums2, return an array of their intersection.
 Each element in the result must appear as many times as it shows in both 
arrays and you may return the result in any order. 

 
 Example 1: 

 
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
 

 Example 2: 

 
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
 

 
 Constraints: 

 
 1 <= nums1.length, nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 1000 
 

 
 Follow up: 

 
 What if the given array is already sorted? How would you optimize your 
algorithm? 
 What if nums1's size is small compared to nums2's size? Which algorithm is 
better? 
 What if elements of nums2 are stored on disk, and the memory is limited such 
that you cannot load all elements into the memory at once? 
 

 Related Topics Array Hash Table Two Pointers Binary Search Sorting 👍 7857 👎 9
89

*/