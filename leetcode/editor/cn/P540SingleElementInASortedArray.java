package editor.cn;
//有序数组中的单一元素

//给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
// 
//
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 105 
// 
//
// 
//
// 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？ 
// Related Topics 数组 二分查找 
// 👍 250 👎 0

public class P540SingleElementInASortedArray{
    public static void main(String[] args){
        Solution solution = new P540SingleElementInASortedArray().new Solution();
        //Test
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l=0,r=nums.length-1,mid;
        if(nums.length==1) return nums[0];
        while (l<r){
            mid = l+(r-l)/2;
            if(nums[mid] == nums[mid+1]){
                if(mid % 2==0){
                    l = mid +2;
                }else{
                    r = mid-1;
                }
            }else if(nums[mid] == nums[mid-1]){
                if(mid % 2==0){
                    r = mid-2;
                }else{
                    l = mid +1;
                }
            }else{
                return nums[mid];
            }
        }
        return nums[r];
    }
//    public int singleNonDuplicate(int[] nums) {
//        int len = nums.length/2,rst=nums[nums.length-1];
//        for(int i=0;i<len;i++){
//            if(nums[2*i] == nums[2*i+1]){
//                continue;
//            }else{
//                rst = nums[2*i];
//                break;
//            }
//        }
//        return rst;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)


}