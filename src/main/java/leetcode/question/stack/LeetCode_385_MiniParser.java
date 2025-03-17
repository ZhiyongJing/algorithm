package leetcode.question.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@Question:  385. Mini Parser
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 37.33%
 *@Time Complexity: O(N) // 遍历字符串一次，逐步解析
 *@Space Complexity: O(N) // 使用栈存储嵌套结构
 */
/**
 * 题目描述：
 * LeetCode 385. Mini Parser
 *
 * 给定一个表示嵌套整数列表的字符串 `s`，其中 `s` 可能是：
 * 1. **单个整数**，如 `"123"`
 * 2. **一个嵌套列表**，如 `"[123,[456,[789]]]"`，其中列表可以包含整数或其他嵌套列表
 *
 * 解析 `s` 并返回对应的 `NestedInteger` 结构。
 *
 * **示例 1：**
 * 输入：`"324"`
 * 输出：`NestedInteger(324)`
 * 解释：该字符串表示单个整数，因此直接返回 `NestedInteger`。
 *
 * **示例 2：**
 * 输入：`"[123,[456,[789]]]"`
 * 输出：`NestedInteger([123, NestedInteger([456, NestedInteger([789])])])`
 * 解释：嵌套列表需要递归解析：
 * ```
 * [
 *   123,
 *   [
 *     456,
 *     [
 *       789
 *     ]
 *   ]
 * ]
 * ```
 *
 * ---
 *
 * **解题思路：**
 * 解析嵌套结构时，我们可以使用 **栈 (Stack) 结合遍历** 来处理嵌套列表：
 *
 * **1. 处理单个整数**
 * - 如果字符串 `s` 没有 `[`，说明它是单个整数，直接转换为 `NestedInteger` 返回。
 *
 * **2. 使用 `Stack` 解析嵌套结构**
 * - **初始化**：
 *   - 创建 `Stack<NestedInteger>` 维护当前的嵌套列表层级。
 *   - `NestedInteger res` 作为最终返回结果（根节点）。
 *   - `start` 变量用于记录数字的起始位置。
 *
 * - **遍历字符串 `s`**：
 *   - 遇到 `'['`：创建新的 `NestedInteger`，并将其加入当前栈顶 `NestedInteger` 的列表中，然后压入栈。
 *   - 遇到 `','` 或 `']'`：
 *     - 提取 `start` 到 `i` 之间的数字，并加入当前 `NestedInteger`。
 *     - 如果 `c == ']'`，说明当前嵌套结束，需要 `stack.pop()`。
 *
 * ---
 * **示例 解析**
 *
 * **输入：`"[123,[456,[789]]]"`**
 * 1. `[` → 创建 `NestedInteger` 并入栈
 * 2. `123` → 存入当前 `NestedInteger`
 * 3. `[` → 创建 `NestedInteger` 并入栈
 * 4. `456` → 存入当前 `NestedInteger`
 * 5. `[` → 创建 `NestedInteger` 并入栈
 * 6. `789` → 存入当前 `NestedInteger`
 * 7. `]` → 弹出 `NestedInteger([789])`
 * 8. `]` → 弹出 `NestedInteger([456, [789]])`
 * 9. `]` → 弹出 `NestedInteger([123, [456, [789]]])`，返回结果。
 *
 * ---
 *
 * **时间和空间复杂度分析：**
 *
 * - **时间复杂度：O(N)**
 *   - 我们遍历字符串 `s` 一次，每个字符最多被处理一次，因此是 **O(N)**。
 *
 * - **空间复杂度：O(N)**
 *   - `Stack<NestedInteger>` 最坏情况下存储 `N` 层嵌套列表，最多 `O(N)` 空间。
 */


public class LeetCode_385_MiniParser {

    // NestedInteger 接口的实现类
    public class NestedInteger  {
        private Integer value;
        private List<NestedInteger> list;

        // 构造函数：初始化为单个整数
        public NestedInteger(int value) {
            this.value = value;
            this.list = null; // 不是嵌套列表
        }

        // 构造函数：初始化为空列表
        public NestedInteger() {
            this.value = null;
            this.list = new ArrayList<>();
        }

        public boolean isInteger() {
            return value != null;
        }

        public Integer getInteger() {
            return value;
        }

        public void setInteger(int value) {
            this.value = value;
            this.list = null; // 变成单个整数，清空嵌套列表
        }

        public void add(NestedInteger ni) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(ni);
        }

        public List<NestedInteger> getList() {
            return list != null ? list : new ArrayList<>();
        }

        @Override
        public String toString() {
            if (isInteger()) {
                return String.valueOf(value);
            } else {
                return list.toString();
            }
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // 这是用于创建嵌套列表的接口。
     * // 你不应该实现它，或者推测其实现方式。
     * public interface NestedInteger {
     *     // 构造函数，初始化一个空的嵌套列表。
     *     public NestedInteger();
     *
     *     // 构造函数，初始化一个单个整数。
     *     public NestedInteger(int value);
     *
     *     // @return 如果这个 NestedInteger 持有一个单个整数，而不是一个嵌套列表，则返回 true。
     *     public boolean isInteger();
     *
     *     // @return 如果这个 NestedInteger 持有一个单个整数，则返回该整数
     *     // 如果这个 NestedInteger 持有一个嵌套列表，则返回 null
     *     public Integer getInteger();
     *
     *     // 使这个 NestedInteger 只持有一个整数。
     *     public void setInteger(int value);
     *
     *     // 使这个 NestedInteger 变成一个嵌套列表，并添加一个 NestedInteger。
     *     public void add(NestedInteger ni);
     *
     *     // @return 如果这个 NestedInteger 持有一个嵌套列表，则返回该列表
     *     // 如果这个 NestedInteger 持有一个单个整数，则返回空列表
     *     public List<NestedInteger> getList();
     * }
     */
    class Solution {
        public NestedInteger deserialize(String s) {
            // 如果字符串不以 "[" 开头，说明是单个整数，直接返回
            if (!s.startsWith("[")) {
                return new NestedInteger(Integer.valueOf(s));
            }

            // 栈用于存储嵌套结构
            Stack<NestedInteger> stack = new Stack<>();

            // 结果对象，存储解析后的 NestedInteger
            NestedInteger res = new NestedInteger();
            stack.push(res);

            int start = 1; // 记录当前数字的起始位置
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c == '[') { // 遇到 '[' 说明是新的 NestedInteger
                    NestedInteger ni = new NestedInteger(); // 创建新的嵌套结构
                    stack.peek().add(ni); // 将其添加到当前栈顶的 NestedInteger
                    stack.push(ni); // 入栈
                    start = i + 1; // 更新起始位置
                } else if (c == ',' || c == ']') { // 遇到 ',' 或 ']' 时处理数字
                    if (i > start) { // 解析数字
                        Integer val = Integer.valueOf(s.substring(start, i));
                        stack.peek().add(new NestedInteger(val)); // 加入当前嵌套结构
                    }
                    start = i + 1; // 更新起始位置
                    if (c == ']') { // 如果是 ']', 说明当前嵌套解析完毕，弹出栈
                        stack.pop();
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_385_MiniParser().new Solution();

        // 测试用例 1
        String input1 = "324";
        NestedInteger result1 = solution.deserialize(input1);
        System.out.println("解析结果 1: " + result1.getInteger());
        // 预期输出: 324

        // 测试用例 2
        String input2 = "[123,[456,[789]]]";
        NestedInteger result2 = solution.deserialize(input2);
        System.out.println("解析结果 2: " + result2.getList());
        // 预期输出: NestedInteger 结构，包含 [123, [456, [789]]]
    }
}

/**
Given a string s represents the serialization of a nested list, implement a 
parser to deserialize it and return the deserialized NestedInteger. 

 Each element is either an integer or a list whose elements may also be 
integers or other lists. 

 
 Example 1: 

 
Input: s = "324"
Output: 324
Explanation: You should return a NestedInteger object which contains a single 
integer 324.
 

 Example 2: 

 
Input: s = "[123,[456,[789]]]"
Output: [123,[456,[789]]]
Explanation: Return a NestedInteger object containing a nested list with 2 
elements:
1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789
 

 
 Constraints: 

 
 1 <= s.length <= 5 * 10⁴ 
 s consists of digits, square brackets "[]", negative sign '-', and commas ','. 

 s is the serialization of valid NestedInteger. 
 All the values in the input are in the range [-10⁶, 10⁶]. 
 

 Related Topics String Stack Depth-First Search 👍 470 👎 1444

*/