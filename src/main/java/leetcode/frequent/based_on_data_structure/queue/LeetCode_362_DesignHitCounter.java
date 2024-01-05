package leetcode.frequent.based_on_data_structure.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Question: 362. Design Hit Counter
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 77.51%
 * @Time Complexity: O(1) for hit, O(n) for getHits where n is the number of hits in the last 5 minutes
 * @Space Complexity: O(n) where n is the number of hits in the last 5 minutes
 */

/**
 * 这段代码实现了一个简单的击打计数器，主要记录击打的时间戳并在需要时计算过去5分钟内的击打次数。以下是解题思路和复杂度分析：
 *
 * **解题思路：**
 *
 * 1. 使用一个队列（`Queue<Integer> hits`）来存储每次击打的时间戳，队列头部存储的是最早的时间戳。
 *
 * 2. `hit` 方法：将当前时间戳添加到队列中，记录击打事件。
 *
 * 3. `getHits` 方法：遍历队列，移除队列头部超过5分钟的时间戳，然后返回队列的大小，即过去5分钟内的击打次数。
 *
 * **时间复杂度：**
 *
 * - `hit` 方法的时间复杂度是 O(1)，因为只是将时间戳添加到队列中，不依赖于击打次数。
 * - `getHits` 方法的时间复杂度是 O(n)，其中 n 是过去5分钟内的击打次数。在最坏情况下，需要遍历整个队列。
 *
 * **空间复杂度：**
 *
 * - 使用了一个队列存储击打的时间戳，所以空间复杂度是 O(n)，其中 n 是过去5分钟内的击打次数。
 *
 * 这个设计的思路比较直观，通过队列存储时间戳，能够方便地计算过去5分钟内的击打次数。需要注意的是，
 * 由于队列只存储了过去5分钟内的击打时间戳，因此对于长时间的击打历史，空间开销是可控的。
 */

public class LeetCode_362_DesignHitCounter {

    // leetcode submit region begin(Prohibit modification and deletion)
    class HitCounter {
        private Queue<Integer> hits;

        /** Initialize your data structure here. */
        public HitCounter() {
            // 使用队列来存储击打的时间戳
            this.hits = new LinkedList<Integer>();
        }

        /**
         * 记录一次击打
         *
         * @param timestamp 当前时间戳（以秒为单位）
         */
        public void hit(int timestamp) {
            this.hits.add(timestamp);
        }

        /**
         * 获取过去5分钟内的击打次数
         *
         * @param timestamp 当前时间戳（以秒为单位）
         * @return 过去5分钟内的击打次数
         */
        public int getHits(int timestamp) {
            // 遍历队列，移除超过5分钟的时间戳
            while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.peek();
                if (diff >= 300) this.hits.remove();
                else break;
            }
            // 返回过去5分钟内的击打次数
            return this.hits.size();
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        HitCounter hitCounter = new LeetCode_362_DesignHitCounter().new HitCounter();
        // 记录3次击打
        hitCounter.hit(1);
        hitCounter.hit(2);
        hitCounter.hit(3);
        // 获取过去5分钟内的击打次数（应为3）
        int hitsInLast5Minutes = hitCounter.getHits(4);
        System.out.println("Hits in the last 5 minutes: " + hitsInLast5Minutes);

        // 记录2次击打
        hitCounter.hit(300);
        hitCounter.hit(301);
        // 获取过去5分钟内的击打次数（应为2）
        hitsInLast5Minutes = hitCounter.getHits(302);
        System.out.println("Hits in the last 5 minutes: " + hitsInLast5Minutes);
    }
}

/**
Design a hit counter which counts the number of hits received in the past 5 
minutes (i.e., the past 300 seconds). 

 Your system should accept a timestamp parameter (in seconds granularity), and 
you may assume that calls are being made to the system in chronological order (i.
e., timestamp is monotonically increasing). Several hits may arrive roughly at 
the same time. 

 Implement the HitCounter class: 

 
 HitCounter() Initializes the object of the hit counter system. 
 void hit(int timestamp) Records a hit that happened at timestamp (in seconds). 
Several hits may happen at the same timestamp. 
 int getHits(int timestamp) Returns the number of hits in the past 5 minutes 
from timestamp (i.e., the past 300 seconds). 
 

 
 Example 1: 

 
Input
["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
[[], [1], [2], [3], [4], [300], [300], [301]]
Output
[null, null, null, null, 3, null, 4, 3]

Explanation
HitCounter hitCounter = new HitCounter();
hitCounter.hit(1);       // hit at timestamp 1.
hitCounter.hit(2);       // hit at timestamp 2.
hitCounter.hit(3);       // hit at timestamp 3.
hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
hitCounter.hit(300);     // hit at timestamp 300.
hitCounter.getHits(300); // get hits at timestamp 300, return 4.
hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 

 
 Constraints: 

 
 1 <= timestamp <= 2 * 10⁹ 
 All the calls are being made to the system in chronological order (i.e., 
timestamp is monotonically increasing). 
 At most 300 calls will be made to hit and getHits. 
 

 
 Follow up: What if the number of hits per second could be huge? Does your 
design scale? 

 Related Topics Array Hash Table Binary Search Design Queue 👍 1976 👎 222

*/