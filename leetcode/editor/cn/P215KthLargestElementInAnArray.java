package editor.cn;
//数组中的第K个最大元素

//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 104 
// -104 <= nums[i] <= 104 
// 
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 
// 👍 1185 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class P215KthLargestElementInAnArray{
    public static void main(String[] args){
        Solution solution = new P215KthLargestElementInAnArray().new Solution();
        //Test
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
//        return nums[nums.length-k];

        int l=0,r=nums.length-1,p,target = nums.length-k;
        while (true){
            p = findPos(nums,l,r);
            if(p==target){
                return nums[p];
            }else if(p<target){
                l=p+1;
            }else{
                r=p-1;
            }
        }
    }

    public int findPos(int[] nums,int l, int r){
        if(l==r) return l;
        int key=nums[l],first=l;
        for(int i=l+1;i<=r;i++){
            if(nums[i]<=key){
                first++;
                swap(nums,i,first);
            }
        }
        swap(nums,l,first);
        return first;
    }

    private void swap(int[] nums,int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public void quickSort(int[] nums,int first,int last){
        if(first>=last){
            return;
        }
        int l=first,r=last;
        int key = nums[l];
        while (l<r){
            while (l<r && key>=nums[r]){
                r--;
            }
            nums[l] = nums[r];
            while (l<r && key<=nums[l]){
                l++;
            }
            nums[r] = nums[l];
        }
        nums[l]=key;
        quickSort(nums,first,l-1);
        quickSort(nums,l+1,last);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}