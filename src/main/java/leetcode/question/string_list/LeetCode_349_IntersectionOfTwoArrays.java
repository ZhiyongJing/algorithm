// 包所在的目录结构
package leetcode.question.string_list;

// 导入所有 Java 内置工具类
import java.util.*;

/**
 *@Question:  349. Intersection of Two Arrays
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 73.16%
 *@Time  Complexity: O(M + N)
 *@Space Complexity: O(N)
 */

/**
 * 1. 题目描述
 *    给定两个数组 nums1 和 nums2，编写一个函数来计算它们的交集。
 *    数组的交集是两个数组中都出现的、且去重后剩余的那些元素集合（即每个元素只出现一次）。
 *
 * 2. 解题思路（超级详细讲解 + 举例）
 *
 *    整体思路：借助 HashMap 或 Set 等数据结构来快速判断某个元素是否在另一个数组中出现，并确保最终结果没有重复。
 *
 *    具体步骤：
 *    a) 首先将数组 nums1 中的所有元素记录到一个数据结构中（题目中使用 HashMap<String,Integer> 或类似的 Set 都可以）。
 *       - 例如用 HashMap<Integer, Integer> 来存储每个元素 x 是否已经被“选择”为交集的一部分。
 *         在这里，key 为元素本身，value 可以是一种标记（代码中是 1），代表此元素初次在 nums1 中出现。
 *       - 举个例子：如果 nums1 = [1, 2, 2, 1]
 *         那么最后 HashMap 的数据大致是 {1:1, 2:1}
 *         其中 "1" 出现过，"2" 也出现过。
 *
 *    b) 然后遍历 nums2，对每个元素 x 判断其是否在第一步建立的“记录”中出现并且尚未被纳入结果集合（即在 HashMap 中的标记还是 1）。
 *       - 如果存在并且标记为 1，则将其加入结果，并且把 HashMap 中对应的标记从 1 改为 0，避免重复加入。
 *       - 举个例子：如果 nums2 = [2, 2]
 *         先看第一个 2，在 HashMap 中 {2:1}，满足条件，加入结果集合后将其改为 0。
 *         第二个 2 再被检查时，已经是 {2:0}，不再加入。
 *         因此最终只加入一次 2 到结果中。
 *
 *    c) 最后，将结果集合中的元素转换为 int[]，返回给调用者。
 *       - 例如如果 result = [2] 或者 result = [4,9]，则转换为对应的整型数组。
 *
 *    举个更完整的例子：
 *    - nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
 *    - 第一步：HashMap 记录 nums1:
 *      {4:1, 9:1, 5:1}
 *    - 第二步：遍历 nums2：
 *      1) 元素 9, 在 HashMap 中存在且 value=1 -> 加入结果 [9], 并将 HashMap 中 9 的值改为 0
 *      2) 元素 4, 在 HashMap 中存在且 value=1 -> 加入结果 [9,4], 并将 HashMap 中 4 的值改为 0
 *      3) 下一个元素 9, 此时 HashMap 中 9 的值已经是 0, 不再加入
 *      4) 元素 8, 不在 HashMap 中，不加入
 *      5) 元素 4, value=0, 不加入
 *    - 结果数组变为 [9, 4]，也可以是 [4, 9]，因为顺序无关。
 *
 * 3. 时间和空间复杂度
 *    - 时间复杂度：O(M + N)，其中 M 和 N 分别是两个数组的长度。
 *      主要包括两次遍历：第一次遍历 nums1，第二次遍历 nums2。
 *    - 空间复杂度：O(N)，这里 N 指的是存储中较长的数组元素个数（或至少是存储 nums1 元素所需的大小）。
 *      在这个过程中，我们使用了一个 HashMap 或类似的 Set 来记录出现的元素。
 */


public class LeetCode_349_IntersectionOfTwoArrays{

    //leetcode submit region begin(Prohibit modification and deletion)
    // 声明一个内部类 Solution，处理题目的解答
    class Solution {
        // 定义 intersection 方法，接收两个整型数组 nums1 和 nums2
        public int[] intersection(int[] nums1, int[] nums2) {

            // Initialize seen map and result list
            // 创建一个 HashMap 用于记录 nums1 中的元素出现状态
            Map<Integer, Integer> seen = new HashMap<>();
            // 创建一个 ArrayList 用于暂存结果中的元素
            List<Integer> result = new ArrayList<>();

            // Mark values occurring in nums1
            // 遍历 nums1，把每个元素 x 存入 HashMap
            // key 为 x，value 为 1，表示 x 已经出现但尚未在结果中使用
            for (int x : nums1) {
                seen.put(x, 1);
            }

            // Check if n is in dictionary and not in the result
            // 遍历 nums2，如果当前元素 x 在 HashMap 中存在并且值为 1，则表示这是一个交集元素
            // 将其加入 result，随后将其在 HashMap 中的值置为 0，以防重复添加
            for (int x : nums2) {
                if (seen.containsKey(x) && seen.get(x) == 1) {
                    result.add(x);
                    seen.put(x, 0);
                }
            }

            // Convert to int array and result the result
            // 将列表 result 转换为 int 数组并返回
            return result.stream().mapToInt(i->i).toArray();

        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    // main 方法，用于测试
    public static void main(String[] args) {
        // 创建 Solution 对象
        Solution solution = new LeetCode_349_IntersectionOfTwoArrays().new Solution();

        // 测试样例 1
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result1 = solution.intersection(nums1, nums2);
        System.out.println("Intersection 1: " + Arrays.toString(result1)); // 期望输出: [2]

        // 测试样例 2
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        int[] result2 = solution.intersection(nums3, nums4);
        System.out.println("Intersection 2: " + Arrays.toString(result2)); // 期望输出: [4, 9] (顺序无所谓)
    }
}

/**
Given two integer arrays nums1 and nums2, return an array of their intersection.
 Each element in the result must be unique and you may return the result in any 
order. 

 
 Example 1: 

 
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2]
 

 Example 2: 

 
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [9,4]
Explanation: [4,9] is also accepted.
 

 
 Constraints: 

 
 1 <= nums1.length, nums2.length <= 1000 
 0 <= nums1[i], nums2[i] <= 1000 
 

 Related Topics Array Hash Table Two Pointers Binary Search Sorting 👍 6315 👎 2
314

*/