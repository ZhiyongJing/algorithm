package leetcode.question.two_pointer;

/**
 *@Question:  277. Find the Celebrity
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 47.82%
 *@Time  Complexity: O(n)
 *@Space Complexity: O(1)
 */

/**
 * 这道题的解题思路基于以下两个观察点：
 *
 * 1. 如果 A 知道 B，那么 A 不可能是名人；如果 A 不知道 B，那么 B 不可能是名人。
 * 2. 名人是所有人都认识的，但名人不认识任何人。
 *
 * 基于这两个观察点，我们可以采用以下的算法：
 *
 * 1. 首先，遍历一次找到可能的名人候选（celebrityCandidate）。我们初始化 celebrityCandidate 为 0，
 * 然后从 0 到 n-1 遍历，如果 `knows(celebrityCandidate, i)` 为 true，说明 celebrityCandidate 不可能是名人，
 * 我们将 celebrityCandidate 更新为 i。由于名人不认识其他人，所以在遍历过程中 celebrityCandidate 会停在可能的名人身上。
 *
 * 2. 然后，我们需要验证 celebrityCandidate 是否是真正的名人。我们遍历一次所有人，检查 celebrityCandidate 是否满足“不认识其他人，
 * 但其他人都认识 celebrityCandidate”的条件。如果满足，那么 celebrityCandidate 就是名人。
 *
 *
 * **时间复杂度分析：**
 * - 第一次遍历所有人，时间复杂度为 O(n)。
 * - 第二次遍历所有人，时间复杂度同样为 O(n)。
 *
 * 所以总体时间复杂度为 O(n)。
 *
 * **空间复杂度分析：**
 * - 使用常数级的额外空间，主要是一个变量 celebrityCandidate，所以空间复杂度为 O(1)。
 */

public class LeetCode_277_FindTheCelebrity {

    // leetcode submit region begin(Prohibit modification and deletion)
    /* The knows API is defined in the parent class Relation.
       boolean knows(int a, int b); */

    public class Solution extends Relation {

        private int numberOfPeople;

        /**
         * 寻找名人的方法
         * @param n 总人数
         * @return 名人的编号，如果不存在名人则返回 -1
         */
        public int findCelebrity(int n) {
            numberOfPeople = n;
            int celebrityCandidate = 0;

            // 第一次遍历找到可能的名人候选
            for (int i = 0; i < n; i++) {
                if (knows(celebrityCandidate, i)) {
                    celebrityCandidate = i;
                }
            }


            // 判断是否是真正的名人
            if (isCelebrity(celebrityCandidate)) {
                return celebrityCandidate;
            }
            return -1;
        }

        /**
         * 判断某个候选是否是名人
         * @param i 候选的编号
         * @return 是否是名人
         */
        private boolean isCelebrity(int i) {
            for (int j = 0; j < numberOfPeople; j++) {
                if (i == j) continue; // 不需要判断自己是否认识自己
                if (knows(i, j) || !knows(j, i)) {
                    return false;
                }
            }
            return true;
        }


    }
    //leetcode submit region end(Prohibit modification and deletion)

    private boolean knows(int j, int i) {
        //fake api
        return true;
    }

    public static void main(String[] args) {
        LeetCode_277_FindTheCelebrity.Solution solution = new LeetCode_277_FindTheCelebrity().new Solution();

        // 测试代码
        int[][] knowsMatrix = {
                {1, 1, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        // 将测试数据转化为 knows 方法的调用
        for (int i = 0; i < knowsMatrix.length; i++) {
            for (int j = 0; j < knowsMatrix[0].length; j++) {
                System.out.println("Person " + i + " knows Person " + j + ": " + (knowsMatrix[i][j] == 1));
            }
        }

        int result = solution.findCelebrity(3);

        if (result != -1) {
            System.out.println("The celebrity is Person " + result);
        } else {
            System.out.println("No celebrity found.");
        }
    }

    private class Relation {
    }
}

/**
Suppose you are at a party with n people labeled from 0 to n - 1 and among them,
 there may exist one celebrity. The definition of a celebrity is that all the 
other n - 1 people know the celebrity, but the celebrity does not know any of 
them. 

 Now you want to find out who the celebrity is or verify that there is not one. 
You are only allowed to ask questions like: "Hi, A. Do you know B?" to get 
information about whether A knows B. You need to find out the celebrity (or verify 
there is not one) by asking as few questions as possible (in the asymptotic sense)
. 

 You are given a helper function bool knows(a, b) that tells you whether a 
knows b. Implement a function int findCelebrity(n). There will be exactly one 
celebrity if they are at the party. 

 Return the celebrity's label if there is a celebrity at the party. If there is 
no celebrity, return -1. 

 
 Example 1: 
 
 
Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 
means person i knows person j, otherwise graph[i][j] = 0 means person i does not 
know person j. The celebrity is the person labeled as 1 because both 0 and 2 
know him but 1 does not know anybody.
 

 Example 2: 
 
 
Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.
 

 
 Constraints: 

 
 n == graph.length == graph[i].length 
 2 <= n <= 100 
 graph[i][j] is 0 or 1. 
 graph[i][i] == 1 
 

 
 Follow up: If the maximum number of allowed calls to the API knows is 3 * n, 
could you find a solution without exceeding the maximum number of calls? 

 Related Topics Two Pointers Graph Interactive 👍 2759 👎 264

*/
