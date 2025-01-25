package leetcode.question.map_set;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
/**
 *@Question:  1244. Design A Leaderboard
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 37.85%
 *@Time  Complexity: O(1) for add score, O(1) for reset, O(N * logK) for top
 *@Space Complexity: O(N + K)
 */

/**
 * 这道题目是要设计一个排行榜系统，其中涉及到添加玩家得分、获取最高得分以及重置玩家得分等操作。
 *
 * ### 解题思路
 *
 * 1. **数据结构选择**：使用HashMap来存储每个玩家的得分，其中键是玩家的ID，值是玩家的得分。
 * 2. **添加得分操作**：当要添加玩家得分时，首先检查该玩家是否已存在于HashMap中，如果不存在，则将玩家ID添加到HashMap中，并将其初始得分设置为0，然后更新该玩家的得分。
 * 3. **获取最高得分操作**：使用最小堆（优先队列）来维护最高的K个得分，遍历所有玩家的得分并将其添加到堆中，保持堆的大小不超过K。然后，计算堆中所有元素的总和即为最高的K个玩家的总分。
 * 4. **重置玩家得分操作**：重置指定玩家的得分为0。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：
 *   - 添加得分操作：O(1)，因为HashMap的插入和更新操作都是O(1)的。
 *   - 获取最高得分操作：O(N log K)，其中N是玩家的数量，因为需要遍历所有玩家的得分并维护一个大小为K的堆。
 *   - 重置玩家得分操作：O(1)，因为HashMap的更新操作是O(1)的。
 *
 * - **空间复杂度**：
 *   - O(N)，其中N是玩家的数量，因为需要存储每个玩家的得分。
 */
public class LeetCode_1244_DesignALeaderboard{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Leaderboard {

        // 使用HashMap来记录每个玩家的分数
        private HashMap<Integer, Integer> scores;

        // 初始化Leaderboard对象
        public Leaderboard() {
            this.scores = new HashMap<Integer, Integer>();
        }

        // 添加玩家得分，如果玩家不在记录中，先添加玩家并初始化分数为0
        public void addScore(int playerId, int score) {
            if (!this.scores.containsKey(playerId)) {
                this.scores.put(playerId, 0);
            }
            // 更新玩家分数
            this.scores.put(playerId, this.scores.get(playerId) + score);
        }

        // 返回最高的K个玩家的总分
        public int top(int K) {
            // 使用最小堆来记录最高的K个分数
            PriorityQueue<Map.Entry<Integer, Integer>> heap = new PriorityQueue<>(
                    (a, b) -> a.getValue() - b.getValue()
            );

            // 遍历所有玩家的分数并添加到堆中
            for (Map.Entry<Integer, Integer> entry : this.scores.entrySet()) {
                heap.offer(entry);
                // 保持堆的大小为K
                if (heap.size() > K) {
                    heap.poll();
                }
            }

            // 计算最高的K个玩家的总分
            int total = 0;
            Iterator<Map.Entry<Integer, Integer>> value = heap.iterator();
            while (value.hasNext()) {
                total += value.next().getValue();
            }

            return total;
        }

        // 重置指定玩家的分数
        public void reset(int playerId) {
            this.scores.put(playerId, 0);
        }
    }

    /**
     * Your Leaderboard object will be instantiated and called as such:
     * Leaderboard obj = new Leaderboard();
     * obj.addScore(playerId,score);
     * int param_2 = obj.top(K);
     * obj.reset(playerId);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Leaderboard leaderboard = new LeetCode_1244_DesignALeaderboard().new Leaderboard();

        // 添加一些玩家的得分
        leaderboard.addScore(1, 73);
        leaderboard.addScore(2, 56);
        leaderboard.addScore(3, 39);
        leaderboard.addScore(4, 51);
        leaderboard.addScore(5, 4);

        // 获取最高的3个玩家的总分
        int top3 = leaderboard.top(3);
        System.out.println("Top 3 total score: " + top3);  // 应该输出 180

        // 重置玩家3的分数
        leaderboard.reset(3);

        // 添加玩家3的新得分
        leaderboard.addScore(3, 63);

        // 获取最高的3个玩家的总分
        top3 = leaderboard.top(3);
        System.out.println("Top 3 total score after reset and new score: " + top3);  // 应该输出 191
    }
}

/**
Design a Leaderboard class, which has 3 functions: 

 
 addScore(playerId, score): Update the leaderboard by adding score to the given 
player's score. If there is no player with such id in the leaderboard, add him 
to the leaderboard with the given score. 
 top(K): Return the score sum of the top K players. 
 reset(playerId): Reset the score of the player with the given id to 0 (in 
other words erase it from the leaderboard). It is guaranteed that the player was 
added to the leaderboard before calling this function. 
 

 Initially, the leaderboard is empty. 

 
 Example 1: 

 
Input: 
["Leaderboard","addScore","addScore","addScore","addScore","addScore","top",
"reset","reset","addScore","top"]
[[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
Output: 
[null,null,null,null,null,null,73,null,null,null,141]

Explanation: 
Leaderboard leaderboard = new Leaderboard ();
leaderboard.addScore(1,73);   // leaderboard = [[1,73]];
leaderboard.addScore(2,56);   // leaderboard = [[1,73],[2,56]];
leaderboard.addScore(3,39);   // leaderboard = [[1,73],[2,56],[3,39]];
leaderboard.addScore(4,51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
leaderboard.addScore(5,4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4
]];
leaderboard.top(1);           // returns 73;
leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
leaderboard.addScore(2,51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
leaderboard.top(3);           // returns 141 = 51 + 51 + 39;
 

 
 Constraints: 

 
 1 <= playerId, K <= 10000 
 It's guaranteed that K is less than or equal to the current number of players. 

 1 <= score <= 100 
 There will be at most 1000 function calls. 
 

 Related Topics Hash Table Design Sorting 👍 768 👎 94

*/