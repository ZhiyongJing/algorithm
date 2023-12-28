package leetcode.frequent.sort;

/**
  *@Question:  75. Sort Colors     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.74%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * 解题思路：
 * 该问题采用的是荷兰国旗问题的解决思路，即通过三个指针来遍历数组，分别表示当前位置前面全是0的区域（p0），
 * 当前位置后面全是2的区域（p2），以及当前位置是1的区域。
 *
 * 1. 初始化指针：
 *    - p0 = 0：表示数组的开始，p0 及其之前的位置全是0。
 *    - curr = 0：当前遍历的位置，遍历整个数组。
 *    - p2 = nums.length - 1：表示数组的末尾，p2 及其之后的位置全是2。
 *
 * 2. 遍历数组：
 *    - 若 nums[curr] 等于 0，则交换 nums[p0] 和 nums[curr] 的值，然后将 p0 和 curr 各自向后移动一步。
 *    - 若 nums[curr] 等于 2，则交换 nums[curr] 和 nums[p2] 的值，然后将 p2 向前移动一步。
 *    - 若 nums[curr] 等于 1，则表示当前位置已经是正确的位置，直接将 curr 向后移动一步。
 *
 * 3. 重复上述步骤，直到 curr 超过 p2。这样，数组就被分为三个部分，p0 及其之前全是0，p2 及其之后全是2，而1的部分自然就在中间。
 *
 * 时间复杂度：
 * 遍历数组一次，因此时间复杂度为 O(N)，其中 N 为数组的长度。
 *
 * 空间复杂度：
 * 只使用了常量级的额外空间，即三个指针和一个临时变量，因此空间复杂度为 O(1)。
 */

public class LeetCode_75_SortColors {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /*
        荷兰国旗问题解决方案。
        */
        public void sortColors(int[] nums) {
            // 对于所有索引 < i：nums[idx < i] = 0
            // j 是考虑元素的索引
            int p0 = 0, curr = 0;

            // 对于所有索引 > k：nums[idx > k] = 2
            int p2 = nums.length - 1;

            int tmp;
            while (curr <= p2) {
                if (nums[curr] == 0) {
                    // 交换第 p0 个和第 curr 个元素
                    // i++ 和 j++
                    tmp = nums[p0];
                    nums[p0++] = nums[curr];
                    nums[curr++] = tmp;
                } else if (nums[curr] == 2) {
                    // 交换第 k 个和第 curr 个元素
                    // p2--
                    tmp = nums[curr];
                    nums[curr] = nums[p2];
                    nums[p2--] = tmp;
                } else {
                    curr++;
                }
            }
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_75_SortColors().new Solution();

        // 测试用例 1
        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums1);
        System.out.print("Test Case 1: ");
        printArray(nums1);

        // 测试用例 2
        int[] nums2 = {2, 0, 1};
        solution.sortColors(nums2);
        System.out.print("Test Case 2: ");
        printArray(nums2);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

/**
Given an array nums with n objects colored red, white, or blue, sort them in-
place so that objects of the same color are adjacent, with the colors in the order 
red, white, and blue. 

 We will use the integers 0, 1, and 2 to represent the color red, white, and 
blue, respectively. 

 You must solve this problem without using the library's sort function. 

 
 Example 1: 

 
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
 

 Example 2: 

 
Input: nums = [2,0,1]
Output: [0,1,2]
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 300 
 nums[i] is either 0, 1, or 2. 
 

 
 Follow up: Could you come up with a one-pass algorithm using only constant 
extra space? 

 Related Topics Array Two Pointers Sorting 👍 17248 👎 601

*/
