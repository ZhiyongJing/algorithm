package leetcode.question.queue;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Question: 281. Zigzag Iterator
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 0.0%
 * @Time Complexity: O(1), N为所有输入列表中的元素总数
 * @Space Complexity: O(k), M为输入列表的数量
 */

/**
 * 这个问题的解题思路比较简单，主要通过使用一个队列来存储每个输入列表中下一个要遍历的元素的索引信息。具体步骤如下：
 *
 * 1. 在 `ZigzagIterator` 构造函数中，将输入的列表 `v1` 和 `v2` 存储到 `vectors` 列表中，并遍历所有输入列表，
 * 将第一个元素的索引信息（列表索引和元素索引）存储到队列 `queue` 中。
 *
 * 2. 在 `next()` 方法中，从队列头部取出一个指针，该指针包含了当前要访问的元素在 `vectors` 列表中的索引信息。
 * 取出元素后，将元素索引加一，如果仍有元素，将新的指针重新加入队列，以便下一次访问。
 *
 * 3. `hasNext()` 方法检查队列是否为空，如果为空则说明没有下一个元素。
 *
 * 这样，通过维护一个队列，我们可以按照题目要求实现交错遍历。
 *
 * 时间复杂度分析：
 * - 构造函数中，需要遍历所有输入列表，时间复杂度为 O(N)，其中 N 为所有输入列表中的元素总数。
 * - `next()` 方法中，取出队列头部元素的操作是 O(1) 的，因此 `next()` 方法的平均时间复杂度为 O(1)。
 * - `hasNext()` 方法中只需要检查队列是否为空，也是 O(1)。
 *
 * 因此，整体时间复杂度为 O(N)。
 *
 * 空间复杂度分析：
 * - 需要额外存储 `vectors` 列表，空间复杂度为 O(M)，其中 M 为输入列表的数量。
 * - 使用队列 `queue` 存储索引信息，最多存储 M 个元素，因此空间复杂度为 O(M)。
 *
 * 因此，整体空间复杂度为 O(M)。
 */

public class LeetCode_281_ZigzagIterator {

    // leetcode submit region begin(Prohibit modification and deletion)
    public class ZigzagIterator {
        private List<List<Integer>> vectors = new ArrayList<>();
        private LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.vectors.add(v1);
            this.vectors.add(v2);
            int index = 0;
            for (List<Integer> vec : this.vectors) {
                if (vec.size() > 0)
                    // <index_to_vec, index_to_element_within_vec>
                    this.queue.add(new Pair<Integer, Integer>(index, 0));
                index++;
            }
            System.out.println(vectors);
            System.out.println(queue);
        }

        /**
         * 获取下一个元素
         *
         * @return 下一个元素的值
         */
        public int next() {
            // 如果队列为空，则抛出异常或返回特殊值
            // if (this.queue.size() == 0)
            // throw new Exception("Out of elements!");

            // <index_to_vec, index_to_element_within_vec>
            Pair<Integer, Integer> pointer = this.queue.removeFirst();
            Integer vecIndex = pointer.getKey();
            Integer elemIndex = pointer.getValue();
            Integer nextElemIndex = elemIndex + 1;
            // 如果当前元素之后仍有元素，将指针添加回队列
            if (nextElemIndex < this.vectors.get(vecIndex).size())
                this.queue.addLast(new Pair<>(vecIndex, nextElemIndex));

            return this.vectors.get(vecIndex).get(elemIndex);
        }

        /**
         * 判断是否还有下一个元素
         *
         * @return 如果还有下一个元素，返回true；否则，返回false
         */
        public boolean hasNext() {
            return this.queue.size() > 0;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试代码
        List<Integer> v1 = Arrays.asList(1, 2);
        List<Integer> v2 = Arrays.asList(3, 4, 5, 6);
        ZigzagIterator zigzagIterator = new LeetCode_281_ZigzagIterator().new ZigzagIterator(v1, v2);

        // 遍历输出
        while (zigzagIterator.hasNext()) {
            System.out.print(zigzagIterator.next() + " ");
        }
        // 输出：1 3 2 4 5 6
    }
}

/**
Given two vectors of integers v1 and v2, implement an iterator to return their 
elements alternately. 

 Implement the ZigzagIterator class: 

 
 ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two 
vectors v1 and v2. 
 boolean hasNext() returns true if the iterator still has elements, and false 
otherwise. 
 int next() returns the current element of the iterator and moves the iterator 
to the next element. 
 

 
 Example 1: 

 
Input: v1 = [1,2], v2 = [3,4,5,6]
Output: [1,3,2,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, the order 
of elements returned by next should be: [1,3,2,4,5,6].
 

 Example 2: 

 
Input: v1 = [1], v2 = []
Output: [1]
 

 Example 3: 

 
Input: v1 = [], v2 = [1]
Output: [1]
 

 
 Constraints: 

 
 0 <= v1.length, v2.length <= 1000 
 1 <= v1.length + v2.length <= 2000 
 -2³¹ <= v1[i], v2[i] <= 2³¹ - 1 
 

 
 Follow up: What if you are given k vectors? How well can your code be extended 
to such cases? 

 Clarification for the follow-up question: 

 The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If 
"Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 

 Follow-up Example: 

 
Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
Output: [1,4,8,2,5,9,3,6,7]
 

 Related Topics Array Design Queue Iterator 👍 659 👎 36

*/