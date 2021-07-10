
首先要对**区间进行排序**，这里先以区间的头来排序，然后在遍历区间。
1，如果```后面区间的头小于当前区间的尾```，
比如当前区间是[3,6]，后面区间是[4,5]或者是[5,9]
说明这两个区间有重复，必须要移除一个，那么要移除哪个呢，为了防止在下一个区间和现有区间有重叠，我们应该让现有区间越短越好，所以应该移除尾部比较大的，保留尾部比较小的。
2，如果```后面区间的头不小于当前区间的尾，说明他们没有重合，不需要移除```

<br>

如下图区间[1,2]和[1,3]有了重叠，我们要移除尾部比较大的，也就是红色的[1,3]区间

![image.png](https://pic.leetcode-cn.com/1609380969-rHsmVx-image.png)



```
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //先排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //记录区间尾部的位置
        int end = intervals[0][1];
        //需要移除的数量
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                //如果重叠了，必须要移除一个，所以count要加1，
                //然后更新尾部的位置，我们取尾部比较小的
                end = Math.min(end, intervals[i][1]);
                count++;
            } else {
                //如果没有重叠，就不需要移除，只需要更新尾部的位置即可
                end = intervals[i][1];
            }
        }
        return count;
    }
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/1609381619-SJYsPn-image.png)


<br>

我把部分**算法题**整理成了PDF文档，截止目前总共有**900多页**，大家可以下载阅读
**链接**：https://pan.baidu.com/s/1hjwK0ZeRxYGB8lIkbKuQgQ 
**提取码**：6666 

#### 如果觉得有用就给个赞吧，还可以关注我的[LeetCode主页](https://leetcode-cn.com/u/sdwwld/)查看更多的详细题解
