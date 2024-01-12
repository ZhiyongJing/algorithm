package leetcode.question.dfs;

import java.util.*;

/**
  *@Question:  126. Word Ladder II     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 53.66%      
  *@Time  Complexity: O(nk^2)N is the number of words in wordList, K is the maximum length of a word,
  *@Space Complexity: O(nk)
 */

public class LeetCode_126_WordLadderIi{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<String, List<String>> adjList = new HashMap<String, List<String>>();
    List<String> currPath = new ArrayList<String>();
    List<List<String>> shortestPaths = new ArrayList<List<String>>();

    private List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<String>();
        char charList[] = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private void backtrack(String source, String destination) {
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            Collections.reverse(tempPath);
            shortestPaths.add(tempPath);
        }

        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private void bfs(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        // remove the root word which is the first layer in the BFS
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }

        Map<String, Integer> isEnqueued = new HashMap<String, Integer>();
        isEnqueued.put(beginWord, 1);

        while (q.size() > 0)  {
            // visited will store the words of current layer
            List<String> visited = new ArrayList<String>();

            for (int i = q.size() - 1; i >= 0; i--) {
                String currWord = q.peek();
                q.remove();

                // findNeighbors will have the adjacent words of the currWord
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (String word : neighbors) {
                    visited.add(word);

                    if (!adjList.containsKey(word)) {
                        adjList.put(word, new ArrayList<String>());
                    }

                    // add the edge from word to currWord in the list
                    adjList.get(word).add(currWord);
                    if (!isEnqueued.containsKey(word)) {
                        q.add(word);
                        isEnqueued.put(word, 1);
                    }
                }
            }
            // removing the words of the previous layer
            for (int i = 0; i < visited.size(); i++) {
                if (wordList.contains(visited.get(i))) {
                    wordList.remove(visited.get(i));
                }
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // copying the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the DAG using BFS
        bfs(beginWord, endWord, copiedWordList);

        // every path will start from the endWord
        currPath.add(endWord);
        // traverse the DAG to find all the paths between endWord and beginWord
        backtrack(endWord, beginWord);

        return shortestPaths;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_126_WordLadderIi().new Solution();
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
 

 Given two words, beginWord and endWord, and a dictionary wordList, return all 
the shortest transformation sequences from beginWord to endWord, or an empty 
list if no such sequence exists. Each sequence should be returned as a list of the 
words [beginWord, s1, s2, ..., sk]. 

 
 Example 1: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
 

 Example 2: 

 
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot",
"log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid 
transformation sequence.
 

 
 Constraints: 

 
 1 <= beginWord.length <= 5 
 endWord.length == beginWord.length 
 1 <= wordList.length <= 500 
 wordList[i].length == beginWord.length 
 beginWord, endWord, and wordList[i] consist of lowercase English letters. 
 beginWord != endWord 
 All the words in wordList are unique. 
 The sum of all shortest transformation sequences does not exceed 10⁵. 
 

 Related Topics Hash Table String Backtracking Breadth-First Search 👍 5771 👎 7
13

*/
