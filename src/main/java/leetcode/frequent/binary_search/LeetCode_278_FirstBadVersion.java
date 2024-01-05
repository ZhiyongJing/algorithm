package leetcode.frequent.binary_search;

/**
  *@Question:  278. First Bad Version     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 54.38000000000001%      
  *@Time  Complexity: O(logN)
  *@Space Complexity: O(1)
 */

/**
 * 这个算法是使用二分查找来查找第一个错误的版本。以下是算法的详细思路：
 *
 * ### 算法思路：
 *
 * 1. 初始化左指针 `left` 为1，右指针 `right` 为总版本数 `n`。
 *
 * 2. 使用二分查找，计算中间版本号 `mid`。
 *
 * 3. 调用 `isBadVersion(mid)` 判断中间版本是否是错误的版本：
 *
 *    - 如果是错误的版本，说明错误的版本在当前 `mid` 的左侧或者就是 `mid`，将右指针 `right` 缩小到 `mid`。
 *
 *    - 如果不是错误的版本，说明错误的版本在 `mid` 的右侧，将左指针 `left` 移动到 `mid + 1`。
 *
 * 4. 重复步骤 2 和 3，直到左指针 `left` 等于右指针 `right`。此时，`left` 或 `right` 就是第一个错误的版本的编号。
 *
 * ### 时间复杂度：
 *
 * - 由于每一步都将搜索范围缩小一半，因此时间复杂度为 O(logN)，其中 N 是总版本数。
 *
 * ### 空间复杂度：
 *
 * - 空间复杂度为 O(1)，因为只使用了常数级别的变量。
 */

public class LeetCode_278_FirstBadVersion {

    //leetcode submit region begin(Prohibit modification and deletion)
    /* The isBadVersion API is defined in the parent class VersionControl.
       boolean isBadVersion(int version); */

    public class Solution extends VersionControl {
        /**
         * 查找第一个错误的版本
         *
         * @param n 版本总数
         * @return 第一个错误的版本号
         */
        public int firstBadVersion(int n) {
            int left = 1;
            int right = n;

            while (left + 1 < right) {
                // 计算中间版本号
                int mid = left + (right - left) / 2;

                // 调用isBadVersion函数判断中间版本是否是错误的版本
                if (isBadVersion(mid)) {
                    // 如果是错误的版本，将搜索范围缩小到左侧
                    right = mid;
                } else {
                    // 如果不是错误的版本，将搜索范围缩小到右侧
                    left = mid;
                }
            }
            if(isBadVersion(left)) return left;

            // 最终left和right相等，返回其中一个即可
            return right;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_278_FirstBadVersion().new Solution();

        // 测试代码
        // 注意：isBadVersion 的实际实现应在测试时提供
        int totalVersions = 5;
        int firstBadVersion = 4;

        // 预期输出: 4
        System.out.println(solution.firstBadVersion(totalVersions));
    }

    // 测试用的 VersionControl 类的实现
    static class VersionControl {
        boolean isBadVersion(int version) {
            // 实际实现应根据题目要求进行设置
            return version >= 4;
        }
    }
}

/**
You are a product manager and currently leading a team to develop a new product.
 Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions 
after a bad version are also bad. 

 Suppose you have n versions [1, 2, ..., n] and you want to find out the first 
bad one, which causes all the following ones to be bad. 

 You are given an API bool isBadVersion(version) which returns whether version 
is bad. Implement a function to find the first bad version. You should minimize 
the number of calls to the API. 

 
 Example 1: 

 
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.
 

 Example 2: 

 
Input: n = 1, bad = 1
Output: 1
 

 
 Constraints: 

 
 1 <= bad <= n <= 2³¹ - 1 
 

 Related Topics Binary Search Interactive 👍 8139 👎 3221

*/
