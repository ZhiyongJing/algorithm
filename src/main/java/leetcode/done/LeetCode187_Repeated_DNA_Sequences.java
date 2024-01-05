package leetcode.done;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//187. Repeated DNA Sequences
//Medium

/*
Time complexity :  O ( ( N − L ) L )
Space complexity :  O ( ( N − L ) L )
 */
public class LeetCode187_Repeated_DNA_Sequences {
    class Solution {
        public List<String> findRepeatedDnaSequences(String s) {
            int L = 10, n = s.length();
            HashSet<String> seen = new HashSet(), output = new HashSet();

            // iterate over all sequences of length L
            for (int start = 0; start < n - L + 1; ++start) {
                String tmp = s.substring(start, start + L);
                if (seen.contains(tmp)) output.add(tmp);
                seen.add(tmp);
            }
            return new ArrayList<String>(output);
        }
    }
}