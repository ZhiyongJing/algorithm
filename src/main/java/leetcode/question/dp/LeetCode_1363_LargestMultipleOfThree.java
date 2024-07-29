package leetcode.question.dp;
/**
 *@Question:  1363. Largest Multiple of Three
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 47.73%
 *@Time  Complexity: O(n log n) [因排序操作]
 *@Space Complexity: O(1) [额外的存储空间]
 */

/**
 * ### 解题思路
 *
 * #### 目标
 * 要构建一个能够被3整除的最大数字，使用给定的数字数组。
 *
 * #### 思路
 * 1. **基础知识**：
 *    - 数字能够被3整除的条件是其各位数字的和能被3整除。
 *    - 对于任意数字，模3的结果可以是0、1或2。
 *
 * 2. **步骤**：
 *    1. **计算总和**：
 *       - 计算所有数字的和，并通过对3取模来确定总和的模3结果（`remainderOfSum`）。这个结果告诉我们如何调整数字，以确保最终构建的数字能够被3整除。
 *
 *    2. **确定移除策略**：
 *       - **如果 `remainderOfSum` 为0**：说明所有数字的和已经是3的倍数，不需要移除任何数字。
 *       - **如果 `remainderOfSum` 为1**：尝试移除一个模3结果为1的数字（例如，1、4、7）。如果没有足够的这样的数字，则需要移除两个模3结果为2的数字（例如，2、5、8）。
 *       - **如果 `remainderOfSum` 为2**：尝试移除一个模3结果为2的数字（例如，2、5、8）。如果没有足够的这样的数字，则需要移除两个模3结果为1的数字（例如，1、4、7）。
 *
 *    3. **构建结果**：
 *       - 通过一个计数器数组来跟踪每个数字的出现次数。
 *       - 从9到0遍历（从大到小），根据之前确定的移除策略来构建最终结果字符串。确保在字符串中较大的数字排在前面，以便构建出最大的数字。
 *
 *    4. **处理特殊情况**：
 *       - 如果最终字符串为空，说明没有合法的数字，返回空字符串。
 *       - 如果字符串以0开头，说明所有数字都是0，返回"0"。
 *
 * ### 时间复杂度
 *
 * - **O(n)**：
 *   - **遍历数字数组**：计算总和和更新计数器数组的时间复杂度是O(n)，其中n是数字数组的长度。
 *   - **构建结果字符串**：遍历计数器数组和构建结果字符串的时间复杂度是O(n)，因为计数器数组的长度是常数（0-9）。
 *
 * - **总体时间复杂度**：O(n)
 *
 * ### 空间复杂度
 *
 * - **O(1)**：
 *   - **计数器数组**：固定大小为10，用于存储0到9的数字计数。
 *   - **其他变量**：仅使用常量级的额外空间来存储中间计算结果和临时变量。
 *
 * - **总体空间复杂度**：O(1)
 *
 * 这种方法有效地使用了常量级的额外空间，并且时间复杂度仅与输入数组的长度线性相关，使得它在处理较大数组时也表现良好。
 */
public class LeetCode_1363_LargestMultipleOfThree {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestMultipleOfThree(int[] digits) {
            /**
             * 重要说明：
             * 1. 如果所有数字的和可以被3整除，则整个数字也可以被3整除。
             * 2. 对于每个数字，如果其模3的结果是0，我们希望这些数字在结果中出现的位置是优先的（从大到小），
             *    比如9应放在前面，0应放在最后面。
             * 3. 排序可以使得结果更容易构建，但时间复杂度为O(nlogn)。
             * 4. 因为数字范围是0-9，我们可以使用长度为10的数组来计数。
             */

            // 如果数组只有一个数字，直接检查它是否能被3整除
            if(digits.length == 1){
                if(digits[0] % 3 == 0){
                    return Integer.toString(digits[0]); // 如果可以被3整除，返回该数字
                }
                else{
                    return ""; // 否则返回空字符串
                }
            }

            // 创建一个变量保存所有数字和的模3结果
            int remainderOfSum = 0;
            // 创建一个数组用于统计每个数字的出现次数
            int[] counter = new int[10];

            // 遍历每个数字，更新计数器并计算所有数字的和
            for(int i: digits){
                counter[i]++;
                remainderOfSum = remainderOfSum + i;
            }

            // 统计模3结果为1和2的数字数量
            int remainderOf1 = counter[1] + counter[4] + counter[7];
            int remainderOf2 = counter[2] + counter[5] + counter[8];

            // 计算总和的模3结果
            remainderOfSum = remainderOfSum % 3;

            /**
             * 根据不同的模3结果，处理移除数字的策略：
             * 1. 如果模3结果为0，不需要进行任何操作
             * 2. 如果模3结果为1，需要移除模3结果为1的最小数字
             *    如果不能移除单个数字，则移除模3结果为2的两个数字
             * 3. 如果模3结果为2，需要移除模3结果为2的最小数字
             *    如果不能移除单个数字，则移除模3结果为1的两个数字
             */
            if(remainderOfSum == 1){
                if(remainderOf1 > 0){
                    remainderOf1--; // 移除一个模3结果为1的数字
                }
                else{
                    remainderOf2 = remainderOf2 - 2; // 移除两个模3结果为2的数字
                }
            }
            if(remainderOfSum == 2){
                if(remainderOf2 > 0){
                    remainderOf2--; // 移除一个模3结果为2的数字
                }
                else{
                    remainderOf1 = remainderOf1 - 2; // 移除两个模3结果为1的数字
                }
            }

            // 构建最终的结果字符串
            StringBuilder sb = new StringBuilder();

            /**
             * 为了得到最大的数字，我们从9开始到0遍历，依次添加数字。
             * 根据计数器中允许使用的数字，构建结果字符串。
             */
            for(int i = 9; i >= 0; i--){
                if(i % 3 == 0){
                    while(counter[i] > 0){
                        sb.append(i);
                        counter[i]--;
                    }
                }
                if(i % 3 == 1){
                    while(counter[i] > 0 && remainderOf1 > 0){
                        sb.append(i);
                        counter[i]--;
                        remainderOf1--;
                    }
                }
                if(i % 3 == 2){
                    while(counter[i] > 0 && remainderOf2 > 0){
                        sb.append(i);
                        counter[i]--;
                        remainderOf2--;
                    }
                }
            }

            // 如果结果为空或以0开头，返回0
            // 否则返回结果字符串
            if(sb.length() == 0){
                return "";
            }
            else{
                if(sb.charAt(0) == '0'){
                    return "0";
                }
                else{
                    return sb.toString();
                }
            }

        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_1363_LargestMultipleOfThree().new Solution();

        // 测试用例1
        int[] digits1 = {8, 1, 9};
        System.out.println(solution.largestMultipleOfThree(digits1)); // 应输出 "981"

        // 测试用例2
        int[] digits2 = {8, 6, 7, 1, 0};
        System.out.println(solution.largestMultipleOfThree(digits2)); // 应输出 "8760"

        // 测试用例3
        int[] digits3 = {1};
        System.out.println(solution.largestMultipleOfThree(digits3)); // 应输出 ""

        // 测试用例4
        int[] digits4 = {0, 0, 0, 0};
        System.out.println(solution.largestMultipleOfThree(digits4)); // 应输出 "0"
    }
}

/**
Given an array of digits digits, return the largest multiple of three that can 
be formed by concatenating some of the given digits in any order. If there is no 
answer return an empty string. 

 Since the answer may not fit in an integer data type, return the answer as a 
string. Note that the returning answer must not contain unnecessary leading zeros.
 

 
 Example 1: 

 
Input: digits = [8,1,9]
Output: "981"
 

 Example 2: 

 
Input: digits = [8,6,7,1,0]
Output: "8760"
 

 Example 3: 

 
Input: digits = [1]
Output: ""
 

 
 Constraints: 

 
 1 <= digits.length <= 10⁴ 
 0 <= digits[i] <= 9 
 

 Related Topics Array Dynamic Programming Greedy 👍 600 👎 87

*/