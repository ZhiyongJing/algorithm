package interview.company.bloomberg;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 目标：求一天开盘到收盘期间，某只股票在任意5分钟窗口内的最大交易量
 *
 * 示例：
 * 给定如下交易记录（格式为：股票代码, 时间, 价格, 交易量）：
 *   ("GOOGLE", "900", "130.1", "120")
 *   ("GOOGLE", "901", "130.1", "100")
 *   ("GOOGLE", "902", "130.1", "90")
 *   ("GOOGLE", "904", "130.1", "150")
 * 分析：将这些交易按时间排序后，利用滑动窗口法求连续窗口内的交易量总和，
 *       其中窗口定义为：任意记录的最早时间与最新时间之差不超过4分钟（这样包含了连续5分钟）。
 *       例如，窗口从900到904这5分钟内，交易量总和 = 120 + 100 + 90 + 150 = 460。
 *
 * 算法步骤：
 * 1. 如果交易记录未按时间排序，则先对记录按交易时间排序。
 * 2. 利用滑动窗口：设置两个指针 left 和 right，遍历交易记录：
 *    - 将右指针指向的新记录的交易量累加到当前窗口总和。
 *    - 检查窗口内最早记录（left指针）的时间和当前记录（right指针）的时间差：
 *         如果差值 > 4（即窗口长度超过5分钟），则从窗口中移除左端记录（并减去其交易量），left 指针右移。
 *    - 每次更新窗口后，记录窗口内交易量的最大值。
 * 3. 返回最大交易量总和。
 *
 * 时间复杂度：
 *    - 排序: O(n log n)，其中 n 为交易记录数。
 *    - 滑动窗口遍历: O(n)（每个记录最多入窗口和出窗口各一次）。
 *    - 总体复杂度：O(n log n)。
 *
 * 空间复杂度：
 *    - 除了存储交易记录所需的 O(n) 空间外，额外使用常数空间，因此空间复杂度为 O(n)。
 */
public class MaxTradingVolumeInWindow {

    // 定义一个交易记录类
    static class Transaction {
        String stock;  // 股票代码
        int time;      // 交易时间（例如900表示9:00，单位为分钟或者格式化后的整数）
        double price;  // 交易价格
        int volume;    // 交易量

        public Transaction(String stock, int time, double price, int volume) {
            this.stock = stock;
            this.time = time;
            this.price = price;
            this.volume = volume;
        }
    }

    /**
     * 计算给定交易记录中任意5分钟窗口内的最大交易量总和
     *
     * @param transactions 交易记录列表（假设所有记录均为同一股票）
     * @return 任意5分钟窗口内的最大交易量总和
     */
    public static int maxVolumeIn5MinWindow(List<Transaction> transactions) {
        // 1. 对交易记录按时间进行排序
        transactions.sort(Comparator.comparingInt(t -> t.time));

        int maxVolume = 0;
        int currentVolume = 0;
        int left = 0;
        // 2. 利用滑动窗口遍历所有交易记录
        for (int right = 0; right < transactions.size(); right++) {
            // 累加当前记录的交易量到窗口内
            currentVolume += transactions.get(right).volume;
            // 当窗口内记录的时间跨度超过 5 分钟（差值 > 4），移动左指针并减去对应交易量
            while (transactions.get(right).time - transactions.get(left).time > 4) {
                currentVolume -= transactions.get(left).volume;
                left++;
            }
            // 更新最大交易量总和
            maxVolume = Math.max(maxVolume, currentVolume);
        }
        return maxVolume;
    }

    public static void main(String[] args) {
        // 示例数据：股票 GOOGLE 的交易记录
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("GOOGLE", 900, 130.1, 120));
        transactions.add(new Transaction("GOOGLE", 901, 130.1, 100));
        transactions.add(new Transaction("GOOGLE", 902, 130.1, 90));
        transactions.add(new Transaction("GOOGLE", 904, 130.1, 150));

        int result = maxVolumeIn5MinWindow(transactions);
        System.out.println("Maximum trading volume in any 5-minute window: " + result);
        // 预期输出：460
    }
}
