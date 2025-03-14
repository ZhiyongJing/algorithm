package leetcode.question.dfs;

import java.util.HashMap;

/**
 *@Question:  388. Longest Absolute File Path
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.10%
 *@Time Complexity: O(N)  // 遍历整个输入字符串一次
 *@Space Complexity: O(N)  // 使用 HashMap 存储不同深度的路径长度
 */
/**
 * 题目描述：
 * LeetCode 388. Longest Absolute File Path
 * 给定一个字符串 `input`，表示一个文件系统中的文件和目录结构。每一行用 `\t`（制表符）表示目录的层级关系。
 * 我们需要计算最长的 **绝对文件路径** 的长度。绝对路径是从根目录开始，到某个文件的完整路径。
 *
 * **示例 1：**
 * 输入：
 * "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 * 解释：
 * ```
 * dir
 * ├── subdir1
 * ├── subdir2
 * │   └── file.ext
 * ```
 * 输出：20
 * 计算路径："dir/subdir2/file.ext"（包含 '/' 的完整路径）
 *
 * **示例 2：**
 * 输入："a"
 * 解释：
 * ```
 * a
 * ```
 * 输出：0（没有文件）
 *
 * **示例 3：**
 * 输入："file.txt"
 * 解释：
 * ```
 * file.txt
 * ```
 * 输出：8（只有一个文件，没有目录）
 *
 * ---
 *
 * **解题思路：**
 * 1. **解析输入字符串**
 *    - 先按 `\n` 拆分 `input`，获取每一行内容。
 *    - 计算每一行的深度 `depth`（即 `\t` 的数量）。
 *    - 获取 `name`（去掉 `\t` 之后的真实文件名或目录名）。
 *
 *    **示例：**
 *    ```
 *    input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 *    lines = ["dir", "\tsubdir1", "\tsubdir2", "\t\tfile.ext"]
 *    ```
 *    - "dir" -> depth = 0, name = "dir"
 *    - "\tsubdir1" -> depth = 1, name = "subdir1"
 *    - "\tsubdir2" -> depth = 1, name = "subdir2"
 *    - "\t\tfile.ext" -> depth = 2, name = "file.ext"
 *
 * 2. **使用 `HashMap` 记录每一层的路径长度**
 *    - `HashMap<Integer, Integer> pathLengths` 用来存储每一层的路径长度。
 *    - `pathLengths.get(depth)` 代表当前深度的目录路径长度。
 *    - 如果是文件，计算完整路径长度，更新 `maxLength`。
 *    - 如果是目录，更新 `pathLengths`，表示当前深度的路径长度。
 *
 *    **示例：**
 *    ```
 *    当前处理 "dir":
 *    depth = 0, name = "dir"
 *    pathLengths.put(1, "dir".length() + 1)  // 存储 "dir/"
 *
 *    处理 "\tsubdir2":
 *    depth = 1, name = "subdir2"
 *    pathLengths.put(2, pathLengths.get(1) + "subdir2".length() + 1)  // "dir/subdir2/"
 *
 *    处理 "\t\tfile.ext":
 *    depth = 2, name = "file.ext"
 *    计算路径长度："dir/subdir2/file.ext".length()
 *    maxLength = Math.max(maxLength, 计算结果)
 *    ```
 *
 * 3. **计算路径长度**
 *    - 如果当前 `name` 是文件 (`name.contains(".")`)，计算完整路径长度 `pathLengths.get(depth) + name.length()` 并更新 `maxLength`。
 *    - 如果当前 `name` 是目录，存储 `pathLengths.put(depth + 1, pathLengths.getOrDefault(depth, 0) + name.length() + 1)`（`+1` 代表 `/`）。
 *
 * ---
 *
 * **时间和空间复杂度分析：**
 * - **时间复杂度：O(N)**
 *   - `split("\n")` 遍历字符串一次，时间复杂度为 `O(N)`。
 *   - 遍历 `lines` 进行路径长度计算，每一行最多访问一次 `HashMap`，整体 `O(N)`。
 *   - 最终 `maxLength` 计算也是 `O(1)`，所以总的时间复杂度为 **O(N)**。
 *
 * - **空间复杂度：O(N)**
 *   - 需要存储 `pathLengths`（深度 -> 路径长度），最坏情况下 `input` 全部是嵌套目录，存储 `O(N)` 个键值对。
 *   - `lines` 存储分割后的字符串数组，最坏情况下 `O(N)`。
 *   - 因此，总的空间复杂度为 **O(N)**。
 */


public class LeetCode_388_LongestAbsoluteFilePath{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthLongestPath(String input) {
            // 按换行符 `\n` 拆分字符串，得到目录和文件的行
            String[] lines = input.split("\n");

            // 用于记录最长绝对文件路径长度的变量
            int maxLength = 0;

            // HashMap 存储当前深度的路径长度
            HashMap<Integer, Integer> pathLengths = new HashMap<>(); // depth: length

            // 遍历每一行文件路径
            for (String line : lines) {
                // 去除 `\t` 获取当前文件/文件夹的名称
                String name = line.replaceAll("\t", "");

                // 计算当前路径的深度，即 `\t` 的数量
                int depth = line.length() - name.length();

                // 判断当前是否是文件（如果包含 `.`，表示是文件）
                boolean isFile = name.contains(".");

                if (isFile) {
                    // 如果是文件，计算该文件的绝对路径长度
                    maxLength = Math.max(maxLength, pathLengths.getOrDefault(depth, 0) + name.length());
                } else {
                    // 如果是文件夹，更新该深度的路径长度（加 1 代表 `/`）
                    pathLengths.put(depth + 1, pathLengths.getOrDefault(depth, 0) + name.length() + 1);
                }
            }

            return maxLength;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_388_LongestAbsoluteFilePath().new Solution();

        // 测试用例 1
        String input1 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        System.out.println("最长绝对文件路径长度: " + solution.lengthLongestPath(input1));
        // 预期输出: 20 -> "dir/subdir2/file.ext"

        // 测试用例 2
        String input2 = "a";
        System.out.println("最长绝对文件路径长度: " + solution.lengthLongestPath(input2));
        // 预期输出: 0 -> 没有文件

        // 测试用例 3
        String input3 = "file.txt";
        System.out.println("最长绝对文件路径长度: " + solution.lengthLongestPath(input3));
        // 预期输出: 8 -> "file.txt"

        // 测试用例 4
        String input4 = "dir\n\tfile1.txt\n\tsubdir1\n\t\tfile2.txt";
        System.out.println("最长绝对文件路径长度: " + solution.lengthLongestPath(input4));
        // 预期输出: 16 -> "dir/file1.txt"
    }
}

/**
Suppose we have a file system that stores both files and directories. An 
example of one system is represented in the following picture: 

 

 Here, we have dir as the only directory in the root. dir contains two 
subdirectories, subdir1 and subdir2. subdir1 contains a file file1.ext and subdirectory 
subsubdir1. subdir2 contains a subdirectory subsubdir2, which contains a file 
file2.ext. 

 In text form, it looks like this (with ⟶ representing the tab character): 

 
dir
⟶ subdir1
⟶ ⟶ file1.ext
⟶ ⟶ subsubdir1
⟶ subdir2
⟶ ⟶ subsubdir2
⟶ ⟶ ⟶ file2.ext
 

 If we were to write this representation in code, it will look like this: "dir
\tsubdir1
\t\tfile1.ext
\t\tsubsubdir1
\tsubdir2
\t\tsubsubdir2
\t\t\tfile2.ext". Note that the '
' and '\t' are the new-line and tab characters. 

 Every file and directory has a unique absolute path in the file system, which 
is the order of directories that must be opened to reach the file/directory 
itself, all concatenated by '/'s. Using the above example, the absolute path to file2
.ext is "dir/subdir2/subsubdir2/file2.ext". Each directory name consists of 
letters, digits, and/or spaces. Each file name is of the form name.extension, where 
name and extension consist of letters, digits, and/or spaces. 

 Given a string input representing the file system in the explained format, 
return the length of the longest absolute path to a file in the abstracted file 
system. If there is no file in the system, return 0. 

 Note that the testcases are generated such that the file system is valid and 
no file or directory name has length 0. 

 
 Example 1: 
 
 
Input: input = "dir
\tsubdir1
\tsubdir2
\t\tfile.ext"
Output: 20
Explanation: We have only one file, and the absolute path is "dir/subdir2/file.
ext" of length 20.
 

 Example 2: 
 
 
Input: input = "dir
\tsubdir1
\t\tfile1.ext
\t\tsubsubdir1
\tsubdir2
\t\tsubsubdir2
\t\t\tfile2.ext"
Output: 32
Explanation: We have two files:
"dir/subdir1/file1.ext" of length 21
"dir/subdir2/subsubdir2/file2.ext" of length 32.
We return 32 since it is the longest absolute path to a file.
 

 Example 3: 

 
Input: input = "a"
Output: 0
Explanation: We do not have any files, just a single directory named "a".
 

 
 Constraints: 

 
 1 <= input.length <= 10⁴ 
 input may contain lowercase or uppercase English letters, a new line character 
'
', a tab character '\t', a dot '.', a space ' ', and digits. 
 All file and directory names have positive length. 
 

 Related Topics String Stack Depth-First Search 👍 1329 👎 2543

*/