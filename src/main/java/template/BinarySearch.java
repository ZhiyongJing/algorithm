package template;

public class BinarySearch {

    // 左闭右开区间
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                return mid;
            }
        }
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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        BinarySearch binarySearch = new BinarySearch();
        System.out.println(binarySearch.binarySearch(nums,2));

    }
}
