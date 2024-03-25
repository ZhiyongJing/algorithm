二分法 Binary Search

## 使用条件

- 排序数组（30-40% 是二分）
- 当面试官要求你找一个比 O(n)*O*(*n*) 更小的时间复杂度算法的时候（99%）
- 找到数组中的一个分割位置，使得左半部分满足某个条件，右半部分不满足（100%）
- 找到一个最大/最小的值使得某个条件被满足（90%）

## 复杂度

- 时间复杂度：O(logn)*O*(*l**o**g**n*)
- 空间复杂度：O(1)*O*(1)

## 代码模板

java

python

```java
1int binarySearch(int[] nums, int target) {
2    // corner case 处理
3    if (nums == null || nums.length == 0) {
4        return -1;
5    }
6    int start = 0, end = nums.length - 1;
7    // 要点 1: start + 1 < end
8    while (start + 1 < end) {
9        // 要点 2：start + (end - start) / 2
10        int mid = start + (end - start) / 2;
11        // 要点 3：=, <, > 分开讨论，mid 不 +1 也不 -1
12        if (nums[mid] == target) {
13            return mid;
14        } else if (nums[mid] < target) {
15            start = mid;
16        } else {
17            end = mid;
18        }
19    }
20
21    // 要点 4: 循环结束后，单独处理 start 和 end
22    if (nums[start] == target) {
23        return start;
24    }
25    if (nums[end] == target) {
26        return end;
27    }
28    return -1;
29}
```