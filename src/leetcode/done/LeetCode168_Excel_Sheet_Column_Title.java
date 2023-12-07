package leetcode.done;

//168. Excel Sheet Column Title
//Easy
public class LeetCode168_Excel_Sheet_Column_Title {
    class Solution {
         String convertToTitle(int n) {
            StringBuilder stringBuilder = new StringBuilder();
            while (n>0){
                System.out.println(stringBuilder.toString());
                n--;
                stringBuilder.insert(0, (char)('A'+n%26));
                n/=26;
            }
            return stringBuilder.toString();
        }
    }

}
