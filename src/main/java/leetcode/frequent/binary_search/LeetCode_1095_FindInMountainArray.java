package leetcode.frequent.binary_search;

/**
  *@Question:  1095. Find in Mountain Array     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 67.81%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

/**
 * 这个算法是在山脉数组中查找目标值的解决方案。下面是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. **寻找山脉数组的峰值：** 首先，使用二分查找的变种寻找山脉数组的峰值，也就是最大的元素。峰值的索引可以将数组分为两个部分，
 * 左侧是严格递增的部分，右侧是严格递减的部分。
 *
 * 2. **在严格递增的部分进行二分查找：** 使用二分查找在严格递增的部分寻找目标值。如果目标值存在于这一部分，返回其索引。
 *
 * 3. **在严格递减的部分进行二分查找：** 如果目标值不存在于严格递增的部分，那么就在严格递减的部分使用二分查找寻找目标值。
 * 如果目标值存在于这一部分，返回其索引。
 *
 * 4. **返回结果：** 如果整个过程中都未找到目标值，返回 -1。
 *
 * ### 时间复杂度：
 *
 * - 由于采用二分查找的变种，整个算法的时间复杂度为 O(logN)，其中 N 是山脉数组的长度。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_1095_FindInMountainArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // 这是MountainArray的API接口。
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
         * @param target       目标值
         * @param mountainArr  山脉数组接口
         * @return 目标值的索引，如果不存在返回-1
         */
        public int findInMountainArray(int target, MountainArray mountainArr) {
            // 保存山脉数组的长度
            int length = mountainArr.length();

            // 1. 寻找峰值的索引
            int low = 1;
            int high = length - 2;
            while (low <= high) {
                int testIndex = (low + high) / 2;
                if (mountainArr.get(testIndex) < mountainArr.get(testIndex + 1)) {
                    low = testIndex + 1;
                } else {
                    high = testIndex  - 1;
                }
            }
            int peakIndex = low;

            // 2. 在数组的严格递增部分进行二分搜索
            low = 0;
            high = peakIndex;
            while (low != high) {
                int testIndex = (low + high) / 2;
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

            // 3. 否则，在严格递减部分进行二分搜索
            low = peakIndex + 1;
            high = length - 1;
            while (low != high) {
                int testIndex = (low + high) / 2;
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

            // 目标值不存在于山脉数组中
            return -1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1095_FindInMountainArray().new Solution();

        // 测试代码
        // 注意：MountainArray 的实际实现应在测试时提供
        int[] array = {1, 2, 3, 4, 5, 3, 1};
        int target = 3;

        // 预期输出: 2
        System.out.println(solution.findInMountainArray(target, new MountainArray(array)));
    }

    // 测试用的 MountainArray 实现
    static class MountainArray //implements MountainArray
    {
        int[] array;

        MountainArray(int[] array) {
            this.array = array;
        }

//        @Override
        public int get(int index) {
            return array[index];
        }

//        @Override
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
