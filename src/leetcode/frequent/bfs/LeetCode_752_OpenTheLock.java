package leetcode.frequent.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
/**
  *@Question:  752. Open the Lock     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 34.7%      
  *@Time  Complexity: O(N^2 * A^N + D) A is the number of digits in our alphabet, N is the number of digits in the lock, and D is the size of deadends.
  *@Space Complexity: O(A^N + D)
 */

/**
 ***算法思路：**
 *
 * 该算法使用广度优先搜索（BFS）来寻找打开密码锁的最小步数。整体思路如下：
 *
 * 1. 创建一个集合 `dead`，用于存储死锁状态。
 * 2. 使用队列 `queue` 进行广度优先搜索。初始时将 "0000" 加入队列，表示密码锁的起始状态。使用 `null` 作为分层标志，
 * 每次处理完一层后，再将 `null` 加入队列，表示层数加一。
 * 3. 使用集合 `seen` 来记录已经遍历过的状态，避免重复搜索。
 * 4. 在每一步中，遍历密码锁的每一位数字，分别尝试向上和向下转动数字，生成新的状态，并判断是否在 `dead` 集合中，
 * 以及是否已经遍历过。如果是新的状态，则加入队列和 `seen` 集合中。
 * 5. 当遇到目标状态时，返回当前步数；如果队列为空，表示无法到达目标状态，返回 -1。
 *
 * **时间复杂度：**
 *
 * 算法的时间复杂度为 O(A^N + D)，其中 A 是密码锁每个数字的可能取值，N 是密码锁的位数，D 是死锁集合的大小。在最坏情况下，需要遍历所有可能的状态。
 *
 * **空间复杂度：**
 *
 * 算法的空间复杂度为 O(A^N + D)，其中 A^N 表示密码锁状态的总数量，D 表示死锁集合的大小。空间主要用于存储死锁集合、队列和已经遍历过的状态。
 */
public class LeetCode_752_OpenTheLock {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int openLock(String[] deadends, String target) {
            Set<String> dead = new HashSet();
            for (String d : deadends) dead.add(d);

            Queue<String> queue = new LinkedList();
            queue.offer("0000");
            queue.offer(null);

            Set<String> seen = new HashSet();
            seen.add("0000");

            int depth = 0;
            while (!queue.isEmpty()) {
                String node = queue.poll();
                if (node == null) {
                    depth++;
                    if (queue.peek() != null)
                        queue.offer(null);
                } else if (node.equals(target)) {
                    return depth;
                } else if (!dead.contains(node)) {
                    // 遍历每一位数字
                    for (int i = 0; i < 4; ++i) {
                        // 分别尝试向上和向下转动数字
                        for (int d = -1; d <= 1; d += 2) {
                            int y = ((node.charAt(i) - '0') + d + 10) % 10;
                            String nei = node.substring(0, i) + ("" + y) + node.substring(i + 1);
                            if (!seen.contains(nei)) {
                                seen.add(nei);
                                queue.offer(nei);
                            }
                        }
                    }
                }
            }
            return -1; // 无法到达目标状态
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_752_OpenTheLock().new Solution();

        // 测试例子1
        String[] deadends1 = {"0201", "0101", "0102", "1212", "2002"};
        String target1 = "0202";
        System.out.println(solution.openLock(deadends1, target1)); // 输出 6

        // 测试例子2
        String[] deadends2 = {"8888"};
        String target2 = "0009";
        System.out.println(solution.openLock(deadends2, target2)); // 输出 1

        // 测试例子3
        String[] deadends3 = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
        String target3 = "8888";
        System.out.println(solution.openLock(deadends3, target3)); // 输出 -1
    }
}

/**
You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots:
 '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely 
and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each 
move consists of turning one wheel one slot. 

 The lock initially starts at '0000', a string representing the state of the 4 
wheels. 

 You are given a list of deadends dead ends, meaning if the lock displays any 
of these codes, the wheels of the lock will stop turning and you will be unable 
to open it. 

 Given a target representing the value of the wheels that will unlock the lock, 
return the minimum total number of turns required to open the lock, or -1 if it 
is impossible. 

 
 Example 1: 

 
Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
Output: 6
Explanation: 
A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201
" -> "1202" -> "0202".
Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would 
be invalid,
because the wheels of the lock become stuck after the display becomes the dead 
end "0102".
 

 Example 2: 

 
Input: deadends = ["8888"], target = "0009"
Output: 1
Explanation: We can turn the last wheel in reverse to move from "0000" -> "0009
".
 

 Example 3: 

 
Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], 
target = "8888"
Output: -1
Explanation: We cannot reach the target without getting stuck.
 

 
 Constraints: 

 
 1 <= deadends.length <= 500 
 deadends[i].length == 4 
 target.length == 4 
 target will not be in the list deadends. 
 target and deadends[i] consist of digits only. 
 

 Related Topics Array Hash Table String Breadth-First Search 👍 3838 👎 144

*/
