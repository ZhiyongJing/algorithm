package leetcode.question.dp;

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
// 定义公共类，题目名称为 LeetCode_55_JumpGame
public class LeetCode_55_JumpGame{

//leetcode submit region begin(Prohibit modification and deletion)

    // 使用枚举类型表示当前位置的状态（GOOD 表示可达终点，BAD 表示不可达终点，UNKNOWN 表示未知）
    enum Index {
        GOOD, // 可达
        BAD,  // 不可达
        UNKNOWN // 未知
    }

    // 定义一个内部类 Solution，用于存放解题方法
    public class Solution {

        // 定义一个数组 memo，用于记录每个位置的状态（GOOD/BAD/UNKNOWN）
        Index[] memo;

        // 这是第 1 种解法：自顶向下的动态规划
        // 从当前位置 position 开始判断能否跳跃到数组的最后一个位置
        public boolean canJumpFromPosition(int position, int[] nums) {
            // 如果当前位置的状态已在 memo 数组中有记录，直接根据状态返回结果
            if (memo[position] != Index.UNKNOWN) {
                return memo[position] == Index.GOOD ? true : false;
            }

            // 计算从当前位置能跳跃到的最远位置（不可超过数组边界）
            int furthestJump = Math.min(position + nums[position], nums.length - 1);

            // 从 position+1 遍历到 furthestJump，尝试下一个可能的位置
            for (int nextPosition = position + 1; nextPosition <= furthestJump; nextPosition++) {
                // 如果从下一个位置可以跳到终点，则把当前位置标记为 GOOD 并返回 true
                if (canJumpFromPosition(nextPosition, nums)) {
                    memo[position] = Index.GOOD;
                    return true;
                }
            }

            // 如果无法跳到终点，则把当前位置标记为 BAD 并返回 false
            memo[position] = Index.BAD;
            return false;
        }

        // 对外提供的接口，判断是否能从数组开头跳到末尾（自顶向下 DP）
        public boolean canJump1(int[] nums) {
            // 初始化 memo 数组，记录所有位置的状态，初始时全部为 UNKNOWN
            memo = new Index[nums.length];
            for (int i = 0; i < memo.length; i++) {
                memo[i] = Index.UNKNOWN;
            }
            // 数组的最后一个位置总是可达的，因此将其标记为 GOOD
            memo[memo.length - 1] = Index.GOOD;
            // 从数组的第 0 个位置开始判断能否跳到末尾
            return canJumpFromPosition(0, nums);
        }

        // 这是第 2 种解法：自底向上的动态规划
        public boolean canJump(int[] nums) {
            // 初始化一个本地 memo 数组，用于记录每个位置的状态
            boolean[] memo = new boolean[nums.length];
//            for (int i = 0; i < memo.length; i++) {
//                memo[i] = Index.UNKNOWN;
//            }
            // 把最后一个位置标记为 GOOD，因为它本身就是终点
            memo[memo.length - 1] = true;

            // 从倒数第二个位置往前遍历
            for (int i = nums.length - 2; i >= 0; i--) {
                // 计算从当前位置能跳到的最远距离
                int furthestJump = Math.min(i + nums[i], nums.length - 1);
                // 在可跳跃的范围内，查找是否存在标记为 GOOD 的位置
                for (int j = i + 1; j <= furthestJump; j++) {
                    // 如果找到一个位置 j 的状态是 GOOD，则当前位置 i 也可以到达终点
                    if (memo[j] == true) {
                        memo[i] = true;
                        // 找到后无需再继续判断，直接跳出
                        break;
                    }
                }
            }

            // 最后判断第 0 个位置是否是 GOOD
            return memo[0] == true;
        }

        // 这是第 3 种解法：贪心算法（greedy）
        public boolean canJump3(int[] nums) {
            // 从数组最后一个位置开始，将其视为当前最远可达的目标 lastPos
            int lastPos = nums.length - 1;
            // 逆序遍历整个数组
            for (int i = nums.length - 1; i >= 0; i--) {
                // 如果从位置 i 出发，可达到（或超过）当前目标 lastPos
                // 则将 lastPos 更新为 i
                if (i + nums[i] >= lastPos) {
                    lastPos = i;
                }
            }
            // 如果最终 lastPos 回到 0，说明可以从开头跳到末尾
            return lastPos == 0;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

    // 主函数，用于测试
    public static void main(String[] args) {
        // 创建一个 Solution 实例
        Solution solution = new LeetCode_55_JumpGame().new Solution();

        // 测试用例1：输入数组为 {2, 3, 1, 1, 4}
        int[] nums1 = {2, 3, 1, 1, 4};
        // 调用 canJump 方法（自底向上 DP）测试
        boolean result1 = solution.canJump(nums1);
        // 打印结果，预期输出：true
        System.out.println("Solution 1: " + result1); // 预期输出：true

        // 测试用例2：输入数组为 {3, 2, 1, 0, 4}
        int[] nums2 = {3, 2, 1, 0, 4};
        // 调用 canJump 方法（自底向上 DP）测试
        boolean result2 = solution.canJump(nums2);
        // 打印结果，预期输出：false
        System.out.println("Solution 2: " + result2); // 预期输出：false

        // 额外添加的测试用例3：输入数组为 {1, 1, 1, 1}
        int[] nums3 = {1, 1, 1, 1};
        // 调用 canJump 方法（自底向上 DP）测试
        boolean result3 = solution.canJump(nums3);
        // 打印结果，预期输出：true
        System.out.println("Solution 3: " + result3); // 预期输出：true
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