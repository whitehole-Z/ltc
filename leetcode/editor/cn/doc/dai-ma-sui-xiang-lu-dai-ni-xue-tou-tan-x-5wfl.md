 

**不少同学对贪心算法还处于朦胧状态，我特意录了一期视频，讲一讲[贪心算法理论基础](https://www.bilibili.com/video/BV1WK4y1R71x)**，这里详细介绍了我们做贪心算法的时候常遇到的问题，相信结合本篇题解，会对你学习贪心有所帮助。

## 思路  

如何使用最少的弓箭呢？

直觉上来看，貌似只射重叠最多的气球，用的弓箭一定最少，那么有没有当前重叠了三个气球，我射两个，留下一个和后面的一起射这样弓箭用的更少的情况呢？

尝试一下举反例，发现没有这种情况。

那么就试一试贪心吧！局部最优：当气球出现重叠，一起射，所用弓箭最少。全局最优：把所有气球射爆所用弓箭最少。

**算法确定下来了，那么如何模拟气球射爆的过程呢？是在数组中移除元素还是做标记呢？**

如果真实的模拟射气球的过程，应该射一个，气球数组就remove一个元素，这样最直观，毕竟气球被射了。

但仔细思考一下就发现：如果把气球排序之后，从前到后遍历气球，被射过的气球仅仅跳过就行了，没有必要让气球数组remote气球，只要记录一下箭的数量就可以了。

以上为思考过程，已经确定下来使用贪心了，那么开始解题。

**为了让气球尽可能的重叠，需要对数组进行排序**。

那么按照气球起始位置排序，还是按照气球终止位置排序呢？

其实都可以！只不过对应的遍历顺序不同，我就按照气球的起始位置排序了。

既然按照起始位置排序，那么就从前向后遍历气球数组，靠左尽可能让气球重复。

从前向后遍历遇到重叠的气球了怎么办？

**如果气球重叠了，重叠气球中右边边界的最小值 之前的区间一定需要一个弓箭**。

以题目示例： [[10,16],[2,8],[1,6],[7,12]]为例，如图：（方便起见，已经排序）

![452.用最少数量的箭引爆气球](https://pic.leetcode-cn.com/1625392414-cttiiW-file_1625392412233)

可以看出首先第一组重叠气球，一定是需要一个箭，气球3，的左边界大于了 第一组重叠气球的最小右边界，所以再需要一支箭来射气球3了。

C++代码如下：

```C++
class Solution {
private:
    static bool cmp(const vector<int>& a, const vector<int>& b) {
        return a[0] < b[0];
    }
public:
    int findMinArrowShots(vector<vector<int>>& points) {
        if (points.size() == 0) return 0;
        sort(points.begin(), points.end(), cmp);

        int result = 1; // points 不为空至少需要一支箭
        for (int i = 1; i < points.size(); i++) {
            if (points[i][0] > points[i - 1][1]) {  // 气球i和气球i-1不挨着，注意这里不是>=
                result++; // 需要一支箭
            }
            else {  // 气球i和气球i-1挨着
                points[i][1] = min(points[i - 1][1], points[i][1]); // 更新重叠气球最小右边界
            }
        }
        return result;
    }
};
```

* 时间复杂度O(nlogn)，因为有一个快排
* 空间复杂度O(1)

可以看出代码并不复杂。

## 注意事项

注意题目中说的是：满足  xstart ≤ x ≤ xend，则该气球会被引爆。那么说明两个气球挨在一起不重叠也可以一起射爆，

所以代码中 `if (points[i][0] > points[i - 1][1])`  不能是>=

## 总结

这道题目贪心的思路很简单也很直接，就是重复的一起射了，但本题我认为是有难度的。

就算思路都想好了，模拟射气球的过程，很多同学真的要去模拟了，实时把气球从数组中移走，这么写的话就复杂了。

而且寻找重复的气球，寻找重叠气球最小右边界，其实都有代码技巧。

贪心题目有时候就是这样，看起来很简单，思路很直接，但是一写代码就感觉贼复杂无从下手。

这里其实是需要代码功底的，那代码功底怎么练？

**多看多写多总结！**


## 其他语言版本


Java：
```java
class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int count = 1;
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > points[i - 1][1]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i][1],points[i - 1][1]);
            }
        }
        return count;
    }
}
```

Python：
```python
class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        if len(points) == 0: return 0
        points.sort(key=lambda x: x[0])
        result = 1
        for i in range(1, len(points)):
            if points[i][0] > points[i - 1][1]: # 气球i和气球i-1不挨着，注意这里不是>=
                result += 1     
            else:
                points[i][1] = min(points[i - 1][1], points[i][1]) # 更新重叠气球最小右边界
        return result
```

Go：

Javascript:
```Javascript
var findMinArrowShots = function(points) {
    points.sort((a, b) => {
        return a[0] - b[0]
    })
    let result = 1
    for(let i = 1; i < points.length; i++) {
        if(points[i][0] > points[i - 1][1]) {
            result++
        } else {
            points[i][1] = Math.min(points[i - 1][1], points[i][1])
        }
    }

    return result
};
```

# 贪心算法力扣题目总结

按照如下顺序刷力扣上的题目，相信会帮你在学习贪心算法的路上少走很多弯路。以下每道题目在力扣题解区都有「代码随想录」的题解。

![image.png](https://pic.leetcode-cn.com/1625389409-qiXLwh-image.png)



------------

**大家好，我是程序员Carl，点击[我的头像](https://mp.weixin.qq.com/s/_DzddsMeQW5JPI6qoC7ARQ)**，查看力扣详细刷题攻略，你会发现相见恨晚！

**如果感觉题解对你有帮助，不要吝啬给一个👍吧！**
