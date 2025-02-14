package interview.company.amazon;

import java.util.Arrays;

public class TwoSumClosest {
    /**
     * 在两个数组中各选一个数，使得它们的和尽可能接近 target，但不超过 target
     *
     * @param nums1 第一个数组
     * @param nums2 第二个数组
     * @param target 目标值
     * @return 最大且不超过 target 的两数之和，如果没有合法组合则返回 -1
     */
    public static int twoSumClosest(int[] nums1, int[] nums2, int target) {
        // 对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 初始化双指针：i 指向 nums1 起始，j 指向 nums2 末尾
        int i = 0, j = nums2.length - 1;
        int best = -1; // 保存当前最接近但不超过 target 的和

        // 双指针遍历：当 i < nums1.length 且 j >= 0 时循环
        while (i < nums1.length && j >= 0) {
            int sum = nums1[i] + nums2[j];
            // 如果当前和小于等于 target，则更新 best，并尝试增大和（移动 i）
            if (sum <= target) {
                best = Math.max(best, sum);
                i++;
            } else {
                // 否则当前和超过 target，尝试减小和（移动 j）
                j--;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        // 测试用例1
        int[] nums1 = {1, 3, 5, 8};
        int[] nums2 = {2, 4, 6, 9};
        int target = 10;
        // 可能的合法组合包括 (1,9) 或 (8,2) 等，返回 10
        System.out.println(twoSumClosest(nums1, nums2, target)); // 输出 10

        // 测试用例2
        int[] nums3 = {7, 2, 5};
        int[] nums4 = {3, 8, 1};
        target = 9;
        // 合法组合例如 (7,1) = 8 或 (2,3) = 5，此处最优为 8
        System.out.println(twoSumClosest(nums3, nums4, target)); // 输出 8

        // 测试用例3：不存在不超过 target 的组合
        int[] nums5 = {10, 11};
        int[] nums6 = {12, 13};
        target = 20;
        // 没有合法组合，返回 -1
        System.out.println(twoSumClosest(nums5, nums6, target)); // 输出 -1
    }
}

