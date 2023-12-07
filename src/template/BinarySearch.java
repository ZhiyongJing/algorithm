package template;

import java.util.Arrays;

public class BinarySearch {

    // help methods for find_Min_Rectangle_Enclosing_Black_Pixels
    //#################################################
    private static boolean isEmptyCol(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return false;
            }
        }
        return true;
    }

    private static boolean isEmptyRow(char[][] image, int row) {
        for (int i = 0; i < image[row].length; i++) {
            if (image[row][i] == '1') {
                return false;
            }
        }
        return true;
    }

    private static int findTop(char[][] image, int start, int end) {
        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (!isEmptyRow(image, mid)) {
                pos = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return pos;
    }

    private static int findBottom(char[][] image, int start, int end) {
        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (!isEmptyRow(image, mid)) {
                pos = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return pos;
    }

    private static int findLeft(char[][] image, int start, int end) {
        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (!isEmptyCol(image, mid)) {
                pos = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return pos;
    }

    private static int findRight(char[][] image, int start, int end) {
        int pos = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (!isEmptyCol(image, mid)) {
                pos = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return pos;
    }
    //#################################################

    public static boolean search(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = (start + end) / 2;
        while (end >= start) {
            mid = (end + start) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int find_First_Pos_In_Sorted_Array(int[] nums, int target) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = (end + start) / 2;
        int firstPost = -1;
        while (start <= end) {
            mid = (end + start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                firstPost = mid;
                end = mid - 1;
            }
        }
        return firstPost;
    }

    public static int find_Min_In_Rotated_Sorted_Array(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = (end + start) / 2;
        int pos = end;
        while (start <= end) {
            mid = (end + start) / 2;
            if (nums[mid] > nums[pos]) {
                start = mid + 1;
            } else if (nums[mid] < nums[pos]) {
                end = mid - 1;
                pos = mid;
            } else {
                end = mid - 1;
                pos = mid;
            }
        }
        return nums[pos];
    }

    public static int search_In_Rotated_Sorted_Array(int[] nums, int target) {
        int pos = -1;
        int start = 0;
        int end = nums.length - 1;
        
        int mid = start + (end - start) / 2;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if(nums[mid] == target) {
                pos = mid;
                return pos;
            }
            if (nums[mid] >= nums[start]) {
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else  {
                    start = mid + 1;

                }

            } else  {
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else  {
                    end  = mid - 1;
                }

            }

        }
        return pos;
    }

    //For nums[0] < nums[1] and nums[length - 2] > nums[length - 1]
    public static int find_Peak_In_Mountains(int[] nums) {
        int pos = -1;
        if (nums == null | nums.length == 0) {
            return pos;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = start + (end - start) / 2;
        while (start <= end && (mid - 1) >= 0 && (mid + 1) <= nums.length - 1) {
            mid = start + (end - start) / 2;
            if (nums[mid] >= nums[mid - 1] && nums[mid] >= nums[mid + 1]) {
                pos = mid;
                return pos;
            } else if (nums[mid] >= nums[mid - 1] && nums[mid] <= nums[mid + 1]) {
                start = mid + 1;
            } else if (nums[mid] <= nums[mid - 1] && nums[mid] >= nums[mid + 1]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

        }
        return pos;

    }

    public static int find_Min_Rectangle_Enclosing_Black_Pixels(char[][] image, int x, int y) {
        if (image == null | image.length == 0) {
            return -1;
        }
        int top = findTop(image, 0, x);
        int bottom = findBottom(image, x, image.length - 1);
        int left = findLeft(image, 0, y);
        int right = findRight(image, y, image[x].length - 1);
        System.out.println(top);
        System.out.println(bottom);
        System.out.println(left);
        System.out.println(right);
        return (bottom - top + 1) * (right - left + 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1};
//        System.out.println(search(nums,2));
//        System.out.println(find_First_Pos_In_Sorted_Array(nums,1));

//        nums = new int[]{7, 8, 9, 5, 6};
//        System.out.println(find_Min_In_Rotated_Sorted_Array(nums));

//        char[][] numss = {{'1','0', '1','0'},{'0', '1', '1', '0'},{'0', '1', '0', '1'}};
//        System.out.println(find_Min_Rectangle_Enclosing_Black_Pixels(numss, 0, 2));

//        nums = new int[]{1,2,5,4};
//        System.out.println(find_Peak_In_Mountains(nums));

//        nums = new int[]{4, 5, 1, 2, 3};
//        System.out.println(search_In_Rotated_Sorted_Array(nums, 5));
    }
}
