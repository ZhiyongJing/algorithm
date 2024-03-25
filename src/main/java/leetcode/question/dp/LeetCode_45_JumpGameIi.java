package leetcode.question.dp;

/**
  *@Question:  45. Jump Game II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 62.86000000000001%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */
/**
 * 当然，让我详细解释这个解题思路。
 *
 * ### 解题思路：
 *
 * 1. 使用贪心算法。每次都选择能够跳跃的范围内，使下一步能够达到最远的位置。这样可以最小化跳跃的次数。
 *
 * 2. 维护两个变量 `curEnd` 和 `curFar`，分别表示当前跳跃的结束位置和下一次跳跃的最远位置。
 *
 * 3. 遍历数组，对于每个位置 `i`，更新 `curFar` 为当前位置能够跳跃的最远位置。
 *
 * 4. 如果当前位置 `i` 等于当前跳跃的结束位置 `curEnd`，说明已经完成了一次跳跃，增加跳跃次数 `answer`，
 * 并将 `curEnd` 更新为 `curFar`。
 *
 * 5. 重复上述步骤，直到遍历完整个数组。最终得到的 `answer` 即为最小的跳跃次数。
 *
 * ### 时间复杂度：
 *
 * 遍历一次数组，时间复杂度为 O(n)，其中 n 为数组的长度。
 *
 * ### 空间复杂度：
 *
 * 只使用了常量级别的额外空间，空间复杂度为 O(1)。
 *
 * 这个算法通过贪心的策略选择每一步最优的跳跃范围，从而保证跳跃次数最小。
 */

public class LeetCode_45_JumpGameIi {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int jump(int[] nums) {
            // 记录跳跃次数
            int answer = 0, n = nums.length;
            // 当前跳跃的结束位置和下一次跳跃的最远位置
            int curEnd = 0, curFar = 0;

            // 遍历数组，不包括最后一个元素
            for (int i = 0; i < n - 1; ++i) {
                // 更新当前跳跃的最远可达位置
                curFar = Math.max(curFar, i + nums[i]);

                // 如果当前位置达到当前跳跃的结束位置
                if (i == curEnd) {
                    // 增加跳跃次数
                    answer++;
                    // 更新下一次跳跃的结束位置
                    curEnd = curFar;
                }
            }

            return answer;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_45_JumpGameIi.Solution solution = new LeetCode_45_JumpGameIi().new Solution();
        // 测试代码
        int[] testArray = {2, 3, 1, 1, 4};
        int result = solution.jump(testArray);
        System.out.println("跳跃次数: " + result);
    }
}

/**
You are given a 0-indexed array of integers nums of length n. You are initially 
positioned at nums[0]. 

 Each element nums[i] represents the maximum length of a forward jump from 
index i. In other words, if you are at nums[i], you can jump to any nums[i + j] 
where: 

 
 0 <= j <= nums[i] and 
 i + j < n 
 

 Return the minimum number of jumps to reach nums[n - 1]. The test cases are 
generated such that you can reach nums[n - 1]. 

 
 Example 1: 

 
Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 
step from index 0 to 1, then 3 steps to the last index.
 

 Example 2: 

 
Input: nums = [2,3,0,1,4]
Output: 2
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 0 <= nums[i] <= 1000 
 It's guaranteed that you can reach nums[n - 1]. 
 

 Related Topics Array Dynamic Programming Greedy 👍 13989 👎 520

*/