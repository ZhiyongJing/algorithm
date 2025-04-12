package leetcode.question.heap;

import java.util.PriorityQueue;

/**
 *@Question:  703. Kth Largest Element in a Stream
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 54.13%
 *@Time  Complexity: O( (M + N)logK), M is size of initial stream nums, N is the number of calls to add
 *@Space Complexity: O(K)
 */

public class LeetCode_703_KthLargestElementInAStream{

    //leetcode submit region begin(Prohibit modification and deletion)
    class KthLargest {

        // 小顶堆，用于维护前 K 大的元素
        PriorityQueue<Integer> minHeap;
        // 第 K 大的索引值
        int k;

        // 构造函数，初始化堆并处理初始数组
        public KthLargest(int k, int[] nums) {
            minHeap = new PriorityQueue<>();
            this.k = k;

            // 把初始数组的元素依次加入 add() 方法中，维护堆
            for (int num : nums) {
                add(num);
            }
        }

        // 每次加入新元素后，返回当前第 K 大的值
        public int add(int val) {
            // 如果当前堆中元素不足 k 个，或者 val 比堆顶大，加入堆中
            if (minHeap.size() < k || minHeap.peek() < val) {
                minHeap.add(val);
                // 加入后如果超过 k 个，移除堆顶（最小值）
                if (minHeap.size() > k) {
                    minHeap.remove();
                }
            }
            // 堆顶元素即为当前第 K 大的元素
            return minHeap.peek();
        }
    }

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 初始化：找第 3 大的元素，初始流为 [4, 5, 8, 2]
        int k = 3;
        int[] nums = {4, 5, 8, 2};
        KthLargest kthLargest = new LeetCode_703_KthLargestElementInAStream().new KthLargest(k, nums);

        // 测试样例：每次加入一个新数，返回当前第 K 大的值
        System.out.println("add(3): " + kthLargest.add(3));   // 输出 4
        System.out.println("add(5): " + kthLargest.add(5));   // 输出 5
        System.out.println("add(10): " + kthLargest.add(10)); // 输出 5
        System.out.println("add(9): " + kthLargest.add(9));   // 输出 8
        System.out.println("add(4): " + kthLargest.add(4));   // 输出 8
    }
}

/**
You are part of a university admissions office and need to keep track of the 
kth highest test score from applicants in real-time. This helps to determine cut-
off marks for interviews and admissions dynamically as new applicants submit 
their scores. 

 You are tasked to implement a class which, for a given integer k, maintains a 
stream of test scores and continuously returns the kth highest test score after 
a new score has been submitted. More specifically, we are looking for the kth 
highest score in the sorted list of all scores. 

 Implement the KthLargest class: 

 
 KthLargest(int k, int[] nums) Initializes the object with the integer k and 
the stream of test scores nums. 
 int add(int val) Adds a new test score val to the stream and returns the 
element representing the kᵗʰ largest element in the pool of test scores so far. 
 

 
 Example 1: 

 
 Input: ["KthLargest", "add", "add", "add", "add", "add"] [[3, [4, 5, 8, 2]], [3
], [5], [10], [9], [4]] 
 

 Output: [null, 4, 5, 5, 8, 8] 

 Explanation: 

 KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]); kthLargest.add(3); // 
return 4 kthLargest.add(5); // return 5 kthLargest.add(10); // return 5 
kthLargest.add(9); // return 8 kthLargest.add(4); // return 8 

 Example 2: 

 
 Input: ["KthLargest", "add", "add", "add", "add"] [[4, [7, 7, 7, 7, 8, 3]], [2]
, [10], [9], [9]] 
 

 Output: [null, 7, 7, 7, 8] 

 Explanation: KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
 kthLargest.add(2); // return 7
 kthLargest.add(10); // return 7
 kthLargest.add(9); // return 7
 kthLargest.add(9); // return 8

 
 Constraints: 

 
 0 <= nums.length <= 10⁴ 
 1 <= k <= nums.length + 1 
 -10⁴ <= nums[i] <= 10⁴ 
 -10⁴ <= val <= 10⁴ 
 At most 10⁴ calls will be made to add. 
 

 Related Topics Tree Design Binary Search Tree Heap (Priority Queue) Binary 
Tree Data Stream 👍 6051 👎 3867

*/