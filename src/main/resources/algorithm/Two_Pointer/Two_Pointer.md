# 1. 算法思想

> **Two Pointer双指针算法**是一种通过设置**两个指针**不断进行**单向移动**来解决问题的算法。
>
> - 常见双指针算法分类
>   1. 同向（即两个指针都相同一个方向移动）， 滑动窗口
>   2. 背向（两个指针从相同或者相邻的位置出发，背向移动直到其中一根指针到达边界为止）
>   3. 相向（两个指针从两边出发一起向中间移动直到两个指针相遇

# 2 算法适用场景

> - 回文串问题（背向双指针）
> - Two sum 问题（相向双指针，数据可能需要排序）
> - 子串substring问题（同向双指针）
> - 子数组subarray问题 （同向双指针）
> - LinkedList loop问题

# 3. 算法模版

## 3.1 非递归方式实现

### 3.1.1 同向双指针，即滑动窗口

> ```java
>//算法模版
> public String slidingWindow(String s, String t) {
>    // 起始的时候，都位于 0，同方向移动
>    int left = 0;
>       int right = 0;
>       while (right < sLen) {
>           if ( 在右移的过程中检测是否满足条件 ) {
>               // 对状态做修改，好让程序在后面检测到满足条件
>           }
>           // 右边界右移 1 格
>           right++;
>           while ( 满足条件 ) {
>               // 走到这里是满足条件的，左边界逐渐逐渐左移，可以取最小值
>               if ( 在左移的过程中检测是否不满足条件 ) {
>                   // 对状态做修改，好让程序在后面检测到不满足条件
>               }
>               // 左边界左移 1 格
>               left++;
>           }
>           // 走到这里是不满足条件的，右边界逐渐右移，可以取最大值
>       }
>       return 需要的结果变量;
>    }
>    
>   //算法应用，longest substring without dup characters
>  public int lengthOfLongestSubstring(String s) {
>      Map<Character, Integer> chars = new HashMap();
>    
>         int left = 0;
>      int right = 0;
>    
>         int res = 0;
>      while (right < s.length()) {
>             char r = s.charAt(right);
>             chars.put(r, chars.getOrDefault(r, 0) + 1);
>    
>             while (chars.get(r) > 1) {
>              char l = s.charAt(left);
>                 chars.put(l, chars.get(l) - 1);
>                 left++;
>             }
>    
>             res = Math.max(res, right - left + 1);
> 
>             right++;
>      }
>         return res;
>     }
>    ```
>    

### 3.1.2 相向双指针

> * 适用于连续数组和字符串，也就是说当你遇到题目给定连续数组和字符床时，应该第一时间想到用对撞指针解题。
> * 求范围， 求和问题
>
> ```java
> //算法模版
> public void find (int[] list) {
>   var left = 0;
>   var right = list.length - 1;
> 
>   //遍历数组
>   while (left < right) {
>     left++;
>     // 一些条件判断 和处理
>     ... ...
>     right--;
>   }
> }
> 
> //算法应用，Two Sum II - Input Array Is Sorted
> public int[] twoSum(int[] numbers, int target) {
>         int low = 0;
>         int high = numbers.length - 1;
>         while (low < high) {
>             int sum = numbers[low] + numbers[high];
> 
>             if (sum == target) {
>                 return new int[] { low + 1, high + 1 };
>             } else if (sum < target) {
>                 ++low;
>             } else {
>                 --high;
>             }
>         }
>         // In case there is no solution, return {-1, -1}.
>         return new int[] { -1, -1 };
>     }
> 
> ```
>

### 3.1.3 背向双指针， 即马拉车算法(Manacher)



## 3.2 非递归方式实现

> - 无

# 4. 算法复杂度

> - 时间复杂度：O(N)
> - 空间复杂度: O(1)

