package template;

import java.util.ArrayList;
import java.util.List;

/**
 * @Question:
 * @Difculty:
 * @Time Complexity: O
 * @Space Complexity: O
 * @UpdateTime: [12/15/23 1:10 PM]
 */
public class DFS_LAB {
    public List<List<Integer>> combination(int n){
        List<List<Integer>> result = new ArrayList<>();
        combinationHelper(1, n, result, new ArrayList<>());
        return result;
    }

    public void combinationHelper(int startIndex, int n, List<List<Integer>> result, List<Integer> comb){
//        if(comb.size() == n){
//            result.add(new ArrayList<>(comb));
//        }
//        result.add(new ArrayList<>(comb));
        for(int i = startIndex; i <=n; i++){

            comb.add(i);
            result.add(new ArrayList<>(comb));
            combinationHelper(i+1, n, result, comb);
            comb.remove(comb.size() - 1);
        }
    }

    public List<List<Integer>> permutation(int n){
        List<List<Integer>> result = new ArrayList<>();
        permutationHelper(1, n, result, new ArrayList<>());
        return result;
    }

    public void permutationHelper(int startIndex, int n, List<List<Integer>> result, List<Integer> perm){


        for(int i = 1; i <=n; i++){
            if(perm.contains(i)){
                continue;
            }
            perm.add(i);
            if(perm.size() == n){
                result.add(new ArrayList<>(perm));
            }
            permutationHelper(i+1, n, result, perm);
            perm.remove(perm.size()-1);

        }

    }

    public static void main(String[] args) {
        DFS_LAB r = new DFS_LAB();
//        System.out.println(r.combination(3));
        System.out.println(r.permutation(3));
    }
}
