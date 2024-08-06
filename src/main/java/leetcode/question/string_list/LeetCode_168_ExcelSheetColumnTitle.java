package leetcode.question.string_list;
/**
 *@Question:  168. Excel Sheet Column Title
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 55.46%
 *@Time  Complexity: O()
 *@Space Complexity: O()
 */

/**
 * ### 题目和解题思路
 *
 * **题目**: [LeetCode 168 - Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)
 *
 * 给定一个正整数，输出其在Excel表格中的列标题。Excel表格中的列标题是按照以下规则生成的：A表示第1列，B表示第2列，依此类推，直到Z表示第26列，然后AA表示第27列，AB表示第28列，以此类推。
 *
 * **解题思路**:
 * 1. 将给定的整数`columnNumber`表示为26进制，其中'A'表示1，'B'表示2，...，'Z'表示26。
 * 2. 为了方便计算，将`columnNumber`每次减1，使得范围变为从0到25。
 * 3. 使用循环，每次计算`columnNumber`的最后一位，即`columnNumber % 26`。这个值加上'A'的ASCII值得到当前字符。
 * 4. 将得到的字符添加到结果中，然后将`columnNumber`除以26，继续处理下一位。
 * 5. 当`columnNumber`为0时停止循环。
 * 6. 由于字符是从低位到高位依次添加的，因此需要反转结果字符串。
 *
 * ### 时间和空间复杂度
 *
 * **时间复杂度**: O(log26(N))
 * 每次将`columnNumber`除以26，所以循环次数取决于N的大小的26进制表示的长度。
 *
 * **空间复杂度**: O(1)
 * 除了存储输出的StringBuilder，没有使用额外的空间。
 */

public class LeetCode_168_ExcelSheetColumnTitle{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToTitle(int columnNumber) {
            // 创建一个StringBuilder对象，用于构建结果字符串
            StringBuilder ans = new StringBuilder();

            // 当columnNumber大于0时，继续执行循环
            while (columnNumber > 0) {
                columnNumber--; // 减少1以匹配0索引
                // 计算当前字母并添加到结果字符串的末尾
                ans.append((char) (((columnNumber) % 26) + 'A'));
                // 更新columnNumber为下一个值
                columnNumber = (columnNumber) / 26;
            }

            // 由于我们是从低位到高位依次添加字母，所以需要反转字符串
            return ans.reverse().toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_168_ExcelSheetColumnTitle().new Solution();
        // 测试样例
        int columnNumber = 28;
        String result = solution.convertToTitle(columnNumber);
        System.out.println(result); // 输出: "AB"
    }
}

/**
Given an integer columnNumber, return its corresponding column title as it 
appears in an Excel sheet. 

 For example: 

 
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 
...
 

 
 Example 1: 

 
Input: columnNumber = 1
Output: "A"
 

 Example 2: 

 
Input: columnNumber = 28
Output: "AB"
 

 Example 3: 

 
Input: columnNumber = 701
Output: "ZY"
 

 
 Constraints: 

 
 1 <= columnNumber <= 2³¹ - 1 
 

 Related Topics Math String 👍 5575 👎 818

*/