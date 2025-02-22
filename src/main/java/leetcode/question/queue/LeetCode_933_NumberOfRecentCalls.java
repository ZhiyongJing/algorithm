package leetcode.question.queue;

import java.util.LinkedList;

/**
 *@Question:  933. Number of Recent Calls
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.8%
 *@Time  Complexity: O(N)  // 每次调用 `ping()` 可能会删除多个过期的时间戳
 *@Space Complexity: O(N)  // 需要存储过去 3000ms 内的所有请求
 */
/**
 * 1. 题目描述:
 *    - 设计一个 **RecentCounter** 类来计算 **最近 3000ms 内的请求数量**。
 *    - `RecentCounter` 需要支持 `ping(int t)` 方法：
 *      - `t` 表示请求的时间（单位：毫秒）。
 *      - 返回 **最近 3000ms 内** 的所有 `ping()` 请求数。
 *    - 例如:
 *      输入：
 *        ["RecentCounter", "ping", "ping", "ping", "ping"]
 *        [[], [1], [100], [3001], [3002]]
 *      输出：
 *        [null, 1, 2, 3, 3]
 *
 *    **解释:**
 *      - `ping(1)`  ->  返回 1  (队列: [1])
 *      - `ping(100)` ->  返回 2  (队列: [1, 100])
 *      - `ping(3001)` -> 返回 3  (队列: [1, 100, 3001])
 *      - `ping(3002)` -> 返回 3  (队列: [100, 3001, 3002])
 *
 *    **约束:**
 *      - `1 <= t <= 10^9`
 *      - 每次调用 `ping(t)`，保证 `t` **严格递增**（不会有乱序）。
 *      - 最多调用 `ping()` 10^4 次。
 */

/**
 * 2. 解题思路:
 *    由于 `ping()` 方法会持续接收时间递增的 `t`，我们需要一个数据结构来存储最近 `3000ms` 内的请求时间，并快速计算数量。
 *
 *    **解法: 使用队列（FIFO - 先进先出）**
 *    - 采用 **LinkedList（双端队列）** 存储请求时间，保证插入和删除操作高效。
 *    - **核心逻辑**
 *      1. **存储当前请求时间 `t`** 到队列末尾。
 *      2. **删除所有 `t-3000` 之前的时间**，保证队列只包含最近 `3000ms` 内的请求。
 *      3. **返回队列的大小**（即最近 3000ms 内的请求数量）。
 *
 *    **示例解析:**
 *
 *    **初始状态（空队列）**
 *      - 调用 `ping(1)`:
 *        - 添加 `1`，无过期时间戳
 *        - 队列 `[1]`
 *        - 返回 `1`
 *
 *    **第二次调用 `ping(100)`**
 *      - 添加 `100`
 *      - `100 - 3000 = -2900`，无过期元素
 *      - 队列 `[1, 100]`
 *      - 返回 `2`
 *
 *    **第三次调用 `ping(3001)`**
 *      - 添加 `3001`
 *      - `3001 - 3000 = 1`，仍然在窗口内
 *      - 队列 `[1, 100, 3001]`
 *      - 返回 `3`
 *
 *    **第四次调用 `ping(3002)`**
 *      - 添加 `3002`
 *      - `3002 - 3000 = 2`，仍然在窗口内
 *      - 队列 `[100, 3001, 3002]`（移除 `1`）
 *      - 返回 `3`
 *
 *    **关键优化:**
 *      - **窗口滑动**: 每次 `ping()` 调用时，只需删除最早过期的数据（队列头部）。
 *      - **`LinkedList` 头部删除 (`removeFirst()`) 时间复杂度 O(1)**，保持高效。
 */

/**
 * 3. 时间和空间复杂度分析:
 *    - **时间复杂度：O(N)**
 *      - `ping(t)` 操作需要:
 *        1. **添加请求时间** 到队列末尾（O(1)）
 *        2. **删除所有过期请求**（O(N) 最坏情况）
 *        3. **计算队列大小**（O(1)）
 *      - 由于 `t` 是递增的，**每个请求最多被加入和移除一次**，因此摊销时间复杂度为 **O(1)**。
 *
 *    - **空间复杂度：O(N)**
 *      - **最坏情况下**，`ping()` 存储最近 `3000ms` 内的 `N` 个请求。
 *      - 因此，最大空间为 **O(N)**，其中 `N` 是 `3000ms` 内的请求数量。
 */


public class LeetCode_933_NumberOfRecentCalls{

    //leetcode submit region begin(Prohibit modification and deletion)
    class RecentCounter {
        // 使用 LinkedList 作为滑动窗口，存储最近 3000ms 内的请求时间
        LinkedList<Integer> slideWindow;

        public RecentCounter() {
            // 初始化滑动窗口
            this.slideWindow = new LinkedList<Integer>();
        }

        public int ping(int t) {
            // step 1). 将当前请求时间 t 添加到队列末尾
            this.slideWindow.addLast(t);

            // step 2). 移除所有 3000ms 之外的请求
            while (this.slideWindow.getFirst() < t - 3000)
                this.slideWindow.removeFirst();  // 移除队列头部的过期请求

            // step 3). 返回滑动窗口中剩余的请求数
            return this.slideWindow.size();
        }
    }

    /**
     * Your RecentCounter object will be instantiated and called as such:
     * RecentCounter obj = new RecentCounter();
     * int param_1 = obj.ping(t);
     */
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        RecentCounter recentCounter = new LeetCode_933_NumberOfRecentCalls().new RecentCounter();

        // 测试样例
        System.out.println(recentCounter.ping(1));     // 1
        System.out.println(recentCounter.ping(100));   // 2
        System.out.println(recentCounter.ping(3001));  // 3
        System.out.println(recentCounter.ping(3002));  // 3
    }
}

/**
 You have a RecentCounter class which counts the number of recent requests
 within a certain time frame.

 Implement the RecentCounter class: 


 RecentCounter() Initializes the counter with zero recent requests. 
 int ping(int t) Adds a new request at time t, where t represents some time in 
 milliseconds, and returns the number of requests that has happened in the past 30
 00 milliseconds (including the new request). Specifically, return the number of
 requests that have happened in the inclusive range [t - 3000, t].


 It is guaranteed that every call to ping uses a strictly larger value of t 
 than the previous call.


 Example 1: 


 Input
 ["RecentCounter", "ping", "ping", "ping", "ping"]
 [[], [1], [100], [3001], [3002]]
 Output
 [null, 1, 2, 3, 3]

 Explanation
 RecentCounter recentCounter = new RecentCounter();
 recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return
 2
 recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001],
 return 3
 recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002]
 , return 3



 Constraints: 


 1 <= t <= 10⁹ 
 Each test case will call ping with strictly increasing values of t. 
 At most 10⁴ calls will be made to ping. 


 Related Topics Design Queue Data Stream 👍 655 👎 1015

 */