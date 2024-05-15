package interview.company.amazon;

/**
 *@Question:  949. Largest Time for Given Digits
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 15.629999999999999%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(1)
 */

/**
 * 这个问题的目标是从给定的数字中构造出最大的24小时制格式的时间。
 *
 * ### 解题思路：
 *
 * #### 解决方案1：通过枚举进行排列
 *
 * 1. 遍历所有可能的4个数字的排列组合。
 * 2. 对于每个排列，将前两个数字表示小时，后两个数字表示分钟。
 * 3. 检查每个排列是否有效，即小时不超过23且分钟不超过59。
 * 4. 维护一个最大时间变量，存储找到的最大时间。
 * 5. 返回找到的最大时间。
 *
 * #### 解决方案2：通过回溯进行排列
 *
 * 1. 使用回溯算法生成所有可能的4个数字的排列组合。
 * 2. 对于每个排列，将前两个数字表示小时，后两个数字表示分钟。
 * 3. 检查每个排列是否有效，即小时不超过23且分钟不超过59。
 * 4. 维护一个最大时间变量，存储找到的最大时间。
 * 5. 返回找到的最大时间。
 *
 * ### 时间复杂度分析：
 *
 * #### 解决方案1：
 *
 * - 生成所有可能的排列组合需要 O(4!) = O(24) 的时间复杂度。
 * - 对于每个排列，检查是否有效需要 O(1) 的时间。
 * - 因此，总时间复杂度为 O(1)。
 *
 * #### 解决方案2：
 *
 * - 生成所有可能的排列组合需要 O(4!) = O(24) 的时间复杂度。
 * - 对于每个排列，检查是否有效需要 O(1) 的时间。
 * - 回溯过程的时间复杂度为 O(4!) = O(24)。
 * - 因此，总时间复杂度为 O(1)。
 *
 * ### 空间复杂度分析：
 *
 * - 解决方案1和解决方案2都只使用了常数额外空间。
 * - 因此，空间复杂度为 O(1)。
 *
 * 综上所述，这两种解决方案的时间复杂度均为 O(1)，空间复杂度也均为 O(1)。
 */

public class LeetCode_949_LargestTimeForGivenDigits{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int max_time = -1;

        // 解决方案1：通过枚举进行排列
        public String largestTimeFromDigits(int[] A) {
            this.max_time = -1;

            for (int i1 = 0; i1 < A.length; ++i1)
                for (int i2 = 0; i2 < A.length; ++i2)
                    for (int i3 = 0; i3 < A.length; ++i3) {
                        // 跳过重复的元素
                        if (i1 == i2 || i2 == i3 || i1 == i3)
                            continue;
                        // 索引的总和为0 + 1 + 2 + 3 = 6。
                        int i4 = 6 - i1 - i2 - i3;
                        int [] perm = {A[i1], A[i2], A[i3], A[i4]};
                        // 检查排列是否可以形成一个时间
                        validateTime(perm);
                    }
            if (this.max_time == -1)
                return "";
            else
                return String.format("%02d:%02d", max_time / 60, max_time % 60);
        }

        protected void validateTime(int[] perm) {
            int hour = perm[0] * 10 + perm[1];
            int minute = perm[2] * 10 + perm[3];
            if (hour < 24 && minute < 60)
                this.max_time = Math.max(this.max_time, hour*60 + minute);
        }

        // 解决方案2：通过回溯进行排列
//        private int max_time = -1;

        public String largestTimeFromDigits2(int[] A) {
            this.max_time = -1;
            permutate(A, 0);
            if (this.max_time == -1)
                return "";
            else
                return String.format("%02d:%02d", max_time / 60, max_time % 60);
        }

        protected void permutate(int[] array, int start) {
            if (start == array.length) {
                this.build_time(array);
                return;
            }
            for (int i = start; i < array.length; ++i) {
                this.swap(array, i, start);
                this.permutate(array, start + 1);
                this.swap(array, i, start);
            }
        }

        protected void build_time(int[] perm) {
            int hour = perm[0] * 10 + perm[1];
            int minute = perm[2] * 10 + perm[3];
            if (hour < 24 && minute < 60)
                this.max_time = Math.max(this.max_time, hour * 60 + minute);
        }

        protected void swap(int[] array, int i, int j) {
            if (i != j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_949_LargestTimeForGivenDigits().new Solution();
        // 测试用例
        int[] digits1 = {1, 2, 3, 4};
        int[] digits2 = {5, 5, 5, 5};
        System.out.println("最大时间1: " + solution.largestTimeFromDigits(digits1)); // 预期输出: "23:41"
        System.out.println("最大时间2: " + solution.largestTimeFromDigits(digits2)); // 预期输出: ""
    }
}


/**
Given an array arr of 4 digits, find the latest 24-hour time that can be made 
using each digit exactly once. 

 24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM 
is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59
. 

 Return the latest 24-hour time in "HH:MM" format. If no valid time can be made,
 return an empty string. 

 
 Example 1: 

 
Input: arr = [1,2,3,4]
Output: "23:41"
Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14
:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" 
is the latest.
 

 Example 2: 

 
Input: arr = [5,5,5,5]
Output: ""
Explanation: There are no valid 24-hour times as "55:55" is not valid.
 

 
 Constraints: 

 
 arr.length == 4 
 0 <= arr[i] <= 9 
 

 Related Topics Array String Enumeration 👍 702 👎 1052

*/