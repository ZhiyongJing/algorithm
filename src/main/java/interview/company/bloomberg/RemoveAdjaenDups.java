package interview.company.bloomberg;

public class RemoveAdjaenDups {
    /**
     * 使用计数数组模拟消除过程：每遇到一个字符，就将其追加到 StringBuilder 中，
     * 同时记录当前字符连续出现的次数（存入 count 数组）。当连续出现的次数达到或超过 3 时，
     * 立即将这一整段字符从 StringBuilder 中删除，模拟“消除”操作。
     *
     * 注意：这种做法是“贪心消除”，即在扫描过程中只要发现连续数量 >= 3 就马上消除，
     * 消除后左右两边的字符自动靠拢，再继续扫描后续字符。最终返回的是一次扫描消除完毕后的结果。
     *
     * 例如：输入 "abbbccca"
     *   - 先处理 'a'，追加后 sb = "a"；
     *   - 处理接下来的三个 'b'，当 sb 形成 "abbb" 时发现末尾连续 'b' 数量为 3，于是删除这3个 'b'，sb 恢复为 "a"；
     *   - 接着处理三个 'c'，追加后 sb = "accc"，连续 'c' 数量为 3，消除后 sb 变为 "a"；
     *   - 最后处理尾部的 'a'，与 sb 末尾 'a' 合并形成 "aa"，由于数量不足 3，无法消除。
     *   最终返回 "aa"。
     *
     * @param s 原始糖果字符串，每个字符代表一种糖果
     * @return 消除所有连续 >= 3 个相同糖果后的最终结果字符串
     */
        // 使用 StringBuilder 模拟栈来存储最终未消除的字符
        public String removeDuplicates(String s, int k) {
            // 使用 StringBuilder 来修改字符串
            StringBuilder sb = new StringBuilder(s);
            // 创建一个计数数组，记录每个字符连续出现的次数
            int count[] = new int[sb.length()];

            // 遍历字符串中的每个字符
            for (int i = 0; i < sb.length(); ++i) {
                // 如果是第一个字符或当前字符与前一个字符不同，将计数置为1
                if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                    count[i] = 1;
                } else {
                    // 如果当前字符与前一个字符相同，计数加1
                    count[i] = count[i - 1] + 1;

                    // 调试输出当前字符串和计数数组

                    // 当某个字符连续出现次数达到 k 时，将其从字符串中删除
                    if (count[i] >= k && (i + 1 == sb.length() || sb.charAt(i) != sb.charAt(i+1) )

                    ) {
                        sb.delete(i - count[i] + 1, i + 1);
//                        System.out.println(sb + ", sb length: " + sb.length());
                        // 删除字符后，重置索引位置
                        i = i - count[i];
                    }
                }
//                System.out.println(Arrays.toString(count));

            }
            // 返回最终的字符串
            return sb.toString();
        }


    public static void main(String[] args) {
        RemoveAdjaenDups solution = new RemoveAdjaenDups();

        // 测试样例 1：题目给出的示例
        String test1 = "abbbccca";
        // 解析过程：
        // "a" -> "ab" -> "abb" -> "abbb" (末尾 "bbb" 连续 3 个，消除后变 "a")
        // 接着 "a" + "c" -> "ac" -> "acc" -> "accc" (末尾 "ccc" 连续 3 个，消除后变 "a")
        // 最后 "a" + "a" -> "aa"（数量不足 3，无法消除）
        System.out.println("Input: " + test1 + ", Output: " + solution.removeDuplicates(test1, 3)); // 期望输出 "aa"

        // 测试样例 2：消除多个连续块的情况
        String test2 = "aaabbbc";
        // 解析过程：
        // "aaa" 连续 3 个被消除；接着 "bbb" 连续 3 个被消除；剩下 "c" 无法消除
        System.out.println("Input: " + test2 + ", Output: " + solution.removeDuplicates(test2, 3)); // 期望输出 "c"

        // 测试样例 3：相邻的同字符块因中间消除而合并（消除顺序可能影响最终结果）
        String test3 = "aabbbaa";
        // 解析过程：
        // 初始 "aa"；遇到 "bbb" 连续 3 个消除后，sb 变为 "aa"；
        // 处理下一个 'a' 使得 "aa" 变 "aaa"（连续 3 个，消除），sb 变为空；
        // 最后处理尾部的 'a'，sb 变为 "a"
        System.out.println("Input: " + test3 + ", Output: " + solution.removeDuplicates(test3, 3)); // 期望输出 "a"（基于贪心消除策略）

        // 测试样例 4：相邻的同字符块因中间消除而合并（消除顺序可能影响最终结果）
        String test4 = "aabbbaad";
        // 解析过程：
        // 初始 "aa"；遇到 "bbb" 连续 3 个消除后，sb 变为 "aa"；
        // 处理下一个 'a' 使得 "aa" 变 "aaa"（连续 3 个，消除），sb 变为空；
        // 最后处理尾部的 'a'，sb 变为 "a"
        System.out.println("Input: " + test4 + ", Output: " + solution.removeDuplicates(test4, 3)); // 期望输出 "a"（基于贪心消除策略）

        // 测试样例 5：相邻的同字符块因中间消除而合并（消除顺序可能影响最终结果）
        String test5 = "abbbbccca";
        // 解析过程：
        // 初始 "aa"；遇到 "bbb" 连续 3 个消除后，sb 变为 "aa"；
        // 处理下一个 'a' 使得 "aa" 变 "aaa"（连续 3 个，消除），sb 变为空；
        // 最后处理尾部的 'a'，sb 变为 "a"
        System.out.println("Input: " + test5 + ", Output: " + solution.removeDuplicates(test5, 3)); // 期望输出 "a"（基于贪心消除策略）
    }
//    coding部分（40mins）
//    给你一个字符串代表一行消糖果游戏。该如何消掉所有 >= 3个以上的糖果？最后返还一个字符代表消完后的结果。
//    ex: "abbbbccca" => "aa"。先消ccc -> bbbb, 剩下aa消不掉
    }
