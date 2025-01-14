package leetcode.question.greedy;
/**
 *@Question:  765. Couples Holding Hands
 *@Difculty:  3 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 51.23%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 题目描述：
 * 给定一个整数数组 row，其中每两个相邻的数字代表一对夫妻。数组的偶数索引表示一对夫妻的第一个人，奇数索引表示第二个成员。
 * 现在数组中的夫妻顺序被打乱，要求通过最少的交换次数将每一对夫妻排到正确的位置。
 * 每次交换两个元素的位置，求最少需要交换多少次，使得所有夫妻都回到正确的位置。
 */

/**
 * 解题思路：
 * 1. **构建映射关系**：
 *    - 首先，遍历给定的数组，构建一个映射表 `map`。`map` 的键是每个元素的值，值是该元素所在的索引位置。
 *    - 这样我们就可以快速找到每个元素的当前索引位置。
 *    - **举例**：
 *      对于输入数组 `[0, 2, 1, 3]`，我们得到的 `map` 是：
 *      ```
 *      map = {
 *        0 -> 0,
 *        2 -> 1,
 *        1 -> 2,
 *        3 -> 3
 *      }
 *      ```
 *    - `map` 可以帮助我们在后续步骤中快速找到元素的位置。

 * 2. **判断夫妻是否已经配对**：
 *    - 遍历数组，按照每两个相邻的元素构成一对夫妻检查。如果当前夫妻已经排好，直接跳过；
 *    - 如果没有排好，说明这两个人需要交换位置。
 *    - 判断是否为一对夫妻：假设夫妻对的元素为 `(a, b)`，则应当满足 `b = a + 1` 或 `a = b + 1`。
 *    - **举例**：
 *      对于数组 `[0, 2, 1, 3]`，夫妻对是 `(0, 2)`，但 0 和 2 并不是配对夫妻，需要交换。

 * 3. **执行交换操作**：
 *    - 如果夫妻没有配对，我们就需要交换位置。
 *    - 找到配对对象的位置，并交换这两个元素，同时更新 `map` 中对应的值。
 *    - 交换后，我们将交换次数 `res` 加 1。
 *    - **举例**：
 *      对于数组 `[0, 2, 1, 3]`，我们交换 0 和 2，数组变为 `[2, 0, 1, 3]`，此时 `res = 1`。
 *      然后继续检查，发现夫妻 `(0, 2)` 配对成功，整个过程结束。

 * 4. **继续遍历和交换**：
 *    - 继续进行类似的操作，直到所有的夫妻都排好位置。
 *    - 每次交换都会更新 `map`，所以我们能在每一轮检查中判断出是否已完成配对。

 * 通过以上步骤，我们能够通过最少的交换次数使得所有夫妻都回到正确的位置。

 */

/**
 * 时间复杂度：
 * - O(N)，其中 N 是数组的长度。我们只需要遍历数组一次来构建映射表，再遍历一次来进行交换操作，因此整体时间复杂度为 O(N)。

 * 空间复杂度：
 * - O(N)，我们需要一个额外的数组 `map` 来存储每个元素的位置，空间复杂度为 O(N)。
 */

public class LeetCode_765_CouplesHoldingHands{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 主函数：计算最少交换次数
        public int minSwapsCouples(int[] row) {
            int a[] = row; // 输入数组，表示每对夫妻的位置
            int map [] = new int[row.length]; // map用于记录每个数字当前的位置

            // 遍历输入数组，将每个位置存入map中
            for(int i = 0 ;i<a.length;i++){
                map[a[i]] = i; // map[a[i]] = i 表示值a[i]在位置i
            }

            int res = 0; // 记录交换次数

            // 遍历每一对夫妻
            for(int i = 0; i<a.length; i+=2){ // 偶数位置为一对夫妻的第一个人
                // 如果a[i]和a[i+1]已经是情侣，则跳过
                if(isTheSame(a[i], a[i+1])) continue;

                // 如果a[i]和a[i+1]不是情侣，则需要交换
                int pairPos = findPairPos(map, a[i+1]); // 找到配对的另一方的位置
                swap(map, a, i, pairPos); // 执行交换
                res++; // 交换次数加1
            }
            return res; // 返回交换次数
        }

        // 辅助函数：判断a和b是否为情侣
        boolean isTheSame(int a, int b){
            if(b%2 == 0) return a == b+1; // 偶数的配偶是偶数+1
            return b == a+1; // 奇数的配偶是奇数+1
        }

        // 辅助函数：找到某个值的配对位置
        int findPairPos(int map [], int val){
            int queryVal = val-1; // 偶数配对数是val+1，奇数配对数是val-1
            if(val % 2 == 0) queryVal = val+1 ;
            return map[queryVal]; // 返回配对值的当前位置
        }

        // 辅助函数：交换两个值的位置
        void swap(int map [], int a[], int i, int j){
            int tmp = a[i]; // 临时存储a[i]
            a[i] = a[j]; // 交换a[i]和a[j]
            a[j] = tmp; // 将临时存储的值放到a[j]

            // 更新map中对应值的位置信息
            map[a[j]] = j;
            map[a[i]] = i;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_765_CouplesHoldingHands().new Solution();
        // 测试样例
        int[] row = {0, 2, 1, 3}; // 初始位置：0和1是一对，2和3是一对
        int result = solution.minSwapsCouples(row); // 应该返回 1，因为需要交换0和2的位置
        System.out.println(result); // 输出交换次数 1
    }
}

/**
There are n couples sitting in 2n seats arranged in a row and want to hold 
hands. 

 The people and seats are represented by an integer array row where row[i] is 
the ID of the person sitting in the iᵗʰ seat. The couples are numbered in order, 
the first couple being (0, 1), the second couple being (2, 3), and so on with 
the last couple being (2n - 2, 2n - 1). 

 Return the minimum number of swaps so that every couple is sitting side by 
side. A swap consists of choosing any two people, then they stand up and switch 
seats. 

 
 Example 1: 

 
Input: row = [0,2,1,3]
Output: 1
Explanation: We only need to swap the second (row[1]) and third (row[2]) person.

 

 Example 2: 

 
Input: row = [3,2,0,1]
Output: 0
Explanation: All couples are already seated side by side.
 

 
 Constraints: 

 
 2n == row.length 
 2 <= n <= 30 
 n is even. 
 0 <= row[i] < 2n 
 All the elements of row are unique. 
 

 Related Topics Greedy Depth-First Search Breadth-First Search Union Find Graph 
👍 2386 👎 119

*/