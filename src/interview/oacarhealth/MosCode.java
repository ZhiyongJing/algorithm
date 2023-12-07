package interview.oacarhealth;
import java.util.*;
public class MosCode {
    public static List<List<String>> findMosCode(String s){
        List<List<String>> result = new ArrayList<>();
        List<String> comb = new ArrayList<>();
        Map<String, String> keyMap = new HashMap<>();
        keyMap.put(".", "E");
        keyMap.put("..", "I");
        keyMap.put("...", "S");
        dfs(keyMap, s, result, comb, 0);
        return result;
    }
    private static void dfs(Map<String, String> keyMap, String s, List<List<String>> result, List<String> comb, int startIndex){
        if (startIndex == s.length()){
            result.add(new ArrayList<>(comb));
        }
        for(int i = startIndex; i < s.length(); i++){
            comb.add(keyMap.get(s.substring(startIndex, i+1)));
            System.out.println(s.substring(startIndex, i+1));
            dfs(keyMap, s, result, comb, i+1);
            comb.remove(comb.size() - 1);
        }

    }

//    private static void dfs(Map<String, String> keyMap, String s, List<List<String>> result, List<String> comb, int startIndex){
//        if (startIndex == s.length()){
//            result.add(new ArrayList<>(comb));
//        }
//        for(int i = startIndex; i < s.length(); i++){
//            comb.add(s.substring(startIndex, i+1));
//            dfs(keyMap, s, result, comb, i+1);
//            comb.remove(comb.size() - 1);
//        }
//
//    }

    public static void perms(int[] nums, List<List<Integer>> result, List<Integer> comb, int startIndex){
        result.add(new ArrayList<>(comb));
        for(int i = startIndex; i < nums.length; i++){
            comb.add(nums[i]);
            perms(nums, result, comb, i + 1);
            comb.remove(comb.size() - 1);
        }

    }

    public static void combnations(int[] nums, List<List<Integer>> result, List<Integer> comb, int startIndex){
        if (comb.size() == nums.length){
        result.add(new ArrayList<>(comb));
        }
        for(int i = 0; i < nums.length; i++){
            if(!comb.contains(nums[i])){
                comb.add(nums[i]);
                combnations(nums, result, comb, i + 1);
                comb.remove(comb.size() - 1);

            }


        }

    }

    public static void main(String[] args) {
        String s = "...";
        System.out.println(findMosCode(s));

        int[] nums = {1,2,3};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> comb = new ArrayList<>();
        perms(nums, result, comb, 0);
        System.out.println(result);

        List<List<Integer>> result1 = new ArrayList<>();
        List<Integer> comb1 = new ArrayList<>();
        combnations(nums, result1, comb1, 0);
        System.out.println(result1);

    }
}
