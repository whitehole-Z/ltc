### 解题思路
​​
![QQ20210616-213631@2x.png](https://pic.leetcode-cn.com/1623850600-ueqfet-QQ20210616-213631@2x.png)
  还是快慢指针解法
            快的每次走2  慢的每次走1
                最终相遇
            再让两个节点从相遇点和头开始走,相遇的地方就是入口, 见图
### 代码

```java

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return head;
        }
        /**
        还是快慢指针解法
            快的每次走2  慢的每次走1
                最终相遇
            再让两个节点从相遇点和头开始走,相遇的地方就是入口, 见图
         */
         ListNode fast=head;
         ListNode low=head;
         while(true){
             if(fast==null||fast.next==null) return null;//说明没有环
             fast=fast.next.next;
             low=low.next;
             if(fast==low) break;
         }
      
         fast=head;
         while(fast!=low){
             fast=fast.next;
             low=low.next;
         }
         //相遇点就是入口
         return low;
        
    }
}
```