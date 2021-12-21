package editor.cn;
//根据字符出现频率排序

//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。 
//
// 示例 1: 
//
// 
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
// 
//
// 示例 2: 
//
// 
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
// 
//
// 示例 3: 
//
// 
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
// 
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列） 
// 👍 322 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P451SortCharactersByFrequency{
    public static void main(String[] args){
        Solution solution = new P451SortCharactersByFrequency().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String frequencySort(String s) {
        Map<Character,Integer> nums = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            nums.put(aChar,nums.getOrDefault(aChar,0)+1);
        }
        ArrayList<Character>[] bubblets = new ArrayList[s.length()+1];
        for(Character item :nums.keySet()){
            if(bubblets[nums.get(item)] == null) bubblets[nums.get(item)] = new ArrayList<>();
            bubblets[nums.get(item)].add(item);
        }

        int len = s.length();
        StringBuilder sb = new StringBuilder();
        for (int i = len; i >=0; i--) {
            if(bubblets[i]==null) continue;
            for (Character character : bubblets[i]) {
                for (int j = 0; j < i; j++) {
                    sb.append(character);
                }
            }
        }
        return sb.toString();



//        Character[] chars2 = nums.keySet().toArray(new Character[0]);
//        Integer[] times = nums.values().toArray(new Integer[0]);
//        quicksort(chars2,times,0,times.length-1);
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < chars2.length; i++) {
//            int j = 0 ;
//            while (j<times[i]){
//                sb.append(chars2[i]);
//                j++;
//            }
//        }
//        return sb.toString();
    }

//    private void quicksort(Character[] chars,Integer[] nums,int l,int r){
//        if(l>=r) return;
//        int first = l,last = r,key = nums[l];
//        char charitem = chars[l];
//        while (first<last){
//            while (first<last && nums[last]<=key){
//                last--;
//            }
//            nums[first] = nums[last];
//            chars[first] = chars[last];
//            while (first<last && nums[first]>=key){
//                first++;
//            }
//            nums[last] = nums[first];
//            chars[last] = chars[first];
//        }
//        nums[first] = key;
//        chars[first] = charitem;
//        quicksort(chars,nums,l,first-1);
//        quicksort(chars,nums,first+1,r);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)


}