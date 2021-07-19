### 解题思路
1、二分查找，注意边界。

### 执行结果
![34.JPG](https://pic.leetcode-cn.com/1626446935-xvLbPI-34.JPG)


### 代码

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1,-1};
        // 边界：空或0
        if (nums.length < 1) {
            return result;
        }
        // 边界：1个元素
        if (nums.length == 1) {
            if (nums[0] == target) {
                result[0] = 0;
                result[1] = 0;
            }     
            return result;
        }
        
        // 开始二分查找
        int left = 0;
        int right = nums.length - 1;
        int pos = -1;
        while (left < right) {
            int midle = (left + right) / 2;
            // 命中
            if (target == nums[midle]) {
                pos = midle;
                break;
            }
            // 前后两个范围
            if (midle == left || midle == right) {
                if (target == nums[left]) {
                    pos = left;
                    break;
                }
                if (target == nums[right]) {
                    pos = right;
                    break;
                }
                // 没有命中
                break;
            }
            // 继续二分
            if (target > nums[midle]) {
                left = midle;
            } else {
                right = midle;
            }     
        }

        if (pos == -1) {
            return result;
        }
        
        // 向后查找
        for (int i = pos; i < nums.length; i++) {
            if (nums[i] == target) {
                result[1] = i;
            } else {
                break;
            }
        }
        // 向前查找
        for (int i = pos; i >= 0; i--) {
            if (nums[i] == target) {
                result[0] = i;            
            } else {
                break;
            }
        }
        return result;
    }
}
```