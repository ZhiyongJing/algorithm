<p>The <strong>beauty</strong> of a string is the difference in frequencies between the most frequent and least frequent characters.</p>

<ul> 
 <li>For example, the beauty of <code>"abaacc"</code> is <code>3 - 1 = 2</code>.</li> 
</ul>

<p>Given a string <code>s</code>, return <em>the sum of <strong>beauty</strong> of all of its substrings.</em></p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "aabcb"
<strong>Output:</strong> 5
<strong>Explanation: </strong>The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "aabcbaa"
<strong>Output:</strong> 17
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;=<sup> </sup>500</code></li> 
 <li><code>s</code> consists of only lowercase English letters.</li> 
</ul>

<div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Counting</li></div></div><br><div><li>👍 1251</li><li>👎 192</li></div>