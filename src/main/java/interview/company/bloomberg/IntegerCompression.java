package interview.company.bloomberg;

import java.util.List;

public class IntegerCompression {
    // 定义主类Solution
    // 第一部分：题目描述
// 本题要求对一个整数进行编码，编码规则为：对于数字中连续相同的数字，
// 统计该数字出现的次数，并将“次数”和“该数字”依次拼接起来，构成编码后的结果。
// 例如，输入整数为 1113344，对应的连续数字分组为 [111]、[33] 和 [44]，
// 分别编码为 31（表示数字 1 出现 3 次）、23（数字 3 出现 2 次）和 24（数字 4 出现 2 次），
// 最终输出编码结果为 312324。
// 题目要求在实现过程中不能将整数转换为字符串，而必须使用数学运算来提取和处理数字。

// 第二部分：解题思路详细说明
// 1. 从整数中提取数字（按从左到右的顺序）
//    - 由于不能将整数转换为字符串，所以需要使用数学方法逐位提取。
//    - 首先确定整数的位数，可以通过计算最大的 10 的幂次 divisor，使得 divisor <= n。
//      例如，对于 1113344，最大的 divisor 为 1000000，对应数字的最高位。
//    - 然后使用除法和取模操作依次提取每一位数字：digit = n / divisor，
//      并更新 n = n % divisor 以及 divisor = divisor / 10，直到所有数字提取完毕。
//    - 这样就能得到数字序列：1, 1, 1, 3, 3, 4, 4。
// 2. 对提取的数字进行分组并编码
//    - 初始化一个变量 prev，用来保存当前正在统计的数字组的数字，
//      并设置一个计数器 count 用于统计该数字连续出现的次数（初始值设为 1）。
//    - 遍历后续每个数字：
//         如果当前数字与 prev 相同，则说明处于同一连续组中，将 count 增加 1；
//         如果不同，则表明前一组已结束，需要将前一组进行编码。
//    - 对于结束的组，其编码规则为：编码值 = (count * 10) + prev。
//      例如，当从 111 过渡到数字 3 时，之前的组为数字 1 连续出现 3 次，编码为 31。
//    - 编码完成后，更新 prev 为当前数字，并将 count 重置为 1，继续处理后面的数字。
//    - 循环结束后，别忘了对最后一组数字进行同样的编码操作。
// 3. 拼接各段编码构成最终结果
//    - 假设每一段编码都是两位数（第一位为计数，第二位为数字），
//      可以通过将当前结果乘以 100，再加上新的组编码来拼接成最终的结果。
//    - 举例说明：
//         初始结果设为 0，处理第一组 [111] 得到编码 31，此时结果 = 0 * 100 + 31 = 31；
//         处理第二组 [33] 得到编码 23，此时结果 = 31 * 100 + 23 = 3123；
//         处理第三组 [44] 得到编码 24，此时结果 = 3123 * 100 + 24 = 312324。
//    - 最终返回拼接后的整数 312324，即为编码后的结果。
// 4. 注意事项
//    - 全程禁止使用字符串转换，必须利用数学运算（除法、取模和乘法）来提取和处理数字。
//    - 在拼接结果时，要考虑每组编码的位数，确保拼接过程中不会丢失位数信息。

// 第三部分：时间和空间复杂度说明
// 时间复杂度：O(d)
//    - 其中 d 为输入整数的位数。提取每一位、统计连续数字以及拼接编码的操作都只需遍历一次数字序列。
// 空间复杂度：O(1)
//    - 算法仅使用了常数级别的额外变量（如 divisor、prev、count 和 result 等），没有使用额外的数据结构。


    // 主方法：程序入口，用于测试encodeNumber方法
    public static void main(String[] args) {
        // 定义输入整数，题目示例中输入为1113344
        int input = 1113344;
        // 调用encodeNumber方法对输入整数进行编码，并将结果存储在encoded变量中
        int encoded = encodeNumber(input);
        // 输出编码后的结果到控制台
        System.out.println(encoded);
    }

    // 定义静态方法encodeNumber，该方法用于对输入整数按照题目要求进行编码
    public static int encodeNumber(int n) {
        // 定义变量divisor，初始值设为1，用于计算最高位的除数
        int divisor = 1;
        // 定义临时变量temp，用于辅助计算n的位数
        int temp = n;
        // 当temp大于等于10时，不断扩大divisor，直到获得最高位的除数
        while (temp / 10 > 0) {
            // 将divisor乘以10，逐步逼近n的最高位
            divisor *= 10;
            // 将temp除以10，去掉当前最低位数字
            temp /= 10;
        }
        System.out.println("current divisor" + divisor);

        // 定义变量prev，用于存储当前连续数字组的数字，初始值设为-1表示尚未设置
        int prev = -1;
        // 定义变量count，用于统计当前连续数字组中相同数字的出现次数
        int count = 0;
        // 创建一个集合groups，用于存储每一组连续数字编码后的结果（编码方式为count*10 + 数字）
        List<Integer> groups = new java.util.ArrayList<>();

        // 使用while循环遍历输入整数的每一位，直到divisor变为0（表示所有数字均已处理）
        while (divisor > 0) {
            // 通过除法运算获得当前最高位的数字
            int digit = n / divisor;
            // 使用取模运算去掉已处理的最高位，更新n为剩余部分
            n = n % divisor;
            // 将divisor除以10，准备提取下一个数字
            divisor /= 10;
            // 如果prev尚未初始化（即第一次处理数字）
            if (prev == -1) {
                // 将当前提取的数字赋值给prev，作为第一个连续数字组的数字
                prev = digit;
                // 初始化当前组的计数器为1，因为当前数字已出现一次
                count = 1;
            } else {
                // 如果当前数字与prev相同，说明属于同一连续组
                if (digit == prev) {
                    // 增加当前组中数字的计数器
                    count++;
                } else {
                    // 如果当前数字与prev不同，则说明前一连续组结束
                    // 将前一组的编码结果计算为：计数器count乘以10再加上数字prev
                    groups.add(count * 10 + prev);
                    // 更新prev为当前数字，开启新的连续数字组
                    prev = digit;
                    // 重置计数器count为1，因为新组中已出现当前数字一次
                    count = 1;
                }
            }
        }
        // 当所有数字处理完毕后，将最后一组的编码结果加入groups集合中
        groups.add(count * 10 + prev);

        // 定义变量result，用于存储最终拼接所有编码组后得到的结果
        int result = 0;
        // 遍历集合groups中存储的每一组编码结果
        for (int encoded : groups) {
            // 定义变量digits用于计算当前编码结果encoded的位数（以便正确拼接）
            int digits = 0;
            // 定义临时变量tempEncoded保存encoded的值，用于计算位数
            int tempEncoded = encoded;
            // 使用while循环计算encoded的位数：不断将tempEncoded除以10，直到其值为0
            while (tempEncoded > 0) {
                // 每次循环，位数计数器digits自增1
                digits++;
                // 更新tempEncoded，去掉最低位数字
                tempEncoded /= 10;
            }
            // 将result左移digits位（乘以10的digits次方），再加上当前编码encoded，实现数字拼接
            result = result * (int) Math.pow(10, digits) + encoded;
        }

        // 返回最终拼接得到的编码结果
        return result;
    }
}

