package leetcode.question.prefix_sum;

/**
 *@Question:  1423. Maximum Points You Can Obtain from Cards
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 25.590000000000003%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 解题思路
 *
 * #### 解法1: 动态规划
 *
 * 1. **初始化：** 我们首先计算从数组的前面取 k 张牌的分数总和，将其初始化为 `frontScore`。
 *
 * 2. **遍历：** 然后，我们从数组的最后一张牌开始向前遍历，依次从前面取 i 张牌，从后面取 k - i 张牌，
 * 计算当前分数，更新最大分数 `maxScore`。
 *
 * 3. **返回结果：** 遍历完成后，`maxScore` 就是从数组中取出 k 张牌的最大分数。
 *
 * #### 解法2: 滑动窗口
 *
 * 1. **初始化：** 我们首先计算所有牌的总分 `totalScore`。
 *
 * 2. **滑动窗口：** 然后，我们维护一个长度为 `n - k` 的窗口，通过滑动窗口的方式计算窗口内牌的分数总和，
 * 并找到窗口内牌分数总和的最小值 `minSubarrayScore`。
 *
 * 3. **计算结果：** 最后，用 `totalScore` 减去 `minSubarrayScore`，即为从数组中取出 k 张牌的最大分数。
 *
 * ### 时间和空间复杂度
 *
 * #### 解法1: 动态规划
 *
 * - **时间复杂度：** O(N)，其中 N 是数组的长度。我们只对数组进行了一次遍历。
 *
 * - **空间复杂度：** O(1)，只使用了常数额外空间。
 *
 * #### 解法2: 滑动窗口
 *
 * - **时间复杂度：** O(N)，其中 N 是数组的长度。我们只对数组进行了一次遍历。
 *
 * - **空间复杂度：** O(1)，只使用了常数额外空间。
 */

public class LeetCode_1423_MaximumPointsYouCanObtainFromCards{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //解法1: 动态规划
        public int maxScore(int[] cardPoints, int k) {
            int frontScore = 0;
            int rearScore = 0;
            int n = cardPoints.length;

            // 从数组的前面取 k 张牌
            for (int i = 0; i < k; i++) {
                frontScore += cardPoints[i];
            }

            // 先假设所有牌都是从前面取的
            int maxScore = frontScore;

            // 从前面取 i 张，从后面取 k - i 张
            for (int i = k - 1; i >= 0; i--) {
                rearScore += cardPoints[n - (k - i)];
                frontScore -= cardPoints[i];
                int currentScore = rearScore + frontScore;
                maxScore = Math.max(maxScore, currentScore);
            }

            return maxScore;
        }

        //解法2: 滑动窗口
        public int maxScore2(int[] cardPoints, int k) {
            int startingIndex = 0;
            int presentSubarrayScore = 0;
            int n = cardPoints.length;
            int requiredSubarrayLength = n - k;
            int minSubarrayScore;
            int totalScore = 0;

            // 计算所有牌的总分
            for (int i : cardPoints) {
                totalScore += i;
            }

            minSubarrayScore = totalScore;

            if (k == n) {
                return totalScore;
            }

            for (int i = 0; i < n; i++) {
                presentSubarrayScore += cardPoints[i];
                int presentSubarrayLength = i - startingIndex + 1;
                // 如果存在合法的子数组（长度为 cardsPoints.length - k）
                if (presentSubarrayLength == requiredSubarrayLength) {
                    minSubarrayScore = Math.min(minSubarrayScore, presentSubarrayScore);
                    presentSubarrayScore -= cardPoints[startingIndex++];
                }
            }
            return totalScore - minSubarrayScore;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1423_MaximumPointsYouCanObtainFromCards().new Solution();
        // 测试代码
        int[] cardPoints1 = {1, 2, 3, 4, 5, 6, 1};
        int k1 = 3;
        System.out.println(solution.maxScore(cardPoints1, k1));  // 应该输出 12

        int[] cardPoints2 = {2, 2, 2};
        int k2 = 2;
        System.out.println(solution.maxScore(cardPoints2, k2));  // 应该输出 4

        int[] cardPoints3 = {9, 7, 7, 9, 7, 7, 9};
        int k3 = 7;
        System.out.println(solution.maxScore(cardPoints3, k3));  // 应该输出 55
    }
}
/**
 There are several cards arranged in a row, and each card has an associated
 number of points. The points are given in the integer array cardPoints.

 In one step, you can take one card from the beginning or from the end of the 
 row. You have to take exactly k cards.

 Your score is the sum of the points of the cards you have taken. 

 Given the integer array cardPoints and the integer k, return the maximum score 
 you can obtain.


 Example 1: 


 Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 Output: 12
 Explanation: After the first step, your score will always be 1. However,
 choosing the rightmost card first will maximize your total score. The optimal strategy
 is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.



 Example 2: 


 Input: cardPoints = [2,2,2], k = 2
 Output: 4
 Explanation: Regardless of which two cards you take, your score will always be 4
 .


 Example 3: 


 Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 Output: 55
 Explanation: You have to take all the cards. Your score is the sum of points of
 all cards.



 Constraints: 


 1 <= cardPoints.length <= 10⁵ 
 1 <= cardPoints[i] <= 10⁴ 
 1 <= k <= cardPoints.length 


 Related Topics Array Sliding Window Prefix Sum 👍 5838 👎 213

 */
