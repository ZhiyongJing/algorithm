package interview.company.amazon;

/**
  *@Question:  535. Encode and Decode TinyURL     
  *@Difculty:  2 [1->Easy, 2->Medium, 3->Hard]
  *@Frequency: 26.91%      
  *@Time  Complexity: O()
  *@Space Complexity: O()
 */

public class LeetCode_535_EncodeAndDecodeTinyurl{
    
//leetcode submit region begin(Prohibit modification and deletion)
public class Codec {
    String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> map = new HashMap<>();
    int count = 1;

    public String getString() {
        int c = count;
        StringBuilder sb = new StringBuilder();
        while (c > 0) {
            c--;
            sb.append(chars.charAt(c % 62));
            c /= 62;
        }
        return sb.toString();
    }

    public String encode(String longUrl) {
        String key = getString();
        map.put(key, longUrl);
        count++;
        return "http://tinyurl.com/" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.replace("http://tinyurl.com/", ""));
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
//leetcode submit region end(Prohibit modification and deletion)

    
    public static void main(String[] args) {
        Solution solution = new LeetCode_535_EncodeAndDecodeTinyurl().new Solution();
        // TO TEST
        //solution.
    }
}
/**

 Note: This is a companion problem to the 
 System Design problem: 
 Design TinyURL.
 

 TinyURL is a URL shortening service where you enter a URL such as https://
leetcode.com/problems/design-tinyurl and it returns a short URL such as http://
tinyurl.com/4e9iAk. Design a class to encode a URL and decode a tiny URL. 

 There is no restriction on how your encode/decode algorithm should work. You 
just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can 
be decoded to the original URL. 

 Implement the Solution class: 

 
 Solution() Initializes the object of the system. 
 String encode(String longUrl) Returns a tiny URL for the given longUrl. 
 String decode(String shortUrl) Returns the original long URL for the given 
shortUrl. It is guaranteed that the given shortUrl was encoded by the same object. 
 

 
 Example 1: 

 
Input: url = "https://leetcode.com/problems/design-tinyurl"
Output: "https://leetcode.com/problems/design-tinyurl"

Explanation:
Solution obj = new Solution();
string tiny = obj.encode(url); // returns the encoded tiny url.
string ans = obj.decode(tiny); // returns the original url after decoding it.
 

 
 Constraints: 

 
 1 <= url.length <= 10⁴ 
 url is guranteed to be a valid URL. 
 

 Related Topics Hash Table String Design Hash Function 👍 1992 👎 3774

*/