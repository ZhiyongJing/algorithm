# 1. Quick Selection 算法思想

> - "Quick selection" 的基本思想是使用快速排序算法的分区（partition）过程，通过不断地缩小问题的规模，找到未排序数组中的第 k 小（或第 k 大）的元素。与快速排序不同的是，Quick selection不需要对整个数组进行排序，只关注目标元素所在的部分

# 2. 算法适用场景

> - TOP k question

# 3. 算法模版

## 3.1 递归方式实现

```java
package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
/*
 * 题目描述：
 * QuickSelect 是一种高效的选择算法，专门用于在 **无序数组** 中查找 **第 k 大（或第 k 小）元素**。
 * 它基于 **快速排序（QuickSort）** 的 **分区（Partition）** 思想，能够在 **O(N) 平均时间复杂度** 下找到目标元素。
 *
 * 本题实现了 **两种不同的 QuickSelect 变种**：
 * 1. **基于 List<Integer>（递归实现，查找第 K 大元素）**
 *    - 使用随机 pivot，将数组划分为 **大于 / 等于 / 小于 pivot** 三个部分，递归查找目标元素。
 * 2. **基于 int[] 数组（递归实现，查找第 K 小元素）**
 *    - 采用 **Lomuto 分区法**，使用 **单轴分区** 并进行递归查找。
 *
 * 输入：
 * - `nums`：一个无序整数数组（可能包含重复元素）。
 * - `k`：目标排名（第 k 大或第 k 小）。
 *
 * 输出：
 * - `第 k 大 / 小的元素`。
 *
 * 示例：
 * 输入：
 *   nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
 *   k = 3
 * 输出：
 *   5  // 第 3 大的元素
 */

/*
 * 解题思路：
 * QuickSelect 通过**分治（Divide & Conquer）**策略，基于 **快速排序（QuickSort）** 的 **分区（Partition）** 操作，
 * 递归地在一个子集范围内寻找 **第 k 大（或第 k 小）元素**，避免了完整排序的开销。
 *
 * **方法 1：基于 List<Integer> 递归 QuickSelect**
 * ------------------------------------------------------
 * 1️⃣ **选择随机 pivot**：
 *    - 选取数组中的一个随机元素 `pivot` 作为分区基准值。
 *
 * 2️⃣ **划分数组为三部分**：
 *    - `left`：所有比 `pivot` **大的** 元素。
 *    - `mid`：所有 **等于** `pivot` 的元素。
 *    - `right`：所有比 `pivot` **小的** 元素。
 *
 * 3️⃣ **判断 `k` 所在的位置**：
 *    - 如果 `k` **落在 left 部分**：递归查找 `left`。
 *    - 如果 `k` **落在 right 部分**：递归查找 `right`（更新 `k` 值）。
 *    - 如果 `k` **落在 mid 部分**：返回 `pivot`。
 *
 * **示例**
 * ```
 * nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
 * k = 3
 *
 * pivot = 4
 * left  = [5, 5, 6]
 * mid   = [4]
 * right = [3, 2, 3, 1, 2]
 *
 * k=3 在 left 中，递归查找 left：[5, 5, 6]
 * pivot = 5
 * left  = [6]
 * mid   = [5, 5]
 * right = []
 *
 * k=3 在 mid 中，返回 `5`
 * ```
 * **最终结果：5**
 *
 * ------------------------------------------------------
 * **方法 2：基于 int[] 递归 QuickSelect**
 * ------------------------------------------------------
 * 1️⃣ **使用 Lomuto 分区法（Partitioning）**
 *    - 选取数组 **最右侧元素** 作为 `pivot`。
 *    - 使用 **双指针** 遍历数组，将小于 `pivot` 的元素放到左侧。
 *
 * 2️⃣ **递归搜索目标索引 `k`**
 *    - 若 `pivotIndex == k - 1`，返回 `nums[pivotIndex]`。
 *    - 若 `pivotIndex < k - 1`，递归右侧子数组。
 *    - 若 `pivotIndex > k - 1`，递归左侧子数组。
 *
 * **示例**
 * ```
 * nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
 * k = 3
 *
 * 选取 pivot = 6，分区后：
 * nums = [3, 2, 3, 1, 2, 4, 5, 5] + [6]
 * pivotIndex = 8
 *
 * k=3 在 pivot 左侧，递归左侧数组：
 * 选取 pivot = 4，分区后：
 * nums = [3, 2, 3, 1, 2] + [4] + [5, 5]
 * pivotIndex = 5
 *
 * k=3 在 pivot 左侧，递归左侧数组：
 * 选取 pivot = 3，分区后：
 * nums = [2, 2, 1] + [3, 3] + []
 * pivotIndex = 3
 *
 * pivotIndex == k-1，返回 `3`
 * ```
 * **最终结果：3**
 */

/*
 * 时间和空间复杂度分析：
 *
 * **方法 1（List 递归 QuickSelect）**
 * - **时间复杂度：O(N) 平均，O(N^2) 最差**
 *   - 平均情况，每次分区后约减少一半元素，总递归次数为 `O(log N)`，整体 `O(N)`。
 *   - 最差情况（已排序数组），每次只去掉一个元素，需要 `O(N^2)`。
 * - **空间复杂度：O(N)**
 *   - 由于创建了 `left`、`mid`、`right` 额外列表，最坏情况 `O(N)` 额外空间。
 *
 * **方法 2（数组递归 QuickSelect）**
 * - **时间复杂度：O(N) 平均，O(N^2) 最差**
 *   - 同样是快速排序的分区操作，平均 `O(N)`，最坏 `O(N^2)`。
 * - **空间复杂度：O(1)**
 *   - 由于在原数组上进行操作，不需要额外存储空间，仅使用常数级变量 `O(1)`。
 *
 * **推荐选择**
 * | 方法 | 时间复杂度 | 空间复杂度 | 适用场景 |
 * |------|----------|----------|--------|
 * | **List 递归 QuickSelect** | O(N) | O(N) | 适用于 `List<Integer>` |
 * | **数组递归 QuickSelect** | O(N) | O(1) | 适用于 `int[]`，性能最佳 |
 */


public class QuickSelection {
    // 方法1: 递归实现 QuickSelect (基于列表) 查找第 K 大元素
    public int quickSelectListBased(List<Integer> nums, int k) {
        // 选择一个随机索引作为基准（pivot）
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);

        // 定义三个列表用于存放比 pivot 大、小和相等的元素
        List<Integer> left = new ArrayList<>(); // 存储比 pivot 大的元素
        List<Integer> mid = new ArrayList<>(); // 存储等于 pivot 的元素
        List<Integer> right = new ArrayList<>(); // 存储比 pivot 小的元素

        // 遍历列表，将元素分别放入不同的列表
        for (int num : nums) {
            if (num > pivot) {
                left.add(num); // 比 pivot 大的元素放入 left
            } else if (num < pivot) {
                right.add(num); // 比 pivot 小的元素放入 right
            } else {
                mid.add(num); // 等于 pivot 的元素放入 mid
            }
        }

        // 如果 k 在 left 中，则递归查找左子数组
        if (k <= left.size()) {
            return quickSelectListBased(left, k);
        }

        // 如果 k 在 right 中，则递归查找右子数组（k 需要调整偏移）
        if (left.size() + mid.size() < k) {
            return quickSelectListBased(right, k - left.size() - mid.size());
        }

        // 如果 k 位于 pivot 位置，则返回 pivot
        return pivot;
    }


    // 方法2: 递归实现 QuickSelect (基于数组) 查找第 K 小元素
    public int quickSelectArrayBased(int[] nums, int k) {
        if (k > 0 && k <= nums.length) {
            return quickSelect(nums, 0, nums.length - 1, k);
        } else {
            return -1; // 如果 k 不合法，返回 -1 或抛出异常
        }
    }

    // 递归方式执行 QuickSelect
    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivotIndex = partition(nums, left, right); // 对数组进行分区

        // 如果 pivot 恰好是第 k 小元素，直接返回
        if (pivotIndex == k - 1) {
            return nums[pivotIndex];
        }
        // 如果 pivot 位置小于 k-1，搜索右半部分
        else if (pivotIndex < k - 1) {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
        // 否则搜索左半部分
        else {
            return quickSelect(nums, left, pivotIndex - 1, k);
        }
    }

    // 执行 Lomuto 分区（单轴分区）
    private int partition(int[] nums, int left, int right) {
        int pivotIndex = choosePivot(nums, left, right); // 选择 pivot 位置
        int pivotValue = nums[pivotIndex];

        // 交换 pivot 和最右侧元素
        swap(nums, pivotIndex, right);
        int currentIndex = left; // 记录小于 pivot 的元素放置位置

        // 遍历数组，将比 pivot 小的元素移动到左侧
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, currentIndex);
                currentIndex++;
            }
        }

        // 最后将 pivot 放入最终位置
        swap(nums, currentIndex, right);

        return currentIndex;
    }

    // 选择 pivot 位置（这里选择最右边的元素）
    private int choosePivot(int[] nums, int left, int right) {
        return right;
    }

    // 交换数组中的两个元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 方法3: 迭代（非递归）实现 QuickSelect（使用栈）
    // TODO: 需要实现非递归版本

    public static void main(String[] args) {
        QuickSelection q = new QuickSelection();

        // 测试方法1：基于列表查找第 K 大元素
        List<Integer> list = Arrays.asList(3, 2, 3, 1, 2, 4, 5, 5, 5, 6);
        System.out.println("Top 3rd largest element (List-based): " + q.quickSelectListBased(list, 3));

        // 测试方法2：基于数组查找第 K 小元素
        int[] array = {3, 2, 3, 1, 2, 4, 5, 5, 5, 6};
        int k = 3;
        System.out.println("Top 3rd smallest element (Array-based): " + q.quickSelectArrayBased(array, k));
    }
}

```

# 4. 时间复杂度

> - 时间复杂度：O(N)， worst case O(N^2)
> - 空间复杂度：O(1)
