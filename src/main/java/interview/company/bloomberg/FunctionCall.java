package interview.company.bloomberg;

import java.util.LinkedList;
import java.util.Queue;

public class FunctionCall {

    // 维护一个队列，保存最近 5 秒的调用时间戳
    private static final Queue<Long> recentCalls = new LinkedList<>();
    // 5 秒对应的毫秒数
    private static final long TIME_WINDOW = 5000;

    /**
     * 如果在最近 5 秒内调用总次数已经达到 10 次，则返回 true，否则返回 false。
     */
    public static boolean called() {
        long now = System.currentTimeMillis();
        // 将当前调用时间戳加入队列
        recentCalls.add(now);

        // 移除超过 5 秒窗口之外的时间戳
        while (!recentCalls.isEmpty() && (now - recentCalls.peek()) > TIME_WINDOW) {
            recentCalls.poll();
        }

        // 判断在 5 秒内的调用次数是否达到 10 次
        return recentCalls.size() >= 10;
    }

    public static void main(String[] args) throws InterruptedException {
        // 简单测试用例演示：

        // 连续 9 次快速调用，尚未到达 10 次
        for (int i = 0; i < 9; i++) {
            System.out.println("Call " + (i + 1) + ", result = " + called());
        }

        // 第 10 次紧接着调用，预期此时返回 true
        System.out.println("Call 10, result = " + called());

        // 稍作等待，让 5 秒时间窗内的调用过期
        Thread.sleep(6000);

        // 第 11 次调用时，之前的调用大多已过期，调用队列小于 10，因此返回 false
        System.out.println("Call 11 (after 6s), result = " + called());
    }
}
