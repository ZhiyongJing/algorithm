package template;

import java.util.Arrays;
import java.util.Stack;

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

