package leetcode.question.prefix_sum;
/**
 *@Question:  303. Range Sum Query - Immutable
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.26%
 *@Time  Complexity: O(1) time per query, O(N) time pre-computation.
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 303. Range Sum Query - Immutable
 *
 * 设计一个支持区间和查询的数据结构，要求：
 * - `NumArray(int[] nums)`: 初始化对象，并预处理 `nums` 以支持高效的区间和查询。
 * - `int sumRange(int left, int right)`: 返回 `nums[left]` 到 `nums[right]` 之间所有元素的和。
 *
 * **限制条件**
 * - `nums.length ≤ 10^4`
 * - `sumRange` 可能会被调用 **10^4 次**
 * - 需要在 **O(1)** 时间内求解区间和
 *
 * **示例**
 * ```plaintext
 * 输入:
 * nums = [-2, 0, 3, -5, 2, -1]
 * sumRange(0, 2) -> 1  (-2 + 0 + 3)
 * sumRange(2, 5) -> -1 (3 + (-5) + 2 + (-1))
 * sumRange(0, 5) -> -3 (-2 + 0 + 3 - 5 + 2 - 1)
 * ```
 *
 * 解题思路：
 * **1. 前缀和数组（Prefix Sum）**
 * - 维护一个 **前缀和数组 `sum[]`**，其中：
 *   `sum[i]` 代表 `nums[0]` 到 `nums[i-1]` 的累加和。
 * - `sum[i] = sum[i-1] + nums[i-1]`
 * - 这样我们可以 **O(1) 时间计算区间和**：
 *   `sumRange(i, j) = sum[j+1] - sum[i]`
 *
 * **2. 初始化 `sum[]`**
 * - 额外构造 `sum[]` 数组，`sum[0] = 0`，使得 `sum[i]` 存储 **`nums[0]` 到 `nums[i-1]` 的累加和**。
 * - 例如 `nums = [-2, 0, 3, -5, 2, -1]`：
 * ```plaintext
 * nums:      [-2,  0,  3, -5,  2, -1]
 * sum[]:  [0, -2, -2,  1, -4, -2, -3]
 * ```
 * - 查询 `sumRange(2, 5) = sum[6] - sum[2] = -3 - (-2) = -1`
 *
 * **3. 举例说明**
 * ```plaintext
 * nums = [-2, 0, 3, -5, 2, -1]
 * sum[0] = 0
 * sum[1] = -2  (nums[0] = -2)
 * sum[2] = -2  (-2 + 0)
 * sum[3] = 1   (-2 + 0 + 3)
 * sum[4] = -4  (-2 + 0 + 3 - 5)
 * sum[5] = -2  (-2 + 0 + 3 - 5 + 2)
 * sum[6] = -3  (-2 + 0 + 3 - 5 + 2 - 1)
 * ```
 *
 * **4. 计算区间和**
 * ```plaintext
 * sumRange(0, 2) = sum[3] - sum[0] = 1 - 0 = 1
 * sumRange(2, 5) = sum[6] - sum[2] = -3 - (-2) = -1
 * sumRange(0, 5) = sum[6] - sum[0] = -3 - 0 = -3
 * ```
 *
 * **时间复杂度分析**
 * - **预处理 `sum[]`：O(N)**
 * - **查询 `sumRange()`：O(1)**
 * - **总时间复杂度：O(N) 预处理，O(1) 查询**
 *
 * **空间复杂度分析**
 * - **存储 `sum[]` 数组：O(N)**
 * - **总空间复杂度：O(N)**
 */


public class LeetCode_303_RangeSumQueryImmutable{

    //leetcode submit region begin(Prohibit modification and deletion)
    class NumArray {

        // 存储前缀和数组，sum[i] 表示 nums[0] 到 nums[i-1] 的累加和
        private int[] sum;

        public NumArray(int[] nums) {
            // 初始化前缀和数组，大小比 nums 数组大 1
            sum = new int[nums.length + 1];
            // 计算前缀和，sum[i+1] 存储的是 nums[0] 到 nums[i] 的累积和
            for (int i = 0; i < nums.length; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            // 计算区间 [i, j] 的和，使用前缀和数组进行 O(1) 计算
            return sum[j + 1] - sum[i];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(left,right);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试样例
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray solution = new LeetCode_303_RangeSumQueryImmutable().new NumArray(nums);

        // 测试 sumRange 方法
        System.out.println(solution.sumRange(0, 2)); // 预期输出: 1 (-2 + 0 + 3)
        System.out.println(solution.sumRange(2, 5)); // 预期输出: -1 (3 + (-5) + 2 + (-1))
        System.out.println(solution.sumRange(0, 5)); // 预期输出: -3 (-2 + 0 + 3 - 5 + 2 - 1)
    }
}

/**
Given an integer array nums, handle multiple queries of the following type: 

 
 Calculate the sum of the elements of nums between indices left and right 
inclusive where left <= right. 
 

 Implement the NumArray class: 

 
 NumArray(int[] nums) Initializes the object with the integer array nums. 
 int sumRange(int left, int right) Returns the sum of the elements of nums 
between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + 
nums[right]). 
 

 
 Example 1: 

 
Input
["NumArray", "sumRange", "sumRange", "sumRange"]
[[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
Output
[null, 1, -1, -3]

Explanation
NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 

 
 Constraints: 

 
 1 <= nums.length <= 10⁴ 
 -10⁵ <= nums[i] <= 10⁵ 
 0 <= left <= right < nums.length 
 At most 10⁴ calls will be made to sumRange. 
 

 Related Topics Array Design Prefix Sum 👍 3409 👎 1948

*/