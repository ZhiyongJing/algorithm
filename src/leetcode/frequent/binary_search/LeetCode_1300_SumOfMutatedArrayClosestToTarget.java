package leetcode.frequent.binary_search;

/**
  *@Question:  1300. Sum of Mutated Array Closest to Target     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 14.35%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * **算法思路：**
 *
 * 1. 先计算数组的总和 `sum` 和数组中的最大值 `max`。
 *
 * 2. 如果数组总和 `sum` 等于目标值 `target`，直接返回数组中的最大值 `max`，因为不需要进行二分查找，数组的总和已经等于目标值。
 *
 * 3. 使用二分查找，寻找在 `[0, max]` 范围内最接近目标值的最佳整数值。初始化二分查找的范围为 `min = 0` 和 `max = max`。
 *
 * 4. 在二分查找的过程中，计算当前整数值 `mid` 对数组的变异和 `getMutatedSum(arr, mid)`。
 *
 * 5. 根据计算的变异和与目标值的关系，更新二分查找的范围 `min` 和 `max`。如果变异和大于目标值，
 * 说明当前整数值 `mid` 太大，应该减小 `max`；如果变异和小于目标值，说明当前整数值 `mid` 太小，应该增大 `min`。
 *
 * 6. 同时，维护一个差值变量 `diff`，记录当前差值最小的整数值 `res`。如果当前变异和与目标值的差值小于 `diff`，
 * 或者差值等于 `diff` 但当前整数值 `mid` 更小，更新 `res` 和 `diff`。
 *
 * 7. 最终返回变异和最小的整数值 `res`。
 *
 * **时间复杂度：**
 *
 * 由于是二分查找，每次查找的时间复杂度为 O(log(max - min))，其中 `max` 是数组中的最大值，`min` 是数组中的最小值。
 * 在最坏情况下，需要进行 O(log(max)) 次二分查找。
 *
 * 每次二分查找都需要计算变异和，计算变异和的时间复杂度为 O(N)，其中 N 是数组的长度。
 *
 * 因此，总体时间复杂度为 O(N * log(max))。
 *
 * **空间复杂度：**
 *
 * 只使用了常量级别的额外空间，因此空间复杂度为 O(1)。
 */
public class LeetCode_1300_SumOfMutatedArrayClosestToTarget {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findBestValue(int[] arr, int target) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            // 计算数组总和和最大值
            for (int a : arr) {
                sum += a;
                max = Math.max(max, a);
            }

            // 如果数组总和等于目标值，直接返回数组中的最大值
            if (sum == target) return max;

            // 使用二分查找，找到最接近目标值的最佳整数值
            int min = 0, res = 1, diff = Integer.MAX_VALUE;

            // 二分查找的范围是从0到数组中的最大值
            while (min <= max) {
                int mid = min + (max - min) / 2;
                sum = getMutatedSum(arr, mid);

                // 根据当前计算的和与目标值的关系，更新二分查找的范围
                if (sum > target) {
                    max = mid - 1;
                } else {
                    min = mid + 1;
                }

                // 如果当前差值小于之前记录的差值，或者当前差值等于之前记录的差值但 mid 更小，更新结果和差值
                if (Math.abs(sum - target) < diff || (Math.abs(sum - target) == diff && mid < res)) {
                    res = mid;
                    diff = Math.abs(sum - target);
                }
            }

            return res;
        }

        // 计算数组中小于等于给定值的元素的和
        private int getMutatedSum(int[] arr, int mid) {
            int sum = 0;
            for (int a : arr) {
                sum += (a > mid) ? mid : a;
            }
            return sum;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_1300_SumOfMutatedArrayClosestToTarget.Solution solution =
                new LeetCode_1300_SumOfMutatedArrayClosestToTarget().new Solution();

        // 测试代码
        int[] arr1 = {4, 9, 3};
        int target1 = 10;
        System.out.println(solution.findBestValue(arr1, target1));  // Output: 3

        int[] arr2 = {2, 3, 5};
        int target2 = 10;
        System.out.println(solution.findBestValue(arr2, target2));  // Output: 5
    }
}

/**
Given an integer array arr and a target value target, return the integer value 
such that when we change all the integers larger than value in the given array 
to be equal to value, the sum of the array gets as close as possible (in absolute 
difference) to target. 

 In case of a tie, return the minimum such integer. 

 Notice that the answer is not neccesarilly a number from arr. 

 
 Example 1: 

 
Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the 
optimal answer.
 

 Example 2: 

 
Input: arr = [2,3,5], target = 10
Output: 5
 

 Example 3: 

 
Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361
 

 
 Constraints: 

 
 1 <= arr.length <= 10⁴ 
 1 <= arr[i], target <= 10⁵ 
 

 Related Topics Array Binary Search Sorting 👍 1097 👎 142

*/
