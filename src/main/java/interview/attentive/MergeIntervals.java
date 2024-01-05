package interview.attentive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
//         Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
//         LinkedList<int[]> merged = new LinkedList<>();
//         for (int[] i: intervals){
//             if (merged.isEmpty() || merged.getLast()[1] < i[0]){
//                 merged.add(i);
//             }else{
//                 merged.getLast()[1] = Math.max(merged.getLast()[1], i[1]);
//             }

//         }
//         return merged.toArray(new int[merged.size()][]);
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        for(int[] i: intervals){
            if(result.size() == 0 || result.get(result.size() - 1)[1] < i[0]){
                result.add(i);
            }
            else{
                // ArrayList<Integer> last = result.remove(result.size() - 1);
                // last.remove(1);
                // last.set
                // last.add(Math.max())
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() -1)[1], i[1]);
            }
        }
        return result.toArray(new int[result.size()][]);


    }
}
