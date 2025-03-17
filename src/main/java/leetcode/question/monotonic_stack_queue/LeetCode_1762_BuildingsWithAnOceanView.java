package leetcode.question.monotonic_stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 *@Question:  1762. Buildings With an Ocean View
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 85.0%
 *@Time Complexity: O(N) // 只需从右向左遍历一次
 *@Space Complexity: O(N) // 由于使用栈存储建筑索引
 */

public class LeetCode_1762_BuildingsWithAnOceanView{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // solution1: 单调栈 (Monotonic Stack)
        public int[] findBuildings(int[] heights) {
            int n = heights.length; // 获取建筑数量
            List<Integer> list = new ArrayList<>(); // 记录可以看到海景的建筑索引

            // 单调递减栈 (Monotonically decreasing stack)
            Stack<Integer> stack = new Stack<>();

            // 从右向左遍历所有建筑
            for (int current = n - 1; current >= 0; --current) {
                // 如果栈顶的建筑比当前建筑矮，则弹出
                while (!stack.isEmpty() && heights[stack.peek()] < heights[current]) {
                    stack.pop();
                }

                // 栈为空，说明当前建筑右侧无更高建筑，可以看到海景
                if (stack.isEmpty()) {
                    list.add(current);
                }

                // 当前建筑入栈
                stack.push(current);
            }
            System.out.println(list);

            // 由于 list 记录的是从右向左的结果，因此需要反转
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                answer[i] = list.get(list.size() - 1 - i);
            }

            return answer; // 返回最终的建筑索引数组
        }

        // solution2: 空间优化的单调栈 (Space Optimized Monotonic Stack)
        public int[] findBuildings2(int[] heights) {
            int n = heights.length; // 获取建筑数量
            List<Integer> list = new ArrayList<>();
            int maxHeight = -1; // 记录当前遍历到的最高建筑

            // 从右向左遍历所有建筑
            for (int current = n - 1; current >= 0; --current) {
                // 如果当前建筑比右侧所有建筑都高，则它可以看到海景
                if (maxHeight < heights[current]) {
                    list.add(current); // 记录该建筑索引
                    maxHeight = heights[current]; // 更新最高建筑
                }
            }

            // 由于 list 记录的是从右向左的结果，因此需要反转
            int[] answer = new int[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                answer[i] = list.get(list.size() - 1 - i);
            }

            return answer; // 返回最终的建筑索引数组
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1762_BuildingsWithAnOceanView().new Solution();

        // 测试用例 1
        int[] heights1 = {4,2,3,1};
        System.out.println("可看到海景的建筑索引 (方法1): " + Arrays.toString(solution.findBuildings(heights1)));
        System.out.println("可看到海景的建筑索引 (方法2): " + Arrays.toString(solution.findBuildings2(heights1)));
        // 预期输出: [0,2,3]

        // 测试用例 2
        int[] heights2 = {1,3,2,4};
        System.out.println("可看到海景的建筑索引 (方法1): " + Arrays.toString(solution.findBuildings(heights2)));
        System.out.println("可看到海景的建筑索引 (方法2): " + Arrays.toString(solution.findBuildings2(heights2)));
        // 预期输出: [3]

        // 测试用例 3
        int[] heights3 = {2,2,2,2};
        System.out.println("可看到海景的建筑索引 (方法1): " + Arrays.toString(solution.findBuildings(heights3)));
        System.out.println("可看到海景的建筑索引 (方法2): " + Arrays.toString(solution.findBuildings2(heights3)));
        // 预期输出: [3]
    }
}

/**
There are n buildings in a line. You are given an integer array heights of size 
n that represents the heights of the buildings in the line. 

 The ocean is to the right of the buildings. A building has an ocean view if 
the building can see the ocean without obstructions. Formally, a building has an 
ocean view if all the buildings to its right have a smaller height. 

 Return a list of indices (0-indexed) of buildings that have an ocean view, 
sorted in increasing order. 

 
 Example 1: 

 
Input: heights = [4,2,3,1]
Output: [0,2,3]
Explanation: Building 1 (0-indexed) does not have an ocean view because 
building 2 is taller.
 

 Example 2: 

 
Input: heights = [4,3,2,1]
Output: [0,1,2,3]
Explanation: All the buildings have an ocean view.
 

 Example 3: 

 
Input: heights = [1,3,2,4]
Output: [3]
Explanation: Only building 3 has an ocean view.
 

 
 Constraints: 

 
 1 <= heights.length <= 10⁵ 
 1 <= heights[i] <= 10⁹ 
 

 Related Topics Array Stack Monotonic Stack 👍 1277 👎 147

*/