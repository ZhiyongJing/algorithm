package template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuickSelection {
    //Way 1: implement by recursion
    public int quickSelectTopKthLargest(List<Integer> nums, int k) {
        int pivotIndex = new Random().nextInt(nums.size());
        int pivot = nums.get(pivotIndex);

        List<Integer> left = new ArrayList<>();
        List<Integer> mid = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int num : nums) {
            if (num > pivot) {
                left.add(num);
            } else if (num < pivot) {
                right.add(num);
            } else {
                mid.add(num);
            }
        }

        if (k <= left.size()) {
            return quickSelectTopKthLargest(left, k);
        }

        if (left.size() + mid.size() < k) {
            return quickSelectTopKthLargest(right, k - left.size() - mid.size());
        }

        return pivot;
    }

    //Way 2: implement by recursion
    public class QuickSelect {

        public int quickSelect(int[] nums, int k) {
            if (k > 0 && k <= nums.length) {
                return quickSelect(nums, 0, nums.length - 1, k);
            } else {
                return -1; // 或者抛出异常，表示输入的 k 不合法
            }
        }

        private int quickSelect(int[] nums, int left, int right, int k) {
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == k - 1) {
                return nums[pivotIndex];
            } else if (pivotIndex < k - 1) {
                return quickSelect(nums, pivotIndex + 1, right, k);
            } else {
                return quickSelect(nums, left, pivotIndex - 1, k);
            }
        }

        private int partition(int[] nums, int left, int right) {
            int pivotIndex = choosePivot(nums, left, right);
            int pivotValue = nums[pivotIndex];

            swap(nums, pivotIndex, right);
            int currentIndex = left;

            for (int i = left; i < right; i++) {
                if (nums[i] < pivotValue) {
                    swap(nums, i, currentIndex);
                    currentIndex++;
                }
            }

            swap(nums, currentIndex, right);

            return currentIndex;
        }

        private int choosePivot(int[] nums, int left, int right) {
            return right; // 选择最右边的元素作为枢纽
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        public void main(String[] args) {
            int[] nums = {3, 1, 4, 4, 2, 2, 5, 3, 9};
            int k = 4;
            int result = quickSelect(nums, k);
            System.out.println("The " + k + "-th smallest element is: " + result);
        }
    }



    //Way 3: implement by non-recursion, stack
    //TODO


    public static void main(String[] args) {
        QuickSelection q = new QuickSelection();
        List<Integer> list = Arrays.asList(3, 2, 3, 1, 2, 4, 5, 5, 5, 6);
        System.out.println(q.quickSelectTopKthLargest( list, 3));
    }
}
