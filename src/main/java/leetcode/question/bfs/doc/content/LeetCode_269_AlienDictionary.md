<p>There is a new alien language that uses the English alphabet. However, the order of the letters is unknown to you.</p>

<p>You are given a list of strings <code>words</code> from the alien language's dictionary. Now it is claimed that the strings in <code>words</code> are <span data-keyword="lexicographically-smaller-string-alien"><strong>sorted lexicographically</strong></span> by the rules of this new language.</p>

<p>If this claim is incorrect, and the given arrangement of string in&nbsp;<code>words</code>&nbsp;cannot correspond to any order of letters,&nbsp;return&nbsp;<code>"".</code></p>

<p>Otherwise, return <em>a string of the unique letters in the new alien language sorted in <strong>lexicographically increasing order</strong> by the new language's rules</em><em>. </em>If there are multiple solutions, return<em> <strong>any of them</strong></em>.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = ["wrt","wrf","er","ett","rftt"]
<strong>Output:</strong> "wertf"
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = ["z","x"]
<strong>Output:</strong> "zx"
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = ["z","x","z"]
<strong>Output:</strong> ""
<strong>Explanation:</strong> The order is invalid, so return <span><code>""</code></span>.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= words.length &lt;= 100</code></li> 
 <li><code>1 &lt;= words[i].length &lt;= 100</code></li> 
 <li><code>words[i]</code> consists of only lowercase English letters.</li> 
</ul>

<div><div>Related Topics</div><div><li>Array</li><li>String</li><li>Depth-First Search</li><li>Breadth-First Search</li><li>Graph</li><li>Topological Sort</li></div></div><br><div><li>👍 4298</li><li>👎 962</li></div>