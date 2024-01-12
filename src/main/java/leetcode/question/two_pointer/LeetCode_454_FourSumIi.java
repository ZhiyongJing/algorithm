package leetcode.question.two_pointer;

/**
  *@Question:  454. 4Sum II     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 53.459999999999994%      
  *@Time  Complexity: O(N^2)
  *@Space Complexity: O(N^(K/2))
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * **解题思路：**
 *
 * 这个问题的思路是使用分治的方法。将四个数组 A、B、C、D 分成两组，分别计算两组中所有数字两两组合的和的情况。
 *
 * 1. 首先定义了一个 `sumCount` 方法，用于计算某一组数组中所有数字两两组合的和的情况，并返回一个 `Map`，记录每个和出现的次数。
 *
 * 2. 在 `fourSumCount` 方法中，将输入的四个数组分成两组，分别用 `left` 和 `right` 表示。
 *
 * 3. 在 `sumCount` 方法中，通过遍历数组，计算每个数字两两组合的和，更新 `map` 中的情况。
 *
 * 4. 在 `fourSumCount` 方法中，遍历 `left` 中的和，查找是否存在相反数在 `right` 中。如果存在，就将两者对应的组合数量相乘，加到结果 `res` 中。
 *
 * 5. 最后返回 `res`，即为满足条件的组合数量。
 *
 * **时间复杂度：**
 *
 * - 对每一组数组进行遍历，时间复杂度为 O(N^2)，其中 N 为数组长度。
 * - 最终的时间复杂度为 O(N^2)，因为两组数组都需要遍历。
 *
 * **空间复杂度：**
 *
 * - 使用了 `Map` 数据结构来存储和的情况，空间复杂度取决于 `Map` 的大小。
 * - 在 `sumCount` 方法中，每次都会创建一个新的 `Map`。
 * - 最终的空间复杂度为 O(N^(K/2))，其中 K 为分组的数量，这里为 2。
 */

public class LeetCode_454_FourSumIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
// 定义 Solution 类
class Solution {
    private int[][] lsts;

    // 定义方法 fourSumCount，接收四个数组作为输入
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // 将输入的数组放入二维数组 lsts 中
        lsts = new int[][] { A, B, C, D };
        int k = lsts.length;
        System.out.println(Arrays.deepToString(lsts));

        // 分别计算两组中所有数字两两组合的和的情况
        Map<Integer, Integer> left = sumCount(0, k / 2);
        Map<Integer, Integer> right = sumCount(k / 2, k);
        int res = 0;

        // 遍历 left 中的和，查找是否存在相反数在 right 中
        for (int s : left.keySet())
            res += left.get(s) * right.getOrDefault(-s, 0);
        return res;
    }

    // 定义 sumCount 方法，用于计算数组中所有数字两两组合的和的情况
    private Map<Integer, Integer> sumCount(int start, int end) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);

        // 遍历分组中的数组
        for (int i = start; i < end; i++) {
            Map<Integer, Integer> map = new HashMap<>();

            // 遍历数组中的每个数字
            for (int a : lsts[i]) {
                // 遍历已有和的情况
                for (int total : cnt.keySet()) {
                    // 计算新的和，更新 map 中的情况
                    map.put(total + a, map.getOrDefault(total + a, 0) + cnt.get(total));
                }
            }
            cnt = map;
        }
        System.out.println(cnt);
        return cnt;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_454_FourSumIi().new Solution();
        int[] A = {1, 2};
        int[] B = {-2, -1};
        int[] C = {-1, 2};
        int[] D = {0, 2};

        int result = solution.fourSumCount(A, B, C, D);
        System.out.println(result);
    }

}
/**
Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, 
return the number of tuples (i, j, k, l) such that: 

 
 0 <= i, j, k, l < n 
 nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0 
 

 
 Example 1: 

 
Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
Output: 2
Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) +
 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) +
 0 = 0
 

 Example 2: 

 
Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
Output: 1
 

 
 Constraints: 

 
 n == nums1.length 
 n == nums2.length 
 n == nums3.length 
 n == nums4.length 
 1 <= n <= 200 
 -2²⁸ <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2²⁸ 
 

 Related Topics Array Hash Table 👍 4823 👎 138

*/
