package leetcode.frequent.two_pointer;

/**
  *@Question:  1004. Max Consecutive Ones III     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 60.870000000000005%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

/**
 * **解题思路：**
 *
 * 这个问题可以通过滑动窗口的方法来解决。我们维护一个窗口，其中包含最多 `k` 个0，以及在窗口内的1的个数。
 * 我们用 `left` 和 `right` 两个指针来表示窗口的左右边界。
 *
 * 具体步骤如下：
 *
 * 1. 初始化 `left` 和 `right` 指针都指向数组的起始位置，表示窗口的左右边界。
 * 2. 当 `right` 指针向右移动时，如果遇到0，则将 `k` 的值减1。
 * 3. 当 `k` 小于0时，表示窗口内的0个数超过了允许的最大值，此时需要移动 `left` 指针，将窗口缩小。
 * 4. 在移动 `left` 指针的过程中，如果遇到的是0，增加 `k` 的值，表示将0变为1，从而继续保持窗口的有效性。
 * 5. 在整个过程中，不断更新窗口的最大长度，即 `right - left` 的值。
 * 6. 最终返回窗口的最大长度作为结果。
 *
 * **时间复杂度：**
 *
 * - 时间复杂度是 O(N)，其中 N 表示数组的长度。由于我们只对数组进行了一次遍历，因此时间复杂度是线性的。
 *
 * **空间复杂度：**
 *
 * - 空间复杂度是 O(1)，因为我们只使用了常数个额外的变量，没有使用额外的数据结构。
 */

public class LeetCode_1004_MaxConsecutiveOnesIii {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 求最大连续1的个数，允许最多进行k次0变1的翻转操作
         *
         * @param nums 给定由 0 和 1 组成的数组
         * @param k    允许进行的0变1的翻转次数
         * @return 返回最大连续1的个数
         */
        public int longestOnes(int[] nums, int k) {
            int left = 0, right;

            for (right = 0; right < nums.length; right++) {
                // 如果窗口中包含了一个0，我们减小k的值，因为k是窗口中允许的最大0的个数
                if (nums[right] == 0) {
                    k--;
                }

                // 当k小于0时，表示我们已经使用了所有允许的翻转次数，并且窗口中的0个数超过了允许的最大个数
                // 此时，增加left指针，保持窗口的大小不变
                if (k < 0) {
                    // 如果要排除的左边元素是0，我们增加k的值
                    k += 1 - nums[left];
                    left++;
                }
            }

            // 返回最终窗口的大小（右指针位置减去左指针位置）
            return right - left;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    // 测试代码
    public static void main(String[] args) {
        Solution solution = new LeetCode_1004_MaxConsecutiveOnesIii().new Solution();

        // 测试用例
        int[] nums1 = {1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1};
        int k1 = 2;
        System.out.println("Test Case 1: " + solution.longestOnes(nums1, k1));  // 预期输出：10

        int[] nums2 = {0, 0, 0, 1};
        int k2 = 4;
        System.out.println("Test Case 2: " + solution.longestOnes(nums2, k2));  // 预期输出：4
    }
}

/**
Given a binary array nums and an integer k, return the maximum number of 
consecutive 1's in the array if you can flip at most k 0's. 

 
 Example 1: 

 
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined. 

 Example 2: 

 
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 nums[i] is either 0 or 1. 
 0 <= k <= nums.length 
 

 Related Topics Array Binary Search Sliding Window Prefix Sum 👍 7947 👎 105

*/
