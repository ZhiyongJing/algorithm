package leetcode.question.sweep_line;

import java.util.Map;
import java.util.TreeMap;

/**
 *@Question:  1094. Car Pooling
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 43.0%
 *@Time  Complexity: O(N log N)
 *@Space Complexity: O(N)
 */
/*8
### 解题思路讲解

**问题描述**：
我们需要判断是否可以在指定容量的车中完成一系列旅程。每个旅程包括乘客人数、上车地点和下车地点。

**解题思路**：

1. **使用 TreeMap 记录每个时间点的乘客变化**：
   - `TreeMap` 用来按时间顺序记录每个上车和下车时间点的乘客变化情况。
   - 对于每个旅程，在上车时间点增加乘客人数，在下车时间点减少乘客人数。

2. **遍历 TreeMap 的值来检查乘客变化**：
   - 初始化一个变量 `usedCapacity` 来记录当前车内的乘客数，初始值为 0。
   - 遍历 `TreeMap` 的值，每个值表示在该时间点的乘客变化量。
   - 将每个时间点的乘客变化量累加到 `usedCapacity` 中。
   - 如果在任何时间点，`usedCapacity` 超过了车的容量 `capacity`，返回 `false`。
   - 如果遍历完所有时间点，`usedCapacity` 始终没有超过容量，返回 `true`。

**示例**：

假设有以下旅程：
- 旅程 1：2 名乘客，从时间点 1 上车，到时间点 5 下车
- 旅程 2：3 名乘客，从时间点 3 上车，到时间点 7 下车

车的容量为 4。

**步骤**：
1. 初始化 `TreeMap`：
   - 在时间点 1，增加 2 名乘客
   - 在时间点 5，减少 2 名乘客
   - 在时间点 3，增加 3 名乘客
   - 在时间点 7，减少 3 名乘客

   `TreeMap` 结果为：
   - 时间点 1: +2
   - 时间点 3: +3
   - 时间点 5: -2
   - 时间点 7: -3

2. 遍历 `TreeMap` 计算 `usedCapacity`：
   - 时间点 1: `usedCapacity` = 2
   - 时间点 3: `usedCapacity` = 5 （超过车容量，返回 `false`）

因此，答案是 `false`，表示无法在指定容量的车内完成所有旅程。

### 时间和空间复杂度分析

**时间复杂度**：
- 初始化 `TreeMap` 的时间复杂度为 O(N log N)，其中 N 是旅程的数量。因为每次插入 `TreeMap` 的操作复杂度为 O(log N)。
- 遍历 `TreeMap` 的时间复杂度为 O(N)。

总体时间复杂度为 O(N log N)。

**空间复杂度**：
- `TreeMap` 中最多存储 2N 个键值对（每个旅程有两个变动点：上车和下车），空间复杂度为 O(N)。

总体空间复杂度为 O(N)。
 */

public class LeetCode_1094_CarPooling{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            // 创建一个 TreeMap 用来记录每个时间点的乘客变化量
            Map<Integer, Integer> timestamp = new TreeMap<>();

            // 遍历每个旅程
            for (int[] trip : trips) {
                // trip[0] 是乘客人数，trip[1] 是上车地点，trip[2] 是下车地点

                // 记录上车地点的乘客人数变化
                int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
                timestamp.put(trip[1], startPassenger);

                // 记录下车地点的乘客人数变化
                int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
                timestamp.put(trip[2], endPassenger);
            }

            int usedCapacity = 0; // 当前使用的车容量
            // 遍历时间戳的乘客变化情况
            for (int passengerChange : timestamp.values()) {
                usedCapacity += passengerChange; // 更新当前车容量
                if (usedCapacity > capacity) { // 如果超过车容量
                    return false; // 返回 false
                }
            }
            return true; // 如果所有的乘客变动都在车容量内，返回 true
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_1094_CarPooling().new Solution();
        // 测试样例
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;
        System.out.println(solution.carPooling(trips1, capacity1)); // false

        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;
        System.out.println(solution.carPooling(trips2, capacity2)); // true

        int[][] trips3 = {{2, 1, 5}, {3, 5, 7}};
        int capacity3 = 3;
        System.out.println(solution.carPooling(trips3, capacity3)); // true

        int[][] trips4 = {{3, 2, 7}, {3, 7, 9}, {8, 3, 9}};
        int capacity4 = 11;
        System.out.println(solution.carPooling(trips4, capacity4)); // true
    }
}

/**
There is a car with capacity empty seats. The vehicle only drives east (i.e., 
it cannot turn around and drive west). 

 You are given the integer capacity and an array trips where trips[i] = [
numPassengersi, fromi, toi] indicates that the iᵗʰ trip has numPassengersi passengers 
and the locations to pick them up and drop them off are fromi and toi 
respectively. The locations are given as the number of kilometers due east from the car's 
initial location. 

 Return true if it is possible to pick up and drop off all passengers for all 
the given trips, or false otherwise. 

 
 Example 1: 

 
Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
 

 Example 2: 

 
Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 

 
 Constraints: 

 
 1 <= trips.length <= 1000 
 trips[i].length == 3 
 1 <= numPassengersi <= 100 
 0 <= fromi < toi <= 1000 
 1 <= capacity <= 10⁵ 
 

 Related Topics Array Sorting Heap (Priority Queue) Simulation Prefix Sum 👍 444
3 👎 100

*/