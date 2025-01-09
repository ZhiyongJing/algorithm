package leetcode.question.binary_search;

/**
 *@Question:  852. Peak Index in a Mountain Array
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 71.48%
 *@Time Complexity: O(logN)
 *@Space Complexity: O(1)
 */
/**
 * 题目：852. Peak Index in a Mountain Array
 *
 * 题目描述：
 * 给定一个山脉数组（严格递增然后严格递减），要求找到峰值元素的索引。数组中必定存在一个峰值元素，并且该峰值元素比其左右的元素都大。
 * 你可以使用二分查找来解决这个问题。
 *
 * 解题思路：
 * 1. 山脉数组的特点是：数组的元素先递增，再递减。也就是说在某个点之前，元素一直递增，之后递减。我们要做的就是找到这个递增递减转折点的位置（即峰值）。
 * 2. 使用二分查找的思想来优化查找过程：
 *    - 通过比较中间元素与其右侧的元素，判断峰值所在的区间。
 *    - 如果 `arr[mid] < arr[mid + 1]`，说明峰值在右侧，移动左指针到 `mid + 1`，继续查找右边。
 *    - 如果 `arr[mid] > arr[mid + 1]`，说明峰值在左侧或就是中间值，移动右指针到 `mid`。
 * 3. 二分查找的结束条件是左指针和右指针重合，此时即为峰值的索引。
 *
 * 时间复杂度：
 * - 每次通过二分查找缩小查找范围，所以时间复杂度为 O(logN)，其中 N 是数组的长度。
 *
 * 空间复杂度：
 * - 该算法只用了常数级别的空间（除了输入数组和返回值），所以空间复杂度为 O(1)。
 *
 * 具体步骤：
 * 1. 初始化左右指针 `l = 0` 和 `r = arr.length - 1`。
 * 2. 使用二分查找的方法，循环判断当前的中间元素 `mid`：
 *    - 如果 `arr[mid] < arr[mid + 1]`，将左指针移到 `mid + 1`，继续查找右边。
 *    - 否则，将右指针移到 `mid`，继续查找左边。
 * 3. 当 `l == r` 时，结束查找，返回 `l`（即峰值的索引）。
 *
 * 示例：
 * 1. 输入：[0, 2, 3, 4, 5, 3, 1]，输出：4（峰值是 5，索引为 4）
 * 2. 输入：[0, 10, 5, 2]，输出：1（峰值是 10，索引为 1）
 *
 * 时间复杂度：O(logN)
 * 空间复杂度：O(1)
 */


public class LeetCode_852_PeakIndexInAMountainArray{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法：使用二分查找找到山脉数组中的峰值索引
        public int peakIndexInMountainArray(int[] arr) {
            int l = 0, r = arr.length - 1, mid; // 初始化左右指针 l 和 r，mid 是中间点

            // 使用二分查找，直到 l 和 r 重合
            while (l < r) {
                mid = (l + r) / 2; // 计算中间位置

                // 如果当前中间值小于其右侧的值，说明峰值在右边
                if (arr[mid] < arr[mid + 1])
                    l = mid + 1; // 移动左指针到中间点的右边
                else
                    r = mid; // 如果中间值大于等于右侧值，说明峰值在左边或就是中间值，移动右指针到中间点
            }

            // 返回 l，即为峰值所在的索引
            return l;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_852_PeakIndexInAMountainArray().new Solution();

        // 测试样例 1
        int[] arr1 = {0, 2, 3, 4, 5, 3, 1}; // 峰值是5，索引为4
        System.out.println(solution.peakIndexInMountainArray(arr1)); // 输出 4

        // 测试样例 2
        int[] arr2 = {0, 10, 5, 2}; // 峰值是10，索引为1
        System.out.println(solution.peakIndexInMountainArray(arr2)); // 输出 1
    }
}

/**
You are given an integer mountain array arr of length n where the values 
increase to a peak element and then decrease. 

 Return the index of the peak element. 

 Your task is to solve it in O(log(n)) time complexity. 

 
 Example 1: 

 
 Input: arr = [0,1,0] 
 

 Output: 1 

 Example 2: 

 
 Input: arr = [0,2,1,0] 
 

 Output: 1 

 Example 3: 

 
 Input: arr = [0,10,5,2] 
 

 Output: 1 

 
 Constraints: 

 
 3 <= arr.length <= 10⁵ 
 0 <= arr[i] <= 10⁶ 
 arr is guaranteed to be a mountain array. 
 

 Related Topics Array Binary Search 👍 7775 👎 1928

*/