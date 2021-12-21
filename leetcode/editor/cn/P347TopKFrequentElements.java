package editor.cn;
//前 K 个高频元素

//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 
// 👍 803 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P347TopKFrequentElements{
    public static void main(String[] args){
        Solution solution = new P347TopKFrequentElements().new Solution();
        int[] a = {1,1,1,2,2,3};
        solution.topKFrequent(a,2);
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer temp = map.get(nums[i]);
            if(temp==null){
                temp = 0;
            }
            map.put(nums[i],temp+1);
        }
        Integer[] item = map.keySet().toArray(new Integer[0]);
        Integer[] times = map.values().toArray(new Integer[0]);
        int len = item.length;
        quickSort(item,times,0,times.length-1);
        System.out.println(Arrays.toString(item));
        System.out.println(Arrays.toString(times));
        int[] rst = new int[k];
        for(int i=0;i<k;i++){
            rst[i] = item[i];
        }
        return rst;
    }

    public void quickSort(Integer[] item,Integer[] nums,int first,int last){
        if(first>=last){
            return;
        }
        int l=first,r=last;
        int key = nums[l];
        int key2 = item[l];
        while (l<r){
            while (l<r && key>=nums[r]){
                r--;
            }
            item[l] = item[r];
            nums[l] = nums[r];
            while (l<r && key<=nums[l]){
                l++;
            }
            item[r] = item[l];
            nums[r] = nums[l];
        }
        nums[l]=key;
        item[l]=key2;
        quickSort(item,nums,first,l-1);
        quickSort(item,nums,l+1,last);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}