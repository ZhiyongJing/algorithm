<p>Given a string <code>s</code> and an integer <code>k</code>, return <code>true</code> if you can use all the characters in <code>s</code> to construct <strong>non-empty</strong> <code>k</code> <span data-keyword="palindrome-string">palindrome strings</span> or <code>false</code> otherwise.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "annabelle", k = 2
<strong>Output:</strong> true
<strong>Explanation:</strong> You can construct two palindromes using all characters in s.
Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "leetcode", k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> It is impossible to construct 3 palindromes using all the characters of s.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "true", k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> The only possible solution is to put each character in a separate string.
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>s</code> consists of lowercase English letters.</li> 
 <li><code>1 &lt;= k &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><div>Related Topics</div><div><li>Hash Table</li><li>String</li><li>Greedy</li><li>Counting</li></div></div><br><div><li>👍 1748</li><li>👎 158</li></div>