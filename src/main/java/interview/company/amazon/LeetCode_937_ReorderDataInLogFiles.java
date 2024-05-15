package interview.company.amazon;

import java.util.Arrays;
import java.util.Comparator;
/**
  *@Question:  937. Reorder Data in Log Files     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 4.14%      
  *@Time  Complexity: O(M*N*logN) Let N be the number of logs in the list and M be the maximum length of a single log.
  *@Space Complexity: O(M*logN)
 */

/**
 * 这道题目是要求重新排列日志文件，要求字母日志排在数字日志之前，并且字母日志按照特定的顺序进行排序。具体的排序规则是，字母日志先按照内容排序，如果内容相同，则按照标识符排序；数字日志保持原有顺序不变。
 *
 * 解题思路：
 * 1. 首先定义一个自定义比较器（`Comparator`），用于对日志进行排序。比较器中的 `compare` 方法实现了按照题目要求对日志进行排序的逻辑：
 *    - 首先将每个日志分成两部分：标识符和内容。
 *    - 如果两个日志都是字母日志，则先按照内容排序，如果内容相同，则按照标识符排序。
 *    - 如果一个日志是字母日志，另一个是数字日志，则字母日志排在前面。
 *    - 如果两个日志都是数字日志，则不改变它们的顺序。
 * 2. 排序完成后，返回排序后的日志数组即可。
 *
 * 时间复杂度分析：
 * - 排序算法的时间复杂度为 O(M * N * logN)，其中 N 是日志的数量，M 是日志中的最大长度。这是因为在比较器中，我们需要对每个日志进行拆分，并且调用了排序算法进行排序。
 * - 假设日志数量为 N，最大日志长度为 M，排序算法的时间复杂度为 O(N * logN * M)。
 *
 * 空间复杂度分析：
 * - 我们使用了一个自定义比较器来对日志进行排序，因此额外空间复杂度为 O(M * logN)，其中 M 是日志中的最大长度，N 是日志的数量。
 */

public class LeetCode_937_ReorderDataInLogFiles{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String[] reorderLogFiles(String[] logs) {
            // 自定义比较器，用于按照题目要求对日志进行排序
            Comparator<String> myComp = new Comparator<String>() {
                @Override
                public int compare(String log1, String log2) {
                    // 将每个日志分成两部分：标识符和内容
                    String[] split1 = log1.split(" ", 2);
                    String[] split2 = log2.split(" ", 2);

                    boolean isDigit1 = Character.isDigit(split1[1].charAt(0)); // 判断第一个日志的内容是否为数字
                    boolean isDigit2 = Character.isDigit(split2[1].charAt(0)); // 判断第二个日志的内容是否为数字

                    // 情况 1: 两个日志都是字母日志
                    if (!isDigit1 && !isDigit2) {
                        // 先比较内容
                        int cmp = split1[1].compareTo(split2[1]);
                        if (cmp != 0)
                            return cmp;
                        // 如果内容相同，再比较标识符
                        return split1[0].compareTo(split2[0]);
                    }

                    // 情况 2: 一个是字母日志，一个是数字日志
                    if (!isDigit1 && isDigit2)
                        // 字母日志应该排在数字日志之前
                        return -1;
                    else if (isDigit1 && !isDigit2)
                        return 1;
                    else
                        // 情况 3: 两个日志都是数字日志，不改变它们的顺序
                        return 0;
                }
            };

            // 使用自定义的比较器对日志进行排序
            Arrays.sort(logs, myComp);
            return logs;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_937_ReorderDataInLogFiles().new Solution();
        // 测试代码
        String[] logs = {
                "dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero"
        };
        String[] reorderedLogs = solution.reorderLogFiles(logs);
        for (String log : reorderedLogs) {
            System.out.println(log);
        }
    }
}

/**
You are given an array of logs. Each log is a space-delimited string of words, 
where the first word is the identifier. 

 There are two types of logs: 

 
 Letter-logs: All words (except the identifier) consist of lowercase English 
letters. 
 Digit-logs: All words (except the identifier) consist of digits. 
 

 Reorder these logs so that: 

 
 The letter-logs come before all digit-logs. 
 The letter-logs are sorted lexicographically by their contents. If their 
contents are the same, then sort them lexicographically by their identifiers. 
 The digit-logs maintain their relative ordering. 
 

 Return the final order of the logs. 

 
 Example 1: 

 
Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3
 art zero"]
Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 
3 6"]
Explanation:
The letter-log contents are all different, so their ordering is "art can", "art 
zero", "own kit dig".
The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
 

 Example 2: 

 
Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act 
zoo"]
Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
 

 
 Constraints: 

 
 1 <= logs.length <= 100 
 3 <= logs[i].length <= 100 
 All the tokens of logs[i] are separated by a single space. 
 logs[i] is guaranteed to have an identifier and at least one word after the 
identifier. 
 

 Related Topics Array String Sorting 👍 2077 👎 4376

*/