package leetcode.frequent.bfs;

import java.util.*;

/**
  *@Question:  444. Sequence Reconstruction
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 23.56%
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */

/**
 * ### 算法思路：
 *
 * 这道题目的目标是判断给定的一些序列是否能够构成唯一的原始序列。判断原始序列的条件是：给定的序列中的每一对相邻元素，
 * 在原始序列中也必须是相邻的。
 *
 * 为了解决这个问题，可以使用拓扑排序的思想。拓扑排序可以判断有向图是否存在拓扑序列，即一种满足有向边的方向的节点排序。
 * 在这个问题中，节点表示数字，有向边表示数字之间的相对顺序。
 *
 * 1. **构建图：** 遍历给定的序列，构建节点之间的关系图（邻接表）和计算每个节点的入度。
 *
 * 2. **拓扑排序：** 使用队列进行拓扑排序。将入度为0的节点加入队列，并不断从队列中取出节点，删除以该节点为起点的边，更新相关节点的入度。
 * 如果最终队列中的元素数量大于1，说明存在多个拓扑序列，返回false。
 *
 * 3. **判断原始序列：** 遍历原始序列，与拓扑排序的结果进行比较。如果任何一对相邻元素在拓扑排序的结果中不相邻，则返回false。
 * 否则，返回true。
 *
 * ### 复杂度分析：
 *
 * - **时间复杂度：** 假设序列的长度为n，对于每个序列，需要遍历其中的元素构建图，所以总体时间复杂度为O(n)。
 * - **空间复杂度：** 使用了两个HashMap分别记录节点之间的关系和节点的入度，以及一个队列，因此空间复杂度为O(n)。
 */

public class LeetCode_444_SequenceReconstruction {

    // leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public boolean sequenceReconstruction(int[] org, List<List<Integer>> sequences) {
            // 使用两个Map，一个记录节点之间的关系，一个记录节点的入度
            Map<Integer, Set<Integer>> map = new HashMap<>();
            Map<Integer, Integer> indegree = new HashMap<>();

            // 构建图的关系和计算入度
            for (List<Integer> seq : sequences) {
                if (seq.size() == 1) {
                    if (!map.containsKey(seq.get(0))) {
                        map.put(seq.get(0), new HashSet<>());
                        indegree.put(seq.get(0), 0);
                    }
                } else {
                    for (int i = 0; i < seq.size() - 1; i++) {
                        if (!map.containsKey(seq.get(i))) {
                            map.put(seq.get(i), new HashSet<>());
                            indegree.put(seq.get(i), 0);
                        }

                        if (!map.containsKey(seq.get(i + 1))) {
                            map.put(seq.get(i + 1), new HashSet<>());
                            indegree.put(seq.get(i + 1), 0);
                        }

                        if (map.get(seq.get(i)).add(seq.get(i + 1))) {
                            indegree.put(seq.get(i + 1), indegree.get(seq.get(i + 1)) + 1);
                        }
                    }
                }
            }
            System.out.println("relation is: " + map);
            System.out.println(indegree);

            // 使用队列进行拓扑排序
            Queue<Integer> queue = new LinkedList<>();
            for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
                if (entry.getValue() == 0) {
                    queue.offer(entry.getKey());
                }
            }
            System.out.println(queue);

            int index = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                if (size > 1) return false; // 如果队列中有超过一个元素，说明存在多种拓扑排序
                int curr = queue.poll();
                if (index == org.length || curr != org[index++]) return false;
                for (int next : map.get(curr)) {
                    indegree.put(next, indegree.get(next) - 1);
                    if (indegree.get(next) == 0) queue.offer(next);
                }
            }

            // 检查是否所有节点都被访问
            return index == org.length && index == map.size();
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_444_SequenceReconstruction().new Solution();

        // 测试用例
        int[] org = {1, 2, 3};
        List<List<Integer>> sequences = new ArrayList<>();
        sequences.add(Arrays.asList(1, 2));
        sequences.add(Arrays.asList(1, 3));

        System.out.println(solution.sequenceReconstruction(org, sequences));  // 应该返回 true
    }
}

/**
You are given an integer array nums of length n where nums is a permutation of 
the integers in the range [1, n]. You are also given a 2D integer array 
sequences where sequences[i] is a subsequence of nums. 

 Check if nums is the shortest possible and the only supersequence. The 
shortest supersequence is a sequence with the shortest length and has all sequences[i] 
as subsequences. There could be multiple valid supersequences for the given 
array sequences. 

 
 For example, for sequences = [[1,2],[1,3]], there are two shortest 
supersequences, [1,2,3] and [1,3,2]. 
 While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence 
possible is [1,2,3]. [1,2,3,4] is a possible supersequence but not the shortest. 
 

 Return true if nums is the only shortest supersequence for sequences, or false 
otherwise. 

 A subsequence is a sequence that can be derived from another sequence by 
deleting some or no elements without changing the order of the remaining elements. 

 
 Example 1: 

 
Input: nums = [1,2,3], sequences = [[1,2],[1,3]]
Output: false
Explanation: There are two possible supersequences: [1,2,3] and [1,3,2].
The sequence [1,2] is a subsequence of both: [1,2,3] and [1,3,2].
The sequence [1,3] is a subsequence of both: [1,2,3] and [1,3,2].
Since nums is not the only shortest supersequence, we return false.
 

 Example 2: 

 
Input: nums = [1,2,3], sequences = [[1,2]]
Output: false
Explanation: The shortest possible supersequence is [1,2].
The sequence [1,2] is a subsequence of it: [1,2].
Since nums is not the shortest supersequence, we return false.
 

 Example 3: 

 
Input: nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
Output: true
Explanation: The shortest possible supersequence is [1,2,3].
The sequence [1,2] is a subsequence of it: [1,2,3].
The sequence [1,3] is a subsequence of it: [1,2,3].
The sequence [2,3] is a subsequence of it: [1,2,3].
Since nums is the only shortest supersequence, we return true.
 

 
 Constraints: 

 
 n == nums.length 
 1 <= n <= 10⁴ 
 nums is a permutation of all the integers in the range [1, n]. 
 1 <= sequences.length <= 10⁴ 
 1 <= sequences[i].length <= 10⁴ 
 1 <= sum(sequences[i].length) <= 10⁵ 
 1 <= sequences[i][j] <= n 
 All the arrays of sequences are unique. 
 sequences[i] is a subsequence of nums. 
 

 Related Topics Array Graph Topological Sort 👍 555 👎 1488

*/
