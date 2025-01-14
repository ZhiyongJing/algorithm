package leetcode.question.dfs;

import java.util.*;

/**
 *@Question:  582. Kill Process
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 58.75%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */
/**
 * 第一步：题目描述
 * 题目要求给定三个参数：一个进程ID列表（pid），一个父进程ID列表（ppid），以及需要杀死的进程ID（kill）。
 * 当杀死某个进程时，与其相关的所有子进程（直接或间接）也会被杀死。
 * 输出被杀死的所有进程ID。
 *
 * 示例：
 * 输入：
 * pid = [1, 3, 10, 5]
 * ppid = [3, 0, 5, 3]
 * kill = 3
 * 输出：[3, 1, 5, 10]
 *
 * 解释：
 * 进程3有两个直接子进程1和5，进程5又有一个子进程10。
 * 杀死进程3会导致杀死它的所有子进程：1、5和10。
 *
 * 第二步：解题思路
 * 1. 构建进程的父子关系：
 *    - 使用哈希表将每个父进程ID映射到其直接子进程ID列表。
 *    - 遍历输入的父进程ID列表（ppid），将子进程ID（pid）归属到对应的父进程。
 *    - 示例：输入ppid = [3, 0, 5, 3]和pid = [1, 3, 10, 5]
 *      构建出如下的哈希表：
 *      map = {
 *        3 -> [1, 5],
 *        5 -> [10]
 *      }
 *
 * 2. 使用深度优先搜索（DFS）获取所有相关进程：
 *    - 从要杀死的进程（kill）开始，递归访问其所有子进程，并将结果记录到列表中。
 *    - 示例：
 *      假设kill = 3，从3开始递归：
 *      - 第一次递归：加入3，发现3有子进程1和5。
 *      - 第二次递归：加入1，1无子进程。
 *      - 第三次递归：加入5，发现5有子进程10。
 *      - 第四次递归：加入10，10无子进程。
 *      最终结果：[3, 1, 5, 10]。
 *
 * 3. 使用广度优先搜索（BFS）获取所有相关进程（可选）：
 *    - 使用队列从kill开始逐层访问其子进程，将每个访问到的进程记录到结果列表中。
 *    - 示例：
 *      假设kill = 3，从3开始：
 *      - 队列初始为[3]，取出3，加入结果，并将3的子进程[1, 5]加入队列。
 *      - 队列变为[1, 5]，取出1，加入结果，1无子进程。
 *      - 队列变为[5]，取出5，加入结果，并将5的子进程[10]加入队列。
 *      - 队列变为[10]，取出10，加入结果，10无子进程。
 *      最终结果：[3, 1, 5, 10]。
 *
 * 第三步：时间和空间复杂度
 * 1. 时间复杂度：
 *    - 构建父子关系哈希表：O(N)，其中N是进程的数量。
 *    - 访问所有相关进程（DFS或BFS）：O(N)。
 *    - 总时间复杂度：O(N)。
 *
 * 2. 空间复杂度：
 *    - 哈希表存储父子关系：O(N)。
 *    - 递归栈（DFS）或队列（BFS）：最坏情况下为O(N)。
 *    - 总空间复杂度：O(N)。
 */

public class LeetCode_582_KillProcess{

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // Solution1(prefer): 使用哈希表 + 深度优先搜索
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            // 创建一个哈希表，键是父进程ID，值是子进程ID列表
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < ppid.size(); i++) {
                if (ppid.get(i) > 0) { // 如果当前父进程ID大于0
                    // 获取当前父进程的子进程列表，如果不存在则初始化为空列表
                    List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<>());
                    l.add(pid.get(i)); // 将当前子进程ID加入列表
                    map.put(ppid.get(i), l); // 更新哈希表
                }
            }
            System.out.println(map);
            List<Integer> l = new ArrayList<>(); // 存储结果的列表
            l.add(kill); // 首先加入要杀死的进程ID
            getAllChildren(map, l, kill); // 递归获取所有子进程
            return l; // 返回结果列表
        }

        // 递归函数：获取所有子进程
        public void getAllChildren(HashMap<Integer, List<Integer>> map, List<Integer> l, int kill) {
            if (map.containsKey(kill)) { // 如果当前进程有子进程
                for (int id : map.get(kill)) { // 遍历子进程
                    l.add(id); // 将子进程加入结果列表
                    getAllChildren(map, l, id); // 递归获取子进程的子进程
                }
            }
        }

        // Solution2: 使用哈希表 + 广度优先搜索
        public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
            // 创建一个哈希表，键是父进程ID，值是子进程ID列表
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < ppid.size(); i++) {
                if (ppid.get(i) > 0) { // 如果当前父进程ID大于0
                    // 获取当前父进程的子进程列表，如果不存在则初始化为空列表
                    List<Integer> l = map.getOrDefault(ppid.get(i), new ArrayList<>());
                    l.add(pid.get(i)); // 将当前子进程ID加入列表
                    map.put(ppid.get(i), l); // 更新哈希表
                }
            }
            System.out.println(map);
            Queue<Integer> queue = new LinkedList<>(); // 队列用于广度优先搜索
            List<Integer> l = new ArrayList<>(); // 存储结果的列表
            queue.add(kill); // 首先将要杀死的进程加入队列
            while (!queue.isEmpty()) { // 当队列不为空时
                int r = queue.remove(); // 取出队首元素
                l.add(r); // 将其加入结果列表
                if (map.containsKey(r)) { // 如果当前进程有子进程
                    for (int id : map.get(r)) { // 遍历子进程
                        queue.add(id); // 将子进程加入队列
                    }
                }
            }
            return l; // 返回结果列表
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new LeetCode_582_KillProcess().new Solution();

        // 测试样例1
        List<Integer> pid = Arrays.asList(1, 3, 10, 5);
        List<Integer> ppid = Arrays.asList(3, 0, 5, 3);
        int kill = 3;
        System.out.println(solution.killProcess(pid, ppid, kill)); // 预期输出：[3, 1, 5, 10]

        // 测试样例2
        System.out.println(solution.killProcess2(pid, ppid, kill)); // 预期输出：[3, 1, 5, 10]
    }
}

/**
 You have n processes forming a rooted tree structure. You are given two integer
 arrays pid and ppid, where pid[i] is the ID of the iᵗʰ process and ppid[i] is
 the ID of the iᵗʰ process's parent process.

 Each process has only one parent process but may have multiple children 
 processes. Only one process has ppid[i] = 0, which means this process has no parent
 process (the root of the tree).

 When a process is killed, all of its children processes will also be killed. 

 Given an integer kill representing the ID of a process you want to kill, 
 return a list of the IDs of the processes that will be killed. You may return the
 answer in any order.


 Example 1: 


 Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 Output: [5,10]
 Explanation: The processes colored in red are the processes that should be
 killed.


 Example 2: 


 Input: pid = [1], ppid = [0], kill = 1
 Output: [1]



 Constraints: 


 n == pid.length 
 n == ppid.length 
 1 <= n <= 5 * 10⁴ 
 1 <= pid[i] <= 5 * 10⁴ 
 0 <= ppid[i] <= 5 * 10⁴ 
 Only one process has no parent. 
 All the values of pid are unique. 
 kill is guaranteed to be in pid. 


 Related Topics Array Hash Table Tree Depth-First Search Breadth-First Search 👍
 1119 👎 21

 */