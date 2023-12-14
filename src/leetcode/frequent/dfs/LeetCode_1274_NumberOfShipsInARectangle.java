package leetcode.frequent.dfs;

/**
 * @Question:  1274. 矩形内船只的数目
 * @Difculty:  3 [1->简单, 2->中等, 3->困难]
 * @Frequency: 56.85%
 * @Time  Complexity: O(S*( log( max(M,N) ) - logS )M be the range of possible x-coordinate values between bottomLeft[0] and topRight[0] and let N be the range of possible y-coordinate values between bottomLeft[1] and topRight[1]. S is maximum number of ships
 * @Space Complexity: O(log ( max(M, N)))
 */

/**
 * ### 算法思路
 *
 * 这道题目要求计算海域中的船只数量，海域用二维平面表示，每艘船只位于一个整数坐标点上。给定的 `Sea`
 * 接口有一个方法 `hasShips(topRight, bottomLeft)`，它接受两个坐标点，表示一个矩形范围，返回该范围内是否包含船只。
 *
 * 为了解决这个问题，我们使用递归的深度优先搜索（DFS）方法。首先，我们检查当前矩形是否包含船只，如果不包含，则返回 0。
 * 接着，我们检查当前矩形是否只包含一个点，如果是，则返回 1。最后，如果矩形不是单点，则将其分为四个子矩形，分别递归地计算每个子矩形内的船只数量，
 * 然后将结果相加。
 *
 * ### 时间复杂度
 *
 * - 假设海域内有 n 艘船只，每次递归将当前矩形分为 4 个子矩形，递归深度最多为 log(n)。
 * - 在每个递归步骤中，我们调用 `hasShips` 方法，其时间复杂度为 O(1)。
 * - 因此，总的时间复杂度为 O(log(n))。
 *
 * ### 空间复杂度
 *
 * - 递归深度最多为 log(n)，因此递归调用的空间复杂度为 O(log(n))，即为递归栈的大小。
 *
 * 总体而言，该算法的时间复杂度和空间复杂度都是相对较小的。
 */

public class LeetCode_1274_NumberOfShipsInARectangle {

    //leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // 这是 Sea 的 API 接口。
     * // 你不应该实现它，或者假设它的实现
     * class Sea {
     *     public boolean hasShips(int[] topRight, int[] bottomLeft);
     * }
     */

    class Solution {
        public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
            // 如果当前矩形不包含任何船只，返回 0。
            if (bottomLeft[0] > topRight[0] || bottomLeft[1] > topRight[1])
                return 0;
            if (!sea.hasShips(topRight, bottomLeft))
                return 0;

            // 如果矩形表示一个点，那么船只位于该点
            if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1])
                return 1;

            // 递归地检查矩形的 4 个子矩形中是否有船只
            int midX = (topRight[0] + bottomLeft[0]) / 2;
            int midY = (topRight[1] + bottomLeft[1]) / 2;
            return countShips(sea, new int[]{midX, midY}, bottomLeft) +
                    countShips(sea, topRight, new int[]{midX + 1, midY + 1}) +
                    countShips(sea, new int[]{midX, topRight[1]}, new int[]{bottomLeft[0], midY + 1}) +
                    countShips(sea, new int[]{topRight[0], midY}, new int[]{midX + 1, bottomLeft[1]});
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1274_NumberOfShipsInARectangle().new Solution();

        // 测试用例1
        Sea sea1 = new Sea(new int[][]{{1, 1}, {2, 2}, {3, 3}, {5, 5}});
        int[] topRight1 = {4, 4};
        int[] bottomLeft1 = {0, 0};
        int result1 = solution.countShips(sea1, topRight1, bottomLeft1);
        System.out.println("Test Case 1: " + result1);  // 应该输出 3

        // 测试用例2
        Sea sea2 = new Sea(new int[][]{{1, 1}, {2, 2}, {3, 3}});
        int[] topRight2 = {1000, 1000};
        int[] bottomLeft2 = {0, 0};
        int result2 = solution.countShips(sea2, topRight2, bottomLeft2);
        System.out.println("Test Case 2: " + result2);  // 应该输出 3
    }

    // 实现 Sea 接口，用于测试
    static class Sea {
        int[][] ships;

        public Sea(int[][] ships) {
            this.ships = ships;
        }

//        @Override
        public boolean hasShips(int[] topRight, int[] bottomLeft) {
            for (int[] ship : ships) {
                if (ship[0] <= topRight[0] && ship[0] >= bottomLeft[0] &&
                        ship[1] <= topRight[1] && ship[1] >= bottomLeft[1]) {
                    return true;
                }
            }
            return false;
        }
    }
}

/**
(This problem is an interactive problem.)

 Each ship is located at an integer point on the sea represented by a cartesian
plane, and each integer point may contain at most 1 ship.

 You have a function Sea.hasShips(topRight, bottomLeft) which takes two points
as arguments and returns true If there is at least one ship in the rectangle
represented by the two points, including on the boundary.

 Given two points: the top right and bottom left corners of a rectangle, return
the number of ships present in that rectangle. It is guaranteed that there are
at most 10 ships in that rectangle.

 Submissions making more than 400 calls to hasShips will be judged Wrong Answer.
 Also, any solutions that attempt to circumvent the judge will result in
disqualification.


 Example :


Input:
ships = [[1,1],[2,2],[3,3],[5,5]], topRight = [4,4], bottomLeft = [0,0]
Output: 3
Explanation: From [0,0] to [4,4] we can count 3 ships within the range.


 Example 2:


Input: ans = [[1,1],[2,2],[3,3]], topRight = [1000,1000], bottomLeft = [0,0]
Output: 3



 Constraints:


 On the input ships is only given to initialize the map internally. You must
solve this problem "blindfolded". In other words, you must find the answer using
the given hasShips API, without knowing the ships position.
 0 <= bottomLeft[0] <= topRight[0] <= 1000
 0 <= bottomLeft[1] <= topRight[1] <= 1000
 topRight != bottomLeft


 Related Topics Array Divide and Conquer Interactive 👍 519 👎 56

*/
