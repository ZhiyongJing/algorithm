package leetcode.question.queue;

import javafx.util.Pair;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *@Question:  353. Design Snake Game
 *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 78.93%
 *@Time  Complexity: O(1) per move
 *@Space Complexity: O(W * H + N) where N is length of food, O(W * H ) is snake and snake_set data structure.
 */
/**
 * ===============================================
 * LeetCode 353. Design Snake Game
 * ===============================================
 *
 * 【一、题目描述】
 * 设计一个模拟贪吃蛇游戏的数据结构：
 * - 游戏在一个 width × height 的二维网格中进行；
 * - 贪吃蛇初始长度为 1，从左上角位置 (0, 0) 开始；
 * - 每次调用 move(direction) 方法移动蛇的头部，方向包括：U(上), D(下), L(左), R(右)；
 * - 若蛇成功吃到食物，蛇的长度增加 1，得分 +1；
 * - 若蛇碰到边界或自身，则游戏结束，返回 -1；
 * - 食物必须按顺序出现；
 *
 * 要实现：
 * - 构造函数 SnakeGame(int width, int height, int[][] food)
 * - 方法 int move(String direction)：返回得分，若游戏结束返回 -1。
 *
 *
 * 【二、解题思路（使用队列+哈希表，模拟蛇身移动）】
 * 为高效判断蛇身和食物位置，我们设计如下数据结构：
 *
 * 1. 用 `Deque<Pair<Integer,Integer>> snake` 保存蛇身体（头部在前）；
 * 2. 用 `HashMap<Pair<Integer,Integer>, Boolean>` 保存当前蛇身位置集合；
 * 3. 每次移动时：
 *    - 根据方向移动头部得到新位置；
 *    - 判断新头是否越界；
 *    - 判断新头是否撞到自己（注意：可以覆盖原来的尾部）；
 *    - 判断是否吃到食物（比较当前食物位置是否等于新头坐标）；
 *      - 若吃到，蛇长度 +1，尾巴保留；
 *      - 若未吃到，移除尾巴；
 *    - 更新蛇身队列与哈希表；
 *    - 返回当前得分（蛇身长度 - 1）。
 *
 * 示例流程：
 * 初始化游戏：width = 4, height = 3, food = [[2,0], [0,1]]
 * 初始蛇：[0,0]
 *
 * move("R") → 头移到 [0,1]，未撞墙未吃食 → 蛇：[0,1]，得分 0
 * move("D") → 头移到 [1,1] → 蛇：[1,1]，得分 0
 * move("D") → 头移到 [2,1] → 蛇：[2,1]，得分 0
 * move("L") → 头移到 [2,0]，吃到食物 → 蛇：[2,0, 2,1]，得分 1
 * move("U") → 头移到 [1,0] → 蛇：[1,0, 2,0]，得分 1
 * move("U") → 头移到 [0,0] → 蛇：[0,0, 1,0]，得分 1
 * move("R") → 头移到 [0,1]，吃到食物 → 蛇：[0,1, 0,0]，得分 2
 *
 *
 * 【三、时间与空间复杂度】
 *
 * 时间复杂度：
 * - 每次移动操作时间复杂度为 O(1)，因为：
 *   - 队首/队尾插入删除是 O(1)；
 *   - 哈希表查找是否撞到自己是 O(1)；
 *
 * 空间复杂度：
 * - 空间复杂度为 O(N)，其中 N 为蛇身体长度：
 *   - 队列与哈希表存储的最大元素数与蛇长度线性相关；
 *   - 食物数组大小为常数；
 */


public class LeetCode_353_DesignSnakeGame{

    //leetcode submit region begin(Prohibit modification and deletion)
    class SnakeGame {

        // 用于快速检查蛇身是否包含某格
        HashMap<Pair<Integer, Integer>, Boolean> snakeMap;

        // 用于记录蛇的身体（头在队首，尾在队尾）
        Deque<Pair<Integer, Integer>> snake;

        // 食物列表，每个坐标为 [row, col]
        int[][] food;

        // 当前吃到的食物编号
        int foodIndex;

        // 游戏区域尺寸
        int width;
        int height;

        /**
         * 构造函数：初始化游戏
         * @param width 游戏宽度
         * @param height 游戏高度
         * @param food 食物位置
         */
        public SnakeGame(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;

            // 初始化蛇身体集合
            this.snakeMap = new HashMap<>();
            this.snakeMap.put(new Pair<>(0, 0), true); // 初始在 [0,0]

            // 初始化蛇身体队列
            this.snake = new LinkedList<>();
            this.snake.offerLast(new Pair<>(0, 0));
        }

        /**
         * 移动蛇并返回得分
         * @param direction "U", "D", "L", "R"
         * @return 当前得分；如果游戏结束返回 -1
         */
        public int move(String direction) {
            // 获取当前蛇头位置
            Pair<Integer, Integer> snakeCell = this.snake.peekFirst();
            int newHeadRow = snakeCell.getKey();
            int newHeadColumn = snakeCell.getValue();

            // 根据方向调整新头位置
            switch (direction) {
                case "U": newHeadRow--; break;
                case "D": newHeadRow++; break;
                case "L": newHeadColumn--; break;
                case "R": newHeadColumn++; break;
            }

            Pair<Integer, Integer> newHead = new Pair<>(newHeadRow, newHeadColumn);
            Pair<Integer, Integer> currentTail = this.snake.peekLast();

            // 边界判断
            boolean crossesBoundary1 = newHeadRow < 0 || newHeadRow >= this.height;
            boolean crossesBoundary2 = newHeadColumn < 0 || newHeadColumn >= this.width;

            // 自撞判断（允许新头位于当前尾部）
//            这是 JavaFX 自带的 Pair<K, V> 类，它已经重写了 equals() 和 hashCode() 方法，也就是说：
//            两个 Pair<Integer, Integer> 只要 key 和 value 相等，即使不是同一个引用，equals() 也会返回 true；
            boolean bitesItself = this.snakeMap.containsKey(newHead)
                    && !(newHead.getKey().equals(currentTail.getKey())
                            && newHead.getValue().equals(currentTail.getValue()));

            if (crossesBoundary1 || crossesBoundary2 || bitesItself) {
                return -1;
            }

            // 判断是否吃到食物
            if ((this.foodIndex < this.food.length)
                    && (this.food[this.foodIndex][0] == newHeadRow)
                    && (this.food[this.foodIndex][1] == newHeadColumn)) {
                this.foodIndex++;
            } else {
                // 正常移动：移除尾巴
                this.snake.pollLast();
                this.snakeMap.remove(currentTail);
            }

            // 添加新头
            this.snake.addFirst(newHead);
            this.snakeMap.put(newHead, true);

            return this.snake.size() - 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        // 初始化游戏：宽4，高3，食物为 (2,0), (0,1)
        int[][] food = {{2, 0}, {0, 1}};
        SnakeGame game = new LeetCode_353_DesignSnakeGame().new SnakeGame(3, 4, food);

        // 模拟移动过程
        System.out.println("Move R → 得分: " + game.move("R")); // (0,1)
        System.out.println("Move D → 得分: " + game.move("D")); // (1,1)
        System.out.println("Move D → 得分: " + game.move("D")); // (2,1)
        System.out.println("Move L → 得分: " + game.move("L")); // (2,0) 吃食物
        System.out.println("Move U → 得分: " + game.move("U")); // (1,0)
        System.out.println("Move U → 得分: " + game.move("U")); // (0,0)
        System.out.println("Move R → 得分: " + game.move("R")); // (0,1) 吃食物
        System.out.println("Move D → 得分: " + game.move("D")); // (1,1)
        System.out.println("Move L → 得分: " + game.move("L")); // (1,0)
        System.out.println("Move D → 得分: " + game.move("D")); // (2,0) 撞身体 → -1
    }
}

/**
Design a Snake game that is played on a device with screen size height x width. 
Play the game online if you are not familiar with the game. 

 The snake is initially positioned at the top left corner (0, 0) with a length 
of 1 unit. 

 You are given an array food where food[i] = (ri, ci) is the row and column 
position of a piece of food that the snake can eat. When a snake eats a piece of 
food, its length and the game's score both increase by 1. 

 Each piece of food appears one by one on the screen, meaning the second piece 
of food will not appear until the snake eats the first piece of food. 

 When a piece of food appears on the screen, it is guaranteed that it will not 
appear on a block occupied by the snake. 

 The game is over if the snake goes out of bounds (hits a wall) or if its head 
occupies a space that its body occupies after moving (i.e. a snake of length 4 
cannot run into itself). 

 Implement the SnakeGame class: 

 
 SnakeGame(int width, int height, int[][] food) Initializes the object with a 
screen of size height x width and the positions of the food. 
 int move(String direction) Returns the score of the game after applying one 
direction move by the snake. If the game is over, return -1. 
 

 
 Example 1: 
 
 
Input
["SnakeGame", "move", "move", "move", "move", "move", "move"]
[[3, 2, [[1, 2], [0, 1]]], ["R"], ["D"], ["R"], ["U"], ["L"], ["U"]]
Output
[null, 0, 0, 1, 1, 2, -1]
 

Explanation
SnakeGame snakeGame = new SnakeGame(3, 2, [[1, 2], [0, 1]]);
snakeGame.move("R"); // return 0
snakeGame.move("D"); // return 0
snakeGame.move("R"); // return 1, snake eats the first piece of food. The 
second piece of food appears at (0, 1).
snakeGame.move("U"); // return 1
snakeGame.move("L"); // return 2, snake eats the second food. No more food 
appears.
snakeGame.move("U"); // return -1, game over because snake collides with border


 
 Constraints: 

 
 1 <= width, height <= 10⁴ 
 1 <= food.length <= 50 
 food[i].length == 2 
 0 <= ri < height 
 0 <= ci < width 
 direction.length == 1 
 direction is 'U', 'D', 'L', or 'R'. 
 At most 10⁴ calls will be made to move. 
 

 Related Topics Array Hash Table Design Queue Simulation 👍 963 👎 341

*/