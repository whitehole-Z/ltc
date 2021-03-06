package editor.cn;
//根据身高重建队列

//假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 
//个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。 
//
// 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第
// j 个人的属性（queue[0] 是排在队列前面的人）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
//输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
//解释：
//编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
//编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
//编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
//编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
//编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
//编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
//因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
// 
//
// 示例 2： 
//
// 
//输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
//输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= people.length <= 2000 
// 0 <= hi <= 106 
// 0 <= ki < people.length 
// 题目数据确保队列可以被重建 
// 
// Related Topics 贪心 数组 排序 
// 👍 924 👎 0

import java.util.*;

public class P406QueueReconstructionByHeight{
    public static void main(String[] args){
        Solution solution = new P406QueueReconstructionByHeight().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int compare = Integer.compare(o2[0], o1[0]);
                if(compare == 0){
                    compare = Integer.compare(o1[1],o2[1]);
                }
                return compare;
            }
        });

        List<int[]> result  = new LinkedList<>();
        for(int[] item:people){
            result.add(item[1],item);
        }
        return result.toArray(people);

//        int[] coordinate  = new int[people.length];
//        for(int i=0;i<people.length;i++){
//            coordinate[i] = people[i][1];
//            for(int j=0;j<i;j++){
//                if(coordinate[j]>=coordinate[i]){
//                    coordinate[j]+=1;
//                }
//            }
//        }
//
//        int[][] result = new int[people.length][2];
//
//        for(int i=0;i<coordinate.length;i++){
//            result[coordinate[i]] = people[i];
//        }
//        return result;

    }
}
//leetcode submit region end(Prohibit modification and deletion)


}