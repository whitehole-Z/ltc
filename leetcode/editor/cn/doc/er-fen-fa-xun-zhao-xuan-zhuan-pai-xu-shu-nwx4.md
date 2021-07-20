![图解每日一练.jpg](https://pic.leetcode-cn.com/1615817903-fzmpwZ-%E5%9B%BE%E8%A7%A3%E6%AF%8F%E6%97%A5%E4%B8%80%E7%BB%83.jpg)

---

### 🧠 解题思路

该题与 [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) 非常相似，唯一的区别在于 *nums* 是否有重复数字！

所以我们该题解在 [【二分法】寻找旋转排序数组中最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/solution/er-fen-fa-xun-zhao-xuan-zhuan-pai-xu-shu-9hsi/) 之上，多加了一个对重复数字的处理，这里就不重写题解了，偷个懒~，代码已对应新增重复检测时的逻辑片段。

若你没有做过 *153*，那么可以继续往下看，而做过的小伙伴，可以跳过题解说明，直接看代码片段即可。

---

根据题意，我们第一时间就能通过暴破来解决，用一个变量记录一下当前遍历过程中遇到的最小值是多少，然后遍历结束后，返回最小值即可。

但是，这样的暴破，不优雅！

为什么这么说呢，因为我们知道数组是被旋转了的，所以，在遍历过程中，只要有数字，小于了 *numbers[0]*，那么该数字一定是最小值，你品，你细品。

诶，所以说，就算是暴破，也要有值得被夸赞的亮点。

---

好了，闲言少叙，暴破不是我们的目标，我们的目标是尽量的降低算法的复杂度，所以我们这里采用二分法。

首先，创建两个指针 *left*, *right* 分别指向 *numbers* 首尾数字，然后计算出两指针之间的中间索引值 *middle*，然后我们会遇到以下三种情况：

1. *middle* > *right* ：代表最小值一定在 *middle* 右侧，所以 *left* 移到 *middle + 1* 的位置。
2. *middle* < *right* ：代表最小值一定在 *middle* 左侧或者就是 *middle*，所以 *right* 移到 *middle* 的位置。
3. *middle* 既不大于 *left* 指针的值，也不小于 *right* 指针的值，代表着 *middle* 可能等于 *left* 指针的值，或者 *right* 指针的值，我们这时候只能让 *right* 指针递减，来一个一个找最小值了。

---

### 🎨 图解演示

 ![1.jpg](https://pic.leetcode-cn.com/1616993625-QxaZKN-1.jpg) ![2.jpg](https://pic.leetcode-cn.com/1616993628-ITazyK-2.jpg) ![3.jpg](https://pic.leetcode-cn.com/1616993630-HchNCE-3.jpg) ![4.jpg](https://pic.leetcode-cn.com/1616993632-cvSDKQ-4.jpg) ![5.jpg](https://pic.leetcode-cn.com/1616993634-mDDuGG-5.jpg) 

---

### 🍭 示例代码

```Javascript []
// 二分法
var findMin = function(nums) {
    let left = 0, right = nums.length - 1;
    while(left < right){
        let middle = left + ~~((right - left) / 2);
        if(nums[middle] > nums[right]) left = middle + 1;
        else if(nums[middle] < nums[right]) right = middle;
        else right--;
    }
    return nums[left];
};

// 暴破【不推荐】
function findMin(nums) {
    for(let i = 0; i < nums.length; i++){
        if(nums[i] < nums[0]){
            return nums[i];
        }
    }
    return nums[0];
}
```
```C++ []
class Solution {
public:
    int findMin(vector<int>& nums) {
        int low = 0;
        int high = nums.size() - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            }else if(nums[pivot] > nums[high]){
                low = pivot + 1;
            }else{
                high--;
            }
        }
        return nums[low];
    }
};
```
```Java []
class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            }else if(nums[pivot] > nums[high]){
                low = pivot + 1;
            }else{
                high--;
            }
        }
        return nums[low];
    }
}
```
```Python3 []
class Solution:
    def findMin(self, nums: List[int]) -> int:    
        low, high = 0, len(nums) - 1
        while low < high:
            pivot = low + (high - low) // 2
            if nums[pivot] < nums[high]:
                high = pivot 
            elif nums[pivot] > nums[high]:
                low = pivot + 1
            else :
                high = high - 1
        return nums[low]
```
```C []
int findMin(int* nums, int numsSize) {
    int low = 0;
    int high = numsSize - 1;
    while (low < high) {
        int pivot = low + (high - low) / 2;
        if (nums[pivot] < nums[high]) {
            high = pivot;
        } else if(nums[pivot] > nums[high]) {
            low = pivot + 1;
        }else {
            high--;
        }
    }
    return nums[low];
}
```
```Golang []
func findMin(nums []int) int {
    low, high := 0, len(nums) - 1
    for low < high {
        pivot := low + (high - low) / 2
        if nums[pivot] < nums[high] {
            high = pivot
        } else if(nums[pivot] > nums[high]) {
            low = pivot + 1
        } else {
            high--;
        }
    }
    return nums[low]
}
```

---

### 转身挥手

嘿，少年，做图不易，留下个赞或评论再走吧！谢啦~ 💐

差点忘了，祝你牛年大吉 🐮 ，AC 和 Offer 📑 多多益善~

⛲⛲⛲ 期待下次再见~ 