package leetcode.question.binary_search;

/**
  *@Question:  1095. Find in Mountain Array     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 67.81%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

/**
 * ==============================
 * 题目描述：LeetCode 1095 - Find in Mountain Array
 * ==============================
 * 给定一个 **山脉数组**（Mountain Array），请查找目标值 `target` 的索引位置。
 *
 * **山脉数组的定义：**
 * - 一个山脉数组满足以下条件：
 *   1. 数组中有一个**峰值元素**，峰值前的部分是**严格递增的**。
 *   2. 峰值后的部分是**严格递减的**。
 *
 * **输入限制：**
 * - 不能直接访问整个数组，只能通过 `MountainArray` 接口访问每个元素。
 * - 必须通过调用 `MountainArray.get(index)` 来获取索引位置的值。
 * - 数组长度在 `[3, 10000]` 范围内。
 *
 * **目标：**
 * - 如果目标值 `target` 存在于山脉数组中，返回其索引位置。
 * - 如果目标值不存在，返回 `-1`。
 *
 * **示例：**
 * 输入：`mountainArr = [1, 2, 3, 4, 5, 3, 1]`，`target = 3`
 * 输出：2 或 5
 * 解释：目标值 3 在索引 2 和 5 位置都出现，可以返回任意一个。
 */

/**
 * ==============================
 * 解题思路：
 * ==============================
 * **核心思路：二分查找**
 * - 山脉数组由两个部分组成：**严格递增部分**和**严格递减部分**。
 * - 可以利用二分查找找到**峰值索引**，然后分别在**递增部分**和**递减部分**进行二分查找目标值。
 *
 * ------------------------------
 * **解法步骤：**
 * ------------------------------
 * **第一步：找到峰值索引**
 * - 峰值是数组中最大的元素。
 * - 使用二分查找找到峰值索引：
 *   - 如果 `nums[mid] < nums[mid + 1]`，说明峰值在右侧，将左指针移动到 `mid + 1`。
 *   - 否则，将右指针移动到 `mid - 1`。
 *
 * **示例：**
 * 输入：`mountainArr = [1, 2, 3, 4, 5, 3, 1]`
 * - 初始状态：`low = 1`，`high = 5`
 * - 第一次循环：`mid = 3`，`nums[3] = 4`，`nums[4] = 5`，峰值在右侧，移动 `low = 4`。
 * - 第二次循环：`mid = 4`，`nums[4] = 5`，`nums[5] = 3`，峰值在左侧，移动 `high = 4`。
 * - 最终峰值索引：`low = 4`。
 *
 * ------------------------------
 * **第二步：在递增部分进行二分查找**
 * - 在索引范围 `[0, peakIndex]` 内进行二分查找。
 * - 如果找到目标值，返回对应索引。
 * - 如果没有找到，继续进行第三步。
 *
 * **示例：**
 * 输入：`mountainArr = [1, 2, 3, 4, 5, 3, 1]`，`target = 3`
 * - 在递增部分二分查找：
 *   - 初始状态：`low = 0`，`high = 4`。
 *   - 第一次循环：`mid = 2`，`nums[2] = 3`，找到目标值，返回索引 2。
 *
 * ------------------------------
 * **第三步：在递减部分进行二分查找**
 * - 如果目标值不在递增部分，则在索引范围 `[peakIndex + 1, length - 1]` 内进行二分查找。
 * - 因为递减部分是倒序的，因此需要反向比较。
 *
 * **示例：**
 * 输入：`mountainArr = [1, 2, 3, 4, 5, 3, 1]`，`target = 3`
 * - 在递减部分二分查找：
 *   - 初始状态：`low = 5`，`high = 6`。
 *   - 第一次循环：`mid = 5`，`nums[5] = 3`，找到目标值，返回索引 5。
 */

/**
 * ==============================
 * 时间和空间复杂度分析：
 * ==============================
 * **时间复杂度：O(log N)**
 * - 找峰值索引的二分查找需要 O(log N) 时间。
 * - 递增部分和递减部分的二分查找也分别需要 O(log N) 时间。
 * - 总体时间复杂度为 O(log N)。
 *
 * **空间复杂度：O(1)**
 * - 只使用了常数级别的额外空间，没有使用额外的数据结构。
 */

public class LeetCode_1095_FindInMountainArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // 这是 MountainArray 的 API 接口。
     * // 你不应该实现它，或者对它进行假设
     * interface MountainArray {
     *     public int get(int index) {}
     *     public int length() {}
     * }
     */

    class Solution {
        /**
         * 在山脉数组中查找目标值
         *
         * @param target      目标值
         * @param mountainArr 山脉数组接口
         * @return 目标值的索引，如果不存在返回 -1
         */
        public int findInMountainArray(int target, MountainArray mountainArr) {
            // 保存山脉数组的长度
            int length = mountainArr.length();

            // 第一步：找到山脉数组的峰值索引
            // 峰值是数组中最大的元素，位于数组的严格递增部分和严格递减部分之间
            int low = 1; // 峰值不会是第一个或最后一个元素，因此从索引 1 开始
            int high = length - 2; // 同理，结束位置为 length - 2
            while (low <= high) {
                int testIndex = (low + high) / 2; // 取中间索引
                // 如果当前元素小于右边的元素，说明峰值在右侧
                if (mountainArr.get(testIndex) < mountainArr.get(testIndex + 1)) {
                    low = testIndex + 1;
                } else { // 否则，峰值在左侧
                    high = testIndex - 1;
                }
            }
            // 找到峰值的索引
            int peakIndex = low;

            // 第二步：在数组的严格递增部分进行二分查找
            low = 0;
            high = peakIndex;
            while (low != high) {
                int testIndex = (low + high) / 2;
                // 如果当前值小于目标值，移动左指针
                if (mountainArr.get(testIndex) < target) {
                    low = testIndex + 1;
                } else {
                    high = testIndex;
                }
            }
            // 检查目标值是否存在于严格递增部分
            if (mountainArr.get(low) == target) {
                return low;
            }

            // 第三步：如果目标值不在递增部分，在严格递减部分进行二分查找
            low = peakIndex + 1;
            high = length - 1;
            while (low != high) {
                int testIndex = (low + high) / 2;
                // 如果当前值大于目标值，移动左指针
                if (mountainArr.get(testIndex) > target) {
                    low = testIndex + 1;
                } else {
                    high = testIndex;
                }
            }
            // 检查目标值是否存在于严格递减部分
            if (mountainArr.get(low) == target) {
                return low;
            }

            // 如果目标值不存在于山脉数组中，返回 -1
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1095_FindInMountainArray().new Solution();

        // 测试代码
        // 创建一个测试山脉数组
        int[] array = {1, 2, 3, 4, 5, 3, 1};
        int target = 3;

        // 输出目标值的索引位置
        System.out.println(solution.findInMountainArray(target, new MountainArray(array)));  // 预期输出：2
    }

    // 测试用的 MountainArray 实现
    static class MountainArray // implements MountainArray
    {
        int[] array;

        // 构造方法，传入数组
        MountainArray(int[] array) {
            this.array = array;
        }

        // 获取指定索引的值
        public int get(int index) {
            return array[index];
        }

        // 返回数组的长度
        public int length() {
            return array.length;
        }
    }
}


/**
(This problem is an interactive problem.) 

 You may recall that an array arr is a mountain array if and only if: 

 
 arr.length >= 3 
 There exists some i with 0 < i < arr.length - 1 such that: 
 
 arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
 arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 
 
 

 Given a mountain array mountainArr, return the minimum index such that 
mountainArr.get(index) == target. If such an index does not exist, return -1. 

 You cannot access the mountain array directly. You may only access the array 
using a MountainArray interface: 

 
 MountainArray.get(k) returns the element of the array at index k (0-indexed). 
 MountainArray.length() returns the length of the array. 
 

 Submissions making more than 100 calls to MountainArray.get will be judged 
Wrong Answer. Also, any solutions that attempt to circumvent the judge will result 
in disqualification. 

 
 Example 1: 

 
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum 
index, which is 2. 

 Example 2: 

 
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
 

 
 Constraints: 

 
 3 <= mountain_arr.length() <= 10⁴ 
 0 <= target <= 10⁹ 
 0 <= mountain_arr.get(index) <= 10⁹ 
 

 Related Topics Array Binary Search Interactive 👍 3085 👎 125

*/
