package leetcode.frequent.based_on_data_structure.heap;

import java.util.PriorityQueue;

/**
  *@Question:  378. Kth Smallest Element in a Sorted Matrix     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 43.15%      
  *@Time  Complexity: O(min(N, K) + K*log(min(N, K)))
  *@Space Complexity: O(min(N, K))
 */s

/**
 * 这个问题是求解一个有序矩阵中第K小的元素。解决这个问题的关键在于利用矩阵的有序性，
 * 以及使用最小堆（PriorityQueue）来维护当前可能的K个最小元素。
 *
 * **解题思路：**
 *
 * 1. **构建最小堆：** 首先，我们通过将每一行的第一个元素加入最小堆来构建初始的最小堆。
 * 这个最小堆中的元素包括元素的值、所在行号和列号。
 *
 * 2. **迭代直到找到第K小的元素：** 接下来，我们进行K次迭代，每次从堆中取出当前堆顶元素（最小元素），
 * 并将其所在行的下一个元素加入堆中。通过这个迭代，我们不断找到当前堆中的最小元素，直到找到第K小的元素为止。
 *
 * **具体步骤：**
 *
 * - 将每行的第一个元素加入最小堆，堆的大小限制为N和K中的较小值。
 * - 从堆中取出最小元素，记为element。
 * - 如果element所在行还有下一个元素，则将其加入堆中。
 * - 重复步骤2和3，直到找到第K小的元素为止。
 *
 * **时间复杂度：**
 *
 * 由于我们最多进行K次迭代，每次迭代都需要从堆中取出最小元素和添加一个新元素，而这两个操作的时间复杂度都是O(logN)。
 * 因此，总的时间复杂度为O(K*logN)。
 *
 * **空间复杂度：**
 *
 * 我们使用了一个最小堆来维护可能的K个最小元素，其大小取决于N和K中的较小值。因此，空间复杂度为O(min(N, K))。
 */

public class LeetCode_378_KthSmallestElementInASortedMatrix{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        class MyHeapNode {

            int row;
            int column;
            int value;

            public MyHeapNode(int v, int r, int c) {
                this.value = v;
                this.row = r;
                this.column = c;
            }

            public int getValue() {
                return this.value;
            }

            public int getRow() {
                return this.row;
            }

            public int getColumn() {
                return this.column;
            }
        }

        public int kthSmallest(int[][] matrix, int k) {

            int N = matrix.length;

            // 使用优先队列构建最小堆，保留堆的大小为N和k中的较小值
            // 使用lambda表达式定义比较器
            PriorityQueue<MyHeapNode> minHeap =
                    new PriorityQueue<MyHeapNode>(Math.min(N, k), (x, y) -> x.value - y.value);

            // 初始化最小堆，将每行的第一个元素添加到堆中
            for (int r = 0; r < Math.min(N, k); r++) {
                minHeap.offer(new MyHeapNode(matrix[r][0], r, 0));
            }

            MyHeapNode element = minHeap.peek();
            while (k-- > 0) {
                // 从堆中取出最小元素
                element = minHeap.poll();
                int r = element.getRow(), c = element.getColumn();

                // 如果当前行还有未添加到堆的元素，则将下一个元素添加到堆中
                if (c < N - 1) {
                    minHeap.offer(new MyHeapNode(matrix[r][c + 1], r, c + 1));
                }
            }

            return element.getValue();
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_378_KthSmallestElementInASortedMatrix.Solution solution = new LeetCode_378_KthSmallestElementInASortedMatrix().new Solution();
        // 测试代码
        int[][] matrix = {
                {1,  5,  9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k = 8;
        int result = solution.kthSmallest(matrix, k);
        System.out.println("矩阵中第 " + k + " 小的元素是: " + result);
    }
}

/**
Given an n x n matrix where each of the rows and columns is sorted in ascending 
order, return the kᵗʰ smallest element in the matrix. 

 Note that it is the kᵗʰ smallest element in the sorted order, not the kᵗʰ 
distinct element. 

 You must find a solution with a memory complexity better than O(n²). 

 
 Example 1: 

 
Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8
ᵗʰ smallest number is 13
 

 Example 2: 

 
Input: matrix = [[-5]], k = 1
Output: -5
 

 
 Constraints: 

 
 n == matrix.length == matrix[i].length 
 1 <= n <= 300 
 -10⁹ <= matrix[i][j] <= 10⁹ 
 All the rows and columns of matrix are guaranteed to be sorted in non-
decreasing order. 
 1 <= k <= n² 
 

 
 Follow up: 

 
 Could you solve the problem with a constant memory (i.e., O(1) memory 
complexity)? 
 Could you solve the problem in O(n) time complexity? The solution may be too 
advanced for an interview but you may find reading this paper fun. 
 

 Related Topics Array Binary Search Sorting Heap (Priority Queue) Matrix 👍 9649
 👎 334

*/