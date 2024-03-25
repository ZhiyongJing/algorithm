> - 请帮我理解一道新的文字代码，并且在每行代码上面添加详细的中文注释和测试代码，并保留已有注释
> - 把解题思路详细讲解出来，并说明时间和空间复杂度

- 最短路径， 一般用BFS 或者 DFS 解决
- 最长路径， 一般用DFS 或者DP解决

# 1. DFS

## 1.1 特点

- **一般用于找出问题的一个解决方案或者一条路径**
- **DFS更适合在图或树等结构上进行搜索**

## 1.2 算法模版

### 1.2.1 通过递归实现

当我们递归地实现 DFS 时，似乎不需要使用任何栈。但实际上，我们使用的是由系统提供的隐式栈，也称为调用栈（Call Stack）。

```java
/* * Return true if there is a path from cur to target. */
boolean DFS(Node cur, Node target, Set<Node> visited) {
   
    return true if cur is target;
    for (next : each neighbor of cur) {
        if (next is not in visited) {
            add next to visted;
            return true if DFS(next, target, visited) == true;
        }
    }
    return false;
}
```

### 1.2.2 通过非递归实现

递归解决方案的优点是它更容易实现。 但是，存在一个很大的缺点：如果递归的深度太高，你将遭受堆栈溢出。 
在这种情况下，您可能会希望使用 BFS，或使用显式栈实现DFS

```java
/* * Return true if there is a path from cur to target. */
boolean DFS(int root, int target) {
    Set<Node> visited;
    Stack<Node> s;
    add root to s;
    while (s is not empty) {
        Node cur = the top element in s;
        return true if cur is target;
        for (Node next : the neighbors of cur) {
            if (next is not in visited) {
                add next to s;
                add next to visited;
            }
        }
        remove cur from s;
    }
    return false;
}
```

## 1.3 总结

深度优先搜索（DFS）是一种常用的搜索算法，适用于多种问题，尤其是图和树结构的遍历问题。以下是一些情况下使用DFS的典型场景：

1. **图的遍历：** DFS 可以用于图的深度优先遍历，查找特定节点或寻找路径。

2. **树的遍历：** 对于树结构，DFS可以用于先序遍历、中序遍历、后序遍历，或者其他变种。

3. **连通性问题：** 如果问题涉及到判断两个节点是否连通，或者查找图中的连通分量，DFS是一个常用的方法。

4. **路径问题：** 求解路径问题，如找到从起点到终点的路径，或找到满足某条件的路径。

5. **拓扑排序：** DFS可以用于拓扑排序，即对有向无环图进行排序。

6. **回溯算法：** 回溯算法通常使用DFS来穷举所有可能的解空间。

7. **状态空间搜索：** 在某些问题中，状态可以看作图的节点，DFS用于搜索状态空间。

8. **生成和验证问题：** 生成问题涉及到生成所有可能的解，验证问题涉及到验证某个解是否符合条件，DFS可以应用于这两种类型的问题。

总体来说，DFS适用于那些具有树状或图状结构的问题，以及需要搜索解空间的问题。DFS的实现通常通过递归或栈来实现。需要注意的是，在某些情况下，DFS可能不是最优解决方案，因此在选择算法时需要综合考虑问题的性质和要求。

回溯算法和深度优先搜索（DFS）算法有一些相似之处，但也有一些关键区别。

1. **定义和目标：**
   - **回溯算法：** 主要用于解决组合、排列、子集等问题，其目标是搜索问题的所有可能解，并在搜索过程中通过回溯（撤销之前的选择）来寻找有效解。
   - **深度优先搜索（DFS）：** 是一种搜索算法，用于遍历或搜索图或树等数据结构的所有节点。DFS 通常用于判断两个节点之间是否连通，或者寻找路径等。

2. **状态的保存：**
   - **回溯算法：** 在回溯算法中，通常需要显式地保存和恢复状态，即在每一步选择之前保存当前状态，在选择完成后恢复到之前的状态，以实现回溯。
   - **深度优先搜索（DFS）：** 在 DFS 中，状态的保存通常是隐式的，因为递归调用栈本身就保存了状态。在 DFS 中，通常通过递归函数的参数传递状态信息，不需要显式保存和恢复状态。

3. **应用场景：**
   - **回溯算法：** 通常应用于组合优化问题，如组合、排列、子集等。它更侧重于找到所有可能的解。
   - **深度优先搜索（DFS）：** 通常应用于图的遍历、连通性判断、路径搜索等问题。它更侧重于在搜索过程中找到其中一个解。

4. **剪枝策略：**
   - **回溯算法：** 回溯算法通常需要考虑剪枝策略，以减少搜索空间，提高算法效率。
   - **深度优先搜索（DFS）：** 在一些情况下，也可以使用剪枝策略，但通常不需要像回溯算法那样频繁地进行状态的保存和恢复。

尽管回溯算法和深度优先搜索有一些相似之处，但它们在目标、应用场景和状态的保存方式上存在一些关键区别。在实际问题中，根据问题的性质选择合适的算法是很重要的。







# 2. Back Tracking

## 2.1 特点

- **基于无返回值DFS**
- **Top Down**

## 2.2 算法模版

~~~java
public class Backtracking {
    public void backtrack(List<List<Integer>> result, List<Integer> path, int[] nums, int start) {
        // 1. 判断是否满足结束条件
        // 如果满足，将当前路径添加到结果集中
        if (满足结束条件) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 2. 尝试每一种可能的选择
        for (int i = start; i < nums.length; i++) {
            // 做选择：将当前元素加入路径
            path.add(nums[i]);

            // 递归进入下一层，注意更新 start 参数
            backtrack(result, path, nums, i + 1);

            // 撤销选择：回溯到上一层，恢复状态
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> backtrackingTemplate(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(result, path, nums, 0);
        return result;
    }
}
~~~

## 2.3 总结



- 回溯算法本质是穷举法，通过对解空间进行深度优先遍历来寻找符合条件的解。在搜索过程中，遇到满足条件的解则记录，直至找到所有解或遍历完成后结束。
- 回溯算法的搜索过程包括尝试与回退两个部分。它通过深度优先搜索来尝试各种选择，当遇到不满足约束条件的情况时，则撤销上一步的选择，退回到之前的状态，并继续尝试其他选择。尝试与回退是两个方向相反的操作。
- 回溯问题通常包含多个约束条件，它们可用于实现剪枝操作。剪枝可以提前结束不必要的搜索分支，大幅提升搜索效率。
- 回溯算法主要可用于解决搜索问题和约束满足问题。组合优化问题虽然可以用回溯算法解决，但往往存在效率更高或效果更好的解法。
- 全排列问题旨在搜索给定集合元素的所有可能的排列。我们借助一个数组来记录每个元素是否被选择，剪掉重复选择同一元素的搜索分支，确保每个元素只被选择一次。
- 在全排列问题中，如果集合中存在重复元素，则最终结果会出现重复排列。我们需要约束相等元素在每轮中只能被选择一次，这通常借助一个哈希表来实现。
- 子集和问题的目标是在给定集合中找到和为目标值的所有子集。集合不区分元素顺序，而搜索过程会输出所有顺序的结果，产生重复子集。我们在回溯前将数据进行排序，并设置一个变量来指示每一轮的遍历起始点，从而将生成重复子集的搜索分支进行剪枝。
- 对于子集和问题，数组中的相等元素会产生重复集合。我们利用数组已排序的前置条件，通过判断相邻元素是否相等实现剪枝，从而确保相等元素在每轮中只能被选中一次。

 





# 3. Divide And Conquer

- **基于有返回值DFS**

## 3.1 特点

## 3.2 算法模版

~~~java
public class DivideAndConquer {
    // 主函数，入口
    public static int divideAndConquer(int[] nums, int left, int right) {
        // 1. 判断递归结束条件
        if (left == right) {
            // 处理基本情况
            return nums[left];
        }

        // 2. 将问题分解为子问题
        int mid = (left + right) / 2;

        // 3. 递归求解子问题
        int leftResult = divideAndConquer(nums, left, mid);
        int rightResult = divideAndConquer(nums, mid + 1, right);

        // 4. 合并子问题的解
        int combinedResult = combineResults(leftResult, rightResult);

        return combinedResult;
    }

    // 辅助函数，用于合并子问题的解
    private static int combineResults(int left, int right) {
        // 根据具体问题合并子问题的解
        return Math.max(left, right);  // 以最大值为例
    }
}

~~~



## 3.3 总结



- 分治是一种常见的算法设计策略，包括分（划分）和治（合并）两个阶段，通常基于递归实现。
- 判断是否是分治算法问题的依据包括：问题能否分解、子问题是否独立、子问题能否合并。
- 归并排序是分治策略的典型应用，其递归地将数组划分为等长的两个子数组，直到只剩一个元素时开始逐层合并，从而完成排序。
- 引入分治策略往往可以提升算法效率。一方面，分治策略减少了操作数量；另一方面，分治后有利于系统的并行优化。
- 分治既可以解决许多算法问题，也广泛应用于数据结构与算法设计中，处处可见其身影。
- 相较于暴力搜索，自适应搜索效率更高。时间复杂度为 的搜索算法通常是基于分治策略实现的。
- 二分查找是分治策略的另一个典型应用，它不包含将子问题的解进行合并的步骤。我们可以通过递归分治实现二分查找。
- 在构建二叉树的问题中，构建树（原问题）可以划分为构建左子树和右子树（子问题），这可以通过划分前序遍历和中序遍历的索引区间来实现。
- 在汉诺塔问题中，一个规模为 的问题可以划分为两个规模为 的子问题和一个规模为 的子问题。按顺序解决这三个子问题后，原问题随之得到解决。







# 4. Binary search

## 4.1 特点

- 适用于排序后的数组， 可以是[1, 2, 3, …n] , 也可以是[OOOOO…XXXXXXXXX], 或者[1,2,3,4, 10, 9, 8, 7, 5]

## 4.2 算法模版

### 4.2.1 基于while loop 实现

~~~java
/* 二分查找（双闭区间） */
int binarySearch(int[] nums, int target) {
    // 初始化双闭区间 [0, n-1] ，即 start, end 分别指向数组首元素、尾元素
    int start = 0, end = nums.length - 1;
    // 循环，当搜索区间为空时跳出（当 start > end 时为空）
    while (start <= end) {
        int m = start + (end - start) / 2; // 计算中点索引 m
        if (nums[m] < target) // 此情况说明 target 在区间 [m+1, end] 中
            start = m + 1;
        else if (nums[m] > target) // 此情况说明 target 在区间 [start, m-1] 中
            end = m - 1;
        else // 找到目标元素，返回其索引
            return m;
    }
    // 未找到目标元素，返回 -1
    return -1;
}

~~~

### 4.2.2 基于recurision实现

~~~java
    // Returns index of x if it is present in arr[l..r], else return -1
    int binarySearch(int arr[], int l, int r, int x)
    {
        if ( l <= r ) {
            int mid = l + (r - l) / 2;
            // If the element is present at the middle itself
            if (arr[mid] == x)
                return mid;
            // If element is smaller than mid, then it can only be present in left subarray
            if (arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
 
            // Else the element can only be present in right subarray
            return binarySearch(arr, mid + 1, r, x);
        }
        // We reach here when element is not present
        // in array
        return -1;
    }
~~~

## 4.3 总结



- 二分查找依赖数据的有序性，通过循环逐步缩减一半搜索区间来进行查找。它要求输入数据有序，且仅适用于数组或基于数组实现的数据结构。
- `if` 写不符合这个性质，把 `mid` 排除掉；`else` 就恰好是这个性质。
- 如果数组仅剩两个Element划分区间的逻辑是 `left = mid + 1;` 和 `right = mid;` 时，`while(left < right)` 退出循环以后 `left == right` 成立，此时 `mid = left + (right - left) /2`；
- 如果划分区间的逻辑是 `left = mid;` 和 `right = mid - 1;` 时，`while(left < right)` 退出循环以后 `left == right` 成立，此时为了避免死循环，`mid= (left + right + 1)/2` 







# 5. BFS

## 5.1 特点

- **适用于图的遍历，层级遍历，由点及面， 拓扑排序**

- **也适用于简单求最短路径（图）， 图中每条边长度都是1， 且没方向**

## 5.2 算法模版

~~~java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                results.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }

~~~



## 5.3 总结



# 6. Dynamic Programming

## 6.1 特点

- **DP 有重复计算， 可以基于Divide and Conquer + Memorization 实现，缺点是使用递归**
- **DP也可以通过多重循环实现，多重循环有Bottom Up(Iteration) 和Top Down(Recursion) 两种方式**

##   6.2 算法模版

### 6.2.1 从上往下实现通常使用递归和记忆化搜索

~~~java 
//自顶向下： 在解决斐波那契数列问题时，可以使用自顶向下的动态规划。定义递归函数 fib(n) 表示第 n 个斐波那契数，利用递归划分子问题。
public class TopDownDP {

    private int[] memo;

    public int solveProblem(int parameter) {
        // Initialize memoization array
        memo = new int[parameter + 1];
        Arrays.fill(memo, -1);

        // Call the recursive function
        return topDownHelper(parameter);
    }

    private int topDownHelper(int n) {
        // Base case
        if (n == 0) {
            return 0;
        }

        // Check if the result is already computed
        if (memo[n] != -1) {
            return memo[n];
        }

        // Recursive calls (subproblems)
        int result = someFunction(n);

        // Memoize the result
        memo[n] = result;

        return result;
    }

    private int someFunction(int n) {
        // Perform the actual computation for the problem
        // This can involve recursive calls to topDownHelper

        // Example: Fibonacci sequence
        return topDownHelper(n - 1) + topDownHelper(n - 2);
    }

    public static void main(String[] args) {
        TopDownDP topDownDP = new TopDownDP();
        int parameter = 5;
        int result = topDownDP.solveProblem(parameter);
        System.out.println("The result is: " + result);
    }
}

~~~

### 6.2.2 从下往上通常通过迭代实现

~~~java
//在解决背包问题时，可以使用自底向上的动态规划。定义一个二维数组 dp[i][j] 表示在前 i 个物品中，背包容量为 j 时的最优解，通过迭代填充动态规划表
public class KnapsackProblem {

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // Step 1: Define state
        int[][] dp = new int[n + 1][capacity + 1];

        // Step 2: Initialize state
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = 0;
            }
        }

        // Step 3: State transition
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // Step 4: Calculate result
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 8;
        int result = knapsackProblem.knapsack(weights, values, capacity);
        System.out.println("The maximum value in the knapsack is: " + result);
    }
}

~~~



## 6.3 总结

- 动态规划对问题进行分解，并通过存储子问题的解来规避重复计算，提高 计算效率。
- 不考虑时间的前提下，所有动态规划问题都可以用回溯（暴力搜索）进行求解，但递归树中存在大量的重叠子问题，效率极低。通过引入记忆化列表，可以存储所有计算过的子问题的解，从而保证重叠子问题只被计算一次。
- 记忆化递归是一种从顶至底的递归式解法，而与之对应的动态规划是一种从底至顶的递推式解法，其如同“填写表格”一样。由于当前状态仅依赖某些局部状态，因此我们可以消除 表的一个维度，从而降低空间复杂度。
- 子问题分解是一种通用的算法思路，在分治、动态规划、回溯中具有不同的性质。
- 动态规划问题有三大特性：重叠子问题、最优子结构、无后效性。
- 如果原问题的最优解可以从子问题的最优解构建得来，则它就具有最优子结构。
- 无后效性指对于一个状态，其未来发展只与该状态有关，而与过去经历的所有状态无关。许多组合优化问题不具有无后效性，无法使用动态规划快速求解。
- 动态规划（Dynamic Programming）和分治（Divide and Conquer）都是解决问题的常用算法范式，它们有一些相似之处，但也存在明显的区别。

  ### 相似之处：

  1. **问题划分：** 在两者中，问题都被划分为一些子问题，这些子问题可以独立求解。

  2. **递归求解：** 对子问题的解进行递归求解。

  ### 区别：

  1. **子问题重叠性：**
     - **动态规划：** 动态规划中，子问题通常具有重叠性，即相同的子问题可能会被多次求解。为了避免重复计算，动态规划使用一种记忆化的方式（例如使用数组或哈希表）来存储已经计算过的子问题的解。
     - **分治：** 分治通常不要求子问题有重叠性，因为分治每次都对问题进行完全划分，子问题之间没有共同的部分。

  2. **最优子结构：**
     - **动态规划：** 动态规划问题具有最优子结构性质，即全局最优解可以通过子问题的最优解来构建。
     - **分治：** 分治问题可以没有最优子结构，即子问题的最优解并不一定能够直接构建全局最优解。

  3. **问题求解方式：**
     - **动态规划：** 动态规划通常采用自底向上的迭代方式，从最小的子问题开始逐步构建解。
     - **分治：** 分治通常采用自顶向下的递归方式，将问题不断划分为子问题。

  4. **时间复杂度：**
     - **动态规划：** 动态规划常常具有较小的时间复杂度，因为它避免了重复计算。
     - **分治：** 分治在不具备重叠子问题性质时可能导致较高的时间复杂度。

  ### 典型应用举例：

  - **动态规划：** 最短路径问题、背包问题、字符串编辑距离等。
  - **分治：** 归并排序、快速排序。

  综上所述，动态规划和分治是两种不同的解决问题的策略，选择使用哪一种策略通常取决于问题的性质和具体的解题要求。



# 7 Two pointer

## 7.1 特点

## 7.2 算法模版

### 7.2.1 背向双指针(基本都是回文串问题，马拉车算法)

### 7.2.2 同向双指针(滑动窗口)

~~~java

  public String slidingWindow(String s, String t) {
      // 起始的时候，都位于 0，同方向移动
      int left = 0;
      int right = 0;
      while (right < sLen) {
          if ( 在右移的过程中检测是否满足条件 ) {
              // 对状态做修改，好让程序在后面检测到满足条件
          }
          // 右边界右移 1 格
          right++;
          while ( 满足条件 ) {
              // 走到这里是满足条件的，左边界逐渐逐渐左移，可以取最小值
              if ( 在左移的过程中检测是否不满足条件 ) {
                  // 对状态做修改，好让程序在后面检测到不满足条件
              }
              // 左边界左移 1 格
              left++;
          }
          // 走到这里是不满足条件的，右边界逐渐右移，可以取最大值
      }
      return 需要的结果变量;
  }
~~~



### 7.2.3 相向双指针(two sum 为基础的问题)



