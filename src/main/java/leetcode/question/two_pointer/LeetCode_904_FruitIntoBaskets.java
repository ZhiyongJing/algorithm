package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  904. Fruit Into Baskets
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 56.89%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */
/**
 * ===============================================
 * LeetCode 904. Fruit Into Baskets
 * ===============================================
 *
 * 【一、题目描述】
 * 有一排树，每棵树上都长着一种类型的水果（用整数表示）。你有两个篮子，
 * 每个篮子只能装**一种类型的水果**，但可以装任意数量。
 *
 * 你的目标是选择一段**连续的子数组**，从中收集尽可能多的水果，但只能装两种类型。
 * 返回最多可以收集多少个水果。
 *
 * 示例：
 * 输入：[1,2,1]，输出：3 （因为可以选择全部三个）
 * 输入：[0,1,2,2]，输出：3（选择 [1,2,2]）
 * 输入：[1,2,3,2,2]，输出：4（选择 [2,3,2,2]）
 * 输入：[3,3,3,1,2,1,1,2,3,3,4]，输出：5（选择 [1,2,1,1,2]）
 *
 *
 * 【二、解题思路详解（滑动窗口 + 哈希表）】
 *
 * ✅ 思路概述：
 * 本题实质是找出一个最长的子数组，其中包含**不超过两种不同的数字**。
 * 经典解法是使用**滑动窗口（双指针）**配合哈希表。
 *
 * ✅ 核心步骤：
 * 1. 使用哈希表 `basket` 存储当前窗口中每种水果的数量；
 * 2. 用两个指针 `left` 和 `right` 表示窗口边界；
 * 3. 向右移动 right，不断加入水果并更新 basket；
 * 4. 如果 basket 中水果种类超过 2 种，就不断移动 left（缩小窗口）直到只剩两种；
 * 5. 每次更新窗口时，记录最大长度；
 *
 * ✅ 举例说明：
 * 输入：[1, 2, 3, 2, 2]
 * - right=0, fruit=1，加入窗口 -> basket={1:1} ✔
 * - right=1, fruit=2，加入窗口 -> basket={1:1,2:1} ✔
 * - right=2, fruit=3，加入窗口 -> basket={1:1,2:1,3:1} ❌ 超过两种，left前移
 *   - left=0, 去掉fruit=1 -> basket={2:1,3:1} ✔
 * - right=3, fruit=2，加入 -> basket={2:2,3:1} ✔
 * - right=4, fruit=2，加入 -> basket={2:3,3:1} ✔
 * - 最大窗口：[2,3,2,2]，长度为 4 ✅
 *
 *
 * 【三、时间与空间复杂度分析】
 *
 * 时间复杂度：O(N)
 * - 其中 N 是数组长度，每个元素最多被 left 和 right 各遍历一次；
 * - 所以整体为线性复杂度；
 *
 * 空间复杂度：O(1)
 * - 哈希表中最多只存两种水果种类，空间恒定；
 * - 符合题目要求的空间最优解；
 */

public class LeetCode_904_FruitIntoBaskets{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int totalFruit(int[] fruits) {
            // 使用哈希表 basket 记录当前窗口中每种水果的个数
            Map<Integer, Integer> basket = new HashMap<>();

            // left 指向窗口左边界，maxPicked 表示当前能收集到的最多水果数量
            int left = 0, maxPicked = 0;

            // 从右侧逐个遍历水果数组，作为滑动窗口右边界
            for (int right = 0; right < fruits.length; ++right) {
                // 将当前水果加入哈希表中，并更新计数
                basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

                // 如果窗口中水果种类超过 2 种，需要从左侧开始缩小窗口
                while (basket.size() > 2) {
                    // 减少左侧水果的数量
                    basket.put(fruits[left], basket.get(fruits[left]) - 1);

                    // 如果某种水果数量为 0，移除该种类
                    if (basket.get(fruits[left]) == 0)
                        basket.remove(fruits[left]);

                    // 左指针右移，缩小窗口
                    left++;
                }

                // 更新当前能收集到的最多水果数量
                maxPicked = Math.max(maxPicked, right - left + 1);
            }

            // 返回最大可收集水果数
            return maxPicked;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_904_FruitIntoBaskets().new Solution();

        // 测试用例 1：输入 [1,2,1]，最多两个篮子 -> 最大窗口长度为 3
        int[] fruits1 = {1, 2, 1};
        System.out.println("测试用例 1 输出：" + solution.totalFruit(fruits1)); // 预期输出：3

        // 测试用例 2：输入 [0,1,2,2]，最大窗口 [1,2,2] -> 输出 3
        int[] fruits2 = {0, 1, 2, 2};
        System.out.println("测试用例 2 输出：" + solution.totalFruit(fruits2)); // 预期输出：3

        // 测试用例 3：输入 [1,2,3,2,2]，最大窗口 [2,3,2,2] -> 输出 4
        int[] fruits3 = {1, 2, 3, 2, 2};
        System.out.println("测试用例 3 输出：" + solution.totalFruit(fruits3)); // 预期输出：4

        // 测试用例 4：输入 [3,3,3,1,2,1,1,2,3,3,4]，最大窗口 [1,2,1,1,2] -> 输出 5
        int[] fruits4 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        System.out.println("测试用例 4 输出：" + solution.totalFruit(fruits4)); // 预期输出：5
    }
}

/**
You are visiting a farm that has a single row of fruit trees arranged from left 
to right. The trees are represented by an integer array fruits where fruits[i] 
is the type of fruit the iᵗʰ tree produces. 

 You want to collect as much fruit as possible. However, the owner has some 
strict rules that you must follow: 

 
 You only have two baskets, and each basket can only hold a single type of 
fruit. There is no limit on the amount of fruit each basket can hold. 
 Starting from any tree of your choice, you must pick exactly one fruit from 
every tree (including the start tree) while moving to the right. The picked fruits 
must fit in one of your baskets. 
 Once you reach a tree with fruit that cannot fit in your baskets, you must 
stop. 
 

 Given the integer array fruits, return the maximum number of fruits you can 
pick. 

 
 Example 1: 

 
Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
 

 Example 2: 

 
Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
 

 Example 3: 

 
Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 

 
 Constraints: 

 
 1 <= fruits.length <= 10⁵ 
 0 <= fruits[i] < fruits.length 
 

 Related Topics Array Hash Table Sliding Window 👍 5000 👎 383

*/