package leetcode.frequent.based_on_data_structure.map_set;

import java.util.HashSet;
import java.util.Set;

/**
  *@Question:  128. Longest Consecutive Sequence     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 79.37%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(N)
 */
/**
 * **解题思路：**
 *
 * 题目要求在未排序的整数数组中找出最长的连续序列的长度，并要求算法的时间复杂度为 O(n)。
 *
 * 为了满足 O(n) 的时间复杂度要求，我们可以使用哈希表（HashSet）来存储数组中的数字。具体步骤如下：
 *
 * 1. 遍历数组，将数组中的每个数字存入 HashSet 中。
 *
 * 2. 再次遍历数组，对于数组中的每个数字，判断它是否是一个连续序列的起点。判断的方式是检查当前数字的前一个数字是否在 HashSet 中。
 * 如果不在，说明当前数字是一个序列的起点。
 *
 * 3. 对于每个连续序列的起点，向后循环查找连续的数字，直到找不到为止。同时，记录当前连续序列的长度。
 *
 * 4. 更新最长连续序列的长度。
 *
 * **时间复杂度：**
 *
 * 遍历数组并将数字存入 HashSet 的时间复杂度为 O(n)。再次遍历数组并判断连续序列的时间复杂度也是 O(n)。因此，总体时间复杂度为 O(n)。
 *
 * **空间复杂度：**
 *
 * 哈希表 HashSet 的空间复杂度取决于数组中不同数字的数量。在最坏情况下，所有数字都是不同的，因此空间复杂度为 O(n)。
 */
public class LeetCode_128_LongestConsecutiveSequence {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestConsecutive(int[] nums) {
            // 使用 HashSet 存储数组中的数字，以实现 O(1) 时间内的查找
            Set<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }

            int longestStreak = 0;

            // 遍历数组中的每个数字
            for (int num : numSet) {
                // 如果当前数字的前一个数字不在 HashSet 中，说明当前数字是一个序列的起点
                if (!numSet.contains(num - 1)) {
                    int currentNum = num;
                    int currentStreak = 1;

                    // 循环查找序列中的下一个数字，直到找不到为止
                    while (numSet.contains(currentNum + 1)) {
                        currentNum += 1;
                        currentStreak += 1;
                    }

                    // 更新最长连续序列的长度
                    longestStreak = Math.max(longestStreak, currentStreak);
                }
            }

            return longestStreak;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
//        Solution solution = new LeetCode_128_LongestConsecutiveSequence().new Solution();
//
//        // 测试用例
//        int[] nums1 = {100, 4, 200, 1, 3, 2};
//        System.out.println(solution.longestConsecutive(nums1)); // 输出 4
//
//        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
//        System.out.println(solution.longestConsecutive(nums2)); // 输出 9
        if(false){
            System.out.println("zjing");
        }
        else{
            System.out.println("cool");
        }
    }
}

/**
Given an unsorted array of integers nums, return the length of the longest 
consecutive elements sequence. 

 You must write an algorithm that runs in O(n) time. 

 
 Example 1: 

 
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
Therefore its length is 4.
 

 Example 2: 

 
Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
 

 
 Constraints: 

 
 0 <= nums.length <= 10⁵ 
 -10⁹ <= nums[i] <= 10⁹ 
 

 Related Topics Array Hash Table Union Find 👍 18984 👎 892

*/