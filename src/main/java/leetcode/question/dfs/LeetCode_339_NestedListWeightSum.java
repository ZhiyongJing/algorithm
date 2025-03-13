package leetcode.question.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *@Question:  339. Nested List Weight Sum (加权嵌套列表求和)
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 91.85%
 *@Time  Complexity: O(N)  // 需要遍历所有的嵌套元素
 *@Space Complexity: O(N)  // 递归深度可能达到 O(N) 或者队列存储的元素数量最多为 O(N)
 */
/**
 * 题目描述：
 * 339. Nested List Weight Sum (嵌套列表权重和)
 *
 * 给定一个嵌套的整数列表 `nestedList`，其中 `nestedList[i]` 可能是一个**整数**，也可能是一个**嵌套列表**。
 * 每个整数都有一个**深度（depth）**，整数的加权和等于 `整数值 × 深度`。
 *
 * 要求计算该嵌套列表的**加权和**。
 *
 * 示例：
 * 输入: [[1,1],2,[1,1]]
 * 输出: 10
 * 解释:
 * - 1 位于第 2 层，贡献 `1 * 2 = 2`
 * - 1 位于第 2 层，贡献 `1 * 2 = 2`
 * - 2 位于第 1 层，贡献 `2 * 1 = 2`
 * - 1 位于第 2 层，贡献 `1 * 2 = 2`
 * - 1 位于第 2 层，贡献 `1 * 2 = 2`
 * - 总和: `2 + 2 + 2 + 2 + 2 = 10`
 *
 * 输入: [1,[4,[6]]]
 * 输出: 27
 * 解释:
 * - 1 位于第 1 层，贡献 `1 * 1 = 1`
 * - 4 位于第 2 层，贡献 `4 * 2 = 8`
 * - 6 位于第 3 层，贡献 `6 * 3 = 18`
 * - 总和: `1 + 8 + 18 = 27`
 *
 *
 * 解题思路：
 *
 * 我们可以使用 **两种方法** 来解决该问题：
 * 1. **深度优先搜索 (DFS)**
 * 2. **广度优先搜索 (BFS)**
 *
 *
 * 【方法 1：DFS（深度优先搜索）】
 *
 * - 递归遍历整个 `nestedList`，每遇到一个**整数**时，计算它的加权值 `值 × 深度`，并将其加入总和。
 * - 如果遇到**嵌套列表**，递归进入该列表，并将**深度加 1**。
 * - 递归终止条件是遍历到列表末尾。
 *
 * **示例分析**
 * 输入: `nestedList = [1,[4,[6]]]`
 * - `1` 在**第 1 层**，贡献 `1 * 1 = 1`
 * - `4` 在**第 2 层**，贡献 `4 * 2 = 8`
 * - `6` 在**第 3 层**，贡献 `6 * 3 = 18`
 * - **最终结果**: `1 + 8 + 18 = 27`
 *
 * **DFS 递归流程**
 * 1. 递归进入 `1`，是整数，贡献 `1 * 1`，返回 `1`
 * 2. 递归进入 `[4, [6]]`：
 *    - `4` 是整数，贡献 `4 * 2`，返回 `8`
 *    - 递归进入 `[6]`：
 *      - `6` 是整数，贡献 `6 * 3`，返回 `18`
 * 3. 最终返回 `1 + 8 + 18 = 27`
 *
 *
 * 【方法 2：BFS（广度优先搜索）】
 *
 * - 使用 **队列 (Queue)** 进行层级遍历，每一层的深度从 `1` 开始递增。
 * - 每次遍历当前层的所有元素：
 *   - 如果是**整数**，计算加权和 `值 × 当前深度`，加入总和。
 *   - 如果是**嵌套列表**，展开后加入队列，等待下一层处理。
 * - 当所有层级遍历完后，得到最终的加权和。
 *
 * **示例分析**
 * 输入: `nestedList = [[1,1],2,[1,1]]`
 *
 * **BFS 遍历流程**
 * 1. `depth = 1`，队列初始状态 `[[1,1],2,[1,1]]`
 *    - `2` 是整数，贡献 `2 * 1 = 2`
 *    - `[1,1]` 和 `[1,1]` 是列表，进入下一层
 * 2. `depth = 2`，队列 `[1,1,1,1]`
 *    - `1 * 2 = 2`
 *    - `1 * 2 = 2`
 *    - `1 * 2 = 2`
 *    - `1 * 2 = 2`
 * 3. **最终结果**: `2 + 2 + 2 + 2 + 2 = 10`
 *
 *
 * 【时间和空间复杂度分析】
 *
 * **方法 1：DFS**
 * - **时间复杂度：O(N)**，因为我们需要遍历 `nestedList` 中的每个元素。
 * - **空间复杂度：O(N)**，最坏情况下（如 `[[[[1]]]]`）递归调用栈深度为 `O(N)`。
 *
 * **方法 2：BFS**
 * - **时间复杂度：O(N)**，我们需要遍历 `nestedList` 中的每个元素。
 * - **空间复杂度：O(N)`**，最坏情况下（所有元素都在同一层）队列最多存储 `N` 个元素。
 *
 *
 * 【总结】
 * ✅ **DFS 适用于递归处理嵌套结构，逐层计算加权和**
 * ✅ **BFS 适用于逐层遍历，层级深度逐步增加**
 * ✅ **时间复杂度 O(N)，空间复杂度 O(N)** 🚀
 */


public class LeetCode_339_NestedListWeightSum{
    public static class NestedInteger {
        private Integer value; // 存储单个整数
        private List<NestedInteger> list; // 存储嵌套列表

        // 构造一个空的 NestedInteger（存储列表）
        public NestedInteger() {
            this.list = new ArrayList<>();
        }

        // 构造一个存储单个整数的 NestedInteger
        public NestedInteger(int value) {
            this.value = value;
            this.list = null; // 该实例表示一个单独的整数，而不是列表
        }

        // 判断当前 NestedInteger 是否是单个整数
        public boolean isInteger() {
            return value != null;
        }

        // 获取当前 NestedInteger 存储的整数（如果是单个整数）
        public Integer getInteger() {
            return value;
        }

        // 设置当前 NestedInteger 为单个整数
        public void setInteger(int value) {
            this.value = value;
            this.list = null; // 设定为整数后，不再存储嵌套列表
        }

        // 将当前 NestedInteger 设定为列表，并添加一个新的 NestedInteger
        public void add(NestedInteger ni) {
            if (this.list == null) {
                this.list = new ArrayList<>();
            }
            this.list.add(ni);
        }

        // 获取存储的嵌套列表（如果当前实例是列表）
        public List<NestedInteger> getList() {
            return list;
        }
    }
//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // 这是允许创建嵌套列表的接口
     * // 你不需要实现它，也不需要猜测其实现方式
     * public interface NestedInteger {
     *     // 构造函数，初始化一个空的嵌套列表
     *     public NestedInteger();
     *
     *     // 构造函数，初始化一个单独的整数
     *     public NestedInteger(int value);
     *
     *     // 如果这个 NestedInteger 只包含一个整数，则返回 true，否则返回 false
     *     public boolean isInteger();
     *
     *     // 如果这个 NestedInteger 只包含一个整数，则返回该整数
     *     // 如果它包含的是一个列表，则结果是未定义的
     *     public Integer getInteger();
     *
     *     // 设置当前 NestedInteger 为一个单独的整数
     *     public void setInteger(int value);
     *
     *     // 设定这个 NestedInteger 为一个嵌套列表，并向其中添加一个 NestedInteger
     *     public void add(NestedInteger ni);
     *
     *     // 如果这个 NestedInteger 代表的是一个嵌套列表，则返回该列表
     *     // 如果它是一个单独的整数，则结果是未定义的
     *     public List<NestedInteger> getList();
     * }
     */
    class Solution {
        private int total;
        // 深度优先搜索 (DFS) 解法
        public int depthSum(List<NestedInteger> nestedList) {
            return dfs(nestedList, 1); // 调用递归方法，初始深度为 1
        }

        /**
         * 深度优先搜索递归方法
         * @param list 当前层的嵌套列表
         * @param depth 当前层的深度
         * @return 该层所有元素的加权和
         */
        private int dfs(List<NestedInteger> list, int depth) {
            int total = 0; // 记录当前层的加权和
            for (NestedInteger nested : list) { // 遍历当前层的所有元素
                if (nested.isInteger()) { // 如果当前元素是一个整数
                    total += nested.getInteger() * depth; // 计算权重后累加到 total
                } else { // 如果当前元素是一个嵌套列表
                    total += dfs(nested.getList(), depth + 1); // 递归处理子列表，并增加深度
                }
            }
            return total; // 返回当前层的总权重和
        }
        private void dfs2(List<NestedInteger> nestedList, int level) {
            for (NestedInteger n : nestedList) {
                if (n.isInteger()) {
                    total += n.getInteger() * level;
                } else {
                    dfs(n.getList(), level + 1);
                }
            }
        }

        // 广度优先搜索 (BFS) 解法
        public int depthSum2(List<NestedInteger> nestedList) {
            Queue<NestedInteger> queue = new LinkedList<>(); // 创建队列进行层序遍历
            queue.addAll(nestedList); // 将整个嵌套列表的第一层元素添加到队列中

            int depth = 1; // 记录当前的深度
            int total = 0; // 记录总加权和

            while (!queue.isEmpty()) { // 进行 BFS 遍历
                int size = queue.size(); // 记录当前层的元素数量
                for (int i = 0; i < size; i++) { // 遍历当前层的所有元素
                    NestedInteger nested = queue.poll(); // 取出队列中的元素
                    if (nested.isInteger()) { // 如果当前元素是整数
                        total += nested.getInteger() * depth; // 计算权重后累加
                    } else { // 如果当前元素是嵌套列表
                        queue.addAll(nested.getList()); // 将子列表的所有元素加入队列，进行下一层遍历
                    }
                }
                depth++; // 每遍历一层，深度增加
            }
            return total; // 返回最终的加权和
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_339_NestedListWeightSum().new Solution();

        // 测试样例 1: [[1,1],2,[1,1]]
        List<NestedInteger> test1 = new ArrayList<>();
        NestedInteger ni1 = new NestedInteger();
        ni1.add(new NestedInteger(1));
        ni1.add(new NestedInteger(1));
        test1.add(ni1);
        test1.add(new NestedInteger(2));
        NestedInteger ni2 = new NestedInteger();
        ni2.add(new NestedInteger(1));
        ni2.add(new NestedInteger(1));
        test1.add(ni2);
        System.out.println(solution.depthSum(test1)); // 输出: 10

        // 测试样例 2: [1,[4,[6]]]
        List<NestedInteger> test2 = new ArrayList<>();
        test2.add(new NestedInteger(1));
        NestedInteger ni3 = new NestedInteger();
        ni3.add(new NestedInteger(4));
        NestedInteger ni4 = new NestedInteger();
        ni4.add(new NestedInteger(6));
        ni3.add(ni4);
        test2.add(ni3);
        System.out.println(solution.depthSum(test2)); // 输出: 27
    }
}

/**
You are given a nested list of integers nestedList. Each element is either an 
integer or a list whose elements may also be integers or other lists. 

 The depth of an integer is the number of lists that it is inside of. For 
example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its 
depth. 

 Return the sum of each integer in nestedList multiplied by its depth. 

 
 Example 1: 
 
 
Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 
= 10.
 

 Example 2: 
 
 
Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2
 + 6*3 = 27. 

 Example 3: 

 
Input: nestedList = [0]
Output: 0
 

 
 Constraints: 

 
 1 <= nestedList.length <= 50 
 The values of the integers in the nested list is in the range [-100, 100]. 
 The maximum depth of any integer is less than or equal to 50. 
 

 Related Topics Depth-First Search Breadth-First Search 👍 1800 👎 458

*/