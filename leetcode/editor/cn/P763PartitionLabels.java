//字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 
//
// 
//
// 示例： 
//
// 
//输入：S = "ababcbacadefegdehijhklij"
//输出：[9,7,8]
//解释：
//划分结果为 "ababcbaca", "defegde", "hijhklij"。
//每个字母最多出现在一个片段中。
//像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
// 
//
// 
//
// 提示： 
//
// 
// S的长度在[1, 500]之间。 
// S只包含小写字母 'a' 到 'z' 。 
// 
// Related Topics 贪心 哈希表 双指针 字符串 
// 👍 523 👎 0

package editor.cn;

import com.sun.corba.se.spi.transport.CorbaAcceptor;

import java.util.*;

//Java：划分字母区间
public class P763PartitionLabels{
    public static void main(String[] args) {
        Solution solution = new P763PartitionLabels().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> partitionLabels(String s) {
        int[][] chars = new int[26][2];
        for(int i=0;i<s.length();i++){
            if(chars[s.charAt(i) - 'a'][0]==0 && s.charAt(i) != s.charAt(0)){
                chars[s.charAt(i) - 'a'][0]=i;
            }
            chars[s.charAt(i) - 'a'][1] = i;
        }

        List<Integer> result = new ArrayList<>();
        Arrays.sort(chars, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0],o2[0]);
            }
        });
        int start = 0;
        int end = 0;
        for(int i=0;i<chars.length;i++){
            if(chars[i][0]<=end){
                end = Math.max(end,chars[i][1]);
            }else{
                result.add(end - start + 1);
                start = chars[i][0];
                end = chars[i][1];
            }
        }
        result.add(end-start+1);
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
