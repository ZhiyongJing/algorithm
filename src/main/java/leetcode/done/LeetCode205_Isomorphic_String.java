package leetcode.done;

import java.util.HashMap;
import java.util.Map;

//205. Isomorphic Strings
//Easy
//Time Complexity: O(n)
public class LeetCode205_Isomorphic_String {
    class Solution {
        private String transformString(String s) {
            Map<Character, Integer> indexMapping = new HashMap<>();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < s.length(); ++i) {
                char c1 = s.charAt(i);

                if (!indexMapping.containsKey(c1)) {
                    indexMapping.put(c1, i);
                }

                builder.append(Integer.toString(indexMapping.get(c1)));
                builder.append(" ");
            }
            return builder.toString();
        }

        public boolean isIsomorphic(String s, String t) {
            return transformString(s).equals(transformString(t));
        }
    }
}
