//给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。 
//
// 
//
// 示例 1： 
//
// 输入：c = 5
//输出：true
//解释：1 * 1 + 2 * 2 = 5
// 
//
// 示例 2： 
//
// 输入：c = 3
//输出：false
// 
//
// 示例 3： 
//
// 输入：c = 4
//输出：true
// 
//
// 示例 4： 
//
// 输入：c = 2
//输出：true
// 
//
// 示例 5： 
//
// 输入：c = 1
//输出：true 
//
// 
//
// 提示： 
//
// 
// 0 <= c <= 231 - 1 
// 
// Related Topics 数学 双指针 二分查找 
// 👍 294 👎 0

package editor.cn;
//Java：平方数之和
public class P633SumOfSquareNumbers{
    public static void main(String[] args) {
        Solution solution = new P633SumOfSquareNumbers().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0,b = (long)Math.sqrt(c);
        long sum = b*b;
        while (a<=b){
            while (sum<c){
                a++;
                sum = a*a + b*b;
            }
            if(sum == c){
                return true;
            }else{
                b--;
                sum = a*a + b*b;
            }
        }
        return sum==c;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
