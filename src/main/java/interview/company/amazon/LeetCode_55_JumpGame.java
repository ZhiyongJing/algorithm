package interview.company.amazon;
//package leetcode.question.dp;

/**
 *@Question:  55. 跳跃游戏
 *@Difculty:  2 [1->简单, 2->中等, 3->困难]
 *@Frequency: 72.88%
 *@Time  Complexity: O(N^2) for solution1, O(N^2) for solution2
 *@Space Complexity: O(N) for solution1, O(N) for solution2
 */

/**
 * ### 问题概述
 *
 * 给定一个非负整数数组 `nums` ，数组中的每个元素表示你在该位置可以跳跃的最大长度。
 * 判断你是否能够从数组的第一个位置跳跃到最后一个位置。
 *
 * ### 解法1: 自顶向下的动态规划
 *
 * #### 思路
 *
 * 使用递归和记忆化搜索的方法，从数组的第一个位置开始判断是否能够跳跃到最后一个位置。定义 `Index` 枚举类型表示当前位置的状态，
 * 使用 `memo` 数组记录每个位置的状态，避免重复计算。
 *
 * #### 算法步骤
 *
 * 1. 定义 `Index` 枚举类型，包括 `GOOD`、`BAD` 和 `UNKNOWN`。
 * 2. 定义 `canJumpFromPosition` 方法，判断能否从当前位置跳跃到最后一个位置。
 * 3. 在 `canJumpFromPosition` 方法中，如果当前位置的状态已知，则直接返回该位置的状态；否则，
 * 计算当前位置能够跳跃的最远距离，并遍历可能的下一个位置，递归调用 `canJumpFromPosition` 方法。
 * 4. 在递归调用的过程中，将已经计算过的位置的状态存储在 `memo` 数组中，以避免重复计算。
 * 5. 在 `canJump` 方法中，初始化 `memo` 数组，然后调用 `canJumpFromPosition` 方法从数组的第一个位置开始判断。
 *
 * #### 复杂度分析
 *
 * - 时间复杂度：O(N^2)，其中 N 为数组的长度。因为每个位置都需要递归调用，最坏情况下需要计算所有可能的状态。
 * - 空间复杂度：O(N)，递归调用的深度为数组的长度，所以需要 O(N) 的额外空间。
 *
 * ### 解法2: 自底向上的动态规划
 *
 * #### 思路
 *
 * 使用动态规划的方法，从数组的最后一个位置开始逆向计算能够跳跃到当前位置的最远距离，同时更新当前位置的状态。
 * 最终判断数组的第一个位置是否可达。
 *
 * #### 算法步骤
 *
 * 1. 定义 `Index` 枚举类型，包括 `GOOD`、`BAD` 和 `UNKNOWN`。
 * 2. 初始化 `memo` 数组，最后一个位置总是可达，标记为 `GOOD`。
 * 3. 从数组的倒数第二个位置开始逆向遍历，计算当前位置能够跳跃的最远距离，同时更新当前位置的状态。
 * 4. 最终判断数组的第一个位置是否可达。
 *
 * #### 复杂度分析
 *
 * - 时间复杂度：O(N^2)，其中 N 为数组的长度。在最坏情况下，需要遍历每个位置并计算其能够跳跃到的最远距离。
 * - 空间复杂度：O(N)，需要 O(N) 的额外空间用于存储状态。

 *
 * 以上两种解法都能够正确判断是否能够从数组的第一个位置跳跃到最后一个位置。选择解法时可以根据实际情况，
 * 自顶向下的动态规划在状态转移方程中直接体现递归关系，而自底向上的动态规划从问题的最终状态开始逆向计算，一般来说更符合动态规划的套路。
 */

public class LeetCode_55_JumpGame{

//leetcode submit region begin(Prohibit modification and deletion)

    // 使用枚举类型表示当前位置的状态
    enum Index {
        GOOD, BAD, UNKNOWN
    }

    public class Solution {

        // Solution1: 自顶向下的动态规划
        Index[] memo;

        // 判断能否从当前位置跳跃到数组的最后一个位置
        public boolean canJumpFromPosition(int position, int[] nums) {
            // 如果当前位置的状态已知，直接返回结果
            if (memo[position] != Index.UNKNOWN) {
                return memo[position] == Index.GOOD ? true : false;
            }

            // 计算当前位置能够跳跃的最远距离
            int furthestJump = Math.min(position + nums[position], nums.length - 1);

            // 遍历可能的下一个位置
            for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
                // 如果从下一个位置可以跳跃到最后一个位置，将当前位置标记为GOOD并返回true
                if (canJumpFromPosition(nextPosition, nums)) {
                    memo[position] = Index.GOOD;
                    return true;
                }
            }

            // 如果无法跳跃到最后一个位置，将当前位置标记为BAD并返回false
            memo[position] = Index.BAD;
            return false;
        }

        // 判断是否能够跳跃到数组的最后一个位置
        public boolean canJump1(int[] nums) {
            // 初始化memo数组
            memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            // 数组的最后一个位置总是可达的
            memo[memo.length - 1] = Index.GOOD;
            // 从数组的第一个位置开始判断
            return canJumpFromPosition(0, nums);
        }

        // Solution2: 自底向上的动态规划
        public boolean canJump(int[] nums) {
            // 初始化memo数组
            Index[] memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            // 数组的最后一个位置总是可达的
            memo[memo.length - 1] = Index.GOOD;

            // 从倒数第二个位置开始遍历数组
            for (int i = nums.length - 2; i >= 0; i--) {
                // 计算当前位置能够跳跃的最远距离
                int furthestJump = Math.min(i + nums[i], nums.length - 1);
                // 遍历可能的下一个位置
                for (int j = i + 1; j <= furthestJump; j++) {
                    // 如果从下一个位置可以跳跃到最后一个位置，将当前位置标记为GOOD并跳出循环
                    if (memo[j] == Index.GOOD) {
                        memo[i] = Index.GOOD;
                        break;
                    }
                }
            }

            // 返回结果
            return memo[0] == Index.GOOD;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new Solution();
        // 测试代码
        int[] nums1 = {2, 3, 1, 1, 4};
        boolean result1 = solution.canJump(nums1);
        System.out.println("Solution 1: " + result1); // 预期输出：true

        int[] nums2 = {3, 2, 1, 0, 4};
        boolean result2 = solution.canJump(nums2);
        System.out.println("Solution 2: " + result2); // 预期输出：false
    }
}


/**
 You are given an integer array nums. You are initially positioned at the
 array's first index, and each element in the array represents your maximum jump length
 at that position.

 Return true if you can reach the last index, or false otherwise.


 Example 1:


 Input: nums = [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.


 Example 2:


 Input: nums = [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum jump
 length is 0, which makes it impossible to reach the last index.



 Constraints:


 1 <= nums.length <= 10⁴
 0 <= nums[i] <= 10⁵


 Related Topics Array Dynamic Programming Greedy 👍 18590 👎 1127

 */