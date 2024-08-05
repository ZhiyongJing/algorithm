package leetcode.question.two_pointer;

/**
 *@Question:  42. Trapping Rain Water
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 98.4%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 这个问题是关于如何计算储水量的问题，通常被称为“Trapping Rain Water”（接雨水）问题。
 *
 * ### 解题思路：
 *
 * 1. 使用双指针法：左右两个指针分别指向数组的起始位置和末尾位置。
 * 2. 使用两个变量 `left_max` 和 `right_max` 分别表示左侧和右侧的最大高度，初始值设为 0。
 * 3. 当左指针小于右指针时，以当前的左侧最大高度 `left_max` 为参考，计算左侧柱子的积水量，并将左指针向右移动一位。
 * 4. 当右指针小于左指针时，以当前的右侧最大高度 `right_max` 为参考，计算右侧柱子的积水量，并将右指针向左移动一位。
 * 5. 重复步骤 3 和步骤 4 直到左右指针相遇，计算完所有积水量，得到最终结果。
 *
 * ### 时间复杂度分析：
 *
 * 双指针法只需要一次遍历数组，所以时间复杂度为 O(N)，其中 N 是数组的长度。
 *
 * ### 空间复杂度分析：
 *
 * 双指针法只需要几个额外的变量来存储左右最大高度以及总的积水量，因此空间复杂度为 O(1)，是常数级别的空间复杂度。
 */

public class LeetCode_42_TrappingRainWater{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // 定义一个方法，用于计算积水的总量
        public int trap(int[] height) {
            // 初始化左右指针和积水总量
            int left = 0, right = height.length - 1;
            int ans = 0;
            // 初始化左右最大高度
            int left_max = 0, right_max = 0;
            // 当左指针小于右指针时进行循环
            while (left < right) {
                // 如果左边高度小于右边高度
                if (height[left] < height[right]) {
                    // 更新左边最大高度
                    left_max = Math.max(left_max, height[left]);
                    // 计算当前位置的积水量并加入总量中
                    ans += left_max - height[left];
                    // 移动左指针
                    ++left;
                } else {
                    // 更新右边最大高度
                    right_max = Math.max(right_max, height[right]);
                    // 计算当前位置的积水量并加入总量中
                    ans += right_max - height[right];
                    // 移动右指针
                    --right;
                }
            }
            // 返回积水的总量
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建解决方案对象
        LeetCode_42_TrappingRainWater.Solution solution = new LeetCode_42_TrappingRainWater().new Solution();
        // 测试用例1
        int[] heights1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(heights1)); // 应返回 6

        // 测试用例2
        int[] heights2 = {4,2,0,3,2,5};
        System.out.println(solution.trap(heights2)); // 应返回 9
    }
}

/**
 Given n non-negative integers representing an elevation map where the width of
 each bar is 1, compute how much water it can trap after raining.


 Example 1:


 Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 Output: 6
 Explanation: The above elevation map (black section) is represented by array [0,
 1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are
 being trapped.


 Example 2:


 Input: height = [4,2,0,3,2,5]
 Output: 9



 Constraints:


 n == height.length
 1 <= n <= 2 * 10⁴
 0 <= height[i] <= 10⁵


 Related Topics Array Two Pointers Dynamic Programming Stack Monotonic Stack 👍
 31591 👎 501

 */
/**
Given n non-negative integers representing an elevation map where the width of 
each bar is 1, compute how much water it can trap after raining. 

 
 Example 1: 
 
 
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,
1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are 
being trapped.
 

 Example 2: 

 
Input: height = [4,2,0,3,2,5]
Output: 9
 

 
 Constraints: 

 
 n == height.length 
 1 <= n <= 2 * 10⁴ 
 0 <= height[i] <= 10⁵ 
 

 Related Topics Array Two Pointers Dynamic Programming Stack Monotonic Stack 👍 
32150 👎 521

*/