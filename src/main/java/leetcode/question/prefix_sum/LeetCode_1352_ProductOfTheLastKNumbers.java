package leetcode.question.prefix_sum;

import java.util.ArrayList;

/**
 *@Question:  1352. Product of the Last K Numbers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 64.49%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 1352. Product of the Last K Numbers
 *
 * 设计一个支持 **动态插入数字** 和 **查询最后 K 个数字乘积** 的数据结构。
 * - `add(int num)`: 向数据结构中添加一个新的数字 `num`。
 * - `getProduct(int k)`: 返回最后 `k` 个数字的乘积。
 *
 * **特殊情况**：
 * - 若 `k` 超过当前数据结构中的元素数量，返回 `0`。
 * - 若 `num == 0`，所有前缀积将失效，需要特殊处理。
 *
 * 示例：
 * ```plaintext
 * 输入：
 * add(3)
 * add(0)
 * add(2)
 * add(5)
 * add(4)
 * getProduct(2) -> 20  (5 * 4)
 * getProduct(3) -> 40  (2 * 5 * 4)
 * getProduct(4) -> 0   (因 0 存在)
 * add(8)
 * getProduct(2) -> 32  (4 * 8)
 * ```
 *
 * 解题思路：
 * **1. 维护前缀乘积数组（Prefix Product）**
 * - 设 `prefixProduct[i]` 表示前 `i` 个元素的乘积，即：
 *   `prefixProduct[i] = prefixProduct[i-1] * nums[i]`
 * - 这样 `getProduct(k)` 可以通过：
 *   `prefixProduct[n] / prefixProduct[n-k]` 计算得到最后 `k` 个元素的乘积。
 *
 * **2. 处理 `0` 的特殊情况**
 * - 若 `num == 0`，则所有前缀积都会变为 `0`，因此需要清空 `prefixProduct` 并重新开始记录：
 *   - `prefixProduct = [1]`
 *   - 这样 `prefixProduct[i]` 只会记录 **从最后一次 0 之后的数值乘积**。
 * - 在 `getProduct(k)` 时，若 `k > size`，说明 0 曾经出现，直接返回 `0`。
 *
 * **举例分析**
 * ```plaintext
 * add(3)  -> prefixProduct = [1, 3]
 * add(0)  -> prefixProduct = [1]  (清空，因为 0 影响所有乘积)
 * add(2)  -> prefixProduct = [1, 2]
 * add(5)  -> prefixProduct = [1, 2, 10]
 * add(4)  -> prefixProduct = [1, 2, 10, 40]
 * getProduct(2) -> 40 / 10 = 20
 * getProduct(3) -> 40 / 2 = 40
 * getProduct(4) -> 返回 0 (因为 `prefixProduct.size < k`)
 * add(8)  -> prefixProduct = [1, 2, 10, 40, 320]
 * getProduct(2) -> 320 / 40 = 32
 * ```
 *
 * **时间复杂度**
 * - `add(num)`: **O(1)**（仅需追加计算乘积）
 * - `getProduct(k)`: **O(1)**（除法计算）
 * - **总时间复杂度：O(1)**
 *
 * **空间复杂度**
 * - 额外存储前缀积 `prefixProduct`，最多 O(N)
 * - **总空间复杂度：O(N)**
 */


public class LeetCode_1352_ProductOfTheLastKNumbers{

    //leetcode submit region begin(Prohibit modification and deletion)
    class ProductOfNumbers {

        // 存储前缀积（prefixProduct[i] 表示前 i 个元素的乘积）
        private ArrayList<Integer> prefixProduct = new ArrayList<>();
        // 记录当前前缀积数组的大小
        private int size = 0;

        public ProductOfNumbers() {
            // 初始化前缀积数组，防止乘法计算时出现错误
            this.prefixProduct.add(1);
            this.size = 0;
        }

        public void add(int num) {
            if (num == 0) {
                // 如果添加 0，则需要清空前缀积数组
                // 因为 0 之后的任何乘积都无效，所以重新初始化数组
                this.prefixProduct = new ArrayList<Integer>();
                this.prefixProduct.add(1);
                this.size = 0;
            } else {
                // 计算新数与当前前缀积的乘积，并存入数组
                this.prefixProduct.add(this.prefixProduct.get(size) * num);
                this.size++;
            }
        }

        public int getProduct(int k) {
            // 如果 k 超过当前数组大小，说明前面曾经出现过 0，乘积结果一定是 0
            if (k > this.size) return 0;

            // 通过除法计算最后 k 个数的乘积
            return (
                    this.prefixProduct.get(this.size) /
                            this.prefixProduct.get(this.size - k)
            );
        }
    }

    /**
     * Your ProductOfNumbers object will be instantiated and called as such:
     * ProductOfNumbers obj = new ProductOfNumbers();
     * obj.add(num);
     * int param_2 = obj.getProduct(k);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        ProductOfNumbers solution = new LeetCode_1352_ProductOfTheLastKNumbers().new ProductOfNumbers();

        // 测试样例
        solution.add(3);
        solution.add(0);
        solution.add(2);
        solution.add(5);
        solution.add(4);

        System.out.println(solution.getProduct(2)); // 预期输出: 20 (5 * 4)
        System.out.println(solution.getProduct(3)); // 预期输出: 40 (2 * 5 * 4)
        System.out.println(solution.getProduct(4)); // 预期输出: 0 (因为存在 0)

        solution.add(8);
        System.out.println(solution.getProduct(2)); // 预期输出: 32 (4 * 8)
    }
}

/**
Design an algorithm that accepts a stream of integers and retrieves the product 
of the last k integers of the stream. 

 Implement the ProductOfNumbers class: 

 
 ProductOfNumbers() Initializes the object with an empty stream. 
 void add(int num) Appends the integer num to the stream. 
 int getProduct(int k) Returns the product of the last k numbers in the current 
list. You can assume that always the current list has at least k numbers. 
 

 The test cases are generated so that, at any time, the product of any 
contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.
 

 
 Example: 

 
Input
["ProductOfNumbers","add","add","add","add","add","getProduct","getProduct",
"getProduct","add","getProduct"]
[[],[3],[0],[2],[5],[4],[2],[3],[4],[8],[2]]

Output
[null,null,null,null,null,null,20,40,0,null,32]

Explanation
ProductOfNumbers productOfNumbers = new ProductOfNumbers();
productOfNumbers.add(3);        // [3]
productOfNumbers.add(0);        // [3,0]
productOfNumbers.add(2);        // [3,0,2]
productOfNumbers.add(5);        // [3,0,2,5]
productOfNumbers.add(4);        // [3,0,2,5,4]
productOfNumbers.getProduct(2); // return 20. The product of the last 2 numbers 
is 5 * 4 = 20
productOfNumbers.getProduct(3); // return 40. The product of the last 3 numbers 
is 2 * 5 * 4 = 40
productOfNumbers.getProduct(4); // return 0. The product of the last 4 numbers 
is 0 * 2 * 5 * 4 = 0
productOfNumbers.add(8);        // [3,0,2,5,4,8]
productOfNumbers.getProduct(2); // return 32. The product of the last 2 numbers 
is 4 * 8 = 32 
 

 
 Constraints: 

 
 0 <= num <= 100 
 1 <= k <= 4 * 10⁴ 
 At most 4 * 10⁴ calls will be made to add and getProduct. 
 The product of the stream at any point in time will fit in a 32-bit integer. 
 

 
Follow-up: Can you implement 
both 
GetProduct and 
Add to work in 
O(1) time complexity instead of 
O(k) time complexity?

 Related Topics Array Math Design Data Stream Prefix Sum 👍 2079 👎 104

*/