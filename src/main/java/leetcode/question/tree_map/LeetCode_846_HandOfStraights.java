package leetcode.question.tree_map;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 *@Question:  846. Hand of Straights
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.17%
 *@Time  Complexity: O(N * logN + N)  // n 是手牌的数量，主要由排序操作决定
 *@Space Complexity: O(n)        // 使用了 TreeMap 和 Queue 存储数据
 */

/**
 * 当然可以。
 *
 * ### 解题思路
 *
 * 问题要求判断手牌是否能够按顺序分成多个连续的组，每组有指定的长度 `groupSize`。我们可以通过使用 `TreeMap` 和队列来解决这个问题。
 *
 * 1. **使用 TreeMap 统计牌的数量**：
 *    - 我们首先使用 `TreeMap` 来存储每种牌的数量，并保持其自然顺序。`TreeMap` 可以保证其键按照升序排列，这样在迭代过程中可以保证按牌值顺序处理。
 *
 * 2. **使用队列来追踪组的情况**：
 *    - 我们使用一个队列 `groupStartQueue` 来追踪以每种牌值开始的新组的数量。这个队列的作用是存储当前牌值开始的组还需要多少个新的牌以满足组的长度要求。
 *
 * 3. **迭代处理每种牌值**：
 *    - 我们迭代 `TreeMap` 中的每个牌值，对于每个牌值：
 *      - 检查当前牌值是否满足组的要求，即是否存在不连续或者组长度不足的情况。
 *      - 如果存在不满足条件的情况，直接返回 `false`。
 *      - 如果满足条件，则更新队列和当前开放的组数。
 *
 * 4. **最后检查组是否完整**：
 *    - 最后检查是否所有组都能够完整形成，即 `currentOpenGroups` 是否为 0。
 *
 * ### 时间复杂度
 *
 * - **插入和遍历 TreeMap** 的时间复杂度为 O(n log n)，其中 n 是手牌的数量。这主要是由于 `TreeMap` 在插入和遍历时的操作复杂度为 O(log n)。
 * - **迭代手牌数组** 的时间复杂度为 O(n)。这是因为我们需要遍历手牌数组，并且在每次迭代中，我们进行常数时间的操作来更新 `TreeMap` 和队列。
 *
 * 综合起来，总体时间复杂度为 O(n log n)，其中 n 是手牌的数量。
 *
 * ### 空间复杂度
 *
 * - **TreeMap** 使用了 O(n) 的额外空间，用于存储每种牌的数量。
 * - **队列 `groupStartQueue`** 使用了常数级的额外空间，因为它最多存储 `groupSize` 个元素。
 *
 * 因此，总体空间复杂度为 O(n)，主要由 `TreeMap` 和队列占用的空间决定。
 */
public class LeetCode_846_HandOfStraights{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean isNStraightHand(int[] hand, int groupSize) {
            // 使用 TreeMap 存储每种牌的数量，保持自然顺序
            Map<Integer, Integer> cardCount = new TreeMap<>();

            // 统计每种牌的数量
            for (int card : hand) {
                cardCount.put(card, cardCount.getOrDefault(card, 0) + 1);
            }

            // 使用队列来追踪以每种牌值开始的新组的数量
            Queue<Integer> groupStartQueue = new LinkedList<>();
            int lastCard = -1, currentOpenGroups = 0;

            // 遍历 TreeMap 中的每个 entry
            for (Map.Entry<Integer, Integer> entry : cardCount.entrySet()) {
                int currentCard = entry.getKey();

                // 检查是否存在序列中的不一致，或者需要更多的组比可用的牌数还多
                if ((currentOpenGroups > 0 && currentCard > lastCard + 1) ||
                        currentOpenGroups > cardCount.get(currentCard)) {
                    return false;
                }

                // 计算以当前牌开始的新组的数量
                groupStartQueue.offer(cardCount.get(currentCard) - currentOpenGroups);
                lastCard = currentCard;
                currentOpenGroups = cardCount.get(currentCard);

                // 维持队列大小等于 groupSize
                if (groupStartQueue.size() == groupSize) {
                    currentOpenGroups -= groupStartQueue.poll();
                }
            }

            // 所有组应在结束时都完成
            return currentOpenGroups == 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_846_HandOfStraights().new Solution();
        // 测试样例
        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize1 = 3;
        System.out.println(solution.isNStraightHand(hand1, groupSize1)); // 应输出 true

        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;
        System.out.println(solution.isNStraightHand(hand2, groupSize2)); // 应输出 false
    }
}

/**
Alice has some number of cards and she wants to rearrange the cards into groups 
so that each group is of size groupSize, and consists of groupSize consecutive 
cards. 

 Given an integer array hand where hand[i] is the value written on the iᵗʰ card 
and an integer groupSize, return true if she can rearrange the cards, or false 
otherwise. 

 
 Example 1: 

 
Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
 

 Example 2: 

 
Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.

 

 
 Constraints: 

 
 1 <= hand.length <= 10⁴ 
 0 <= hand[i] <= 10⁹ 
 1 <= groupSize <= hand.length 
 

 
 Note: This question is the same as 1296: https://leetcode.com/problems/divide-
array-in-sets-of-k-consecutive-numbers/ 

 Related Topics Array Hash Table Greedy Sorting 👍 3228 👎 255

*/