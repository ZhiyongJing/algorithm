package interview.company.amazon;

/**
  *@Question:  1152. Analyze User Website Visit Pattern     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 69.54%      
  *@Time  Complexity: O(N^3)
  *@Space Complexity: O()
 */

/**
 * 这段代码解决了LeetCode问题1152：“分析用户网站访问模式”。
 *
 * ### 解题思路：
 *
 * 1. **构建数据结构：**
 *    - 首先，定义了一个 `Pair` 类来表示用户访问记录，包含时间和网站名称两个属性。
 *    - 然后，使用一个 `HashMap` 构建一个映射，将每个用户的访问记录按用户名进行分组存储。
 *
 * 2. **暴力搜索：**
 *    - 对于每个用户的访问记录列表，对时间进行排序，然后使用三重循环来遍历所有可能的三个网站序列。
 *    - 在内层循环中，构建一个字符串表示当前的三个网站序列，并使用一个 `HashSet` 来确保在同一个用户的访问记录中不重复计算相同的三个网站序列。
 *    - 对于每个不重复的三个网站序列，使用一个 `HashMap` 来记录其出现的次数。
 *
 * 3. **找到出现次数最多的网站序列：**
 *    - 在遍历过程中，始终维护一个字符串 `res`，用于记录出现次数最多的网站序列。
 *    - 如果新的网站序列出现次数更多，或者出现次数相同但字典序更小，则更新 `res`。
 *
 * 4. **解析结果：**
 *    - 将最终的结果字符串 `res` 按空格分割，并将结果存储在一个列表中返回。
 *
 * ### 时间复杂度分析：
 * - 构建映射：将用户访问记录按用户名分组存储，时间复杂度为 O(N)，其中 N 是访问记录的数量。
 * - 暴力搜索：遍历所有可能的三个网站序列，时间复杂度为 O(N^3)，其中 N 是访问记录的数量。
 * - 找到出现次数最多的网站序列：遍历 `HashMap` 查找最大值，时间复杂度为 O(N)。
 * 综合来看，总的时间复杂度为 O(N^3)。
 *
 * ### 空间复杂度分析：
 * - 构建映射：使用了一个 `HashMap` 存储每个用户的访问记录，空间复杂度为 O(N)。
 * - 暴力搜索：使用了一个 `HashSet` 存储不重复的三个网站序列，空间复杂度为 O(N)。
 * - 找到出现次数最多的网站序列：使用了一个 `HashMap` 存储每个不重复的三个网站序列及其出现次数，空间复杂度为 O(N)。
 * 综合来看，总的空间复杂度为 O(N)。
 *
 * 希望这样的解释能帮助你理解代码的功能和复杂度分析。
 */

public class LeetCode_1152_AnalyzeUserWebsiteVisitPattern{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Pair {
        int time;
        String web;
        public Pair(int time, String web) {
            this.time = time;
            this.web = web;
        }
    }

    class Solution {
        public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
            // 构建映射，key为用户名，value为该用户的访问记录列表
            Map<String, List<Pair>> map = new HashMap<>();
            int n = username.length;
            for (int i = 0; i < n; i++) {
                map.putIfAbsent(username[i], new ArrayList<>());
                map.get(username[i]).add(new Pair(timestamp[i], website[i]));
            }

            // 记录三个网站序列的出现次数
            Map<String, Integer> count = new HashMap<>();
            String res = "";
            for (String key : map.keySet()) {
                Set<String> set = new HashSet<>(); // 用于避免在同一个用户中访问相同的三个网站序列
                List<Pair> list = map.get(key);
                Collections.sort(list, (a, b)->(a.time - b.time)); // 按时间排序

                // 暴力搜索所有可能的三个网站序列
                for (int i = 0; i < list.size(); i++) {
                    for (int j = i + 1; j < list.size(); j++) {
                        for (int k = j + 1; k < list.size(); k++) {
                            String str = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                            if (!set.contains(str)) {
                                count.put(str, count.getOrDefault(str, 0) + 1);
                                set.add(str);
                            }
                            if (res.equals("") || count.get(res) < count.get(str) || (count.get(res) == count.get(str) && res.compareTo(str) > 0)) {
                                // 确保结果按照字典顺序排列
                                res = str;
                            }
                        }
                    }
                }
            }
            // 解析结果字符串，得到最终结果
            String[] r = res.split(" ");
            List<String> result = new ArrayList<>();
            for (String str : r) {
                result.add(str);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        LeetCode_1152_AnalyzeUserWebsiteVisitPattern.Solution solution = new LeetCode_1152_AnalyzeUserWebsiteVisitPattern().new Solution();

        // 测试代码
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about","career"};

        List<String> result = solution.mostVisitedPattern(username, timestamp, website);
        System.out.println(result); // 预期输出: ["home","about","career"]
    }
}

/**
You are given two string arrays username and website and an integer array 
timestamp. All the given arrays are of the same length and the tuple [username[i], 
website[i], timestamp[i]] indicates that the user username[i] visited the website 
website[i] at time timestamp[i]. 

 A pattern is a list of three websites (not necessarily distinct). 

 
 For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and [
"luffy", "luffy", "luffy"] are all patterns. 
 

 The score of a pattern is the number of users that visited all the websites in 
the pattern in the same order they appeared in the pattern. 

 
 For example, if the pattern is ["home", "away", "love"], the score is the 
number of users x such that x visited "home" then visited "away" and visited "love" 
after that. 
 Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is 
the number of users x such that x visited "leetcode" then visited "love" and 
visited "leetcode" one more time after that. 
 Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number 
of users x such that x visited "luffy" three different times at different 
timestamps. 
 

 Return the pattern with the largest score. If there is more than one pattern 
with the same largest score, return the lexicographically smallest such pattern. 

 
 Example 1: 

 
Input: username = ["joe","joe","joe","james","james","james","james","mary",
"mary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about",
"career","home","cart","maps","home","home","about","career"]
Output: ["home","about","career"]
Explanation: The tuples in this example are:
["joe","home",1],["joe","about",2],["joe","career",3],["james","home",4],[
"james","cart",5],["james","maps",6],["james","home",7],["mary","home",8],["mary",
"about",9], and ["mary","career",10].
The pattern ("home", "about", "career") has score 2 (joe and mary).
The pattern ("home", "cart", "maps") has score 1 (james).
The pattern ("home", "cart", "home") has score 1 (james).
The pattern ("home", "maps", "home") has score 1 (james).
The pattern ("cart", "maps", "home") has score 1 (james).
The pattern ("home", "home", "home") has score 0 (no user visited home 3 times).

 

 Example 2: 

 
Input: username = ["ua","ua","ua","ub","ub","ub"], timestamp = [1,2,3,4,5,6], 
website = ["a","b","a","a","b","c"]
Output: ["a","b","a"]
 

 
 Constraints: 

 
 3 <= username.length <= 50 
 1 <= username[i].length <= 10 
 timestamp.length == username.length 
 1 <= timestamp[i] <= 10⁹ 
 website.length == username.length 
 1 <= website[i].length <= 10 
 username[i] and website[i] consist of lowercase English letters. 
 It is guaranteed that there is at least one user who visited at least three 
websites. 
 All the tuples [username[i], timestamp[i], website[i]] are unique. 
 

 Related Topics Array Hash Table Sorting 👍 484 👎 3652

*/