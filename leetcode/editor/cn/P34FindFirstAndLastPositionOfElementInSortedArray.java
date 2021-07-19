package editor.cn;
//在排序数组中查找元素的第一个和最后一个位置

//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1110 👎 0

public class P34FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args){
        Solution solution = new P34FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] rst = {-1,-1};
        int mid = binary(nums,target);
        if(mid != -1){
            rst[0] = mid;
            rst[1] = mid;
            for(int i = mid-1;i>=0;i--){
                if(nums[i]==target){
                    rst[0] = i;
                }else{
                    break;
                }
            }
            for(int i = mid+1;i<nums.length;i++){
                if(nums[i]==target){
                    rst[1] = i;
                }else{
                    break;
                }
            }
        }
        return rst;
    }
    private int binary(int[] nums, int target){
        int l =0,r = nums.length-1,mid = 0;
        while (l<=r){
            mid = l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid]<target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return -1;
    }
//    public int[] searchRange(int[] nums, int target) {
//        int l=-1,r=-1;
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i] == target){
//                l=i;
//                break;
//            }
//        }
//        for (int i = nums.length-1; i >=0; i--) {
//            if(nums[i] == target){
//                r=i;
//                break;
//            }
//        }
//        return new int[]{l,r};
//    }
}
//leetcode submit region end(Prohibit modification and deletion)


}