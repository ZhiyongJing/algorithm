package leetcode.frequent.dfs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
  *@Question:  341. Flatten Nested List Iterator     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 64.22%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_341_FlattenNestedListIterator{
    
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


//    public class NestedIterator implements Iterator<Integer> {
//
//        private List<Integer> integers = new ArrayList<Integer>();
//        private int position = 0; // Pointer to next integer to return.
//
//        public NestedIterator(List<NestedInteger> nestedList) {
//            flattenList(nestedList);
//        }
//
//        // Recursively unpacks a nested list in DFS order.
//        private void flattenList(List<NestedInteger> nestedList) {
//            for (NestedInteger nestedInteger : nestedList) {
//                if (nestedInteger.isInteger()) {
//                    integers.add(nestedInteger.getInteger());
//                } else {
//                    flattenList(nestedInteger.getList());
//                }
//            }
//        }
//
//        @Override
//        public Integer next() {
//            // As per Java specs, we should throw an exception if no more ints.
//            if (!hasNext()){throw new NoSuchElementException();}
//            // Return int at current position, and then *after*, increment position.
//            return integers.get(position++);
//        }
//
//        @Override
//        public boolean hasNext() {
//            return position < integers.size();
//        }
//    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
//        Solution solution = new LeetCode_341_FlattenNestedListIterator().new Solution();
        // TO TEST
        //solution.
    }
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
