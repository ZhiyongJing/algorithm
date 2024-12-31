package template;

import java.util.HashMap;
import java.util.Map;

public class TwoPointer {
    /**
     * 滑动窗口使用思路（寻找最长）
     * ————核心：左右双指针(L,R)在起始点，R向右逐位滑动循环
     * ————每次滑动过程中
     * 如果：窗内元素满足条件，R向右扩大窗口，并更新最优结果
     * 如果：窗内元素不满足条件，L向右缩小窗口
     * ————R到达结尾
     * <p>
     * 初始化 left，right，result，bestResult
     * while("右指针没有到结尾"){
     * 窗口扩大，加入right对应元素，更新当前result
     * while("result不满足要求"){
     * 窗口缩小，移除left对应元素，left右移
     * }
     * 更新最优结果bestResult
     * right++;
     * }
     * 返回bestResult
     */
    //算法应用，longest substring without dup characters
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> chars = new HashMap();

        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    /**
     * 滑动窗口使用思路（寻找最短）
     * ————核心：左右双指针(L,R)在起始点，R向右逐位滑动循环
     * ————每次滑动过程中
     * 如果：窗内元素满足条件，L向右缩小窗口，并更新最优结果
     * 如果：窗内元素不满足条件，R向右扩大窗口
     * ————R到达结尾
     * <p>
     * # 算法模版
     * 初始化 left，right，result，bestResult
     * while("右指针没有到结尾"){
     * 窗口扩大，加入right对应元素，更新当前result
     * while("result满足要求"){
     * 更新最优结果bestResult
     * 窗口缩小，移除left对应元素，left右移
     * }
     * right++;
     * }
     * 返回bestResult
     */
    //算法应用， 该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr]
    public int minSubArrayLen(int target, int[] nums) {
        // 初始化 left，right，result，bestResult
        // result是当前的currSum、bestResult为mixLength
        int left = 0, right = 0, currSum = 0, mixLength = 0;
        // 右指针没有到结尾
        while (right < nums.length) {
            // 窗口扩大，加入right对应元素，更新当前
            currSum += nums[right];
            // result满足要求
            while (currSum >= target) {
                // 更新最优结果mixLength
                if (right - left + 1 < mixLength || mixLength == 0) {
                    mixLength = right - left + 1;
                }
                // 窗口缩小，移除left对应元素，left右移
                currSum = currSum - nums[left];
                left++;
            }
            // right++
            right++;
        }
        // 返回mixLength
        return mixLength;
    }


    //算法应用，Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];

            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        // In case there is no solution, return {-1, -1}.
        return new int[]{-1, -1};
    }




    public static void main(String[] args) {
        System.out.println("hello world");
    }
}
