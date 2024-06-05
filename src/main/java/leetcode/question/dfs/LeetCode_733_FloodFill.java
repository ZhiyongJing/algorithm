package leetcode.question.dfs;

/**
 *@Question:  733. Flood Fill
 *@Difculty:  1 [1->Easy, 2->Medium, 3->Hard]
 *@Frequency: 37.51%
 *@Time  Complexity: O(N)
 *@Space Complexity: O(N)
 */

/**
 * ### 解题思路
 *
 * 这道题目要求对图像进行“泛洪填充”，即从指定的起始位置开始，将相邻区域中与起始位置相同颜色的像素填充为新的颜色。
 *
 * #### 步骤详解
 *
 * 1. **DFS遍历**：
 *    - 我们从指定的起始位置 `(sr, sc)` 开始，利用深度优先搜索（DFS）遍历相邻区域。
 *    - 在遍历过程中，如果当前像素的颜色与起始像素的颜色相同，则将当前像素的颜色填充为新的颜色，并继续遍历相邻区域。
 *
 * 2. **递归遍历**：
 *    - 递归地遍历相邻区域，包括上、下、左、右四个方向的相邻像素。
 *    - 对于每个相邻像素，如果其颜色与起始像素的颜色相同，则将其颜色填充为新的颜色，并继续递归遍历其相邻区域。
 *
 * #### 时间和空间复杂度分析
 *
 * #### 时间复杂度
 * - 对于一个给定的像素，最坏情况下，我们可能需要遍历整个图像。因此，时间复杂度为 `O(N)`，其中 `N` 是图像中的像素数量。
 *
 * #### 空间复杂度
 * - 递归调用的深度取决于图像中的相邻区域的数量。在最坏情况下，可能需要遍历整个图像，因此空间复杂度为 `O(N)`。
 */
public class LeetCode_733_FloodFill{

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 定义floodFill方法，用于填充图像中的区域
        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            // 获取起始位置的颜色值
            int color = image[sr][sc];
            // 如果新颜色与起始颜色不同，则执行DFS填充
            if (color != newColor) {
                dfs(image, sr, sc, color, newColor);
            }
            return image;
        }
        // 定义DFS方法，用于填充相邻区域
        public void dfs(int[][] image, int r, int c, int color, int newColor) {
            // 如果当前位置的颜色与起始颜色相同，则填充新颜色，并继续DFS填充相邻区域
            if (image[r][c] == color) {
                image[r][c] = newColor;
                // 填充上方相邻区域
                if (r >= 1) {
                    dfs(image, r - 1, c, color, newColor);
                }
                // 填充左方相邻区域
                if (c >= 1) {
                    dfs(image, r, c - 1, color, newColor);
                }
                // 填充下方相邻区域
                if (r + 1 < image.length) {
                    dfs(image, r + 1, c, color, newColor);
                }
                // 填充右方相邻区域
                if (c + 1 < image[0].length) {
                    dfs(image, r, c + 1, color, newColor);
                }
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new LeetCode_733_FloodFill().new Solution();
        // TO TEST
        //solution.
    }
}

/**
An image is represented by an m x n integer grid image where image[i][j] 
represents the pixel value of the image. 

 You are also given three integers sr, sc, and color. You should perform a 
flood fill on the image starting from the pixel image[sr][sc]. 

 To perform a flood fill, consider the starting pixel, plus any pixels 
connected 4-directionally to the starting pixel of the same color as the starting pixel, 
plus any pixels connected 4-directionally to those pixels (also with the same 
color), and so on. Replace the color of all of the aforementioned pixels with 
color. 

 Return the modified image after performing the flood fill. 

 
 Example 1: 
 
 
Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e.,
 the red pixel), all pixels connected by a path of the same color as the 
starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally 
connected to the starting pixel.
 

 Example 2: 

 
Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]
Explanation: The starting pixel is already colored 0, so no changes are made to 
the image.
 

 
 Constraints: 

 
 m == image.length 
 n == image[i].length 
 1 <= m, n <= 50 
 0 <= image[i][j], color < 2¹⁶ 
 0 <= sr < m 
 0 <= sc < n 
 

 Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 8304 👎 
856

*/