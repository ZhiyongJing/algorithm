package leetcode.question.dp;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  118. Pascal's Triangle
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 76.36%
 *@Time  Complexity: O(n^2)
 *@Space Complexity: O(n^2)
 */

/**
 * 当然，我可以为你解释解题思路并分析时间和空间复杂度。
 *
 * ### 解题思路：
 *
 * 1. **基本规则**：首先我们需要理解杨辉三角的基本规则。每一行的第一个和最后一个元素都是1，中间的元素是其上方和左上方两个元素之和。
 *
 * 2. **初始化**：我们先初始化一个空的列表 `triangle` 用于存储整个杨辉三角。
 *
 * 3. **基础情况**：首先添加第一行 `[1]` 到 `triangle` 中。
 *
 * 4. **逐行构建**：从第二行开始，我们逐行构建杨辉三角。对于每一行：
 *    - 我们创建一个新的列表 `row` 来存储当前行的元素。
 *    - 首先，在 `row` 中添加第一个元素1。
 *    - 然后，对于每一个中间的元素，我们通过获取上一行相邻两个元素的值相加得到当前元素。
 *    - 最后，在 `row` 中添加最后一个元素1。
 *    - 将构建好的当前行 `row` 添加到 `triangle` 中。
 *
 * 5. **重复步骤**：重复以上步骤直到生成所需的行数。
 *
 * 6. **返回结果**：返回最终的 `triangle`，即构建好的杨辉三角。
 *
 * ### 时间复杂度分析：
 *
 * 生成杨辉三角的时间复杂度取决于生成的行数 `numRows`。对于每一行，我们需要计算其元素，其中第 `i` 行有 `i` 个元素。因此，总时间复杂度为 `O(numRows^2)`。
 *
 * ### 空间复杂度分析：
 *
 * 空间复杂度取决于存储整个杨辉三角所需的空间。由于每一行最多有 `numRows` 个元素，因此空间复杂度为 `O(numRows^2)`。
 */

public class LeetCode_118_PascalsTriangle{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            // 创建一个新的列表，用于存储整个杨辉三角
            List<List<Integer>> triangle = new ArrayList<List<Integer>>();

            // 基础情况：第一行总是 [1]
            triangle.add(new ArrayList<>());
            triangle.get(0).add(1);

            // 从第二行开始构建杨辉三角
            for (int rowNum = 1; rowNum < numRows; rowNum++) {
                // 创建当前行的列表
                List<Integer> row = new ArrayList<>();
                // 获取上一行的列表
                List<Integer> prevRow = triangle.get(rowNum - 1);

                // 当前行的第一个元素总是 1
                row.add(1);

                // 对于每一个当前行的元素（除了第一个和最后一个），
                // 它等于它正上方和左上方的两个元素之和
                for (int j = 1; j < rowNum; j++) {
                    row.add(prevRow.get(j - 1) + prevRow.get(j));
                }

                // 当前行的最后一个元素总是 1
                row.add(1);

                // 将当前行添加到杨辉三角列表中
                triangle.add(row);
            }

            // 返回构建好的杨辉三角
            return triangle;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_118_PascalsTriangle().new Solution();

        // 测试样例
        int numRows = 5;
        List<List<Integer>> result = solution.generate(numRows);

        // 打印结果
        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }
}

/**
Given an integer numRows, return the first numRows of Pascal's triangle. 

 In Pascal's triangle, each number is the sum of the two numbers directly above 
it as shown: 
 
 
 Example 1: 
 Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 
 Example 2: 
 Input: numRows = 1
Output: [[1]]
 
 
 Constraints: 

 
 1 <= numRows <= 30 
 

 Related Topics Array Dynamic Programming 👍 12741 👎 440

*/