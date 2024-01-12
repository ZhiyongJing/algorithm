package leetcode.question.binary_search;

/**
  *@Question:  74. Search a 2D Matrix     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 68.37%      
  *@Time  Complexity: O(log(M*N))
  *@Space Complexity: O(1)
 */

/**
 *这个算法是使用二分查找在一个排好序的二维矩阵中搜索目标值。以下是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. 获取矩阵的行数 `m` 和列数 `n`。
 *
 * 2. 使用二分查找，将二维矩阵展开成一个一维有序数组。左指针 `left` 初始化为0，右指针 `right` 初始化为 `m * n - 1`。
 *
 * 3. 在每一次迭代中，计算中间元素的索引 `pivotIdx`，并获取该位置的元素 `pivotElement`。
 *
 * 4. 将目标值 `target` 与 `pivotElement` 进行比较：
 *
 *    - 如果 `target == pivotElement`，说明找到了目标值，返回 `true`。
 *
 *    - 如果 `target < pivotElement`，说明目标值可能在左侧，将右指针 `right` 缩小到 `pivotIdx - 1`。
 *
 *    - 如果 `target > pivotElement`，说明目标值可能在右侧，将左指针 `left` 移动到 `pivotIdx + 1`。
 *
 * 5. 重复步骤 3 和 4，直到左指针 `left` 大于右指针 `right`。此时，如果目标值存在，会在某个位置找到并返回 `true`，否则返回 `false`。
 *
 * ### 时间复杂度：
 *
 * - 由于每一步都将搜索范围缩小一半，因此时间复杂度为 O(log(M * N))，其中 M 是矩阵的行数，N 是矩阵的列数。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */
public class LeetCode_74_SearchA2dMatrix {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 在二维矩阵中搜索目标值
         *
         * @param matrix 二维矩阵
         * @param target 目标值
         * @return 是否存在目标值
         */
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length;
            if (m == 0)
                return false;
            int n = matrix[0].length;

            // 二分搜索
            int left = 0, right = m * n - 1;
            int pivotIdx, pivotElement;
            while (left <= right) {
                pivotIdx = (left + right) / 2;
                // 获取中间元素
                pivotElement = matrix[pivotIdx / n][pivotIdx % n];

                if (target == pivotElement)
                    return true;
                else {
                    if (target < pivotElement)
                        right = pivotIdx - 1;
                    else
                        left = pivotIdx + 1;
                }
            }
            return false;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_74_SearchA2dMatrix().new Solution();

        // 测试代码
        int[][] matrix = {
                {1,   3,  5,  7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };

        // 测试目标值存在的情况
        // 预期输出: true
        System.out.println(solution.searchMatrix(matrix, 3));

        // 测试目标值不存在的情况
        // 预期输出: false
        System.out.println(solution.searchMatrix(matrix, 13));
    }
}

/**
You are given an m x n integer matrix matrix with the following two properties: 


 
 Each row is sorted in non-decreasing order. 
 The first integer of each row is greater than the last integer of the previous 
row. 
 

 Given an integer target, return true if target is in matrix or false otherwise.
 

 You must write a solution in O(log(m * n)) time complexity. 

 
 Example 1: 
 
 
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
 

 Example 2: 
 
 
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false
 

 
 Constraints: 

 
 m == matrix.length 
 n == matrix[i].length 
 1 <= m, n <= 100 
 -10⁴ <= matrix[i][j], target <= 10⁴ 
 

 Related Topics Array Binary Search Matrix 👍 14977 👎 394

*/
