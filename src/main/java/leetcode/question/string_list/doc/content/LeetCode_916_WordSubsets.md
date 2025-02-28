<p>You are given two string arrays <code>words1</code> and <code>words2</code>.</p>

<p>A string <code>b</code> is a <strong>subset</strong> of string <code>a</code> if every letter in <code>b</code> occurs in <code>a</code> including multiplicity.</p>

<ul> 
 <li>For example, <code>"wrr"</code> is a subset of <code>"warrior"</code> but is not a subset of <code>"world"</code>.</li> 
</ul>

<p>A string <code>a</code> from <code>words1</code> is <strong>universal</strong> if for every string <code>b</code> in <code>words2</code>, <code>b</code> is a subset of <code>a</code>.</p>

<p>Return an array of all the <strong>universal</strong> strings in <code>words1</code>. You may return the answer in <strong>any order</strong>.</p>

<p>&nbsp;</p> 
<p><strong class="example">Example 1:</strong></p>

<div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]</span></p> 
</div>

<p><strong>Output:</strong> <span class="example-io">["facebook","google","leetcode"]</span></p>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lc","eo"]</span></p> 
</div>

<p><strong>Output:</strong> <span class="example-io">["leetcode"]</span></p>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block"> 
 <p><strong>Input:</strong> <span class="example-io">words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc","b"]</span></p> 
</div>

<p><strong>Output:</strong> <span class="example-io">["cccbb"]</span></p>

<p>&nbsp;</p> 
<p><strong>Constraints:</strong></p>

<ul> 
 <li><code>1 &lt;= words1.length, words2.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>1 &lt;= words1[i].length, words2[i].length &lt;= 10</code></li> 
 <li><code>words1[i]</code> and <code>words2[i]</code> consist only of lowercase English letters.</li> 
 <li>All the strings of <code>words1</code> are <strong>unique</strong>.</li> 
</ul>

<div><div>Related Topics</div><div><li>Array</li><li>Hash Table</li><li>String</li></div></div><br><div><li>👍 3486</li><li>👎 312</li></div>