package leetcode.question.binary_search;

/**
 *@Question:  2064. Minimized Maximum of Products Distributed to Any Store
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 0.0%
 *@Time Complexity: O(NlogK)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目描述
 *
 * 给定一个正整数数组 `quantities`，表示每种产品的需求量，以及一个整数 `n`，表示商店的数量。
 * 我们需要将所有的产品分配给商店，使得**每个商店分配的产品数量的最大值最小**。
 *
 * #### 示例
 *
 * - 输入：`n = 6`, `quantities = [11, 6]`
 * - 输出：`3`
 *   **解释**：可以将产品分配为 `[3, 3, 3, 2]` 给商店，每个商店最多分配 3 个产品。
 *
 * ### 解题思路
 *
 * 这个问题可以归类为**二分查找问题**，我们需要找到一个合适的最大分配量 `x`，使得所有的产品可以在 `n` 个商店中分配完成。
 * 以下是具体解题步骤：
 *
 * ---
 *
 * #### 1. **问题转换**
 * 我们需要找到一个整数 `x`（每个商店分配的最大产品数量），使得：
 * - **最小化 `x`**。
 *
 * - 在 `n` 个商店中完成所有产品分配。
 *
 * 问题的核心是验证某个 `x` 是否满足分配条件。如果可以满足条件，我们尝试更小的 `x`；否则增大 `x`。
 *
 * ---
 *
 * #### 2. **二分查找的边界**
 * - **左边界** `left`：最小可能的最大分配量，即 `1`（每个商店至少分配 1 个产品）。
 * - **右边界** `right`：最大可能的最大分配量，即 `max(quantities)`（所有产品分配到一个商店的极端情况）。
 *
 * ---
 *
 * #### 3. **验证函数 `canDistribute(x)`**
 * 我们定义一个函数 `canDistribute(x)` 来判断，是否可以以 `x` 为最大分配量，将所有产品分配给 `n` 个商店：
 * 1. 遍历 `quantities` 中每种产品需求量 `q`。
 * 2. 对于每个需求量 `q`，计算至少需要多少商店来分配这些产品，公式为：
 *    ```
 *    ceil(q / x) = (q + x - 1) // x
 *    ```
 * 3. 将所有计算出来的商店数量相加。如果总商店数不超过 `n`，返回 `true`，否则返回 `false`。
 *
 * ---
 *
 * #### 4. **二分查找**
 * 通过二分查找，尝试找到满足条件的最小 `x`：
 * 1. 计算中间值 `mid = (left + right) // 2`。
 * 2. 调用 `canDistribute(mid)`：
 *    - 如果返回 `true`，说明可以用更小的 `x`，将 `right` 更新为 `mid`。
 *    - 如果返回 `false`，说明当前 `x` 太小，无法满足条件，将 `left` 更新为 `mid + 1`。
 * 3. 二分结束时，`left` 就是最小的 `x`。
 *
 *
 * 通过二分查找，我们在满足分配条件的情况下，逐渐缩小最大分配量的范围，从而找到最小化后的最大分配量 `x`。
 */

public class LeetCode_2064_MinimizedMaximumOfProductsDistributedToAnyStore{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        /**
         * 判断是否可以以 x 为每个商店的最大分配量，将所有产品分配完
         * @param x 每个商店可以分配的最大产品数量
         * @param quantities 每种产品的需求数量
         * @param n 商店的数量
         * @return 是否可以完成分配
         */
        public boolean canDistribute(int x, int[] quantities, int n) {
            // 指针 j 指向第一个未完全分配的产品种类
            int j = 0;
            // 当前产品种类的剩余需求数量
            int remaining = quantities[j];

            // 遍历每个商店
            for (int i = 0; i < n; i++) {
                // 如果当前产品种类的剩余需求量小于等于 x
                // 表示该商店可以完全分配当前产品种类
                if (remaining <= x) {
                    // 将指针移动到下一个产品种类
                    j++;
                    // 如果所有产品种类都已分配完
                    if (j == quantities.length) {
                        return true; // 返回成功
                    } else {
                        // 更新当前产品种类的剩余需求量
                        remaining = quantities[j];
                    }
                } else {
                    // 当前商店无法完全分配当前产品种类，分配最大可能的数量 x
                    remaining -= x;
                }
            }
            return false; // 如果遍历完所有商店仍有未分配的产品，返回失败
        }

        /**
         * 找到每个商店最大产品分配量的最小值
         * @param n 商店的数量
         * @param quantities 每种产品的需求数量
         * @return 最小化后的每个商店最大分配量
         */
        public int minimizedMaximum(int n, int[] quantities) {
            // 初始化二分查找的边界
            int left = 0; // 左边界表示最低可能的最大值
            int right = 0; // 右边界初始化为产品需求量的最大值

            // 找到需求量的最大值
            for (int quantity : quantities) {
                if (quantity > right) {
                    right = quantity;
                }
            }

            // 进行二分查找
            while (left < right) {
                // 计算中间值
                int middle = (left + right) / 2;
                // 判断是否可以以 middle 为最大分配量完成分配
                if (canDistribute(middle, quantities, n)) {
                    // 如果可以分配，尝试更小的最大分配量
                    right = middle;
                } else {
                    // 如果无法分配，增加最小可能的最大分配量
                    left = middle + 1;
                }
            }
            // 返回最小化的最大分配量
            return left;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_2064_MinimizedMaximumOfProductsDistributedToAnyStore().new Solution();

        // 测试样例
        int n1 = 6;
        int[] quantities1 = {11, 6};
        System.out.println(solution.minimizedMaximum(n1, quantities1));
        // 输出：3（每个商店最多分配3个产品）

        int n2 = 7;
        int[] quantities2 = {15, 10, 10};
        System.out.println(solution.minimizedMaximum(n2, quantities2));
        // 输出：5（每个商店最多分配5个产品）

        int n3 = 1;
        int[] quantities3 = {100};
        System.out.println(solution.minimizedMaximum(n3, quantities3));
        // 输出：100（只有一个商店，分配所有产品）
    }
}

/**
You are given an integer n indicating there are n specialty retail stores. 
There are m product types of varying amounts, which are given as a 0-indexed integer 
array quantities, where quantities[i] represents the number of products of the 
iᵗʰ product type. 

 You need to distribute all products to the retail stores following these rules:
 

 
 A store can only be given at most one product type but can be given any amount 
of it. 
 After distribution, each store will have been given some number of products (
possibly 0). Let x represent the maximum number of products given to any store. 
You want x to be as small as possible, i.e., you want to minimize the maximum 
number of products that are given to any store. 
 

 Return the minimum possible x. 

 
 Example 1: 

 
Input: n = 6, quantities = [11,6]
Output: 3
Explanation: One optimal way is:
- The 11 products of type 0 are distributed to the first four stores in these 
amounts: 2, 3, 3, 3
- The 6 products of type 1 are distributed to the other two stores in these 
amounts: 3, 3
The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.
 

 Example 2: 

 
Input: n = 7, quantities = [15,10,10]
Output: 5
Explanation: One optimal way is:
- The 15 products of type 0 are distributed to the first three stores in these 
amounts: 5, 5, 5
- The 10 products of type 1 are distributed to the next two stores in these 
amounts: 5, 5
- The 10 products of type 2 are distributed to the last two stores in these 
amounts: 5, 5
The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 
5.
 

 Example 3: 

 
Input: n = 1, quantities = [100000]
Output: 100000
Explanation: The only optimal way is:
- The 100000 products of type 0 are distributed to the only store.
The maximum number of products given to any store is max(100000) = 100000.
 

 
 Constraints: 

 
 m == quantities.length 
 1 <= m <= n <= 10⁵ 
 1 <= quantities[i] <= 10⁵ 
 

 Related Topics Array Binary Search Greedy 👍 1680 👎 97

*/