# 1. 算法思想

> 回文串可分为奇数回文串和偶数回文串，它们的区别是：奇数回文串关于它的“中点”满足“中心对称”，偶数回文串关于它“中间的两个点”满足“中心对称”。为了避免对于回文串字符个数为奇数还是偶数的套路。首先对原始字符串进行预处理，方法也很简单：添加分隔符

# 2 算法适用场景

> - 专门用于解决“最长回文子串”问题，时间复杂度为 O(n)

# 3. 算法模版

## 3.1 非递归方式实现

> #### 第 1 步：对原始字符串进行预处理（添加分隔符）
>
> 我们先给出具体的例子，看看如何添加分隔符。
>
> 例1：给字符串 `"level"` 添加分隔符 `"#"`。
>
> 答：字符串 `"level"` 添加分隔符 `"#"` 以后得到：`"#le#v#e#l#"`。
>
> 例2：给字符串 `"noon"` 添加分隔符 `"#"`。
>
> 答：字符串 `"noon"` 添加分隔符 `"#"` 以后得到：`"#n#o#o#n#"`。
>
> 我想你已经看出来分隔符是如何添加的，下面是两点说明。
>
> 1、分隔符是一定是原始字符串中没有出现过的字符，这个分隔符的种类也只能有一个，即你不能同时添加 `"#"` 和 `"?"` 作为分隔符；
>
> 2、添加分隔符的方法是在字符串的首位置、尾位置和每个字符的“中间”都添加 11 个这个分隔符。可以很容易知道，如果这个字符串的长度是 `len`，那么添加的分隔符的个数就是 `len + 1`，得到的新的字符串的长度就是 `2 * len + 1`，**显然它一定是奇数**。
>
> 为什么要添加分隔符？
>
> 1、首先是正确性：添加了分隔符以后的字符串的回文性质与原始字符串是一样的（这句话不是很严谨，大家意会即可）；
>
> 2、其次是避免回文串长度奇偶性的讨论（马上我们就会看到这一点是如何体现的）。
>
> #### [#](https://liweiwei1419.github.io/leetcode-solution-blog/leetcode-problemset/dynamic-programming/0005-longest-palindromic-substring.html#第-2-步：得到-p-数组)第 2 步：得到 p 数组
>
> p 数组可以通过填表得到。以字符串 `"abbabb"` 为例，说明如何手动计算得到 p 数组。假设我们要填的就是下面这张表。
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     |       |       |       |       |       |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> 第 1 行 char 数组：这个数组是原始字符串**加上分隔符以后**的字符构成的数组。
>
> 第 2 行 index 数组：这个数组是char 数组的索引数组，我们后面要利用到它，它的值就是索引编号，从 00 开始。
>
> 下面我们来看看 p 数组应该如何填写。首先我们定义**“回文半径”**。
>
> **回文半径**：以 `char[i]` 作为回文中心，同时向左边、向右边进行“中心扩散”，直到“不能构成回文串”或“触碰到字符串边界”为止，**能扩散的步数** **+ 1（这个 1 表示“中心”）** ，即定义为 p 数组的值，也称之为“回文半径。
>
> - 用上面的例子，我们首先填 `p[0]`。
>
> 以 `char[0] = '#'`为中心，同时向左边向右扩散，走 1 步就碰到边界了，因此“能扩散的步数”为0，“能扩散的步数 + 1 = 1”，因此 `p[0] = 1`；
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | **1** |       |       |       |       |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> - 下面填写 `p[1]` 。
>
> 以 `char[1] = 'a'` 为中心，同时向左边向右扩散，走 1 步，左右都是 `"#"`，构成回文子串，于是再继续同时向左边向右边扩散，左边就碰到边界了，因此“能扩散的步数”为1，“能扩散的步数 + 1 = 2”，因此 `p[1] = 2`；
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | **2** |       |       |       |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> - 下面填写 `p[2]` 。
>
> 以 `char[2] = '#'` 为中心，同时向左边向右扩散，走 1 步，左边是 `"a"`，右边是 `"b"`，不匹配，因此“能扩散的步数”为 00，“能扩散的步数 + 1 = 1”，因此 `p[2] = 1`；
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | 2     | **1** |       |       |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> - 下面填写 `p[3]`。
>
> 以 `char[3] = 'b'` 为中心，同时向左边向右扩散，走 1 步，左右两边都是 `“#”`，构成回文子串，继续同时向左边向右扩散，左边是 `"a"`，右边是 `"b"`，不匹配，因此“能扩散的步数”为 1 ，“能扩散的步数 + 1 = 2”，因此 `p[3] = 2`；
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | 2     | 1     | **2** |       |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> - 下面填写 `p[4]`。
>
> 以 `char[4] = '#'` 为中心，同时向左边向右扩散，最多可以走 4 步，左边到达边界，因此“能扩散的步数”为4，“能扩散的步数 + 1 = 5”，因此 `p[4] = 5`。
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | 2     | 1     | 2     | **5** |       |       |       |       |       |       |       |       |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> - 继续填完 p 数组剩下的部分。
>
> 分析到这里，后面的数字不难填出，最后写成如下表格：
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | 2     | 1     | 2     | 5     | 2     | 1     | 6     | 1     | 2     | 3     | 2     | 1     |
> | **p-1**   |       |       |       |       |       |       |       |       |       |       |       |       |       |
>
> p-1 数组很简单了，把 p 数组对应位置的值 -1 就行了。是不是觉得有点“绕”，刚才每一步要 + 1 ，现在每一步要 -1，这不是吃饱了撑的吗？你说的没错。这里得到 p 数组不过就是为了给“回文半径”一个定义而已。
>
> 即：p 数组可以称之为“回文半径数组”。
>
> 于是我们得到如下表格：
>
> | **char**  | **#** | **a** | **#** | **b** | **#** | **b** | **#** | **a** | **#** | **b** | **#** | **b** | **#** |
> | --------- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- | ----- |
> | **index** | 0     | 1     | 2     | 3     | 4     | 5     | 6     | 7     | 8     | 9     | 10    | 11    | 12    |
> | **p**     | 1     | 2     | 1     | 2     | 5     | 2     | 1     | 6     | 1     | 2     | 3     | 2     | 1     |
> | **p-1**   | 0     | 1     | 0     | 1     | 4     | 1     | 0     | 5     | 0     | 1     | 2     | 1     | 0     |
>
> - p 数组的最大值是 66 ，对应的 “最长回文子串” 是 `"#b#b#a#b#b#"`；
> - p - 1 数组的最大值是 55，就对应了原字符串 `"abbabb"` 的 “最长回文子串” ：`"bbabb"`。
>
> 规律如下：
>
> > p -1 数组的最大值就是“最长回文子串”的长度。即“最大回文半径”知道了，减 1 就是原字符串的“最长回文子串”的长度。
>
> - 可以在得到 p 数组的过程中记录这个最大值，并且记录最长回文子串。
>
> 
>
> 那么如何编写程序得到 p 数组呢？其实也不难，即**使用“回文串”的性质避免重复计算**。下面这张图展示了这个思想
>
> ![image.png](/Users/zhiyongjing/Repo/algorithm/src/main/resources/algorithm/Manacher.assets/1.png)
>
> 上面这张图画得仔细一点是下面这张图：
>
> ![img.png](/Users/zhiyongjing/Repo/algorithm/src/main/resources/algorithm/Manacher.assets/2.png)
>
> 把上面说的整理一下，当 `p[j]` 的范围很小的时候，有 `p[i] = p[j]`，`p[i]` 的值再大不过超过 `mx - i` ：
>
> ```python
> if i < mx:
>     p[i] = min(p[2 * id - i], mx - i);
> ```
>
> 2、当 `i >= mx` 的时候，此时也只能老老实实使用“中心扩散法”来逐渐得到 p 数组的值，同时记录 `id` 和 `mx`。
>
> 以上可以合并成一行代码：
>
> ```
> p[i] = mx > i ? min(p[2 * id - i], mx - i) : 1;
> ```
>
> 这一行代码拆开来看就是：
>
> 如果 `mx > i`, 则 `p[i] = min(p[2 * id - i], mx - i)`，否则 `p[i] = 1`。

~~~java
public class Solution {

    /**
     * 创建分隔符分割的字符串
     *
     * @param s      原始字符串
     * @param divide 分隔字符
     * @return 使用分隔字符处理以后得到的字符串
     */
    private String generateSDivided(String s, char divide) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        if (s.indexOf(divide) != -1) {
            throw new IllegalArgumentException("参数错误，您传递的分割字符，在输入字符串中存在！");
        }
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append(divide);
        for (int i = 0; i < len; i++) {
            sBuilder.append(s.charAt(i));
            sBuilder.append(divide);
        }
        return sBuilder.toString();
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len == 0) {
            return "";
        }
        String sDivided = generateSDivided(s, '#');
        int slen = sDivided.length();
        int[] p = new int[slen];
        int mx = 0;
        // id 是由 mx 决定的，所以不用初始化，只要声明就可以了
        int id = 0;
        int longestPalindrome = 1;
        String longestPalindromeStr = s.substring(0, 1);
        for (int i = 0; i < slen; i++) {
            if (i < mx) {
                // 这一步是 Manacher 算法的关键所在，一定要结合图形来理解
                // 这一行代码是关键，可以把两种分类讨论的情况合并
                p[i] = Integer.min(p[2 * id - i], mx - i);
            } else {
                // 走到这里，只可能是因为 i = mx
                if (i > mx) {
                    throw new IllegalArgumentException("程序出错！");
                }
                p[i] = 1;
            }
            // 老老实实去匹配，看新的字符
            while (i - p[i] >= 0 && i + p[i] < slen && sDivided.charAt(i - p[i]) == sDivided.charAt(i + p[i])) {
                p[i]++;
            }
            // 我们想象 mx 的定义，它是遍历过的 i 的 i + p[i] 的最大者
            // 写到这里，我们发现，如果 mx 的值越大，
            // 进入上面 i < mx 的判断的可能性就越大，这样就可以重复利用之前判断过的回文信息了
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }

            if (p[i] - 1 > longestPalindrome) {
                longestPalindrome = p[i] - 1;
                longestPalindromeStr = sDivided.substring(i - p[i] + 1, i + p[i]).replace("#", "");
            }
        }
        return longestPalindromeStr;
    }
}
~~~

# 4. 算法复杂度

> - 时间复杂度：*O*(*N*)，由于 Manacher 算法只有在遇到还未匹配的位置时才进行匹配，已经匹配过的位置不再匹配，所以对于对于字符串`S` 的每一个位置，都只进行一次匹配，所以算法的总体复杂度为 O(N)*O*(*N*)。
> - 空间复杂度：O*(*N)
