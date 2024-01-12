package leetcode.question.heap;

import java.util.PriorityQueue;

/**
  *@Question:  295. Find Median from Data Stream     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 62.34%      
  *@Time  Complexity: O(log N) - 插入和获取中位数的操作的时间复杂度
  *@Space Complexity: O(N) - 堆的空间复杂度
 */

/**
 * **问题描述：**
 *
 * 这个问题是在数据流中找中位数。具体来说，我们需要设计一个数据结构，支持在不断添加数字到数据流中的同时，
 * 能够实时获取当前数据流的中位数。
 *
 * **解题思路：**
 *
 * 为了在不断添加数字的过程中实时获取中位数，我们可以利用两个堆来维护数据流的两部分：
 * 大顶堆（Max Heap）和小顶堆（Min Heap）。大顶堆存储数据流中较小的一半元素，小顶堆存储数据流中较大的一半元素。
 * 这样，中位数就可以通过这两个堆的堆顶元素来得到。
 *
 * **具体实现：**
 *
 * 1. **初始化：** 我们使用一个计数器 `count` 来统计数据流中的元素个数。同时，初始化一个大顶堆 `maxHeap`
 * 和一个小顶堆 `minHeap`。大顶堆和小顶堆的元素之和为 `count`。
 *
 * 2. **添加元素：** 当有新元素加入数据流时，首先将元素添加到大顶堆中。然后，将大顶堆的堆顶元素弹出并添加到小顶堆中。
 * 如果两个堆合起来的元素个数是奇数，再将小顶堆的堆顶元素弹出并添加到大顶堆中。
 *
 * 3. **获取中位数：** 中位数的获取取决于两个堆的元素个数。如果元素个数是偶数，则中位数是两个堆顶元素的平均值；
 * 如果元素个数是奇数，则中位数是大顶堆的堆顶元素。
 *
 * **时间复杂度：**
 *
 * 1. 添加元素的时间复杂度为插入到两个堆中的复杂度之和，即 O(log N)（其中 N 是两个堆的元素之和）。
 * 2. 获取中位数的时间复杂度为 O(1)，因为我们只需要访问两个堆的堆顶元素。
 *
 * **空间复杂度：**
 *
 * 我们使用了两个堆来维护数据流的两部分，因此空间复杂度为 O(N)（其中 N 是数据流的元素个数）。
 */



public class LeetCode_295_FindMedianFromDataStream {

    //leetcode submit region begin(Prohibit modification and deletion)
    class MedianFinder {

        /**
         * 当前大顶堆和小顶堆的元素个数之和
         */
        private int count;
        private PriorityQueue<Integer> maxHeap; // 大顶堆，存储数据流中较小的一半元素
        private PriorityQueue<Integer> minHeap; // 小顶堆，存储数据流中较大的一半元素

        /**
         * 初始化数据结构
         */
        public MedianFinder() {
            count = 0;
            maxHeap = new PriorityQueue<>((x, y) -> y - x); // 大顶堆，比较器定义为降序
            minHeap = new PriorityQueue<>(); // 小顶堆，默认是升序
        }

        /**
         * 向数据流中添加一个元素
         * @param num 要添加的元素
         */
        public void addNum(int num) {
            count += 1;
            maxHeap.offer(num);
            minHeap.add(maxHeap.poll());

            // 如果两个堆合起来的元素个数是奇数，小顶堆要拿出堆顶元素给大顶堆
            if ((count & 1) != 0) {
                maxHeap.add(minHeap.poll());
            }
        }

        /**
         * 获取数据流的中位数
         * @return 数据流的中位数
         */
        public double findMedian() {
            if ((count & 1) == 0) {
                // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
                return (double) (maxHeap.peek() + minHeap.peek()) / 2;
            } else {
                // 如果两个堆合起来的元素个数是奇数，数据流的中位数是大顶堆的堆顶元素
                return (double) maxHeap.peek();
            }
        }
    }

    /**
     * 实例化并使用MedianFinder对象的示例代码
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_295_FindMedianFromDataStream.MedianFinder medianFinder = new LeetCode_295_FindMedianFromDataStream().new MedianFinder();

        // 测试代码
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        double median1 = medianFinder.findMedian(); // 应输出 1.5
        System.out.println("当前中位数: " + median1);

        medianFinder.addNum(3);
        double median2 = medianFinder.findMedian(); // 应输出 2.0
        System.out.println("当前中位数: " + median2);
    }
}

/**
The median is the middle value in an ordered integer list. If the size of the 
list is even, there is no middle value, and the median is the mean of the two 
middle values. 

 
 For example, for arr = [2,3,4], the median is 3. 
 For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5. 
 

 Implement the MedianFinder class: 

 
 MedianFinder() initializes the MedianFinder object. 
 void addNum(int num) adds the integer num from the data stream to the data 
structure. 
 double findMedian() returns the median of all elements so far. Answers within 1
0⁻⁵ of the actual answer will be accepted. 
 

 
 Example 1: 

 
Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

 
 Constraints: 

 
 -10⁵ <= num <= 10⁵ 
 There will be at least one element in the data structure before calling 
findMedian. 
 At most 5 * 10⁴ calls will be made to addNum and findMedian. 
 

 
 Follow up: 

 
 If all integer numbers from the stream are in the range [0, 100], how would 
you optimize your solution? 
 If 99% of all integer numbers from the stream are in the range [0, 100], how 
would you optimize your solution? 
 

 Related Topics Two Pointers Design Sorting Heap (Priority Queue) Data Stream 👍
 11455 👎 228

*/