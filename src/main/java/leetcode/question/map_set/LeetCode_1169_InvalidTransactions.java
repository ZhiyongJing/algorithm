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
 * 这个问题的解决方案比较直观：
 *
 * 1. 首先，我们需要定义一个用于存储交易信息的类。这个类包含交易的姓名、时间、金额和城市信息。
 *
 * 2. 然后，我们遍历输入的交易数组，将每个交易信息解析为一个对象，并根据交易姓名将这些对象存储在一个 Map 中。Map 的键是交易姓名，值是一个列表，存储了该姓名对应的所有交易对象。
 *
 * 3. 接下来，我们再次遍历交易数组。对于每个交易，我们检查其是否有效。一个交易被认为是无效的情况包括：
 *    - 交易金额超过1000。
 *    - 与同一姓名的其他交易时间在不到60分钟内，但发生在不同的城市。
 *
 * 4. 如果交易被判定为无效，则将其添加到结果列表中。
 *
 * 时间复杂度分析：假设交易数量为 N，对于每个交易，我们需要线性时间来解析其信息，并且在检查其有效性时需要线性时间来遍历同名交易列表。因此，总体时间复杂度为 O(N^2)。
 *
 * 空间复杂度分析：我们需要使用一个 Map 来存储交易信息，其空间复杂度为 O(N)，其中 N 是交易数量。
 */
public class LeetCode_1169_InvalidTransactions{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 将交易存储在对象中，以便更容易处理和更清晰的代码
        class Transaction{
            String name;
            int time;
            int amount;
            String city;
            public Transaction(String line){
                // 拆分字符串并解析它
                String[] split = line.split(",");
                name = split[0];
                time = Integer.parseInt(split[1]);
                amount = Integer.parseInt(split[2]);
                city = split[3];
            }
        }
        public List<String> invalidTransactions(String[] transactions) {
            List<String> invalid = new ArrayList<>();
            Map<String, List<Transaction>> map = new HashMap<>();
            // 遍历整个交易列表，创建对象并存储在以'name'为键的映射中
            for(String transaction : transactions){
                Transaction t = new Transaction(transaction);
                map.putIfAbsent(t.name, new ArrayList<>());
                map.get(t.name).add(t);
            }
            // 现在再次遍历交易列表，比较每个交易和该名称对应的列表
            for(String transaction : transactions){
                Transaction t = new Transaction(transaction);
                // 如果交易无效，则添加到最终结果中
                // （注意我们只向函数传递具有相同名称的特定交易列表
                // 我们只关心相同名称的交易）
                if(!isValid(t, map.getOrDefault(t.name, new ArrayList<>()))){
                    invalid.add(transaction);
                }
            }
            return invalid;
        }
        public boolean isValid(Transaction t, List<Transaction> list){
            // 如果交易金额大于1000，
            // 我们满足了描述中的第一个条件，将交易返回为false
            if(t.amount > 1000){
                return false;
            }
            // 遍历该名称的列表
            // 查看交易时间是否小于或等于60，
            // 并且交易不在同一城市
            // 我们满足了第二个条件，将交易返回为false
            for(Transaction ta : list){
                if(Math.abs(ta.time - t.time) <= 60 && !ta.city.equals(t.city)){
                    return false;
                }
            }
            // 如果我们遍历整个列表，没有满足任何条件，那么交易为true
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1169_InvalidTransactions().new Solution();
        // TO TEST
        String[] transactions = {"alice,20,800,mtv","bob,50,1200,mtv"};
        List<String> invalid = solution.invalidTransactions(transactions);
        System.out.println("Invalid Transactions: " + invalid);
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