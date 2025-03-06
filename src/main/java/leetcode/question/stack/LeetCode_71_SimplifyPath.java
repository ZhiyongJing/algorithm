package leetcode.question.stack;

import java.util.Stack;

/**
 *@Question:  71. Simplify Path
 *@Difficulty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 84.22%
 *@Time Complexity: O(N)  (遍历路径字符串，每个部分最多入栈或出栈一次)
 *@Space Complexity: O(N)  (使用栈存储目录名，最坏情况下存储所有目录)
 */
/**
 * 题目描述：
 * LeetCode 71. Simplify Path
 * 给定一个字符串 `path`，表示 Unix 风格的绝对路径，需要简化该路径，使其变为**规范路径**。
 *
 * Unix 规范路径规则：
 * - 多个连续的 `/` 视为一个 `/` 处理，例如 `/home//foo/` 变为 `/home/foo`。
 * - `"."` 表示当前目录，不影响路径，例如 `/a/./b` 变为 `/a/b`。
 * - `".."` 表示返回上一级目录，例如 `/a/b/..` 变为 `/a`。
 * - 根目录 `/` 无法再向上返回，例如 `/../` 仍然是 `/`。
 * - 结果路径必须以 `/` 开头，且不能以 `/` 结尾（除非结果是根目录）。
 *
 * 示例 1：
 * 输入: path = "/home/"
 * 输出: "/home"
 *
 * 示例 2：
 * 输入: path = "/../"
 * 输出: "/"
 *
 * 示例 3：
 * 输入: path = "/home//foo/"
 * 输出: "/home/foo"
 *
 * 示例 4：
 * 输入: path = "/a/./b/../../c/"
 * 输出: "/c"
 *
 * 示例 5：
 * 输入: path = "/a/../../b/../c//.//"
 * 输出: "/c"
 *
 * 示例 6：
 * 输入: path = "/a//b////c/d//././/.."
 * 输出: "/a/b/c"
 */

/**
 * 解题思路：
 * 该问题可以使用 **栈（Stack）+ 字符串处理** 解决。
 * 主要思路如下：
 *
 * 1. **使用 "/" 作为分隔符，将路径拆分成多个部分**
 *    - 例如 `path = "/a/./b/../../c/"`，使用 `split("/")` 处理后：
 *      ```
 *      ["", "a", ".", "b", "..", "..", "c", ""]
 *      ```
 *    - 忽略 `""`（连续的 `/` 导致的空字符）。
 *    - 忽略 `"."`（表示当前目录）。
 *    - `".."` 代表返回上一级目录，因此如果栈非空，则 `pop()`。
 *    - 其他部分（合法目录名）压入栈中。
 *
 * 2. **遍历 `components[]` 处理每个部分**
 *    - 如果 `component == "."`，跳过，不影响路径。
 *    - 如果 `component == ".."`：
 *      - 如果栈非空，弹出栈顶目录（返回上一级）。
 *      - 如果栈为空，说明 `..` 超出了根目录，忽略即可（不能返回 `/../`）。
 *    - 其他情况，`component` 是目录名，直接压入栈 `stack.push(component)`。
 *
 * 3. **遍历结束后，使用 `"/"` 连接栈中的目录，构造最终路径**
 *    - 如果栈为空，则返回 `/`。
 *    - 否则，将栈中的目录用 `"/"` 拼接，返回结果字符串。
 *
 * **举例分析**
 * **示例 1**：
 * ```
 * 输入: "/home/"
 * 解析: ["", "home", ""]
 * 处理后: stack = ["home"]
 * 输出: "/home"
 * ```
 *
 * **示例 2**：
 * ```
 * 输入: "/../"
 * 解析: ["", "..", ""]
 * 处理后: stack = []
 * 输出: "/"
 * ```
 *
 * **示例 3**：
 * ```
 * 输入: "/a/./b/../../c/"
 * 解析: ["", "a", ".", "b", "..", "..", "c", ""]
 * 处理过程:
 * - "a" -> push("a")
 * - "."  -> 忽略
 * - "b" -> push("b")
 * - ".." -> pop("b")
 * - ".." -> pop("a")
 * - "c" -> push("c")
 * 输出: "/c"
 * ```
 *
 * **示例 4**：
 * ```
 * 输入: "/a//b////c/d//././/.."
 * 解析: ["", "a", "", "b", "", "", "", "c", "d", ".", ".", ".."]
 * 处理过程:
 * - "a" -> push("a")
 * - "b" -> push("b")
 * - "c" -> push("c")
 * - "d" -> push("d")
 * - "."  -> 忽略
 * - ".." -> pop("d")
 * 输出: "/a/b/c"
 * ```
 */

/**
 * 时间和空间复杂度分析：
 *
 * 1. **时间复杂度：O(N)**
 *    - `split("/")` 需要 `O(N)` 遍历路径字符串。
 *    - `for` 循环遍历 `components[]`，每个部分最多 `push` 或 `pop` 一次，最坏 `O(N)`。
 *    - 连接最终路径 `O(N)`。
 *    - **总时间复杂度：O(N)**。
 *
 * 2. **空间复杂度：O(N)**
 *    - 主要是 `stack` 存储有效目录名，最坏情况下 `O(N)`。
 *    - `StringBuilder` 存储最终结果，最坏 `O(N)`。
 *    - **总空间复杂度：O(N)**。
 */


public class LeetCode_71_SimplifyPath{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String simplifyPath(String path) {
            // 初始化一个栈来存储有效的目录名
            Stack<String> stack = new Stack<String>();

            // 使用 "/" 作为分隔符，将路径拆分成多个部分
            String[] components = path.split("/");

            // 遍历分割后的路径部分
            for (String directory : components) {
                // 忽略 "." 或空字符串（因为多个 "/" 之间会产生空字符串）
                if (directory.equals(".") || directory.isEmpty()) {
                    continue;
                } else if (directory.equals("..")) {
                    // 如果遇到 ".."，需要返回上一级目录，弹出栈顶元素（如果栈非空）
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else {
                    // 否则，将有效的目录名入栈
                    stack.add(directory);
                }
            }

            // 重新构造简化路径
            StringBuilder result = new StringBuilder();
            for (String dir : stack) {
                result.append("/");
                result.append(dir);
            }

            // 如果栈为空，返回 "/"
            return result.length() > 0 ? result.toString() : "/";
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_71_SimplifyPath().new Solution();

        // 测试用例 1
        System.out.println(solution.simplifyPath("/home/"));
        // 期望输出: "/home"

        // 测试用例 2
        System.out.println(solution.simplifyPath("/../"));
        // 期望输出: "/"

        // 测试用例 3
        System.out.println(solution.simplifyPath("/home//foo/"));
        // 期望输出: "/home/foo"

        // 测试用例 4
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));
        // 期望输出: "/c"

        // 测试用例 5
        System.out.println(solution.simplifyPath("/a/../../b/../c//.//"));
        // 期望输出: "/c"

        // 测试用例 6
        System.out.println(solution.simplifyPath("/a//b////c/d//././/.."));
        // 期望输出: "/a/b/c"
    }
}

/**
You are given an absolute path for a Unix-style file system, which always 
begins with a slash '/'. Your task is to transform this absolute path into its 
simplified canonical path. 

 The rules of a Unix-style file system are as follows: 

 
 A single period '.' represents the current directory. 
 A double period '..' represents the previous/parent directory. 
 Multiple consecutive slashes such as '//' and '///' are treated as a single 
slash '/'. 
 Any sequence of periods that does not match the rules above should be treated 
as a valid directory or file name. For example, '...' and '....' are valid 
directory or file names. 
 

 The simplified canonical path should follow these rules: 

 
 The path must start with a single slash '/'. 
 Directories within the path must be separated by exactly one slash '/'. 
 The path must not end with a slash '/', unless it is the root directory. 
 The path must not have any single or double periods ('.' and '..') used to 
denote current or parent directories. 
 

 Return the simplified canonical path. 

 
 Example 1: 

 
 Input: path = "/home/" 
 

 Output: "/home" 

 Explanation: 

 The trailing slash should be removed. 

 Example 2: 

 
 Input: path = "/home//foo/" 
 

 Output: "/home/foo" 

 Explanation: 

 Multiple consecutive slashes are replaced by a single one. 

 Example 3: 

 
 Input: path = "/home/user/Documents/../Pictures" 
 

 Output: "/home/user/Pictures" 

 Explanation: 

 A double period ".." refers to the directory up a level (the parent directory).
 

 Example 4: 

 
 Input: path = "/../" 
 

 Output: "/" 

 Explanation: 

 Going one level up from the root directory is not possible. 

 Example 5: 

 
 Input: path = "/.../a/../b/c/../d/./" 
 

 Output: "/.../b/d" 

 Explanation: 

 "..." is a valid name for a directory in this problem. 

 
 Constraints: 

 
 1 <= path.length <= 3000 
 path consists of English letters, digits, period '.', slash '/' or '_'. 
 path is a valid absolute Unix path. 
 

 Related Topics String Stack 👍 5949 👎 1346

*/