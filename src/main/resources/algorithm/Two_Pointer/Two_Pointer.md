# 1. 算法思想

> **Two Pointer双指针算法**是一种通过设置**两个指针**不断进行**单向移动**来解决问题的算法。
>
> - 常见双指针算法分类
>   1. 同向（即两个指针都相同一个方向移动）， 滑动窗口
>   2. 背向（两个指针从相同或者相邻的位置出发，背向移动直到其中一根指针到达边界为止）
>   3. 相向（两个指针从两边出发一起向中间移动直到两个指针相遇

# 2 算法适用场景

> - 背向双指针算法
>
>   > - 回文串问题（背向双指针）
>
> - 相向双指针算法
>
>   > - Two sum 问题（相向双指针，数据可能需要排序）
>
> - 同向双指针算法
>
>   > - 子串substring问题（同向双指针）
>   > - 子数组subarray问题 （同向双指针）
>   > - LinkedList loop问题

# 3. 算法模版

## 3.1 非递归方式实现

### 3.1.1 同向双指针，即滑动窗口

> 1. **滑动窗口使用思路1（寻找最长）** 
>
>    > ```java
>    > /**
>    >  * 滑动窗口使用思路1（寻找最长）
>    >  * ————核心：左右双指针(L,R)在起始点，R向右逐位滑动循环
>    >  * ————每次滑动过程中
>    >  * 如果：窗内元素满足条件，R向右扩大窗口，并更新最优结果
>    >  * 如果：窗内元素不满足条件，L向右缩小窗口
>    >  * ————R到达结尾
>    >  *
>    >  * 初始化 left，right，result，bestResult
>    >  *     while("右指针没有到结尾"){
>    >  *         窗口扩大，加入right对应元素，更新当前result
>    >  *         while("result不满足要求"){
>    >  *             窗口缩小，移除left对应元素，left右移
>    >  *         }
>    >  *         更新最优结果bestResult
>    >  *         right++;
>    >  *     }
>    >  *     返回bestResult
>    >  */
>    > //算法应用，longest substring without dup characters
>    > public int lengthOfLongestSubstring(String s) {
>    >     Map<Character, Integer> chars = new HashMap();
>    >     int left = 0;
>    >     int right = 0;
>    >     int res = 0;
>    >     while (right < s.length()) {
>    >         char r = s.charAt(right);
>    >         chars.put(r, chars.getOrDefault(r, 0) + 1);
>    > 
>    >         while (chars.get(r) > 1) {
>    >             char l = s.charAt(left);
>    >             chars.put(l, chars.get(l) - 1);
>    >             left++;
>    >         }
>    > 
>    >         res = Math.max(res, right - left + 1);
>    >         right++;
>    >     }
>    >     return res;
>    > }
>    > ```
>
> 2. **滑动窗口使用思路2（寻找最短）**
>
>    > ```java
>    > /**
>    >  * 滑动窗口使用思路2（寻找最短）
>    >  * ————核心：左右双指针(L,R)在起始点，R向右逐位滑动循环
>    >  * ————每次滑动过程中
>    >  * 如果：窗内元素满足条件，L向右缩小窗口，并更新最优结果
>    >  * 如果：窗内元素不满足条件，R向右扩大窗口
>    >  * ————R到达结尾
>    >  *
>    >  * # 算法模版
>    >  * 初始化 left，right，result，bestResult
>    >  * while("右指针没有到结尾"){
>    >  *     窗口扩大，加入right对应元素，更新当前result
>    >  *     while("result满足要求"){
>    >  *         更新最优结果bestResult
>    >  *         窗口缩小，移除left对应元素，left右移
>    >  *     }
>    >  *     right++;
>    >  * }
>    >  * 返回bestResult
>    >  */
>    > //算法应用， 该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] 
>    > public int minSubArrayLen(int target, int[] nums) {
>    >     // 初始化 left，right，result，bestResult
>    >     // result是当前的currSum、bestResult为mixLength
>    >     int left = 0, right = 0,currSum = 0, mixLength = 0;
>    >     // 右指针没有到结尾
>    >     while(right < nums.length){
>    >         // 窗口扩大，加入right对应元素，更新当前
>    >         currSum += nums[right];
>    >         // result满足要求
>    >         while(currSum >= target){
>    >             // 更新最优结果mixLength
>    >             if(right - left + 1 < mixLength || mixLength == 0){
>    >                 mixLength = right - left + 1;
>    >             }
>    >             // 窗口缩小，移除left对应元素，left右移
>    >             currSum = currSum - nums[left];
>    >             left++;
>    >         }
>    >         // right++
>    >         right++;
>    >     }
>    >     // 返回mixLength
>    >     return mixLength;
>    > }
>    > ```

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
>     int low = 0;
>     int high = numbers.length - 1;
>     while (low < high) {
>         int sum = numbers[low] + numbers[high];
>
>         if (sum == target) {
>             return new int[]{low + 1, high + 1};
>         } else if (sum < target) {
>             ++low;
>         } else {
>             --high;
>         }
>     }
>     // In case there is no solution, return {-1, -1}.
>     return new int[]{-1, -1};
> }
>
> ```

### 3.1.3 背向双指针， 即马拉车算法(Manacher)

# 4. 算法复杂度

> - 时间复杂度：O(N)
> - 空间复杂度: O(1)
