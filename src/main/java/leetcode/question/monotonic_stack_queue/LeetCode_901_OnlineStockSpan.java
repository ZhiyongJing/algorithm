package leetcode.question.monotonic_stack_queue;

import java.util.Stack;

/**
 *@Question:  901. Online Stock Span
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 36.22%
 *@Time  Complexity: O(1) for next
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路详细讲解
 *
 * #### 问题理解
 * - 题目要求我们设计一个类 `StockSpanner`，它支持计算股票价格的跨度。
 * - 股票价格的跨度定义为：从当前价格往前看，有多少天的价格是小于等于当前价格的，包含当前这一天。
 *
 * #### 单调栈的应用
 * - 为了解决这个问题，我们使用单调递减栈来帮助计算。
 * - 单调递减栈：栈内的元素按照从栈底到栈顶递减的顺序排列。这意味着，当新价格比栈顶元素大时，我们可以不断地弹出栈顶元素，直到栈顶元素大于新价格为止。
 *
 * #### 算法步骤
 * 1. **初始化栈**：
 *    - 创建一个栈 `stack`，用于存储股票价格和对应的跨度。栈中的每个元素都是一个二元数组 `[price, span]`，表示价格 `price` 和它的跨度 `span`。
 *
 * 2. **处理新价格**：
 *    - 定义 `next` 方法，该方法接受一个参数 `price`，表示新的股票价格。
 *    - 初始化跨度 `span` 为 1，因为当前价格至少自己包括在跨度内。
 *    - 使用循环，判断栈是否为空且栈顶元素的价格小于等于当前价格。如果满足条件，弹出栈顶元素，并累加弹出元素的跨度到 `span`。
 *    - 当栈顶元素的价格大于当前价格时，停止弹栈，将当前价格和累计的跨度 `span` 压入栈中。
 *    - 返回当前价格的跨度 `span`。
 *
 * 3. **时间复杂度**：
 *    - 每个价格最多会被压入和弹出栈一次，因此对于每个调用 `next` 方法的时间复杂度是 O(1)。
 *
 * 4. **空间复杂度**：
 *    - 我们使用了一个栈来存储价格和跨度，对于 N 次调用 `next` 方法，最坏情况下需要存储所有的价格和跨度，因此空间复杂度是 O(N)。
 *
 * ### 总结
 * - 通过使用单调栈，我们可以在常数时间内处理每个新的股票价格，确保算法在时间和空间上的高效性。
 * - 每次调用 `next` 方法时，我们通过弹出栈顶元素，累加跨度，直到栈顶元素的价格大于当前价格，最后将当前价格和累计的跨度压入栈中。这种方法使得我们能够快速计算股票价格的跨度。
 */
public class LeetCode_901_OnlineStockSpan{

    //leetcode submit region begin(Prohibit modification and deletion)
    class StockSpanner {
        // 创建一个栈，用于存储股票价格和跨度的数组
        Stack<int[]> stack = new Stack<>();

        // 定义 next 方法，接受一个股票价格并返回股票价格的跨度
        public int next(int price) {
            int ans = 1; // 初始跨度为 1
            // 当栈不为空且栈顶元素的价格小于等于当前价格时，进行弹栈操作
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                ans += stack.pop()[1]; // 更新跨度，增加弹出元素的跨度
            }

            // 将当前价格和对应的跨度压入栈中
            stack.push(new int[] {price, ans});
            return ans; // 返回当前价格的跨度
        }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建 StockSpanner 对象
        StockSpanner stockSpanner = new LeetCode_901_OnlineStockSpan().new StockSpanner();

        // 测试样例
        System.out.println(stockSpanner.next(100)); // 应该输出 1
        System.out.println(stockSpanner.next(80));  // 应该输出 1
        System.out.println(stockSpanner.next(60));  // 应该输出 1
        System.out.println(stockSpanner.next(70));  // 应该输出 2
        System.out.println(stockSpanner.next(60));  // 应该输出 1
        System.out.println(stockSpanner.next(75));  // 应该输出 4
        System.out.println(stockSpanner.next(85));  // 应该输出 6
    }
}

/**
Design an algorithm that collects daily price quotes for some stock and returns 
the span of that stock's price for the current day. 

 The span of the stock's price in one day is the maximum number of consecutive 
days (starting from that day and going backward) for which the stock price was 
less than or equal to the price of that day. 

 
 For example, if the prices of the stock in the last four days is [7,2,1,2] and 
the price of the stock today is 2, then the span of today is 4 because starting 
from today, the price of the stock was less than or equal 2 for 4 consecutive 
days. 
 Also, if the prices of the stock in the last four days is [7,34,1,2] and the 
price of the stock today is 8, then the span of today is 3 because starting from 
today, the price of the stock was less than or equal 8 for 3 consecutive days. 
 

 Implement the StockSpanner class: 

 
 StockSpanner() Initializes the object of the class. 
 int next(int price) Returns the span of the stock's price given that today's 
price is price. 
 

 
 Example 1: 

 
Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including 
today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6
 

 
 Constraints: 

 
 1 <= price <= 10⁵ 
 At most 10⁴ calls will be made to next. 
 

 Related Topics Stack Design Monotonic Stack Data Stream 👍 6266 👎 403

*/