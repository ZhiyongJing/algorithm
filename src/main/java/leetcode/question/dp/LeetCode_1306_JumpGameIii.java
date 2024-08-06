package leetcode.question.dp;

/**
  *@Question:  1306. Jump Game III     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 47.21%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/**
 * 当然，让我详细解释这个解题思路。
 *
 * ### 解题思路：
 *
 * 1. 递归判断能否从起始位置 `start` 到达目标位置，每次递归时更新当前位置的值为其相反数，表示已经访问过。
 *
 * 2. 在递归的过程中，判断当前位置是否越界（在数组有效范围内）、是否已经访问过，以及是否能够到达目标位置。
 *
 * 3. 如果当前位置的值为 0，表示已经到达目标位置，返回 `true`。
 *
 * 4. 递归地检查从当前位置能够到达的左右两个位置，分别是 `start + arr[start]` 和 `start - arr[start]`。
 *
 * 5. 如果递归的任意一条路径返回 `true`，则说明能够到达目标位置，返回 `true`；否则返回 `false`。
 *
 * ### 时间复杂度：
 *
 * 每个位置最多被访问两次，因为当第一次访问时会将其值取负，表示已访问。所以，时间复杂度为 O(N)，其中 N 为数组的长度。
 *
 * ### 空间复杂度：
 *
 * 递归的最大深度为数组的长度 N，因此空间复杂度为 O(N)。此外，递归调用栈的空间也会占用额外的空间。
 *
 * 这个算法通过递归方式深度搜索数组，判断是否能够从起始位置到达目标位置。然而，递归深度可能较大，可能会导致栈溢出。
 */

public class LeetCode_1306_JumpGameIii {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 判断是否能够到达指定位置
         * @param arr 给定的数组
         * @param start 起始位置
         * @return 是否能够到达指定位置
         */
        //top-down dfs
        public boolean canReach(int[] arr, int start) {
            // 检查起始位置是否有效
            if (start >= 0 && start < arr.length && arr[start] >= 0) {
                // 如果当前位置的值为 0，说明可以到达
                if (arr[start] == 0) {
                    return true;
                }
                // 将当前位置的值标记为已访问（取负值）
                arr[start] = -arr[start];
                // 递归检查从当前位置可以到达的左右两个位置
                return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
            }
            // 位置无效或已经访问过，返回 false
            return false;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        LeetCode_1306_JumpGameIii.Solution solution = new LeetCode_1306_JumpGameIii().new Solution();
        // 测试代码
        int[] testArray1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;
        System.out.println("测试1：" + solution.canReach(testArray1, start1));  // 输出：true

        int[] testArray2 = {4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;
        System.out.println("测试2：" + solution.canReach(testArray2, start2));  // 输出：true

        int[] testArray3 = {3, 0, 2, 1, 2};
        int start3 = 2;
        System.out.println("测试3：" + solution.canReach(testArray3, start3));  // 输出：false
    }
}

/**
Given an array of non-negative integers arr, you are initially positioned at 
start index of the array. When you are at index i, you can jump to i + arr[i] or i 
- arr[i], check if you can reach any index with value 0. 

 Notice that you can not jump outside of the array at any time. 

 
 Example 1: 

 
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
 

 Example 2: 

 
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
 

 Example 3: 

 
Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

 
 Constraints: 

 
 1 <= arr.length <= 5 * 10⁴ 
 0 <= arr[i] < arr.length 
 0 <= start < arr.length 
 

 Related Topics Array Depth-First Search Breadth-First Search 👍 4040 👎 95

*/