package interview.company.amazon;

/**
 * @Question: 135. Candy
 * @Difculty: 3 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 58.82999999999999%
 * @Time Complexity: O(n) 其中 n 是评分数组的长度
 * @Space Complexity: O(n) 使用了额外的数组来存储结果
 */

/**
 * 这段代码解决了 LeetCode 上的「135. Candy」问题，以下是解题思路的详细解释：
 *
 * 1. **左到右遍历评分数组：** 首先，我们从左到右遍历一次评分数组 `ratings`，
 * 在这个过程中，我们根据当前孩子的评分与前一个孩子的评分的比较来确定当前孩子应该获得的糖果数。
 * 如果当前孩子的评分高于前一个孩子的评分，则当前孩子应该比前一个孩子多一个糖果。
 * 我们将这些糖果数存储在数组 `left2right` 中。
 *
 * 2. **右到左遍历评分数组：** 接着，我们从右到左遍历一次评分数组 `ratings`，在这个过程中，
 * 我们根据当前孩子的评分与后一个孩子的评分的比较来确定当前孩子应该获得的糖果数。
 * 如果当前孩子的评分高于后一个孩子的评分，则当前孩子应该比后一个孩子多一个糖果。
 * 我们将这些糖果数存储在数组 `right2left` 中。
 *
 * 3. **取最大值：** 最后，我们遍历一次评分数组，对于每个孩子，
 * 我们取其在 `left2right` 和 `right2left` 中所记录的糖果数的最大值，
 * 作为该孩子所获得的糖果数。然后将所有孩子的糖果数相加，得到最终的结果。
 *
 * **时间复杂度：**
 *
 * - 对评分数组的两次遍历都是线性的，因此总的时间复杂度是 O(n)，其中 n 是评分数组的长度。
 *
 * **空间复杂度：**
 *
 * - 需要额外两个数组 `left2right` 和 `right2left` 来存储每个孩子的糖果数，因此空间复杂度是 O(n)，其中 n 是评分数组的长度。
 *
 * 这种方法的关键在于通过两次遍历评分数组来确定每个孩子应该获得的最小糖果数，以保证满足题目中的要求。
 */
public class LeetCode_135_Candy {

    // leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        /**
         * 计算给定的评分数组所需的最小糖果数
         *
         * @param ratings 评分数组
         * @return 最小糖果数
         */
        public int candy(int[] ratings) {
            int sum = 0;
            // 左到右扫描，记录每个孩子对应的最小糖果数
            int[] left2right = new int[ratings.length];
            // 右到左扫描，记录每个孩子对应的最小糖果数
            int[] right2left = new int[ratings.length];
            // 初始化数组为1，每个孩子至少分配一个糖果
            Arrays.fill(left2right, 1);
            Arrays.fill(right2left, 1);
            // 从第二个孩子开始，如果当前孩子的评分高于前一个孩子，则糖果数增加
            for (int i = 1; i < ratings.length; i++) {
                if (ratings[i] > ratings[i - 1]) {
                    left2right[i] = left2right[i - 1] + 1;
                }
            }
            // 从倒数第二个孩子开始，如果当前孩子的评分高于后一个孩子，则糖果数增加
            for (int i = ratings.length - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) {
                    right2left[i] = right2left[i + 1] + 1;
                }
            }
            // 统计最终结果，取左到右和右到左两种分配方式中的最大值
            for (int i = 0; i < ratings.length; i++) {
                sum += Math.max(left2right[i], right2left[i]);
            }
            return sum;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 测试
        Solution solution = new LeetCode_135_Candy().new Solution();
        int[] ratings = {1, 0, 2};
        System.out.println(solution.candy(ratings)); // 应输出 5
    }
}

/**
There are n children standing in a line. Each child is assigned a rating value 
given in the integer array ratings. 

 You are giving candies to these children subjected to the following 
requirements: 

 
 Each child must have at least one candy. 
 Children with a higher rating get more candies than their neighbors. 
 

 Return the minimum number of candies you need to have to distribute the 
candies to the children. 

 
 Example 1: 

 
Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 
candies respectively.
 

 Example 2: 

 
Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 
candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.
 

 
 Constraints: 

 
 n == ratings.length 
 1 <= n <= 2 * 10⁴ 
 0 <= ratings[i] <= 2 * 10⁴ 
 

 Related Topics Array Greedy 👍 7625 👎 636

*/