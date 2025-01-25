package template;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoPointer {
    /**
     * 滑动窗口使用思路（寻找最长无重复子串）
     * ————核心：使用左右双指针（L, R），R 右移扩展窗口，L 右移缩小窗口
     * ————每次滑动过程中：
     *      - 如果窗口内元素满足条件，R 右移，并更新最长结果
     *      - LOOP如果窗口内元素不满足条件，L 右移缩小窗口，直到满足条件
     * ————直到 R 到达结尾，返回最长子串长度
     *
     * # 算法模版
     *      1. 初始化 left，right，condition，bestResult；
     *      2. 初始化 data 用于计算bestResult(如果left, right, condition可用于计算bestResult, 此步可省)
     *      while("右指针没有到结尾"){
     *          1. 添加right对应元素到当前condition；
     *          2. 添加right对应元素到用于计算bestResult的data(基于问题，可省)；
     *          while("带有right对应元素的condition不满足要求"){
     *              1. condition移除left对应元素；
     *              2. data移除left对应元素(基于问题)；
     *              3. left右移；
     *          }
     *          1. 更新最优结果bestResult
     *          2. right++;
     *      }
     *      返回bestResult
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> chars = new HashMap<>(); // 记录字符及其出现次数
        int left = 0, right = 0, res = 0; // 初始化左指针、右指针、最长长度

        while (right < s.length()) {
            char r = s.charAt(right); // 获取右指针当前字符
            chars.put(r, chars.getOrDefault(r, 0) + 1); // 更新字符频率

            //不满足要求：当窗口内存在重复字符时，移动左指针直到窗口内无重复
            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            // 更新最大长度
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 滑动窗口使用思路（寻找最短子数组满足和 ≥ target）
     * ————核心：使用左右双指针（L, R），R 右移扩展窗口，L 右移缩小窗口
     * ————每次滑动过程中：
     *      - 如果result不满足要求，右移 R 扩展窗口
     *      - LOOP如果result满足要求，更新最优结果，并右移 L 缩小窗口
     * ————直到 R 到达结尾，返回最小长度
     * # 算法模版
     *
     *      初始化 left，right，condition，bestResult；
     *      初始化 data 用于计算bestResult(如果left, right, condition可用于计算bestResult, 此步骤可省)
     *
     *      while("右指针没有到结尾"){
     *          1. 添加right对应元素到当前condition；
     *          2. 添加right对应元素到用于计算bestResult的data(基于问题，可省)；
     *          while("带有right对应元素的condition满足要求"){
     *              1. 更新最优结果bestResult；
     *              2. condition移除left对应元素；
     *              3. data移除left对应元素(基于问题，可省)；
     *              4. left右移；
     *          }
     *          right++;
     *      }
     *
     *      返回bestResult；
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, currSum = 0, minLength = Integer.MAX_VALUE; // 初始化变量

        while (right < nums.length) {
            currSum += nums[right]; // 窗口右扩展

            // 满足要求：当窗口内的和满足 ≥ target 时，尝试缩小窗口
            while (currSum >= target) {
                minLength = Math.min(minLength, right - left + 1); // 更新最小长度
                currSum -= nums[left]; // 移除左端元素
                left++;
            }
            right++;
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength; // 若无符合条件的子数组，返回 0
    }

    /**
     * 相向双指针（Two Sum II - 输入数组已排序）
     * ————核心：使用双指针，一个指向数组头部，一个指向尾部
     * ————每次检查当前元素和：
     *      - 若 `sum == target`，返回当前索引（+1）
     *      - 若 `sum < target`，移动左指针 `low` 右移
     *      - 若 `sum > target`，移动右指针 `high` 左移
     * ————直到找到结果或指针相遇，返回结果
     */
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1; // 初始化双指针

        while (low < high) {
            int sum = numbers[low] + numbers[high]; // 计算当前指针元素的和

            if (sum == target) {
                return new int[]{low + 1, high + 1}; // 题目要求索引从 1 开始
            } else if (sum < target) {
                low++; // 移动左指针，使和增大
            } else {
                high--; // 移动右指针，使和减小
            }
        }

        return new int[]{-1, -1}; // 若无解，返回默认值
    }

    public static void main(String[] args) {
        TwoPointer tp = new TwoPointer();

        // 测试滑动窗口最长无重复子串
        String s1 = "abcabcbb";
        System.out.println("Longest substring without duplicate characters: " + tp.lengthOfLongestSubstring(s1)); // 预期输出: 3 ("abc")

        // 测试滑动窗口最短子数组
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println("Minimum subarray length with sum >= target: " + tp.minSubArrayLen(target1, nums1)); // 预期输出: 2 ([4,3])

        // 测试相向双指针（Two Sum II）
        int[] numbers = {2, 7, 11, 15};
        int target2 = 9;
        System.out.println("Two Sum indices: " + Arrays.toString(tp.twoSum(numbers, target2))); // 预期输出: [1, 2]
    }
}
