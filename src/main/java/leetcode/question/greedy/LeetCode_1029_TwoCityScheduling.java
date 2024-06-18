package leetcode.question.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
  *@Question:  1029. Two City Scheduling     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 57.24%      
  *@Time  Complexity: O(NlogN)
  *@Space Complexity: O(N)
 */

/**### 解题思路

 这道题的目标是将一组人分配到两个城市，使得总成本最小。每个人都有去A城市的成本和去B城市的成本，我们需要决定每个人去哪个城市，以使得总成本最小。

 1. **排序：** 首先，我们将人员按照去A城市和去B城市的成本差值进行排序。排序的依据是 `(o1[0] - o1[1]) - (o2[0] - o2[1])`，
 即按照差值升序排序。这样排完序后，前一半的人员去A城市，后一半去B城市，可以使得总成本最小。

 2. **成本计算：** 排序后，前一半的人员去A城市，后一半去B城市，遍历计算总成本。

 ### 时间复杂度

 - 排序的时间复杂度为 O(N log N)，其中 N 为`costs`数组的长度。

 ### 空间复杂度

 - 使用了常数级的额外空间，因此空间复杂度为 O(1)。

 该算法通过排序和贪心的思想，选择成本差值小的人员去A城市，总体上达到了最优解。时间和空间复杂度都是线性级别的。
 */

public class LeetCode_1029_TwoCityScheduling {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int twoCitySchedCost(int[][] costs) {
            // 按照公司的利润差值排序
            // 利润差值 = 发送一人到城市A而不是城市B的利润
            Arrays.sort(costs, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o1[1] - (o2[0] - o2[1]);
                }
            });

            int total = 0;
            int n = costs.length / 2;
            // 为了优化公司的费用，
            // 将前 n 个人发送到城市A，
            // 将后面的人发送到城市B
            for (int i = 0; i < n; ++i) total += costs[i][0] + costs[i + n][1];
            return total;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1029_TwoCityScheduling().new Solution();

        // 测试用例
        int[][] costs = {
                {10, 20},
                {30, 200},
                {50, 30},
                {200, 40}
        };

        int result = solution.twoCitySchedCost(costs);
        System.out.println("Result: " + result);  // 输出：110
    }
}

/**
A company is planning to interview 2n people. Given the array costs where costs[
i] = [aCosti, bCosti], the cost of flying the iᵗʰ person to city a is aCosti, 
and the cost of flying the iᵗʰ person to city b is bCosti. 

 Return the minimum cost to fly every person to a city such that exactly n 
people arrive in each city. 

 
 Example 1: 

 
Input: costs = [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people 
interviewing in each city.
 

 Example 2: 

 
Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
Output: 1859
 

 Example 3: 

 
Input: costs = [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,
359],[631,42]]
Output: 3086
 

 
 Constraints: 

 
 2 * n == costs.length 
 2 <= costs.length <= 100 
 costs.length is even. 
 1 <= aCosti, bCosti <= 1000 
 

 Related Topics Array Greedy Sorting 👍 4616 👎 346

*/