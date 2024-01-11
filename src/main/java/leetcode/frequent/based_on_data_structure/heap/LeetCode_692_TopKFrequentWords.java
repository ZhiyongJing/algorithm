package leetcode.frequent.based_on_data_structure.heap;

import java.util.*;

/**
  *@Question:  692. Top K Frequent Words     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 57.09000000000001%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_692_TopKFrequentWords{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Word implements Comparable<Word> {
        private String word;
        private int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }

        public int compareTo(Word other) {
            if (this.count == other.count) {
                return this.word.compareTo(other.word);
            }
            return other.count - this.count;
        }
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }

        List<Word> candidates = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            candidates.add(new Word(entry.getKey(), entry.getValue()));
        }

        Queue<Word> h = new PriorityQueue<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(h.poll().word);
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_692_TopKFrequentWords().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an array of strings words and an integer k, return the k most frequent 
strings. 

 Return the answer sorted by the frequency from highest to lowest. Sort the 
words with the same frequency by their lexicographical order. 

 
 Example 1: 

 
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
 

 Example 2: 

 
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], 
k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, 
with the number of occurrence being 4, 3, 2 and 1 respectively.
 

 
 Constraints: 

 
 1 <= words.length <= 500 
 1 <= words[i].length <= 10 
 words[i] consists of lowercase English letters. 
 k is in the range [1, The number of unique words[i]] 
 

 
 Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space? 

 Related Topics Hash Table String Trie Sorting Heap (Priority Queue) Bucket 
Sort Counting 👍 7473 👎 341

*/