**解题思路**
* 我们希望划分尽可能多的区间，且同一个字母只能出现在一个区间。
* 所以我们的划分策略可定义如下：**从i出开始遍历，判断当前遍历是否超出了记录的最远处值，如果未超过，更新bound，且继续向后遍历，如果超出了，说明可以划分一个区间**。
* 举例说明，我们首先记录每个字母出现的最远位置。然后，对于`"ababcbacadefegdehijhklij"`来说，我们首先从a开始，a出现的最远位置索引为8，bound记做8。继续向后遍历，遍历到b，b出现的最远距离为5，bound不需要更新，继续向后，后面依次，直到索引遍历到8，说明之前的字母的索引值最大即到此，可以做出一个划分。然后更新起始位置，继续做如上的操作。
* 本题中，我们用i表示开始，cur表示当前遍历的位置。当可以做出一个划分时，片段长度为cur-i+1，然后更新`i = cur + 1`

```
class Solution {
    public List<Integer> partitionLabels(String S) {
        char[] arr = S.toCharArray();
        int[] local = new int[26];
        for(int i=0;i<26;i++){
            local[i] = -1;
        }
        for(int i=0;i<arr.length;i++){
            local[arr[i]-'a'] = i;
        }
        List<Integer> res = new ArrayList<>();
        int i=0;
        while(i < arr.length){
            int cur = i;
            int bound = local[arr[cur] - 'a'];
            while(cur < bound){
                cur++;
                bound = Math.max(bound,local[arr[cur] - 'a']);
            }
            res.add(cur - i + 1);
            i = cur + 1;
        }
        return res;
    }
}
```