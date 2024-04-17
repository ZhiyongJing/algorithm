1. 
   ![image-20240414225114951](Amazon.assets/image-20240414225114951.png)

2. 
   ![image-20240414225329756](Amazon.assets/image-20240414225329756.png)

3. 
   ![image-20240414225530053](Amazon.assets/image-20240414225530053.png)

4. ![image-20240414225908541](Amazon.assets/image-20240414225908541.png)

   ![image-20240414225925485](Amazon.assets/image-20240414225925485.png)

5. 
   ![image-20240414230123871](Amazon.assets/image-20240414230123871.png)

6. ![image-20240414230200252](Amazon.assets/image-20240414230200252.png)
   ![image-20240414230233675](Amazon.assets/image-20240414230233675.png)

7. 
   ![image-20240414230342816](Amazon.assets/image-20240414230342816.png)

8. ![image-20240414230441083](Amazon.assets/image-20240414230441083.png)
   ![image-20240414230511295](Amazon.assets/image-20240414230511295.png)

9. ![image-20240414230623799](Amazon.assets/image-20240414230623799.png)
   ![image-20240414230711915](Amazon.assets/image-20240414230711915.png)

10. 
    ![image-20240414230741085](Amazon.assets/image-20240414230741085.png)

11. 
    ![image-20240414230830353](Amazon.assets/image-20240414230830353.png)

12. (关键)
    ![image-20240414230914652](Amazon.assets/image-20240414230914652.png)

13. 阿玛粽 SDEII L5
    ![image-20240414231100556](Amazon.assets/image-20240414231100556.png)

14. 上周刚面完，四轮：
    第一轮 ood： 假设我们要给一个系统安装一个package A，然后A有几个dependence pacakge B，C，每个package又都有自己的dependence，首先要设计出package类，里面放个install()，然后再设计一个installUtility类，里面主要提供method installPackage(A)，然后遍历A的dependence，把每个都安装好后，最后安装package A。算法比较简单，就是常规的bfs，最后follow up是考虑每个package在三个不同的platform上都有不同的install()，其实就是再抽象一个接口，然后每个平台的package class都implement这个接口。这轮感觉很顺利，印度小哥口音有点听不懂但是人一直笑嘻嘻。
    广告
    Powered By


    PlayUnmute
    Fullscreen


    第二轮coding：输入是String[] logs, 每个log里有一个timestamp，customerid，pageid，输出是找到most visited 3 pages sequence，这里的sequence是每个人visit page的顺序的subarray，连续的。输入的时候是按照时间排序好的，所以就是用hashmap记录freq就行。follow up，要找most K visited m pages，用min heap做。这轮主要考察这俩数据结构。

    第三轮coding：lc夭厄器，要输出path，没用bfs，用dfs做的。

    design是设计一个订阅系统，主要就一个use case，如果对订阅的东西有更新，需要通知相关user，得到用户许可后才能给他更新，不然就cancel他的订阅。这轮感觉答的不好，面试官在面试中也没有什么反
    NY_Jimmy，您好！
    本帖隐藏的内容需要积分高于 188 才可浏览
    您当前积分为 155。
    VIP即刻解锁阅读权限 或 查看其他获取积分的方式

    04;‌‍‌‍‌‌‍‍‌‌‍‍‍‍‌‌得很顺。

    所以最后design反而是ok的，说明这轮要求并不高，面试官就是问了他想问的几个问题讨论了一下就够了，什么estimation，api，db schema都让我跳过了。

    整体感觉面的挺顺的，没想到两个coding给了negative feedback。

15. ![image-20240414231603988](Amazon.assets/image-20240414231603988.png)

16. ![image-20240414231813856](Amazon.assets/image-20240414231813856.png)

17. 
    ![image-20240414231847258](Amazon.assets/image-20240414231847258.png)

18. ![image-20240414232651578](Amazon.assets/image-20240414232651578.png)

19. ![image-20240414232734707](Amazon.assets/image-20240414232734707.png)

20. ![image-20240414232918702](Amazon.assets/image-20240414232918702.png)

21. ![image-20240414233029941](Amazon.assets/image-20240414233029941.png)

22. https://leetcode.com/discuss/interview-question/1481915/amazon-oa-count-distinct-characters-in-all-substrings

23. ![image-20240414234324140](Amazon.assets/image-20240414234324140.png)

    ![image-20240414234348890](Amazon.assets/image-20240414234348890.png)

24. 重点
    ![image-20240414234501151](Amazon.assets/image-20240414234501151.png)
    ![image-20240414234519039](Amazon.assets/image-20240414234519039.png)

25. ![image-20240414234708893](Amazon.assets/image-20240414234708893.png)

26. ![image-20240414234802670](Amazon.assets/image-20240414234802670.png)

27. ![image-20240414235116139](Amazon.assets/image-20240414235116139.png)

28. ![image-20240414235459227](Amazon.assets/image-20240414235459227.png)

29. ![image-20240414235629542](Amazon.assets/image-20240414235629542.png)