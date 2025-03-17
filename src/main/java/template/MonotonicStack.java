package template;

import java.util.Arrays;
import java.util.Stack;

/**
 * 题目描述：
 * LeetCode 1762. Buildings With an Ocean View
 *
 * 给定一个整数数组 `heights`，其中 `heights[i]` 代表第 `i` 座建筑的高度，
 * **所有建筑面向右侧的海洋**（数组的末尾）。
 *
 * **要求**：返回所有能直接看到海景的建筑索引，索引需 **按升序排列**。
 *
 * **示例 1：**
 * ```
 * 输入: heights = [4,2,3,1]
 * 输出: [0,2,3]
 * 解释:
 * - `heights[3] = 1` 能看到海景（右侧无更高建筑）
 * - `heights[2] = 3` 能看到海景（右侧比它小）
 * - `heights[0] = 4` 能看到海景（右侧比它小）
 * - `heights[1] = 2` 被 `heights[2] = 3` 挡住，看不到海景
 * ```
 *
 * ---
 *
 * **解题思路：**
 *
 * 由于所有建筑面向 **右侧的海洋**，如果 **一个建筑右侧存在更高的建筑**，则该建筑看不到海景。
 * 因此，我们可以 **从右向左遍历**，并使用 **单调栈** 来维护可见的建筑索引：
 *
 * ---
 * **方法 1：单调栈 (Monotonic Stack)**
 *
 * 1. **初始化一个单调递减栈**，用于存储右侧比当前建筑高的建筑索引。
 * 2. **从右向左遍历 `heights`**：
 *    - **如果栈顶建筑高度 `heights[stack.peek()]` 小于当前建筑 `heights[current]`**：
 *      - 说明 `stack.peek()` 代表的建筑被 `heights[current]` 挡住，不可能看到海景，`pop` 出栈。
 *    - **如果栈为空**，表示 `current` 右侧没有更高的建筑，它可以看到海景，将 `current` 记录。
 *    - **将 `current` 压入栈**。
 * 3. **反转结果数组**，确保索引按升序返回。
 *
 * **示例 解析**
 * ```
 * 输入: heights = [4,2,3,1]
 * 遍历过程:
 * 1. 位置 3 (高度 1): 右侧无建筑，记录 => [3]
 * 2. 位置 2 (高度 3): 栈顶 (1) 被挡住，弹出；记录 => [3,2]
 * 3. 位置 1 (高度 2): 被 `heights[2]=3` 挡住，跳过
 * 4. 位置 0 (高度 4): 栈顶 (3) 被挡住，弹出；记录 => [3,2,0]
 * 最终结果: **[0,2,3]**
 * ```
 *
 * ---
 * **方法 2：单调栈优化 (Space Optimized)**
 *
 * - 由于我们 **只关心右侧的最大高度**，可以直接使用 `maxHeight` 变量代替栈：
 *   - **从右向左遍历**，维护 `maxHeight`（当前已访问的最高建筑）。
 *   - **如果 `heights[current] > maxHeight`**，说明 `current` 可以看到海景，更新 `maxHeight` 并记录索引。
 *   - **最终反转结果数组**。
 *
 * **示例 解析**
 * ```
 * 输入: heights = [4,2,3,1]
 * 遍历过程:
 * 1. 位置 3 (高度 1): 右侧无建筑，记录，maxHeight = 1 => [3]
 * 2. 位置 2 (高度 3): 高于 maxHeight，记录，maxHeight = 3 => [3,2]
 * 3. 位置 1 (高度 2): 被 `maxHeight=3` 挡住，跳过
 * 4. 位置 0 (高度 4): 高于 maxHeight，记录，maxHeight = 4 => [3,2,0]
 * 最终结果: **[0,2,3]**
 * ```
 *
 * ---
 * **时间和空间复杂度分析**
 *
 * - **方法 1（单调栈）：**
 *   - **时间复杂度：O(N)** （遍历 `heights` 一次，每个元素最多进出栈一次）
 *   - **空间复杂度：O(N)** （最坏情况下 `stack` 需要存储 `N` 个建筑索引）
 *
 * - **方法 2（优化版单调栈）：**
 *   - **时间复杂度：O(N)** （遍历 `heights` 一次）
 *   - **空间复杂度：O(1)** （仅用 `maxHeight` 变量，不使用额外栈）
 */

public class MonotonicStack {
    // 1. stack 可以存储元素也可存 index，推荐存入 index
    // 《单调栈模板方法》

    /**
     * 查找每个元素右侧第一个比它大的元素
     * @param nums 输入数组
     * @return 返回数组，res[i] 代表 nums[i] 右侧第一个比它大的元素，如果不存在则为 -1
     */
    public int[] findRightGreater(int[] nums) {
        // 结果数组，用于存储右侧第一个比当前元素大的数
        int[] res = new int[nums.length];
        // 单调栈，存储数组中的元素
        Stack<Integer> stack = new Stack<>();

        // 倒序遍历数组，从右向左遍历
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果当前元素比栈顶元素大，弹出栈顶元素，直到找到比当前元素大的数
            while (!stack.isEmpty() && nums[i] > stack.peek()) stack.pop();
            // 记录当前元素右侧的第一个较大元素
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            // 当前元素入栈，确保栈中的元素保持单调递减
            stack.push(nums[i]);
            System.out.println(stack);
        }
        return res;
    }

    /**
     * 查找每个元素右侧第一个比它大的元素 (存索引版本)
     * @param nums 输入数组
     * @return 返回数组，res[i] 代表 nums[i] 右侧第一个比它大的元素，如果不存在则为 -1
     */
    public int[] findRightGreater2(int[] nums) {
        // 结果数组
        int[] res = new int[nums.length];
        // 单调栈，存储的是数组索引
        Stack<Integer> stack = new Stack<>();

        // 正序遍历数组，从左到右遍历
        for (int i = 0; i < nums.length; i++) {
            // 当栈不为空且当前元素比栈顶索引对应的元素大，则栈顶索引元素的答案就是当前元素
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                res[stack.pop()] = nums[i];
            }
            // 所有元素最终都会入栈
            stack.push(i);
        }

        // 遍历完成后，栈中剩余元素说明它们右侧没有更大的元素，填充为 -1
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;
    }

    /**
     * 查找每个元素左侧第一个比它小的元素
     * @param nums 输入数组
     * @return 返回数组，res[i] 代表 nums[i] 左侧第一个比它小的元素，如果不存在则为 -1
     */
    public int[] findLeftSmaller(int[] nums) {
        // 结果数组
        int[] res = new int[nums.length];
        // 单调栈，存储的是数组元素
        Stack<Integer> stack = new Stack<>();

        // 正序遍历数组，从左向右遍历
        for (int i = 0; i < nums.length; i++) {
            // 如果当前元素比栈顶元素小，弹出栈顶元素，直到找到比当前元素小的数
            while (!stack.isEmpty() && nums[i] <= stack.peek()) {
                stack.pop();
            }
            // 记录当前元素左侧的第一个较小元素
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            // 当前元素入栈，确保栈中的元素保持单调递增
            stack.push(nums[i]);
        }
        return res;
    }

    /**
     * 查找每个元素左侧第一个比它小的元素 (存索引版本)
     * @param nums 输入数组
     * @return 返回数组，res[i] 代表 nums[i] 左侧第一个比它小的元素，如果不存在则为 -1
     */
    public int[] findLeftSmaller2(int[] nums) {
        // 结果数组
        int[] res = new int[nums.length];
        // 单调栈，存储的是数组索引
        Stack<Integer> stack = new Stack<>();

        // 倒序遍历数组，从右向左遍历
        for (int i = nums.length - 1; i > -1; i--) {
            // 如果当前元素比栈顶索引对应的元素小，则栈顶索引元素的答案就是当前元素
            while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
                res[stack.pop()] = nums[i];
            }
            // 当前索引入栈，确保栈中的索引对应的值保持单调递增
            stack.push(i);
        }

        // 遍历完成后，栈中剩余索引说明它们左侧没有更小的元素，填充为 -1
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }

        return res;
    }

    public static void main(String[] args) {
        MonotonicStack mStack = new MonotonicStack();

        // 测试样例 1
        int[] nums = {6, 10, 3, 7, 4, 12};
        System.out.println("findRightGreater: " + Arrays.toString(mStack.findRightGreater(nums)));
        // 预期输出: [10, 12, 7, 12, 12, -1]
        System.out.println("findRightGreater2: " + Arrays.toString(mStack.findRightGreater2(nums)));
        // 预期输出: [10, 12, 7, 12, 12, -1]

        // 测试样例 2
        int[] nums1 = {1, 3, 0, 2, 5};
        System.out.println("findLeftSmaller: " + Arrays.toString(mStack.findLeftSmaller(nums1)));
        // 预期输出: [-1, 1, -1, 0, 2]
        System.out.println("findLeftSmaller2: " + Arrays.toString(mStack.findLeftSmaller2(nums1)));
        // 预期输出: [-1, 1, -1, 0, 2]
    }
}

