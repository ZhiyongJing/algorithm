package leetcode.question.string_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@Question:  271. Encode and Decode Strings
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.42%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(K)
 */
/**
 * 第一步：题目描述：
 * ------------------------------------------------------------
 * LeetCode 271 - Encode and Decode Strings（编码与解码字符串）
 *
 * 设计一个算法，实现将字符串列表编码成单个字符串，并能将其准确还原为原始字符串列表。
 *
 * 要求：
 * - 编码后的字符串必须能被唯一解码；
 * - 字符串中可能包含任何字符（包括特殊字符）；
 * - 编码与解码过程必须能应对空字符串、空列表、包含分隔符的内容等情况。
 *
 * 示例：
 * 输入：["hello", "world", "", "abc:/123"]
 * 编码后可能为："5/:hello5/:world0/:8/:abc:/123"
 * 解码后应能还原为原始字符串列表。
 *
 * 第二步：解题思路（逐步详细说明）：
 * ------------------------------------------------------------
 * 整体策略：
 * - 采用定长+分隔符的方式进行编码，例如 "5/:hello"
 *   其中：
 *     - "5" 表示字符串长度；
 *     - "/:" 是人为选择的安全分隔符；
 *     - "hello" 是字符串内容。
 * - 解码时则按此格式提取出长度字段和后面的字符串内容。
 *
 * 步骤详解：
 * 1. 编码：
 *    - 初始化 StringBuilder 存储结果；
 *    - 遍历字符串列表，将每个字符串变为：s.length() + "/:" + s；
 *    - 最后将整个编码字符串返回。
 *
 *    示例：
 *    输入：["abc", "xyz"]
 *    编码：3/:abc3/:xyz → 合并为 "3/:abc3/:xyz"
 *
 * 2. 解码：
 *    - 初始化指针 i = 0；
 *    - 在字符串中查找下一个 "/:" 分隔符，位置为 delim；
 *    - 从 i 到 delim 是长度字段，解析为 int；
 *    - 从 delim+2 开始，提取指定长度的字符串，即为原始字符串；
 *    - 将其加入结果列表，并更新指针 i 到下一个块的开始位置；
 *    - 循环直到字符串结束。
 *
 *    示例：
 *    输入："3/:abc3/:xyz"
 *    第一次：i=0 → delim=1 → 长度=3 → str="abc" → i=6
 *    第二次：i=6 → delim=7 → 长度=3 → str="xyz" → i=12
 *    返回列表 ["abc", "xyz"]
 *
 * 3. 特殊情况说明：
 *    - 可以处理空字符串：0/:；
 *    - 可以处理包含 "/:" 的字符串内容；
 *    - 可处理空列表；
 *
 * 第三步：时间与空间复杂度分析：
 * ------------------------------------------------------------
 * 时间复杂度：O(N)
 * - N 是所有字符串总长度；
 * - 编码和解码都是线性扫描，总共访问每个字符一次。
 *
 * 空间复杂度：O(K)
 * - K 是结果字符串列表中字符串个数（用于存储结果）；
 * - 不使用额外辅助结构，除了解码结果。
 *
 * ✅ 该解法通过 LeetCode 所有测试用例，已验证正确。
 */


public class LeetCode_271_EncodeAndDecodeStrings{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Codec {
        public String encode(List<String> strs) {
            // 初始化一个 StringBuilder 用于构建编码后的字符串
            StringBuilder encodedString = new StringBuilder();
            // 遍历每个字符串
            for (String s : strs) {
                // 编码格式为：字符串长度 + "/:" + 字符串内容
                // 例如: "hello" 会变成 "5/:hello"
                encodedString.append(s.length()).append("/:").append(s);
            }
            // 返回编码后的完整字符串
            return encodedString.toString();
        }

        public List<String> decode(String s) {
            // 初始化一个列表，用于保存解码后的字符串
            List<String> decodedStrings = new ArrayList<>();
            // 从字符串起始位置开始解析
            int i = 0;
            while (i < s.length()) {
                // 查找下一个 "/:" 的位置，作为分隔符
                int delim = s.indexOf("/:", i);
                // 解析长度字段，即起始位置到 "/:" 之间的整数
                int length = Integer.parseInt(s.substring(i, delim));
                // 根据长度提取目标字符串，位置从 delim+2 开始，长度为 length
                String str = s.substring(delim + 2, delim + 2 + length);
                // 将提取出的字符串加入结果列表
                decodedStrings.add(str);
                // 移动 i 到下一个长度字段的位置
                i = delim + 2 + length;
            }
            // 返回解码后的字符串列表
            return decodedStrings;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 测试 Codec 编解码功能
        Codec codec = new LeetCode_271_EncodeAndDecodeStrings().new Codec();

        // 构造一个测试字符串列表
        List<String> input = Arrays.asList("hello", "world", "", "LeetCode", "123:/456");

        // 编码：将字符串列表编码成一个字符串
        String encoded = codec.encode(input);
        System.out.println("Encoded String:");
        System.out.println(encoded);

        // 解码：将编码后的字符串还原成字符串列表
        List<String> decoded = codec.decode(encoded);
        System.out.println("Decoded Strings:");
        for (String str : decoded) {
            System.out.println(str);
        }
    }
}

/**
Design an algorithm to encode a list of strings to a string. The encoded string 
is then sent over the network and is decoded back to the original list of 
strings. 

 Machine 1 (sender) has the function: 

 
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
} 

Machine 2 (receiver) has the function:

 
vector<string> decode(string s) {
  //... your code
  return strs;
}
 

 So Machine 1 does: 

 
string encoded_string = encode(strs);
 

 and Machine 2 does: 

 
vector<string> strs2 = decode(encoded_string);
 

 strs2 in Machine 2 should be the same as strs in Machine 1. 

 Implement the encode and decode methods. 

 You are not allowed to solve the problem using any serialize methods (such as 
eval). 

 
 Example 1: 

 
Input: dummy_input = ["Hello","World"]
Output: ["Hello","World"]
Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

Machine 2:
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
 

 Example 2: 

 
Input: dummy_input = [""]
Output: [""]
 

 
 Constraints: 

 
 1 <= strs.length <= 200 
 0 <= strs[i].length <= 200 
 strs[i] contains any possible characters out of 256 valid ASCII characters. 
 

 
 Follow up: Could you write a generalized algorithm to work on any possible set 
of characters? 

 Related Topics Array String Design 👍 1519 👎 438

*/