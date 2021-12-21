### 方法一：桶排序。
#
### 思路分析
1. 首先根据 s 构造 HashMap，键值对中的键就是 s[i]，值就是 s[i] 出现的次数。
2. 然后我们构造一个桶的集合，意思就是集合里的每个元素都是一个桶。我们维护第 i 个桶中存放 s 中出现次数为 i 的字符。
3. 从后往前遍历这个桶的集合，并填充要返回的数组 res 即可。
#
### 先放 Java 代码，思路清晰明了，详细注释已写好在代码中。
```java
class Solution {
    public String frequencySort(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return s;
        }

        // 构造 HashMap。Key：s 中的每个元素；Value：对应元素出现的次数（即频率）
        Map<Character, Integer> store = new HashMap<>();

        for (char c : s.toCharArray()) {
            // 填充 HashMap。如果当前 Key c 不存在，getOrDefault() 方法返回默认值 0；
            // 否则返回当前 Key c 对应的 Value。
            // 不管哪种情况最后都在 0 或者 Value 的基础上 +1。
            store.put(c, store.getOrDefault(c, 0) + 1);
        }

        // 构造一个桶的集合（即一系列桶），桶的个数为 s 的长度 +1，因为 buckets[0] 没有意义
        // 目的是将出现频率为 i 的字符放到第 i 个桶里（即 buckets[i]）
        List<Character>[] buckets = new List[s.length() + 1];

        for (char key : store.keySet()) {
            // 某个字符在 HashMap 中的 Value 是几就会被放到第几个桶里
            int value = store.get(key);

            if (buckets[value] == null) {
                // 如果某个桶还未放入过字符（即未初始化），则初始化其为一个数组
                buckets[value] = new ArrayList<Character>();
            }

            buckets[value].add(key); // 然后将字符放到桶中
        }

        StringBuilder res = new StringBuilder();

        for (int i = buckets.length - 1; i > 0; --i) {
            // 遍历每个桶
            if (buckets[i] != null) {
                // 如果桶里有字符
                for (char j : buckets[i]) {
                    // 遍历桶里的每个字符
                    for (int k = i; k > 0; --k) {
                        // 字符出现了几次就向 res 中添加几次该字符
                        res.append(j);
                    }
                }
            }
        }

        return res.toString();
    }
}
```
### 执行结果截图：
![image.png](https://pic.leetcode-cn.com/1611978064-tPDfOB-image.png)
#
### 复杂度分析
- 时间复杂度：O(n)。遍历 s 需要 O(n)；遍历 HashMap 需要 O(n)；遍历桶的集合需要 O(n) x 两个常数，依然是 O(n)。
- 空间复杂度：O(n)。建立 HashMap 需要 O(n)；建立 n 个桶需要 O(n)。
#
### Tracker
1. 1刷：2021/01/30
