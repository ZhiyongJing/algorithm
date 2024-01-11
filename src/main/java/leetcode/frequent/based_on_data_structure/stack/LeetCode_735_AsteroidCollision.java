package leetcode.frequent.based_on_data_structure.stack;

import java.util.Arrays;
import java.util.Stack;

/**
  *@Question:  735. Asteroid Collision     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 73.05%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

/**
 * 这道题的目标是模拟行星碰撞过程，给定一组表示行星大小的整数数组，当两个行星相遇时，较小的行星会被销毁。
 * 最终要求返回模拟结束后剩余的行星数组。
 *
 * ### 解题思路
 *
 * 1. 使用栈来模拟行星碰撞的过程。
 * 2. 遍历给定的行星数组，对于每个行星：
 *    - 如果栈为空或者栈顶行星为正数（向右移动）且当前行星也为正数，则直接入栈。
 *    - 如果栈顶行星为负数（向左移动）且当前行星也为正数，则需要比较它们的大小：
 *      - 如果当前行星的绝对值较大，则入栈，替代栈顶的行星。
 *      - 如果当前行星的绝对值较小，则继续下一轮比较，栈顶的行星保持不变。
 *      - 如果当前行星和栈顶行星的绝对值相等，则同时出栈，两者均被销毁。
 * 3. 最终栈中剩余的行星即为模拟结束后的结果。
 *
 * ### 时间复杂度
 *
 * - 遍历行星数组的时间复杂度为 O(N)，其中 N 为数组长度。
 *
 * ### 空间复杂度
 *
 * - 使用了一个栈，最坏情况下可能需要存储全部行星，因此空间复杂度为 O(N)。
 *
 * 综上所述，这个算法通过栈的模拟过程，高效地模拟了行星碰撞的过程，时间和空间复杂度都是线性的。

 */

public class LeetCode_735_AsteroidCollision {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> stack = new Stack<>();

            for (int asteroid : asteroids) {
                boolean flag = true;
                while (!stack.isEmpty() && (stack.peek() > 0 && asteroid < 0)) {
                    // 如果栈顶的行星较小，它将发生碰撞并被销毁。
                    // 因此从栈中弹出，并继续下一个行星的比较。
                    if (Math.abs(stack.peek()) < Math.abs(asteroid)) {
                        stack.pop();
                        continue;
                    }
                    // 如果两个行星大小相同，它们都将发生碰撞并被销毁。
                    // 弹出栈顶行星，但当前行星不加入栈。
                    else if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
                        stack.pop();
                    }

                    // 如果执行到这里，当前行星将被销毁
                    // 因此，不将其加入栈中
                    flag = false;
                    break;
                }

                if (flag) {
                    stack.push(asteroid);
                }
            }

            // 将栈中的行星从后往前加入数组
            int[] remainingAsteroids = new int[stack.size()];
            for (int i = remainingAsteroids.length - 1; i >= 0; i--) {
                remainingAsteroids[i] = stack.peek();
                stack.pop();
            }

            return remainingAsteroids;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_735_AsteroidCollision().new Solution();

        // 测试用例
        int[] asteroids = {5, 10, -5};
        int[] result = solution.asteroidCollision(asteroids);
        System.out.println("Result: " + Arrays.toString(result));  // 输出：[5, 10]

        asteroids = new int[]{8, -8};
        result = solution.asteroidCollision(asteroids);
        System.out.println("Result: " + Arrays.toString(result));  // 输出：[]

        asteroids = new int[]{10, 2, -5};
        result = solution.asteroidCollision(asteroids);
        System.out.println("Result: " + Arrays.toString(result));  // 输出：[10]

        asteroids = new int[]{-2, -1, 1, 2};
        result = solution.asteroidCollision(asteroids);
        System.out.println("Result: " + Arrays.toString(result));  // 输出：[-2, -1, 1, 2]
    }
}

/**
We are given an array asteroids of integers representing asteroids in a row. 

 For each asteroid, the absolute value represents its size, and the sign 
represents its direction (positive meaning right, negative meaning left). Each 
asteroid moves at the same speed. 

 Find out the state of the asteroids after all collisions. If two asteroids 
meet, the smaller one will explode. If both are the same size, both will explode. 
Two asteroids moving in the same direction will never meet. 

 
 Example 1: 

 
Input: asteroids = {5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 

 Example 2: 

 
Input: asteroids = {8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
 

 Example 3: 

 
Input: asteroids = {10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide 
resulting in 10.
 

 
 Constraints: 

 
 2 <= asteroids.length <= 10⁴ 
 -1000 <= asteroids[i] <= 1000 
 asteroids[i] != 0 
 

 Related Topics Array Stack Simulation 👍 7452 👎 913

*/