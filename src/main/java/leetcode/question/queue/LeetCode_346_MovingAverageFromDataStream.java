package leetcode.question.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  *@Question:  346. Moving Average from Data Stream     
  *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 52.21%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_346_MovingAverageFromDataStream{
    
//leetcode submit region begin(Prohibit modification and deletion)
class MovingAverage {
    int size, windowSum = 0, count = 0;
    Deque queue = new ArrayDeque<Integer>();

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        ++count;
        // calculate the new sum by shifting the window
        //windowSum is the total sum of queue before poll
        queue.add(val);
        int tail = count > size ? (int)queue.poll() : 0;

        windowSum = windowSum - tail + val;

        return windowSum * 1.0 / Math.min(size, count);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        MovingAverage solution = new LeetCode_346_MovingAverageFromDataStream().new MovingAverage(3);
        // TO TEST
        //solution.
    }
}
/**
Given a stream of integers and a window size, calculate the moving average of 
all integers in the sliding window. 

 Implement the MovingAverage class: 

 
 MovingAverage(int size) Initializes the object with the size of the window 
size. 
 double next(int val) Returns the moving average of the last size values of the 
stream. 
 

 
 Example 1: 

 
Input
["MovingAverage", "next", "next", "next", "next"]
[[3], [1], [10], [3], [5]]
Output
[null, 1.0, 5.5, 4.66667, 6.0]

Explanation
MovingAverage movingAverage = new MovingAverage(3);
movingAverage.next(1); // return 1.0 = 1 / 1
movingAverage.next(10); // return 5.5 = (1 + 10) / 2
movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 

 
 Constraints: 

 
 1 <= size <= 1000 
 -10⁵ <= val <= 10⁵ 
 At most 10⁴ calls will be made to next. 
 

 Related Topics Array Design Queue Data Stream 👍 1602 👎 159

*/