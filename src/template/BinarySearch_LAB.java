package template;

import java.util.Arrays;
import java.util.List;

/**
 * @Question:
 * @Difculty:
 * @Time Complexity: O
 * @Space Complexity: O
 * @UpdateTime: [12/20/23 9:53 AM]
 */
public class BinarySearch_LAB {
    //find target value in list with no dup element
    public int binarySearch(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        int start = 0, end = list.size() -1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
            if(list.get(mid) == target) return mid;
            else if (list.get(mid) > target) {
                end = mid;
            } else if (list.get(mid) < target) {
               start = mid;
            }
        }
        if(list.get(start) == target) return start;
        if(list.get(end) == target) return end;
        return -1;
    }

    //find first position of target element in list with dup elements
    public int binarySearchWithFirstPosition(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        int start = 0, end = list.size() -1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
//            if(list.get(mid) == target) end = mid;
//            else if (list.get(mid) > target) {
//                end = mid;
//            } else if (list.get(mid) < target) {
//                start = mid;
//            }
            if(list.get(mid) < target){
                start = mid;
            }
            else {
                end = mid;
            }
        }
        if(list.get(start) == target) return start;
        if(list.get(end) == target) return end;
        return -1;
    }
    //find last position of target element in list with dup elements
    public int binarySearchWithLastPosition(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        int start = 0, end = list.size() -1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
//            if(list.get(mid) == target) start = mid;
//            else if (list.get(mid) > target) {
//                end = mid;
//            } else if (list.get(mid) < target) {
//                start = mid;
//            }
            if(list.get(mid) > target){
                end = mid;
            }
            else start = mid;
        }
        if(list.get(end) == target) return end;
        if(list.get(start) == target) return start;

        return -1;
    }


    //OOOOXXXX firstBadVersion question
    public int findFirstBadVersion(int n){
        int start = 1, end = n;
        while (start + 1 < end){
            int mid = start + (end-start)/2;
            if(isBadVersion(mid)){
                end = mid;
            }
            else start = mid;

        }
        if(isBadVersion(start)){
            return start;
        }
        return end;
    }
    public  boolean isBadVersion( int i ){
        return false;
    }

    //search in a big sorted Array(no end for this list)
    public int searchBigSortedArray(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        //find the right edge of list which cover target;
        int index = 1;
        while(list.get(index - 1) < target){
            index = index * 2;
        }
        int start = 0, end = index -1;
        while(start + 1 < end){
            int mid = start + (end -start)/2;
            if(list.get(mid) < target) start = mid;
            else end = mid;
        }
        if(list.get(start) == target) return start;
        if(list.get(end) == target) return end;
        return -1;
    }

    //find minimum in rotated sorted array
    public int binarySearchMinimum(List<Integer> list){
        if(list == null || list.size() == 0){
            return -1;
        }
        int target = list.get(0);
        int start = 0, end = list.size() - 1;
        while(start + 1 < end){
            int mid = start +  (end -start)/2;
            if(list.get(mid) <= target ){
                end = mid;
            }
            else start = mid;
        }
        if(list.get(start) <=target)
            return list.get(start);
        else return list.get(end);
    }

    //find target in rotated sorted array
    public int binarySearchFindTargetInRotated(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        int start = 0, end = list.size() - 1;
        while(start + 1 < end){
            int mid = start +  (end -start)/2;
            if(list.get(start) < list.get(mid)){
                if(list.get(start) <=target && target <= list.get(mid) ){
                    end = mid;
                }
                else start = mid;
            }
            else {
                if(list.get(mid) <=target && target <=list.get(end)){
                    start = mid;
                }
                else end = mid;


            }
        }
        if(list.get(start) ==target)
            return list.get(start);
        if(list.get(end) ==target)
            return list.get(end);

        return -1;
    }

    //find one peak of mountain
    public int binarySearchPeakMountain(List<Integer> list){
        if(list == null || list.size() == 0) return -1;

        int start = 0, end = list.size() -1;
        while(start + 1 < end){
            int mid = start + (end - start)/2;
            if(list.get(mid) < list.get(mid -1)) end = mid;
            else if(list.get(mid) < list.get(mid + 1)){
                start = mid;
            }
            else end = mid;
        }
        if(list.get(start) < list.get(end)){
            return end;
        }
        return start;

    }





    //闭区间查找（用于发现唯一目标值，不建议）;
    public int binarySearch2(List<Integer> list, int target){
        if(list == null || list.size() == 0){
            return -1;
        }
        int left = 0, right = list.size()-1;
        while(left <= right){
            int mid = left + (right -left)/2;
            if(list.get(mid) == target){
                return mid;
            }
            else if(list.get(mid) > target){
                right = mid -1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        BinarySearch_LAB binarySearchLab = new BinarySearch_LAB();

//        //find target in List with unique and sorted elements
//        System.out.println(binarySearchLab.binarySearch2(Arrays.asList(1, 2, 3, 4, 5, 6), 4));
//        System.out.println(binarySearchLab.binarySearch(Arrays.asList(1, 2, 3, 4, 5, 6), 4));

        //find first position of target element in list
        System.out.println(binarySearchLab.binarySearchWithFirstPosition(Arrays.asList(3, 4, 4, 4, 5, 6), 4));

        //find last position of target element in list
        System.out.println(binarySearchLab.binarySearchWithLastPosition(Arrays.asList(4, 4, 4, 4, 4, 5), 4));
        System.out.println(binarySearchLab.binarySearchWithLastPosition(Arrays.asList(4, 4), 4));//reason why we use start+1 < end, not start < end

//        //find minimum in roated sorted array
//        System.out.println(binarySearchLab.binarySearchMinimum(Arrays.asList(4 ,5, 6, 7, 0, 1, 2, 3)));

//        //find target in rotated sorted array
//        System.out.println(binarySearchLab.binarySearchFindTargetInRotated(Arrays.asList(4 ,5, 6, 7, 0, 1, 2, 3), 2));




    }
}
