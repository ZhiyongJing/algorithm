package leetcode.question.two_pointer;

/**
 *@Question:  26. Remove Duplicates from Sorted Array
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 82.4%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(1)
 */

/**
 * ### 题目说明
 *
 * 题目要求从一个有序数组中移除重复的元素，并返回去重后的数组长度。去重操作必须在原数组上进行，即不能使用额外的数组空间。返回的结果应包括去重后的数组长度和修改后的数组内容（在数组的前 `k` 个位置，其中 `k` 是新的数组长度）。
 *
 * ### 解题思路
 *
 * 为了高效地解决这个问题，可以使用双指针技术：
 *
 * 1. **定义两个指针**：
 *    - `insertIndex`：用于指示下一个应该插入的位置。初始化为1，因为第一个元素总是唯一的。
 *    - `i`：用于遍历数组的指针，从第二个元素（索引为1）开始。
 *
 * 2. **遍历数组**：
 *    - 从 `i = 1` 开始，逐个检查数组元素。
 *    - 对于每个 `nums[i]`，检查它是否与前一个元素 `nums[i - 1]` 相同。
 *    - 如果 `nums[i]` 和 `nums[i - 1]` 不同，则将 `nums[i]` 赋值给 `nums[insertIndex]`，并将 `insertIndex` 加1。这意味着在去重后，`insertIndex` 前的元素都是唯一的。
 *
 * 3. **最终结果**：
 *    - `insertIndex` 的值即为去重后数组的长度。
 *    - 数组的前 `insertIndex` 个元素为去重后的数组内容。
 *
 * ### 时间复杂度和空间复杂度
 *
 * - **时间复杂度**：O(N)，其中 N 是数组的长度。算法仅需遍历一次数组，因此时间复杂度为线性。
 * - **空间复杂度**：O(1)。算法只使用了常数级别的额外空间，即用于指示位置的几个变量。因此空间复杂度为常数级别。
 */

public class LeetCode_26_RemoveDuplicatesFromSortedArray {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeDuplicates(int[] nums) {
            // 初始化插入索引为1，表示下一个存储唯一元素的位置
            int insertIndex = 1;

            // 从数组的第二个元素开始遍历
            for (int i = 1; i < nums.length; i++) {
                // 如果当前元素与前一个元素不同，则更新insertIndex位置的元素
                if (nums[i - 1] != nums[i]) {
                    // 将当前元素存储到insertIndex位置
                    nums[insertIndex] = nums[i];
                    // 插入索引加1，准备存储下一个唯一元素
                    insertIndex++;
                }
            }

            // 返回唯一元素的个数，即去重后的数组长度
            return insertIndex;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_26_RemoveDuplicatesFromSortedArray().new Solution();

        // 测试用例
        int[] nums = {1, 1, 2, 2, 3};
        int length = solution.removeDuplicates(nums);

        // 打印结果
        System.out.println("去重后的数组长度为：" + length);
        System.out.print("去重后的数组为：");
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}

/**
Given an integer array nums sorted in non-decreasing order, remove the 
duplicates in-place such that each unique element appears only once. The relative order 
of the elements should be kept the same. Then return the number of unique 
elements in nums. 

 Consider the number of unique elements of nums to be k, to get accepted, you 
need to do the following things: 

 
 Change the array nums such that the first k elements of nums contain the 
unique elements in the order they were present in nums initially. The remaining 
elements of nums are not important as well as the size of nums. 
 Return k. 
 

 Custom Judge: 

 The judge will test your solution with the following code: 

 
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
 

 If all assertions pass, then your solution will be accepted. 

 
 Example 1: 

 
Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of 
nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are 
underscores).
 

 Example 2: 

 
Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of 
nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are 
underscores).
 

 
 Constraints: 

 
 1 <= nums.length <= 3 * 10⁴ 
 -100 <= nums[i] <= 100 
 nums is sorted in non-decreasing order. 
 

 Related Topics Array Two Pointers 👍 13367 👎 17657

*/
