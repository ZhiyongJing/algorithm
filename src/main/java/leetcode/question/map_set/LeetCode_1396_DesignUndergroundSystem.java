package leetcode.question.map_set;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 *@Question:  1396. Design Underground System
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 28.23%
 *@Time  Complexity: O(1)
 *@Space Complexity: O(P + S^2) S is the number of stations on the network,
 * and P is the number of passengers making a journey concurrently during peak time.
 */

/**
 * ### 解题思路
 *
 * 这个问题要求我们设计一个地铁系统，能够记录乘客的进站和出站时间，并计算特定路线的平均行程时间。
 * 我们可以使用两个哈希表来存储相关数据：一个用于记录乘客的进站信息，另一个用于记录每条路线的总行程时间和总次数。具体的解题思路如下：
 *
 * 1. **数据结构设计**：
 *    - `checkInData`：使用 `HashMap` 存储每个乘客的 `check-in` 信息，键是乘客的 ID，值是一个 `Pair` 对象，包含进站站名和进站时间。
 *    - `journeyData`：使用 `HashMap` 存储每条路线的总行程时间和总次数，
 *    键是路线的唯一标识（由起点站和终点站组合而成），值是一个 `Pair` 对象，包含总行程时间和行程次数。
 *
 * 2. **方法**：
 *    - `checkIn(int id, String stationName, int t)`：记录乘客的 `check-in` 信息，将其存储到 `checkInData` 中。
 *    - `checkOut(int id, String stationName, int t)`：获取乘客的 `check-in` 信息，计算乘客的行程时间，更新对应路线的总行程时间和总次数到 `journeyData`，然后删除乘客的 `check-in` 信息。
 *    - `getAverageTime(String startStation, String endStation)`：根据 `journeyData` 中存储的数据，计算并返回给定路线的平均行程时间。
 *    - `stationsKey(String startStation, String endStation)`：生成唯一的路线标识符，用于存储和查询 `journeyData` 中的路线数据。
 *
 * ### 时间复杂度
 *
 * - **`checkIn` 方法**：
 *   - 插入操作的时间复杂度是 O(1)，因为 `HashMap` 的插入操作平均时间复杂度是 O(1)。
 *
 * - **`checkOut` 方法**：
 *   - 获取乘客的 `check-in` 信息、更新 `journeyData` 和删除 `checkInData` 中的信息，这些操作都是 O(1)，因为 `HashMap` 的查询和删除操作平均时间复杂度是 O(1)。
 *
 * - **`getAverageTime` 方法**：
 *   - 查询 `journeyData` 并计算平均时间，这些操作都是 O(1)，因为 `HashMap` 的查询操作平均时间复杂度是 O(1)。
 *
 * ### 空间复杂度
 *
 * - **`checkInData` 和 `journeyData`**：
 *   - 最坏情况下，`checkInData` 的空间复杂度是 O(N)，其中 N 是乘客的数量，因为每个乘客可能都有一个未完成的 `check-in` 记录。
 *   - `journeyData` 的空间复杂度是 O(M)，其中 M 是不同路线的数量，因为我们为每条路线存储了总行程时间和行程次数。
 *
 * 综合以上分析，这个设计能够高效地记录和查询地铁系统中的乘客行程时间和平均行程时间。
 */
public class LeetCode_1396_DesignUndergroundSystem{

    //leetcode submit region begin(Prohibit modification and deletion)
    class UndergroundSystem {
        // 存储每条路线的总时间和总次数 路线，总时间，次数
        private Map<String, Pair<Double, Integer>> journeyData = new HashMap<>();
        // 存储每个乘客的check-in信息
        private Map<Integer, Pair<String, Integer>> checkInData = new HashMap<>();

        public UndergroundSystem() {
            // 构造函数，初始化数据结构
        }

        public void checkIn(int id, String stationName, int t) {
            // 记录乘客的check-in信息
            checkInData.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            // 获取乘客的check-in信息
            Pair<String, Integer> checkInDataForId = checkInData.get(id);
            String startStation = checkInDataForId.getKey();
            Integer checkInTime = checkInDataForId.getValue();

            // 生成路线的key
            String routeKey = stationsKey(startStation, stationName);
            // 获取当前路线的统计数据
            Pair<Double, Integer> routeStats  = journeyData.getOrDefault(routeKey, new Pair<>(0.0, 0));
            Double totalTripTime = routeStats.getKey();
            Integer totalTrips = routeStats.getValue();

            // 更新路线的总时间和总次数
            double tripTime = t - checkInTime;
            journeyData.put(routeKey, new Pair<>(totalTripTime + tripTime, totalTrips + 1));

            // 删除乘客的check-in信息
            checkInData.remove(id);
        }

        public double getAverageTime(String startStation, String endStation) {
            // 获取路线的总时间和总次数
            String routeKey = stationsKey(startStation, endStation);
            Double totalTime = journeyData.get(routeKey).getKey();
            Integer totalTrips = journeyData.get(routeKey).getValue();
            // 计算平均时间
            return totalTime / totalTrips;
        }

        private String stationsKey(String startStation, String endStation) {
            // 生成唯一的路线key
            return startStation + "->" + endStation;
        }
    }

    /**
     * Your UndergroundSystem object will be instantiated and called as such:
     * UndergroundSystem obj = new UndergroundSystem();
     * obj.checkIn(id,stationName,t);
     * obj.checkOut(id,stationName,t);
     * double param_3 = obj.getAverageTime(startStation,endStation);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 创建UndergroundSystem对象
        UndergroundSystem undergroundSystem = new LeetCode_1396_DesignUndergroundSystem().new UndergroundSystem();

        // 添加测试样例
        undergroundSystem.checkIn(1, "A", 3);
        undergroundSystem.checkIn(2, "A", 8);
        undergroundSystem.checkOut(1, "B", 10);
        undergroundSystem.checkOut(2, "B", 16);

        // 测试获取平均时间
        System.out.println(undergroundSystem.getAverageTime("A", "B")); // 应该输出7.5

        undergroundSystem.checkIn(3, "A", 17);
        undergroundSystem.checkOut(3, "B", 27);

        // 测试获取平均时间
        System.out.println(undergroundSystem.getAverageTime("A", "B")); // 应该输出8.0
    }
}

/**
An underground railway system is keeping track of customer travel times between 
different stations. They are using this data to calculate the average time it 
takes to travel from one station to another. 

 Implement the UndergroundSystem class: 

 
 void checkIn(int id, string stationName, int t) 
 

 
 A customer with a card ID equal to id, checks in at the station stationName at 
time t. 
 A customer can only be checked into one place at a time. 
 
 
 void checkOut(int id, string stationName, int t)
 
 A customer with a card ID equal to id, checks out from the station stationName 
at time t. 
 
 
 double getAverageTime(string startStation, string endStation)
 
 Returns the average time it takes to travel from startStation to endStation. 
 The average time is computed from all the previous traveling times from 
startStation to endStation that happened directly, meaning a check in at startStation 
followed by a check out from endStation. 
 The time it takes to travel from startStation to endStation may be different 
from the time it takes to travel from endStation to startStation. 
 There will be at least one customer that has traveled from startStation to 
endStation before getAverageTime is called. 
 
 


 You may assume all calls to the checkIn and checkOut methods are consistent. 
If a customer checks in at time t1 then checks out at time t2, then t1 < t2. All 
events happen in chronological order. 

 
 Example 1: 

 
Input
["UndergroundSystem","checkIn","checkIn","checkIn","checkOut","checkOut",
"checkOut","getAverageTime","getAverageTime","checkIn","getAverageTime","checkOut",
"getAverageTime"]
[[],[45,"Leyton",3],[32,"Paradise",8],[27,"Leyton",10],[45,"Waterloo",15],[27,
"Waterloo",20],[32,"Cambridge",22],["Paradise","Cambridge"],["Leyton","Waterloo"],
[10,"Leyton",24],["Leyton","Waterloo"],[10,"Waterloo",38],["Leyton","Waterloo"]]


Output
[null,null,null,null,null,null,null,14.00000,11.00000,null,11.00000,null,12.0000
0]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(45, "Leyton", 3);
undergroundSystem.checkIn(32, "Paradise", 8);
undergroundSystem.checkIn(27, "Leyton", 10);
undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" -> 
"Waterloo" in 15-3 = 12
undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" -> 
"Waterloo" in 20-10 = 10
undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" -> 
"Cambridge" in 22-8 = 14
undergroundSystem.getAverageTime("Paradise", "Cambridge"); // return 14.00000. 
One trip "Paradise" -> "Cambridge", (14) / 1 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000. 
Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11
undergroundSystem.checkIn(10, "Leyton", 24);
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 11.00000
undergroundSystem.checkOut(10, "Waterloo", 38);  // Customer 10 "Leyton" -> 
"Waterloo" in 38-24 = 14
undergroundSystem.getAverageTime("Leyton", "Waterloo");    // return 12.00000. 
Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12
 

 Example 2: 

 
Input
["UndergroundSystem","checkIn","checkOut","getAverageTime","checkIn","checkOut",
"getAverageTime","checkIn","checkOut","getAverageTime"]
[[],[10,"Leyton",3],[10,"Paradise",8],["Leyton","Paradise"],[5,"Leyton",10],[5,
"Paradise",16],["Leyton","Paradise"],[2,"Leyton",21],[2,"Paradise",30],["Leyton",
"Paradise"]]

Output
[null,null,null,5.00000,null,null,5.50000,null,null,6.66667]

Explanation
UndergroundSystem undergroundSystem = new UndergroundSystem();
undergroundSystem.checkIn(10, "Leyton", 3);
undergroundSystem.checkOut(10, "Paradise", 8); // Customer 10 "Leyton" -> 
"Paradise" in 8-3 = 5
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.00000, (5) /
 1 = 5
undergroundSystem.checkIn(5, "Leyton", 10);
undergroundSystem.checkOut(5, "Paradise", 16); // Customer 5 "Leyton" -> 
"Paradise" in 16-10 = 6
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 5.50000, (5 + 
6) / 2 = 5.5
undergroundSystem.checkIn(2, "Leyton", 21);
undergroundSystem.checkOut(2, "Paradise", 30); // Customer 2 "Leyton" -> 
"Paradise" in 30-21 = 9
undergroundSystem.getAverageTime("Leyton", "Paradise"); // return 6.66667, (5 + 
6 + 9) / 3 = 6.66667
 

 
 Constraints: 

 
 1 <= id, t <= 10⁶ 
 1 <= stationName.length, startStation.length, endStation.length <= 10 
 All strings consist of uppercase and lowercase English letters and digits. 
 There will be at most 2 * 10⁴ calls in total to checkIn, checkOut, and 
getAverageTime. 
 Answers within 10⁻⁵ of the actual value will be accepted. 
 

 Related Topics Hash Table String Design 👍 3492 👎 173

*/