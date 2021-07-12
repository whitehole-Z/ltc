package editor.cn;
//最小覆盖子串

//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1234 👎 0

import java.util.HashMap;
import java.util.Map;

public class P76MinimumWindowSubstring{
    public static void main(String[] args){
        Solution solution = new P76MinimumWindowSubstring().new Solution();
        //Test
        solution.minWindow("ADOBECODEBANC","ABC");
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Map<Character,Integer> sArrs = new HashMap<>();
    Map<Character,Integer> tArrs = new HashMap<>();

    public String minWindow(String s, String t) {
        if("".equals(s) || "".equals(t)){return "";}
        int len = s.length();
        int tlen = t.length();
        int start = 0;
        int end = 1;
        int minstart = 0,minend = 0;
        for(int i=0;i<tlen;i++){
            tArrs.put(t.charAt(i),tArrs.getOrDefault(t.charAt(i),0)+1);
        }
        while (end<=len){
            char c = s.charAt(end-1);
            sArrs.put(c,sArrs.getOrDefault(c,0)+1);
            while (end-start>=tlen && check()){
                if(minend==0 || end - start < minend - minstart){
                    minend = end;
                    minstart = start;
                }
                char c2 = s.charAt(start);
                sArrs.put(c2,sArrs.get(c2)-1);
                start++;
            }
            end++;
        }
        return s.substring(minstart,minend);
    }

    private boolean check(){
        for(Character titem:tArrs.keySet()){
            if (sArrs.get(titem) ==null || sArrs.get(titem)<tArrs.get(titem)){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}