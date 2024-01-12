package leetcode.question.dfs;



/**
 * @Question:  17. 电话号码的字母组合
 * @Difculty:  2 [1->简单, 2->中等, 3->困难]
 * @Frequency: 83.34%
 * @Time  Complexity: O(4^n * n)
 * @Space Complexity: O(n)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 这是一个使用回溯算法解决的问题。主要思路是对输入的数字字符串进行遍历，对于每个数字，找出其对应的所有可能的字母组合，
 * 然后通过回溯算法逐步构建出所有可能的组合。
 *
 * 以下是算法的详细步骤：
 *
 * 1. **初始化：** 创建一个HashMap，将每个数字映射到其可能的字母集合。定义一个全局变量`combinations`，用于存储最终的所有字母组合。
 * 同时，定义全局变量`phoneDigits`保存输入的数字字符串。
 *
 * 2. **回溯函数：** 创建一个回溯函数`backtrack`，它接受两个参数，当前数字的索引`index`和当前已构建的部分字母组合`path`。
 * 在回溯函数中，递归地尝试每个数字对应的所有字母，并不断构建路径，直到达到数字字符串的长度。当路径长度与数字字符串相同时，
 * 表示找到一个完整的字母组合，将其加入`combinations`中。
 *
 * 3. **递归过程：** 在回溯过程中，对于当前数字，获取其对应的字母集合，然后遍历每个字母，将其添加到当前路径中，然后递归调用回溯函数。
 *
 * 4. **回溯：** 在递归的过程中，为了尝试其他可能的组合，需要进行回溯操作。回溯操作包括移除当前路径的最后一个字母，使得路径能够回到上一层，
 * 继续尝试其他字母。
 *
 * 5. **终止条件：** 当路径长度等于数字字符串的长度时，表示找到一个有效的字母组合，将其加入`combinations`。
 * 递归过程会继续进行，直到尝试完所有可能的组合。
 *
 * 6. **返回结果：** 最终返回`combinations`，即所有可能的字母组合。
 *
 * **时间复杂度：** 如果输入的数字字符串长度为n，最坏情况下，每个数字对应3个或4个字母，因此时间复杂度为O(4^n * n)。
 *
 * **空间复杂度：** 除了存储输入和输出的空间外，主要的额外空间消耗是递归调用时的栈空间，因为在递归过程中需要保存当前的路径。因此，空间复杂度为O(n)。
 */

public class LeetCode_17_LetterCombinationsOfAPhoneNumber {

    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        private List<String> combinations = new ArrayList<>();
        private Map<Long, String> letters = Stream.of(
                        new Object[]{'2', "abc"},
                        new Object[]{'3', "def"},
                        new Object[]{'4', "ghi"},
                        new Object[]{'5', "jkl"},
                        new Object[]{'6', "mno"},
                        new Object[]{'7', "pqrs"},
                        new Object[]{'8', "tuv"},
                        new Object[]{'9', "wxyz"})
                .collect(Collectors.toMap(arr -> (Long) arr[0], arr -> (String) arr[1]));
        private String phoneDigits;

        public List<String> letterCombinations(String digits) {
            // 如果输入为空，则立即返回空答案数组
            if (digits.length() == 0) {
                return combinations;
            }

            // 使用空路径和起始索引0启动回溯
            phoneDigits = digits;
            backtrack(0, new StringBuilder());
            return combinations;
        }

        private void backtrack(int index, StringBuilder path) {
            // 如果路径的长度与数字相同，我们有一个完整的组合
            if (path.length() == phoneDigits.length()) {
                combinations.add(path.toString());
                return; // 回溯
            }

            // 获取当前数字映射的字母，并循环遍历它们
            String possibleLetters = letters.get(phoneDigits.charAt(index));
            for (char letter : possibleLetters.toCharArray()) {
                // 将字母添加到当前路径
                path.append(letter);
                // 转到下一个数字
                backtrack(index + 1, path);
                // 回溯，通过在移动到下一个之前删除字母
                path.deleteCharAt(path.length() - 1);
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_17_LetterCombinationsOfAPhoneNumber().new Solution();
        // 测试
        List<String> result = solution.letterCombinations("23");
        System.out.println(result);
    }
}

/**
Given a string containing digits from 2-9 inclusive, return all possible letter 
combinations that the number could represent. Return the answer in any order. 

 A mapping of digits to letters (just like on the telephone buttons) is given 
below. Note that 1 does not map to any letters. 
 
 
 Example 1: 

 
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 

 Example 2: 

 
Input: digits = ""
Output: []
 

 Example 3: 

 
Input: digits = "2"
Output: ["a","b","c"]
 

 
 Constraints: 

 
 0 <= digits.length <= 4 
 digits[i] is a digit in the range ['2', '9']. 
 

 Related Topics Hash Table String Backtracking 👍 17624 👎 922

*/
