package leetcode.question.heap;
import javafx.util.Pair;

import java.util.*;

/**
 *@Question:  373. Find K Pairs with Smallest Sums
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.96%
 *@Time  Complexity: O(min(k * logK, m * n * log(m*n)))
 *@Space Complexity: O(min(k, m * n))
 */

public class LeetCode_373_FindKPairsWithSmallestSums{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            // 获取两个数组的长度
            int m = nums1.length;
            int n = nums2.length;

            // 存放最终结果对的列表
            List<List<Integer>> ans = new ArrayList<>();

            // 用于记录访问过的索引对，避免重复
            Set<Pair<Integer, Integer>> visited = new HashSet<>();

            // 最小堆，按照两个元素之和排序。数组中格式：[和, i, j]
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b)->(a[0] - b[0]));

            // 初始加入第一个对 (0, 0) 即 nums1[0] + nums2[0]
            minHeap.offer(new int[]{nums1[0] + nums2[0], 0, 0});
            visited.add(new Pair<>(0, 0));

            // 直到取出 k 个元素或者堆为空
            while (k-- > 0 && !minHeap.isEmpty()) {
                // 取出当前最小和的配对
                int[] top = minHeap.poll();
                int i = top[1];
                int j = top[2];

                // 添加结果
                ans.add(Arrays.asList(nums1[i], nums2[j]));

                // 向下扩展：下一个 i + 1 配对
                if (i + 1 < m && !visited.contains(new Pair<>(i + 1, j))) {
                    minHeap.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
                    visited.add(new Pair<>(i + 1, j));
                }

                // 向右扩展：下一个 j + 1 配对
                if (j + 1 < n && !visited.contains(new Pair<>(i, j + 1))) {
                    minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
                    visited.add(new Pair<>(i, j + 1));
                }
                System.out.println(minHeap);
            }

            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_373_FindKPairsWithSmallestSums().new Solution();

        // 测试用例 1
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        System.out.println("测试用例 1 输出：" + solution.kSmallestPairs(nums1, nums2, k));
        // 预期输出：[[1, 2], [1, 4], [1, 6]]

//        // 测试用例 2
//        int[] nums3 = {1, 1, 2};
//        int[] nums4 = {1, 2, 3};
//        int k2 = 2;
//        System.out.println("测试用例 2 输出：" + solution.kSmallestPairs(nums3, nums4, k2));
//        // 预期输出：[[1, 1], [1, 1]]
//
//        // 测试用例 3：空数组
//        int[] nums5 = {};
//        int[] nums6 = {1, 2, 3};
//        int k3 = 3;
//        System.out.println("测试用例 3 输出：" + solution.kSmallestPairs(nums5, nums6, k3));
//        // 预期输出：[]
    }
}

/**
You are given two integer arrays nums1 and nums2 sorted in non-decreasing order 
and an integer k. 

 Define a pair (u, v) which consists of one element from the first array and 
one element from the second array. 

 Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums. 

 
 Example 1: 

 
Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6]
,[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 

 Example 2: 

 
Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2]
,[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 

 
 Constraints: 

 
 1 <= nums1.length, nums2.length <= 10⁵ 
 -10⁹ <= nums1[i], nums2[i] <= 10⁹ 
 nums1 and nums2 both are sorted in non-decreasing order. 
 1 <= k <= 10⁴ 
 k <= nums1.length * nums2.length 
 

 Related Topics Array Heap (Priority Queue) 👍 6554 👎 469

*/