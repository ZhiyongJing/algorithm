package leetcode.question.two_pointer;

/**
 *@Question:  3453. Separate Squares I
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 53.99%
 *@Time  Complexity: O()
 *@Space Complexity: O()
 */

public class LeetCode_3453_SeparateSquaresI{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 辅助函数：给定一条水平线的位置 line，计算该线以上和以下的面积差（aAbove - aBelow）
        private double helper(double line, int[][] squares) {
            double aAbove = 0, aBelow = 0; // 初始化线以上与线以下的面积

            // 遍历每一个正方形
            for (int i = 0; i < squares.length; i++) {
                int x = squares[i][0], y = squares[i][1], l = squares[i][2]; // 获取左下角坐标和边长
                double total = (double) l * l; // 正方形总面积

                // 如果整块都在 line 之上
                if (line <= y) {
                    aAbove += total;
                }
                // 如果整块都在 line 之下
                else if (line >= y + l) {
                    aBelow += total;
                }
                // 如果 line 与正方形相交
                else {
                    // 上方的部分高度
                    double aboveHeight = (y + l) - line;
                    // 下方的部分高度
                    double belowHeight = line - y;
                    // 计算上下部分面积（矩形 = 宽度 * 高度）
                    aAbove += l * aboveHeight;
                    aBelow += l * belowHeight;
                }
            }

            // 返回面积差（上 - 下）
            return aAbove - aBelow;
        }

        // 主函数：通过二分查找，找出可以平分面积的水平线位置
        public double separateSquares(int[][] squares) {
            // 搜索范围设为 [0, 2e9]，因为坐标和边长都不超过 1e9
            double lo = 0, hi = 2*1e9;

            // 进行 60 次二分，保证结果精度达 double 的极限 double 的精度上限约为 1e-15
            for (int i = 0; i < 60; i++) {
                double mid = (lo + hi) / 2.0;
                double diff = helper(mid, squares); // 计算 mid 处线的上下面积差

                // 如果上方面积更大 → 向下移动线
                if (diff > 0)
                    lo = mid;
                else
                    hi = mid;
            }

            // 最后返回面积最接近平衡的高度
            return hi;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_3453_SeparateSquaresI().new Solution();

        // ✅ 示例 1：两个完全重叠的正方形
        int[][] squares1 = {
                {0, 0, 2},
                {0, 0, 2}
        };
        System.out.printf("Test 1: %.5f\n", solution.separateSquares(squares1)); // 应该是中线 y = 2

        // ✅ 示例 2：一个正方形在上，一个在下
        int[][] squares2 = {
                {0, 0, 2},
                {0, 4, 2}
        };
        System.out.printf("Test 2: %.5f\n", solution.separateSquares(squares2)); // 应该是中间 y = 3

        // ✅ 示例 3：多个高度不同的正方形
        int[][] squares3 = {
                {0, 0, 2},
                {0, 2, 4},
                {0, 7, 1}
        };
        System.out.printf("Test 3: %.5f\n", solution.separateSquares(squares3)); // 输出为平衡点高度

        // ✅ 示例 4：单个正方形
        int[][] squares4 = {
                {0, 0, 2}
        };
        System.out.printf("Test 4: %.5f\n", solution.separateSquares(squares4)); // 应该为 y = 1
    }
}

/**
You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] 
represents the coordinates of the bottom-left point and the side length of a square 
parallel to the x-axis. 

 Find the minimum y-coordinate value of a horizontal line such that the total 
area of the squares above the line equals the total area of the squares below the 
line. 

 Answers within 10⁻⁵ of the actual answer will be accepted. 

 Note: Squares may overlap. Overlapping areas should be counted multiple times. 


 
 Example 1: 

 
 Input: squares = [[0,0,1],[2,2,1]] 
 

 Output: 1.00000 

 Explanation: 

 

 Any horizontal line between y = 1 and y = 2 will have 1 square unit above it 
and 1 square unit below it. The lowest option is 1. 

 Example 2: 

 
 Input: squares = [[0,0,2],[1,1,1]] 
 

 Output: 1.16667 

 Explanation: 

 

 The areas are: 

 
 Below the line: 7/6 * 2 (Red) + 1/6 (Blue) = 15/6 = 2.5. 
 Above the line: 5/6 * 2 (Red) + 5/6 (Blue) = 15/6 = 2.5. 
 

 Since the areas above and below the line are equal, the output is 7/6 = 1.16667
. 

 
 Constraints: 

 
 1 <= squares.length <= 5 * 10⁴ 
 squares[i] = [xi, yi, li] 
 squares[i].length == 3 
 0 <= xi, yi <= 10⁹ 
 1 <= li <= 10⁹ 
 The total area of all the squares will not exceed 10¹². 
 

 Related Topics Array Binary Search 👍 121 👎 27

*/