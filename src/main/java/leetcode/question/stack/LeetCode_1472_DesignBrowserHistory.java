package leetcode.question.stack;

import java.util.Stack;

/**
 * 这道题目的主要目标是设计一个浏览器历史记录功能，支持访问、后退和前进等操作。以下是解题思路的详细讲解：
 *
 * 1. **使用两个栈：**
 *    - `history` 栈用于存储浏览历史记录。
 *    - `future` 栈用于存储未来浏览记录（通过后退操作产生的）。
 *
 * 2. **访问新的URL（visit）：**
 *    - 当访问新的URL时，将当前URL加入`history`栈，同时将新URL设为当前URL。
 *    - 需要清空`future`栈中的所有记录，因为进行了新的访问。
 *
 * 3. **后退操作（back）：**
 *    - 从`history`栈中弹出指定步数的记录，并将这些记录放入`future`栈。
 *    - 返回弹出的最后一条记录，作为当前URL。
 *    - 此时，这个URL在浏览历史中被认为是后退了若干步。
 *
 * 4. **前进操作（forward）：**
 *    - 从`future`栈中弹出指定步数的记录，并将这些记录放入`history`栈。
 *    - 返回弹出的最后一条记录，作为当前URL。
 *    - 此时，这个URL在浏览历史中被认为是前进了若干步。
 *
 * 5. **时间复杂度：**
 *    - 访问新的URL（visit）操作的时间复杂度是 O(1)，因为只需要操作栈和赋值。
 *    - 后退操作（back）和前进操作（forward）中，弹出和压入栈的次数受到步数的限制，但步数通常较小，
 *    因此时间复杂度也可以看作 O(1)。
 *
 * 6. **空间复杂度：**
 *    - 使用了两个栈，`history`和`future`，它们的总空间复杂度取决于浏览历史记录的总数。
 *    - 空间复杂度为 O(N)，其中 N 为浏览历史记录的总数。
 *
 * 综上所述，这个设计使用两个栈来维护浏览历史和未来记录，实现了浏览器历史记录的基本功能，且时间和空间复杂度相对较低。
 */


public class LeetCode_1472_DesignBrowserHistory{

    //leetcode submit region begin(Prohibit modification and deletion)
    class BrowserHistory {
        private Stack<String> history, future; // 使用两个栈来管理浏览历史和未来浏览记录
        private String current; // 当前访问的URL

        // 构造函数，初始化浏览器历史，传入初始主页
        public BrowserHistory(String homepage) {
            history = new Stack<String>();
            future = new Stack<String>();
            // 'homepage' 是第一次访问的URL。
            current = homepage;
        }

        // 访问新的URL，将当前URL加入历史记录，将新URL标记为当前URL
        public void visit(String url) {
            // 将 'current' 加入 'history' 栈，并将 'url' 设为 'current'。
            history.push(current);
            current = url;
            // 需要清空 'future' 栈中的所有记录。
            if(future.size() !=0){
                future = new Stack<String>();
            }

        }

        // 后退指定步数，从 'history' 栈中取出记录，放入 'future' 栈
        public String back(int steps) {
            // 从 'history' 栈中弹出元素，并将元素加入 'future' 栈。
            while(steps > 0 && !history.empty()) {
                future.push(current);
                current = history.pop();
                steps--;
            }
            return current;
        }

        // 前进指定步数，从 'future' 栈中取出记录，放入 'history' 栈
        public String forward(int steps) {
            // 从 'future' 栈中弹出元素，并将元素加入 'history' 栈。
            while(steps > 0 && !future.empty()) {
                history.push(current);
                current = future.pop();
                steps--;
            }
            return current;
        }
    }

    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        BrowserHistory browserHistory = new LeetCode_1472_DesignBrowserHistory().new BrowserHistory("www.homepage.com");

        // 访问新的URL
        browserHistory.visit("www.page1.com");
        browserHistory.visit("www.page2.com");

        // 后退两步
        String backResult = browserHistory.back(2);
        System.out.println("Back Result: " + backResult);  // 退回到 www.page1.com

        // 前进一步
        String forwardResult = browserHistory.forward(1);
        System.out.println("Forward Result: " + forwardResult);  // 前进到 www.page2.com
    }
}

/**
You have a browser of one tab where you start on the homepage and you can visit 
another url, get back in the history number of steps or move forward in the 
history number of steps. 

 Implement the BrowserHistory class: 

 
 BrowserHistory(string homepage) Initializes the object with the homepage of 
the browser. 
 void visit(string url) Visits url from the current page. It clears up all the 
forward history. 
 string back(int steps) Move steps back in history. If you can only return x 
steps in the history and steps > x, you will return only x steps. Return the 
current url after moving back in history at most steps. 
 string forward(int steps) Move steps forward in history. If you can only 
forward x steps in the history and steps > x, you will forward only x steps. Return 
the current url after forwarding in history at most steps. 
 

 
 Example: 

 
Input:
["BrowserHistory","visit","visit","visit","back","back","forward","visit",
"forward","back","back"]
[["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],[
"linkedin.com"],[2],[2],[7]]
Output:
[null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.
com","google.com","leetcode.com"]

Explanation:
BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
browserHistory.visit("google.com");       // You are in "leetcode.com". Visit 
"google.com"
browserHistory.visit("facebook.com");     // You are in "google.com". Visit 
"facebook.com"
browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit 
"youtube.com"
browserHistory.back(1);                   // You are in "youtube.com", move 
back to "facebook.com" return "facebook.com"
browserHistory.back(1);                   // You are in "facebook.com", move 
back to "google.com" return "google.com"
browserHistory.forward(1);                // You are in "google.com", move 
forward to "facebook.com" return "facebook.com"
browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit 
"linkedin.com"
browserHistory.forward(2);                // You are in "linkedin.com", you 
cannot move forward any steps.
browserHistory.back(2);                   // You are in "linkedin.com", move 
back two steps to "facebook.com" then to "google.com". return "google.com"
browserHistory.back(7);                   // You are in "google.com", you can 
move back only one step to "leetcode.com". return "leetcode.com"
 

 
 Constraints: 

 
 1 <= homepage.length <= 20 
 1 <= url.length <= 20 
 1 <= steps <= 100 
 homepage and url consist of '.' or lower case English letters. 
 At most 5000 calls will be made to visit, back, and forward. 
 

 Related Topics Array Linked List Stack Design Doubly-Linked List Data Stream 👍
 3674 👎 220

*/