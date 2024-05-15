package interview.company.amazon;
//package leetcode.question.heap;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Question: 973. K Closest Points to Origin
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 57.91%
 * @Time Complexity: O(NlogN), where N is the number of points
 * @Space Complexity: O(N)
 */

/**
 * **解题思路：**
 *
 * 这段代码解决的问题是找到距离原点最近的K个点。解题思路主要包括以下几个步骤：
 *
 * 1. 对每个点计算到原点的距离的平方，通过函数 `dist` 实现。这里为了避免使用浮点数开方，直接计算距离的平方，因为比较大小并不受影响。
 *
 * 2. 将每个点的距离平方存储在数组 `dists` 中。
 *
 * 3. 对距离数组 `dists` 进行排序。
 *
 * 4. 找到排序后数组中第 K 个距离的平方，记为 `distK`。
 *
 * 5. 遍历原始点集，将距离原点小于等于 `distK` 的点加入结果数组 `ans` 中。
 *
 * 6. 返回结果数组 `ans`。
 *
 * **时间复杂度：**
 *
 * - 首先，计算每个点到原点的距离平方的时间复杂度为 O(N)，其中 N 是点的个数。
 * - 排序距离数组 `dists` 的时间复杂度为 O(NlogN)。
 * - 遍历点集合的时间复杂度为 O(N)。
 * - 因此，总体时间复杂度为 O(N + NlogN + N) = O(NlogN)。
 *
 * **空间复杂度：**
 *
 * - 使用了一个大小为 N 的数组 `dists` 来存储每个点到原点的距离平方，因此空间复杂度为 O(N)。
 * - 结果数组 `ans` 的空间复杂度为 O(K)。
 * - 因此，总体空间复杂度为 O(N)。
 */

public class LeetCode_973_KClosestPointsToOrigin {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 获取距离原点最近的K个点的坐标
         *
         * @param points 二维数组，表示坐标点集合
         * @param K      需要获取的最近点的个数
         * @return 返回距离原点最近的K个点的坐标数组
         */
        public int[][] kClosest(int[][] points, int K) {
            int N = points.length;
            Queue<Pair<Integer, Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue()- o1.getValue());
            int[] dists = new int[N];

            // 计算每个点到原点的距离并存储在 dists 数组中
            for (int i = 0; i < N; ++i){
                heap.add(new Pair<>(i, dist(points[i])));
                if(heap.size() > K){
                    heap.poll();
                }
            }
//
//            // 对距离数组进行排序
//            Arrays.sort(dists);
//            int distK = dists[K - 1];
//
//            int[][] ans = new int[K][2];
//            int t = 0;
//
//            // 遍历点集合，将距离小于等于 distK 的点加入结果数组
//            for (int i = 0; i < N; ++i)
//                if (dist(points[i]) <= distK)
//                    ans[t++] = points[i];
            System.out.println(heap);
            int[][] ans = new int[K][2];
            int size = heap.size();
            for(int i = 0; i < size; i++){
                Pair<Integer, Integer> point = heap.poll();
                ans[i][0] = points[point.getKey()][0];
                ans[i][1] = points[point.getKey()][1];
            }

            return ans;
        }

        /**
         * 计算点到原点的距离的平方
         *
         * @param point 二维数组，表示坐标点
         * @return 返回点到原点的距离的平方
         */
        public int dist(int[] point) {
            return point[0] * point[0] + point[1] * point[1];
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        leetcode.question.heap.LeetCode_973_KClosestPointsToOrigin.Solution solution = new leetcode.question.heap.LeetCode_973_KClosestPointsToOrigin().new Solution();
        int[][] points = {{1, 3}, {-2, 2}, {2, -2}};
        int K = 2;
        int[][] result = solution.kClosest(points, K);
        System.out.println("The closest " + K + " points to the origin are: " + Arrays.deepToString(result));
        // 输出：The closest 2 points to the origin are: [[-2, 2], [2, -2]]
    }
}

/**
 Given an array of points where points[i] = [xi, yi] represents a point on the X-
 Y plane and an integer k, return the k closest points to the origin (0, 0).

 The distance between two points on the X-Y plane is the Euclidean distance (i.
 e., √(x1 - x2)² + (y1 - y2)²).

 You may return the answer in any order. The answer is guaranteed to be unique (
 except for the order that it is in).


 Example 1:


 Input: points = [[1,3],[-2,2]], k = 1
 Output: [[-2,2]]
 Explanation:
 The distance between (1, 3) and the origin is sqrt(10).
 The distance between (-2, 2) and the origin is sqrt(8).
 Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 We only want the closest k = 1 points from the origin, so the answer is just [[-
 2,2]].


 Example 2:


 Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 Output: [[3,3],[-2,4]]
 Explanation: The answer [[-2,4],[3,3]] would also be accepted.



 Constraints:


 1 <= k <= points.length <= 10⁴
 -10⁴ <= xi, yi <= 10⁴


 Related Topics Array Math Divide and Conquer Geometry Sorting Heap (Priority
 Queue) Quickselect 👍 8119 👎 289

 */