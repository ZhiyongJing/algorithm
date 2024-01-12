package leetcode.question.union_find;

import java.util.*;

/**
  *@Question:  721. Accounts Merge
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 56.44%
  *@Time  Complexity: O(NK log{NK}) N is the number of accounts and K is the maximum length of an account.
  *@Space Complexity: O(O(NK))
 */

/**
 * ### 解题思路
 *
 * 这道题的目标是将具有相同邮箱的账户合并。可以通过使用深度优先搜索（DFS）和邻接表来解决。
 *
 * 1. **构建邻接表：** 遍历每个账户，将账户的第一个邮箱作为邻接表的键，其余邮箱作为与第一个邮箱相邻的邮箱列表。
 *
 * 2. **DFS遍历：** 遍历账户列表，对每个账户的第一个邮箱进行DFS遍历，将所有相连的邮箱添加到一个合并账户列表中。
 *
 * 3. **结果整理：** 对于每个合并账户列表，将账户名添加到列表的第一个位置，然后对邮箱进行排序。
 *
 * ### 时间复杂度
 *
 * - 构建邻接表的时间复杂度为O(N * E)，其中 N 为账户数量，E 为邮箱数量。
 * - DFS遍历的时间复杂度为O(N + E)，其中 N 为账户数量，E 为邮箱数量。
 * - 结果整理中的排序时间复杂度为O(E log E)。
 *
 * 综合起来，总的时间复杂度为O(N * E + E log E)。
 *
 * ### 空间复杂度
 *
 * - 使用了一个HashSet `visited` 和一个HashMap `adjacent`，它们的空间复杂度为O(N + E)，其中 N 为账户数量，E 为邮箱数量。
 * - 使用了一个合并账户列表，最坏情况下可能需要存储全部的邮箱，因此空间复杂度为O(N + E)。
 *
 * 综合起来，总的空间复杂度为O(N + E)。
 *
 * 该算法通过DFS和邻接表的结合使用，能够高效地合并相连的账户，时间和空间复杂度都是线性的。
 */

public class LeetCode_721_AccountsMerge {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        HashSet<String> visited = new HashSet<>();
        Map<String, List<String>> adjacent = new HashMap<String, List<String>>();

        // 深度优先搜索遍历邮箱，将所有相连的邮箱添加到 mergedAccount 中
        private void DFS(List<String> mergedAccount, String email) {
            visited.add(email);
            // 将包含当前组件的所有邮箱添加到 mergedAccount 中
            mergedAccount.add(email);

            if (!adjacent.containsKey(email)) {
                return;
            }

            for (String neighbor : adjacent.get(email)) {
                if (!visited.contains(neighbor)) {
                    DFS(mergedAccount, neighbor);
                }
            }
        }

        public List<List<String>> accountsMerge(List<List<String>> accountList) {
            int accountListSize = accountList.size();

            // 构建邻接表
            for (List<String> account : accountList) {
                int accountSize = account.size();

                // 添加第一个邮箱与其他邮箱之间的边
                String accountFirstEmail = account.get(1);
                for (int j = 2; j < accountSize; j++) {
                    String accountEmail = account.get(j);

                    if (!adjacent.containsKey(accountFirstEmail)) {
                        adjacent.put(accountFirstEmail, new ArrayList<String>());
                    }
                    adjacent.get(accountFirstEmail).add(accountEmail);

                    if (!adjacent.containsKey(accountEmail)) {
                        adjacent.put(accountEmail, new ArrayList<String>());
                    }
                    adjacent.get(accountEmail).add(accountFirstEmail);
                }
            }

            // 遍历所有账户，进行深度优先搜索
            List<List<String>> mergedAccounts = new ArrayList<>();
            for (List<String> account : accountList) {
                String accountName = account.get(0);
                String accountFirstEmail = account.get(1);

                // 如果邮箱已经访问过，说明它属于不同的组件
                // 只有未访问的邮箱才进行深度优先搜索
                if (!visited.contains(accountFirstEmail)) {
                    List<String> mergedAccount = new ArrayList<>();
                    // 将账户名添加到结果列表的第一个位置
                    mergedAccount.add(accountName);

                    DFS(mergedAccount, accountFirstEmail);
                    Collections.sort(mergedAccount.subList(1, mergedAccount.size()));
                    mergedAccounts.add(mergedAccount);
                }
            }

            return mergedAccounts;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_721_AccountsMerge().new Solution();

        // 测试用例
        List<List<String>> accountList = new ArrayList<>();
        accountList.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accountList.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accountList.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accountList.add(Arrays.asList("Mary", "mary@mail.com"));

        List<List<String>> result = solution.accountsMerge(accountList);
        for (List<String> mergedAccount : result) {
            System.out.println(mergedAccount);
        }
    }
}

/**
Given a list of accounts where each element accounts[i] is a list of strings, 
where the first element accounts[i][0] is a name, and the rest of the elements 
are emails representing emails of the account. 

 Now, we would like to merge these accounts. Two accounts definitely belong to 
the same person if there is some common email to both accounts. Note that even 
if two accounts have the same name, they may belong to different people as people 
could have the same name. A person can have any number of accounts initially, 
but all of their accounts definitely have the same name. 

 After merging the accounts, return the accounts in the following format: the 
first element of each account is the name, and the rest of the elements are 
emails in sorted order. The accounts themselves can be returned in any order. 

 
 Example 1: 

 
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],[
"John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John",
"johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"]
,["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email 
"johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses 
are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 
'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] 
would still be accepted.
 

 Example 2: 

 
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin",
"Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co",
"Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.
co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.
co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"]
,["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1
@m.co","Fern5@m.co"]]
 

 
 Constraints: 

 
 1 <= accounts.length <= 1000 
 2 <= accounts[i].length <= 10 
 1 <= accounts[i][j].length <= 30 
 accounts[i][0] consists of English letters. 
 accounts[i][j] (for j > 0) is a valid email. 
 

 Related Topics Array Hash Table String Depth-First Search Breadth-First Search 
Union Find Sorting 👍 6414 👎 1099

*/