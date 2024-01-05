package leetcode.frequent.based_on_data_structure.map_set;

/**
  *@Question:  299. Bulls and Cows     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 26.44%      
  *@Time  Complexity: O(N)
  *@Space Complexity: O(1)
 */

import java.util.HashMap;

/**
 * ### 解题思路
 *
 * 这道题目要求猜数字游戏的提示字符串。给定一个秘密数字和一个猜测数字，如果数字和位置都正确，则称为“公牛”（bulls），
 * 如果数字正确但位置不正确，则称为“奶牛”（cows）。任务是返回一个字符串，格式为 "xAyB"，其中 x 表示公牛的数量，y 表示奶牛的数量。
 *
 *
 * 为了解决这个问题，可以遍历秘密数字和猜测数字，记录公牛的数量，并通过一个数组 `count` 记录每个数字出现的次数。
 * 遍历过程中，如果遇到相同数字并且位置相同，公牛数量增加；如果位置不同，则更新 `count` 数组，并在后面的遍历中，
 * 如果数字相同但位置不同，奶牛数量增加。
 *
 * ### 详细步骤
 *
 * 1. 初始化一个长度为 10 的数组 `count`，用于记录数字的出现次数。
 * 2. 初始化公牛和奶牛的数量。
 * 3. 遍历秘密数字和猜测数字：
 *    - 如果数字和位置都相同，公牛数量增加。
 *    - 如果位置不同，检查 `count` 数组：
 *      - 如果在之前的猜测中 `count[s - '0']` 为负值，说明秘密数字中有相同数字，奶牛数量增加。
 *      - 如果在之前的猜测中 `count[g - '0']` 为正值，说明猜测数字中有相同数字，奶牛数量增加。
 *      - 更新 `count` 数组。
 * 4. 构建并返回结果字符串。
 *
 * ### 复杂度分析
 *
 * - 时间复杂度：O(N)，其中 N 是字符串的长度。遍历秘密数字和猜测数字的过程是线性的。
 * - 空间复杂度：O(1)，因为 `count` 数组的长度是固定的（0到9），不随输入规模变化。
 *
 */

public class LeetCode_299_BullsAndCows {

    //leetcode submit region begin(Prohibit modification and deletion)
    //Solution 1: use hashmap
    class Solution {
            public String getHint(String secret, String guess) {
                HashMap<Character, Integer> h = new HashMap();
                for (char s : secret.toCharArray()) {
                    h.put(s, h.getOrDefault(s, 0) + 1);
                }

                int bulls = 0, cows = 0;
                int n = guess.length();
                for (int idx = 0; idx < n; ++idx) {
                    char ch = guess.charAt(idx);
                    if (h.containsKey(ch)) {
                        // corresponding characters match
                        if (ch == secret.charAt(idx)) {
                            // update the bulls
                            bulls++;
                            // update the cows
                            // if all ch characters from secret
                            // were used up
                            if (h.get(ch) <= 0)
                                cows--;
                            // corresponding characters don't match
                        } else {
                            // update the cows
                            if (h.get(ch) > 0)
                                cows++;
                        }
                        // ch character was used
                        h.put(ch, h.get(ch) - 1);
                    }
                }

                return Integer.toString(bulls) + "A" + Integer.toString(cows) + "B";
            }


        //Solution 2: use arraylist(基于solution1 的优化)
        /**
         * 获取提示字符串
         *
         * @param secret 神秘数字字符串
         * @param guess  猜测数字字符串
         * @return 提示字符串，格式为 "xAyB"，x 表示猜中的数字且位置正确的个数，y 表示猜中的数字但位置不正确的个数
         */
        public String getHint2(String secret, String guess) {
            int[] count = new int[10]; // 记录数字出现的次数
            int bulls = 0, cows = 0;
            int n = guess.length();

            for (int idx = 0; idx < n; ++idx) {
                char s = secret.charAt(idx);
                char g = guess.charAt(idx);

                if (s == g) {
                    bulls++; // 猜中数字且位置正确，bulls 自增
                } else {
                    // 位置不正确的情况
                    if (count[s - '0'] < 0) cows++; // secret 中当前数字在之前的猜测中有，cows 自增
                    if (count[g - '0'] > 0) cows++; // guess 中当前数字在之前的猜测中有，cows 自增
                    count[s - '0']++; // 记录 secret 中当前数字
                    count[g - '0']--; // 记录 guess 中当前数字
                }
            }

            // 构建结果字符串
            StringBuilder sb = new StringBuilder();
            sb.append(bulls).append("A").append(cows).append("B");
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_299_BullsAndCows().new Solution();

        // 测试用例1
        String secret1 = "1807";
        String guess1 = "7810";
        System.out.println("Test Case 1: " + solution.getHint(secret1, guess1)); // 预期输出: "1A3B"

        // 测试用例2
        String secret2 = "1123";
        String guess2 = "0111";
        System.out.println("Test Case 2: " + solution.getHint(secret2, guess2)); // 预期输出: "1A1B"
    }
}

/**
You are playing the Bulls and Cows game with your friend. 

 You write down a secret number and ask your friend to guess what the number is.
 When your friend makes a guess, you provide a hint with the following info: 

 
 The number of "bulls", which are digits in the guess that are in the correct 
position. 
 The number of "cows", which are digits in the guess that are in your secret 
number but are located in the wrong position. Specifically, the non-bull digits in 
the guess that could be rearranged such that they become bulls. 
 

 Given the secret number secret and your friend's guess guess, return the hint 
for your friend's guess. 

 The hint should be formatted as "xAyB", where x is the number of bulls and y 
is the number of cows. Note that both secret and guess may contain duplicate 
digits. 

 
 Example 1: 

 
Input: secret = "1807", guess = "7810"
Output: "1A3B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1807"
  |
"7810" 

 Example 2: 

 
Input: secret = "1123", guess = "0111"
Output: "1A1B"
Explanation: Bulls are connected with a '|' and cows are underlined:
"1123"        "1123"
  |      or     |
"0111"        "0111"
Note that only one of the two unmatched 1s is counted as a cow since the non-
bull digits can only be rearranged to allow one 1 to be a bull.
 

 
 Constraints: 

 
 1 <= secret.length, guess.length <= 1000 
 secret.length == guess.length 
 secret and guess consist of digits only. 
 

 Related Topics Hash Table String Counting 👍 2334 👎 1762

*/