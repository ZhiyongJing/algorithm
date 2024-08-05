package leetcode.question.map_set;

import java.util.HashMap;

/**
 *@Question:  359. Logger Rate Limiter
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 89.82%
 *@Time  Complexity: O(1) // 插入和查找操作在 HashMap 中的时间复杂度是 O(1)
 *@Space Complexity: O(N) // 空间复杂度取决于存储的消息数量
 */

/**
 * ### 解题思路
 *
 * #### 问题描述
 * 我们需要实现一个日志记录器 `Logger`，它能在指定的时间戳下决定是否可以打印一条消息。
 * 每条消息在 10 秒内只能打印一次。如果消息的时间间隔大于等于 10 秒，消息可以被打印；否则，消息被忽略。
 *
 * #### 解题思路
 *
 * 1. **数据结构选择**：
 *    - 采用 `HashMap` 存储每条消息及其最后打印的时间戳。这样可以在常数时间内进行插入和查询操作。
 *
 * 2. **实现步骤**：
 *    - **初始化**：
 *      - 创建一个 `HashMap` 用于存储每条消息的时间戳。
 *
 *    - **消息处理**：
 *      - **检查消息是否存在**：
 *        - 如果消息不在 `HashMap` 中，说明这是消息第一次出现。记录当前时间戳，并返回 `true`，表示可以打印。
 *        - 如果消息已存在，获取其最后打印的时间戳。
 *      - **检查时间间隔**：
 *        - 计算当前时间戳与消息上次打印时间戳之间的差值。如果差值大于等于 10 秒，则更新 `HashMap` 中的时间戳，并返回 `true`。
 *        - 否则，返回 `false`，表示当前时间间隔不足 10 秒，不能打印。
 *
 * ### 时间复杂度和空间复杂度
 *
 * - **时间复杂度**：`O(1)`
 *   - 对于 `HashMap` 的 `containsKey`、`get` 和 `put` 操作，它们的平均时间复杂度都是 `O(1)`。因此，`shouldPrintMessage` 方法的时间复杂度为 `O(1)`。
 *
 * - **空间复杂度**：`O(N)`
 *   - `HashMap` 需要存储所有消息及其时间戳，其中 `N` 是消息的数量。空间复杂度因此取决于 `HashMap` 中存储的消息条数。
 */
public class LeetCode_359_LoggerRateLimiter{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Logger {
        private HashMap<String, Integer> msgDict; // 存储每条消息及其最后打印的时间戳

        /** Initialize your data structure here. */
        public Logger() {
            msgDict = new HashMap<String, Integer>(); // 初始化 HashMap
        }

        /**
         * Returns true if the message should be printed in the given timestamp, otherwise returns false.
         */
        public boolean shouldPrintMessage(int timestamp, String message) {
            // 检查消息是否在 msgDict 中
            if (!this.msgDict.containsKey(message)) {
                this.msgDict.put(message, timestamp); // 如果消息不存在，记录当前时间戳
                return true;
            }

            Integer oldTimestamp = this.msgDict.get(message); // 获取消息的最后时间戳
            // 判断当前时间戳和消息的最后时间戳之间的差是否大于等于 10
            if (timestamp - oldTimestamp >= 10) {
                this.msgDict.put(message, timestamp); // 更新消息的时间戳
                return true; // 可以打印消息
            } else
                return false; // 不能打印消息
        }
    }

    /**
     * Your Logger object will be instantiated and called as such:
     * Logger obj = new Logger();
     * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Logger logger = new LeetCode_359_LoggerRateLimiter().new Logger();

        // 测试代码
        System.out.println(logger.shouldPrintMessage(1, "foo")); // 输出：true
        System.out.println(logger.shouldPrintMessage(2, "bar")); // 输出：true
        System.out.println(logger.shouldPrintMessage(3, "foo")); // 输出：false
        System.out.println(logger.shouldPrintMessage(8, "bar")); // 输出：false
        System.out.println(logger.shouldPrintMessage(10, "foo")); // 输出：true
        System.out.println(logger.shouldPrintMessage(11, "foo")); // 输出：true
    }
}

/**
Design a logger system that receives a stream of messages along with their 
timestamps. Each unique message should only be printed at most every 10 seconds (i.e.
 a message printed at timestamp t will prevent other identical messages from 
being printed until timestamp t + 10). 

 All messages will come in chronological order. Several messages may arrive at 
the same timestamp. 

 Implement the Logger class: 

 
 Logger() Initializes the logger object. 
 bool shouldPrintMessage(int timestamp, string message) Returns true if the 
message should be printed in the given timestamp, otherwise returns false. 
 

 
 Example 1: 

 
Input
["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", 
"shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage"]
[[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
Output
[null, true, true, false, false, false, true]

Explanation
Logger logger = new Logger();
logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp 
for "foo" is 1 + 10 = 11
logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp 
for "bar" is 2 + 10 = 12
logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed 
timestamp for "foo" is 11 + 10 = 21
 

 
 Constraints: 

 
 0 <= timestamp <= 10⁹ 
 Every timestamp will be passed in non-decreasing order (chronological order). 
 1 <= message.length <= 30 
 At most 10⁴ calls will be made to shouldPrintMessage. 
 

 Related Topics Hash Table Design Data Stream 👍 1733 👎 190

*/