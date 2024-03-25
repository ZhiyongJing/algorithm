双指针 Two Pointers

## 使用条件

- 滑动窗口（90%）
- 时间复杂度要求 O(n)*O*(*n*) （80%是双指针）
- 要求原地操作，只可以使用交换，不能使用额外空间（80%）
- 有子数组 `subarray` / 子字符串 `substring` 的关键词（50%）
- 有回文 `Palindrome` 关键词（50%）

## 复杂度

- 时间复杂度：O(n)*O*(*n*)
  - 时间复杂度与最内层循环主体的执行次数有关
  - 与有多少重循环无关
- 空间复杂度：O(1)*O*(1)
  - 只需要分配两个指针的额外内存

## 代码模板

java

python

```java
1// 相向双指针(patition in quicksort)
2public void partition(int[] A, int start, int end) {
3    if (start >= end) {
4        return;
5    }
6    int left = start, right = end;
7    // key point 1: pivot is the value, not the index
8    int pivot = A[(start + end) / 2];
9    // key point 2: every time you compare left & right, it should be
10    // left <= right not left < right
11    while (left <= right) {
12        while (left <= right && A[left] < pivot) {
13            left++;
14        }
15        while (left <= right && A[right] > pivot) {
16            right--;
17        }
18        if (left <= right) {
19            int temp = A[left];
20            A[left] = A[right];
21            A[right] = temp;
22            left++;
23            right--;
24        }
25    }
26
27}
28/* 
29    // 背向双指针
30    left = position; 
31    right = position + 1;
32    while (left >= 0 && right < length) {
33        if (可以停下来了) {
34            break;
35        }
36        left--;
37        right++;
38    }
39    // 同向双指针
40    int j = 0;
41    for (int i = 0; i < n; i++) {
42        // 不满足则循环到满足搭配为止
43        while (j < n && i 到 j 之间不满足条件) {
44            j += 1;
45        }
46        if (i 到 j 之间满足条件) {
47            处理 i, j 这次搭配 
48        }
49    }
50    // 合并双指针
51    ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
52        // 需要 new 一个新的 list, 而不是在 list1 或者 list2 上直接改动
53        ArrayList<Integer> newList = new ArrayList<Integer>();
54        int i = 0, j = 0;
55        while (i < list1.size() && j < list2.size()) {
56            if (list1.get(i) < list2.get(j)) {
57                newList.add(list1.get(i));
58                i++;
59            } else {
60                newList.add(list2.get(j);
61                j++;
62            }
63        }
64        // 合并上下的数到 newList 里
65        // 无需用 if (i < list1.size())，直接 while 即可
66        while (i < list1.size()) {
67            newList.add(list1.get(i));
68            i++;
69        }
70        while (j < list2.size()) {
71            newList.add(list2.get(j);
72            j++;
73        }
74        return newList;
75    }
76    */
```