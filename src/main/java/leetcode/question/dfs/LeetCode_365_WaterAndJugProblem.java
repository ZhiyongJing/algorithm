package leetcode.question.dfs;
/**
 *@Question:  365. Water and Jug Problem
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 57.59000000000001%
 *@Time  Complexity: O(log(min(x, y)))
 *@Space Complexity: O(1)
 */

/**
 * ### 题目描述
 *
 * **题目编号：365. Water and Jug Problem**
 *
 * **问题描述：**
 *
 * 给定两个容量分别为 `x` 和 `y` 的水壶，以及一个目标水量 `target`，你需要判断是否能够通过任意方式使用这两个水壶测量出 `target` 升的水。你可以使用以下操作：
 *
 * - 将水壶灌满水。
 * - 将水壶清空。
 * - 将一个水壶中的水倒入另一个水壶，直到第一个水壶空或者第二个水壶满。
 *
 * **示例：**
 *
 * - **输入：** `x = 3`，`y = 5`，`target = 4`
 * - **输出：** `true`
 * - **解释：** 可以通过以下操作测量出 4 升水：将 5 升水壶灌满，倒入 3 升水壶，直到 3 升水壶满，此时 5 升水壶剩下 2 升水。然后将 3 升水壶清空，将 2 升水倒入 3 升水壶中，最后将 5 升水壶再次灌满，倒入 3 升水壶，直到 3 升水壶满，此时 5 升水壶剩下 4 升水，即目标量。
 *
 * ### 解题思路
 *
 * 1. **容量总和与目标量比较**：
 *    - 首先，检查目标量是否大于两个水壶的容量之和。如果目标量大于 `x + y`，则无法测量出目标量，因为两个水壶的总容量都不够。
 *
 * 2. **直接匹配**：
 *    - 检查目标量是否等于任意一个水壶的容量，或者等于两个水壶容量的总和。如果是，则可以直接测量出目标量。例如，目标量等于其中一个水壶的容量或两个水壶的容量之和时，可以通过将水壶灌满或清空来测量出目标量。
 *
 * 3. **最大公约数**：
 *    - 如果目标量不满足前两种条件，则需要检查目标量是否是两个水壶容量的最大公约数的倍数。根据数论的原理，只要目标量是两个数的最大公约数的倍数，就可以通过组合这两个水壶的水量来测量出目标量。
 *
 *    - 使用辗转相除法计算两个水壶容量的最大公约数，然后检查目标量是否能够被最大公约数整除。
 *
 * ### 时间和空间复杂度
 *
 * - **时间复杂度**：`O(log(min(x, y)))`
 *   计算最大公约数的过程使用了辗转相除法，其时间复杂度是对数级的，因为每一步都将问题规模减小为原来的一个子问题。
 *
 * - **空间复杂度**：`O(1)`
 *   该算法只使用了常量空间来存储变量，没有使用额外的存储空间，因此空间复杂度为常数级。
 */

public class LeetCode_365_WaterAndJugProblem{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 判断是否可以用两个水壶装水，测量出目标量的水
        public boolean canMeasureWater(int x, int y, int target) {

            // 情况一：目标量大于两个水壶的容量之和
            if (x + y < target) {
                return false;  // 不可能测量出目标量
            }

            // 情况二：目标量等于任意一个水壶的容量，或者等于两个水壶容量之和
            if (x == target || y == target || x + y == target) {
                return true;  // 可以测量出目标量
            }

            // 情况三：目标量是两个水壶容量的最大公约数的倍数
            if (target % gcd(x, y) == 0) {
                return true;  // 可以测量出目标量
            }

            return false;  // 以上条件都不满足，不能测量出目标量
        }

        // 计算两个数的最大公约数（辗转相除法）
        public int gcd(int a, int b) {
            if (b == 0) {
                return a;  // 当 b 为 0 时，a 就是最大公约数
            } else {
                return gcd(b, a % b);  // 递归调用，计算 a 和 b 的最大公约数
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_365_WaterAndJugProblem().new Solution();

        // 测试样例 1
        int x1 = 3, y1 = 5, target1 = 4;
        System.out.println("测试样例 1：");
        // 期望输出：true，使用 3 升水壶和 5 升水壶可以测量出 4 升水
        System.out.println("是否可以测量出 " + target1 + " 升水: " + solution.canMeasureWater(x1, y1, target1));

        // 测试样例 2
        int x2 = 2, y2 = 6, target2 = 5;
        System.out.println("测试样例 2：");
        // 期望输出：false，使用 2 升水壶和 6 升水壶无法测量出 5 升水
        System.out.println("是否可以测量出 " + target2 + " 升水: " + solution.canMeasureWater(x2, y2, target2));

        // 测试样例 3
        int x3 = 1, y3 = 1, target3 = 2;
        System.out.println("测试样例 3：");
        // 期望输出：true，使用 1 升水壶和 1 升水壶可以测量出 2 升水
        System.out.println("是否可以测量出 " + target3 + " 升水: " + solution.canMeasureWater(x3, y3, target3));
    }
}

/**
You are given two jugs with capacities x liters and y liters. You have an 
infinite water supply. Return whether the total amount of water in both jugs may 
reach target using the following operations: 

 
 Fill either jug completely with water. 
 Completely empty either jug. 
 Pour water from one jug into another until the receiving jug is full, or the 
transferring jug is empty. 
 

 
 Example 1: 

 
 Input: x = 3, y = 5, target = 4 
 

 Output: true 

 Explanation: 

 Follow these steps to reach a total of 4 liters: 

 
 Fill the 5-liter jug (0, 5). 
 Pour from the 5-liter jug into the 3-liter jug, leaving 2 liters (3, 2). 
 Empty the 3-liter jug (0, 2). 
 Transfer the 2 liters from the 5-liter jug to the 3-liter jug (2, 0). 
 Fill the 5-liter jug again (2, 5). 
 Pour from the 5-liter jug into the 3-liter jug until the 3-liter jug is full. 
This leaves 4 liters in the 5-liter jug (3, 4). 
 Empty the 3-liter jug. Now, you have exactly 4 liters in the 5-liter jug (0, 4)
. 
 

 Reference: The Die Hard example. 

 Example 2: 

 
 Input: x = 2, y = 6, target = 5 
 

 Output: false 

 Example 3: 

 
 Input: x = 1, y = 2, target = 3 
 

 Output: true 

 Explanation: Fill both jugs. The total amount of water in both jugs is equal 
to 3 now. 

 
 Constraints: 

 
 1 <= x, y, target <= 10³ 
 

 Related Topics Math Depth-First Search Breadth-First Search 👍 1513 👎 1472

*/