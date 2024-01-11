package leetcode.frequent.based_on_data_structure.stack;

import java.util.*;

/**
  *@Question:  770. Basic Calculator IV     
  *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 0.0%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_770_BasicCalculatorIv{
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int n;
    String s;
    char[] arr;
    int[] braces;
    HashMap<String, Integer> variables = new HashMap<>();

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        // Initialize stuff
        s = expression;
        arr = s.toCharArray();
        n = arr.length;
        braces = new int[n];
        Arrays.fill(braces, -1);
        int[] stack = new int[n/2];
        int index = -1;
        for(int i=0; i<n; ++i) {
            if(arr[i] == '(') stack[++index] = i;
            else if(arr[i] == ')') {
                int last = stack[index--];
                braces[last] = i;
                braces[i] = last;
            }
        }
        for(int i=0; i<evalvars.length; ++i) variables.put(evalvars[i], evalints[i]);

        // Call the main parser which opens all brackets to the deepest levels and creates Terms
        List<Term> terms = dewIt(0, n-1);

        // Create map to collapse and sort Terms
        TreeMap<String, Integer> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int ca = countStars(a), cb = countStars(b);
                if(ca != cb) return cb - ca;
                else return a.compareTo(b);
            }

            private int countStars(String s) {
                int ans = 0;
                for(char c: s.toCharArray()) if(c == '*') ++ans;
                return ans;
            }
        });
        for(Term term: terms) {
            if(term.coeff != 0) {
                String key = term.getKey();
                if(map.containsKey(key)) {
                    int oldCoeff = map.get(key);
                    if(oldCoeff == -term.coeff) map.remove(key);
                    else map.put(key, oldCoeff + term.coeff);
                } else map.put(key, term.coeff);
            }
        }

        // Convert map to list
        List<String> ans = new LinkedList<>();
        for(String k: map.keySet()) ans.add(map.get(k) + "" + k);
        return ans;
    }

    private List<Term> dewIt(int a, int b) {
        if(braces[a] == b) return dewIt(a+1, b-1);
        List<Term> ans = new LinkedList<>(), buffer = new LinkedList<>();
        buffer.add(new Term(1, new LinkedList<>()));
        for(int i=a; i<=b; ) {
            int j = i;
            List<Term> curr = null;
            if(arr[i] == '(') {
                j = braces[i] + 1;
                curr = dewIt(i+1, j-2);
            }
            else {
                while(j <= b && arr[j] != ' ') ++j;
                String exp = s.substring(i, j);
                int val = 1;
                List<String> vars = new LinkedList<>();
                if(variables.containsKey(exp)) val = variables.get(exp);
                else if (exp.charAt(0) <= '9') val = Integer.valueOf(exp);
                else vars.add(exp);
                curr = new LinkedList<>();
                curr.add(new Term(val, vars));
            }
            buffer = multiply(buffer, curr);
            if(j > b || arr[j+1] == '+' || arr[j+1] == '-') {
                ans.addAll(buffer);
                buffer = new LinkedList<>();
            }
            if(j < b) {
                ++j;
                if(arr[j] == '+') buffer.add(new Term(1, new LinkedList<>()));
                else if(arr[j] == '-') buffer.add(new Term(-1, new LinkedList<>()));
                j += 2;
            }
            i = j;
        }
        return ans;
    }

    private List<Term> multiply(List<Term> a, List<Term> b) {
        List<Term> ans = new LinkedList<>();
        for(Term x: a) for(Term y: b) {
            Term prod = x.clone();
            prod.multiply(y);
            ans.add(prod);
        }
        return ans;
    }
}

    class Term {
        int coeff;
        List<String> vars;

        public Term(int a, List<String> c) {
            this.coeff = a;
            vars = new LinkedList<>();
            vars.addAll(c);
        }

        public String getKey() {
            StringBuilder b = new StringBuilder();
            Collections.sort(vars);
            for(String x: vars) {
                b.append('*');
                b.append(x);
            }
            return b.toString();
        }

        public void multiply(Term that) {
            this.coeff *= that.coeff;
            if(this.coeff == 0) vars.clear();
            else this.vars.addAll(that.vars);
        }

        public Term clone() {
            return new Term(coeff, vars);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_770_BasicCalculatorIv().new Solution();
        // TO TEST
        //solution.
    }
}
/**
Given an expression such as expression = "e + 8 - a + 5" and an evaluation map 
such as {"e": 1} (given in terms of evalvars = ["e"] and evalints = [1]), return 
a list of tokens representing the simplified expression, such as ["-1*a","14"] 

 
 An expression alternates chunks and symbols, with a space separating each 
chunk and symbol. 
 A chunk is either an expression in parentheses, a variable, or a non-negative 
integer. 
 A variable is a string of lowercase letters (not including digits.) Note that 
variables can be multiple letters, and note that variables never have a leading 
coefficient or unary operator like "2x" or "-x". 
 

 Expressions are evaluated in the usual order: brackets first, then 
multiplication, then addition and subtraction. 

 
 For example, expression = "1 + 2 * 3" has an answer of ["7"]. 
 

 The format of the output is as follows: 

 
 For each term of free variables with a non-zero coefficient, we write the free 
variables within a term in sorted order lexicographically. 
 
 For example, we would never write a term like "b*a*c", only "a*b*c". 
 
 Terms have degrees equal to the number of free variables being multiplied, 
counting multiplicity. We write the largest degree terms of our answer first, 
breaking ties by lexicographic order ignoring the leading coefficient of the term. 
 
 For example, "a*a*b*c" has degree 4. 
 
 The leading coefficient of the term is placed directly to the left with an 
asterisk separating it from the variables (if they exist.) A leading coefficient of 
1 is still printed. 
 An example of a well-formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b", "4*
a", "5*c", "-6"]. 
 Terms (including constant terms) with coefficient 0 are not included. 
 
 For example, an expression of "0" has an output of []. 
 
 

 Note: You may assume that the given expression is always valid. All 
intermediate results will be in the range of [-2³¹, 2³¹ - 1]. 

 
 Example 1: 

 
Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
Output: ["-1*a","14"]
 

 Example 2: 

 
Input: expression = "e - 8 + temperature - pressure", evalvars = ["e", 
"temperature"], evalints = [1, 12]
Output: ["-1*pressure","5"]
 

 Example 3: 

 
Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
Output: ["1*e*e","-64"]
 

 
 Constraints: 

 
 1 <= expression.length <= 250 
 expression consists of lowercase English letters, digits, '+', '-', '*', '(', 
')', ' '. 
 expression does not contain any leading or trailing spaces. 
 All the tokens in expression are separated by a single space. 
 0 <= evalvars.length <= 100 
 1 <= evalvars[i].length <= 20 
 evalvars[i] consists of lowercase English letters. 
 evalints.length == evalvars.length 
 -100 <= evalints[i] <= 100 
 

 Related Topics Hash Table Math String Stack Recursion 👍 159 👎 1390

*/