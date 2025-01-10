package leetcode.question.two_pointer;

import java.util.ArrayList;
import java.util.List;

/**
 *@Question:  386. Lexicographical Numbers
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 83.5%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(log10N)
 */
/**
 * 题目描述：
 * 给定一个整数n，返回从1到n的所有整数的字典序排列。
 * 字典序是指按照字典顺序排列的数字顺序。例如，数字 1, 10, 11, 12... 是按照字典序排列的。
 *
 * 例如:
 * 输入: n = 13
 * 输出: [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]
 *
 * 问题要求生成从1到n的所有整数的字典序，并返回这些数字的列表。
 */

/**
 * 解题思路：
 *
 * 1. 我们从数字1开始，并尝试通过递归生成字典序的数字。字典序是以1为根，每个数字可以通过在其后面加上0到9的数字来继续扩展。
 *    - 比如，1可以扩展为10, 11, 12, ..., 19；然后是2, 20, 21, 22...。
 *
 * 2. 递归过程中，我们需要控制生成的数字不超过给定的n。
 *    - 如果当前生成的数字超过n，我们停止继续生成。
 *
 * 3. 初始时，我们从1开始，逐个尝试1到9作为根数字，然后利用递归生成这些根数字的字典序数字。
 *    - 例如，根数字1会生成[1, 10, 11, 12, ...]，根数字2会生成[2, 20, 21, 22, ...]。
 *
 * 4. 在每次递归中，我们尝试将当前数字乘以10，然后加上0到9中的每一个数字来扩展当前数字，直到超过n为止。
 *    - 例如，从数字1开始，我们生成[1, 10, 11, 12]，然后停止继续生成超过n的数字。
 *    - 当我们递归到10时，我们尝试生成10的后续数字，比如[10, 100, 101]，但由于限制n为13，递归在10之后停止。
 *
 * 举例：
 * 假设n=30
 * - 从数字1开始，依次生成 [1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19]。
 * - 然后从2开始生成 [2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]。
 * - 然后是数字3生成 [3, 30]。
 * - 然后是4到9，分别生成[4, 5, 6, 7, 8, 9]。
 *
 * 最终结果：[1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 3, 30, 4, 5, 6, 7, 8, 9]
 */

/**
 * 时间复杂度：
 * 假设n为输入的整数，时间复杂度主要受递归生成数字的过程影响。我们递归生成每个字典序数字，递归树的深度与数字位数有关，通常为log10(n)。
 * 在每一层递归中，最多生成10个数字，且每个数字都会调用递归，直到超出n。
 * 因此，生成的数字个数最多为n，因此时间复杂度是O(N)。
 *
 * 空间复杂度：
 * 空间复杂度主要受递归栈的深度和结果列表的大小影响。递归栈的深度是O(log10(N))，因为每次递归都会在当前数字后面加一个数字，直到超过n。
 * 结果列表存储了所有字典序数字，最多会有N个数字，因此空间复杂度为O(N)。
 */
/**
1
├── 10
├── 11
├── 12
├── 13
├── 14
├── 15
├── 16
├── 17
├── 18
├── 19
└── 2
└── 20
*/

public class LeetCode_386_LexicographicalNumbers{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {

        // 主函数，用于生成从1到n的字典序数字
        public List<Integer> lexicalOrder(int n) {
            // 创建一个列表用于存储结果
            List<Integer> lexicographicalNumbers = new ArrayList<>();

            // 从1到9开始生成字典序的数字
            for (int start = 1; start <= 9; ++start) {
                // 递归生成数字并加入结果列表
                generateLexicalNumbers(start, n, lexicographicalNumbers);
            }

            // 返回结果列表
            return lexicographicalNumbers;
        }

        // 递归函数生成字典序数字
        private void generateLexicalNumbers(
                int currentNumber,  // 当前生成的数字
                int limit,          // 上限限制
                List<Integer> result // 结果列表
        ) {
            // 如果当前数字大于上限，停止递归
            if (currentNumber > limit) return;

            // 将当前数字加入结果列表
            result.add(currentNumber);

            // 尝试将0到9的数字添加到当前数字上
            for (int nextDigit = 0; nextDigit <= 9; ++nextDigit) {
                int nextNumber = currentNumber * 10 + nextDigit;
                // 如果下一个数字小于等于上限，继续递归生成
                if (nextNumber <= limit) {
                    generateLexicalNumbers(nextNumber, limit, result);
                } else {
                    // 如果下一个数字超过上限，停止循环
                    break;
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    // 测试函数
    public static void main(String[] args) {
        // 创建Solution对象
        Solution solution = new LeetCode_386_LexicographicalNumbers().new Solution();

        // 测试样例，生成从1到30的字典序数字
        List<Integer> result = solution.lexicalOrder(30);

        // 输出结果
        System.out.println(result);  // 预期输出: [1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 3, 30, 4, 5, 6, 7, 8, 9]
    }
}

/**
Given an integer n, return all the numbers in the range [1, n] sorted in 
lexicographical order. 

 You must write an algorithm that runs in O(n) time and uses O(1) extra space. 

 
 Example 1: 
 Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 
 Example 2: 
 Input: n = 2
Output: [1,2]
 
 
 Constraints: 

 
 1 <= n <= 5 * 10⁴ 
 

 Related Topics Depth-First Search Trie 👍 2691 👎 188

*/