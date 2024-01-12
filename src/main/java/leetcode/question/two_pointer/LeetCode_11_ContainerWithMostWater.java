package leetcode.question.two_pointer;

/**
 *@Question:  11. Container With Most Water
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 90.52%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * 这道题的解法基于双指针的思想，通过从两侧向中间移动指针的方式，不断计算容器的面积，并更新最大面积。
 *
 * **解题思路：**
 *
 * 1. 使用两个指针 `left` 和 `right` 分别指向数组的开头和结尾。
 * 2. 计算当前指针指向的区域的容器面积，面积的计算方式是两个指针所指高度的较小值乘以宽度（两个指针的距离）。
 * 3. 更新最大容器面积，同时移动较小高度的指针向中间移动，以寻找可能的更大面积。
 * 4. 重复步骤2和步骤3，直到两个指针相遇。
 *
 * 这个算法的关键在于移动指针的策略：每次移动较小高度的指针，这样有可能找到更大的容器面积。
 * 因为如果移动较大高度的指针，容器的高度受限于较小高度的值，而移动较小高度的指针可能找到更高的边界。
 *
 * **时间复杂度：**
 * - 使用双指针，每次移动一步，所以时间复杂度是 O(N)，其中 N 是数组的长度。
 *
 * **空间复杂度：**
 * - 使用常数级别的额外空间，主要是存储一些变量，因此空间复杂度是 O(1)。
 *
 * 这个算法的优点在于它在线性时间内找到了最优解，而且使用了常数级别的空间。
 */

public class LeetCode_11_ContainerWithMostWater {

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        /**
         * 计算最大容器面积的方法
         * @param height 存储高度的数组
         * @return 最大容器面积
         */
        public int maxArea(int[] height) {
            int maxarea = 0; // 用于存储最大容器面积的变量
            int left = 0;    // 左边界指针
            int right = height.length - 1;  // 右边界指针

            // 使用双指针法向中间靠拢，计算每个可能的容器面积并更新最大值
            while (left < right) {
                int width = right - left; // 计算容器的宽度
                maxarea = Math.max(maxarea, Math.min(height[left], height[right]) * width); // 更新最大面积

                // 移动指针的条件是：移动较小的一侧，以寻找更大的容器面积
                if (height[left] <= height[right]) {
                    left++;
                } else {
                    right--;
                }
            }

            return maxarea; // 返回最大容器面积
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_11_ContainerWithMostWater.Solution solution = new LeetCode_11_ContainerWithMostWater().new Solution();

        // 测试代码
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = solution.maxArea(heights);

        System.out.println("The maximum area of the container is: " + result);
    }
}

/**
You are given an integer array height of length n. There are n vertical lines 
drawn such that the two endpoints of the iᵗʰ line are (i, 0) and (i, height[i]). 

 Find two lines that together with the x-axis form a container, such that the 
container contains the most water. 

 Return the maximum amount of water a container can store. 

 Notice that you may not slant the container. 

 
 Example 1: 
 
 
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,
7]. In this case, the max area of water (blue section) the container can 
contain is 49.
 

 Example 2: 

 
Input: height = [1,1]
Output: 1
 

 
 Constraints: 

 
 n == height.length 
 2 <= n <= 10⁵ 
 0 <= height[i] <= 10⁴ 
 

 Related Topics Array Two Pointers Greedy 👍 27625 👎 1564

*/
