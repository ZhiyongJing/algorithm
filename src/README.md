[TOC]

- 最短路径， 一般用BFS 或者 DFS 解决
- 最长路径， 一般用DFS 或者DP解决

# 1. DFS

## 1.1 特点

- **一般用于找出所有方案**

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







# 2. Back Tracking

## 2.1 特点

- **基于无返回值DFS**
- **Top Down**

## 2.2 算法模版

~~~java
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return

    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
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
- **Bottom Up**

## 3.1 特点

## 3.2 算法模版

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
- **DP也可以通过多重循环实现，多重循环有Bottom Up 和Top Down 两种方式**

##   6.2 算法模版

## 6.3 总结

- 动态规划对问题进行分解，并通过存储子问题的解来规避重复计算，提高 计算效率。
- 不考虑时间的前提下，所有动态规划问题都可以用回溯（暴力搜索）进行求解，但递归树中存在大量的重叠子问题，效率极低。通过引入记忆化列表，可以存储所有计算过的子问题的解，从而保证重叠子问题只被计算一次。
- 记忆化递归是一种从顶至底的递归式解法，而与之对应的动态规划是一种从底至顶的递推式解法，其如同“填写表格”一样。由于当前状态仅依赖某些局部状态，因此我们可以消除 表的一个维度，从而降低空间复杂度。
- 子问题分解是一种通用的算法思路，在分治、动态规划、回溯中具有不同的性质。
- 动态规划问题有三大特性：重叠子问题、最优子结构、无后效性。
- 如果原问题的最优解可以从子问题的最优解构建得来，则它就具有最优子结构。
- 无后效性指对于一个状态，其未来发展只与该状态有关，而与过去经历的所有状态无关。许多组合优化问题不具有无后效性，无法使用动态规划快速求解。

