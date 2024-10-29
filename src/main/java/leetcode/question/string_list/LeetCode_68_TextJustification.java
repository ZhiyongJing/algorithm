//package leetcode.question.string_list;
//import java.util.*;
//
///**
// *@Question:  68. Text Justification
// *@Difficulty:  3 [1->Easy, 2->Medium, 3->Hard]
// *@Frequency: 83.98%
// *@Time Complexity: O(N * K) // n is words length, k is average length of a word
// *@Space Complexity: O(M) // M is maxwidth
// */
//
///**
// * 题目 **LeetCode 68: Text Justification（文本对齐）** 的核心要求是将一组单词按照指定宽度（`maxWidth`）进行左右对齐，每行字符数不超过 `maxWidth`。每个单词之间的间隔空格数必须调整，以达到指定宽度的要求。
// *
// * ### 题目描述
// * 给定一个字符串数组 `words`（每个元素为一个单词），以及一个整数 `maxWidth` 表示每行字符的最大宽度。需要将 `words` 中的单词排列成多行，使每行的宽度都等于 `maxWidth`。具体要求如下：
// * 1. 每行至少一个单词，单词之间需要填充空格。
// * 2. 如果一行仅包含一个单词，左对齐该单词，剩余部分用空格填充。
// * 3. 如果是最后一行，则所有单词左对齐，右边用空格填充。
// * 4. 非最后一行的空格应尽量平均分配，无法均分的空格则分配到左侧的间隔。
// *
// * ### 解题思路
// * 解决此问题可以分为以下几步：
// *
// * #### 1. 分行处理
// * 首先，我们需要遍历 `words` 数组，将能够放入一行且总宽度小于 `maxWidth` 的单词收集起来。每次累积单词直到长度超出 `maxWidth`，将这些单词作为一行加入结果集，随后处理下一行。
// *
// * #### 2. 处理单行对齐
// * 每行的单词可以分为两种情况：
// * - **普通行**：除了最后一行之外的行，需要做到左右对齐。具体来说，将多余的空格均匀分配在单词间，如果空格数无法均分，则从左侧开始逐一分配剩余空格。
// * - **最后一行**：左对齐所有单词，并在右侧填充空格至 `maxWidth`。
// *
// * #### 3. 空格分配
// * 为了对齐，我们可以计算出一行的基础长度（所有单词加上单词间最少的一个空格）。然后根据 `maxWidth` 计算出额外需要的空格数，并将这些空格平均分配到每个间隔，剩余空格从左侧间隔开始逐个分配。
// *
// * #### 4. 特殊情况处理
// * - 如果当前行只有一个单词，直接左对齐该单词，右侧填充空格至 `maxWidth`。
// * - 如果是最后一行，直接左对齐并填充右侧空格至 `maxWidth`。
// *
// * ### 时间复杂度
// * 1. **分行处理**：遍历 `words` 数组，复杂度为 `O(n)`，其中 `n` 是单词数量。
// * 2. **每行对齐操作**：每行的单词平均数量可以近似为常数 `k`，所以在单行中重新分配空格的复杂度也可以视作 `O(k)`。
// *
// * 因此，整个算法的时间复杂度近似为 `O(n)`。
// *
// * ### 空间复杂度
// * - 结果存储在 `List<String>` 中，因此空间复杂度为 `O(L)`，其中 `L` 是所有字符数的总和（包括空格）。
// */
//
//public class LeetCode_68_TextJustification {
//
//    // leetcode submit region begin(Prohibit modification and deletion)
//    class Solution {
//        /**
//         * 完全对齐给定单词数组，使每行字符数等于 maxWidth
//         * @param words 单词数组
//         * @param maxWidth 每行的最大宽度
//         * @return 返回调整对齐后的文本列表
//         */
//        public List<String> fullJustify(String[] words, int maxWidth) {
//            List<String> ans = new ArrayList<>(); // 存储最终结果
//            int i = 0; // 当前处理的单词索引
//
//            // 主循环，直到处理完所有单词
//            while (i < words.length) {
//                // 获取当前行能容纳的单词列表
//                List<String> currentLine = getWords(i, words, maxWidth);
//
//                // 更新索引，跳过当前行的单词
//                i += currentLine.size();
//
//                // 创建并添加当前行的完全对齐字符串
//                ans.add(createLine(currentLine, i, words, maxWidth));
//            }
//
//            return ans;
//        }
//
//        /**
//         * 获取当前行可以容纳的单词
//         * @param i 当前单词索引
//         * @param words 单词数组
//         * @param maxWidth 每行的最大宽度
//         * @return 返回当前行的单词列表
//         */
//        private List<String> getWords(int i, String[] words, int maxWidth) {
//            List<String> currentLine = new ArrayList<>(); // 当前行的单词列表
//            int currLength = 0; // 当前行的字符总长度
//
//            // 循环添加单词，直到超出 maxWidth
//            while (i < words.length && currLength + words[i].length() <= maxWidth) {
//                currentLine.add(words[i]); // 将单词加入当前行
//                currLength += words[i].length() + 1; // 更新当前行长度，+1 用于空格
//                i++;
//            }
//
//            return currentLine;
//        }
//
//        /**
//         * 将当前行单词列表调整为指定宽度的对齐字符串
//         * @param line 当前行的单词列表
//         * @param i 当前单词索引
//         * @param words 单词数组
//         * @param maxWidth 每行的最大宽度
//         * @return 返回对齐后的行字符串
//         */
//        private String createLine(List<String> line, int i, String[] words, int maxWidth) {
//            int baseLength = -1; // 当前行的基本长度（单词总长度加空格数），初始为-1便于计算第一个空格
//            for (String word : line) {
//                baseLength += word.length() + 1; // 更新行长度
//            }
//
//            int extraSpaces = maxWidth - baseLength; // 计算额外空格数以达到 maxWidth
//
//            // 如果当前行只有一个单词或已处理到最后一行，左对齐
//            if (line.size() == 1 || i == words.length) {
//                return String.join(" ", line) + " ".repeat(extraSpaces); // 左对齐后填充空格
//            }
//
//            int wordCount = line.size() - 1; // 间隔数，即空格应填充的位置
//            int spacesPerWord = extraSpaces / wordCount; // 每个间隔的空格数
//            int needsExtraSpace = extraSpaces % wordCount; // 剩余的空格数，用于分配到左侧
//
//            // 分配额外空格到左侧的若干单词
//            for (int j = 0; j < needsExtraSpace; j++) {
//                line.set(j, line.get(j) + " ");
//            }
//
//            // 每个间隔填充基本空格数
//            for (int j = 0; j < wordCount; j++) {
//                line.set(j, line.get(j) + " ".repeat(spacesPerWord));
//            }
//
//            return String.join(" ", line); // 返回当前行完全对齐的字符串
//        }
//    }
//    // leetcode submit region end(Prohibit modification and deletion)
//
//    public static void main(String[] args) {
//        Solution solution = new LeetCode_68_TextJustification().new Solution();
//
//        // 测试样例
//        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        int maxWidth = 16;
//
//        // 调用 fullJustify 方法并输出结果
//        List<String> result = solution.fullJustify(words, maxWidth);
//
//        // 打印每一行的对齐结果
//        for (String line : result) {
//            System.out.println("\"" + line + "\"");
//        }
//    }
//}
//
///**
//Given an array of strings words and a width maxWidth, format the text such that
//each line has exactly maxWidth characters and is fully (left and right)
//justified.
//
// You should pack your words in a greedy approach; that is, pack as many words
//as you can in each line. Pad extra spaces ' ' when necessary so that each line
//has exactly maxWidth characters.
//
// Extra spaces between words should be distributed as evenly as possible. If the
//number of spaces on a line does not divide evenly between words, the empty
//slots on the left will be assigned more spaces than the slots on the right.
//
// For the last line of text, it should be left-justified, and no extra space is
//inserted between words.
//
// Note:
//
//
// A word is defined as a character sequence consisting of non-space characters
//only.
// Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//
// The input array words contains at least one word.
//
//
//
// Example 1:
//
//
//Input: words = ["This", "is", "an", "example", "of", "text", "justification."],
//maxWidth = 16
//Output:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//
// Example 2:
//
//
//Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//
//Output:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//Explanation: Note that the last line is "shall be    " instead of "shall
//be", because the last line must be left-justified instead of fully-justified.
//Note that the second line is also left-justified because it contains only one
//word.
//
// Example 3:
//
//
//Input: words = ["Science","is","what","we","understand","well","enough","to",
//"explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth
//= 20
//Output:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//
// Constraints:
//
//
// 1 <= words.length <= 300
// 1 <= words[i].length <= 20
// words[i] consists of only English letters and symbols.
// 1 <= maxWidth <= 100
// words[i].length <= maxWidth
//
//
// Related Topics Array String Simulation 👍 3844 👎 4877
//
//*/