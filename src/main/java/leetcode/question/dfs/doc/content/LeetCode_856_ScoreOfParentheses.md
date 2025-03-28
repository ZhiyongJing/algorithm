<p>Given a balanced parentheses string <code>s</code>, return <em>the <strong>score</strong> of the string</em>.</p>

<p>The <strong>score</strong> of a balanced parentheses string is based on the following rule:</p>

<ul> 
 <li><code>"()"</code> has score <code>1</code>.</li> 
 <li><code>AB</code> has score <code>A + B</code>, where <code>A</code> and <code>B</code> are balanced parentheses strings.</li> 
 <li><code>(A)</code> has score <code>2 * A</code>, where <code>A</code> is a balanced parentheses string.</li> 
</ul>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = "()"
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = "(())"
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = "()()"
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>2 &lt;= s.length &lt;= 50</code></li> 
 <li><code>s</code> consists of only <code>'('</code> and <code>')'</code>.</li> 
 <li><code>s</code> is a balanced parentheses string.</li> 
</ul>

<div><div>Related Topics</div><div><li>String</li><li>Stack</li></div></div><br><div><li>👍 5300</li><li>👎 185</li></div>