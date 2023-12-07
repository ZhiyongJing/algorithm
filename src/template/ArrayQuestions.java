package template; /**sort  | quick | merge | heap         |
 * time  | nlogn | nlogn | nlogn        |
 * space | O(1)  | O(n)  | O(1)(array)  |
 */

import java.util.*;
public class ArrayQuestions {
    public static int[] mergeTwoSortedArray(int[] nums1, int[] nums2){
        if (nums1 == null && nums2 == null){
            return  null;
        }
        int[] result = new int[nums1.length + nums2.length];
        int i = 0, j = 0, index = 0;
        while(i < nums1.length && j < nums2.length){
            if (nums1[i] < nums2[j]){
                result[index++] = nums1[i++];
            }
            else{
                result[index++] = nums2[j++];
            }
        }
        while (i < nums1.length){
            result[index++] = nums1[i++];
        }
        while (j < nums2.length){
            result[index++] = nums2[j++];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] nums1 =  {1, 2, 3,400};
        int[] nums2 = {6, 7, 8, 9};
        System.out.println(Arrays.toString(mergeTwoSortedArray(nums1, nums2)));
    }
}
