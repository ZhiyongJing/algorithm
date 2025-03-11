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
public class BinarySearch {
    //find target value in list with no dup element
    public int binarySearch(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) == target) return mid;
            else if (list.get(mid) > target) {
                end = mid;
            } else if (list.get(mid) < target) {
                start = mid;
            }
        }
        if (list.get(start) == target) return start;
        if (list.get(end) == target) return end;
        return -1;
    }

    //find first position of target element in list with dup elements
    public int binarySearchWithFirstPosition(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
//            if(list.get(mid) == target) end = mid;
//            else if (list.get(mid) > target) {
//                end = mid;
//            } else if (list.get(mid) < target) {
//                start = mid;
//            }
            if (list.get(mid) < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (list.get(start) == target) return start;
        if (list.get(end) == target) return end;
        return -1;
    }

    //find last position of target element in list with dup elements
    public int binarySearchWithLastPosition(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
//            if(list.get(mid) == target) start = mid;
//            else if (list.get(mid) > target) {
//                end = mid;
//            } else if (list.get(mid) < target) {
//                start = mid;
//            }
            if (list.get(mid) > target) {
                end = mid;
            } else start = mid;
        }
        if (list.get(end) == target) return end;
        if (list.get(start) == target) return start;

        return -1;
    }


    //OOOOXXXX firstBadVersion question
    public int findFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else start = mid;

        }
        if (isBadVersion(start)) {
            return start;
        }
        return end;
    }

    public boolean isBadVersion(int i) {
        return false;
    }

    //search in a big sorted Array(no end for this list)
    public int searchBigSortedArray(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        //find the right edge of list which cover target;
        int index = 1;
        while (list.get(index - 1) < target) {
            index = index * 2;
        }
        int start = 0, end = index - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < target) start = mid;
            else end = mid;
        }
        if (list.get(start) == target) return start;
        if (list.get(end) == target) return end;
        return -1;
    }

    //find minimum in rotated sorted array
    public int binarySearchMinimum(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int target = list.get(0);
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) <= target) {
                end = mid;
            } else start = mid;
        }
        if (list.get(start) <= target)
            return list.get(start);
        else return list.get(end);
    }

    //find target in rotated sorted array
    public int binarySearchFindTargetInRotated(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(start) < list.get(mid)) {
                if (list.get(start) <= target && target <= list.get(mid)) {
                    end = mid;
                } else start = mid;
            } else {
                if (list.get(mid) <= target && target <= list.get(end)) {
                    start = mid;
                } else end = mid;


            }
        }
        if (list.get(start) == target)
            return list.get(start);
        if (list.get(end) == target)
            return list.get(end);

        return -1;
    }

    //find one peak of mountain
    public int binarySearchPeakMountain(List<Integer> list) {
        if (list == null || list.size() == 0) return -1;

        int start = 0, end = list.size() - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) < list.get(mid - 1)) end = mid;
            else if (list.get(mid) < list.get(mid + 1)) {
                start = mid;
            } else end = mid;
        }
        if (list.get(start) < list.get(end)) {
            return end;
        }
        return start;

    }


    //闭区间查找（用于发现唯一目标值，不建议）;
    public int binarySearch2(List<Integer> list, int target) {
        if (list == null || list.size() == 0) {
            return -1;
        }
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }


    public int findLeftMost(int nums[], int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums[nums.length - 1] < target) return nums.length;

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }

    /* 模版一：（双闭区间） */
    int binarySearch1(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 left > right 时为空）
        while (left <= right) {
            int m = left + (right - left) / 2; // 计算中点索引 m
            if (nums[m] < target) // 此情况说明 target 在区间 [m+1, right] 中
                left = m + 1;
            else if (nums[m] > target) // 此情况说明 target 在区间 [left, m-1] 中
                right = m - 1;
            else // 找到目标元素，返回其索引
                return m;
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /* 模版二，向左偏移 */
    //mid 向下取整，使其更靠近 left。
    //这样可以保证 当目标值重复出现时，搜索会往左靠拢，最终 left 会停在 target 的最左侧位置。
    int binarySearch2LeftMost(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 left = right 时为空）
        while (left < right) {
            int mid = left + (right - left) / 2; // 计算中点索引 m

            //第一种写法， 容易理解
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] == target) {
                right = mid;
            } else {
                right = mid - 1;
            }

//            //第二种写法： 小于一定不是解
//            if (nums[mid] < target) {
//                // 下一轮搜索区间是 [mid + 1..right]
//                left = mid + 1;
//            } else {
//                // nums[mid] > target，下一轮搜索区间是 [left..mid]
//                right = mid;
//            }
        }
        // 退出循环以后不能确定 nums[left] 是否等于 target，因此需要再判断一次
        if (nums[left] == target) {
            return left;
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /* 模版三，向右偏移 */
    int binarySearch3RightMost(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间为空时跳出（当 left = right 时为空）
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            //第一种写法， 容易理解
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                left = mid;
            }

//            //第二种写法
//            if (nums[mid] > target) {
//                // 下一轮搜索区间是 [left..mid - 1]
//                right = mid - 1;
//            } else {
//                // 下一轮搜索区间是 [mid..right]
//                left = mid;
//            }
        }

        // 退出循环以后不能确定 nums[right] 是否等于 target，因此需要再判断一次
        if (nums[right] == target) {
            return right;
        }
        // 未找到目标元素，返回 -1
        return -1;
    }

    /* 模版四，专门解决stack overflow, 找到某个值*/
    int binarySearch4(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间仅剩下left 和 right 两个时跳出
        while (left + 1 < right) {
            int m = left + (right - left) / 2; // 计算中点索引 m
            if (nums[m] < target) // 此情况说明 target 在区间 [m, right] 中
                left = m;
            else if (nums[m] > target) // 此情况说明 target 在区间 [left, m] 中
                right = m;
            else { // 找到目标元素，返回其索引
                return m;
            }
        }
        return -1;
    }

    /* 模版四，专门解决stack overflow, 找到最左某个值*/
    int binarySearch4FindMostLeft(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间仅剩下left 和 right 两个时跳出

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            //第一种写法,容易理解
            if (nums[mid] < target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                right = mid; //找到目标值，继续向左找
            }
//            //第二种写法
//            if (nums[mid] < target) {
//                left = mid;
//            } else {
//                right = mid;
//            }
        }

        //Condition: left + 1 == right， 需要向左偏移(find most left position)
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;


    }

    /* 模版四，专门解决stack overflow, 找到最右某个值*/
    int binarySearch4FindMostRight(int[] nums, int target) {
        // 初始化双闭区间 [0, n-1] ，即 left, right 分别指向数组首元素、尾元素
        int left = 0, right = nums.length - 1;
        // 循环，当搜索区间仅剩下left 和 right 两个时跳出
        //Find Most right
        while (left + 1 < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            //第一种写法
            if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                left = mid; //继续向右查找
            }
//            //第二种写法
//            if (nums[mid] > target) {
//                right = mid;
//            } else {
//                left = mid;
//            }
        }

        //Condition: left + 1 == right， 需要向右偏移(find most right position)
        if (nums[right] == target) return right;
        if (nums[left] == target) return left;
        return -1;

    }



public static void main(String[] args) {
    BinarySearch binarySearchLab = new BinarySearch();

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
