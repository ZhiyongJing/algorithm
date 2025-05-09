<p>Given a string <code>s</code> and a dictionary of strings <code>wordDict</code>, add spaces in <code>s</code> to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in <strong>any order</strong>.</p>

<p><strong>Note</strong> that the same word in the dictionary may be reused multiple times in the segmentation.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
<strong>Output:</strong> ["cats and dog","cat sand dog"]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
<strong>Output:</strong> ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
<strong>Explanation:</strong> Note that you are allowed to reuse a dictionary word.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
<strong>Output:</strong> []
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 20</code></li> 
 <li><code>1 &lt;= wordDict.length &lt;= 1000</code></li> 
 <li><code>1 &lt;= wordDict[i].length &lt;= 10</code></li> 
 <li><code>s</code> and <code>wordDict[i]</code> consist of only lowercase English letters.</li> 
 <li>All the strings of <code>wordDict</code> are <strong>unique</strong>.</li> 
 <li>Input is generated in a way that the length of the answer doesn't exceed&nbsp;10<sup>5</sup>.</li> 
</ul>

<div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>String</li><li>Dynamic Programming</li><li>Backtracking</li><li>Trie</li><li>Memoization</li></div></div><br><div><li>👍 7167</li><li>👎 538</li></div>