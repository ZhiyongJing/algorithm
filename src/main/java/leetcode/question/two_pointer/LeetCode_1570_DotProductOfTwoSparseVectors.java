package leetcode.question.two_pointer;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1570. Dot Product of Two Sparse Vectors
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 88.8%
 *@Time  Complexity: O(n), n 为非零值的数量，遍历非零值完成点积
 *@Space Complexity: O(n)，用于存储非零值的索引和值
 */
/**
 * @Question: 1570. Dot Product of Two Sparse Vectors
 *
 * 题目描述：
 * 给定两个稀疏向量（Sparse Vectors），稀疏向量是一个大部分元素为零的向量。
 * 实现一个类 SparseVector，提供一个方法 dotProduct，用于计算两个稀疏向量的点积。
 *
 * 点积定义为两个向量中对应位置的元素乘积之和：
 * 假设向量 A = [a1, a2, ..., an] 和向量 B = [b1, b2, ..., bn]，则点积为：
 * dotProduct(A, B) = a1*b1 + a2*b2 + ... + an*bn。
 *
 * 由于向量是稀疏的（大部分元素为零），直接遍历整个数组计算会浪费时间和空间。
 * 需要利用稀疏向量的特点，仅计算非零元素部分。
 *
 * 示例：
 * 输入：
 * nums1 = [1, 0, 0, 2, 3]
 * nums2 = [0, 3, 0, 4, 0]
 * 输出：
 * 8
 * 解释：
 * 非零元素点积计算为 2 * 4 = 8。
 *
 * 解题思路：
 * 1. 使用一个哈希表来存储稀疏向量中非零元素的索引和值。
 *    键是索引，值是对应的非零值。
 * 2. 在计算点积时，只需遍历第一个稀疏向量的非零元素：
 *    - 如果第二个稀疏向量在相同索引也有非零值，则将两者相乘并累加到结果中。
 * 3. 最终返回累加结果即为点积。
 *
 * 时间复杂度：
 * O(n1 + n2)，其中 n1 和 n2 是两个稀疏向量中非零值的数量。
 * - 构造哈希表时遍历向量，时间与非零值数量成正比。
 * - 计算点积时，遍历第一个向量的非零值，并查找第二个向量的非零值。
 *
 * 空间复杂度：
 * O(n1 + n2)，用于存储两个稀疏向量的非零值索引和值的哈希表。
 */

public class LeetCode_1570_DotProductOfTwoSparseVectors{

    //leetcode submit region begin(Prohibit modification and deletion)
    class SparseVector {
        // 使用哈希表存储稀疏向量中非零值的索引和值
        Map<Integer, Integer> mapping;

        // 构造函数，初始化稀疏向量
        SparseVector(int[] nums) {
            mapping = new HashMap<>();
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) { // 如果值不为零，记录索引和值
                    mapping.put(i, nums[i]);
                }
            }
        }

        // 计算当前稀疏向量与另一个稀疏向量的点积
        public int dotProduct(SparseVector vec) {
            int result = 0;

            // 遍历当前稀疏向量中的所有非零元素
            for (Integer i : this.mapping.keySet()) {
                if (vec.mapping.containsKey(i)) { // 如果另一个向量在相同索引也有非零值
                    // 将对应索引的值相乘并累加到结果中
                    result += this.mapping.get(i) * vec.mapping.get(i);
                }
            }
            return result; // 返回点积结果
        }
    }

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 示例测试用例
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 0};

        // 初始化两个稀疏向量
        SparseVector v1 = new LeetCode_1570_DotProductOfTwoSparseVectors().new SparseVector(nums1);
        SparseVector v2 = new LeetCode_1570_DotProductOfTwoSparseVectors().new SparseVector(nums2);

        // 计算点积并输出结果
        int ans = v1.dotProduct(v2);
        System.out.println("Dot Product: " + ans); // 输出点积结果
    }
}

/**
Given two sparse vectors, compute their dot product. 

 Implement class SparseVector: 

 
 SparseVector(nums) Initializes the object with the vector nums 
 dotProduct(vec) Compute the dot product between the instance of SparseVector 
and vec 
 

 A sparse vector is a vector that has mostly zero values, you should store the 
sparse vector efficiently and compute the dot product between two SparseVector. 

 Follow up: What if only one of the vectors is sparse? 

 
 Example 1: 

 
Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 

 Example 2: 

 
Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 

 Example 3: 

 
Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6
 

 
 Constraints: 

 
 n == nums1.length == nums2.length 
 1 <= n <= 10^5 
 0 <= nums1[i], nums2[i] <= 100 
 

 Related Topics Array Hash Table Two Pointers Design 👍 1246 👎 155

*/