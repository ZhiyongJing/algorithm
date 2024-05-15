<p>Given the <code>root</code> of a binary search tree and a node <code>p</code> in it, return <em>the in-order successor of that node in the BST</em>. If the given node has no in-order successor in the tree, return <code>null</code>.</p>

<p>The successor of a node <code>p</code> is the node with the smallest key greater than <code>p.val</code>.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_1.PNG" style="width: 122px; height: 117px;" /> 
<pre>
<strong>Input:</strong> root = [2,1,3], p = 1
<strong>Output:</strong> 2
<strong>Explanation:</strong> 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
</pre>

<p><strong class="example">Example 2:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2019/01/23/285_example_2.PNG" style="width: 246px; height: 229px;" /> 
<pre>
<strong>Input:</strong> root = [5,3,6,2,4,null,null,1], p = 6
<strong>Output:</strong> null
<strong>Explanation:</strong> There is no in-order successor of the current node, so the answer is <span><code>null</code></span>.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li>The number of nodes in the tree is in the range <code>[1, 10<sup>4</sup>]</code>.</li> 
 <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li>All Nodes will have unique values.</li> 
</ul>

<div><div>Related Topics</div><div><li>Tree</li><li>Depth-First Search</li><li>Binary Search Tree</li><li>Binary Tree</li></div></div><br><div><li>👍 2561</li><li>👎 93</li></div>