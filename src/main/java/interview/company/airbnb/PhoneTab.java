package interview.company.airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//Assume that Airbnb decided to enable search on user reviews. In order to make the search better you were requested to tag specific words in the review .
//Search team has given you a bunch of key value pairs where the keys denote words which may or may not present in a review and the value
// corresponds to the search metadata. Your task is to prepend the metadata tag and highlight the specific review word in brackets.
// You should make sure the original case is maintained in the result string.

/**
 * Test case #1 [ no confliction ]
 *
 * Input:
 *
 * Review:
 * "I visited San Francisco for work and stayed at Airbnb.
 * I really loved the city and the home where I stayed."
 *
 * Tags: {
 * "airbnb": "business",
 * "san francisco": "city",
 * }
 *
 * Output:
 * "I visited [city]{San Francisco} for work and stayed at [business]{Airbnb}.
 * I really loved the city and the home where I stayed."
 *
 * est case #2 [ with confliction ]
 *
 * Input
 *
 * Review:
 * "I travelled to San Francisco for work and stayed at Airbnb.
 * I really loved the city and the home where I stayed.
 * I stayed with San and Francisco.
 * They both were really good and san's hospitality was outstanding."
 *
 * Tags: {
 * "san": "person",
 * "francisco": "person",
 * "san francisco": "city",
 * "Airbnb": "business",
 * "city": "location",
 * }
 *
 * Output:
 * "I travelled to [city]{San Francisco} for work and stayed at [business]{Airbnb}.
 * I really loved the [location]{city} and the home where I stayed.
 * I stayed with [person]{San} and [person]{Francisco}.
 * They both were really good and [person]{san}'s hospitality was outstanding."
 */
// 假设 Airbnb 决定启用对用户评论的搜索功能。为使搜索更精确，需要标记评论中的特定单词。
// 搜索团队提供了一些键值对，键是可能出现在评论中的单词，值是搜索的元数据。
// 任务是为评论中的特定单词添加元数据标签，并用括号突出显示该单词，同时保持原始大小写。

public class PhoneTab {

    public static void main(String[] args) {
        // 创建Phonetab对象
        PhoneTab obj = new PhoneTab();

        // 测试用例1
        String s = "I visited San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed.";
        String[][] tags = {{"airbnb", "business"}, {"san francisco", "city"}};

        System.out.println(obj.prepareTags(s, tags)); // 输出结果

        System.out.println(" ");
        System.out.println(" ");

        // 测试用例2
        String s1 = "I travelled to San Francisco for work and stayed at Airbnb. I really loved the city and the home where I stayed. " +
                "I stayed with San and Francisco. They both were really good and san's hospitality was outstanding.";
        String[][] tags1 = {{"san", "person"},
                {"francisco", "person"},
                {"san francisco", "city"},
                {"Airbnb", "business"},
                {"city", "location"}};

        System.out.println(obj.prepareTags(s1, tags1)); // 输出结果
    }

    // 将元数据标签应用到字符串中
    private String prepareTags(String str, String[][] tags) {

        // 初始化映射，用于存储标签和其在names列表中的索引
        Map<String, Integer> map = new HashMap<>();
        List<String> names = new ArrayList<>();

        // 遍历每个标签对，如果标签元数据（tag[1]）不在映射中，则将其添加
        for (String[] tag : tags) {
            if (!map.containsKey(tag[1])) {
                map.put(tag[1], names.size()); // 标签与其索引映射
                names.add(tag[1]);             // 添加标签名到names列表
            }
        }

        // 创建Trie根节点
        Trie root = new Trie();

        // 将每个标签词条加入Trie中，并关联到其元数据的索引
        for (String[] tag : tags) {
            prepareTrie(root, tag[0].toLowerCase(), map.get(tag[1]));
        }

        // 构建输出字符串
        StringBuilder sb = new StringBuilder();
        int idx = -1;  // 存储当前匹配的标签索引
        int next = -1; // 存储下一个字符的索引

        // 遍历字符串中的每个字符
        for (int i = 0; i < str.length();) {
            int j = i;
            Trie node = root;

            // 在Trie中查找字符串的前缀匹配
            while (j < str.length() && node.c != null && node.c[Character.toLowerCase(str.charAt(j))] != null) {
                node = node.c[Character.toLowerCase(str.charAt(j++))];

                // 如果当前节点包含有效的标签索引
                if (node.idx != null) {
                    idx = node.idx; // 更新idx为当前标签的索引
                    next = j;       // 更新下一个字符的索引
                }
            }

            // 如果找到了标签
            if (idx != -1) {
                // 将标签信息和匹配的单词加到结果字符串中
                sb.append("[" + names.get(idx) + "]{"+ str.substring(i, next) + "}");
                idx = -1;  // 重置索引
                i = next;  // 更新i为下一个位置
            } else {
                // 否则将当前字符直接添加到结果字符串
                sb.append(str.charAt(i++));
            }
        }
        return sb.toString();
    }

    // 构建Trie树，用于存储标签和对应的元数据索引
    private void prepareTrie(Trie root, String tag, int index) {
        Trie node = root;

        // 遍历标签中的每个字符，构建路径
        for (char ch : tag.toCharArray()) {
            if (node.c == null) {
                node.c = new Trie[256]; // 初始化节点的子节点数组
            }
            if (node.c[ch] == null) {
                node.c[ch] = new Trie(); // 新建子节点
            }
            node = node.c[ch]; // 移动到下一个节点
        }
        node.idx = index; // 将标签的索引存储到最后的节点
    }
}

// Trie树节点类
class Trie {
    Trie[] c;       // 存储子节点的数组
    Integer idx;    // 存储标签的索引，用于识别标签类型
}
