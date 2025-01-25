然后设计一个挂单竞价系统


HR: Why Bloomberg. Most challenging problem.

EM: Most challenging problem. 简历。简历上的project是如何design的，目前的design有没有问题，为什么不用另外一种design


给一系列股票操作，比如[[9.1, BUY, 100 (shares), $50], [9.3, SELL, 50 (shares), $80]，但是30天内sell不能超过buy price（不能赚钱）问怎么判断是否valid；先假设只有buy，followup是如果有的有sell怎么判断


三面：

1. 给一些fail的节点，每个节点知道自己的children，找到最开始fail的那个节点
2. 类似 耳零零，但是找number of lakes

difference between rabbitmq and kafka（我project有用到）
