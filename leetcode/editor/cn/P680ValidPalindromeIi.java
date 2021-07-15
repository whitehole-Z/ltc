package editor.cn;
//验证回文字符串 Ⅱ

//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 贪心 双指针 字符串 
// 👍 369 👎 0

public class P680ValidPalindromeIi{
    public static void main(String[] args){
        Solution solution = new P680ValidPalindromeIi().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        int l=0,r=s.length()-1;
        while(l<r){
            if(s.charAt(l) !=s.charAt(r)){
                int tr = r-1;
                int tl = l;
                boolean temp = true;
                while (tl<tr){
                    if(s.charAt(tl) !=s.charAt(tr)){
                        temp = false;
                        break;
                    }
                    tl++;
                    tr--;
                }
                if(!temp){
                    tr = r;
                    tl = l+1;
                    while (tl<tr){
                        if(s.charAt(tl) !=s.charAt(tr)){
                            return false;
                        }
                        tl++;
                        tr--;
                    }
                    return true;
                }else{
                    return true;
                }
            }
            l++;
            r--;
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}