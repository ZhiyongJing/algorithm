# 1. 算法思想

> **Two Pointer双指针算法**是一种通过设置**两个指针**不断进行**单向移动**来解决问题的算法。
>
> - 常见双指针算法分类
>   1. 同向（即两个指针都相同一个方向移动）， 滑动窗口
>      1. **并不严格要求数组只能包含非负数，但它在处理**某些类型的问题时**通常更适用于** **非负数或单调递增序列**
>   2. 背向（两个指针从相同或者相邻的位置出发，背向移动直到其中一根指针到达边界为止）
>   3. 相向（两个指针从两边出发一起向中间移动直到两个指针相遇

# 2 算法适用场景

> | **双指针类型**                            | **是否适用于非负数列表** | **是否适用于包含负数的列表** | **适用场景**                          |
> | ----------------------------------------------- | ------------------------------ | ---------------------------------- | ------------------------------------------- |
> | **同向双指针（滑动窗口）**                | ✅ 适用                        | ❌ 可能不适用                      | 滑动窗口、最长/最短子数组（仅适用于非负数） |
> | **相向双指针（Two Sum in Sorted Array）** | ✅ 适用                        | ✅ 适用                            | 适用于已排序数组的和、差问题                |
> | **背向双指针（回文检查）**                | ✅ 适用                        | ✅ 适用                            | 适用于字符串处理、回文检测                  |
>
> - **同向双指针（滑动窗口）在处理和相关的子数组问题时，通常只适用于非负数列表**，因为负数可能导致 `sum` 不单调变化，使 `left` 指针调整变得不可预测。
> - **相向双指针可以适用于负数列表，但要求数组是有序的**（如 Two Sum in Sorted Array）。
> - **背向双指针不受数值正负影响，适用于回文检查、对称性问题**。
> - 如果问题需要**精确匹配子数组的和**（如 `sum == k`），并且数组**包含负数**，那么**双指针可能无法保证正确性**，通常需要**前缀和 + 哈希表**来优化（如 **LeetCode 325**）。
>
> **建议：如何判断双指针是否适用？**
>
> 1. **如果问题可以通过单调调整窗口来优化（例如 `sum` 只会增加） → 适用于滑动窗口**。
> 2. **如果问题涉及有序数组查找（如 Two Sum in Sorted Array）→ 适用于相向双指针**。
> 3. **如果问题是对称性检查（如回文）→ 适用于背向双指针**。
> 4. **如果数组包含负数，并且需要精确求和 → 滑动窗口可能失效，使用前缀和 + 哈希表更优**。
>
> **总结一句话**：
> **双指针算法适用于非负数和负数列表，但某些情况下（如滑动窗口+负数），可能无法正常工作，需要使用前缀和或其他方法优化！**
>
> - 背向双指针算法
>
>> #### **✅ 适用于非负数列表，✅ 适用于负数列表**
>>
>> - **特点**：
>>
>>   - 两个指针从**中间向两侧扩展**，通常用于回文检查、寻找对称结构等问题。
>> - **适用场景**：
>>
>>   - **✅ 适用于非负数和负数列表**，因为它只依赖于数据的相对位置，而非数值大小。
>>   - **适用于字符串处理、回文检测等问题**。
>>   - 回文串问题（背向双指针）
>> - **示例**（适用于非负数和负数）：
>>   **LeetCode 125** - **验证回文串**
>>
>
> - 相向双指针算法
>
>> #### **✅ 适用于非负数列表，✅ 适用于负数列表**
>>
>> - **特点**：
>>
>>   - 一个指针 `left` 从左向右，另一个指针 `right` 从右向左。
>>   - 常用于**有序数组中的双指针问题**，比如 Two Sum 问题。
>> - **适用场景**：
>>
>>   - **✅ 适用于非负数和负数列表**，前提是数组是**已排序的**。
>>   - **❌ 如果数组无序，则相向双指针通常不可行**。
>>   - Two sum 问题（相向双指针，数据可能需要排序）
>> - **示例**（适用于有序数组，包括负数）：**LeetCode 167** - **两数之和 II**
>>
>>   - **适用于负数数组**，因为 `sum = numbers[left] + numbers[right]` 只受**有序性**影响，与数值正负无关。
>>
>
> - 同向双指针算法
>
>> #### **✅ 适用于非负数列表，❌ 可能不适用于包含负数的列表**
>>
>> - **特点**：
>>
>>   - 两个指针 `left` 和 `right` **从左向右移动**，`left` 始终不超过 `right`。
>>   - 常用于**滑动窗口（Sliding Window）**，比如最长子数组、最短子数组等问题。
>> - **适用场景**：
>>
>>   - **✅ 适用于非负数列表**（如 **LeetCode 209** 最小长度子数组和）
>>   - **❌ 在包含负数的情况下可能失效**，因为窗口和 `sum` 可能增大或减小，使得 `left` 无法正确调整
>>   - 子串substring问题（同向双指针）
>>   - 子数组subarray问题 （同向双指针）
>>   - LinkedList loop问题
>> - **示例**（适用于非负数）：**LeetCode 209** - **最小长度子数组和 ≥ k**
>>
>>   - **如果 `nums` 包含负数，上述代码可能失效**，因为 `sum` 可能随着 `right` 增大反而变小，`left` 指针的调整无法保证正确性。
>>

# 3. 算法模版

## 3.1 非递归方式实现

### 3.1.1 同向双指针，即滑动窗口

> | **情况**                              | **滑动窗口是否适用** | **原因**                                                 |
> | ------------------------------------------- | -------------------------- | -------------------------------------------------------------- |
> | **数组包含** 仅 **正数**        | ✅ 适用                    | `sum` 只会增大或维持，`left` 指针可以单调右移              |
> | **数组包含 0 和正数**                 | ✅ 适用                    | `sum` 可能保持不变，但不会减少，仍然可以调整窗口             |
> | **数组包含负数**（如 LeetCode 325）   | ❌**不适用**         | `sum` 可能增大也可能减小，无法保证 `left` 指针移动的正确性 |
> | **要求子数组的最长/最短长度**         | ✅ 适用                    | 主要是窗口长度的动态调整，不涉及求和的单调性                   |
> | **要求子数组的确切和（如 sum == k）** | ❌**不适用**         | 负数可能导致 `sum` 变化不确定，导致 `left` 无法正确调整    |
>
> **为什么 LeetCode 325 不能用滑动窗口**？
>
> 以 `nums = [-2, -1, 2, 1]`，`k = 1` 为例：
>
> - `[-2, -1]` 的和是 `-3`，扩展窗口：
> - `[-2, -1, 2]` 的和是 `-1`，扩展窗口：
> - `[-2, -1, 2, 1]` 的和是 `0`，**即使 `sum < k`，但无法简单地移动 `left` 以使 `sum == k`**。
> - 如果 `right` 指针继续移动，`sum` 可能因负数而减小，这时 `left` 是否移动就不确定了。
>
> 由于 `sum` 变化无法预测，**滑动窗口失效**，所以需要**前缀和 + HashMap**。
>
> ✅ **滑动窗口适用于**：
>
> - **非负数组**（递增或单调性问题）
> - **字符串匹配、子串统计问题**
> - **最大/最小满足条件的窗口长度问题**
>
> ❌ **滑动窗口不适用于**：
>
> - **数组包含负数，并且问题要求子数组的“确切和”**
> - **无法单调扩展/收缩窗口时**
> - **数组和的变化无法预测时（如 LeetCode 325）**
>
> **结论**：滑动窗口在 **非负数数组** 或 **单调递增性质的问题** 下最有效，而对于包含负数的**子数组求和问题**，通常需要 **前缀和 + HashMap** 进行优化。
>
> 1. **滑动窗口使用思路1（寻找最长）**
>
>    > ```java
>    > /**
>    >  * 滑动窗口使用思路（寻找最长无重复子串）
>    >  * ————核心：使用左右双指针（L, R），R 右移扩展窗口，L 右移缩小窗口
>    >  * ————每次滑动过程中：
>    >  *      - 如果窗口内元素满足条件，R 右移，并更新最长结果
>    >  *      - LOOP如果窗口内元素不满足条件，L 右移缩小窗口，直到满足条件
>    >  * ————直到 R 到达结尾，返回最长子串长度
>    >  *
>    >  * # 算法模版
>    >  *      1. 初始化 left，right，condition，bestResult；
>    >  *      2. 初始化 data 用于计算bestResult(如果left, right, condition可用于计算bestResult, 此步可省)
>    >  *      while("右指针没有到结尾"){
>    >  *          1. 添加right对应元素到当前condition；
>    >  *          2. 添加right对应元素到用于计算bestResult的data(基于问题，可省)；
>    >  *          while("带有right对应元素的condition不满足要求"){
>    >  *              1. condition移除left对应元素；
>    >  *              2. data移除left对应元素(基于问题)；
>    >  *              3. left右移；
>    >  *          }
>    >  *          1. 更新最优结果bestResult
>    >  *          2. right++;
>    >  *      }
>    >  *      返回bestResult
>    >  */
>    > //算法应用，Leetcode 3: longest substring without dup characters
>    > public int lengthOfLongestSubstring(String s) {
>    >       Map<Character, Integer> chars = new HashMap<>(); // 记录字符及其出现次数
>    >       int left = 0, right = 0, res = 0; // 初始化左指针、右指针、最长长度
>    >
>    >       while (right < s.length()) {
>    >           char r = s.charAt(right); // 获取右指针当前字符
>    >           chars.put(r, chars.getOrDefault(r, 0) + 1); // 更新字符频率
>    >
>    >           //不满足要求：当窗口内存在重复字符时，移动左指针直到窗口内无重复
>    >           while (chars.get(r) > 1) {
>    >               char l = s.charAt(left);
>    >               chars.put(l, chars.get(l) - 1);
>    >               left++;
>    >           }
>    >
>    >           // 更新最大长度
>    >           res = Math.max(res, right - left + 1);
>    >           right++;
>    >       }
>    >       return res;
>    >   }
>    > ```
>    >
>
> 2. **滑动窗口使用思路2（寻找最短）**
>
>    > ```java
>    > /**
>    >  * 滑动窗口使用思路（寻找最短子数组满足和 ≥ target）
>    >  * ————核心：使用左右双指针（L, R），R 右移扩展窗口，L 右移缩小窗口
>    >  * ————每次滑动过程中：
>    >  *      - 如果result不满足要求，右移 R 扩展窗口
>    >  *      - LOOP如果result满足要求，更新最优结果，并右移 L 缩小窗口
>    >  * ————直到 R 到达结尾，返回最小长度
>    >  * # 算法模版
>    >  *
>    >  *      1. 初始化 left，right，condition，bestResult；
>    >  *      2. 初始化 data 用于计算bestResult(如果left, right, condition可用于计算bestResult, 此步可省)
>    >  *
>    >  *      while("右指针没有到结尾"){
>    >  *          1. 添加right对应元素到当前condition；
>    >  *          2. 添加right对应元素到用于计算bestResult的data(基于问题，可省)；
>    >  *          while("带有right对应元素的condition满足要求"){
>    >  *              1. 更新最优结果bestResult；
>    >  *              2. condition移除left对应元素；
>    >  *              3. data移除left对应元素(基于问题，可省)；
>    >  *              4. left右移；
>    >  *          }
>    >  *          right++;
>    >  *      }
>    >  *
>    >  *      返回bestResult；
>    >  */
>    > //Leetcode 209数组中满足和 ≥target长度最小的连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] 
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
>    >
>
> 3. **滑动窗口使用思路3（寻找连续固定 K size subarray最值）**
>
>    > ```java
>    > /**
>    >  * 滑动窗口使用思路（寻找寻找固定K size 连续subarray最值）
>    >  * 1. 找出前K element 总值
>    >  * 2. for loop 遍历，找出固定K size 连续subarray最值
>    >  */
>    > //Leetcode 643 findMaxAverage
>    > public double findMaxAverage(int[] nums, int k) {
>    >     // 初始化前 k 个元素的和
>    >     int sum = 0;
>    >     for(int i = 0; i < k; i ++)
>    >         sum += nums[i];
>    > 
>    >     // 初始化最大和为当前的 sum
>    >     int maxSum = sum;
>    > 
>    >     // 使用滑动窗口遍历数组，从第 k 个元素开始
>    >     for(int i = k; i < nums.length; i ++) {
>    >         // 滑动窗口：加上新元素，减去窗口左端旧元素
>    >         sum += nums[i] - nums[i - k];
>    >         // 更新最大和
>    >         maxSum = Math.max(maxSum, sum);
>    >     }
>    > 
>    >     // 返回最大平均值（注意类型转换）
>    >     return (double)maxSum / k;
>    > }
>    > ```

### 3.1.2 相向双指针

> * 适用于连续数组和字符串，也就是说当你遇到题目给定连续数组和字符床时，应该第一时间想到用对撞指针解题。
> * 求范围， 求和问题
>
> ```java
> //算法模版
> public List<List<Integer>> find (int[] list, int target) {
>   // 1. 数组排序
>   Arrays.sort(nums);
>   List<List<Integer>> result = new ArrayList<>();
>   var left = 0;
>   var right = list.size() - 1;
>
>   // 2. 遍历数组
>   while (left < right) {
>     if(大于目标值){
>       right--;
>     }
>     else if(小于目标值){
>       left++;
>     }
>     else{ //等于目标值
>       //3. 添加到结果，继续找下一对
>       result.add(Arrarys.asList(list[left++], list[right--]));
>       //4. 跳过重复值
>       while(left < right && list[left] == list[left - 1]){
>         left++;
>       }
>     }
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
