package leetcode.question.map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@Question:  1169. Invalid Transactions
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 42.56%
 *@Time  Complexity: O(N^2)
 *@Space Complexity: O(N)
 */

/**
 * 第一部分：题目描述
 *
 * 题目：1169. 无效交易（Invalid Transactions）
 *
 * 题目描述：
 * 1. 给定一组交易记录，每条交易的格式如下：
 *    `"name,time,amount,city"`
 * 2. 交易记录是字符串数组，每个字符串由 **逗号** `,` 分隔的四个部分组成：
 *    - `name`：交易人的名字（小写字母）
 *    - `time`：交易时间（单位：分钟）
 *    - `amount`：交易金额（整数）
 *    - `city`：交易发生的城市（小写字母）
 * 3. **无效交易的定义**：
 *    - 交易金额超过 `1000`，视为无效交易。
 *    - 同一交易人 **在 60 分钟内**，**在不同城市** 进行的交易，视为无效交易。
 * 4. 返回所有无效交易的列表（顺序与输入相同）。
 *
 * **示例**
 * ```
 * 输入: ["alice,20,800,mtv","bob,50,1200,mtv"]
 * 输出: ["bob,50,1200,mtv"]
 *
 * 输入: ["alice,20,800,mtv","alice,50,100,beijing"]
 * 输出: ["alice,20,800,mtv", "alice,50,100,beijing"]
 * ```
 *
 * **要求**
 * - **时间复杂度尽可能优化**
 * - **保证交易顺序**
 */

/**
 * 第二部分：解题思路（基于代码的超级详细解析）
 *
 * **代码核心思路**
 * 1. **使用一个 `Transaction` 类封装交易数据**
 *    - 将 `name, time, amount, city` 存入一个对象中，方便操作。
 * 2. **遍历交易数据，存储到 `HashMap<String, List<Transaction>>`**
 *    - 使用 `name` 作为 `key`，存储该交易人的所有交易列表。
 * 3. **再次遍历交易数据，判断是否无效**
 *    - 交易金额超过 `1000`，直接标记为无效。
 *    - 在 **60 分钟内**，**不同城市** 的交易，也标记为无效。
 * 4. **返回无效交易列表**
 *
 * ---------------------------------------
 * **第一步：将交易数据转换为对象，存入 `map`**
 * ---------------------------------------
 * **思路**
 * 1. 遍历 `transactions`，使用 `Transaction` 类解析交易信息。
 * 2. 使用 `map.putIfAbsent(name, new ArrayList<>())` 存储该交易人所有的交易记录。
 * 3. 目的是方便后续在 `isValid()` 方法中进行 **同名交易检查**。
 *
 * **示例解析**
 * ```
 * transactions = ["alice,20,800,mtv", "alice,50,100,beijing"]
 *
 * map 结构：
 * {
 *   "alice" -> [
 *       Transaction("alice", 20, 800, "mtv"),
 *       Transaction("alice", 50, 100, "beijing")
 *   ]
 * }
 * ```
 *
 * ---------------------------------------
 * **第二步：遍历交易数据，判断是否无效**
 * ---------------------------------------
 * **思路**
 * 1. 交易金额 **超过 1000**，直接无效。
 * 2. 遍历该交易人的所有交易：
 *    - **时间差 ≤ 60 分钟** 且 **城市不同**，则无效。
 * 3. 记录所有无效交易，并返回结果。
 *
 * **示例解析**
 * ```
 * 输入: ["alice,20,800,mtv", "alice,50,100,beijing"]
 *
 * 遍历：
 * - 交易 "alice,20,800,mtv"
 *   - 在 `map` 中找到 `alice` 的交易列表：
 *     - 交易 "alice,50,100,beijing"
 *       - 时间差 `50 - 20 = 30`（≤60）
 *       - **城市不同**（"mtv" ≠ "beijing"）
 *       - **该交易无效**
 *
 * 输出: ["alice,20,800,mtv", "alice,50,100,beijing"]
 * ```
 *
 * **核心优化**
 * - **使用 `HashMap` 存储交易列表，避免重复遍历整个数组**
 * - **遍历该交易人已有的交易列表，而非所有交易**
 * - **保证时间复杂度尽可能低**
 */

/**
 * 第三部分：时间和空间复杂度分析
 *
 * **时间复杂度**
 * - **第一遍遍历交易数据，构建 `map`**
 *   - 需要遍历 `N` 条交易：`O(N)`
 * - **第二遍遍历交易数据，检查无效交易**
 *   - 每个交易在 `O(N)` 内检查交易人名下的交易
 *   - **最坏情况下**（所有交易人相同），时间复杂度 **O(N²)**
 * - **总体时间复杂度：O(N²)**
 *
 * **空间复杂度**
 * - **`map` 存储所有交易数据**：`O(N)`
 * - **`invalid` 列表存储无效交易**：最坏情况 `O(N)`
 * - **总体空间复杂度：O(N)**
 */

public class LeetCode_1169_InvalidTransactions{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义一个 `Transaction` 交易对象类，以便更容易管理和处理交易数据
        class Transaction{
            String name;  // 交易人姓名
            int time;     // 交易时间（单位：分钟）
            int amount;   // 交易金额
            String city;  // 交易发生的城市

            public Transaction(String line){
                // 解析交易字符串，格式为 "name,time,amount,city"
                String[] split = line.split(",");
                name = split[0];               // 提取交易人姓名
                time = Integer.parseInt(split[1]); // 提取交易时间
                amount = Integer.parseInt(split[2]); // 提取交易金额
                city = split[3];               // 提取交易城市
            }
        }

        public List<String> invalidTransactions(String[] transactions) {
            List<String> invalid = new ArrayList<>(); // 用于存储无效交易
            Map<String, List<Transaction>> map = new HashMap<>(); // 存储每个人的交易记录

            // **第一遍遍历**: 构建 `map`，以交易人的 `name` 作为 key，存储其所有交易记录
            for(String transaction : transactions){
                Transaction t = new Transaction(transaction); // 解析交易字符串
                map.putIfAbsent(t.name, new ArrayList<>()); // 若 `map` 中不存在该交易人，则初始化
                map.get(t.name).add(t); // 将交易对象添加到该交易人的交易列表中
            }

            // **第二遍遍历**: 判断哪些交易是无效的
            for(String transaction : transactions){
                Transaction t = new Transaction(transaction); // 解析当前交易
                // 如果交易无效，则添加到 `invalid` 结果集中
                // 只需要检查该交易人的交易列表
                if(!isValid(t, map.getOrDefault(t.name, new ArrayList<>()))){
                    invalid.add(transaction);
                }
            }

            return invalid; // 返回所有无效交易
        }

        public boolean isValid(Transaction t, List<Transaction> list){
            // **检查第一种无效交易条件**：如果交易金额超过 1000，则视为无效交易
            if(t.amount > 1000){
                return false;
            }

            // **检查第二种无效交易条件**：
            // 交易时间差小于等于 60 分钟，且交易发生在不同的城市
            for(Transaction ta : list){
                if(Math.abs(ta.time - t.time) <= 60 && !ta.city.equals(t.city)){
                    return false; // 发现无效交易
                }
            }

            // 如果以上两种无效交易条件都不满足，则交易是有效的
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1169_InvalidTransactions().new Solution();

        // 测试用例 1：存在无效交易（交易金额超过 1000）
        String[] transactions1 = {"alice,20,800,mtv","bob,50,1200,mtv"};
        List<String> invalid1 = solution.invalidTransactions(transactions1);
        System.out.println("Invalid Transactions (Case 1): " + invalid1);
        // 预期输出: ["bob,50,1200,mtv"] （Bob 交易金额超过 1000）

        // 测试用例 2：存在无效交易（同一人 60 分钟内在不同城市交易）
        String[] transactions2 = {"alice,20,800,mtv","alice,50,100,beijing"};
        List<String> invalid2 = solution.invalidTransactions(transactions2);
        System.out.println("Invalid Transactions (Case 2): " + invalid2);
        // 预期输出: ["alice,20,800,mtv", "alice,50,100,beijing"] （Alice 在不同城市内 60 分钟内交易）

        // 测试用例 3：所有交易均有效
        String[] transactions3 = {"alice,20,800,mtv","bob,200,500,mtv"};
        List<String> invalid3 = solution.invalidTransactions(transactions3);
        System.out.println("Invalid Transactions (Case 3): " + invalid3);
        // 预期输出: [] （所有交易均有效）
    }
}


/**
A transaction is possibly invalid if: 

 
 the amount exceeds $1000, or; 
 if it occurs within (and including) 60 minutes of another transaction with the 
same name in a different city. 
 

 You are given an array of strings transaction where transactions[i] consists 
of comma-separated values representing the name, time (in minutes), amount, and 
city of the transaction. 

 Return a list of transactions that are possibly invalid. You may return the 
answer in any order. 

 
 Example 1: 

 
Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
Explanation: The first transaction is invalid because the second transaction 
occurs within a difference of 60 minutes, have the same name and is in a different 
city. Similarly the second one is invalid too. 

 Example 2: 

 
Input: transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
Output: ["alice,50,1200,mtv"]
 

 Example 3: 

 
Input: transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
Output: ["bob,50,1200,mtv"]
 

 
 Constraints: 

 
 transactions.length <= 1000 
 Each transactions[i] takes the form "{name},{time},{amount},{city}" 
 Each {name} and {city} consist of lowercase English letters, and have lengths 
between 1 and 10. 
 Each {time} consist of digits, and represent an integer between 0 and 1000. 
 Each {amount} consist of digits, and represent an integer between 0 and 2000. 
 

 Related Topics Array Hash Table String Sorting 👍 540 👎 2311

*/