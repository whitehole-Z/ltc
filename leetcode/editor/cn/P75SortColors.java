package editor.cn;
//颜色分类

//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。 
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。 
//
// 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,0,1]
//输出：[0,1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[0]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 300 
// nums[i] 为 0、1 或 2 
// 
//
// 
//
// 进阶： 
//
// 
// 你可以不使用代码库中的排序函数来解决这道题吗？ 
// 你能想出一个仅使用常数空间的一趟扫描算法吗？ 
// 
// Related Topics 数组 双指针 排序 
// 👍 949 👎 0

import java.util.HashMap;
import java.util.Map;

public class P75SortColors{
    public static void main(String[] args){
        Solution solution = new P75SortColors().new Solution();
        //Test
        int[] a = {1};
        solution.sortColors(a);
    }
    
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        //快拍 划分 O(n)
    public void sortColors(int[] nums) {
        int l=0,r=nums.length-1,i=0;
        while (i<=r){
            if(nums[i] == 0){
                swap(nums,i,l);
                i++;
                l++;
            }else if(nums[i] == 1){
                i++;
            }else{
                swap(nums,i,r);
                r--;
            }
        }
    }

    private void swap(int[] nums,int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
    //桶排序，O（2n）
//    public void sortColors(int[] nums) {
//        Map<Integer,Integer> times = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            times.put(nums[i],times.getOrDefault(nums[i],0) + 1);
//        }
//
//        int len1 = times.getOrDefault(0,0);
//        int len2 = len1 + times.getOrDefault(1,0);
//        for (int i = 0; i < nums.length; i++) {
//            if(i<len1){
//                nums[i] = 0;
//            }else if(i<len2){
//                nums[i] = 1;
//            }else{
//                nums[i] = 2;
//            }
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)


}