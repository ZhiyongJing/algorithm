package leetcode.question.two_pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  281. Zigzag Iterator
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 62.67%
 *@Time  Complexity: Solution1: O(K) for next, O(1) for hasNext; Solution2: O(1) for next and hasNext
 *@Space Complexity: O(K)
 */
/**
 * 第一步：题目描述：
 * ------------------------------------------------------------
 * LeetCode 281 - Zigzag Iterator（之字形迭代器）
 *
 * 给定两个或多个一维列表，实现一个交错迭代器，使其依次从每个列表中轮流返回一个元素。
 * 当某个列表被遍历完时，跳过该列表继续从其他未完成的列表中轮询。
 *
 * 示例：
 * 输入：
 *   v1 = [1, 3, 5]
 *   v2 = [2, 4, 6, 8, 10]
 * 输出：1, 2, 3, 4, 5, 6, 8, 10
 * 即每次从不同列表交替取值，当某个列表没有更多元素时跳过它。
 *
 * 第二步：解题思路（基于两种代码实现，逐步详细说明）：
 * ------------------------------------------------------------
 * ✅ 解法一（Solution1：双指针模拟，适合两个列表）：
 *
 * 核心变量：
 * - vectors: 存储两个列表
 * - pVec: 当前访问哪个列表（轮流切换）
 * - pElem: 当前访问的是该列表中的哪个元素
 * - totalNum: 所有元素的总数
 * - outputCount: 已输出的元素数量
 *
 * 解题步骤：
 * 1. 初始化时记录两个列表，并计算所有元素总数。
 * 2. 每次调用 next():
 *    - 从当前列表的 pElem 位置尝试读取元素；
 *    - 如果该位置存在有效值则返回，并更新 outputCount；
 *    - 然后 pVec 向右轮换一次（%2），一轮结束后 pElem +1；
 *    - 如果没有取到值继续循环（最多试两次）。
 * 3. hasNext(): 判断 outputCount 是否小于 totalNum。
 *
 * 示例：
 *   vectors = [[1,3,5], [2,4,6,8]]
 *   pVec/pElem 变化如下：
 *   (0,0)→1, (1,0)→2, (0,1)→3, (1,1)→4, (0,2)→5, (1,2)→6, (1,3)→8
 *
 * ✅ 解法二（Solution2：队列存活跃索引，适合任意个列表）：
 *
 * 核心结构：
 * - 使用一个队列存储 Pair<列表索引, 元素索引>
 * - 每次取出一个 Pair，获取该位置元素，加入下一个元素位置到队尾（如果有）
 *
 * 解题步骤：
 * 1. 初始化时，遍历所有列表，若非空，则加入 Pair<vecIndex, 0> 到队列；
 * 2. next():
 *    - 取出队首 Pair(vecIdx, elemIdx)，访问 vectors[vecIdx][elemIdx]；
 *    - 若还有下一个元素，则加入 Pair(vecIdx, elemIdx + 1) 到队尾；
 *    - 返回当前元素。
 * 3. hasNext2(): 判断队列是否非空。
 *
 * 示例：
 *   v1 = [1,3], v2 = [2,4,6], v3 = [5]
 *   初始队列：[(0,0), (1,0), (2,0)]
 *   输出顺序：1(v1) → 2(v2) → 5(v3) → 3(v1) → 4(v2) → 6(v2)
 *
 * 第三步：时间与空间复杂度分析：
 * ------------------------------------------------------------
 * ✅ 解法一（适合两个列表）：
 * - next(): O(K)，K为列表数量，这里是2；
 * - hasNext(): O(1)
 * - 空间复杂度：O(1)，只维护几个索引变量
 *
 * ✅ 解法二（适合多个列表）：
 * - next(): O(1)
 * - hasNext(): O(1)
 * - 空间复杂度：O(k)，k 为非空列表数，队列最多存 k 个活跃元素索引
 *
 * ✅ 解法已通过 LeetCode 测试，结果正确，适用于交错输出多个列表元素。
 */

public class LeetCode_281_ZigzagIterator{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class ZigzagIterator {
        // 解法一：使用两个指针轮流遍历多个列表

        // 存储所有输入的列表（如v1, v2）
        private List<List<Integer>> vectors = new ArrayList<>();
        // pVec 表示当前遍历的是哪个列表；pElem 表示当前要取列表中的第几个元素
        private Integer pVec = 0, pElem = 0;
        // totalNum 表示总共有多少个元素；outputCount 表示已经输出了多少个
        private Integer totalNum = 0, outputCount = 0;

        // 构造函数，接收两个列表作为输入
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            // 把两个输入列表添加到 vectors 中
            this.vectors.add(v1);
            this.vectors.add(v2);
            // 计算总共有多少个元素
            for (List<Integer> vec : this.vectors) {
                this.totalNum += vec.size();
            }
        }

        // 返回下一个元素
        public int next() {
            // iterNum 记录循环轮数；ret 用于存储当前返回的值
            Integer iterNum = 0, ret = null;
            // 最多尝试遍历 vectors.size() 次，避免死循环
            while (iterNum < this.vectors.size()) {
                // 获取当前指向的列表
                List<Integer> currVec = this.vectors.get(this.pVec);
                // 如果当前元素索引未超出该列表长度，则可以取值
                if (this.pElem < currVec.size()) {
                    // 获取当前元素并存储
                    ret = currVec.get(this.pElem);
                    // 已输出元素数量+1
                    this.outputCount += 1;
                }

                // 尝试访问下一个列表
                iterNum += 1;
                this.pVec = (this.pVec + 1) % this.vectors.size();
                // 如果一轮访问结束（所有列表都访问完），则元素索引 +1，下一轮访问下一行
                if (this.pVec == 0)
                    this.pElem += 1;

                // 如果当前已经找到结果，直接返回
                if (ret != null)
                    return ret;
            }
            // 如果全部元素都访问完，理论上这里应该抛异常
            return 0;
        }

        // 判断是否还有下一个元素
        public boolean hasNext() {
            // 输出数量小于总数就说明还有元素可取
            return this.outputCount < this.totalNum;
        }

        // solution2:
//        private List<List<Integer>> vectors = new ArrayList<>();
//        // 使用队列保存每个待访问的元素位置，Pair<列表索引, 当前元素在该列表中的索引>
//        private LinkedList<Pair<Integer, Integer>> queue = new LinkedList<>();
//
//        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//            // 将两个列表加入 vectors
//            this.vectors.add(v1);
//            this.vectors.add(v2);
//            int index = 0;
//            // 初始化队列，将每个非空列表的首个元素位置信息加入队列
//            for (List<Integer> vec : this.vectors) {
//                if (vec.size() > 0)
//                    // 每个元素为 <列表索引, 元素索引>，起始为 (index, 0)
//                    this.queue.add(new Pair<Integer, Integer>(index, 0));
//                index++;
//            }
//        }
//
//        public int next() {
//            // 如果队列为空，理论上应抛出异常（已被注释）
//            // if (this.queue.size() == 0)
//            //     throw new Exception("Out of elements!");
//
//            // 弹出队首元素，表示当前要访问的 (vector_index, element_index)
//            Pair<Integer, Integer> pointer = this.queue.removeFirst();
//            Integer vecIndex = pointer.getKey();       // 当前要访问的列表索引
//            Integer elemIndex = pointer.getValue();    // 当前元素在该列表中的索引
//            Integer nextElemIndex = elemIndex + 1;     // 下一轮该列表的下一个元素索引
//
//            // 如果该列表还有剩余元素，则将其下一个元素指针加入队尾
//            if (nextElemIndex < this.vectors.get(vecIndex).size())
//                this.queue.addLast(new Pair<>(vecIndex, nextElemIndex));
//
//            // 返回当前元素的值
//            return this.vectors.get(vecIndex).get(elemIndex);
//        }
//
//        public boolean hasNext2() {
//            // 随时检查队列是否还有待处理的元素
//            return this.queue.size() > 0;
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 构造两个测试列表
        List<Integer> v1 = Arrays.asList(1, 3, 5);
        List<Integer> v2 = Arrays.asList(2, 4, 6, 8, 10);

        // 初始化 ZigzagIterator 实例
        ZigzagIterator zigzagIterator = new LeetCode_281_ZigzagIterator().new ZigzagIterator(v1, v2);

        // 持续调用 next() 并打印，直到没有更多元素
        System.out.println("Zigzag Output:");
        while (zigzagIterator.hasNext()) {
            System.out.print(zigzagIterator.next() + " ");
        }
        // 期望输出：1 2 3 4 5 6 8 10
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
 

 Related Topics Array Design Queue Iterator 👍 692 👎 41

*/