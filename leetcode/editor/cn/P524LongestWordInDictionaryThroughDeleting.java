package editor.cn;
//通过删除字母匹配到字典里最长单词

//给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。 
//
// 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
//输出："apple"
// 
//
// 示例 2： 
//
// 
//输入：s = "abpcplea", dictionary = ["a","b","c"]
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 1000 
// s 和 dictionary[i] 仅由小写英文字母组成 
// 
// Related Topics 数组 双指针 字符串 排序 
// 👍 150 👎 0

import java.nio.charset.StandardCharsets;
import java.util.List;

public class P524LongestWordInDictionaryThroughDeleting{
    public static void main(String[] args){
        Solution solution = new P524LongestWordInDictionaryThroughDeleting().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public boolean isSubsequence(String x, String y) {
//        int j = 0;
//        for (int i = 0; i < y.length() && j < x.length(); i++)
//            if (x.charAt(j) == y.charAt(i))
//                j++;
//        return j == x.length();
//    }
//    public String findLongestWord(String s, List < String > d) {
//        String max_str = "";
//        for (String str: d) {
//            if (isSubsequence(str, s)) {
//                if (str.length() > max_str.length() || (str.length() == max_str.length() && str.compareTo(max_str) < 0))
//                    max_str = str;
//            }
//        }
//        return max_str;
//    }
    public String findLongestWord(String s, List<String> dictionary) {
        String rst = "";
        int l = 0,r=s.length()-1;
        int dlen = dictionary.size();
        int[] dl = new int[dlen];
        int[] dr = new int[dlen];
        for (int i = 0; i < dlen; i++) {
            dr[i] = dictionary.get(i).length()-1;
        }
        while (l<=r){
            char litem = s.charAt(l);
            char ritem = s.charAt(r);
            for (int i = 0; i < dlen ; i++) {
                if(dl[i]<=dr[i]){
                    String temp = dictionary.get(i);
                    if(temp.charAt(dl[i]) == litem){
                        dl[i]++;
                    }
                    if(temp.charAt(dr[i]) == ritem){
                        dr[i]--;
                    }
                    if(dl[i]>dr[i]){
                        if(rst.length() < temp.length() || (rst.length() == temp.length() && rst.compareTo(temp)>0)){
                            rst = temp;
                        }
                    }
                }
            }
            l++;
            r--;
        }

        return rst;
    }
//    public String findLongestWord(String s, List<String> dictionary) {
//        String rst = "";
//        int l = 0;
//        int[] dps = new int[dictionary.size()];
//        int len = s.length();
//        while (l<len){
//            for(int i=0;i<dps.length;i++){
//                String temp = dictionary.get(i);
//                if(dps[i]!=-1 && temp.charAt(dps[i]) == s.charAt(l)){
//                    int tlen = temp.length();
//                    if(++dps[i] == tlen){
//                        dps[i] = -1;
//                        if(rst.length()<tlen || (rst.length() == tlen && cmp(rst,temp)>0)){
//                            rst = temp;
//                        }
//                    }
//                }
//            }
//            l++;
//        }
//        return rst;
//    }

    private int cmp(String s,String t){
        for (int i = 0; i < s.length(); i++) {
            int temp = s.charAt(i)-t.charAt(i);
            if(temp<0){
                return -1;
            }else if(temp>0){
                return 1;
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}