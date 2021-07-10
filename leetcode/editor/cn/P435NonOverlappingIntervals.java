package editor.cn;
//无重叠区间

//给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。 
//
// 注意: 
//
// 
// 可以认为区间的终点总是大于它的起点。 
// 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2], [2,3], [3,4], [1,3] ]
//
//输出: 1
//
//解释: 移除 [1,3] 后，剩下的区间没有重叠。
// 
//
// 示例 2: 
//
// 
//输入: [ [1,2], [1,2], [1,2] ]
//
//输出: 2
//
//解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,2], [2,3] ]
//
//输出: 0
//
//解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
// 
// Related Topics 贪心 数组 动态规划 排序 
// 👍 447 👎 0

import java.util.Arrays;
import java.util.Comparator;

public class P435NonOverlappingIntervals{
    public static void main(String[] args){
        Solution solution = new P435NonOverlappingIntervals().new Solution();
        //Test
        int[][] ints = {
                {10,99},{10,33},{10,192},{10,22},{10,66},{1,12},{2,44}
        };
        solution.eraseOverlapIntervals(ints);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int num = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0] !=0 ?o1[0]-o2[0] :o1[1]-o2[1];
            }
        });
        int end =0;
        for(int i=0;i<intervals.length;i++){
            if(i==0){
                end=intervals[i][1];
            }else{
                if(intervals[i][0]>=end){
                    end=intervals[i][1];
                }else{
                    if(intervals[i][1] < end){
                        end = intervals[i][1];
                    }
                    num++;
                }
            }
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}