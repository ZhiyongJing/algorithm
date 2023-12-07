package leetcode.done;

//240. Search a 2D Matrix II
//Medium
public class LeetCode240_Search_A_2D_Matrix_II {
    class Solution {
        private boolean binarySearch(int[][] matrix, int target, int start, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length-1 : matrix.length-1;

            while (hi >= lo) {
                int mid = (lo + hi)/2;
                if (vertical) { // searching a column
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else { // searching a row
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }

            return false;
        }

        public boolean searchMatrix(int[][] matrix, int target) {
            // an empty matrix obviously does not contain `target`
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            // iterate over matrix diagonals
            int shorterDim = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < shorterDim; i++) {
                boolean verticalFound = binarySearch(matrix, target, i, true);
                boolean horizontalFound = binarySearch(matrix, target, i, false);
                if (verticalFound || horizontalFound) {
                    return true;
                }
            }

            return false;
        }
    }
//    class Solution {
//        public boolean searchMatrix(int[][] matrix, int target) {
//            // start our "pointer" in the bottom-left
//            int row = matrix.length-1;
//            int col = 0;
//
//            while (row >= 0 && col < matrix[0].length) {
//                if (matrix[row][col] > target) {
//                    row--;
//                } else if (matrix[row][col] < target) {
//                    col++;
//                } else { // found it
//                    return true;
//                }
//            }
//
//            return false;
//        }
//    }
}
