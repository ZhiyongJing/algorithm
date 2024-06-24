package leetcode.question.tree_map;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 *@Question:  480. Sliding Window Median
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.169999999999995%
 *@Time  Complexity: O(NlogN)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题的核心是使用两个平衡树结构来维护滑动窗口内的元素，并在每次窗口移动时高效地计算中位数。详细解题思路如下：
 *
 * 1. **定义比较器和两个 TreeSet**：
 *    - 定义一个比较器，用于比较数组中两个元素的值，如果值相同则按索引比较。
 *    - 使用这个比较器创建两个 TreeSet，分别存储滑动窗口的左半部分和右半部分。左半部分存储较小的元素，右半部分存储较大的元素。
 *
 * 2. **初始化窗口**：
 *    - 将数组的前 k 个元素加入到左半部分的 TreeSet 中。
 *
 * 3. **平衡 TreeSet**：
 *    - 保持左半部分和右半部分的元素数量尽量相等，使得左半部分的最大值小于或等于右半部分的最小值。
 *
 * 4. **计算第一个窗口的中位数**：
 *    - 根据窗口大小 k 的奇偶性来确定中位数的计算方式。如果 k 是奇数，中位数为右半部分的最小值；如果 k 是偶数，中位数为左半部分的最大值和右半部分的最小值的平均值。
 *
 * 5. **滑动窗口**：
 *    - 从第 k 个元素开始，依次将新元素加入窗口，同时将旧元素移出窗口。
 *    - 根据比较结果，将新元素加入到左半部分或右半部分。
 *    - 每次加入新元素后，将右半部分的最小值移动到左半部分，以保持平衡。
 *    - 从窗口中移出旧元素时，也需要维护平衡。
 *    - 在每次滑动后，重新计算当前窗口的中位数并存储到结果数组中。
 *
 * 6. **返回结果**：
 *    - 返回存储了所有滑动窗口中位数的结果数组。
 *
 * ### 时间复杂度
 *
 * - **插入和删除操作**：每次插入和删除操作的复杂度为 O(log k)，因为 TreeSet 是基于红黑树实现的，支持对数时间复杂度的插入和删除操作。
 * - **窗口滑动**：窗口滑动 n 次，每次滑动需要进行两次插入和删除操作。因此，总时间复杂度为 O(n log k)。
 *
 * ### 空间复杂度
 *
 * - **TreeSet 存储**：两个 TreeSet 的总空间复杂度为 O(k)，因为它们总共存储 k 个元素。
 * - **结果数组**：结果数组的空间复杂度为 O(n - k + 1)。
 *
 * 综上所述，该算法的时间复杂度为 O(n log k)，空间复杂度为 O(k)。
 */
public class LeetCode_480_SlidingWindowMedian{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            // 定义一个比较器，用于比较数组中两个元素的值
            Comparator<Integer> comparator = new Comparator<Integer>(){
                public int compare(Integer a, Integer b) {
                    // 如果两个元素的值不相同，则按值比较
                    if (nums[a] != nums[b]) {
                        return Integer.compare(nums[a], nums[b]);
                    }
                    // 否则按索引比较
                    else {
                        return a - b;
                    }
                }
            };
            // 定义两个 TreeSet，分别存储左半部分和右半部分的元素
            TreeSet<Integer> left = new TreeSet<Integer>(comparator.reversed());
            TreeSet<Integer> right = new TreeSet<Integer>(comparator);
            // 存储结果数组
            double[] res = new double[nums.length - k + 1];

            // 初始化窗口，将前 k 个元素加入左半部分
            for (int i = 0; i < k; i++) {
                left.add(i);
            }
            // 平衡两个 TreeSet，使得左半部分和右半部分元素数量相近
            balance(left, right);
            // 计算第一个窗口的中位数
            res[0] = getMedian(k, nums, left, right);

            // 移动窗口，计算每个窗口的中位数
            int r = 1;
            for (int i = k; i < nums.length; i++) {
                // 从窗口中移除左边的元素
                if(!left.remove(i - k)) {
                    right.remove(i - k);
                }
                // 将新的元素加入右半部分
                right.add(i);
                // 将右半部分最小的元素移到左半部分
                left.add(right.pollFirst());
                // 平衡两个 TreeSet
                balance(left, right);
                // 计算当前窗口的中位数
                res[r] = getMedian(k, nums, left, right);
                r++;
            }

            return res;
        }

        // 平衡两个 TreeSet，使得左半部分和右半部分元素数量相近
        private void balance(TreeSet<Integer> left, TreeSet<Integer> right) {
            while (left.size() > right.size()) {
                right.add(left.pollFirst());
            }
        }

        // 计算当前窗口的中位数
        private double getMedian(int k, int[] nums, TreeSet<Integer> left, TreeSet<Integer> right) {
            if (k % 2 == 0) {
                return ((double) nums[left.first()] + nums[right.first()]) / 2;
            }
            else {
                return (double) nums[right.first()];
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_480_SlidingWindowMedian().new Solution();

        // 测试样例
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        double[] result = solution.medianSlidingWindow(nums, k);

        // 输出结果
        System.out.println(Arrays.toString(result)); // 应该输出 [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}

/**
 The median is the middle value in an ordered integer list. If the size of the
 list is even, there is no middle value. So the median is the mean of the two
 middle values.


 For examples, if arr = [2,3,4], the median is 3. 
 For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5. 


 You are given an integer array nums and an integer k. There is a sliding 
 window of size k which is moving from the very left of the array to the very right.
 You can only see the k numbers in the window. Each time the sliding window moves
 right by one position.

 Return the median array for each window in the original array. Answers within 1
 0⁻⁵ of the actual value will be accepted.


 Example 1: 


 Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 Explanation:
 Window position                Median
 ---------------                -----
 [1  3  -1] -3  5  3  6  7        1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7        3
 1  3  -1  -3 [5  3  6] 7        5
 1  3  -1  -3  5 [3  6  7]       6


 Example 2: 


 Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
 Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]



 Constraints: 


 1 <= k <= nums.length <= 10⁵ 
 -2³¹ <= nums[i] <= 2³¹ - 1 


 Related Topics Array Hash Table Sliding Window Heap (Priority Queue) 👍 3193 👎
 199

 */