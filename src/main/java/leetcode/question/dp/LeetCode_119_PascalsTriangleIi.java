package leetcode.question.dp;

/**
 *@Question:  119. Pascal's Triangle II
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 31.02%
 *@Time  Complexity: O(K ^2) K is the Kth row.
 *@Space Complexity: O(2K) for solution 1, O(K) for solution2
 */


/**
 * 这个问题要求我们生成帕斯卡三角的指定行。帕斯卡三角是一个由数字构成的三角形，其每一行的数字是由前一行相邻两个数字相加而得出的。
 *
 * ### 解题思路：
 *
 * 1. **动态规划：**
 *    - 我们可以使用动态规划来生成帕斯卡三角的指定行。
 *    - 我们从第一行开始，逐行生成到目标行。对于每一行，我们首先构建一个当前行的列表，然后根据前一行的值来填充当前行的元素。最后，我们将前一行更新为当前行，并继续生成下一行，直到达到目标行。
 *    - 在生成每一行时，我们可以根据上一行来计算当前行的元素值，每一行的元素个数等于行号加一。
 *    - 时间复杂度：O(rowIndex^2)，空间复杂度：O(rowIndex)
 *
 * 2. **双重循环：**
 *    - 在这种方法中，我们从一个空的列表开始，然后根据所需行的索引逐步生成该行的元素。对于每一行，我们只使用一个列表，并根据所需的行的索引来逐步生成该行。
 *    - 在生成每一行时，我们只需使用一个列表，并在每一行的末尾添加一个1，然后通过双重循环来计算并更新列表中间的元素值。
 *    - 时间复杂度：O(rowIndex^2)，空间复杂度：O(rowIndex)
 */

public class LeetCode_119_PascalsTriangleIi{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //solution1: DP
        /**
         * Solution1: 动态规划
         * 我们可以使用动态规划来生成帕斯卡三角的指定行。
         * 我们从第一行开始，逐行生成到目标行。对于每一行，我们首先构建一个当前行的列表，然后根据前一行的值来填充当前行的元素。
         * 最后，我们将前一行更新为当前行，并继续生成下一行，直到达到目标行。
         * 时间复杂度：O(rowIndex^2)，空间复杂度：O(rowIndex)
         */
        public List<Integer> getRow(int rowIndex) {
            List<Integer> curr, prev = new ArrayList<>() {
                {
                    add(1);
                }
            };

            for (int i = 1; i <= rowIndex; i++) {
                curr = new ArrayList<>(i + 1) {
                    {
                        add(1);
                    }
                };

                for (int j = 1; j < i; j++) {
                    curr.add(prev.get(j - 1) + prev.get(j));
                }

                curr.add(1);

                prev = curr;
            }

            return prev;
        }

        //Solution2: in the previous approach, we have maintained the previous row in memory on the premise
        // that we need terms from it to build the current row. This is true, but not wholly.
        /**
         * Solution2: 双重循环
         * 在之前的方法中，我们在内存中保持了前一行，前提是我们需要它的项来构建当前行。 这是正确的，但不是完全正确的。
         * 在这种方法中，我们从一个空的列表开始，然后根据所需行的索引逐步生成该行的元素。 对于每一行，我们只使用一个列表，并根据所需的行的索引来逐步生成该行。
         * 时间复杂度：O(rowIndex^2)，空间复杂度：O(rowIndex)
         */
        public List<Integer> getRow2(int rowIndex) {
            List<Integer> row = new ArrayList<>(rowIndex + 1) {
                {
                    add(1);
                }
            };

            for (int i = 0; i < rowIndex; i++) {
                for (int j = i; j > 0; j--) {
                    row.set(j, row.get(j) + row.get(j - 1));
                }
                row.add(1);
            }

            return row;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_119_PascalsTriangleIi().new Solution();
        // TO TEST
        // 测试样例1：获取第5行
        System.out.println("测试样例1：获取第5行");
        List<Integer> result1 = solution.getRow(5);
        System.out.println(result1);

        // 测试样例2：获取第8行
        System.out.println("测试样例2：获取第8行");
        List<Integer> result2 = solution.getRow(8);
        System.out.println(result2);
    }
}

/**
Given an integer rowIndex, return the rowIndexᵗʰ (0-indexed) row of the 
Pascal's triangle. 

 In Pascal's triangle, each number is the sum of the two numbers directly above 
it as shown: 
 
 
 Example 1: 
 Input: rowIndex = 3
Output: [1,3,3,1]
 
 Example 2: 
 Input: rowIndex = 0
Output: [1]
 
 Example 3: 
 Input: rowIndex = 1
Output: [1,1]
 
 
 Constraints: 

 
 0 <= rowIndex <= 33 
 

 
 Follow up: Could you optimize your algorithm to use only O(rowIndex) extra 
space? 

 Related Topics Array Dynamic Programming 👍 4802 👎 338

*/