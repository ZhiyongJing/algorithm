<p>Given an array of strings <code>words</code> (<strong>without duplicates</strong>), return <em>all the <strong>concatenated words</strong> in the given list of</em> <code>words</code>.</p>

<p>A <strong>concatenated word</strong> is defined as a string that is comprised entirely of at least two shorter words (not necessarily distinct)&nbsp;in the given array.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
<strong>Output:</strong> ["catsdogcats","dogcatsdog","ratcatdogcat"]
<strong>Explanation:</strong> "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = ["cat","dog","catdog"]
<strong>Output:</strong> ["catdog"]
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= words.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>1 &lt;= words[i].length &lt;= 30</code></li> 
 <li><code>words[i]</code> consists of only lowercase English letters.</li> 
 <li>All the strings of <code>words</code> are <strong>unique</strong>.</li> 
 <li><code>1 &lt;= sum(words[i].length) &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>Array</li><li>String</li><li>Dynamic Programming</li><li>Depth-First Search</li><li>Trie</li></div></div><br><div><li>👍 3768</li><li>👎 276</li></div>