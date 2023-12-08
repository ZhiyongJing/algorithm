package leetcode.frequent.dfs;

import javafx.util.Pair;

import java.util.*;

/**
  *@Question:  127. Word Ladder     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 69.58%      
  *@Time  Complexity: O(n*m^2) M is the length of each word and N is the total number of words in world list
  *@Space Complexity: O(m^2)
 */

public class LeetCode_127_WordLadder{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach( word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });
        System.out.println("allComboDict=is: " + allComboDict);


        // Queue for BFS
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        Q.add(new Pair(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Pair<String, Integer> node = Q.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    System.out.println(word +"->" + newWord + "="+allComboDict.getOrDefault(newWord, new ArrayList<>()));

                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_127_WordLadder().new Solution();
        // TO TEST
        //solution.
    }
}
/**
A transformation sequence from word beginWord to word endWord using a 
dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that: 

 
 Every adjacent pair of words differs by a single letter. 
 Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to 
be in wordList. 
 sk == endWord 
 

 Given two words, beginWord and endWord, and a dictionary wordList, return the 
number of words in the shortest transformation sequence from beginWord to 
endWord, or 0 if no such sequence exists. 

 
 Example 1: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> 
"dog" -> cog", which is 5 words long.
 

 Example 2: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid 
transformation sequence.
 

 
 Constraints: 

 
 1 <= beginWord.length <= 10 
 endWord.length == beginWord.length 
 1 <= wordList.length <= 5000 
 wordList[i].length == beginWord.length 
 beginWord, endWord, and wordList[i] consist of lowercase English letters. 
 beginWord != endWord 
 All the words in wordList are unique. 
 

 Related Topics Hash Table String Breadth-First Search 👍 11509 👎 1840

*/
