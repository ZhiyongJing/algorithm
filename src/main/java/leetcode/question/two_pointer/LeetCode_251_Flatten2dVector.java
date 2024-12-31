package leetcode.question.two_pointer;

import java.util.NoSuchElementException;

/**
 * @Question: 251. Flatten 2D Vector
 * @Difculty: 2 [1->Easy, 2->Medium, 3->Hard]
 * @Frequency: 49.46%
 * @Time Complexity: O()
 * @Space Complexity: O()
 */

/**
 * ### 题目概述
 *
 * 问题编号：**251. Flatten 2D Vector**
 * 难度等级：**中等**
 *
 * #### 问题描述
 *
 * 给定一个二维整数数组 `vector`，其中每个元素都是一个整数数组。需要实现一个迭代器 `Vector2D`，该迭代器能够逐步访问二维数组中的每个整数，类似于“扁平化”二维向量。实现的方法包括：
 *
 * - **`next()`**：返回当前整数并移动到下一个位置。
 * - **`hasNext()`**：判断是否存在下一个整数。
 *
 * 要求不能将二维数组直接展平成一维数组，而是要在二维数组中逐步遍历并返回每个元素。
 *
 * ### 解题思路
 *
 * 1. **维护两个指针**：
 *    - 使用 `row` 和 `col` 两个指针分别表示当前访问的外层数组索引和内层数组索引。
 *
 * 2. **跳过空数组**：
 *    - 因为可能会遇到空数组或者子数组已经遍历完的情况，因此需要一个辅助方法 `advanceToNext()`，在每次 `next` 或 `hasNext` 调用之前，将指针调整到下一个有效整数的位置。
 *    - `advanceToNext()` 方法会在 `col` 达到当前子数组末尾时，将 `row` 移动到下一个子数组，并将 `col` 重置为 `0`。这保证每次 `next()` 调用时，指针都指向一个有效的整数位置。
 *
 * 3. **方法实现**：
 *    - **`next()`**：返回当前整数，并将 `col` 移动到下一个位置。
 *    - **`hasNext()`**：在调用 `next()` 前使用 `advanceToNext()` 方法检查指针是否处于有效位置，如果 `row` 超过 `vector` 的长度，说明已经遍历完所有元素，返回 `false`。
 *
 * ### 时间复杂度分析
 *
 * - **`next()` 和 `hasNext()` 的时间复杂度**：每个整数仅访问一次，因此 `next()` 和 `hasNext()` 方法的**摊还时间复杂度**均为 \( O(1) \)，因为每次操作只需移动指针。
 *
 * - **`advanceToNext()` 的摊还时间复杂度**：虽然在 `advanceToNext()` 中会跳过空的子数组，但每个子数组（包括空数组）只访问一次。因此总的摊还时间复杂度为 \( O(1) \)。
 *
 * ### 空间复杂度分析
 *
 * 该实现直接使用二维数组 `vector` 的引用，不额外存储所有整数，因此空间复杂度为 \( O(1) \)（仅占用指针 `row` 和 `col` 所需的固定空间）。
 */
public class LeetCode_251_Flatten2dVector {

    //leetcode submit region begin(Prohibit modification and deletion)
//    import java.util.NoSuchElementException;

    class Vector2D {

        private int[][] vector; // 存储二维向量的引用
        private int col = 0;  // 当前内层列表的索引
        private int row = 0;  // 当前外层列表的索引
        private int current_next_row = 0;
        private int current_next_col = 0;
        private boolean canRemove = false; // 标记是否可以执行删除操作

        // 构造函数，接受二维数组作为输入
        public Vector2D(int[][] v) {
            vector = v; // 存储对输入二维向量的引用
        }

        // 将指针移动到下一个可用的整数位置
//        private void advanceToNext() {
//            // 当row在二维数组范围内且col已超出当前子数组长度时，移动到下一个子数组的起始位置
//            while (row < vector.length && col == vector[row].length) {
//                col = 0; // 重置内层索引
//                row++;   // 移动到下一个外层索引
//            }
//        }

        // 返回下一个整数
        public int next() {
            // 如果没有下一个整数，根据Java规范抛出异常
            if (!hasNext()) throw new NoSuchElementException();
            // 返回当前元素，并将col向后移动，以便指向下一个元素
            current_next_col = col;
            current_next_row = row;
            canRemove =  true;
            return vector[row][col++];
        }

        // 检查是否存在下一个整数
        public boolean hasNext() {
            // 确保指针移动到有效的整数位置，或者row已到达二维数组末尾
//            advanceToNext();
            // 当row在二维数组范围内且col已超出当前子数组长度时，移动到下一个子数组的起始位置
            while (row < vector.length && col == vector[row].length) {
                col = 0; // 重置内层索引
                row++;   // 移动到下一个外层索引
            }
            // 如果row等于vector.length则没有剩余整数，否则表示仍有可用整数
            return row < vector.length;
        }

        public void remove(){
            // 检查是否可以删除
            if (!canRemove) throw new IllegalStateException("Remove cannot be called before next()");

            // 从当前内层数组中删除最后返回的元素
            int[] colArray = vector[row];

            // 计算内层数组的新长度
            int newLength = colArray.length - 1;

            // 创建新的内层数组并填充
            int[] newInnerArray = new int[newLength];

            // 复制元素到新的内层数组
            for (int i = 0, j = 0; i < colArray.length; i++) {
                // 如果是被删除的元素，跳过
                if (i != col - 1) {
                    newInnerArray[j++] = colArray[i];
                }
            }

            // 将新内层数组赋值给当前外层数组
            vector[row] = newInnerArray;

            // 如果移除的是当前元素后面还有元素，col自减
            if (col > 0) {
                col--;
            }

            // 调整row和col索引，如果内层数组变空，row需要向后移动
            if (colArray.length == 1) {
                row++;
                col = 0; // 重置col
            }

            // 重置canRemove以防止连续删除
            canRemove = false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        // 测试用例
        int[][] vec = {{1, 2}, {3}, {4, 5, 6}};
        Vector2D vector2D = new LeetCode_251_Flatten2dVector().new Vector2D(vec);

        // 逐步输出所有元素
        while (vector2D.hasNext()) {
            System.out.println(vector2D.next() + " ");
        }
        // 输出：1 2 3 4 5 6

        // 测试用例
        int[][] vec2 = {{1, 2}, {5}};
        vector2D = new LeetCode_251_Flatten2dVector().new Vector2D(vec2);

        // 逐步输出所有元素
        System.out.println(vector2D.hasNext());
        System.out.println(vector2D.next());
        System.out.println(vector2D.hasNext());
        System.out.println(vector2D.next());
        vector2D.remove();
//        System.out.println(Arrays.deepToString(vector2D));
    }
}

/**
Design an iterator to flatten a 2D vector. It should support the next and 
hasNext operations. 

 Implement the Vector2D class: 

 
 Vector2D(int[][] vec) initializes the object with the 2D vector vec. 
 next() returns the next element from the 2D vector and moves the pointer one 
step forward. You may assume that all the calls to next are valid. 
 hasNext() returns true if there are still some elements in the vector, and 
false otherwise. 
 

 
 Example 1: 

 
Input
["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
[[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
Output
[null, 1, 2, 3, true, true, 4, false]

Explanation
Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
vector2D.next();    // return 1
vector2D.next();    // return 2
vector2D.next();    // return 3
vector2D.hasNext(); // return True
vector2D.hasNext(); // return True
vector2D.next();    // return 4
vector2D.hasNext(); // return False
 

 
 Constraints: 

 
 0 <= vec.length <= 200 
 0 <= vec[i].length <= 500 
 -500 <= vec[i][j] <= 500 
 At most 10⁵ calls will be made to next and hasNext. 
 

 
 Follow up: As an added challenge, try to code it using only iterators in C++ 
or iterators in Java. 

 Related Topics Array Two Pointers Design Iterator 👍 722 👎 405

*/