package leetcode.question.dfs;

/**
 *@Question:  341. Flatten Nested List Iterator
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 64.22%
 *@Time  Complexity: O(n) // 线性时间复杂度，n为所有整数的总数
 *@Space Complexity: O(n) // 线性空间复杂度，用于存储扁平化后的整数
 */

public class LeetCode_341_FlattenNestedListIterator {

//leetcode submit region begin(Prohibit modification and deletion)
    /**
     * // This is the interface that allows for creating nested lists.
     * // You should not implement it, or speculate about its implementation
     * public interface NestedInteger {
     *
     *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     *     public boolean isInteger();
     *
     *     // @return the single integer that this NestedInteger holds, if it holds a single integer
     *     // Return null if this NestedInteger holds a nested list
     *     public Integer getInteger();
     *
     *     // @return the nested list that this NestedInteger holds, if it holds a nested list
     *     // Return empty list if this NestedInteger holds a single integer
     *     public List<NestedInteger> getList();
     * }
     */

//    // 嵌套迭代器类，实现了迭代器接口
//    import java.util.NoSuchElementException;
//    public class NestedIterator implements Iterator<Integer> {
//
//        private List<Integer> integers = new ArrayList<Integer>(); // 存储扁平化后的整数
//        private int position = 0; // 指向下一个要返回的整数的位置
//
//        // 构造函数，接收一个嵌套整数列表
//        public NestedIterator(List<NestedInteger> nestedList) {
//            flattenList(nestedList); // 扁平化嵌套列表
//        }
//
//        // 递归地以深度优先顺序解包嵌套列表
//        private void flattenList(List<NestedInteger> nestedList) {
//            for (NestedInteger nestedInteger : nestedList) { // 遍历每个嵌套整数
//                if (nestedInteger.isInteger()) { // 如果是单个整数
//                    integers.add(nestedInteger.getInteger()); // 添加到整数列表
//                } else {
//                    flattenList(nestedInteger.getList()); // 如果是嵌套列表，递归调用
//                }
//            }
//        }
//
//        @Override
//        public Integer next() {
//            // 根据Java规范，如果没有更多整数，应抛出异常
//            if (!hasNext()) {
//                throw new NoSuchElementException(); // 如果没有更多元素，抛出异常
//            }
//            // 返回当前指针位置的整数，并在返回后增加指针
//            return integers.get(position++);
//        }
//
//        @Override
//        public boolean hasNext() {
//            return position < integers.size(); // 判断是否还有下一个整数
//        }
//    }
//
//    /**
//     * Your NestedIterator object will be instantiated and called as such:
//     * NestedIterator i = new NestedIterator(nestedList);
//     * while (i.hasNext()) v[f()] = i.next();
//     */
////leetcode submit region end(Prohibit modification and deletion)
//
//
//    public static void main(String[] args) {
//        // 创建测试用的嵌套整数列表
//        List<NestedInteger> nestedList = new ArrayList<>();
//
//        // 使用匿名内部类来创建嵌套整数列表（实现 NestedInteger 接口）
//        nestedList.add(new NestedInteger() { // 第一个元素是一个整数
//            public boolean isInteger() { return true; }
//            public Integer getInteger() { return 1; }
//            public List<NestedInteger> getList() { return new ArrayList<>(); }
//        });
//
//        nestedList.add(new NestedInteger() { // 第二个元素是一个嵌套列表
//            public boolean isInteger() { return false; }
//            public Integer getInteger() { return null; }
//            public List<NestedInteger> getList() {
//                List<NestedInteger> innerList = new ArrayList<>();
//                innerList.add(new NestedInteger() { // 添加嵌套列表中的元素
//                    public boolean isInteger() { return true; }
//                    public Integer getInteger() { return 2; }
//                    public List<NestedInteger> getList() { return new ArrayList<>(); }
//                });
//                innerList.add(new NestedInteger() { // 另一个嵌套列表中的元素
//                    public boolean isInteger() { return true; }
//                    public Integer getInteger() { return 3; }
//                    public List<NestedInteger> getList() { return new ArrayList<>(); }
//                });
//                return innerList;
//            }
//        });
//
//        // 实例化 NestedIterator 并遍历输出所有整数
//        NestedIterator iterator = new LeetCode_341_FlattenNestedListIterator().new NestedIterator(nestedList);
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next()); // 打印每个整数
//        }
//    }
}

/**
You are given a nested list of integers nestedList. Each element is either an 
integer or a list whose elements may also be integers or other lists. Implement 
an iterator to flatten it. 

 Implement the NestedIterator class: 

 
 NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with 
the nested list nestedList. 
 int next() Returns the next integer in the nested list. 
 boolean hasNext() Returns true if there are still some integers in the nested 
list and false otherwise. 
 

 Your code will be tested with the following pseudocode: 

 
initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
 

 If res matches the expected flattened list, then your code will be judged as 
correct. 

 
 Example 1: 

 
Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order 
of elements returned by next should be: [1,1,2,1,1].
 

 Example 2: 

 
Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order 
of elements returned by next should be: [1,4,6].
 

 
 Constraints: 

 
 1 <= nestedList.length <= 500 
 The values of the integers in the nested list is in the range [-10⁶, 10⁶]. 
 

 Related Topics Stack Tree Depth-First Search Design Queue Iterator 👍 4791 👎 1
712

*/
